package com.example.demo.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {

	@GetMapping("/Admin/TrangChu" )
	public String TrangChuGet(@CookieValue(value = "email", defaultValue = "123", required = false) String email)
	{
//		model.addAttribute("email",email);
		System.out.println(email +"?");
		return "Admin/TrangChu/TrangChu";
	}
}
