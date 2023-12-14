package com.example.demo.Service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.SanPhamService;
import com.example.demo.Service.TheLoaiService; 

@Service
public class SanPhamIMPL implements SanPhamService{
	@Autowired
	SanPhamDao SPDao;
	
	@Autowired
	TheLoaiService TLService;
	
	Boolean count = true;

	@Override
	public List<SanPhamEntity> findAll() {
		// TODO Auto-generated method stub
		return SPDao.findAll();
	}

	@Override
	public SanPhamEntity findById(int id) {
		// TODO Auto-generated method stub
		return SPDao.findById(id).get();
	}

	@Override
	public SanPhamEntity create(SanPhamEntity SPEntity) throws Exception {
		// TODO Auto-generated method stub
//		TheLoaiEntity TLEntity = new TheLoaiEntity();
//		TLEntity.setId_tl(SPEntity.getTheloai().getId_tl());
//		TLEntity.setTentheloai(SPEntity.getTheloai().getTentheloai());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String formattedDateAsString = dateFormat.format(SPEntity.getNgayxuatban());
		Date formattedDateAsDate = dateFormat.parse(formattedDateAsString);
		SPEntity.setNgayxuatban(formattedDateAsDate);
//		TLService.create(SPEntity.getTheloai());
		System.out.println(SPEntity.getTheloai().getId_tl());
		System.out.println(SPEntity.getTheloai().getTentheloai());
		SPEntity.setTheloai(SPEntity.getTheloai());
		
		
		return SPDao.save(SPEntity);
	}

//	@Override
//	public SanPhamEntity update(SanPhamEntity SPEntity) {
//		// TODO Auto-generated method stub
//		
//		
//		return SPDao.save(SPEntity);
//	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		SPDao.deleteById(id);
	}

	@Override
	public Integer countAllProduct() {
		// TODO Auto-generated method stub
		return  SPDao.countAllProduct();
	}

	@Override
	public Page<SanPhamEntity> findAll(Integer page, Integer limit) {
		Pageable pageable = PageRequest.of(page, limit);
		return SPDao.findAll(pageable);
	}

	@Override
	public List<SanPhamEntity> findAllByTentheLoai(String categoryName) {
		return SPDao.findAllByTentheLoai(categoryName);
	}

	@Override
	public Page<SanPhamEntity> findByField(Integer page, Integer limit, String field, String ten) {
		if (!ten.isEmpty()) {
			Pageable pageable = PageRequest.of(page, limit, Sort.by(Direction.ASC, "id_sp"));
			return SPDao.findAllByTenLike(ten, pageable);
		} else if (field.equals("")) {
			Pageable pageable = PageRequest.of(page, limit);
			return SPDao.findAll(pageable);
		} else {
			if (count) {
				count = false;
				Pageable pageable = PageRequest.of(page, limit, Sort.by(Direction.ASC, field));
				return SPDao.findAll(pageable);
			} else {
				count = true;
				Pageable pageable = PageRequest.of(page, limit, Sort.by(Direction.DESC, field));
				return SPDao.findAll(pageable);
			}
		}
	}
}
