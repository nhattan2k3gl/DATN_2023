package com.example.demo.Admin.Service;

import java.util.List;

import com.example.demo.Admin.Entity.HoaDonChiTietEntity;


public interface HoaDonChiTietService {
	public List<HoaDonChiTietEntity> findAll();

	public HoaDonChiTietEntity findById(String id);


	public HoaDonChiTietEntity create(HoaDonChiTietEntity HDCTEntity) throws Exception;



	public void delete(String id);
}
