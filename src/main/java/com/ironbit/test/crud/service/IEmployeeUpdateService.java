package com.ironbit.test.crud.service;

import com.ironbit.test.crud.model.Employee;
import reactor.core.publisher.Mono;

/**
 * Method to update an employee from bd by id
 */
public interface IEmployeeUpdateService {

    Mono<Employee> updateEmployee(long id, Employee employee) throws Exception;


}
