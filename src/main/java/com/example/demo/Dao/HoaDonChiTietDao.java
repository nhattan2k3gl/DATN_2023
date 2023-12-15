package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.HoaDonChiTietEntity;

@Repository
public interface HoaDonChiTietDao extends JpaRepository<HoaDonChiTietEntity, String> {
	@Query(value = "select SUM(HoaDonChiTiet.gia*HoaDonChiTiet.SoLuong) as N'Tổng Tiền' from HoaDonChiTiet join SanPham on HoaDonChiTiet.ID_HD=sanpham.ID_SP", nativeQuery = true)
	Integer ThongKeTongHDCT();

//  Cánh này cũng dùng được
	@Query(value = "SELECT HoaDonChiTiet.gia, HoaDonChiTiet.SoLuong FROM HoaDonChiTiet LEFT JOIN HoaDon ON HoaDonChiTiet.ID_HD = HoaDon.ID_HD WHERE  YEAR(HoaDon.NgayTaoHoaDon) = :year", nativeQuery = true)
	List<Object[]> ThongKeHDCTTheoThang( @Param("year") String year);

}
