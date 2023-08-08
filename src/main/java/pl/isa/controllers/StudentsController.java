package pl.isa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import pl.isa.dao.StudentsData;
import pl.isa.models.StudentModel;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsData studentsData;

    @Autowired
    public StudentsController(StudentsData studentsData) {
        this.studentsData = studentsData;
    }

    @GetMapping
    public List<StudentModel> getStudents() {
        return studentsData.readStudentData();
    }

    @PostMapping
    public ResponseEntity<Void> saveStudent(@RequestBody StudentModel student) {
        studentsData.saveStudentData(student);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/averageAge")
    public double getAverageAge() {
        return studentsData.getAverageAge();
    }

    @GetMapping("/totalStudents")
    public int getTotalStudents() {
        return studentsData.getTotalStudents();
    }

    @GetMapping("/studentsInCourse/{course}")
    public int getStudentsInCourse(@PathVariable String course) {
        return studentsData.getStudentsInCourse(course);
    }
}
