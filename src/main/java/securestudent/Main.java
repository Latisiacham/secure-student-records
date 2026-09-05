package securestudent;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice = 0;

        while (choice != 4) {

            System.out.println("=== Secure Student Records ===");
            System.out.println("1. Add student");
            System.out.println("2. View students");
            System.out.println("3. Search student");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            
            if (choice == 1) {

                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                System.out.print("Enter student email: ");
                String email = scanner.nextLine();

                Student student = new Student(id, name, email);
                manager.addStudent(student);

                System.out.println("Student added successfully.");

            } else if (choice == 2) {
                if (manager.getStudents().isEmpty()) {
                    System.out.println("No students found.");
                } else {
                    System.out.println("\n=== Students ===");

                    for (Student student : manager.getStudents()) {
                        System.out.println(
                            student.getId() + " - "
                            + student.getName() + " - "
                            + student.getEmail()
                        );
                    }
                }

            } else if (choice == 3) {
                System.out.println("Search student selected.");
            } else if (choice == 4) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();


        
    }

}