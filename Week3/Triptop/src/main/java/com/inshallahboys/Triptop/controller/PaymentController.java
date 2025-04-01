package com.inshallahboys.Triptop.controller;

import com.inshallahboys.Triptop.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public String processPayment(@RequestBody Map<String, String> request) {
        String amount = request.get("amount");
        return paymentService.processPayment(amount);
    }
}
