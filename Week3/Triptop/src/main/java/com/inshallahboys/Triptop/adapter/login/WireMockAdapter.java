package com.inshallahboys.Triptop.adapter.login;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class WireMockAdapter implements LoginAdapter {
    @Override
    public String getToken(String username, String password) throws UnirestException {

        String url = "https://triptop-identity.wiremockapi.cloud/login";
        String tokenValue;
        String expirationTime;

        JSONObject userJson = new JSONObject();
        userJson.put("username", username);
        userJson.put("password", password);


        try {
            HttpResponse<JsonNode> response = Unirest.post(url)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(userJson)
                    .asJson();

            JsonNode responseBody = response.getBody(); // pakt de hele response
            JSONObject jsonObject = responseBody.getObject(); // pakt daarvan het object
            JSONObject tokenObject = jsonObject.getJSONObject("token"); // pakt daarvan 't token object
            tokenValue = tokenObject.getString("value"); // pakt daarvan de daadwerkelijke token
            expirationTime = tokenObject.getString("expirationTime"); // en de expiration time
        } catch (Exception e) {
            return "Information was not correct";
        }

        System.out.println("Token: " + tokenValue);
        System.out.println("Expiration time: " + expirationTime);

        return tokenValue;
    }

    @Override
    public boolean checkForAcces(String username, String token) throws UnirestException {
        String application = "triptop";

        JSONObject accesJson = new JSONObject();
        accesJson.put("username", username);
        accesJson.put("application", application);

        String url = "https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=" + token;
        String hasAccess;
        String role;

        try {
            HttpResponse<JsonNode> response = Unirest.post(url)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(accesJson)
                    .asJson();

            JsonNode responseBody = response.getBody();
            JSONObject jsonObject = responseBody.getObject();
            hasAccess = jsonObject.getString("access");
            role = jsonObject.getString("role");


        } catch (Exception e) {
            return false;
        }


        if (hasAccess.equals("allowed") && !role.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
