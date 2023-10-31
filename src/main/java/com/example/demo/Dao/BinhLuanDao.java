package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.BinhLuanEntity; 

public interface BinhLuanDao extends JpaRepository<BinhLuanEntity,String> {

}
