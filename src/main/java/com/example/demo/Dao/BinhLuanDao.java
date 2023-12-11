package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.BinhLuanEntity; 

public interface BinhLuanDao extends JpaRepository<BinhLuanEntity,String> {
	@Query("SELECT count(p.id_bl) FROM BinhLuanEntity p")
	Integer BinhLuanCount();
	@Query(value="SELECT * from BinhLuan where ID_SP  = :id_sp", nativeQuery = true)
	List<BinhLuanEntity> findByIDSp(@Param("id_sp") int id_sp);
}
