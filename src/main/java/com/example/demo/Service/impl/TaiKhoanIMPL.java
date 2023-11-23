package com.example.demo.Service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Entity.VaiTroEntity;
import com.example.demo.Service.TaiKhoanService; 

@Service
public class TaiKhoanIMPL implements TaiKhoanService{

	@Autowired
	TaiKhoanDao TKDao;

	@Override
	public List<TaiKhoanEntity> findAll() {
		// TODO Auto-generated method stub
		return TKDao.findAll();
	}

	@Override
	public TaiKhoanEntity findByUsername(String id) {
		// TODO Auto-generated method stub
		return TKDao.findById(id).get();
	}

	@Override
	public TaiKhoanEntity create(TaiKhoanEntity TKEntity) {
		// TODO Auto-generated method stub
		return TKDao.save(TKEntity);
	}

	@Override
	public TaiKhoanEntity update(TaiKhoanEntity TKEntity) {
		// TODO Auto-generated method stub
//		Set Vai tro 
		Set<VaiTroEntity> vaitroSet = new HashSet<>();
		VaiTroEntity VTEntity = new VaiTroEntity(3, "GUEST");
		vaitroSet.add(VTEntity);
		TKEntity.setVaitro(vaitroSet);
		
		return TKDao.save(TKEntity);
	}

	@Override
	public void delete(String id) {
		TKDao.deleteById(id);
		
	}
	
	

}
