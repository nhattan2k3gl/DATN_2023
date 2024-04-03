package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.HoaDonChiTietEntity;

@Repository
public interface HoaDonChiTietDao extends JpaRepository<HoaDonChiTietEntity, Integer> {
	@Query(value = "select SUM(HoaDonChiTiet.gia*HoaDonChiTiet.SoLuong) as N'Tổng Tiền' from HoaDonChiTiet join SanPham on HoaDonChiTiet.ID_HD=sanpham.ID_SP", nativeQuery = true)
	int ThongKeTongHDCT();

//  Cánh này cũng dùng được
	@Query(value = "SELECT sum(HoaDonChiTiet.gia* HoaDonChiTiet.soluong) as 'Tong',MONTH(HoaDon.NgayTaoHoaDon) as 'Thang' FROM HoaDonChiTiet LEFT JOIN HoaDon ON HoaDonChiTiet.ID_HD = HoaDon.ID_HD WHERE  YEAR(HoaDon.NgayTaoHoaDon) = :year group by MONTH(HoaDon.NgayTaoHoaDon)", nativeQuery = true)
	List<Object[]> ThongKeHDCTTheoThang(@Param("year") int year);

}
