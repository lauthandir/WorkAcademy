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
import java.util.stream.Collectors;


@Repository
public class StudentsData {

    private static final String JSON_FILE_PATH = "src/main/resources/students.json";
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentsData(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void saveStudentData(StudentModel student) {
        List<StudentModel> students = readStudentData();
        if (students == null) {
            students = new ArrayList<>();
        }
        Long nextId = getNextId(students);
        student.setId(nextId);

        students.add(student);

        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            objectMapper.writeValue(writer, students);
            System.out.println("Student data saved to JSON file: " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while saving student data to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Long getNextId(List<StudentModel> students) {
        Long maxId = students.stream()
                .mapToLong(StudentModel::getId)
                .max()
                .orElse(0L);
        return maxId + 1;
    }


    public void saveStudentData(List<StudentModel> students) {
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

    public void editStudentData(Long id, StudentModel updatedStudent) {
        List<StudentModel> students = readStudentData();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                updatedStudent.setId(id); // Przypisz to samo ID
                students.set(i, updatedStudent);
                saveStudentData(students);
                break;
            }
        }
    }

    public void deleteStudentData(Long id) {
        List<StudentModel> students = readStudentData();
        Iterator<StudentModel> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                saveStudentData(students);
                break;
            }
        }
    }

    public List<StudentModel> findStudentsByName(String name) {
        List<StudentModel> students = readStudentData();
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<StudentModel> findStudentsBySurname(String surname) {
        List<StudentModel> students = readStudentData();
        return students.stream()
                .filter(student -> student.getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

    // DODAWANIE KILKU STUDENTÓW JEDNOCZEŚNIE - DZIAŁA !! :D
    public void SaveSeveralAtOnce(List<StudentModel> students) {
        List<StudentModel> existingStudents = readStudentData();

        Long nextId = getNextId(existingStudents);

        for (StudentModel student : students) {
            student.setId(nextId);
            existingStudents.add(student);
            nextId++;
        }

        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            objectMapper.writeValue(writer, existingStudents);
            System.out.println("Student data saved to JSON file: " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while saving student data to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }



}