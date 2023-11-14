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

import com.example.demo.Entity.HoaDonChiTietEntity;
import com.example.demo.Service.HoaDonChiTietService;
import com.example.demo.Service.HoaDonService;
import com.example.demo.Service.SanPhamService;

@Controller
public class HoaDonChiTietController {

	@Autowired
	HoaDonChiTietService HDCTService;

	@Autowired
	HoaDonService HDService;

	@Autowired
	SanPhamService SPService;

	@GetMapping("/Admin/HoaDonChiTiet")
	public String HoaDonChiTietGet(Model model) {
		HoaDonChiTietEntity HDCTEntity = new HoaDonChiTietEntity();
		model.addAttribute("HDCTEntity", HDCTEntity);

		List<HoaDonChiTietEntity> ListHDCTService = HDCTService.findAll();
		model.addAttribute("HDCTService", ListHDCTService);

		return "Admin/HoaDon/HoaDonChiTiet";
	}

	@GetMapping("/Admin/HoaDonChiTiet/Edit/{id}")
	public String HoaDonChiTietEdit(Model model, @PathVariable("id") String id) {
		model.addAttribute("HDCTEntity", HDCTService.findById(id));

		List<HoaDonChiTietEntity> ListHDCTService = HDCTService.findAll();
		model.addAttribute("HDCTService", ListHDCTService);

		return "Admin/HoaDon/HoaDonChiTiet";
	}

	@PostMapping("/Admin/HoaDonChiTiet/Create")
	public String HoaDonChiTietCreate(@Validated @ModelAttribute("HDCTEntity") HoaDonChiTietEntity HDCTEntity,
			Errors errors, Model model) throws Exception {
		if (errors.hasErrors()) {

			model.addAttribute("HDCTEntity", HDCTEntity);
			List<HoaDonChiTietEntity> ListHDCTService = HDCTService.findAll();
			model.addAttribute("HDCTService", ListHDCTService);

			for (FieldError error : errors.getFieldErrors()) {
				System.out.println("Field: " + error.getField());
				System.out.println("Message: " + error.getDefaultMessage());
			}

			return "Admin/HoaDon/HoaDonChiTiet";
		}
		
		HDCTService.create(HDCTEntity);

		return "redirect:/Admin/HoaDonChiTiet";
	}

//	@PostMapping("/Admin/HoaDonChiTiet/Update")
//	public String HoaDonChiTietUpdate(@Validated @ModelAttribute("HDCTEntity") HoaDonChiTietEntity HDCTEntity,
//			Errors errors, Model model) {
//		if (errors.hasErrors()) {
//
//			model.addAttribute("HDCTEntity", HDCTEntity);
//			List<HoaDonChiTietEntity> ListHDCTService = HDCTService.findAll();
//			model.addAttribute("HDCTService", ListHDCTService);
//
//			for (FieldError error : errors.getFieldErrors()) {
//				System.out.println("Field: " + error.getField());
//				System.out.println("Message: " + error.getDefaultMessage());
//			}
//
//			return "Admin/HoaDon/HoaDonChiTiet";
//		} else {
//			HoaDonEntity HDEntity = new HoaDonEntity();
//			HDEntity.setId_hd(HDCTEntity.getHoadon().getId_hd());
//			HDEntity.setNgaytaohoadon(HDCTEntity.getHoadon().getNgaytaohoadon());
//			HDEntity.setDiachi(HDCTEntity.getHoadon().getDiachi());
//			HDEntity.setEmail(HDCTEntity.getHoadon().getEmail());
//			HDService.create(HDEntity);
////			HDCTService.create(HDCTEntity);
//		}
//
//		return "redirect:/Admin/HoaDonChiTiet";
//	}
	
	
}
