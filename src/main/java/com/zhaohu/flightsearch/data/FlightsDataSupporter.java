package com.zhaohu.flightsearch.data;

import com.zhaohu.flightsearch.Constants;
import com.zhaohu.flightsearch.Utils;
import com.zhaohu.flightsearch.entity.Flight;
import com.zhaohu.flightsearch.entity.FlightsData;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

@Component
public class FlightsDataSupporter implements CommandLineRunner {
    private Logger logger = Utils.getLogger(FlightsDataSupporter.class);

    private static final TreeMap<LocalTime, List<Flight>> flightsDepartureMap = new TreeMap<LocalTime, List<Flight>>(
            new Comparator<LocalTime>() {
                @Override
                public int compare(LocalTime o1, LocalTime o2) {
                    if (o1.isAfter(o2)) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
    );

    @Override
    public void run(String... args) throws Exception {
        String resourceName = null;
        if (args == null || args.length == 0) {
            resourceName = Constants.DEFAULT_RESOURCE_NAME;
        }
        logger.info("prepare data..." + resourceName);
        FlightsData data = Utils.getFlightsData(resourceName);
        data.getFlights().stream()
                .filter(flight -> {
                    if (Utils.verifyTimeFormat(flight.getDeparture())) {
                        return true;
                    } else {
                        logger.error("Ilegel departure time: " + flight.getFlight() + " " + flight.getDeparture());
                        return false;
                    }
                })
                .forEach((flight) -> {
                    LocalTime localTime = Utils.formatDeparture(flight.getDeparture());
                    if (flightsDepartureMap.containsKey(localTime)) {
                        flightsDepartureMap.get(localTime).add(flight);
                    } else {
                        List<Flight> list = new ArrayList<Flight>();
                        list.add(flight);
                        flightsDepartureMap.put(localTime, list);
                    }
                });
        logger.info("data is ready! ");
    }

    public static TreeMap<LocalTime, List<Flight>> getFlightsData() {
        return flightsDepartureMap;
    }
}
