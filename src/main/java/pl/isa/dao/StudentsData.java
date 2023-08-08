package pl.isa.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;
import pl.isa.util.LocalDateTypeAdapter;
import pl.isa.models.StudentModel;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentsData {
    static final String JSON_FILE_PATH = "src/main/resources/students.json";

    public static void saveStudentData(StudentModel student) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        List<StudentModel> students = readStudentData(gson);
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);

        try {
            try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                gson.toJson(students, writer);
            }
            System.out.println("Student data saved to JSON file: " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while saving student data to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<StudentModel> readStudentData(Gson gson) {
        List<StudentModel> students = new ArrayList<>();

        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    Type studentListType = new TypeToken<List<StudentModel>>() {
                    }.getType();
                    students = gson.fromJson(reader, studentListType);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading student data from JSON file: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            System.out.println("JSON Syntax error: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }

    public static double getAverageAge() {
        List<StudentModel> students = readStudentData(new Gson());
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

    public static int getTotalStudents() {
        List<StudentModel> students = readStudentData(new Gson());
        return students.size();
    }

    public static int getStudentsInCourse(String course) {
        List<StudentModel> students = readStudentData(new Gson());
        int count = 0;
        for (StudentModel student : students) {
            if (student.getCourse().equalsIgnoreCase(course)) {
                count++;
            }
        }
        return count;
    }


}
