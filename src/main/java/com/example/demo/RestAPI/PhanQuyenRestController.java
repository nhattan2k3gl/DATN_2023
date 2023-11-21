package com.example.demo.RestAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.PhanQuyenEntity;
import com.example.demo.Service.PhanQuyenService;
import com.example.demo.Service.TaiKhoanService;
import com.example.demo.Service.VaiTroService;



@RestController
public class PhanQuyenRestController {

	@Autowired
	PhanQuyenService PQService;

	@Autowired
	VaiTroService VTService;

	@Autowired
	TaiKhoanService TKService;

	@GetMapping("/Rest/PhanQuyen")
	public Map<String, Object> getAuthorities() {
		Map<String, Object> data = new HashMap<>();
		data.put("phanquyen", PQService.findAll());
		data.put("vaitro", VTService.findAll());
		data.put("taikhoan", TKService.findAll());
		return data;
	}
	
	@GetMapping("/Rest/PhanQuyen1")
	public ResponseEntity<List<PhanQuyenEntity>> getAuthorities1() {
		
		return ResponseEntity.ok(PQService.findAll());
	}
	
	@PostMapping("/Rest/PhanQuyen/Create")
	public PhanQuyenEntity create(@RequestBody PhanQuyenEntity PQEnitty) {
		return PQService.create(PQEnitty);
	}

	@DeleteMapping("/Rest/PhanQuyen/Delete/{id}")
	public void delete(@PathVariable("id") String id) {
		PQService.delete(id);
	}
}
