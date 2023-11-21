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

import com.example.demo.Entity.BinhLuanEntity;
import com.example.demo.Service.BinhLuanService;

@CrossOrigin("*")
@RestController
public class BinhLuanRestcontroller {
	@Autowired
	BinhLuanService BLSerVice;
	
	@GetMapping("/rest/binhluan")
	public ResponseEntity<Collection<BinhLuanEntity>> getAll(Model model)
	{
		return ResponseEntity.ok(BLSerVice.findAll());
	}
	@GetMapping("/rest/binhluan/{id}")
	public ResponseEntity<BinhLuanEntity> getById(@PathVariable("id") String id){
		
		return ResponseEntity.ok(BLSerVice.findById(id));
	}
	@PostMapping("/rest/binhluan")
	public ResponseEntity<BinhLuanEntity> post(@RequestBody BinhLuanEntity BinhLuanEntity) throws Exception{
		
		BLSerVice.create(BinhLuanEntity);
		return ResponseEntity.ok(BinhLuanEntity);
	}
	
	@DeleteMapping("/rest/binhluan/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		
		BLSerVice.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
