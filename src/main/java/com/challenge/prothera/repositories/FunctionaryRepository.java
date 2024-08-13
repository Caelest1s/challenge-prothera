package com.challenge.prothera.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.prothera.entities.Functionary;

public interface FunctionaryRepository extends JpaRepository<Functionary, Long> {

    @Query("SELECT obj FROM Functionary obj JOIN FETCH obj.person")
    List<Functionary> findFunctionaryWithPerson();

    @Query("DELETE FROM Functionary obj WHERE obj.id = :id")
    void deleteFunctionary(@Param("id") Long id);
}
