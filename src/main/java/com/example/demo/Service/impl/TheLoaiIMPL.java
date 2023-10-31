package com.example.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.TheLoaiDao;
import com.example.demo.Entity.TheLoaiEntity;
import com.example.demo.Service.TheLoaiService; 

@Service
public class TheLoaiIMPL implements TheLoaiService{
	@Autowired
	TheLoaiDao TLDao;
	@Override
	public List<TheLoaiEntity> findAll() {
		// TODO Auto-generated method stub
		return TLDao.findAll();
	}

	@Override
	public TheLoaiEntity findById(String id) {
		// TODO Auto-generated method stub
		return TLDao.findById(id).get();
	}

	@Override
	public TheLoaiEntity create(TheLoaiEntity TLEntity) {
		// TODO Auto-generated method stub
		return TLDao.save(TLEntity);
	}

	@Override
	public TheLoaiEntity update(TheLoaiEntity TLEntity) {
		// TODO Auto-generated method stub
		return TLDao.save(TLEntity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		TLDao.deleteById(id);
	}

}
