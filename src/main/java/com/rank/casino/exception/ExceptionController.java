package com.rank.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ResponseEntity<Object> insufficientBalance(InsufficientBalanceException exception) {
        HttpStatus httpStatus = HttpStatus.PRECONDITION_FAILED;
        ExceptionMessageDetails exceptionMessageDetails = new ExceptionMessageDetails(exception.getMessage(),
                                                                                      httpStatus,
                                                                                      LocalDateTime.now(),
                                                                                      "Add more money into your account for you to play!");
        return new ResponseEntity<>(exceptionMessageDetails, httpStatus);
    }

    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseEntity<Object> playerNotFound(PlayerNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = new ExceptionMessageDetails(exception.getMessage(),
                                                       httpStatus,
                                                       LocalDateTime.now(),
                                                       "Try with another player!");
        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
