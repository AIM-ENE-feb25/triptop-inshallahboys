package com.inshallahboys.Triptop.service;

import com.inshallahboys.Triptop.adapter.Payment.PaymentAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private LoginService loginService;

    private final PaymentAdapter paymentAdapter;


    public PaymentService(PaymentAdapter paymentAdapter) {
        this.paymentAdapter = paymentAdapter;
    }

    public String processPayment(String amount) {
        if (loginService.checkForAcces("username", "TripTop", "token")) {
            return "Access denied";
        } else {
            return paymentAdapter.processPayment(amount);
        }
    }
}
