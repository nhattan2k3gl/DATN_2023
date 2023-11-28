package com.example.demo.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.HoaDonEntity;
import com.example.demo.Service.HoaDonService;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	HoaDonService hoaDonService;
	
	@GetMapping
	public ResponseEntity<HoaDonEntity> getAllHoaDon(String id_hd){
		return ResponseEntity.ok(hoaDonService.findById(id_hd));
	}

	@PostMapping
	public ResponseEntity<HoaDonEntity> create(@RequestBody JsonNode orderData) {
		return ResponseEntity.ok(hoaDonService.crt(orderData));
	}
}
