package com.challenge.prothera.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prothera.dto.PersonRequestDTO;
import com.challenge.prothera.dto.PersonResponseDTO;
import com.challenge.prothera.entities.Person;
import com.challenge.prothera.repositories.PersonRepository;
import com.challenge.prothera.service.exceptions.DataBaseException;
import com.challenge.prothera.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public PersonRequestDTO insert(PersonRequestDTO dto) {
        Person entity = new Person();
        copyDtoToPerson(dto, entity);
        personRepository.save(entity);
        return dto;
    }

    private void copyDtoToPerson(PersonRequestDTO dto, Person entity) {
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
    }

    @Transactional
    public PersonRequestDTO update(Long id, PersonRequestDTO dto) {
        try {
            Person entity = personRepository.getReferenceById(id);
            copyDtoToPerson(dto, entity);
            entity = personRepository.save(entity);
            return new PersonRequestDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            personRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

    @Transactional(readOnly = true)
    public PersonResponseDTO findById(Long id) {
        Optional<Person> result = personRepository.findById(id);
        return new PersonResponseDTO(result.get());
    }

    @Transactional(readOnly = true)
    public List<PersonResponseDTO> findAll() {
        List<Person> result = personRepository.findAllPerson();
        return result.stream().map(x -> new PersonResponseDTO(x)).toList();
    }
}
