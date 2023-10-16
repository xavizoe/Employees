package com.ironbit.test.crud.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Wrapper to save endpoint, wrap list employees
 */
@Getter
@Setter
@ApiModel("employees")
public class EmployeeList implements Serializable {

    private List<Employee> employees;

}
