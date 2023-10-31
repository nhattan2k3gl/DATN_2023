package com.example.demo.Admin.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "vaitro")
public class VaiTroEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id_vt;
	
	@NotBlank
	String tenvaitro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vaitro")
	List<PhanQuyenEntity> phanquyen;
	
}
