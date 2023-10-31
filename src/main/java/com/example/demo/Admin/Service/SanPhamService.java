package com.example.demo.Admin.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.Admin.Entity.SanPhamEntity;


public interface SanPhamService {
	public List<SanPhamEntity> findAll();

	public SanPhamEntity findById(String id);


	public SanPhamEntity create(SanPhamEntity SPEntity) throws Exception;
	
	Integer countAllProduct();
	Page<SanPhamEntity> findAll(Integer page, Integer limit);
	
	public void delete(String id);
}
