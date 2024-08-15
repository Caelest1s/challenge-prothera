package com.challenge.prothera.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.challenge.prothera.entities.Person;

public class PersonResponseDTO {

    private Long id;
    private String name;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String birthDate;

    public PersonResponseDTO() {
    }

    public PersonResponseDTO(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = formatterDate(birthDate);
    }

    public PersonResponseDTO(Person entity) {
        id = entity.getId();
        name = entity.getName();
        birthDate = formatterDate(entity.getBirthDate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "PersonDTO [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

    private String formatterDate(LocalDate birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthDate.format(formatter);
    }
}
