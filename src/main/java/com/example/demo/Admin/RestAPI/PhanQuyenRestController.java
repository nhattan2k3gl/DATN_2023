package com.example.demo.Admin.RestAPI;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Admin.Entity.PhanQuyenEntity;
import com.example.demo.Admin.Service.PhanQuyenService;
import com.example.demo.Admin.Service.TaiKhoanService;
import com.example.demo.Admin.Service.VaiTroService;



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

	@PostMapping("/Rest/PhanQuyen/Create")
	public PhanQuyenEntity create(@RequestBody PhanQuyenEntity PQEnitty) {
		return PQService.create(PQEnitty);
	}

	@DeleteMapping("/Rest/PhanQuyen/Delete/{id}")
	public void delete(@PathVariable("id") String id) {
		PQService.delete(id);
	}
}
