package com.zhaohu.flightsearch.controller;

import com.zhaohu.flightsearch.Constants;
import com.zhaohu.flightsearch.DateFormatParseException;
import com.zhaohu.flightsearch.data.ExceptionData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = RestController.class)
public class FlightSearchExceptionController{


    @ExceptionHandler(DateFormatParseException.class)
    @ResponseBody
    public ExceptionData handleDateFormatParseException(HttpServletRequest req, DateFormatParseException ex) {
        return new ExceptionData(Constants.DATA_FORMAT_PARSE_EXCEPTION_ERROR_CODE, ex.getMessage());
    }
}
