package com.example.demo.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Admin.Entity.TheLoaiEntity;

public interface TheLoaiDao extends JpaRepository<TheLoaiEntity, String>  {

}
