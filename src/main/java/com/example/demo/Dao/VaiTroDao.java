package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.VaiTroEntity; 

public interface VaiTroDao extends JpaRepository<VaiTroEntity, String> {

	VaiTroEntity findByTenvaitro(String tenvaitro);

}
