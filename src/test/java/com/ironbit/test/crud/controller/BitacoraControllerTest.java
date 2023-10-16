package com.ironbit.test.crud.controller;

import com.ironbit.test.crud.model.Bitacora;
import com.ironbit.test.crud.service.IBitacoraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BitacoraControllerTest {

    private BitacoraController bitacoraController;

    @Mock
    private IBitacoraService bitacoraService;

    @BeforeEach
    public void load() {

        bitacoraController = new BitacoraController(bitacoraService);

    }

    @Test
    public void test_bitacora_controller_when_all_is_ok() throws Exception {

        Mockito.when(bitacoraService.getBitacora()).thenReturn(getBitacora());

        Mono<ResponseEntity<List<Bitacora>>> response = bitacoraController.getBitacora();

        StepVerifier
                .create(response)
                .expectNextMatches(data -> data.getStatusCode().is2xxSuccessful())
                .expectComplete()
                .verify();

    }

    private Mono<List<Bitacora>> getBitacora() {

        List<Bitacora> list = new ArrayList<>();
        Bitacora bitacora = new Bitacora();

        bitacora.setData("TEST");
        bitacora.setOperation("TEST");

        list.add(bitacora);

        return Mono.just(list);

    }

}
