package com.inshallahboys.Triptop.adapter.Payment;

public class StripeAdapter implements PaymentAdapter {

        @Override
        public String processPayment(String amount) {
            return "Stripe payment processed for amount: " + amount;
        }
    }
