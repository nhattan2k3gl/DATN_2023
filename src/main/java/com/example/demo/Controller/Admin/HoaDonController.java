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

import com.example.demo.Entity.HoaDonEntity;
import com.example.demo.Service.HoaDonService; 

@Controller
public class HoaDonController {

	@Autowired
	HoaDonService HDService;

	@GetMapping("/Admin/HoaDon")
	public String HoaDonGet(Model model) {
		HoaDonEntity HDEntity = new HoaDonEntity();
		model.addAttribute("HDEntity", HDEntity);

		List<HoaDonEntity> ListHDService = HDService.findAll();
		model.addAttribute("HDService", ListHDService);

		return "Admin/HoaDon/HoaDon";
	}

	@GetMapping("/Admin/HoaDon/Edit/{id}")
	public String HoaDonEdit(Model model, @PathVariable("id") String id) {
		model.addAttribute("HDEntity", HDService.findById(id));

		List<HoaDonEntity> ListHDService = HDService.findAll();
		model.addAttribute("HDService", ListHDService);

		return "Admin/HoaDon/HoaDon";
	}

	@PostMapping("/Admin/HoaDon/Create")
	public String HoaDonCreate(@Validated @ModelAttribute("HDEntity") HoaDonEntity HDEntity, Errors errors, Model model)
			throws Exception {
		if (errors.hasErrors()) {

			model.addAttribute("HDEntity", HDEntity);
			List<HoaDonEntity> ListHDService = HDService.findAll();
			model.addAttribute("HDService", ListHDService);
			
			for (FieldError error : errors.getFieldErrors()) {
				System.out.println("Field: " + error.getField());
				System.out.println("Message: " + error.getDefaultMessage());
			}

			return "Admin/HoaDon/HoaDon";
		}
		HDService.create(HDEntity);

		return "redirect:/Admin/HoaDon";
	}

//	@PostMapping("/Admin/HoaDon/Update")
//	public String HoaDonUpdate(@Validated @ModelAttribute("HDEntity") HoaDonEntity HDEntity, Errors errors,
//			Model model)  throws Exception{
//
//		if (errors.hasErrors()) {
//
//			model.addAttribute("HDEntity", HDEntity);
//			List<HoaDonEntity> ListHDService = HDService.findAll();
//			model.addAttribute("HDService", ListHDService);
//
//			return "Admin/HoaDon/HoaDon";
//		} else {
//			HDService.create(HDEntity);
//		}
//
//		return "redirect:/Admin/HoaDon";
//	}

}
