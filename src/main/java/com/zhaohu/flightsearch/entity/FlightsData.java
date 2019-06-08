package com.zhaohu.flightsearch.entity;

import java.util.ArrayList;
import java.util.List;

public class FlightsData {
    private List<Flight> flights = new ArrayList<Flight>();

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
