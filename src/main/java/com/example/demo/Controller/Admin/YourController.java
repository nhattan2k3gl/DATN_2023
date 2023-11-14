package com.example.demo.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.SanPhamEntity;

@Controller
public class YourController {

    @Autowired
    private YourEntityRepository yourEntityRepository;

    @GetMapping("/yourPage")
    public String yourPage(Model model) {
        // Truy vấn dữ liệu từ cơ sở dữ liệu, ví dụ:
        SanPhamEntity SPEntity = yourEntityRepository.findById(yourEntityId).orElse(null);

        // Đặt dữ liệu vào model để sử dụng trong Thymeleaf template
        model.addAttribute("yourEntity", yourEntity);

        return "yourPage";
    }
}
