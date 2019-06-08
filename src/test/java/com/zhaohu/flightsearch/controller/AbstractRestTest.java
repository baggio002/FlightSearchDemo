package com.zhaohu.flightsearch.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaohu.flightsearch.FlightSearchApplication;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlightSearchApplication.class)
@WebAppConfiguration
public class AbstractRestTest {
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() throws Exception {
        mockMvc = null;
    }

}
