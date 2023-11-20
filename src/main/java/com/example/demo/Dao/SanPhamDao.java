package com.example.demo.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.SanPhamEntity;

public interface SanPhamDao extends JpaRepository<SanPhamEntity, String>  {
	@Query("SELECT count(p.id_sp) FROM SanPhamEntity p")
	Integer countAllProduct();

	@Query("SELECT p FROM SanPhamEntity p join p.theloai t where t.tentheloai = :name")
	Page<SanPhamEntity> findAllByNameLike(@Param("name") String string, Pageable pageable);


}
