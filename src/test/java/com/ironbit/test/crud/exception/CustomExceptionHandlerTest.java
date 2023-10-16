package com.ironbit.test.crud.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    public void load() {

        customExceptionHandler = new CustomExceptionHandler();

    }

    @Test
    public void test_getException_when_all_is_ok() {

        CustomException customException = new CustomException("Error");

        ResponseEntity<ErrorPayload> response = customExceptionHandler.handleApplicationException(customException);

        Assertions.assertNotNull(response.getBody());

    }

}
