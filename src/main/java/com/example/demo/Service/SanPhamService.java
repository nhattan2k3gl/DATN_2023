package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.Entity.SanPhamEntity; 


public interface SanPhamService {
	public List<SanPhamEntity> findAll();

	public SanPhamEntity findById(int id);


	public SanPhamEntity create(SanPhamEntity SPEntity) throws Exception;
	
	Integer countAllProduct();
	
	Page<SanPhamEntity> findAll(Integer page, Integer limit);
	
	public void delete(int id);

	List<SanPhamEntity> findAllByTentheLoai(String categoryName);

	Page<SanPhamEntity> findByField(Integer page, Integer limit, String field, String ten);
}
