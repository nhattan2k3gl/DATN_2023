package com.example.demo.Controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.TheLoaiEntity;
import com.example.demo.Service.TheLoaiService; 

@Controller
public class TheLoaiController {
	
	@Autowired
	TheLoaiService TLService;
	
	@GetMapping("/Admin/TheLoai")
	public String TheLoaiGet(Model model)
	{
		TheLoaiEntity TLEntity=new TheLoaiEntity();
		model.addAttribute("TLEntity",TLEntity);
		
		List<TheLoaiEntity> ListTLService = TLService.findAll();
		model.addAttribute("TLService", ListTLService);
		
		return "Admin/SanPham/TheLoai";
	}
	@GetMapping("/Admin/TheLoai/Edit/{id}")
	public String TheLoaiEdit(Model model, @PathVariable("id") String id)
	{
		model.addAttribute("TLEntity", TLService.findById(id));
		
		List<TheLoaiEntity> ListTLService = TLService.findAll();
		model.addAttribute("TLService", ListTLService);
		
		return "Admin/SanPham/TheLoai";
	}
	@GetMapping("/Admin/TheLoai/Delete/{id}")
	public String TheLoaiDeleteId(Model model, @PathVariable("id") String id)
	{
		
		TLService.delete(id);
		
		
		return "redirect:/Admin/TheLoai";
	}
	@PostMapping("/Admin/TheLoai/Create")
	public String TheLoaiCreate(@Validated @ModelAttribute("TLEntity")TheLoaiEntity TLEntity, Errors errors, Model model)
	{
		if (errors.hasErrors()) {
			
			model.addAttribute("TLEntity",TLEntity);
			List<TheLoaiEntity> ListTLService = TLService.findAll();
			model.addAttribute("TLService", ListTLService);
			
			for (FieldError error : errors.getFieldErrors()) {
				System.out.println("Field: " + error.getField());
				System.out.println("Message: " + error.getDefaultMessage());
			}
			
			return "Admin/SanPham/TheLoai";
		}
		else {
			TLService.create(TLEntity);
		}
		
		
		return "redirect:/Admin/TheLoai";
	}
	@PostMapping("/Admin/TheLoai/Update")
	public String TheLoaiUpdate(@Validated @ModelAttribute("TLEntity")TheLoaiEntity TLEntity, Errors errors, Model model)
	{
		if (errors.hasErrors()) {
			
			model.addAttribute("TLEntity",TLEntity);
			List<TheLoaiEntity> ListTLService = TLService.findAll();
			model.addAttribute("TLService", ListTLService);
			
			return "Admin/TaiKhoan/TheLoai";
		}
		else {
			TLService.create(TLEntity);
		}
		
		
		return "redirect:/Admin/TheLoai";
	}
}
