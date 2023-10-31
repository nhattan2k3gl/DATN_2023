package com.example.demo.Admin.Service;

import java.util.List;

import com.example.demo.Admin.Entity.TheLoaiEntity;


public interface TheLoaiService {
	public List<TheLoaiEntity> findAll();

	public TheLoaiEntity findById(String id);


	public TheLoaiEntity create(TheLoaiEntity TLEntity);

	public TheLoaiEntity update(TheLoaiEntity TLEntity);

	public void delete(String id);
}
