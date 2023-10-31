package com.example.demo.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Admin.Entity.SanPhamEntity;
import com.example.demo.Admin.Service.SanPhamService;



@Controller
public class DetailController {

	@Autowired
	SanPhamService SPService;

	@RequestMapping("/detail")
	public String detail() {
		return "user/product/detail";
	}

	@GetMapping("/detail/{id}")
	public String details(Model model, @PathVariable("id") String id) {
		SanPhamEntity item = SPService.findById(id);
		model.addAttribute("item", item);
		return "user/product/detail";
	}
}
