package com.challenge.prothera.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prothera.dto.FunctionaryRequestDTO;
import com.challenge.prothera.dto.FunctionaryResponseDTO;
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
    public FunctionaryRequestDTO insert(FunctionaryRequestDTO dto) {
        try {
            Functionary functionary = new Functionary();
            copyDtoToEntity(dto, functionary);
            functionary = functionaryRepository.save(functionary);
            return new FunctionaryRequestDTO(functionary);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("recurso não encontrado");
        }
    }

    private void copyDtoToEntity(FunctionaryRequestDTO dto, Functionary entity) {
        entity.setOffice(dto.getOffice());
        entity.setSalary(dto.getSalary());
        Person person = new Person(
                dto.getPersonDTO().getName(),
                dto.getPersonDTO().getBirthDate());
        entity.setPerson(person);
    }

    @Transactional
    public FunctionaryRequestDTO update(Long id, FunctionaryRequestDTO dto) {
        try {
            Person person = personRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
            Functionary functionary = functionaryRepository.findById(id)
                    .orElseGet(() -> new Functionary());

            if (functionary.getId() == null)
                copyDtoToEntity(dto, functionary, person);
            else {
                functionary = functionaryRepository.getReferenceById(id);
                copyMinDtoToEntity(dto, functionary);
            }
            functionary = functionaryRepository.save(functionary);

            return new FunctionaryRequestDTO(functionary);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(FunctionaryRequestDTO dto, Functionary entity, Person person) {
        entity.setOffice(dto.getOffice());
        entity.setSalary(dto.getSalary());
        entity.setPerson(new Person());
        entity.setPerson(person);
    }

    private void copyMinDtoToEntity(FunctionaryRequestDTO dto, Functionary entity) {
        entity.setOffice(dto.getOffice());
        entity.setSalary(dto.getSalary());
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
    public FunctionaryResponseDTO findById(Long id) {
        Optional<Functionary> entity = functionaryRepository.findById(id);
        return new FunctionaryResponseDTO(entity.get());
    }

    // @Transactional(readOnly = true)
    // public Page<FunctionaryDTO> findAll(Pageable pageable) {
    // Page<Functionary> entity = functionaryRepository.findAll(pageable);
    // return entity.map(x -> new FunctionaryDTO(x));
    // }

    @Transactional(readOnly = true)
    public List<FunctionaryResponseDTO> findAllFunctionaryWithPerson() {
        List<Functionary> entity = functionaryRepository.findAllFunctionaryWithPerson();
        return entity.stream().map(x -> new FunctionaryResponseDTO(x)).toList();
    }
}
