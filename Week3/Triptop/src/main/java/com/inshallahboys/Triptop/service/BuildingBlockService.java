package com.inshallahboys.Triptop.service;

import com.inshallahboys.Triptop.adapter.travel.TravelAdapter;
import com.inshallahboys.Triptop.domain.User;
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

    public String getTravelData(String locationStart, String locationEnd, String transportType, User user) throws UnirestException {



        String result = "";

        String token = loginService.getToken(user.username(), user.password());



        if(loginService.checkForAcces(user.username(), token)) {
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
