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

import com.example.demo.Admin.Dao.MaGiamGiaDao;
import com.example.demo.Admin.Entity.BinhLuanEntity;
import com.example.demo.Admin.Entity.MaGiamGiaEntity;
import com.example.demo.Admin.Entity.TaiKhoanEntity;
import com.example.demo.Admin.Service.MaGiamGiaService;

@Controller
public class MaGiamGiaController {
	
	@Autowired
	MaGiamGiaService MGGService;
	
	@GetMapping("/Admin/MaGiamGia")
	public String MaGiamGiaGet(Model model)
	{
		MaGiamGiaEntity MGGEntity= new MaGiamGiaEntity();
		model.addAttribute("MGGEntity",MGGEntity);
		
		List<MaGiamGiaEntity> ListMGGService = MGGService.findAll();
		model.addAttribute("MGGService", ListMGGService);
		
		return "Admin/MaGiamGia/MaGiamGia";
	}
	@GetMapping("/Admin/MaGiamGia/Edit/{id}")
	public String MaGiamGiaEdit(Model model, @PathVariable("id") String id)
	{
		model.addAttribute("MGGEntity", MGGService.findById(id));
		
		List<MaGiamGiaEntity> ListMGGService = MGGService.findAll();
		model.addAttribute("MGGService", ListMGGService);
		
		return "Admin/MaGiamGia/MaGiamGia";
	}
	@GetMapping("/Admin/MaGiamGia/Delete/{id}")
	public String MaGiamGiaDeleteId(Model model, @PathVariable("id") String id)
	{
		
		MGGService.delete(id);
		
		
		return "redirect:/Admin/MaGiamGia";
	}
	@PostMapping("/Admin/MaGiamGia/Create")
	public String MaGiamGiaCreate(@Validated @ModelAttribute("MGGEntity")MaGiamGiaEntity MGGEntity, Errors errors, Model model)
	{
		if (errors.hasErrors()) {
			
			model.addAttribute("MGGEntity",MGGEntity);
			List<MaGiamGiaEntity> ListMGGService = MGGService.findAll();
			model.addAttribute("MGGService", ListMGGService);
			
			return "Admin/TaiKhoan/MaGiamGia";
		}
		else {
			MGGService.create(MGGEntity);
		}
		
		
		return "redirect:/Admin/MaGiamGia";
	}
	@PostMapping("/Admin/MaGiamGia/Update")
	public String MaGiamGiaUpdate(@Validated @ModelAttribute("MGGEntity")MaGiamGiaEntity MGGEntity, Errors errors, Model model)
	{
		if (errors.hasErrors()) {
			
			model.addAttribute("MGGEntity",MGGEntity);
			List<MaGiamGiaEntity> ListMGGService = MGGService.findAll();
			model.addAttribute("MGGService", ListMGGService);
			
			return "Admin/TaiKhoan/MaGiamGia";
		}
		else {
			MGGService.create(MGGEntity);
		}
		
		
		return "redirect:/Admin/MaGiamGia";
	}
}
