package com.example.demo.Admin.Controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Admin.Dao.TaiKhoanDao;
import com.example.demo.Admin.Entity.TaiKhoanEntity;
import com.example.demo.Admin.Service.TaiKhoanService;

import jakarta.validation.Valid;

@Controller
public class TaiKhoanController {
	@Autowired
	TaiKhoanService TKService;

	@GetMapping("/Admin/TaiKhoan")
	public String TaiKhoanGet(Model model) {
		TaiKhoanEntity TKEntity = new TaiKhoanEntity();
		model.addAttribute("TKEntity", TKEntity);

		List<TaiKhoanEntity> ListTKService = TKService.findAll();
		model.addAttribute("TKService", ListTKService);

		return "Admin/TaiKhoan/TaiKhoan";
	}

	@GetMapping("/Admin/TaiKhoan/Edit/{id}")
	public String TaiKhoanEdit(Model model, @PathVariable("id") String id) {
		model.addAttribute("TKEntity", TKService.findById(id));

		List<TaiKhoanEntity> ListTKService = TKService.findAll();
		model.addAttribute("TKService", ListTKService);

		return "Admin/TaiKhoan/TaiKhoan";
	}

	@GetMapping("/Admin/TaiKhoan/Delete/{id}")
	public String TaiKhoanDeleteId(Model model, @PathVariable("id") String id) {

		TKService.delete(id);

		return "redirect:/Admin/TaiKhoan";
	}

	@PostMapping("/Admin/TaiKhoan/Create")
	public String TaiKhoanCreate(@Valid @ModelAttribute("TKEntity") TaiKhoanEntity TKEntity, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			model.addAttribute("TKEntity", TKEntity);
			List<TaiKhoanEntity> ListTKService = TKService.findAll();
			model.addAttribute("TKService", ListTKService);

			return "Admin/TaiKhoan/TaiKhoan";
		}

		TKService.create(TKEntity);

		return "redirect:/Admin/TaiKhoan";
	}

	@PostMapping("/Admin/TaiKhoan/Update")
	public String TaiKhoanUpdate(@Validated @ModelAttribute("TKEntity") TaiKhoanEntity TKEntity, Errors errors,
			Model model) {

		if (errors.hasErrors()) {

			model.addAttribute("TKEntity", TKEntity);
			List<TaiKhoanEntity> ListTKService = TKService.findAll();
			model.addAttribute("TKService", ListTKService);

			return "Admin/TaiKhoan/TaiKhoan";
		} else {
			TKService.create(TKEntity);
		}

		return "redirect:/Admin/TaiKhoan";
	}

	@PostMapping("/Admin/TaiKhoan/Reset")
	public String TaiKhoanReset(@ModelAttribute("TKAttribute") TaiKhoanEntity TKAttribute) {

		return "redirect:/Admin/TaiKhoan";
	}
}
