package com.example.demo.RestAPI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Dao.TheLoaiDao;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Entity.TheLoaiEntity;
import com.example.demo.Service.GioHangService;
import com.example.demo.Service.SanPhamService;
import com.example.demo.Service.SessionService;
import com.example.demo.Service.TheLoaiService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class HomeRestController {
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	SanPhamDao sanPhamDao;
	
	@Autowired
	TheLoaiService theLoaiService;
	
	@Autowired
	TheLoaiDao theLoaiDao;
	
	@Autowired
	GioHangService gioHangService;
	
	@Autowired
	SessionService session;
	
	@GetMapping
	public ResponseEntity<List<SanPhamEntity>> search(@RequestParam String tentheloai) {
		return ResponseEntity.ok(sanPhamService.findAllByTentheLoai(tentheloai));
	}
}
