package com.challenge.prothera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.prothera.dto.PersonDTO;
import com.challenge.prothera.service.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "{id}")
    public PersonDTO findById(@PathVariable Long id) {
        PersonDTO result = personService.findById(id);
        return result;
    }
}
