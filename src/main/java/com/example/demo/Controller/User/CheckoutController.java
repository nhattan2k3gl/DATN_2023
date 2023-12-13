package com.example.demo.Controller.User;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.HoaDonChiTietDao;
import com.example.demo.Dao.HoaDonDao;
import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Entity.Item;
import com.example.demo.Entity.HoaDonChiTietEntity;
import com.example.demo.Entity.HoaDonEntity;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Service.GioHangService;
import com.example.demo.Service.HoaDonService;
import com.example.demo.Service.SessionService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class CheckoutController {

	@Autowired
	HoaDonDao dao;
	
	@Autowired
	HoaDonService hoaDonService;

	@Autowired
	SanPhamDao pdao;

	@Autowired
	HoaDonChiTietDao ddao;

	@Autowired
	GioHangService cart;

	@Autowired
	SessionService session;

	@GetMapping("/order/view")
	public String index(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		return "user/order/checkout";
	}
	
	@GetMapping("/order/success")
	public String hiiii(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		return "user/order/success";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		model.addAttribute("request", request);
		model.addAttribute("hoadon", hoaDonService.findById(id));
		return "user/order/detail";
	}

	@RequestMapping("/order/list")
	public String detail(Model model, HttpServletRequest request) {
		String email = request.getRemoteUser();
		model.addAttribute("request", request);
		model.addAttribute("dshoadon", hoaDonService.findByUsername(email));
		return "user/order/list";
	}
	
	
}
