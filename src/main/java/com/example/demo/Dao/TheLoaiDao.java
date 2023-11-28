package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.TheLoaiEntity; 

public interface TheLoaiDao extends JpaRepository<TheLoaiEntity, Integer>  {

}
