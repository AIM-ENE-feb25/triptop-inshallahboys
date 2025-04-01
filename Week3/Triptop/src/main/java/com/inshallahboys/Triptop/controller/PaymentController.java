package com.inshallahboys.Triptop.controller;

import com.inshallahboys.Triptop.service.PaymentService;
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
            @RequestParam(required = false, defaultValue = "paypal") String paymentType) {

        return paymentService.processPayment(amount, paymentType);
    }
}

