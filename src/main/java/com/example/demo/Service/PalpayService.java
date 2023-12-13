package com.example.demo.Service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PalpayService {
	public Payment createPayment(Double total) throws PayPalRESTException;
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
