package com.zhaohu.flightsearch.data;

public class ExceptionData {
    private int status = 0;
    private String message = null;

    public ExceptionData(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ExceptionData() {}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
