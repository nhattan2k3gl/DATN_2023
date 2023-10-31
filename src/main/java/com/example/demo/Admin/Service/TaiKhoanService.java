package com.example.demo.Admin.Service;

import java.util.List;

import com.example.demo.Admin.Entity.TaiKhoanEntity;



public interface TaiKhoanService {
	public List<TaiKhoanEntity> findAll();

	public TaiKhoanEntity findById(String id);


	public TaiKhoanEntity create(TaiKhoanEntity TKEntity);

	public TaiKhoanEntity update(TaiKhoanEntity TKEntity);

	public void delete(String id);
}
