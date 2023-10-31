package com.example.demo.Service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Entity.GioHang;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.GioHangService;
@Service
public class GioHangServiceImpl implements GioHangService{
	
	@Autowired
	SanPhamDao sanPhamDao;
	
	Map<String, GioHang> map = new HashMap<>();

	ArrayList<GioHang> list = new ArrayList<GioHang>();
	
	@Override
	public GioHang add(String id) {
		GioHang gioHang = map.get(id);
		if (gioHang == null) {
			gioHang = new GioHang();
			SanPhamEntity sp = new SanPhamEntity();
			gioHang.setId_sp(sp.getId_sp());
			gioHang.setTen(sp.getTen());
			gioHang.setAnh(sp.getAnh());
			gioHang.setGia(sp.getGia());
			gioHang.setSoluongsp(1);
			map.put(id, gioHang);
		} else {
			gioHang.setSoluongsp(gioHang.getSoluongsp() + 1);
		}
		return gioHang;
	}

	@Override
	public void remove(String id) {
		map.remove(id);
		
	}

	@Override
	public GioHang update(String id, int qty) {
		GioHang giohang = map.get(id);
		giohang.setSoluongsp(qty);;
		return giohang;
	}

	@Override
	public void clear() {
		map.clear();
		
	}

	@Override
	public Collection<GioHang> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		return map.values().stream().mapToInt(item -> item.getSoluongsp()).sum();
	}

	@Override
	public double getAmount() {
		return map.values().stream().mapToDouble(item -> item.getGia() * item.getSoluongsp()).sum();
	}

}
