package com.challenge.prothera.dto;

import java.math.BigDecimal;

import com.challenge.prothera.entities.Functionary;

public class FunctionaryRequestDTO {

    private Long id;
    private String office;
    private BigDecimal salary;
    private PersonRequestDTO personDTO;

    public FunctionaryRequestDTO() {
    }

    public FunctionaryRequestDTO(String office, BigDecimal salary) {
        this.office = office;
        this.salary = salary;
    }

    public FunctionaryRequestDTO(Functionary entity) {
        id = entity.getId();
        office = entity.getOffice();
        salary = entity.getSalary();
        personDTO = new PersonRequestDTO(entity.getPerson());
    }

    public Long getId() {
        return id;
    }

    public String getOffice() {
        return office;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public PersonRequestDTO getPersonDTO() {
        return personDTO;
    }

    @Override
    public String toString() {
        return "FunctionaryDTO [id=" + id + ", office=" + office + ", salary=" + salary
                + ", personDTO=" + personDTO + "]";
    }
}