package com.example.demo.Admin.Service.IMPL;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Admin.Dao.BinhLuanDao;
import com.example.demo.Admin.Entity.BinhLuanEntity;
import com.example.demo.Admin.Entity.SanPhamEntity;
import com.example.demo.Admin.Entity.TaiKhoanEntity;
import com.example.demo.Admin.Entity.TheLoaiEntity;
import com.example.demo.Admin.Service.BinhLuanService;
import com.example.demo.Admin.Service.PhanQuyenService;
import com.example.demo.Admin.Service.SanPhamService;
import com.example.demo.Admin.Service.TaiKhoanService;
import com.example.demo.Admin.Service.TheLoaiService;

@Service
public class BinhLuanIMPL implements BinhLuanService{
	@Autowired
	BinhLuanDao BLDao;
	
	@Autowired
	TaiKhoanService TKService;
	
	@Autowired
	SanPhamService SPService;
	
	@Autowired
	TheLoaiService TLService;
	
	

	@Override
	public List<BinhLuanEntity> findAll() {
		// TODO Auto-generated method stub
		return BLDao.findAll();
	}

	@Override
	public BinhLuanEntity findById(String id) {
		// TODO Auto-generated method stub
		return BLDao.findById(id).get();
	}

	@Override
	public BinhLuanEntity create(BinhLuanEntity BLEntity) throws Exception {
		
//		TaiKhoanEntity TKEntity = new TaiKhoanEntity();
//		TKEntity.setEmail(BLEntity.getTaikhoan().getEmail());
//		TKEntity.setMatkhau(BLEntity.getTaikhoan().getMatkhau());
//		TKEntity.setHovaten(BLEntity.getTaikhoan().getHovaten());
//		TKEntity.setAnh(BLEntity.getTaikhoan().getAnh());
//		TKEntity.setDiachi(BLEntity.getTaikhoan().getDiachi());
//		TKService.create(TKEntity);
		
//		SanPhamEntity SPEntity = new SanPhamEntity();
//		SPEntity.setId_sp(BLEntity.getSanpham().getId_sp());
//		SPEntity.setGia(BLEntity.getSanpham().getGia());
//		SPEntity.setTen(BLEntity.getSanpham().getTen());
////		Ngay
//		try {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//			String formattedDateAsString = dateFormat.format(BLEntity.getSanpham().getNgayxuatban()); 
//			Date formattedDateAsDate = dateFormat.parse(formattedDateAsString);
//			SPEntity.setNgayxuatban(formattedDateAsDate);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
////		EndNgay
//		SPEntity.setAnh(BLEntity.getSanpham().getAnh());
//		SPEntity.setSoluongsp(BLEntity.getSanpham().getSoluongsp());
//		System.out.println(SPEntity.getNgayxuatban());
//		TheLoaiEntity TLEntity = new TheLoaiEntity();
//		TLEntity.setId_tl(BLEntity.getSanpham().getTheloai().getId_tl());
//		TLEntity.setTentheloai(BLEntity.getSanpham().getTheloai().getTentheloai());
//		SPEntity.setTheloai(TLEntity);
		
		TKService.create(BLEntity.getTaikhoan());
		SPService.create(BLEntity.getSanpham());
		
		return BLDao.save(BLEntity);
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		BLDao.deleteById(id);
	}
}
