package com.challenge.prothera.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prothera.dto.PersonDTO;
import com.challenge.prothera.entities.Person;
import com.challenge.prothera.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional
    public PersonDTO insert(PersonDTO dto) {
        Person entity = new Person();
        entity = copyDtoToPerson(dto);
        repository.save(entity);
        return dto;
    }

    private Person copyDtoToPerson(PersonDTO dto) {
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        return entity;
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Optional<Person> result = repository.findById(id);
        return copyPersonToDto(result);
    }

    private PersonDTO copyPersonToDto(Optional<Person> entity) {
        Person person = entity.get();
        PersonDTO dto = new PersonDTO(person);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        Page<Person> result = repository.findAll(pageable);
        return result.map(x -> new PersonDTO(x));
    }
}
