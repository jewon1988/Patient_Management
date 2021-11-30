package com.assignment.patient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/*
 * DataNotFoundExceptionHandler
 *
 * @Author Jewon Moon 11/27/2021
 * */
@ControllerAdvice
public class DataNotFoundExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(DataNotFoundExceptionHandler.class);

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException dnfe) {
        logger.info("Call handleDataNotFoundException(DataNotFoundException dnfe)");

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        DataExceptionInfo dataExceptionInfo = new DataExceptionInfo(
                dnfe.getMessage(),
                ZonedDateTime.now(ZoneId.of("America/New_York"))
        );

        return new ResponseEntity<>(dataExceptionInfo, notFound);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        logger.info("Call handleException(Exception e)");

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        DataExceptionInfo dataExceptionInfo = new DataExceptionInfo(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("America/New_York"))
        );

        return new ResponseEntity<>(dataExceptionInfo, notFound);
    }
}
