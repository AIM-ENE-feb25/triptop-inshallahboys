package com.inshallahboys.Triptop.domain;

public class Route {
    String locationStart;
    String locationEnd;

    public Route(String locationStart, String locationEnd) {
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
    }

    public String getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(String locationStart) {
        this.locationStart = locationStart;
    }

    public String getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(String locationEnd) {
        this.locationEnd = locationEnd;
    }
}
