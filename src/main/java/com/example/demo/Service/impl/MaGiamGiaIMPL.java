package com.example.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.MaGiamGiaDao;
import com.example.demo.Entity.MaGiamGiaEntity;
import com.example.demo.Service.MaGiamGiaService;

@Service
public class MaGiamGiaIMPL implements MaGiamGiaService{

	@Autowired
	MaGiamGiaDao MGGDao;
	@Override
	public List<MaGiamGiaEntity> findAll() {
		// TODO Auto-generated method stub
		return MGGDao.findAll();
	}

	@Override
	public MaGiamGiaEntity findById(String id) {
		// TODO Auto-generated method stub
		return MGGDao.findById(id).get();
	}

	@Override
	public MaGiamGiaEntity create(MaGiamGiaEntity MGGEntity) {
		// TODO Auto-generated method stub
		return MGGDao.save(MGGEntity);
	}

	@Override
	public MaGiamGiaEntity update(MaGiamGiaEntity MGGEntity) {
		// TODO Auto-generated method stub
		return MGGDao.save(MGGEntity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		MGGDao.deleteById(id);
	}

}
