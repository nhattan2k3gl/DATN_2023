package com.example.demo.Service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.UploadService;

import jakarta.servlet.ServletContext;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	ServletContext servletContext;
	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public File save(MultipartFile file, String folder) {
		try {
			// Lấy đường dẫn gốc của ứng dụng
			String rootPath = System.getProperty("user.dir");

			// Tạo thư mục lưu trữ trong thư mục static
			Path uploadDir = Paths.get(rootPath, "src", "main", "resources", "static","user","img", folder);
			File uploadDirFile = uploadDir.toFile();
			if (!uploadDirFile.exists()) {
				uploadDirFile.mkdirs();
			}

			// Tạo tên file duy nhất
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

			// Tạo đường dẫn đầy đủ của tệp tin
			File savedFile = new File(uploadDirFile, fileName);

			// Lưu tệp tin
			file.transferTo(savedFile);

			// In đường dẫn tuyệt đối của tệp tin
			System.out.println(savedFile.getAbsolutePath());

			return savedFile;
		} catch (IOException e) {
			throw new RuntimeException("Failed to save file", e);
		}
	}
}
