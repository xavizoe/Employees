package com.ironbit.test.crud.mapper;

import com.ironbit.test.crud.dto.EmployeeDTO;
import com.ironbit.test.crud.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct's library, to map dto to employee and backwards
 */
@Mapper
public interface IEmployeeMapper {

    /**
     * Instance to use the mapper
     */
    IEmployeeMapper INSTANCE = Mappers.getMapper(IEmployeeMapper.class);

    /**
     * Map employee model to employeeDTO
     * @param employee model
     * @return dto employee
     */
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "secondName", target = "secondName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "secondLastName", target = "secondLastName")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "job", target = "job")
    EmployeeDTO employeeToDTO(Employee employee);

    /**
     * Map employeeDTO to employee model
     * @param employee DTO
     * @return employee model
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "secondName", target = "secondName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "secondLastName", target = "secondLastName")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "job", target = "job")
    Employee employeeDTOToModel(EmployeeDTO employee);

}
