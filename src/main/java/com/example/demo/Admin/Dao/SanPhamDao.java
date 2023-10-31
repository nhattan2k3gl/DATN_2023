package com.example.demo.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Admin.Entity.SanPhamEntity;

public interface SanPhamDao extends JpaRepository<SanPhamEntity, String>  {
	@Query("SELECT count(p.id_sp) FROM SanPhamEntity p")
	Integer countAllProduct();
}
