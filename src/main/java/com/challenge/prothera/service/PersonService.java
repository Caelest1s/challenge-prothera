package com.challenge.prothera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prothera.dto.PersonDTO;
import com.challenge.prothera.entities.Person;
import com.challenge.prothera.repositories.PersonRepository;

public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional
    public PersonDTO insert(PersonDTO dto) {

        repository.save(new Person());
        return new PersonDTO();
    }
}
