package com.example.demo.Controller.User;

import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Entity.PhanQuyenEntity;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Service.PhanQuyenService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class AccountController {
	
	@Autowired
	TaiKhoanDao taiKhoanDao;

	@GetMapping("/login")
	public String formlogin() {
		return "user/taikhoan/dangnhap";
	}
	
	@GetMapping("/login/success")
	public String success(HttpServletRequest request,HttpSession session) {
		session.setAttribute("currentuser", request.getRemoteUser());
		return "auth/login";
	}
	@GetMapping("/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Username or Password incorrect!");
		
		return "user/taikhoan/dangnhap";
	}
	@RequestMapping("/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "You do not have permission!");
		return "user/taikhoan/dangnhap";
	}
	
	@RequestMapping("/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "user/taikhoan/dangnhap";
	}
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/login/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
	
	@GetMapping("/register")
	public String register() {
		return "user/taikhoan/dangky";
	}
	
//	@GetMapping("/forgot-password")
//	public String forgot(Model model) {
//		return "user/taikhoa/";
//	}
//	
//	@PostMapping("/register")
//	public String save(ModelMap model, @Valid @ModelAttribute TaiKhoanEntity tk, BindingResult br,
//			HttpServletResponse response) {
//		if (br.hasErrors()) {
//			model.addAttribute("message", "Please correct the errors below !");
//		} else {
//			tk.setAnh("noavt.png");
//			taiKhoanDao.save(tk);
//			model.addAttribute("message", "You have successfully registered an account!");
//			response.addHeader("refresh", "3;url=/login");
//		}
//		return "user/register";
//	}


}
