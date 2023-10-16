package com.ironbit.test.crud.controller;

import com.ironbit.test.crud.exception.ErrorPayload;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.model.EmployeeList;
import com.ironbit.test.crud.service.IDeleteEmployeeService;
import com.ironbit.test.crud.service.IEmployeeUpdateService;
import com.ironbit.test.crud.service.IGetEmployeesService;
import com.ironbit.test.crud.service.ISaveEmployeesService;
import com.ironbit.test.crud.utils.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

    private final IGetEmployeesService getEmployeesService;
    private final ISaveEmployeesService saveEmployeesService;
    private final IDeleteEmployeeService deleteEmployeeService;
    private final IEmployeeUpdateService employeeService;

    public EmployeeController(IGetEmployeesService getEmployeesService, ISaveEmployeesService saveEmployeesService, IDeleteEmployeeService deleteEmployeeService, IEmployeeUpdateService employeeService) {
        this.getEmployeesService = getEmployeesService;
        this.saveEmployeesService = saveEmployeesService;
        this.deleteEmployeeService = deleteEmployeeService;
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Get all employees",
            produces = "application/json",
            notes = "Get all employees saved in BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK.", response = Employee.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not Found. No employees found in BD",
                    response = ErrorPayload.class)
    })
    @GetMapping("/findAll")
    public Mono<ResponseEntity<List<Employee>>> getAllEmployees(@ApiIgnore final ServerWebExchange exchange) throws Exception {

        LOGGER.info(":::Init get employees::::");
        printHeaders(exchange);

        return getEmployeesService.getEmployees()
                .map(data -> ResponseEntity.status(HttpStatus.OK).body(data));
    }

    @ApiOperation(value = "Save one or more employees",
            produces = "application/json",
            consumes = "application/json",
            notes = "Save employees in BD")
    @ApiParam()
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = Employee.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Error",
                    response = ErrorPayload.class)
    })
    @PostMapping(value = "/saveAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<Employee>>> saveEmployee(@RequestBody EmployeeList employeeList,
                                                             @ApiIgnore final ServerWebExchange serverWebExchange) throws Exception {

        LOGGER.info(":::::Init save employees::::");
        printHeaders(serverWebExchange);

        return saveEmployeesService.saveEmpleado(employeeList)
                .map(data -> ResponseEntity.status(HttpStatus.CREATED).body(data));
    }


    @ApiOperation(value = "Delete an employee by id.",
            produces = "application/json",
            notes = "Delete an employee from BD")
    @ApiParam()
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO CONTENT, successful deleted", response = ErrorPayload.class),
            @ApiResponse(code = 404, message = "No employee found",
                    response = ErrorPayload.class)
    })
    @DeleteMapping("/delete/employee/{id}")
    public Mono<ErrorPayload> deleteEmployee(@PathVariable("id") long id,
                                             @ApiIgnore final ServerWebExchange serverWebExchange) throws Exception {

        LOGGER.info("::::Init delete employee::::");
        printHeaders(serverWebExchange);

        deleteEmployeeService.deleteEmployee(id);

        return Mono.just(ErrorPayload
                .builder()
                .message(Constants.EMPLOYEE_DELETED)
                .status(HttpStatus.NO_CONTENT)
                .build());
    }

    @ApiOperation(value = "Update some data from an employee by id.",
            produces = "application/json",
            consumes = "application/json",
            notes = "Update an employee in BD")
    @ApiParam()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = Employee.class),
            @ApiResponse(code = 404, message = "No employee found",
                    response = ErrorPayload.class)
    })
    @PutMapping("update/employee/{id}")
    public Mono<ResponseEntity<Employee>> updateEmployee(@PathVariable("id") long id,
                                                         @RequestBody Employee employee,
                                                         @ApiIgnore final ServerWebExchange serverWebExchange) throws Exception {

        LOGGER.info("::::Init update employee::");
        printHeaders(serverWebExchange);

        return employeeService.updateEmployee(id, employee)
                .map(data -> ResponseEntity.status(HttpStatus.OK)
                        .body(data));
    }

    /**
     * Print all headers in request
     *
     * @param serverWebExchange http request data
     */
    private void printHeaders(ServerWebExchange serverWebExchange) {

        Optional.ofNullable(serverWebExchange)
                .map(ServerWebExchange::getRequest)
                .map(HttpMessage::getHeaders)
                .ifPresent(LOGGER::info);

    }

}
