package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.SanPhamEntity; 

public interface SanPhamDao extends JpaRepository<SanPhamEntity, String>  {
	@Query("SELECT count(p.id_sp) FROM SanPhamEntity p")
	Integer countAllProduct();
	
//	List<SanPhamEntity> findByNameContaining(String search);
}
