package com.zhaohu.flightsearch.controller;

import com.zhaohu.flightsearch.DateFormatParseException;
import com.zhaohu.flightsearch.entity.FlightsData;
import com.zhaohu.flightsearch.Utils;
import com.zhaohu.flightsearch.service.FlightSearchService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
public class FlightSearchController {
    public static Logger logger = Utils.getLogger(FlightSearchController.class);

    @Autowired
    private FlightSearchService service;

    @RequestMapping(method= RequestMethod.GET, value="/search/departure/{departure}")
    public FlightsData searchByDeparture(@PathVariable String departure) throws DateFormatParseException {
        logger.info("searchByDeparture: " + departure);
        return service.search(departure);
    }
}
