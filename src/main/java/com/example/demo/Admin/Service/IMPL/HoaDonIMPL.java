package com.example.demo.Admin.Service.IMPL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Admin.Dao.HoaDonDao;
import com.example.demo.Admin.Entity.HoaDonEntity;
import com.example.demo.Admin.Service.HoaDonService;

@Service
public class HoaDonIMPL implements HoaDonService{
	@Autowired
	HoaDonDao HDDao;

	@Override
	public List<HoaDonEntity> findAll() {
		// TODO Auto-generated method stub
		return HDDao.findAll();
	}

	@Override
	public HoaDonEntity findById(String id) {
		// TODO Auto-generated method stub
		return HDDao.findById(id).get();
	}

	@Override
	public HoaDonEntity create(HoaDonEntity HDEntity)  throws Exception{
//		Ngay
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String formattedDateAsString = dateFormat.format(HDEntity.getNgaytaohoadon());
		Date formattedDateAsDate = dateFormat.parse(formattedDateAsString);
		HDEntity.setNgaytaohoadon(formattedDateAsDate);
//		EndNgay
		return HDDao.save(HDEntity);
	}

//	@Override
//	public HoaDonEntity update(HoaDonEntity HDEntity) throws Exception {
//		// TODO Auto-generated method stub
////		Ngay
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		String formattedDateAsString = dateFormat.format(HDEntity.getNgaytaohoadon());
//		Date formattedDateAsDate = dateFormat.parse(formattedDateAsString);
//		HDEntity.setNgaytaohoadon(formattedDateAsDate);
////		EndNgay
//		
//		return HDDao.save(HDEntity);
//	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		HDDao.deleteById(id);
	}
	
	
}
