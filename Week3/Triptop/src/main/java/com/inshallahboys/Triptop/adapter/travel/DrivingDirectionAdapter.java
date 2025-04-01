package com.inshallahboys.Triptop.adapter.travel;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DrivingDirectionAdapter implements TravelAdapter{
    @Value("${drivingDirectionApiKey}")
    private String apiKey;

    @Override
    public String getRoute(String locationStart, String locationEnd) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://driving-directions1.p.rapidapi.com/get-directions?" + "origin=" + locationStart + "&destination=" + locationEnd + "&distance_units=km&avoid_routes=tolls%2Cferries&country=nl&language=en")
                .header("Content-Type", "application/json")
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", "driving-directions1.p.rapidapi.com")
                .asJson();

        StringBuilder result = new StringBuilder();

        JSONObject jsonObject = response.getBody().getObject();
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject origin = data.getJSONObject("origin");
        JSONObject destination = data.getJSONObject("destination");
        JSONArray bestRoutes = data.getJSONArray("best_routes");

        if (bestRoutes.length() > 0) {
            JSONObject bestRoute = bestRoutes.getJSONObject(0);
            String distanceLabel = bestRoute.getString("distance_label");
            String durationLabel = bestRoute.getString("duration_label");

            result.append(String.format("Origin: %s, Destination: %s, Distance: %s, Duration: %s",
                    origin.getString("name"), destination.getString("name"), distanceLabel, durationLabel));
        } else {
            result.append("No routes found.");
        }

        return result.toString();
    }
}
