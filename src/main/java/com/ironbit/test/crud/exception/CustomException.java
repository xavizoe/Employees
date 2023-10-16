package com.ironbit.test.crud.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Custom exception data
 */
@Getter
@Setter
public class CustomException extends Exception {

    private String message;

    public CustomException(String message) {
        this.message = message;
    }
}
