package com.example.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Item;
import com.example.demo.Service.GioHangService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CartController {
	@Autowired
	GioHangService gioHangService;


	@GetMapping("/cart")
	public String cartProduct(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		return "user/cart/cart";
	}

}
