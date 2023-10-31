package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.TaiKhoanEntity; 



public interface TaiKhoanService {
	public List<TaiKhoanEntity> findAll();

	public TaiKhoanEntity findById(String id);


	public TaiKhoanEntity create(TaiKhoanEntity TKEntity);

	public TaiKhoanEntity update(TaiKhoanEntity TKEntity);

	public void delete(String id);
}
