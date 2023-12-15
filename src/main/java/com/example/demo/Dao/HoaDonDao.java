package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.HoaDonEntity;

public interface HoaDonDao extends JpaRepository<HoaDonEntity, String> {
	@Query("SELECT count(p.id_hd) FROM HoaDonEntity p")
	Integer DemHD();
	@Query("SELECT o FROM HoaDonEntity o WHERE o.taikhoan.email=?1")
	List<HoaDonEntity> findByUsername(String email);

}
