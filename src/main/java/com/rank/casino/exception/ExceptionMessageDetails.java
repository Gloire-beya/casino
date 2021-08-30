package com.rank.casino.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionMessageDetails {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime dateTime;
    private final String details;

    public ExceptionMessageDetails(String message, HttpStatus httpStatus, LocalDateTime dateTime, String details) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDetails() {
        return details;
    }
}
