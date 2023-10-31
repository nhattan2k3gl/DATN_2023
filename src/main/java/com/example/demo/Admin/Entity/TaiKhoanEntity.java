package com.example.demo.Admin.Entity;



import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "taikhoan")
public class TaiKhoanEntity implements Serializable {
	@Id
	@Email
	@NotBlank
	String email;
	
	@NotBlank
	String matkhau;
	
	@NotBlank
	String hovaten;

	@NotBlank
	String anh;
	
	@NotBlank
	String diachi;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<PhanQuyenEntity> phanquyen;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "taikhoan")
//	List<HoaDonEntity> hoadon;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuanEntity> binhluan;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<MaGiamGiaEntity> magiamgia ;
	
	
	
}
