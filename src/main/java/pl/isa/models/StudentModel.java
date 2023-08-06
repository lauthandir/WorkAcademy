package pl.isa.models;

import java.time.LocalDate;

public class StudentModel {
    private String name;
    private String surname;
    private DateInfo dateBirth;
    private String course;

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

    public DateInfo getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = new DateInfo(dateBirth.getYear(), dateBirth.getMonthValue(), dateBirth.getDayOfMonth());
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
