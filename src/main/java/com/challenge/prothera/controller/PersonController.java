package com.challenge.prothera.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.prothera.dto.PersonDTO;
import com.challenge.prothera.service.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO dto) {
        dto = personService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO dto) {
        dto = personService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findById(@PathVariable Long id) {
        PersonDTO result = personService.findById(id);

        return result;
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        List<PersonDTO> result = personService.findAll();

        return result;
    }

    // @PostMapping(value = "/all")
    // public void insertAllPerson() {
    // for (PersonDTO dto : populateListPersons()) {
    // insert(dto);
    // }
    // }

    // public List<PersonDTO> populateListPersons() {
    // List<PersonDTO> listPerson = new ArrayList<PersonDTO>();
    // listPerson.add(new PersonDTO("Maria", LocalDate.parse("2000-10-18")));
    // listPerson.add(new PersonDTO("Joao", LocalDate.parse("1990-05-12")));
    // listPerson.add(new PersonDTO("Caio", LocalDate.parse("1961-05-02")));
    // listPerson.add(new PersonDTO("Miguel", LocalDate.parse("1988-10-14")));
    // listPerson.add(new PersonDTO("Alice", LocalDate.parse("1995-01-05")));
    // listPerson.add(new PersonDTO("Heitor", LocalDate.parse("1999-11-19")));
    // listPerson.add(new PersonDTO("Arthur", LocalDate.parse("1993-03-31")));
    // listPerson.add(new PersonDTO("Laura", LocalDate.parse("1994-07-08")));
    // listPerson.add(new PersonDTO("Heloisa", LocalDate.parse("2003-05-24")));
    // listPerson.add(new PersonDTO("Helena", LocalDate.parse("1996-09-02")));
    // return listPerson;
    // }
}
