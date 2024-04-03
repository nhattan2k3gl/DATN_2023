package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.PhanQuyenEntity; 

public interface PhanQuyenDao extends JpaRepository<PhanQuyenEntity, Integer>  {
		 @Query(value = "SELECT pq.id_pq, pq.Email, pq.ID_VT FROM phanquyen pq WHERE pq.Email = :email", nativeQuery = true)
		 List<PhanQuyenEntity> findPQByEmail(@Param("email") String email);

}
