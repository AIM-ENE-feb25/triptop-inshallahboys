package com.inshallahboys.Triptop.service;

import com.inshallahboys.Triptop.adapter.Payment.PaymentAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private LoginService loginService;

    @Autowired
    @Qualifier("paypalAdapter")
    private PaymentAdapter paypalAdapter;

    @Autowired
    @Qualifier("stripeAdapter")
    private PaymentAdapter stripeAdapter;

    public String processPayment(String amount, String paymentType) {
        if (loginService.checkForAcces("username", "TripTop", "token")) {

            PaymentAdapter paymentAdapter;
            if ("stripe".equalsIgnoreCase(paymentType)) {
                paymentAdapter = stripeAdapter;
            } else {
                paymentAdapter = paypalAdapter;
            }

            return paymentAdapter.processPayment(amount);
        } else {
            return "Access denied";
        }
    }
}
