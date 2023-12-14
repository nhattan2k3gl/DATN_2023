package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Dto.ThongKeHDCTTheoThangDTO;

public interface ThongKeHDCTTheoThangDAO extends JpaRepository<ThongKeHDCTTheoThangDTO,Double>{
//	@Query(value = "SELECT new ThongKeHDCTTheoThangDTO(ThongKeHDCTTheoThangDTO.gia, ThongKeHDCTTheoThangDTO.SoLuong) FROM HoaDonEntity")
//	List<ThongKeHDCTTheoThangDTO> ThongKeHDCTTheoThang();
}
