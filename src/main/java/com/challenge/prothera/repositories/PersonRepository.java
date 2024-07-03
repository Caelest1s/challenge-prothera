package com.challenge.prothera.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.prothera.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
