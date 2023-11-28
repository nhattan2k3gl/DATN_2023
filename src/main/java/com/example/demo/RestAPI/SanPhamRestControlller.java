package com.example.demo.RestAPI;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.SanPhamService;

@CrossOrigin("*")
@RestController
public class SanPhamRestControlller {
	@Autowired
	SanPhamService SPDao;
	
	@GetMapping("/rest/sanpham")
	public ResponseEntity<Collection<SanPhamEntity>> getAll(Model model)
	{
		return ResponseEntity.ok(SPDao.findAll());
	}
	@GetMapping("/rest/sanpham/{id}")
	public ResponseEntity<SanPhamEntity> getById(@PathVariable("id") int id){
		
		return ResponseEntity.ok(SPDao.findById(id));
	}
	@PostMapping("/rest/sanpham")
	public ResponseEntity<SanPhamEntity> post(@RequestBody SanPhamEntity SanPhamEntity) throws Exception{
		
		SPDao.create(SanPhamEntity);
		return ResponseEntity.ok(SanPhamEntity);
	}
	
	@DeleteMapping("/rest/sanpham/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		
		SPDao.delete(id);
		return ResponseEntity.ok().build();
	}
}
