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
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "binhluan")
public class BinhLuanEntity implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_bl;
	
	@NotBlank
	String binhluan;
	
	@NotBlank
	String danhgia;

//	String email;
	@ManyToOne
	@JoinColumn(name="email")
	TaiKhoanEntity taikhoan;
	
//	String id_sp;
	@ManyToOne
	@JoinColumn(name="id_sp")
	SanPhamEntity sanpham;
}
