package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.TaiKhoanEntity;  

public interface TaiKhoanDao extends JpaRepository<TaiKhoanEntity, String> {

}
