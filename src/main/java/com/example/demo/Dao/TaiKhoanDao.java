package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.TaiKhoanEntity;  

public interface TaiKhoanDao extends JpaRepository<TaiKhoanEntity, String> {
	@Query(value = "update taikhoan set matkhau = :matkhau where email = :email",nativeQuery=true)
	List<TaiKhoanEntity> updatematkhau(@Param("matkhau") String matkhau, @Param("email") String email);
}
