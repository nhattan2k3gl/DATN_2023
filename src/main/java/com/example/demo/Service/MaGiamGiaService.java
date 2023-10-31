package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.MaGiamGiaEntity; 


public interface MaGiamGiaService 
{
	public List<MaGiamGiaEntity> findAll();

	public MaGiamGiaEntity findById(String id);


	public MaGiamGiaEntity create(MaGiamGiaEntity MGGEntity);

	public MaGiamGiaEntity update(MaGiamGiaEntity MGGEntity);

	public void delete(String id);
}
