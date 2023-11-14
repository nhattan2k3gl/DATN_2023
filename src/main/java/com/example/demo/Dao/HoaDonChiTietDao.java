package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.HoaDonChiTietEntity; 

public interface HoaDonChiTietDao extends JpaRepository<HoaDonChiTietEntity, String>  {
	@Query(value = "select SUM(sanpham.gia*HoaDonChiTiet.SoLuong) as N'Tổng Tiền' from HoaDonChiTiet join SanPham on HoaDonChiTiet.ID_HD=sanpham.ID_SP", nativeQuery = true)
	Integer thongke();
}
