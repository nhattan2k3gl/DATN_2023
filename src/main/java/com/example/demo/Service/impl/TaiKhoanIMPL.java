package com.example.demo.Service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Entity.VaiTroEntity;
import com.example.demo.Service.TaiKhoanService;

@Service
public class TaiKhoanIMPL implements TaiKhoanService {

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
		TaiKhoanEntity existingTKEntity = TKDao.findById(TKEntity.getEmail()).orElse(null);
		System.out.println(existingTKEntity.getEmail());
		TKEntity.setEmail(existingTKEntity.getEmail());
		TKEntity.setHovaten(existingTKEntity.getHovaten());
		TKEntity.setMatkhau(new BCryptPasswordEncoder().encode(TKEntity.getMatkhau()));
		TKEntity.setDiachi(TKEntity.getDiachi());
		TKEntity.setVaitro(existingTKEntity.getVaitro());
		return TKDao.save(TKEntity);
	}

	@Override
	public void delete(String id) {
		TKDao.deleteById(id);

	}

}
