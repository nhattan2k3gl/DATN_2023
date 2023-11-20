package com.example.demo.Controller.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.SanPhamDao;
import com.example.demo.Dao.TheLoaiDao;
import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Entity.TheLoaiEntity;
import com.example.demo.Service.GioHangService;
import com.example.demo.Service.SanPhamService;
import com.example.demo.Service.SessionService;
import com.example.demo.Service.TaiKhoanService;
import com.example.demo.Service.TheLoaiService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	GioHangService gioHangService;
	
	@Autowired
	SanPhamDao sanPhamDao;
	
	@Autowired
	TheLoaiService theLoaiService;
	
	@Autowired
	TheLoaiDao theLoaiDao;
	
	@Autowired
	SessionService session;
	
	@ModelAttribute("current")
	public String request(Model model, HttpServletRequest request) {
		return request.getRemoteUser();
	}
	
	@RequestMapping("/contact")
	public String contact(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		return "user/home/contact";
	}

	@RequestMapping("/about")
	public String about(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		return "user/home/about";
	}
	
	@GetMapping("/index")
	public String homeIndex(Model model, HttpServletRequest request
							, @RequestParam(name = "page") Optional<Integer> page) {
		model.addAttribute("request", request);
		model.addAttribute("items", sanPhamService.findAll(page.orElse(0), 8));
		return "user/home/index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model, HttpServletRequest request
						, @RequestParam(name = "field", defaultValue = "") String field,
						@RequestParam(name = "page") Optional<Integer> page) {
		model.addAttribute("request", request);
		if (field.equals("")) {
			model.addAttribute("items", sanPhamService.findAll(page.orElse(0), 9));
		} else {
			model.addAttribute("items", sanPhamService.findAll(page.orElse(0), 9));
		}
		List<TheLoaiEntity> list = theLoaiService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("product", sanPhamService.countAllProduct());
		return "user/home/shop";
	}
	
	@PostMapping("/shop/search")
	public String search(Model model, HttpServletRequest request
			, @RequestParam("keywords") Optional<String> kw,
			@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 9);
		String kwords = kw.orElse(session.get("keywords", ""));
		Page<SanPhamEntity> items = sanPhamDao.findAllByNameLike("%" + kwords + "%", pageable);
		List<TheLoaiEntity> list = theLoaiService.findAll();
		model.addAttribute("request", request);
		model.addAttribute("list", list);
		model.addAttribute("items", items);
		model.addAttribute("product", sanPhamService.countAllProduct());
		return "user/home/shop";
	}
	
	
}
