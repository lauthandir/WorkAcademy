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
    public ResponseEntity<Void> saveStudents(@RequestBody StudentModel students) {
        studentsData.saveStudentData(students);
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

    @GetMapping("/allNames")
    public String getAllNames() {
        return studentsData.getAllNames().toString();
    }

    @GetMapping("/allSureNames")
    public String getAllSurnames() {
        return studentsData.getAllSurnames().toString();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editStudent(@PathVariable Long id, @RequestBody StudentModel updatedStudent) {
        studentsData.editStudentData(id, updatedStudent);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentsData.deleteStudentData(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/byName/{name}")
    public ResponseEntity<List<StudentModel>> findStudentsByName(@PathVariable String name) {
        List<StudentModel> students = studentsData.findStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/search/bySurname/{surname}")
    public ResponseEntity<List<StudentModel>> findStudentsBySurname(@PathVariable String surname) {
        List<StudentModel> students = studentsData.findStudentsBySurname(surname);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> saveStudentsBatch(@RequestBody List<StudentModel> students) {
        studentsData.SaveSeveralAtOnce(students);
        return ResponseEntity.status(201).build();
    }

}
