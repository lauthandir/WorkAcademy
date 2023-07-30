package pl.isa;
import pl.isa.models.StudentModel;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentModel student = new StudentModel();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        String dateBirth = scanner.nextLine();

        System.out.print("Course: ");
        String course = scanner.nextLine();

        student.setName(name);
        student.setSurname(surname);
        student.setDateBirth(LocalDate.parse(dateBirth));
        student.setCourse(course);

        System.out.println();
        System.out.println("Student Information:");
        System.out.println("Name: " + student.getName());
        System.out.println("Surname: " + student.getSurname());
        System.out.println("Date of Birth: " + student.getDateBirth());
        System.out.println("Course: " + student.getCourse());
    }
}
