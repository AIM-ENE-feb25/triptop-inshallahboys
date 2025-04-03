package com.inshallahboys.Triptop.adapter.login;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

@Component
public interface LoginAdapter {
    String getToken(String username, String password) throws UnirestException;

    boolean checkForAcces(String username, String token) throws UnirestException;
}
