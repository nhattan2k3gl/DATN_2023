package com.example.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.GioHang;
import com.example.demo.Service.GioHangService;

@Controller
public class CartController {
	@Autowired
	GioHangService gioHangService;


	@RequestMapping("/cart")
	public String cartProduct(Model model) {
		model.addAttribute("cart", gioHangService);
		model.addAttribute("countcart", gioHangService.getCount());
		return "user/cart/cart";
	}

	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") String id) {
		gioHangService.add(id);
		return "redirect:/cart";
	}

	@RequestMapping("/cart/update/{id}")
	public String update(@PathVariable("id") String id, @RequestParam("qty") Integer qty) {
		gioHangService.update(id, qty);
		return "redirect:/cart";
	}

	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") String id) {
		gioHangService.remove(id);
		return "redirect:/cart";
	}

	@RequestMapping("/cart/clear")
	public String clear() {
		gioHangService.getItems().clear();
		return "redirect:/cart";
	}
}
