package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.HoaDonEntity; 

public interface HoaDonDao extends JpaRepository<HoaDonEntity, String> {
	@Query("SELECT count(p.id_hd) FROM HoaDonEntity p")
	Integer DemHD();
}
