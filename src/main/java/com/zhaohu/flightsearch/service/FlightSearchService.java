package com.zhaohu.flightsearch.service;

import com.zhaohu.flightsearch.DateFormatParseException;
import com.zhaohu.flightsearch.entity.FlightsData;

public interface FlightSearchService {
    public FlightsData search(String departure) throws DateFormatParseException;
}
