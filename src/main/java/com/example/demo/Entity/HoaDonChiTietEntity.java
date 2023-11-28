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
	
	@NotNull
	int soluong;
	
	@NotNull
	Double gia;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_hd")
	HoaDonEntity hoadon;
	
	@ManyToOne
	@JoinColumn(name="id_sp")
	SanPhamEntity sanpham;
	
	
}
