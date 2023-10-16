package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.model.Bitacora;
import com.ironbit.test.crud.repository.BitacoraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BitacoraServiceTest {

    private BitacoraService bitacoraService;

    @Mock
    private BitacoraRepository repository;

    @BeforeEach
    public void load() {

        bitacoraService = new BitacoraService(repository);
    }

    @Test
    public void test_getBitacora_when_all_is_ok() throws Exception {

        Mockito.when(repository.findAll()).thenReturn(getBitacora());

        Mono<List<Bitacora>> response = bitacoraService.getBitacora();

        StepVerifier
                .create(response)
                .expectNextMatches(data -> !data.isEmpty())
                .expectComplete()
                .verify();

    }

    private List<BitacoraDTO> getBitacora() {

        List<BitacoraDTO> list = new ArrayList<>();

        BitacoraDTO bitacora = new BitacoraDTO();
        bitacora.setOperation("TEST");
        bitacora.setData("TEST");

        list.add(bitacora);

        return list;

    }
}
