package pl.isa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import java.time.LocalDate;

@Entity
public class StudentModel {
    @Id
    private Long Id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private LocalDate dateBirth;
    @JsonProperty
    private String course;


    public Long getId() {
        return Id;
    }


    public void setId(Long id) {
        this.Id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
