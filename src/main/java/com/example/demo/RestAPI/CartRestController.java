package com.example.demo.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.SanPhamService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class CartRestController {
	
	@Autowired
	SanPhamService sanPhamService;
	
	@GetMapping("{id_sp}")
	public SanPhamEntity getById(@PathVariable("id_sp") int id_sp) {
		return sanPhamService.findById(id_sp);
	}

}
