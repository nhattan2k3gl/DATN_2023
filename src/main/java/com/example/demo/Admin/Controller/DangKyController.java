package com.example.demo.Admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Admin.Dao.TaiKhoanDao;
import com.example.demo.Admin.Entity.TaiKhoanEntity;
import com.example.demo.Admin.Service.TaiKhoanService;

@Controller
public class DangKyController {
	@Autowired
	TaiKhoanService TKService;
	
	@GetMapping("/Admin/DangKy")
	public String DangKyget(Model model )
	{
		
		TaiKhoanEntity TKEntity= new TaiKhoanEntity();
		model.addAttribute("TKEntity",TKEntity);

		return "Admin/TaiKhoan/DangKy";
	}

	
	@PostMapping("/Admin/DangKy/Create")
	public String TaiKhoanCreate( @ModelAttribute("TKEntity") TaiKhoanEntity TKEntity, Errors errors, Model model)
	{
		if (errors.hasErrors()) {
			List<TaiKhoanEntity> ListTKDao = TKService.findAll();
			model.addAttribute("TKDao", ListTKDao);
			return "Admin/TrangChu/Trangchu";
		}
		
		TKService.create(TKEntity);
		return "redirect:/Admin/TrangChu";
	}
	
}
