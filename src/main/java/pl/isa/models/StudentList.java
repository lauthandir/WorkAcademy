package pl.isa.models;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    public List<StudentModel> students;

    public StudentList() {
        students = new ArrayList<>();
    }
    public List<StudentModel> getStudents() {
        return students;
    }

    public void setStudents(List<StudentModel> students) {
        this.students = students;
    }
}
