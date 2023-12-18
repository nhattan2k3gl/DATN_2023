package com.example.demo.Controller.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
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
import com.example.demo.Service.HoaDonService;
import com.example.demo.Service.MailerService;
import com.example.demo.Service.TaiKhoanService;
import com.example.demo.Service.UploadService;
import com.example.demo.Service.VaiTroService;

import ch.qos.logback.core.testUtil.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	
	@Autowired
	HoaDonService hoaDonService;

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
		model.addAttribute("dshoadon", hoaDonService.findByUsername(request.getRemoteUser()));
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
				uploadService.save(file, "accounts");
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
	
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam("email") String email, HttpServletRequest request, Model model)
			throws Exception {
		try {
			if (taiKhoanService.existsByEmail(email)) {
	            String token = generateRandomString(10);
	            taiKhoanService.updateToken(token, email);
	            String resetLink = getSiteURL(request) + "/reset-password?token=" + token;
	            mailer.sendEmail(email, resetLink);

	            model.addAttribute("request", request);
	            model.addAttribute("message", "We have sent a reset password link to your email. "
	                    + "If you don't see the email, check your spam folder.");
	        } else {
	        	model.addAttribute("request", request);
	            model.addAttribute("message", "Email does not exist. Please check the provided email address.");
	            return "user/taikhoan/forgot-password";
	        }
		} catch (MessagingException e) {
			e.printStackTrace();
			model.addAttribute("request", request);
			model.addAttribute("error", "Error while sending email");
		}
		return "user/taikhoan/forgot-password";
	}
	
	@GetMapping("/reset-password")
	public String resetPasswordForm(@Param(value = "token") String token, Model model, HttpServletRequest request) {
		Optional<TaiKhoanEntity> account = taiKhoanService.getByToken(token);
		model.addAttribute("request", request);
		model.addAttribute("token", token);
		if (account.isEmpty()) {
			model.addAttribute("message", "Invalid token!");
			return "redirect:/login";
		}
		return "user/taikhoan/reset-password";
	}
	
	@PostMapping("/reset-password")
	public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password,
	                                   HttpServletResponse response, Model model, HttpServletRequest request) {
		Optional<TaiKhoanEntity> user = taiKhoanService.getByToken(request.getParameter("token"));
	    System.out.println(token);
	    model.addAttribute("request", request);

	    if (user.isEmpty()) {
			model.addAttribute("message", "Invalid token!");
		} else {
	        taiKhoanService.updatePassword(user.get(), password);
	        model.addAttribute("message", "You have successfully changed your password!");
	        response.addHeader("refresh", "3;url=/login");
	    }

	    return "user/taikhoan/reset-password";
	}

	
	public String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
	
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

}
