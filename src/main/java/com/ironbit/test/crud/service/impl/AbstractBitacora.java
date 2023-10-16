package com.ironbit.test.crud.service.impl;


import com.ironbit.test.crud.repository.BitacoraRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public abstract class AbstractBitacora {

    @Autowired
    protected BitacoraRepository bitacoraRepository;

    abstract void saveBitacora(String operation, Object object);

}
