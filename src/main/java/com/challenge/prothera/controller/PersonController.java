package com.challenge.prothera.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.prothera.dto.PersonDTO;
import com.challenge.prothera.service.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO dto) {
        PersonDTO t1 = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(t1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findById(@PathVariable Long id) {
        PersonDTO result = service.findById(id);
        return result;
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        List<PersonDTO> result = service.findAll();
        return result;
    }
}
