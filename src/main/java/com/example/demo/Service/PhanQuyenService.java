package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.PhanQuyenEntity;
 

public interface PhanQuyenService 
{
	public List<PhanQuyenEntity> findAll();
	
	public PhanQuyenEntity findById(String id);
	
	public PhanQuyenEntity create(PhanQuyenEntity PQEntity);

	
	public void delete(String id);
	
	public List<PhanQuyenEntity> findByEmail(String email);
}
