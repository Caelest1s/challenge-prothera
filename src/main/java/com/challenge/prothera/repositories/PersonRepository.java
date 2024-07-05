package com.challenge.prothera.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.prothera.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    // Join Fetch não funciona para buscas paginadas
    @Query("SELECT obj FROM Person obj JOIN FETCH obj.functionary")
    Page<Person> findAllPersonPage(Pageable pageable);

    @Query("SELECT obj FROM Person obj")
    List<Person> findAllPerson();
}
