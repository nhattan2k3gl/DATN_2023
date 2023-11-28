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
import com.example.demo.Entity.Item;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.GioHangService;
@Service
public class GioHangServiceImpl implements GioHangService{
	
	@Autowired
	SanPhamDao sanPhamDao;
	
	Map<Integer, Item> map = new HashMap<>();

	ArrayList<Item> list = new ArrayList<Item>();

	@Override
	public Collection<Item> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		int sum = map.values().stream().mapToInt(item -> item.getSoluongsp()).sum();
	    System.out.println("Total count: " + sum);
	    return sum;
	}

	@Override
	public double getAmount() {
		return map.values().stream().mapToDouble(item -> item.getGia() * item.getSoluongsp()).sum();
	}

}
