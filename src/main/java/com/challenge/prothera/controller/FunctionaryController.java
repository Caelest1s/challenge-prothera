package com.challenge.prothera.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.prothera.dto.FunctionaryDTO;
import com.challenge.prothera.service.FunctionaryService;

@RestController
@RequestMapping(value = "/functionarys")
public class FunctionaryController {

    @Autowired
    private FunctionaryService service;

    @PostMapping
    public ResponseEntity<FunctionaryDTO> insert(@RequestBody FunctionaryDTO dto) {
        service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "{id}")
    public FunctionaryDTO findById(@PathVariable Long id) {
        FunctionaryDTO result = service.findById(id);
        return result;
    }

    @GetMapping
    public List<FunctionaryDTO> findAllWithPerson() {
        List<FunctionaryDTO> result = service.findAllWithPerson();
        return result;
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
