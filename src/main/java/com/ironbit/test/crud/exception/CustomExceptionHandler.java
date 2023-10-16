package com.ironbit.test.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Custom exception handler, to catch custom exceptions
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Catch customException when throw
     * @param e exception
     * @return custom data with error
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorPayload> handleApplicationException(CustomException e) {

        ErrorPayload errorPayload = ErrorPayload
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity
                .status(errorPayload.getStatus())
                .body(errorPayload);
    }
}
