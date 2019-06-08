package com.zhaohu.flightsearch;

import com.zhaohu.flightsearch.controller.FlightSearchControllerTest;
import com.zhaohu.flightsearch.service.FlightSearchServiceImplTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FlightSearchServiceImplTest.class, FlightSearchControllerTest.class})
public class FlightSearchTestSuite {
    public static void main(String[] args) {
        // JUnitCore.runClasses(FlightSearchTestSuite.class);
    }
}
