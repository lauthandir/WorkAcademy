package pl.isa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.isa.models.StudentModel;
import pl.isa.studentsdatabase.StudentsData;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentModel student = new StudentModel();
        System.out.println("Please fill in the required data below:");

        System.out.print("1.Name: ");
        String name = scanner.nextLine();

        System.out.print("2.Surname: ");
        String surname = scanner.nextLine();

        LocalDate dateBirth = null;
        while (true) {
            try {
                System.out.print("3.Date of Birth (yyyy-MM-dd): ");
                String dateBirthString = scanner.nextLine();
                dateBirth = LocalDate.parse(dateBirthString);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Use the format yyyy-MM-dd.");
            }
        }

        System.out.print("4.Course: ");
        String course = scanner.nextLine();

        student.setName(name);
        student.setSurname(surname);
        student.setCourse(course);
        student.setDateBirth(dateBirth);

        System.out.println();
        System.out.println("Student Information:");
        System.out.println("Name: " + student.getName());
        System.out.println("Surname: " + student.getSurname());
        System.out.println("Date of Birth: " + student.getDateBirth());
        System.out.println("Course: " + student.getCourse());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        StudentsData.saveStudentData(student, gson);
    }
}
