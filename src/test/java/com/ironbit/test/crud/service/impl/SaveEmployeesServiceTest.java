package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.model.EmployeeList;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SaveEmployeesServiceTest {

    private SaveEmployeesService saveEmployeesService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BitacoraRepository bitacoraRepository;

    @BeforeEach
    public void load() {

        saveEmployeesService = new SaveEmployeesService(employeeRepository);

    }

    @Test
    public void test_delete_employee_when_all_is_ok() throws Exception {

        EmployeeList employeeList = new EmployeeList();

        List<Employee> employees = new ArrayList<>();

        employees.add(Employee.builder().firstName("TEST").birthDate("12-12-1992").build());

        employeeList.setEmployees(employees);

        ReflectionTestUtils.setField(saveEmployeesService, "bitacoraRepository", bitacoraRepository);

        Mockito.when(employeeRepository.saveAll(Mockito.any())).thenReturn(getEmployeesDTO());

        Mockito.when(bitacoraRepository.save(Mockito.any())).thenReturn(getBitacoraDTO());

        Mono<List<Employee>> response = saveEmployeesService.saveEmpleado(employeeList);

        StepVerifier
                .create(response)
                .expectNextMatches(data -> !data.isEmpty())
                .expectComplete()
                .verify();


    }

    private List<EmployeeDTO> getEmployeesDTO() {

        List<EmployeeDTO> list = new ArrayList<>();

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setFirstName("TEST");

        list.add(employeeDTO);

        return list;

    }

    private BitacoraDTO getBitacoraDTO() {
        BitacoraDTO bitacoraDTO = new BitacoraDTO();

        bitacoraDTO.setData("TEST");

        return bitacoraDTO;

    }

}
