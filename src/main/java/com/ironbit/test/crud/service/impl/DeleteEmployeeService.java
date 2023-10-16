package com.ironbit.test.crud.service.impl;

import com.ironbit.test.crud.dto.BitacoraDTO;
import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.exception.CustomException;
import com.ironbit.test.crud.repository.EmployeeRepository;
import com.ironbit.test.crud.service.IDeleteEmployeeService;
import com.ironbit.test.crud.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeService extends AbstractBitacora implements IDeleteEmployeeService {

    /**
     * Logger slfj4
     */
    private static final Logger LOGGER = LogManager.getLogger(DeleteEmployeeService.class);

    /**
     * Employee repository
     */
    private final EmployeeRepository repository;

    /**
     * Constructor
     *
     * @param repository employee repo
     */
    public DeleteEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Method that delete an employee from BD by Id
     *
     * @param id
     * @throws CustomException with message error
     */
    @Override
    public void deleteEmployee(long id) throws Exception {

        LOGGER.info("Init delete employee service, value id:: {}", id);

        try {

            EmployeeDTO employeeDTO = repository.findById(id)
                    .orElseThrow(() -> new CustomException(Constants.NO_DATA));

            repository.delete(employeeDTO);
            LOGGER.info("Deleted successful");

            saveBitacora(HttpMethod.DELETE.name(), employeeDTO);

        } catch (EmptyResultDataAccessException ex) {

            LOGGER.info("Error in delete employee:: {}", ex.getMessage());
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
