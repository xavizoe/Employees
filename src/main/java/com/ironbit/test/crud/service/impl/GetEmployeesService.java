package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.exception.CustomException;
import com.ironbit.test.crud.mapper.IEmployeeMapper;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.repository.EmployeeRepository;
import com.ironbit.test.crud.service.IGetEmployeesService;
import com.ironbit.test.crud.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetEmployeesService extends AbstractBitacora implements IGetEmployeesService {

    /**
     * Logger slfj4
     */
    private static final Logger LOGGER = LogManager.getLogger(GetEmployeesService.class);

    /**
     * Employee repository
     */
    private final EmployeeRepository repository;

    /**
     * Constructor
     *
     * @param repository
     */
    public GetEmployeesService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to get all employees from bd
     *
     * @return List employees
     * @throws CustomException if not data retrieve
     */
    @Override
    public Mono<List<Employee>> getEmployees() throws Exception {

        LOGGER.info("Init service search all employees::");

        // Execute query
        List<EmployeeDTO> employees = repository.findAll();

        LOGGER.info("Employees found {}", Collections.singletonList(employees));

        // verify if exists data and map to model employee
        List<Employee> result = Optional.of(employees)
                .filter(r -> !r.isEmpty())
                .orElseThrow(() -> new CustomException(Constants.NO_DATA))
                .stream()
                .map(IEmployeeMapper.INSTANCE::employeeDTOToModel)
                .collect(Collectors.toList());

        saveBitacora(HttpMethod.GET.name(), result);

        return Mono.just(result);
    }

    @Override
    void saveBitacora(String operation, Object object) {

        LOGGER.info("INIT Service bitacora for:: {}", operation);

        List<Object> list = Collections.singletonList(object);

        String data = list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        BitacoraDTO bitacoraDTO = new BitacoraDTO();

        bitacoraDTO.setOperation(operation);
        bitacoraDTO.setData(data);

        getBitacoraRepository().save(bitacoraDTO);
    }
}
