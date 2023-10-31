package com.example.demo.Admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Admin.Dao.VaiTroDao;
import com.example.demo.Admin.Entity.TaiKhoanEntity;
import com.example.demo.Admin.Entity.VaiTroEntity;
import com.example.demo.Admin.Service.VaiTroService;

@Controller
public class VaiTroController {
	
	@Autowired
	VaiTroService VTService;
	
	@GetMapping("/Admin/VaiTro")
	public String TaiKhoanGet(Model model)
	{
		VaiTroEntity VTEntity = new VaiTroEntity();
		model.addAttribute("VTEntity",VTEntity);
		
		List<VaiTroEntity> ListVTService = VTService.findAll();
		model.addAttribute("VTService", ListVTService);
		
		return "Admin/TaiKhoan/VaiTro";
	}
	@GetMapping("/Admin/VaiTro/Edit/{id}")
	public String VaiTroEdit(Model model, @PathVariable("id") String id)
	{
		model.addAttribute("VTEntity", VTService.findById(id));
		
		List<VaiTroEntity> ListVTService = VTService.findAll();
		model.addAttribute("VTService", ListVTService);
		
		return "Admin/TaiKhoan/VaiTro";
	}
	

//	
//	
//	@PostMapping("/Admin/TaiKhoan/Create")
//	public String TaiKhoanCreate(@Validated @ModelAttribute("TKEntity")TaiKhoanEntity TKEntity, Errors errors, Model model)
//	{
//		if (errors.hasErrors()) {
//			
//			model.addAttribute("TKEntity",TKEntity);
//			List<TaiKhoanEntity> ListTKService = TKService.findAll();
//			model.addAttribute("TKService", ListTKService);
//			
//			return "Admin/TaiKhoan/TaiKhoan";
//		}
//		else {
//			TKService.create(TKEntity);
//		}
//		
//		
//		return "redirect:/Admin/TaiKhoan";
//	}
//	@PostMapping("/Admin/TaiKhoan/Update")
//	public String TaiKhoanUpdate(@Validated @ModelAttribute("TKEntity")TaiKhoanEntity TKEntity, Errors errors, Model model)
//	{
//		
//		if (errors.hasErrors()) {
//			
//			model.addAttribute("TKEntity",TKEntity);
//			List<TaiKhoanEntity> ListTKService = TKService.findAll();
//			model.addAttribute("TKService", ListTKService);
//			
//			return "Admin/TaiKhoan/TaiKhoan";
//		}
//		else {
//			TKService.create(TKEntity);
//		}
//		
//		return "redirect:/Admin/TaiKhoan";
//	}
//	
//	
//	
//	@PostMapping("/Admin/TaiKhoan/Reset")
//	public String TaiKhoanReset(@ModelAttribute("TKAttribute")TaiKhoanEntity TKAttribute)
//	{
//		
//		return "redirect:/Admin/TaiKhoan";
//	}
}
