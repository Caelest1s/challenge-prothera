package com.challenge.prothera.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prothera.dto.FunctionaryDTO;
import com.challenge.prothera.entities.Functionary;
import com.challenge.prothera.repositories.FunctionaryRepository;

@Service
public class FunctionaryService {

    @Autowired
    private FunctionaryRepository repository;

    public void delete(Long id) {

    }

    @Transactional(readOnly = true)
    public FunctionaryDTO findById(Long id) {
        Optional<Functionary> result = repository.findById(id);
        return copyFunctionaryToDto(result);
    }

    @Transactional(readOnly = true)
    public Page<FunctionaryDTO> findAll(Pageable pageable) {
        Page<Functionary> result = repository.findAll(pageable);
        return result.map(x -> new FunctionaryDTO(x));
    }

    @Transactional(readOnly = true)
    public List<FunctionaryDTO> findAllWithPerson() {
        List<Functionary> result = repository.findFunctionaryWithPerson();
        return result.stream().map(x -> new FunctionaryDTO(x)).toList();
    }

    private FunctionaryDTO copyFunctionaryToDto(Optional<Functionary> entity) {
        Functionary functionary = entity.get();
        FunctionaryDTO dto = new FunctionaryDTO(functionary);
        return dto;
    }
}