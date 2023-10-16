package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.repository.BitacoraRepository;
import com.ironbit.test.crud.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeUpdateServiceTest {

    private EmployeeUpdateService updateService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BitacoraRepository bitacoraRepository;

    @BeforeEach
    public void load() {

        updateService = new EmployeeUpdateService(employeeRepository);

    }

    @Test
    public void test_delete_employee_when_all_is_ok() throws Exception {

        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(getEmployeeDTO());

        ReflectionTestUtils.setField(updateService, "bitacoraRepository", bitacoraRepository);

        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(getEmployeeDTO().get());

        Mockito.when(bitacoraRepository.save(Mockito.any())).thenReturn(getBitacoraDTO());

        Mono<Employee> response = updateService.updateEmployee(1, Employee.builder().firstName("TEST").build());

        StepVerifier
                .create(response)
                .expectNextMatches(data -> data.getFirstName().equals("TEST"))
                .expectComplete()
                .verify();


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
