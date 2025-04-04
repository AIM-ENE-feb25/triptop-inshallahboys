package com.inshallahboys.Triptop.adapter.Payment;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class PaypalAdapter implements PaymentAdapter {

    private static final String CLIENT_ID = "ASTweCPJkU8kXj803z6q1downz-DyMZHtavkiXSRHn1kcLyLPjOFCyhRABuQOqGFMSFbFmEoxI4D64UE";
    private static final String CLIENT_SECRET = "EEZIIIf9gq9JhGlkGgk6lqrIvsDzer6mUtZkiUAaze_Hu_KXo-hFP5FZCVabyivgpMvYi1E8vtQD_Kvw";
    private static final String BASE_URL = "https://api-m.sandbox.paypal.com";

    public PaypalAdapter() {}

    @Override
    public String processPayment(String amount) {
        try {
            String accessToken = getAccessToken();
            if (accessToken != null) {
                JSONObject paymentResponse = createPayment(accessToken, amount);
                String approvalUrl = getApprovalUrl(paymentResponse);

                if (approvalUrl != null) {
                    // Return de approval URL zodat de gebruiker daar heen kan gaan
                    return new JSONObject().put("approval_url", approvalUrl).toString();
                } else {
                    return new JSONObject().put("error", "Kan geen goedkeurings-URL verkrijgen").toString();
                }
            } else {
                return new JSONObject().put("error", "Kan geen access token verkrijgen").toString();
            }
        } catch (UnirestException e) {
            return new JSONObject().put("error", e.getMessage()).toString();
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

    private JSONObject createPayment(String accessToken, String amount) throws UnirestException {
        JSONObject requestBody = new JSONObject()
                .put("intent", "CAPTURE")
                .put("purchase_units", new JSONArray()
                        .put(new JSONObject()
                                .put("amount", new JSONObject()
                                        .put("currency_code", "EUR")
                                        .put("value", amount))
                        ));

        HttpResponse<String> response = Unirest.post(BASE_URL + "/v2/checkout/orders")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody.toString())
                .asString();

        JSONObject jsonResponse = new JSONObject(response.getBody());
        System.out.println("Create Payment Response: " + response.getBody());  // Log het antwoord voor debugging
        return jsonResponse;
    }

    private String getApprovalUrl(JSONObject paymentResponse) {
        JSONArray links = paymentResponse.optJSONArray("links");
        for (int i = 0; i < links.length(); i++) {
            JSONObject link = links.optJSONObject(i);
            if ("approve".equals(link.optString("rel"))) {
                return link.optString("href");
            }
        }
        return null;
    }

    public String capturePayment(String orderId) throws UnirestException {
        try {
            String accessToken = getAccessToken();
            if (accessToken == null) {
                return new JSONObject().put("error", "Geen access token beschikbaar").toString();
            }

            HttpResponse<String> response = Unirest.post(BASE_URL + "/v2/checkout/orders/" + orderId + "/capture")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .asString();

            JSONObject jsonResponse = new JSONObject(response.getBody());
            String status = jsonResponse.optString("status");

            if ("COMPLETED".equals(status)) {
                return new JSONObject()
                        .put("id", jsonResponse.optString("id"))
                        .put("status", "CAPTURED")
                        .put("capture_time", jsonResponse.optString("update_time"))
                        .toString();
            } else {
                return new JSONObject()
                        .put("error", "Betaling vastleggen mislukt")
                        .put("status", status)
                        .put("details", jsonResponse.toString())
                        .toString();
            }
        } catch (UnirestException e) {
            return new JSONObject().put("error", e.getMessage()).toString();
        }
    }
}
