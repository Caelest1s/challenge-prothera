package com.challenge.prothera.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_functionary")
public class Functionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String office;
    @Column
    private Double salary;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Functionary() {

    }

    public Functionary(Long id, String office, Double salary, Person person) {
        this.id = id;
        this.office = office;
        this.salary = salary;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Functionary [id Functionary = " + id + ", office=" + office + ", salary=" + salary
                + ", person=" + person + "]";
    }

}
