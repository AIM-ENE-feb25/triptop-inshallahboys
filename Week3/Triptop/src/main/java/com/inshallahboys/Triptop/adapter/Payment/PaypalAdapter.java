package com.inshallahboys.Triptop.adapter.Payment;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

@Component
public class PaypalAdapter implements PaymentAdapter {

    private static final String CLIENT_ID = "ASTweCPJkU8kXj803z6q1downz-DyMZHtavkiXSRHn1kcLyLPjOFCyhRABuQOqGFMSFbFmEoxI4D64UE";
    private static final String CLIENT_SECRET = "EEZIIIf9gq9JhGlkGgk6lqrIvsDzer6mUtZkiUAaze_Hu_KXo-hFP5FZCVabyivgpMvYi1E8vtQD_Kvw";
    private static final String BASE_URL = "https://api-m.sandbox.paypal.com";

    @Override
    public String processPayment(String amount) {
        try {
            String accessToken = getAccessToken();
            if (accessToken != null) {
                return createPayment(accessToken, amount, "EUR");
            } else {
                return new JSONObject().put("error", "Kan geen access token verkrijgen").toString();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            return new JSONObject().put("error", "Er is een fout opgetreden").toString();
        }
    }

    private String getAccessToken() throws UnirestException {
        HttpResponse<String> response = Unirest.post(BASE_URL + "/v1/oauth2/token")
                .header("Accept", "application/json")
                .header("Accept-Language", "en_US")
                .basicAuth(CLIENT_ID, CLIENT_SECRET)
                .field("grant_type", "client_credentials")
                .asString();

        JSONObject jsonResponse = new JSONObject(response.getBody());
        return jsonResponse.optString("access_token", null);
    }

    private String createPayment(String accessToken, String amount, String currency) throws UnirestException {
        String requestBody = new JSONObject()
                .put("intent", "sale")
                .put("payer", new JSONObject().put("payment_method", "paypal"))
                .put("transactions", new JSONObject[]
                        { new JSONObject()
                                .put("amount", new JSONObject()
                                .put("total", amount)
                                .put("currency", currency)
                        )
                        })
                .put("redirect_urls", new JSONObject()
                        .put("return_url", "https://example.com/success")
                        .put("cancel_url", "https://example.com/cancel")
                )
                .toString();

        HttpResponse<String> response = Unirest.post(BASE_URL + "/v1/payments/payment")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .asString();

        return response.getBody();
    }
}
