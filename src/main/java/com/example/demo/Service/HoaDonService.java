package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.HoaDonEntity;
 

public interface HoaDonService 
{
	public List<HoaDonEntity> findAll();

	public HoaDonEntity findById(String id);


	public HoaDonEntity create(HoaDonEntity HDEntity) throws Exception;


	public void delete(String id);
}
