package com.example.demo.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Admin.Entity.TaiKhoanEntity;



public interface TaiKhoanDao extends JpaRepository<TaiKhoanEntity, String> {

}
