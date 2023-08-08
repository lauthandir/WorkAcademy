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
    public ResponseEntity<Void> saveStudents(@RequestBody List<StudentModel> students) {
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
    public String getAllNames(){
        return studentsData.getAllNames().toString();
    }
    @GetMapping("/allSureNames")
    public String getAllSurnames(){
        return studentsData.getAllSurnames().toString();
    }

//    @PostMapping("/{studentId}")
//    public ResponseEntity<Void> updateStudent(@PathVariable Long studentId, @RequestBody StudentModel updatedStudent) {
//        boolean success = studentsData.updateStudentData(studentId, updatedStudent);
//        if (success) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{studentId}")
//    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
//        boolean success = studentsData.deleteStudentData(studentId);
//        if (success) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
