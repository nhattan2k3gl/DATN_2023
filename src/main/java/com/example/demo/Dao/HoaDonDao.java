package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.HoaDonEntity; 

public interface HoaDonDao extends JpaRepository<HoaDonEntity, String> {

}
