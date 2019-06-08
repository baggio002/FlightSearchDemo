package com.zhaohu.flightsearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaohu.flightsearch.Constants;
import com.zhaohu.flightsearch.DateFormatParseException;
import com.zhaohu.flightsearch.Utils;
import com.zhaohu.flightsearch.data.ExceptionData;
import com.zhaohu.flightsearch.data.FlightsDataSupporter;
import com.zhaohu.flightsearch.entity.Flight;
import com.zhaohu.flightsearch.service.FlightSearchServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.zhaohu.flightsearch.TestUtils.generateFlights;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FlightSearchControllerTest extends AbstractRestTest {
    private Logger logger = Utils.getLogger(FlightSearchControllerTest.class);
    private FlightSearchServiceImpl fsi = new FlightSearchServiceImpl();

    @Before
    public void setUp() {
        super.setUp();
        generateFlights().stream()
                .forEach(flight -> {
                    LocalTime time = Utils.formatDeparture(flight.getDeparture());
                    if (FlightsDataSupporter.getFlightsData().containsKey(time)) {
                        FlightsDataSupporter.getFlightsData().get(time).add(flight);
                    } else {
                        List<Flight> list = new ArrayList<Flight>();
                        list.add(flight);
                        FlightsDataSupporter.getFlightsData().put(time, list);
                    }
                });

    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        fsi = null;
        logger = null;
    }

    @Test
    public void searchByDeparture() throws DateFormatParseException, UnsupportedEncodingException {
        ExceptionData eData = new ExceptionData(Constants.DATA_FORMAT_PARSE_EXCEPTION_ERROR_CODE, String.format(Constants.DATA_FORMAT_PARSE_EXCEPTION_MESSEGE, "ERRORDATA"));
        assertRestResult("/search/departure/12:00AM", convertToJson(fsi.search("12:00AM")));
        assertRestResult("/search/departure/12:00PM", convertToJson(fsi.search("12:00PM")));
        assertRestResult("/search/departure/ERRORDATA", convertToJson(eData));
    }

    private void assertRestResult(String uri, String expectResult) throws UnsupportedEncodingException {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        assertEquals(mvcResult.getResponse().getContentAsString(), expectResult);
    }

    private<T> String convertToJson(T t) {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(t);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return json;
    }
}
