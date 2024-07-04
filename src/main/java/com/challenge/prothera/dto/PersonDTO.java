package com.challenge.prothera.dto;

import java.time.LocalDate;

import com.challenge.prothera.entities.Person;

public class PersonDTO {

    private Long id;
    private String name;
    private LocalDate birthDate;

    public PersonDTO() {

    }

    public PersonDTO(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public PersonDTO(Person entity) {
        id = entity.getId();
        name = entity.getName();
        birthDate = entity.getBirthDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "PersonDTO [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

}
