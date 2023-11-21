package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.HoaDonEntity;
import com.fasterxml.jackson.databind.JsonNode;
 

public interface HoaDonService 
{
	public List<HoaDonEntity> findAll();

	public HoaDonEntity findById(String id);
	
	public HoaDonEntity create(HoaDonEntity HDEntity) throws Exception;

	public void delete(String id);
	
	public HoaDonEntity crt(JsonNode orderData);
}
