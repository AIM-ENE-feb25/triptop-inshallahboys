package com.inshallahboys.Triptop.adapter.travel;

import com.inshallahboys.Triptop.domain.Route;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NSAdapter implements TravelAdapter{
    @Value("${nsApiKey}")
    private String apiKey;

    @Override
    public String getRoute(String locationStart, String locationEnd) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips?" + "fromStation=" + locationStart + "&toStation=" + locationEnd)
                .header("Content-Type", "application/json")
                .header("Cache-Control", "no-cache")
                .header("Ocp-Apim-Subscription-Key", apiKey)
                .asJson();

        JSONObject jsonObject = response.getBody().getObject();
        JSONArray trips = jsonObject.getJSONArray("trips");

        StringBuilder result = new StringBuilder();

        if(trips.length() > 0) {
            for (int i = 0; i < trips.length(); i++) {
                JSONObject trip = trips.getJSONObject(i).getJSONArray("legs").getJSONObject(0);

                String transportType = trip.getJSONObject("product").getString("longCategoryName");
                String departureTime = trip.getJSONObject("origin").getString("plannedDateTime");
                String arrivalTime = trip.getJSONObject("destination").getString("plannedDateTime");
                int priceInCents = trips.getJSONObject(i).getJSONArray("fares").getJSONObject(0).getInt("priceInCents");
                double price = priceInCents / 100.0;

                result.append(String.format("Option %d: Transport Type: %s, Departure Time: %s, Arrival Time: %s, Price: €%.2f\n", i + 1, transportType, departureTime, arrivalTime, price));
            }
        } else {
            result.append("No routes found.");
        }
        return result.toString();
    }
}
