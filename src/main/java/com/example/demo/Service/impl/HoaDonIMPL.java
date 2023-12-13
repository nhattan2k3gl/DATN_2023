package com.example.demo.Service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.HoaDonChiTietDao;
import com.example.demo.Dao.HoaDonDao;
import com.example.demo.Entity.HoaDonChiTietEntity;
import com.example.demo.Entity.HoaDonEntity;
import com.example.demo.Service.HoaDonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HoaDonIMPL implements HoaDonService{
	@Autowired
	HoaDonDao HDDao;
	
	@Autowired
	HoaDonChiTietDao HDCTDao;

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

	@Override
	public HoaDonEntity crt(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		HoaDonEntity order = mapper.convertValue(orderData, HoaDonEntity.class);
		

		TypeReference<List<HoaDonChiTietEntity>> type = new TypeReference<List<HoaDonChiTietEntity>>() {};
		
		List<HoaDonChiTietEntity> details = mapper.convertValue(orderData.get("hoadonchitiet"), type).stream()
				.peek(d -> d.setHoadon(order)).collect(Collectors.toList());
		HDDao.save(order);
		HDCTDao.saveAll(details);

		return order;
	}

	@Override
	public List<HoaDonEntity> findByUsername(String email) {
		return HDDao.findByUsername(email);
	}

	
	
}
