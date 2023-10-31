package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.csrf().disable();
      http.authorizeHttpRequests()
              .requestMatchers("/auth/**").permitAll()
              .requestMatchers("/role1/**").hasAnyRole("admin")
              .requestMatchers("/role2/**").hasAnyRole("admin")
              .requestMatchers("/role3/**").hasAnyRole("admin")
              .anyRequest()
              .permitAll();
     return http.build();
  }
}
