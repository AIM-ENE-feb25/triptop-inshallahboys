package com.inshallahboys.Triptop.controller;

import com.inshallahboys.Triptop.domain.User;
import com.inshallahboys.Triptop.service.BuildingBlockService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingBlockController {

    @Autowired
    private BuildingBlockService buildingBlockService;

    @GetMapping("/traveldata")
    public String getTravelData(@RequestParam String locationStart
            , @RequestParam String locationEnd, @RequestParam String transportType) throws UnirestException {
        User user = new User("edevries", "3g2Rw9sT1x");

        return buildingBlockService.getTravelData(locationStart, locationEnd, transportType, user);
    }
}