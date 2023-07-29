package pl.isa;

import pl.isa.models.StudentModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentModel student = new StudentModel();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        LocalDate dateBirth = null;
        while (true) {
            try {
                System.out.print("Date of Birth (yyyy-MM-dd): ");
                String dateBirthString = scanner.nextLine();
                dateBirth = LocalDate.parse(dateBirthString);
                break; // Jeśli data jest w prawidłowym formacie, przerywamy pętlę.
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Use the format yyyy-MM-dd.");
            }
        }

        System.out.print("Course: ");
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
    }
}
