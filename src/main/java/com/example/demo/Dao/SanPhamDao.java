package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.SanPhamEntity;

public interface SanPhamDao extends JpaRepository<SanPhamEntity, Integer>  {
	@Query("SELECT count(p.id_sp) FROM SanPhamEntity p")
	Integer countAllProduct();
	
	@Query("SELECT COUNT(p.id_sp) FROM SanPhamEntity p WHERE p.ten LIKE %:kw%")
	Integer countAllProductLike(@Param("kw") String kw);

	@Query("SELECT p FROM SanPhamEntity p join p.theloai t where lower(t.tentheloai) like lower(:name)")
	Page<SanPhamEntity> findAllByTheLoai(@Param("name") String string, Pageable pageable);
	
	@Query("SELECT p FROM SanPhamEntity p JOIN p.theloai t WHERE t.tentheloai = :name")
	List<SanPhamEntity> findAllByTentheLoai(@Param("name") String name);
	
	Page<SanPhamEntity> findAllByTenLike(String ten, Pageable pageable);


}
