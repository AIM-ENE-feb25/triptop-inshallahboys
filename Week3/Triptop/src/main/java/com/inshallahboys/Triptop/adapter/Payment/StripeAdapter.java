package com.inshallahboys.Triptop.adapter.Payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.stripe.param.PaymentIntentCreateParams;

@Component
public class StripeAdapter implements PaymentAdapter {

    private static final String STRIPE_SECRET_KEY = "sk_test_Gx4mWEgHtCMr4DYMUIqfIrsz";

    public StripeAdapter() {
        Stripe.apiKey = STRIPE_SECRET_KEY;
    }

    @Override
    public String processPayment(String amount) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) (Double.parseDouble(amount) * 100))
                    .setCurrency("eur")
                    .setPaymentMethod("pm_card_visa")
                    .setConfirm(true)
                    .setReturnUrl("https://example.com/success")
                    .build();


            PaymentIntent intent = PaymentIntent.create(params);

            return new JSONObject()
                    .put("id", intent.getId())
                    .put("status", intent.getStatus())
                    .toString();

        } catch (StripeException e) {
            return new JSONObject().put("error", e.getMessage()).toString();
        }
    }
}
