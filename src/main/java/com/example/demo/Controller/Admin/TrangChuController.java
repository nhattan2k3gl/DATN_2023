package com.example.demo.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Dao.BinhLuanDao;
import com.example.demo.Dao.HoaDonChiTietDao;
import com.example.demo.Dao.HoaDonDao;
import com.example.demo.Dao.SanPhamDao;

@Controller
public class TrangChuController {
	@Autowired
	HoaDonChiTietDao HDCTDao;
	@Autowired
	SanPhamDao SPDao;
	@Autowired
	BinhLuanDao BLDao;
	@Autowired
	HoaDonDao HDDao;
	@GetMapping("/Admin/TrangChu" )
	public String TrangChuGet(Model model, @CookieValue(value = "email", defaultValue = "123", required = false) String email)
	{
//		model.addAttribute("email",email);
		
		
		int a = HDCTDao.thongke();
		model.addAttribute("thongKe",a);
		
		int b =SPDao.countAllProduct();
		model.addAttribute("Count",b);
		
		int c =BLDao.BinhLuanCount();
		model.addAttribute("BinhLuan",c);
		
		model.addAttribute("HoaDon",HDDao.HoaDonCount());
		return "Admin/TrangChu/TrangChu";
	}
}
