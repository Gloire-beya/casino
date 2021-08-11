package com.rank.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PlayerExceptionController {
    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseEntity<Object> playerNotFound(PlayerNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = new ErrorMessage("Player Not Found",
                                                     httpStatus,
                                                     LocalDateTime.now(),
                                            "Try with another player!");
        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
