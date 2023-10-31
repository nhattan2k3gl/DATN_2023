package com.example.demo.Admin.Service.IMPL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Admin.Dao.HoaDonChiTietDao;
import com.example.demo.Admin.Entity.HoaDonChiTietEntity;
import com.example.demo.Admin.Entity.HoaDonEntity;
import com.example.demo.Admin.Entity.SanPhamEntity;
import com.example.demo.Admin.Service.HoaDonChiTietService;
import com.example.demo.Admin.Service.HoaDonService;
import com.example.demo.Admin.Service.SanPhamService;

@Service
public class HoaDonChiTietIMPL implements HoaDonChiTietService {
	@Autowired
	HoaDonChiTietDao HDCTDao;

	@Autowired
	HoaDonService HDService;
	
	@Autowired
	SanPhamService SPService;

	@Override
	public List<HoaDonChiTietEntity> findAll() {
		// TODO Auto-generated method stub
		return HDCTDao.findAll();
	}

	@Override
	public HoaDonChiTietEntity findById(String id) {
		// TODO Auto-generated method stub
		return HDCTDao.findById(id).get();
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		HDCTDao.deleteById(id);
	}

	@Override
	public HoaDonChiTietEntity create(HoaDonChiTietEntity HDCTEntity) throws Exception {

//		HoaDonEntity HDEntity = new HoaDonEntity();
//		HDEntity.setId_hd(HDCTEntity.getHoadon().getId_hd());
////		Ngay
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		String formattedDateAsString = dateFormat.format(HDCTEntity.getHoadon().getNgaytaohoadon());
//		Date formattedDateAsDate = dateFormat.parse(formattedDateAsString);
//		HDEntity.setNgaytaohoadon(formattedDateAsDate);
////		EndNgay
//		HDEntity.setDiachi(HDCTEntity.getHoadon().getDiachi());
//		HDEntity.setEmail(HDCTEntity.getHoadon().getEmail());
		HDService.create(HDCTEntity.getHoadon());
		SPService.create(HDCTEntity.getSanpham());

		return HDCTDao.save(HDCTEntity);
	}

}
