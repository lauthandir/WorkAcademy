package pl.isa.controllers;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import pl.isa.dao.StudentsData;
import pl.isa.models.StudentModel;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentsController {


    @GetMapping
    public List<StudentModel> getStudents() {
        return (List<StudentModel>) StudentsData.readStudentData(new Gson());
    }

    @PostMapping
    public ResponseEntity<Void> saveStudent(@RequestBody StudentModel student) {
        StudentsData.saveStudentData(student);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/averageAge")
    public double getAverageAge() {
        return StudentsData.getAverageAge();
    }

    @GetMapping("/totalStudents")
    public int getTotalStudents() {
        return StudentsData.getTotalStudents();
    }

    @GetMapping("/studentsInCourse/{course}")
    public int getStudentsInCourse(@PathVariable String course) {
        return StudentsData.getStudentsInCourse(course);
    }
}
