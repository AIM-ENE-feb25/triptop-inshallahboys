package com.inshallahboys.Triptop.controller;

import com.inshallahboys.Triptop.domain.User;
import com.inshallahboys.Triptop.service.PaymentService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public String processPayment(
            @RequestParam String amount,
            @RequestParam(required = false, defaultValue = "paypal") String paymentType) throws UnirestException {

        // Omdat het een prototype is, maken we hier gebruik van een set User.
        // De token zou normaliter vanuit de frontend komen.
        User user = new User("edevries", "3g2Rw9sT1x");

        return paymentService.processPayment(amount, paymentType, user);
    }
}

