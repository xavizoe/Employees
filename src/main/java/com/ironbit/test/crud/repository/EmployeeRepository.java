package com.ironbit.test.crud.repository;

import com.ironbit.test.crud.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Employee repository
 * contains methods to find, save, delete data from BD.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
}
