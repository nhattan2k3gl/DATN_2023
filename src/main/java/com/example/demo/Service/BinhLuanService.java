package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.Entity.BinhLuanEntity;
 


public interface BinhLuanService 
{
	public List<BinhLuanEntity> findAll();
	
	public Page<BinhLuanEntity> findAllCmt(Integer page, Integer limit);

	public BinhLuanEntity findById(String id);


	public BinhLuanEntity create(BinhLuanEntity BLEntity) throws Exception  ;


	public void delete(String id);
}
