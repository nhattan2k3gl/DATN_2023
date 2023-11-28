package com.example.demo.Entity;



import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

	String anh;
	
	@NotBlank
	String diachi;
	
	
	@ManyToMany
    @JoinTable(
            name = "phanquyen",
            joinColumns = @JoinColumn(name = "Email"),
            inverseJoinColumns = @JoinColumn(name = "ID_VT")
    )
    Collection<VaiTroEntity> vaitro;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "taikhoan")
//	List<HoaDonEntity> hoadon;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuanEntity> binhluan;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<MaGiamGiaEntity> magiamgia ;


	public TaiKhoanEntity(@Email @NotBlank String email) {
		super();
		this.email = email;
	}

	
	
}
