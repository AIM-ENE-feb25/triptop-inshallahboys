package com.inshallahboys.Triptop.controller;

import com.inshallahboys.Triptop.domain.User;
import com.inshallahboys.Triptop.service.LoginService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin()
@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws UnirestException {

        String token = loginService.getToken(user.username(), user.password());

        System.out.println("has access in controller: " + loginService.checkForAcces(user.username(), token));


        return ResponseEntity.ok().body(token);
    }
}