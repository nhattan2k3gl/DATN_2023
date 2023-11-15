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

import com.example.demo.Entity.PhanQuyenEntity;
import com.example.demo.Service.PhanQuyenService;
import com.example.demo.Service.TaiKhoanService; 

@Controller
public class PhanQuyenController {

	@Autowired
	PhanQuyenService PQService;
	@Autowired
	TaiKhoanService TKService;

	@GetMapping("/Admin/PhanQuyen")
	public String PhanQuyenGet(Model model) {
		PhanQuyenEntity PQEntity = new PhanQuyenEntity();
		model.addAttribute("PQEntity", PQEntity);

		List<PhanQuyenEntity> ListPQService = PQService.findAll();
		model.addAttribute("PQService", ListPQService);

		return "Admin/TaiKhoan/PhanQuyen";
	}

	@GetMapping("/Admin/PhanQuyen2")
	public String PhanQuyenGet2(Model model) {

		return "Admin/TaiKhoan/PhanQuyen2";
	}

	@GetMapping("/Admin/PhanQuyen/Edit/{id}")
	public String PhanQuyenEdit(Model model, @PathVariable("id") String id) {
		model.addAttribute("PQEntity", PQService.findById(id));

		List<PhanQuyenEntity> ListPQService = PQService.findAll();
		model.addAttribute("PQService", ListPQService);

		return "Admin/TaiKhoan/PhanQuyen";
	}

	@GetMapping("/Admin/PhanQuyen/Delete/{id}")
	public String PhanQuyenDeleteId(Model model, @PathVariable("id") String id) {
		try {
		    model.addAttribute("message", "ThanhCong");
		    PQService.delete(id);
		    
		} catch (Exception e) {
		    
		    if (e.getMessage().contains("The DELETE statement conflicted with the REFERENCE constraint")) {
		    	model.addAttribute("message", "LoiFK");
		    } else {
		    	model.addAttribute("message", "Loi");
		    }
		}

		List<PhanQuyenEntity> ListPQService = PQService.findAll();
		model.addAttribute("PQService", ListPQService);
		PhanQuyenEntity PQEntity = new PhanQuyenEntity();
		model.addAttribute("PQEntity", PQEntity);


		return "Admin/TaiKhoan/PhanQuyen";
	}

	@PostMapping("/Admin/PhanQuyen/Create")
	public String PhanQuyenCreate(@Validated @ModelAttribute("PQEntity") PhanQuyenEntity PQEntity, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("PQEntity", PQEntity);
			List<PhanQuyenEntity> ListPQService = PQService.findAll();
			model.addAttribute("PQService", ListPQService);

			for (FieldError error : errors.getFieldErrors()) {
				System.out.println("Field: " + error.getField());
				System.out.println("Message: " + error.getDefaultMessage());
			}
			return "Admin/TaiKhoan/PhanQuyen";
		}
		PQService.create(PQEntity);

		return "redirect:/Admin/PhanQuyen";
	}

//	@PostMapping("/Admin/PhanQuyen/Update")
//	public String PhanQuyenUpdate(@Validated @ModelAttribute("PQEntity") PhanQuyenEntity PQEntity, Errors errors,
//			Model model) {
//
//		if (errors.hasErrors()) {
//			
//			model.addAttribute("PQEntity", PQEntity);
//			List<PhanQuyenEntity> ListPQService = PQService.findAll();
//			model.addAttribute("PQService", ListPQService);
//			return "Admin/TaiKhoan/PhanQuyen";
//
//		} else {
////			TaiKhoanEntity TaiKhoanEntiy = new TaiKhoanEntity();
////			TaiKhoanEntiy.setEmail(PQEntity.getTaikhoan().getEmail());
////			TaiKhoanEntiy.setMatkhau(PQEntity.getTaikhoan().getMatkhau());
////			TaiKhoanEntiy.setHovaten(PQEntity.getTaikhoan().getHovaten());
////			TaiKhoanEntiy.setAnh(PQEntity.getTaikhoan().getAnh());
////			TaiKhoanEntiy.setDiachi(PQEntity.getTaikhoan().getDiachi());
////			TKService.create(TaiKhoanEntiy);
////			PQService.create(PQEntity);
//			
//			System.out.println( "khong bao loi af");
//		}
//
//		return "redirect:/Admin/PhanQuyen";
//	}

	@PostMapping("/Admin/PhanQuyen/Rest")
	public String PhanQuyenRest(Model model, @ModelAttribute("PQEntity") PhanQuyenEntity PQEntity) {

		return "redirect:/Admin/PhanQuyen";
	}
}
