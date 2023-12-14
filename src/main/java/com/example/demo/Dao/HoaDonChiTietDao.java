package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Dto.ThongKeHDCTTheoThangDTO;
import com.example.demo.Entity.HoaDonChiTietEntity;

@Repository
public interface HoaDonChiTietDao extends JpaRepository<HoaDonChiTietEntity, String> {
	@Query(value = "select SUM(HoaDonChiTiet.gia*HoaDonChiTiet.SoLuong) as N'Tổng Tiền' from HoaDonChiTiet join SanPham on HoaDonChiTiet.ID_HD=sanpham.ID_SP", nativeQuery = true)
	Integer ThongKeTongHDCT();

//	@Query(value = "SELECT HoaDonChiTiet.gia, HoaDonChiTiet.SoLuong FROM HoaDonChiTiet LEFT JOIN HoaDon ON HoaDonChiTiet.ID_HD = HoaDon.ID_HD WHERE month(HoaDon.NgayTaoHoaDon) = '12' AND YEAR(HoaDon.NgayTaoHoaDon) = '2023'", nativeQuery = true)
//	List<Object[]> ThongKeHDCTTheoThang();
//	@Query(value = "SELECT new ThongKeHDCTTheoThangDTO(ThongKeHDCTTheoThangDTO.gia, ThongKeHDCTTheoThangDTO.SoLuong) FROM HoaDonEntity")
//    List<ThongKeHDCTTheoThangDTO> ThongKeHDCTTheoThang();
}
