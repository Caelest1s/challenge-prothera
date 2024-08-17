package com.challenge.prothera.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.prothera.entities.Functionary;

public interface FunctionaryRepository extends JpaRepository<Functionary, Long> {

    @Query("SELECT obj FROM Functionary obj JOIN FETCH obj.person")
    List<Functionary> findAllFunctionaryWithPerson();

    @Query("DELETE FROM Functionary obj WHERE obj.id = :id")
    void deleteFunctionary(@Param("id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tb_functionary SET salary = salary + (salary * :percentage / 100)")
    void updateAllSalaries(@Param("percentage") BigDecimal percentage);
}
