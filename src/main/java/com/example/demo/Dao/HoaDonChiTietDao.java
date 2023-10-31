package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.HoaDonChiTietEntity; 

public interface HoaDonChiTietDao extends JpaRepository<HoaDonChiTietEntity, String>  {

}
