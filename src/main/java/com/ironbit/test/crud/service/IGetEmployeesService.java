package com.ironbit.test.crud.service;

import com.ironbit.test.crud.model.Employee;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Method to get all employee from BD.
 */
public interface IGetEmployeesService {

    Mono<List<Employee>> getEmployees() throws Exception;

}
