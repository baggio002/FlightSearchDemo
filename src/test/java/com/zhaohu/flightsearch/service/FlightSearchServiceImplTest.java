package com.zhaohu.flightsearch.service;

import com.zhaohu.flightsearch.DateFormatParseException;
import com.zhaohu.flightsearch.Utils;
import com.zhaohu.flightsearch.data.FlightsDataSupporter;
import com.zhaohu.flightsearch.entity.Flight;
import com.zhaohu.flightsearch.entity.FlightsData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.zhaohu.flightsearch.TestUtils.generateFlights;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class FlightSearchServiceImplTest {
    private FlightSearchServiceImpl fsi = new FlightSearchServiceImpl();
    private Logger logger = Utils.getLogger(FlightSearchServiceImplTest.class);

    @Before
    public void setUp() throws Exception {
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
        fsi = null;
        logger = null;
    }

    @Test
    public void search() throws DateFormatParseException {
        logger.info("run search");
        assertThat(fsi.search("0:00AM").getFlights().size(), is(5));
        assertThat(fsi.search("11:59PM").getFlights().size(), is(5));
        assertThat(fsi.search("12:00PM").getFlights().size(), is(2));
        assertThat(fsi.search("12:01AM").getFlights().size(), is(7));
        assertThat(fsi.search("11:59AM").getFlights().size(), is(3));
        assertThat(fsi.search("12:00AM").getFlights().size(), is(5));
        assertThat(fsi.search("12:01PM").getFlights().size(), is(3));
        assertThat(fsi.search("0:30AM").getFlights().size(), is(6));
        assertThat(fsi.search("3:00AM").getFlights().size(), is(4));
        assertThat(fsi.search("3:00PM").getFlights().size(), is(2));
        assertThat(fsi.search("5:32PM").getFlights().size(), is(0));
        assertThat(fsi.search("3:00PM").getFlights().size(), is(2));

        assertThrown("1100");
        assertThrown("25:00PM");
        assertThrown("5:00am");
        assertThrown("05:00PM");
        assertThrown("15:00PM");
        assertThrown("OICQ");
        assertThrown("");
        assertThrown(null);

        logger.info("test search complete!");
    }

    private void assertThrown(String departure) {
        boolean isThrow = false;
        try {
            fsi.search(departure);
        } catch (DateFormatParseException e) {
            isThrow = true;
        } finally {
            assertTrue(isThrow);
        }
    }

}
