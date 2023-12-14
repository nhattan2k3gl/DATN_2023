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
import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Dto.ThongKeHDCTTheoThangDTO;
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
		model.addAttribute("DemSP",SPDao.DemSP());
		model.addAttribute("DemBL",BLDao.DemBL());
		model.addAttribute("DemHD",HDDao.DemHD());
		
		System.out.println(TKDao.DemTK());
		
//		for(ThongKeHDCTTheoThangDTO hd : HDCTDao.ThongKeHDCTTheoThang())
//		{
//			System.out.println(hd.getGia());
//		}
//		for (Object[] result : HDCTDao.ThongKeHDCTTheoThang()) {
//		    Double gia = (Double) result[0];
//		    Integer soLuong = (Integer) result[1];
//
//		    // Bạn có thể sử dụng gia và soLuong ở đây để thực hiện các thao tác khác
//		    System.out.println("Gia: " + gia + ", SoLuong: " + soLuong);
//		}
		
		return "Admin/TrangChu/TrangChu";
	}
}
