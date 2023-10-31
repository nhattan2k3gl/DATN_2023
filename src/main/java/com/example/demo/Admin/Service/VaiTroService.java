package com.example.demo.Admin.Service;

import java.util.List;

import com.example.demo.Admin.Entity.VaiTroEntity;

public interface VaiTroService {
	public List<VaiTroEntity> findAll();
	
	public VaiTroEntity findById(String id);
	
	public VaiTroEntity create(VaiTroEntity VTEntity);
	
	public VaiTroEntity update(VaiTroEntity VTEntity);
	
	public void delete(String id);
}
