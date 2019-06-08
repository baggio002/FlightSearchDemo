package com.zhaohu.flightsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FlightSearchApplication {
    public static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(FlightSearchApplication.class, Constants.DEFAULT_RESOURCE_NAME);
    }
}
