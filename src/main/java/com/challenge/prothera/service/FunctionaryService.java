package com.challenge.prothera.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prothera.dto.FunctionaryDTO;
import com.challenge.prothera.entities.Functionary;
import com.challenge.prothera.entities.Person;
import com.challenge.prothera.repositories.FunctionaryRepository;
import com.challenge.prothera.repositories.PersonRepository;
import com.challenge.prothera.service.exceptions.DataBaseException;
import com.challenge.prothera.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FunctionaryService {

    @Autowired
    private FunctionaryRepository functionaryRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public FunctionaryDTO insert(FunctionaryDTO dto) {
        String name = dto.getPersonDTO().getName();
        LocalDate birthDate = dto.getPersonDTO().getBirthDate();
        String office = dto.getOffice();
        Double salary = dto.getSalary();

        try {
            Person person = new Person(name, birthDate);
            Functionary functionary = new Functionary(null, office, salary, person);

            functionary = functionaryRepository.save(functionary);
            return new FunctionaryDTO(functionary);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("recurso não encontrado");
        }
    }

    private void copyDtoToEntity(FunctionaryDTO dto, Functionary entity) {
        entity.setOffice(dto.getOffice());
        entity.setSalary(dto.getSalary());
    }

    @Transactional
    public FunctionaryDTO update(Long id, FunctionaryDTO dto) {
        try {
            Person person = personRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
            Functionary functionary = functionaryRepository.findById(id)
                    .orElseGet(() -> new Functionary());

            if (functionary.getId() == null) {
                functionary.setPerson(person);
                copyDtoToEntity(dto, functionary);
                functionaryRepository.save(functionary);
            } else {
                functionary = functionaryRepository.getReferenceById(id);
                copyDtoToEntity(dto, functionary);
                functionaryRepository.save(functionary);
            }

            return new FunctionaryDTO(functionary);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            Functionary result = functionaryRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
            Person person = result.getPerson();
            person.setFunctionary(null);
            personRepository.save(person);
            functionaryRepository.delete(result);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

    @Transactional(readOnly = true)
    public FunctionaryDTO findById(Long id) {
        Optional<Functionary> entity = functionaryRepository.findById(id);
        return copyFunctionaryToDto(entity);
    }

    private FunctionaryDTO copyFunctionaryToDto(Optional<Functionary> entity) {
        Functionary functionary = entity.get();
        FunctionaryDTO dto = new FunctionaryDTO(functionary);
        return dto;
    }

    // @Transactional(readOnly = true)
    // public Page<FunctionaryDTO> findAll(Pageable pageable) {
    // Page<Functionary> entity = functionaryRepository.findAll(pageable);
    // return entity.map(x -> new FunctionaryDTO(x));
    // }

    @Transactional(readOnly = true)
    public List<FunctionaryDTO> findAllFunctionaryWithPerson() {
        List<Functionary> entity = functionaryRepository.findAllFunctionaryWithPerson();
        return entity.stream().map(x -> new FunctionaryDTO(x)).toList();
    }
}
