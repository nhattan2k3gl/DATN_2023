package com.example.demo.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "magiamgia")
public class MaGiamGiaEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank
	String id_mgg;
	
	@NotBlank
	float phantram;
	
//	String email;
	@ManyToOne
	@JoinColumn(name="email")
	TaiKhoanEntity taikhoan;
	
//	String id_sp;
	@ManyToOne
	@JoinColumn(name="id_sp")
	SanPhamEntity sanpham;
}
