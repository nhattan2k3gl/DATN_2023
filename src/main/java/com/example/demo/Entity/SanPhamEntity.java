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
@Table(name = "sanpham")
public class SanPhamEntity implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_sp;
	
	@NotBlank
	String ten;
	
	@NotNull
	double gia;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "ngayxuatban")
	Date ngayxuatban = new Date();
	
	
	
	
	String anh;
	
	String anh1;
	
	String anh2;
	
	
	@NotNull
	int soluongsp;
	
	@NotBlank
	String mota;
	
//	String id_tl;
	@ManyToOne
	@JoinColumn(name = "id_tl")
	TheLoaiEntity theloai;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<HoaDonChiTietEntity> hoadonchitiet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<BinhLuanEntity> binhluan;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<MaGiamGiaEntity> magiamgia;
	
	
}
