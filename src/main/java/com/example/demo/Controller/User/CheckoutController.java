package com.example.demo.Controller.User;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CheckoutController {

	@RequestMapping("/checkout")
	public String index(Model model) {
		return "user/checkout/checkout";
	}
	
	@RequestMapping("/checkout/list")
	public String list(Model model) {
		return "user/checkout/list";
	}
	
	@RequestMapping("/checkout/detail/{id}")
	public String detail(Model model) {
		return "user/checkout/detail";
	}

	@PostMapping("/checkout")
	public String success(Model model, @RequestParam("phone") String phone, @RequestParam("address") String address) {
		return "user/success";
	}
}
