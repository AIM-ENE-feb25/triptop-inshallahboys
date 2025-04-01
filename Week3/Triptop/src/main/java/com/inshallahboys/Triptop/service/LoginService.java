package com.inshallahboys.Triptop.service;

import com.inshallahboys.Triptop.adapter.login.LoginAdapter;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    @Qualifier("wireMockAdapter") // Geen idee waarom dit een kleine letter moet zien, maar inshallah
    private LoginAdapter loginAdapter;
    // private LoginAdapter loginAdapter = new WireMockAdapter();

    public String getToken(String username, String password) throws UnirestException {

        String token = loginAdapter.getToken(username, password);

        return token;
    }

    public boolean checkForAcces(String username, String token) throws UnirestException {
        boolean hasAccess = loginAdapter.checkForAcces(username, token);
        System.out.println("has access in service: " + hasAccess);
        return hasAccess;
    }
}