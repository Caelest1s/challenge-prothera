package com.challenge.prothera.dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.challenge.prothera.entities.Functionary;

public class FunctionaryDTO {

    private Long id;
    private String office;
    private String salary;

    private PersonDTO personDTO;

    public FunctionaryDTO() {
    }

    public FunctionaryDTO(String office, BigDecimal salary) {
        this.office = office;
        this.salary = formaterSalary(salary);
    }

    public FunctionaryDTO(Functionary entity) {
        id = entity.getId();
        office = entity.getOffice();
        salary = formaterSalary(entity.getSalary());
        personDTO = new PersonDTO(entity.getPerson());
    }

    public Long getId() {
        return id;
    }

    public String getOffice() {
        return office;
    }

    public String getSalary() {
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

    public String formaterSalary(BigDecimal salary) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(salary);
    }

    public BigDecimal formaterSalary() {
        try {
            NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
            Number value = formatter.parse(this.salary);
            return BigDecimal.valueOf(value.doubleValue());

        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de salário inválido: " + this.salary);
        }
    }
}