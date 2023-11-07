package com.example.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.SanPhamService; 



@Controller
public class DetailController {

	@Autowired
	SanPhamService SPService;
	
	@Autowired
	SanPhamDao sanPhamDao;

	@RequestMapping("/detail")
	public String detail() {
		return "user/product/detail";
	}

	@GetMapping("/detail/{id_sp}")
	public String details(Model model, @PathVariable("id_sp") String id_sp) {
		SanPhamEntity item = sanPhamDao.findById(id_sp).get();
		model.addAttribute("item", item);
		return "user/product/detail";
	}
}
