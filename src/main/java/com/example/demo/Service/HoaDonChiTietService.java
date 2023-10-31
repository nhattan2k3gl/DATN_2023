package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.HoaDonChiTietEntity;
 


public interface HoaDonChiTietService {
	public List<HoaDonChiTietEntity> findAll();

	public HoaDonChiTietEntity findById(String id);


	public HoaDonChiTietEntity create(HoaDonChiTietEntity HDCTEntity) throws Exception;



	public void delete(String id);
}
