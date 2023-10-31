package com.example.demo.Admin.Controller;

import java.text.ParseException;
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

import com.example.demo.Admin.Dao.BinhLuanDao;
import com.example.demo.Admin.Entity.BinhLuanEntity;
import com.example.demo.Admin.Entity.TaiKhoanEntity;
import com.example.demo.Admin.Entity.VaiTroEntity;
import com.example.demo.Admin.Service.BinhLuanService;

@Controller
public class BinhLuanController {
	@Autowired
	BinhLuanService BLService;

	@GetMapping("/Admin/BinhLuan")
	public String BinhLuanGet(Model model) {
		BinhLuanEntity BLEntity = new BinhLuanEntity();
		model.addAttribute("BLEntity", BLEntity);
		
		System.out.println("gg");
		System.out.println("123");

		List<BinhLuanEntity> ListBLService = BLService.findAll();
		model.addAttribute("BLService", ListBLService);

		return "Admin/BinhLuan/BinhLuan";
	}

	@GetMapping("/Admin/BinhLuan/Edit/{id}")
	public String BinhLuanEdit(Model model, @PathVariable("id") String id) {
		model.addAttribute("BLEntity", BLService.findById(id));

		List<BinhLuanEntity> ListBLService = BLService.findAll();
		model.addAttribute("BLService", ListBLService);

		return "Admin/BinhLuan/BinhLuan";
	}

	@GetMapping("/Admin/BinhLuan/Delete/{id}")
	public String BinhLuanDeleteId(Model model, @PathVariable("id") String id) {

		BLService.delete(id);

		return "redirect:/Admin/BinhLuan";
	}

	@PostMapping("/Admin/BinhLuan/Create")
	public String BinhLuanCreate(@Validated @ModelAttribute("BLEntity") BinhLuanEntity BLEntity, Errors errors,
			Model model) throws Exception{
		if (errors.hasErrors()) {

			model.addAttribute("BLEntity", BLEntity);
			List<BinhLuanEntity> ListBLService = BLService.findAll();
			model.addAttribute("BLService", ListBLService);

			for (FieldError error : errors.getFieldErrors()) {
				System.out.println("Field: " + error.getField());
				System.out.println("Message: " + error.getDefaultMessage());
			}

			return "Admin/BinhLuan/BinhLuan";
		}

		BLService.create(BLEntity);

		return "redirect:/Admin/BinhLuan";
	}
//	@PostMapping("/Admin/BinhLuan/Update")
//	public String BinhLuanUpdate(@Validated @ModelAttribute("BLEntity")BinhLuanEntity BLEntity, Errors errors, Model model) 
//	{
//		if (errors.hasErrors()) {
//			
//			model.addAttribute("BLEntity",BLEntity);
//			List<BinhLuanEntity> ListBLService = BLService.findAll();
//			model.addAttribute("BLService", ListBLService);
//			return "Admin/BinhLuan/BinhLuan";
//		}
//		else {
//			BLService.create(BLEntity);
//		}
//		
//		
//		return "redirect:/Admin/BinhLuan";
//	}
}
