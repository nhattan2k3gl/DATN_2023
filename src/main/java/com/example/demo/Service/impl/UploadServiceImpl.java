package com.example.demo.Service.impl;


import java.io.File;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.UploadService;

import jakarta.servlet.ServletContext;



@Service
public class UploadServiceImpl implements UploadService{

	@Autowired
	ServletContext app;
	
	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath("C:\\Users\\ADMIN\\Documents\\GitHub\\DATN_2023\\src\\main\\resources\\static\\user\\img\\products\\"+folder));
		System.out.println("");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode())+s.substring(s.lastIndexOf("."));
		try {
			File savedFile = new File(dir,name);
			System.out.println(savedFile);
			file.transferTo(savedFile);
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
