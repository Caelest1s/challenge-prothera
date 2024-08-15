package com.challenge.prothera.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDate birthDate;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY, optional = true, cascade = { CascadeType.ALL,
            CascadeType.REMOVE }, orphanRemoval = true)
    private Functionary functionary;

    public Person() {
    }

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Functionary getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Functionary functionary) {
        this.functionary = functionary;
    }

    @Override
    public String toString() {
        return "Person [id pessoa=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

}
