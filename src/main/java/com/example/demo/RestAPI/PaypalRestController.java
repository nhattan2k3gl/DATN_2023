package com.example.demo.RestAPI;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SanPhamEntity;
import com.example.demo.Service.PalpayService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
public class PaypalRestController {
	@Autowired
	PalpayService palpay;
	
	@GetMapping(value = "rest/palpay/{gia}", produces = "application/json" )
	public ResponseEntity<?> getByGia(Model model, @PathVariable("gia") double gia)
	{
		try {
			Payment payment = palpay.createPayment(10.0);
			for(Links link: payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					 return ResponseEntity.ok().body("{\"redirectUrl\":\"" + link.getHref() + "\"}");
				}
			}
		}
		catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		// Nếu không có đường dẫn, trả về HTTP 404 Not Found
        return ResponseEntity.notFound().build();
		
	}

}

