package pl.isa.studentsdatabase;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import pl.isa.models.StudentModel;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class StudentsData {
    private static final String JSON_FILE_PATH = "src/main/resources/students.json";

    public static void saveStudentData(StudentModel student, Gson gson) {
        List<StudentModel> students = readStudentData(gson);

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
private static List<StudentModel> readStudentData(Gson gson) {
        List<StudentModel> students = new ArrayList<>();

        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                    Type studentListType = new TypeToken<List<StudentModel>>() {}.getType();
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
}
