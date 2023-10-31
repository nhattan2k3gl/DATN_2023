package com.example.demo.User.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Admin.Dao.TheLoaiDao;
import com.example.demo.Admin.Entity.TheLoaiEntity;
import com.example.demo.Admin.Service.SanPhamService;
import com.example.demo.Admin.Service.TheLoaiService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	TheLoaiService theLoaiService;
	
	@Autowired
	TheLoaiDao theLoaiDao;
	
	@ModelAttribute("current")
	public String request(Model model, HttpServletRequest request) {
		return request.getRemoteUser();
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "user/home/contact";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		return "user/home/about";
	}
	
	@GetMapping("/index")
	public String homeIndex(Model model, @RequestParam(name = "page") Optional<Integer> page) {
		
		model.addAttribute("items", sanPhamService.findAll(page.orElse(0), 8));
		return "user/home/index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model, @RequestParam(name = "field", defaultValue = "") String field,
						@RequestParam(name = "page") Optional<Integer> page) {
		if (field.equals("")) {
			model.addAttribute("items", sanPhamService.findAll(page.orElse(0), 9));
		} else {
			model.addAttribute("items", sanPhamService.findAll(page.orElse(0), 9));
		}
		List<TheLoaiEntity> list = theLoaiService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("product", sanPhamService.countAllProduct());
		return "user/home/shop";
	}
	
	
}
