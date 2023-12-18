package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.TaiKhoanEntity; 



public interface TaiKhoanService {
	public List<TaiKhoanEntity> findAll();

	public TaiKhoanEntity findByUsername(String id);


	public TaiKhoanEntity create(TaiKhoanEntity TKEntity);

	public TaiKhoanEntity update(TaiKhoanEntity TKEntity);

	public void delete(String id);

	public void updateToken(String token, String email) throws Exception;

	public void updatePassword(TaiKhoanEntity user, String password);

	public Optional<TaiKhoanEntity> getByToken(String token);

	public boolean existsByEmail(String email);
}
