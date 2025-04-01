package com.inshallahboys.Triptop.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public LoginService() {
        System.out.println("LoginService initialized");
    }

    private ResponseEntity<String> getToken(String username, String password){
        System.out.println("token got");
        return null;
    }

    public boolean checkForAcces(String username, String application, String token){
        System.out.println("access checked");
        return true;
    }
}
