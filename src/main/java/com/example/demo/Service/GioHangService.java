package com.example.demo.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.GioHang;

public interface GioHangService {
	GioHang add(String id);

	void remove(String id);

	GioHang update(String id, int qty);

	void clear();
	
	Collection<GioHang> getItems();

	int getCount();

	double getAmount();
}
