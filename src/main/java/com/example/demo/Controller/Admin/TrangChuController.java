package com.example.demo.Controller.Admin;

import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Entity.HoaDonChiTietEntity;

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
	
	@Autowired
	TaiKhoanDao TKDao;
	
	@GetMapping("/Admin/TrangChu" )
	public String TrangChuGet(Model model)
	{
		model.addAttribute("TongTienHDCT",HDCTDao.ThongKeTongHDCT());
		model.addAttribute("DemSP",SPDao.countAllProduct());
		model.addAttribute("DemBL",BLDao.DemBL());
		model.addAttribute("DemHD",HDDao.DemHD());
		
		List<HoaDonChiTietEntity> HDCTThongKeTheoNam= new ArrayList<>();
		for (Object[] result : HDCTDao.ThongKeHDCTTheoThang("2023")) {
		    Double gia = (Double) result[0];
		    Integer soLuong = (Integer) result[1];
		    HoaDonChiTietEntity hdct = new HoaDonChiTietEntity(0, soLuong, gia, null, null);
		    HDCTThongKeTheoNam.add(hdct);
		}
		model.addAttribute("HDCTThongKeTheoNam", HDCTThongKeTheoNam);
		
		return "Admin/TrangChu/TrangChu";
	}
}
