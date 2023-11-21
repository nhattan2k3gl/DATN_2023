package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "hoadon")
public class HoaDonEntity implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_hd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaytaohoadon")
	Date ngaytaohoadon = new Date();
	
	@NotBlank
	String diachi;
	
	@ManyToOne
	@JoinColumn(name="Email")
	TaiKhoanEntity taikhoan;
	
	@JsonIgnore
	@OneToMany(mappedBy = "hoadon")
	List<HoaDonChiTietEntity> hoadonchitiet;
	
	
}
