package com.example.demo.Controller.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Dao.TheLoaiDao;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Entity.TheLoaiEntity;
import com.example.demo.Service.GioHangService;
import com.example.demo.Service.SanPhamService;
import com.example.demo.Service.SessionService;
import com.example.demo.Service.TheLoaiService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SearchController {
	
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	SanPhamDao sanPhamDao;
	
	@Autowired
	TheLoaiService theLoaiService;
	
	@Autowired
	TheLoaiDao theLoaiDao;
	
	@Autowired
	GioHangService gioHangService;
	
	@Autowired
	SessionService session;
	
	@PostMapping("/shop/search")
	public String search(Model model, HttpServletRequest request,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("category") Optional<String> category,
			@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 9);
		String kwords = kw.orElse(session.get("keywords", ""));
		String categoryName = category.orElse("");
		Page<SanPhamEntity> items;
		
		if (!categoryName.isEmpty()) {
			items = sanPhamDao.findAllByTheLoai(categoryName, pageable);
	    } else {
	    	items = sanPhamDao.findAllByTenLike("%" + kwords + "%", pageable);
	    }
		List<TheLoaiEntity> list = theLoaiDao.findAll();
		model.addAttribute("request", request);
		model.addAttribute("list", list);
		model.addAttribute("items", items);
		model.addAttribute("product", sanPhamService.countAllProduct());
		return "user/home/shop";
	}
	
//	@PostMapping("/shop/search")
//	public String search(Model model, HttpServletRequest request,
//	        @RequestParam("keywords") Optional<String> kw,
//	        @RequestParam("category") Optional<String> category,
//	        @RequestParam("page") Optional<Integer> page) {
//
//	    Pageable pageable = PageRequest.of(page.orElse(0), 9);
//	    String kwords = kw.orElse(session.get("keywords", ""));
//	    String categoryName = category.orElse(""); // Lấy tên danh mục từ tham số
//	    Page<SanPhamEntity> items;
//
//	    // Nếu có danh mục được chọn, thực hiện tìm kiếm theo danh mục
//	    if (!categoryName.isEmpty()) {
//	        items = sanPhamDao.findAllByTenLikeAndTheLoaiTentheloai("%" + kwords + "%", categoryName, pageable);
//	    } else {
//	        // Nếu không có danh mục, thực hiện tìm kiếm theo từ khóa
//	        items = sanPhamDao.findAllByTenLike("%" + kwords + "%", pageable);
//	    }
//
//	    List<TheLoaiEntity> list = theLoaiDao.findAll();
//	    model.addAttribute("request", request);
//	    model.addAttribute("list", list);
//	    model.addAttribute("items", items);
//	    model.addAttribute("product", sanPhamService.countAllProduct());
//	    return "user/home/shop";
//	}

	
}
