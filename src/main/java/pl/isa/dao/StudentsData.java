package pl.isa.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.isa.models.StudentModel;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class StudentsData {

    private static final String JSON_FILE_PATH = "src/main/resources/students.json";
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentsData(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

//    public void saveStudentData(StudentModel student) {
//        List<StudentModel> students = readStudentData();
//        if (students == null) {
//            students = new ArrayList<>();
//        }
//        students.add(student);
//
//        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
//            objectMapper.writeValue(writer, students);
//            System.out.println("Student data saved to JSON file: " + JSON_FILE_PATH);
//        } catch (IOException e) {
//            System.out.println("An error occurred while saving student data to JSON file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    public void saveStudentData(List<StudentModel> students) {
        List<StudentModel> studentsList = readStudentData();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.addAll(studentsList);

        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            objectMapper.writeValue(writer, students);
            System.out.println("Student data saved to JSON file: " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while saving student data to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<StudentModel> readStudentData() {
        List<StudentModel> students = new ArrayList<>();

        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, StudentModel.class);
                students = objectMapper.readValue(file, collectionType);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading student data from JSON file: " + e.getMessage());
            e.printStackTrace();
        }
        return students;
    }

    public double getAverageAge() {
        List<StudentModel> students = readStudentData();
        if (students.isEmpty()) {
            return 0;
        }

        double totalAge = 0;
        LocalDate currentDate = LocalDate.now();
        for (StudentModel student : students) {
            LocalDate dateOfBirth = student.getDateBirth();
            int age = currentDate.getYear() - dateOfBirth.getYear();
            if (currentDate.getDayOfYear() < dateOfBirth.getDayOfYear()) {
                age--;
            }
            totalAge += age;
        }

        return totalAge / students.size();
    }

    public int getTotalStudents() {
        List<StudentModel> students = readStudentData();
        return students.size();
    }

    public int getStudentsInCourse(String course) {
        List<StudentModel> students = readStudentData();
        int count = 0;
        for (StudentModel student : students) {
            if (student.getCourse().equalsIgnoreCase(course)) {
                count++;
            }
        }
        return count;
    }
    public List<String> getAllNames() {
        List<StudentModel> students = readStudentData();
        List<String> names = new ArrayList<>();
        for (StudentModel student : students) {
            names.add(student.getName());
        }
        return names;
    }

    public List<String> getAllSurnames() {
        List<StudentModel> students = readStudentData();
        List<String> surnames = new ArrayList<>();
        for (StudentModel student : students) {
            surnames.add(student.getSurname());
        }
        return surnames;
    }
        public boolean updateStudentData(Long studentId, StudentModel updatedStudent) {
        List<StudentModel> students = readStudentData();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                students.set(i, updatedStudent);
                saveStudentData(students);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudentData(Long studentId) {
        List<StudentModel> students = readStudentData();
        for (Iterator<StudentModel> iterator = students.iterator(); iterator.hasNext();) {
            StudentModel student = iterator.next();
            if (student.getId().equals(studentId)) {
                iterator.remove();
                saveStudentData(students);
                return true;
            }
        }
        return false;
    }

}