package com.example.demo.Controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Service.TaiKhoanService;

@Controller
public class DoiMatKhauController {
	@Autowired
	TaiKhoanService TKService;
	@Autowired
	TaiKhoanDao TKDao;
	
	@GetMapping("/Admin/TaiKhoan/doimatkhau")
	public String DoiMatKhauget(Model model)
	{
		return "Admin/TaiKhoan/doimatkhau";
	}
	@GetMapping("/updateMatkhau")
	public String update()
	{
		TKDao.updatematkhau("345","anhhao@gmail.com");
		return "Admin/TaiKhoan/doimatkhau";
		
	}
	
	
}
