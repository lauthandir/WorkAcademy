package pl.isa.studentsdatabase;
import com.google.gson.Gson;
import pl.isa.models.StudentModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;


public class StudentsData {
    private static final String JSON_FILE_PATH = "students.json";


    public static void saveStudentData(StudentModel student) {
        try {
            Gson gson = new Gson();


            try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                gson.toJson(student, writer);
            }

            System.out.println("Student data saved to JSON file: " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while saving student data to JSON file: " + e.getMessage());
        }
    }



}
