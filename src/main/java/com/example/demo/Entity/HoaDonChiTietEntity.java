package com.example.demo.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "hoadonchitiet")
public class HoaDonChiTietEntity implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_hdct;
	
//	String id_hd;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_hd")
	HoaDonEntity hoadon;
	
//	String id_sp;
	
	@ManyToOne
	@JoinColumn(name="id_sp")
	SanPhamEntity sanpham;
	
	@NotNull
	int soluong;
}
