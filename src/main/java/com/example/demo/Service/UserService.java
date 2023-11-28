package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Dao.VaiTroDao;
import com.example.demo.Entity.TaiKhoanEntity;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	TaiKhoanDao taiKhoanDao;
	
	@Autowired
	TaiKhoanService taiKhoanService;
	
	@Autowired
	VaiTroDao vaiTroDao;
	
	@Autowired
	VaiTroService vaiTroService;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    try {
	    	
	        TaiKhoanEntity taikhoan = taiKhoanService.findByUsername(username);
	        
	        System.out.println("Username: " + taikhoan.getEmail());
	        System.out.println("Encoded Password: " + taikhoan.getMatkhau());
	        System.out.println("Role: " + taikhoan.getVaitro().stream().map(role -> new SimpleGrantedAuthority(role.getTenvaitro())).collect(Collectors.toList()));

	        String[] roles = taikhoan.getVaitro().stream().map(role -> role.getTenvaitro())
					.collect(Collectors.toList()).toArray(new String[0]);
	        
	        System.out.println(roles);
	        
//	        return new org.springframework.security.core.userdetails.User(
//	        		taikhoan.getEmail(),
//	        		taikhoan.getMatkhau(), // Ensure the password is encoded
//	        		taikhoan.getVaitro().stream().map(role -> new SimpleGrantedAuthority(role.getTenvaitro())).collect(Collectors.toList()));
	        
	        return User.withUsername(taikhoan.getEmail()).password(taikhoan.getMatkhau()).roles(roles).build();

	    } catch (Exception e) {
	        e.printStackTrace(); // In ra stack trace để kiểm tra lỗi
	        throw new UsernameNotFoundException(username + " not found");
	    }
	}
	

}
















