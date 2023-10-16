package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.repository.BitacoraRepository;
import com.ironbit.test.crud.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeleteEmployeeServiceTest {

    private DeleteEmployeeService deleteEmployeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BitacoraRepository bitacoraRepository;

    @BeforeEach
    public void load() {

        deleteEmployeeService = new DeleteEmployeeService(employeeRepository);

    }

    @Test
    public void test_delete_employee_when_all_is_ok() throws Exception {

        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(getEmployeeDTO());

        ReflectionTestUtils.setField(deleteEmployeeService,"bitacoraRepository", bitacoraRepository);

        Mockito.when(bitacoraRepository.save(Mockito.any())).thenReturn(getBitacoraDTO());

        deleteEmployeeService.deleteEmployee(3);


    }

    private Optional<EmployeeDTO> getEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setFirstName("TEST");

        return Optional.of(employeeDTO);

    }

    private BitacoraDTO getBitacoraDTO() {
        BitacoraDTO bitacoraDTO = new BitacoraDTO();

        bitacoraDTO.setData("TEST");

        return bitacoraDTO;

    }


}
