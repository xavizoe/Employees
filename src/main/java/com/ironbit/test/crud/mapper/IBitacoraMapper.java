package com.ironbit.test.crud.mapper;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.model.Bitacora;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IBitacoraMapper {

    IBitacoraMapper INSTANCE = Mappers.getMapper(IBitacoraMapper.class);

    /**
     * Map employee model to employeeDTO
     * @param employee model
     * @return dto employee
     */
    @Mapping(source = "operation", target = "operation")
    @Mapping(source = "data", target = "data")
    Bitacora bitacoraDTOToModel(BitacoraDTO employee);

}
