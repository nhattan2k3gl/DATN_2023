package com.example.demo.Entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "phanquyen")
public class PhanQuyenEntity implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_pq;
	
//	String email;
	
	@ManyToOne
	@JoinColumn(name="email")
	
	TaiKhoanEntity taikhoan;
	
//	String id_vt;
	
	@ManyToOne
	@JoinColumn(name="id_vt")
	VaiTroEntity vaitro;
}
