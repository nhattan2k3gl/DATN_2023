package com.example.demo.Controller.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dao.TaiKhoanDao;
import com.example.demo.Dto.TaiKhoanDTO;
import com.example.demo.Dto.TaiKhoanDTOForUpdate;
import com.example.demo.Entity.TaiKhoanEntity;
import com.example.demo.Service.MailerService;
import com.example.demo.Service.TaiKhoanService;
import com.example.demo.Service.UploadService;
import com.example.demo.Service.VaiTroService;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class AccountController {

	@Value("${upload.path}")
	private String uploadPath;

	@Autowired
	TaiKhoanDao taiKhoanDao;

	@Autowired
	TaiKhoanService taiKhoanService;

	@Autowired
	ServletContext app;

	@Autowired
	UploadService uploadService;

	@Autowired
	VaiTroService vaiTroService;

	@Autowired
	MailerService mailer;

	@Autowired
	UploadService upload;

	@GetMapping("/login")
	public String formlogin() {
		return "user/taikhoan/dangnhap";
	}

	@GetMapping("/login/success")
	public String success(HttpServletRequest request, HttpSession session) {
		session.setAttribute("currentuser", request.getRemoteUser());
		return "auth/login";
	}

	@GetMapping("/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Email hoạc tài khoản không đúng!");

		return "user/taikhoan/dangnhap";
	}

	@RequestMapping("/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy cập!");
		return "user/taikhoan/dangnhap";
	}

	@RequestMapping("/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "user/taikhoan/dangnhap";
	}

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}

	@GetMapping("/profile")
	public String profile(Model model, HttpServletRequest request) {
		model.addAttribute("taiKhoanDTO", taiKhoanService.findByUsername(request.getRemoteUser()));
		model.addAttribute("request", request);
		model.addAttribute("items", taiKhoanDao.findAll());
		return "user/taikhoan/profile";
	}

	@PostMapping("/profile/update")
	public String updateProfile(Model model, HttpServletRequest request,
			@ModelAttribute("taiKhoanDTO") TaiKhoanDTOForUpdate taiKhoanDTO, @RequestParam("anh") MultipartFile file)
			throws IOException {

		TaiKhoanEntity optionalUser = taiKhoanService.findByUsername(request.getRemoteUser());
		if (optionalUser != null) {
			TaiKhoanEntity user = optionalUser;
			user.setDiachi(taiKhoanDTO.getDiachi());
			if (file != null && !file.isEmpty()) {
				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				Path filePath = Path.of(uploadPath).resolve(fileName);
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				user.setAnh(fileName);
			} else {
				user.setAnh("nd1.png");
			}

			String newPassword = taiKhoanDTO.getMatkhau();
			if (newPassword != null && !newPassword.isEmpty()) {
				user.setMatkhau(new BCryptPasswordEncoder().encode(newPassword));
			}

			taiKhoanDao.save(user);
		}

		return "redirect:/profile";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("taiKhoanDTO", new TaiKhoanEntity());
		return "user/taikhoan/dangky";
	}

	@PostMapping("/register/create")
	public String createNewUser(@ModelAttribute("taiKhoanDTO") TaiKhoanDTO taiKhoanDTO,
			@RequestParam("anh") MultipartFile file, Model model) throws IOException {
		TaiKhoanEntity TKEntity = null;
		try {
			TKEntity = taiKhoanService.findByUsername(taiKhoanDTO.getEmail());
			if(TKEntity != null)
			{
				model.addAttribute("messageDK", "Tài khoản này đã có rồi");
				model.addAttribute("taiKhoanDTO", new TaiKhoanEntity());
				return "user/taikhoan/dangky";
			}
		} catch (Exception e) {
			
		} finally {
			if (file != null && !file.isEmpty() && TKEntity == null) {
				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				uploadService.save(file, "accounts");
				TaiKhoanEntity user = new TaiKhoanEntity();
				user.setDiachi(taiKhoanDTO.getDiachi());
				user.setAnh(fileName);
				user.setHovaten(taiKhoanDTO.getHovaten());
				user.setEmail(taiKhoanDTO.getEmail());
				user.setMatkhau(new BCryptPasswordEncoder().encode(taiKhoanDTO.getMatkhau()));
				user.setVaitro(List.of(vaiTroService.findByName()));
				taiKhoanDao.save(user);
				model.addAttribute("messageDK", "Đăng kí thành công");
				return "user/taikhoan/dangnhap";
			} 
		}
		return "redirect:/register";
	}

	@GetMapping("/forgot-password")
	public String forgot(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		return "user/taikhoan/forgot-password";
	}

}
