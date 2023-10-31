package com.example.demo.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Admin.Entity.HoaDonEntity;

public interface HoaDonDao extends JpaRepository<HoaDonEntity, String> {

}
