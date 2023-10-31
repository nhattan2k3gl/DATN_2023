package com.example.demo.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Admin.Entity.BinhLuanEntity;

public interface BinhLuanDao extends JpaRepository<BinhLuanEntity,String> {

}
