package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.exception.CustomException;
import com.ironbit.test.crud.mapper.IEmployeeMapper;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.repository.EmployeeRepository;
import com.ironbit.test.crud.service.IEmployeeUpdateService;
import com.ironbit.test.crud.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class EmployeeUpdateService extends AbstractBitacora implements IEmployeeUpdateService {

    /**
     * Logger slfj4
     */
    private static final Logger LOGGER = LogManager.getLogger(EmployeeUpdateService.class);

    /**
     * Employee repository
     */
    private final EmployeeRepository repository;

    /**
     * Constructor
     *
     * @param repository
     */
    public EmployeeUpdateService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to update data employee
     *
     * @param id       employee Id
     * @param employee Data updated
     * @return [Employee.class] updated
     * @throws CustomException exception with message error.
     */
    @Override
    public Mono<Employee> updateEmployee(long id, Employee employee) throws Exception {

        LOGGER.info("Init update service for id {}", id);

        // Find employee to update
        LOGGER.info("Find the employee to be update");
        Optional<EmployeeDTO> employeeDTO = repository.findById(id);

        // If exists, we update
        if (employeeDTO.isPresent()) {

            LOGGER.info("Employee to be update {}", employeeDTO.get());
            LOGGER.info("New data employee {}", employee);

            EmployeeDTO em = IEmployeeMapper.INSTANCE.employeeToDTO(employee);
            em.setId(id);

            Employee updateEmployee = IEmployeeMapper.INSTANCE.employeeDTOToModel(repository.save(em));

            saveBitacora(HttpMethod.PUT.name(), updateEmployee);

            return Mono.just(updateEmployee);

        } else {

            // throw exception if no exists employee in BD.
            LOGGER.error("Error in update service:: Employee not found");
            throw new CustomException(Constants.NO_DATA);

        }
    }

    @Override
    void saveBitacora(String operation, Object object) {

        LOGGER.info("INIT Service bitacora for:: {}", operation);

        BitacoraDTO bitacoraDTO = new BitacoraDTO();

        bitacoraDTO.setOperation(operation);
        bitacoraDTO.setData(object.toString());

        getBitacoraRepository().save(bitacoraDTO);
    }
}
