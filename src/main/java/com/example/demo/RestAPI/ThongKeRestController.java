package com.example.demo.RestAPI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.HoaDonChiTietDao;
import com.example.demo.Dto.ThongKeTheoThangDTO;
import com.example.demo.Entity.HoaDonChiTietEntity;
import com.example.demo.Entity.TheLoaiEntity;

@CrossOrigin("*")
@RestController
public class ThongKeRestController {

	@Autowired
	HoaDonChiTietDao HDCTDao;
	
	@GetMapping("/rest/thongketheothang")
	public ResponseEntity<List<ThongKeTheoThangDTO>> getAll(Model model) {
		List<ThongKeTheoThangDTO> HDCTThongKeTheoThang= new ArrayList<>();
//		Lấy năm hiện tại
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		
		for (Object[] result : HDCTDao.ThongKeHDCTTheoThang(currentYear)) {
		    Double Tong = (Double) result[0];
		    Integer Thang = (Integer) result[1];
		    ThongKeTheoThangDTO hdct = new ThongKeTheoThangDTO(Tong, Thang);
		    HDCTThongKeTheoThang.add(hdct);
		}
		model.addAttribute("HDCTThongKeTheoNam", HDCTThongKeTheoThang);
		return ResponseEntity.ok(HDCTThongKeTheoThang);
	}
}
