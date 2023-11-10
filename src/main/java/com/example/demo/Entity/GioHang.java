package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHang {
	private int id_sp;
	private String ten;
	private String anh;
	private double gia;
	private int soluongsp = 1;
}