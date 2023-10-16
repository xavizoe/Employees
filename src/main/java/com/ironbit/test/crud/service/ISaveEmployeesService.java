package com.ironbit.test.crud.service;

import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.model.EmployeeList;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Method to save one or more employees in BD.
 */
public interface ISaveEmployeesService {

    Mono<List<Employee>> saveEmpleado(EmployeeList employeeList) throws Exception;

}
