package com.ironbit.test.crud.mapper;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.model.Bitacora;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BitacoraMapperTest {

    @Test
    public void testMap_when_all_is_ok() {

        BitacoraDTO bitacoraDTO = new BitacoraDTO();

        bitacoraDTO.setOperation("TEST");
        bitacoraDTO.setData("TEST");

        //when
        Bitacora bitacora = IBitacoraMapper.INSTANCE.bitacoraDTOToModel(bitacoraDTO);

        //then
        Assertions.assertThat(bitacora).isNotNull();
        Assertions.assertThat(bitacora.getData()).isEqualTo("TEST");

    }


}
