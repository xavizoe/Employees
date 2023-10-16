package com.ironbit.test.crud.mapper;

import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTest {


    @Test
    public void testMap_when_all_is_ok() {

        Employee employee = Employee.builder().firstName("TEST").build();

        //when
        EmployeeDTO employeeDTO = IEmployeeMapper.INSTANCE.employeeToDTO(employee);

        //then
        Assertions.assertThat(employeeDTO).isNotNull();
        Assertions.assertThat(employeeDTO.getFirstName()).isEqualTo("TEST");

    }

    @Test
    public void testMapDTO_when_all_is_ok() {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setFirstName("TEST");

        //when
        Employee employee = IEmployeeMapper.INSTANCE.employeeDTOToModel(employeeDTO);

        //then
        Assertions.assertThat(employee).isNotNull();
        Assertions.assertThat(employee.getFirstName()).isEqualTo("TEST");

    }


}
