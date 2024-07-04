package com.challenge.prothera.dto;

import com.challenge.prothera.entities.Functionary;

public class FunctionaryDTO {

    private Long id;
    private String office;
    private Double salary;

    private PersonDTO personDTO;

    public FunctionaryDTO() {

    }

    public FunctionaryDTO(String office, Double salary) {
        this.office = office;
        this.salary = salary;
    }

    public FunctionaryDTO(Functionary entity) {
        id = entity.getId();
        office = entity.getOffice();
        salary = entity.getSalary();
        personDTO = new PersonDTO(entity.getPerson());
    }

    public Long getId() {
        return id;
    }

    public String getOffice() {
        return office;
    }

    public Double getSalary() {
        return salary;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    @Override
    public String toString() {
        return "FunctionaryDTO [id=" + id + ", office=" + office + ", salary=" + salary
                + ", personDTO=" + personDTO + "]";
    }

}
