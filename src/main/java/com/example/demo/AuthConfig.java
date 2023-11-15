//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.example.demo.Service.TaiKhoanService;
//import com.example.demo.Service.UserService;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class AuthConfig{
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	TaiKhoanService taiKhoanService;
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(authz -> authz
//						.anyRequest().permitAll())
//				
//				.formLogin(login -> login.loginPage("/Admin/TaiKhoan/DangNhap").loginProcessingUrl("/home/login")
//						.defaultSuccessUrl("/index", false).failureUrl("/Admin/TaiKhoan/DangNhap")
//						.usernameParameter("username").passwordParameter("password"))
//				.rememberMe(remem -> remem.rememberMeParameter("remember").tokenValiditySeconds(86400));
//		http.exceptionHandling(ex -> ex.accessDeniedPage("/Admin/TaiKhoan/DangNhap"));
//		return http.build();
//	}
//	
//	protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//		auth.userDetailsService(userService);
////		auth.inMemoryAuthentication()
////		.withUser("user").password(passwordEncoder().encode("123"))
////		.authorities("USER").and()
////		.withUser("admin").password(passwordEncoder().encode("234"))
////		.authorities("USER", "ADMIN").and()
////		.withUser("guest").password(passwordEncoder().encode("456"))
////		.authorities("USER", "ADMIN", "GUEST");
//	}
//	
//
//	
//}
