package com.zhaohu.flightsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaohu.flightsearch.entity.FlightsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Utils {
    public static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private static final String TIME_FORMAT_PATTERN = "([0-9]|([1][0-2])):[0-5][0-9]([A]|[P])M";
    private static final String TIME_FORMAT = "h:mma";

    private static ObjectMapper mapper = new ObjectMapper();
    
    public static Logger getLogger(Class cls) {
        return LoggerFactory.getLogger(cls.getName());
    }

    public static FlightsData getFlightsData(String fileName) {
        FlightsData data = null;
        try {
            data = mapper.readValue(new String(Files.readAllBytes(Paths.get(ResourceUtils
                    .getFile("classpath:" + fileName).getAbsolutePath()))), FlightsData.class);
        } catch (IOException e) {
            logger.error("getFlightsData: get data source fail! ");
        }
        return data;
    }

    public static boolean verifyTimeFormat(String time) {
        if (time == null) {
            return false;
        }
        return Pattern.matches(TIME_FORMAT_PATTERN, time);
    }

    public static LocalTime formatDeparture(String departure) {
        return LocalTime.parse(departure, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
