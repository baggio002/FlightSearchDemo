package com.zhaohu.flightsearch;

import com.zhaohu.flightsearch.entity.Flight;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    private static List<Flight> list = new ArrayList<Flight>();
    static {
        Flight f1 = new Flight();
        f1.setFlight("Air Canada 1");
        f1.setDeparture("5:00AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 2");
        f1.setDeparture("5:00AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 3");
        f1.setDeparture("5:00AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 4");
        f1.setDeparture("1:30AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 5");
        f1.setDeparture("2:10AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 6");
        f1.setDeparture("11:59AM");
        f1 = new Flight();
        f1.setFlight("Air Canada 7");
        f1.setDeparture("12:00AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 8");
        f1.setDeparture("12:01PM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 9");
        f1.setDeparture("05:00PM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 10");
        f1.setDeparture("05:00PM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 11");
        f1.setDeparture("3:00AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 12");
        f1.setDeparture("11:59AM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 13");
        f1.setDeparture("12:00PM");
        list.add(f1);
        f1 = new Flight();
        f1.setFlight("Air Canada 14");
        f1.setDeparture("0:01AM");
        list.add(f1);
    }

    public static List<Flight> generateFlights() {
        return list;
    }
}
