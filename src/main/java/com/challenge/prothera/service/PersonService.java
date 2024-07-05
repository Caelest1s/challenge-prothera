package com.challenge.prothera.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prothera.dto.PersonDTO;
import com.challenge.prothera.entities.Person;
import com.challenge.prothera.repositories.PersonRepository;
import com.challenge.prothera.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional
    public PersonDTO insert(PersonDTO dto) {
        Person entity = new Person();
        copyDtoToPerson(dto, entity);
        repository.save(entity);
        return dto;
    }

    private void copyDtoToPerson(PersonDTO dto, Person entity) {
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
    }

    @Transactional
    public PersonDTO update(Long id, PersonDTO dto) {
        try {
            Person entity = repository.getReferenceById(id);
            copyDtoToPerson(dto, entity);
            entity = repository.save(entity);
            return dto;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
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
    public List<PersonDTO> findAll() {
        // Page<Person> result = repository.findAllPersonPage(pageable);
        List<Person> result = repository.findAllPerson();
        return result.stream().map(x -> new PersonDTO(x)).toList();
    }
}
