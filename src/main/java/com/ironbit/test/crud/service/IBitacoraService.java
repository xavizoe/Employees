package com.ironbit.test.crud.service;

import com.ironbit.test.crud.model.Bitacora;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBitacoraService {

    Mono<List<Bitacora>> getBitacora() throws Exception;

}
