package com.rank.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class InsufficientExceptionController {
    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ResponseEntity<Object> insufficientBalance(InsufficientBalanceException exception){
        HttpStatus httpStatus = HttpStatus.PRECONDITION_FAILED;
        ErrorMessage errorMessage = new ErrorMessage("Insufficient balance!",
                                                     httpStatus,
                                                     LocalDateTime.now(),
                                                     "Add more money into your account for you to play!");
        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
