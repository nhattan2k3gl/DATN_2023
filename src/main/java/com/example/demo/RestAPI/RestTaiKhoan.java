package com.example.demo.RestAPI;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Service.TaiKhoanService; 



@CrossOrigin("*")
@RestController
public class RestTaiKhoan {
	
	@Autowired
	TaiKhoanService TKService;
	
	@GetMapping("/Rest/TaiKhoan")
	public ResponseEntity<Collection<TaiKhoanEntity>> getAll(Model model)
	{
		return ResponseEntity.ok(TKService.findAll());
	}
	@GetMapping("/Rest/TaiKhoan/{email}")
	public ResponseEntity<TaiKhoanEntity> getOne(@PathVariable("email") String email){
		
		System.out.println(email);
		return ResponseEntity.ok(TKService.findByUsername(email));
	}
	@PostMapping("/Rest/TaiKhoan")
	public ResponseEntity<TaiKhoanEntity> post(@RequestBody TaiKhoanEntity TaiKhoanEntity){
		
		TKService.create(TaiKhoanEntity);
		return ResponseEntity.ok(TaiKhoanEntity);
	}
	@PutMapping("/Rest/TaiKhoan/{email}")
	public ResponseEntity<TaiKhoanEntity> put(@PathVariable("email") String email,@RequestBody TaiKhoanEntity TaiKhoanEntity){
		
		TKService.create(TaiKhoanEntity);
		return ResponseEntity.ok(TaiKhoanEntity);
	}
	@DeleteMapping("/Rest/TaiKhoan/{email}")
	public ResponseEntity<Void> delete(@PathVariable("email") String email){
		
		TKService.delete(email);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/rest/profile/update")
	public ResponseEntity<TaiKhoanEntity> updateProfile( @RequestBody TaiKhoanEntity taiKhoanEntity) {
		return ResponseEntity.ok(TKService.update(taiKhoanEntity));
	}
}
