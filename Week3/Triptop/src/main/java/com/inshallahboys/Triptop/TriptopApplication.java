package com.inshallahboys.Triptop;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriptopApplication {
	public static void main(String[] args) throws UnirestException {
		SpringApplication.run(TriptopApplication.class, args);
	}
}
