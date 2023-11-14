package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.BinhLuanEntity; 

public interface BinhLuanDao extends JpaRepository<BinhLuanEntity,String> {
	@Query("SELECT count(p.id_bl) FROM BinhLuanEntity p")
	Integer BinhLuanCount();
}
