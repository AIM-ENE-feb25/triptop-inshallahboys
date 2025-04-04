package com.inshallahboys.Triptop.service;

import com.inshallahboys.Triptop.adapter.Payment.PaymentAdapter;
import com.inshallahboys.Triptop.domain.User;
import com.mashape.unirest.http.exceptions.UnirestException;
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

    public String processPayment(String amount, String paymentType, User user) throws UnirestException {

        // Omdat het een prototype is, maken we hier gebruik van een set User.
        // De token zou normaliter vanuit de frontend komen.
        String token = loginService.getToken(user.username(), user.password());
        if (loginService.checkForAcces(user.username(), token)) {

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
