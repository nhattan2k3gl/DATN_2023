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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.TheLoaiDao;
import com.example.demo.Entity.TheLoaiEntity;
import com.example.demo.Service.TheLoaiService;

@CrossOrigin("*")
@RestController
public class TheLoaiRestController {

	@Autowired
	TheLoaiService TLService;
	
	@GetMapping("/rest/theloai")
	public ResponseEntity<Collection<TheLoaiEntity>>getAll (Model model)
	{
		return ResponseEntity.ok(TLService.findAll());
	}
	@GetMapping("/rest/theloai/{id}")
	public ResponseEntity<TheLoaiEntity>getById(@PathVariable("id") String id)
	{
		return ResponseEntity.ok(TLService.findById(id));
	}
	@PostMapping("/rest/theloai")
	public ResponseEntity<TheLoaiEntity>post(@RequestBody TheLoaiEntity theloaientity) throws Exception
	{
		TLService.create(theloaientity);
		return ResponseEntity.ok(theloaientity);
	}
	@DeleteMapping("/rest/theloai/{id}")
	public ResponseEntity<Void>delete (@PathVariable("id") String id)
	{
		TLService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
