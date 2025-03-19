package nl.han.se.bewd;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
    public static void main(String[] args) throws UnirestException {

        String airportCode = "ams";

        HttpResponse<String> response = Unirest.get("https://google-flights2.p.rapidapi.com/api/v1/searchAirport?query=" + airportCode + "&language_code=en-US&country_code=US")
                .header("x-rapidapi-key", "aa6df114acmsh538e336786a4322p14a742jsnee88930f544f")
                .header("x-rapidapi-host", "google-flights2.p.rapidapi.com")
                .asString();
        System.out.println(response.getBody());
    }
}