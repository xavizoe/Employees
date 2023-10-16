package com.ironbit.test.crud.repository;

import com.ironbit.test.crud.dto.BitacoraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitacoraRepository extends JpaRepository<BitacoraDTO, Long> {
}
