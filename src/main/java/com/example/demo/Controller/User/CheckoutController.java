package com.example.demo.Controller.User;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.HoaDonChiTietDao;
import com.example.demo.Dao.HoaDonDao;
import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Entity.HoaDonEntity;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Service.GioHangService;
import com.example.demo.Service.SessionService;


@Controller
public class CheckoutController {

	@Autowired
	HoaDonDao dao;

	@Autowired
	SanPhamDao pdao;

	@Autowired
	HoaDonChiTietDao ddao;

	@Autowired
	GioHangService cart;

	@Autowired
	SessionService session;

	@RequestMapping("/checkout")
	public String index(Model model) {
		return "user/checkout/checkout";
	}
	
	@GetMapping("/checkout/a")
	public String hiiii(Model model) {
		return "user/checkout/success";
	}

	@PostMapping("/checkout")
	public String success(Model model, @RequestParam("address") String address) {
		HoaDonEntity order = new HoaDonEntity();
		order.setNgaytaohoadon(new Date());
		order.setDiachi(address);
		dao.save(order);
		cart.getItems().clear();
		return "user/success";
	}
}
