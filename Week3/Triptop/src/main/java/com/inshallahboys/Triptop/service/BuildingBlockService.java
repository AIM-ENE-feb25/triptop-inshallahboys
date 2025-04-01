package com.inshallahboys.Triptop.service;

import com.inshallahboys.Triptop.adapter.travel.TravelAdapter;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BuildingBlockService {

    @Autowired
    private LoginService loginService;

    @Autowired
    @Qualifier("NSAdapter")
    private TravelAdapter nsAdapter;

    @Autowired
    @Qualifier("drivingDirectionAdapter") // first letter cannot be capital
    private TravelAdapter drivingDirectionAdapter;

    public String getTravelData(String locationStart, String locationEnd, String transportType) throws UnirestException {
        String result = "";
        if(loginService.checkForAcces("hoi", "hoi", "hoi")) {
            switch (transportType) {
                case "TRAIN":
                    result = nsAdapter.getRoute(locationStart, locationEnd);
                    break;
                case "CAR":
                    result = drivingDirectionAdapter.getRoute(locationStart, locationEnd);
                    break;
                default:
            }
        } else {
            return "no access";
        }
        return result;
    }

}
