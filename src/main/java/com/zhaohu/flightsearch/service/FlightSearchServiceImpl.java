package com.zhaohu.flightsearch.service;

import com.zhaohu.flightsearch.*;
import com.zhaohu.flightsearch.data.FlightsDataSupporter;
import com.zhaohu.flightsearch.entity.Flight;
import com.zhaohu.flightsearch.entity.FlightsData;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {
    private static final Logger logger = Utils.getLogger(FlightSearchServiceImpl.class);

    @Override
    public FlightsData search(String departure) throws DateFormatParseException {
        logger.info("search!");
        if (!Utils.verifyTimeFormat(departure)) {
            throw new DateFormatParseException(String.format(Constants.DATA_FORMAT_PARSE_EXCEPTION_MESSEGE, departure));
        }
        FlightsData data = new FlightsData();
        LocalTime time = Utils.formatDeparture(departure);
        LocalTime limitTime = time.plusHours(Constants.PLUS_HOUR);
        if (limitTime.isAfter(time)) {
            FlightsDataSupporter.getFlightsData().subMap(time, true, limitTime, true)
                    .entrySet().stream().map(entry -> {
                        return entry.getValue();
                    }).forEach(flights -> data.getFlights().addAll((List<Flight>) flights));
        } else {
            FlightsDataSupporter.getFlightsData().tailMap(time, true)
                    .entrySet().stream().map(entry -> {
                return entry.getValue();
            }).forEach(flights -> data.getFlights().addAll((List<Flight>) flights));
            FlightsDataSupporter.getFlightsData().headMap(limitTime, true)
                    .entrySet().stream().map(entry -> {
                return entry.getValue();
            }).forEach(flights -> data.getFlights().addAll((List<Flight>) flights));

        }
        logger.info("search complete!");
        return data;
    }
}
