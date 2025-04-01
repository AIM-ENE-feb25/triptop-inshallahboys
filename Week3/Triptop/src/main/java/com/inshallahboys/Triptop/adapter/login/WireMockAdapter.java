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

        JSONObject userJson = new JSONObject();
        userJson.put("username", username);
        userJson.put("password", password);

        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(userJson)
                .asJson();

        JsonNode responseBody = response.getBody(); // pakt de hele response
        JSONObject jsonObject = responseBody.getObject(); // pakt daarvan het object
        JSONObject tokenObject = jsonObject.getJSONObject("token"); // pakt daarvan 't token object
        String tokenValue = tokenObject.getString("value"); // pakt daarvan de daadwerkelijke token
        String expirationTime = tokenObject.getString("expirationTime"); // en de expiration time

        System.out.println("Token: " + tokenValue);
        System.out.println("Expiration time: " + expirationTime);

        return tokenValue;
    }

    @Override
    public boolean checkForAcces(String username, String application, String token) {
        return true;
    }
}
