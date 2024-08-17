package com.challenge.prothera.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.prothera.dto.FunctionaryRequestDTO;
import com.challenge.prothera.dto.FunctionaryResponseDTO;
import com.challenge.prothera.service.FunctionaryService;

@RestController
@RequestMapping(value = "/functionarys")
public class FunctionaryController {

    @Autowired
    private FunctionaryService serviceFunctionary;

    @PostMapping
    public ResponseEntity<FunctionaryRequestDTO> insert(@RequestBody FunctionaryRequestDTO dto) {
        serviceFunctionary.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FunctionaryRequestDTO> update(@PathVariable Long id, @RequestBody FunctionaryRequestDTO dto) {
        dto = serviceFunctionary.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceFunctionary.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public FunctionaryResponseDTO findById(@PathVariable Long id) {
        FunctionaryResponseDTO result = serviceFunctionary.findById(id);
        return result;
    }

    @GetMapping
    public List<FunctionaryResponseDTO> findAllFunctionaryWithPerson() {
        List<FunctionaryResponseDTO> result = serviceFunctionary.findAllFunctionaryWithPerson();
        return result;
    }

    @PutMapping(value = "/salary")
    public ResponseEntity<List<FunctionaryResponseDTO>> updateSalaryFunctionaries(@RequestParam BigDecimal percentage) {
        List<FunctionaryResponseDTO> result = serviceFunctionary.updateSalaryFunctionaries(percentage);
        return ResponseEntity.ok(result);
    }
}
