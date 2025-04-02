package com.inshallahboys.Triptop.adapter.travel;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

@Component
public interface TravelAdapter {
    String getRoute(String locationStart, String locationEnd) throws UnirestException;
}
