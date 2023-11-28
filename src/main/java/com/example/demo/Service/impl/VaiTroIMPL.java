package com.example.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.VaiTroDao;
import com.example.demo.Entity.VaiTroEntity;
import com.example.demo.Service.VaiTroService; 

@Service
public class VaiTroIMPL implements VaiTroService{

	@Autowired
	VaiTroDao VTDao;
	@Override
	public List<VaiTroEntity> findAll() {
		// TODO Auto-generated method stub
		return VTDao.findAll();
	}

	@Override
	public VaiTroEntity findById(String id) {
		// TODO Auto-generated method stub
		return VTDao.findById(id).get();
	}

	@Override
	public VaiTroEntity create(VaiTroEntity VTEntity) {
		// TODO Auto-generated method stub
		return VTDao.save(VTEntity);
	}

	@Override
	public VaiTroEntity update(VaiTroEntity VTEntity) {
		// TODO Auto-generated method stub
		return VTDao.save(VTEntity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		VTDao.deleteById(id);
	}

	@Override
	public VaiTroEntity findByName() {
		// TODO Auto-generated method stub
		return VTDao.findByTenvaitro("USER");
	}

}
