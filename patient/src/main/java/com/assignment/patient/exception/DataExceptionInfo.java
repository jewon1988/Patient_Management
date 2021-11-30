package com.assignment.patient.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/*
 * DataExceptionInfo
 *
 * @Author Jewon Moon 11/27/2021
 * */
public class DataExceptionInfo {
    private final String message;
    private final ZonedDateTime timestamp;

    public DataExceptionInfo(String message, ZonedDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
