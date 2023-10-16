package com.ironbit.test.crud.controller;

import com.ironbit.test.crud.exception.ErrorPayload;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.model.EmployeeList;
import com.ironbit.test.crud.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private EmployeeController employeeController;

    @Mock
    private IGetEmployeesService getEmployeesService;

    @Mock
    private ISaveEmployeesService saveEmployeesService;

    @Mock
    private IDeleteEmployeeService deleteEmployeeService;

    @Mock
    private IEmployeeUpdateService updateService;

    private MockServerWebExchange mockServerWebExchange;

    @BeforeEach
    public void load() {

        employeeController = new EmployeeController(getEmployeesService, saveEmployeesService, deleteEmployeeService, updateService);

    }

    @Test
    public void test_getEmployees_when_all_is_ok() throws Exception {

        Mockito.when(getEmployeesService.getEmployees()).thenReturn(getEmployees());

        Mono<ResponseEntity<List<Employee>>> response = employeeController.getAllEmployees(mockServerWebExchange);

        StepVerifier
                .create(response)
                .expectNextMatches(data -> data.getBody() != null)
                .expectComplete()
                .verify();

    }

    @Test
    public void test_saveEmployees_when_all_is_ok() throws Exception {

        EmployeeList employeeList = new EmployeeList();
        List<Employee> employees = new ArrayList<>();

        employees.add(Employee.builder().age("TEST").build());
        employeeList.setEmployees(employees);

        Mockito.when(saveEmployeesService.saveEmpleado(Mockito.any())).thenReturn(getEmployees());

        Mono<ResponseEntity<List<Employee>>> response = employeeController
                .saveEmployee(employeeList, mockServerWebExchange);

        StepVerifier
                .create(response)
                .expectNextMatches(data -> data.getBody() != null)
                .expectComplete()
                .verify();

    }

    @Test
    public void test_updateEmployee_when_all_is_ok() throws Exception {

        Mockito.when(updateService.updateEmployee(Mockito.anyLong(), Mockito.any()))
                .thenReturn(getEmployee());

        Mono<ResponseEntity<Employee>> response = employeeController
                .updateEmployee(1, Employee.builder().age("TEST").build(), mockServerWebExchange);

        StepVerifier
                .create(response)
                .expectNextMatches(data -> data.getBody() != null)
                .expectComplete()
                .verify();

    }

    @Test
    public void test_deleteEmployee_when_all_is_ok() throws Exception {

        Mono<ErrorPayload> response = employeeController.deleteEmployee(1, mockServerWebExchange);

        StepVerifier
                .create(response)
                .expectNextMatches(data -> data.getMessage().equals("Employee deleted"))
                .expectComplete()
                .verify();

    }

    private Mono<List<Employee>> getEmployees() {

        List<Employee> list = new ArrayList<>();

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("TEST").build();

        list.add(employee);

        return Mono.just(list);

    }

    private Mono<Employee> getEmployee() {

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("TEST").build();

        return Mono.just(employee);

    }

}
