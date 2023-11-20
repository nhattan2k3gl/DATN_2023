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

import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.SanPhamService; 

@Controller
public class SanPhamController {
	@Autowired
	SanPhamService SPService;

	@GetMapping("/Admin/SanPham")
	public String SanPhamGet(Model model) {
		
		return "Admin/SanPham/SanPham";
	}

	@GetMapping("/Admin/SanPham/Edit/{id}")
	public String BinhLuanEdit(Model model, @PathVariable("id") String id) {
		model.addAttribute("SPEntity", SPService.findById(id));

		List<SanPhamEntity> ListSPService = SPService.findAll();
		model.addAttribute("SPService", ListSPService);

		return "Admin/SanPham/SanPham";
	}

	@GetMapping("/Admin/SanPham/Delete/{id}")
	public String SanPhamDeleteId(Model model, @PathVariable("id") String id) {

		SPService.delete(id);

		return "redirect:/Admin/SanPham";
	}

	@PostMapping("/Admin/SanPham/Create")
	public String SanPhamCreate(@Validated @ModelAttribute("SPEntity") SanPhamEntity SPEntity, Errors errors,
			Model model) throws Exception{
		
		if (errors.hasErrors()) {

			model.addAttribute("SPEntity", SPEntity);
			List<SanPhamEntity> ListSPService = SPService.findAll();
			model.addAttribute("SPService", ListSPService);
			
			for (FieldError error : errors.getFieldErrors()) {
				System.out.println("Field: " + error.getField());
				System.out.println("Message: " + error.getDefaultMessage());
			}
			
			return "Admin/SanPham/SanPham";
		} else {
			SPService.create(SPEntity);
		}

		return "redirect:/Admin/SanPham";
	}

//	@PostMapping("/Admin/SanPham/Update")
//	public String SanPhamUpdate(@Validated @ModelAttribute("SPEntity") SanPhamEntity SPEntity, Errors errors,
//			Model model) {
//		if (errors.hasErrors()) {
//
//			model.addAttribute("SPEntity", SPEntity);
//			List<SanPhamEntity> ListSPService = SPService.findAll();
//			model.addAttribute("SPService", ListSPService);
//
//			return "Admin/TaiKhoan/SanPham";
//		} else {
//			SPService.create(SPEntity);
//		}
//
//		return "redirect:/Admin/SanPham";
//	}
}
