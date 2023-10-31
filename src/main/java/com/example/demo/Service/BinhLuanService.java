package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.BinhLuanEntity;
 


public interface BinhLuanService 
{
	public List<BinhLuanEntity> findAll();

	public BinhLuanEntity findById(String id);


	public BinhLuanEntity create(BinhLuanEntity BLEntity) throws Exception  ;


	public void delete(String id);
}
