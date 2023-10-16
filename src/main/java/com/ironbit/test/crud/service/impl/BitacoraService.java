package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.exception.CustomException;
import com.ironbit.test.crud.mapper.IBitacoraMapper;
import com.ironbit.test.crud.model.Bitacora;
import com.ironbit.test.crud.repository.BitacoraRepository;
import com.ironbit.test.crud.service.IBitacoraService;
import com.ironbit.test.crud.utils.Constants;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BitacoraService implements IBitacoraService {

    private final BitacoraRepository bitacoraRepository;

    public BitacoraService(BitacoraRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    @Override
    public Mono<List<Bitacora>> getBitacora() throws Exception {

        return Mono.just(Optional.of(bitacoraRepository.findAll())
                .filter(bitacoraDTOS -> !bitacoraDTOS.isEmpty())
                .orElseThrow(() -> new CustomException(Constants.NO_DATA))
                .stream()
                .filter(Objects::nonNull)
                .map(IBitacoraMapper.INSTANCE::bitacoraDTOToModel)
                .collect(Collectors.toList()));

    }


}
