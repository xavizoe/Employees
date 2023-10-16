package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.exception.CustomException;
import com.ironbit.test.crud.mapper.IEmployeeMapper;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.model.EmployeeList;
import com.ironbit.test.crud.repository.EmployeeRepository;
import com.ironbit.test.crud.service.ISaveEmployeesService;
import com.ironbit.test.crud.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaveEmployeesService extends AbstractBitacora implements ISaveEmployeesService {

    /**
     * Logger log4j
     */
    private static final Logger LOGGER = LogManager.getLogger(SaveEmployeesService.class);

    /**
     * Employee repository
     */
    private final EmployeeRepository repository;

    /**
     * Employee repository
     *
     * @param repository
     */
    public SaveEmployeesService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to save list employee
     *
     * @param employeeList employees to save
     * @return List<Employee> saved in BD.
     * @throws CustomException if some error occurs in save.
     */
    @Override
    public Mono<List<Employee>> saveEmpleado(EmployeeList employeeList) throws Exception {

        LOGGER.info(":::::Init save employees service::::");

        // Validate pattern
        this.validatePattern(employeeList);

        // Transform employee list model to dto list
        List<EmployeeDTO> employeeDTO = Optional.of(employeeList)
                .map(EmployeeList::getEmployees)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(IEmployeeMapper.INSTANCE::employeeToDTO)
                .collect(Collectors.toList());

        LOGGER.info("Employees to be saved:: {}", Collections.singletonList(employeeDTO));

        List<EmployeeDTO> savedEmployees;

        try {

            // save all list
            savedEmployees = repository.saveAll(employeeDTO);

        } catch (ConstraintViolationException ex) {

            LOGGER.error("Error in save employees:: {}", ex.getMessage());
            throw new CustomException(Constants.ERROR_IN_SAVE);

        }

        LOGGER.info("Saved employees to map:: {}", Collections.singletonList(savedEmployees));
        saveBitacora(HttpMethod.POST.name(), savedEmployees);

        //once saved, we map dto to model list
        return Mono.just(Optional.of(savedEmployees)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(IEmployeeMapper.INSTANCE::employeeDTOToModel)
                .collect(Collectors.toList()));

    }

    /**
     * Fix method to validate birthdate field format
     * This method must be in API contract, i had problems with binding result.
     *
     * @param employeeList data employee list
     * @throws CustomException if invalid format
     */
    private void validatePattern(EmployeeList employeeList) throws CustomException {

        LOGGER.info("Will validate birthDate pattern:::");

        boolean found = Optional.of(employeeList)
                .map(EmployeeList::getEmployees)
                .orElse(Collections.emptyList())
                .stream()
                .anyMatch(data -> !data.getBirthDate().matches("^\\d{2}-\\d{2}-\\d{4}"));

        if (found) {
            LOGGER.error("Pattern birthDate invalid");
            throw new CustomException(Constants.INVALID_PATTERN);
        }

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
