package com.example.demo.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Item;

public interface GioHangService {
	
	Collection<Item> getItems();

	int getCount();

	double getAmount();
}
