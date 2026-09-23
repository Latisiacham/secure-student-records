package securestudent;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        StudentFileManager fileManager = new StudentFileManager();
        fileManager.createFileIfMissing();

        for (Student student : fileManager.loadStudents()) {
            manager.addStudent(student);
        }

        UserManager userManager = new UserManager();

        UserFileManager userFileManager = new UserFileManager();
        userFileManager.createFileIfMissing();

        for (User user : userFileManager.loadUsers()) {
            userManager.addUser(user);
        }

        int choice = 0;

        int authChoice = 0;
        boolean loggedIn = false;
        User loggedInUser = null;

        while (authChoice != 3) {

            System.out.println("\n=== Secure Student Records ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            authChoice = scanner.nextInt();
            scanner.nextLine();

            if (authChoice == 1) {

                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                if (!userManager.isValidUsername(username)) {
                    System.out.println("Invalid username. Username must be at least 3 characters.");
                    continue;
                }

                if (userManager.usernameExists(username)) {
                    System.out.println("Username already exists.");
                    continue;
                }

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (!userManager.isValidPassword(password)) {
                    System.out.println(
                        "Invalid password. Use at least 8 characters with uppercase, lowercase, and a number."
                    );
                    continue;
                }


                User user = userManager.registerUser(username, password);

                userFileManager.saveUser(user);


                System.out.println("Registration successful.");

            } else if (authChoice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (userManager.login(username, password)) {
                    loggedInUser = userManager.findUser(username);
                    loggedIn = true;

                    System.out.println("Login successful.");
                    System.out.println("Role: " + loggedInUser.getRole());

                    break;

                } else {
                    System.out.println("Invalid username or password.");
                }



            } else if (authChoice == 3) {

                System.out.println("Logged out successfully.");

            } else {

                System.out.println("Invalid option.");
            }
        }

        if (!loggedIn) {
            scanner.close();
            return;
        }

        while (choice != 4) {

            System.out.println("=== Secure Student Records ===");
            System.out.println("1. Add student");
            System.out.println("2. View students");
            System.out.println("3. Search student");
            System.out.println("4. Logout");

            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            
            if (choice == 1) {
                if (!loggedInUser.getRole().equals("ADMIN")) {
                    System.out.println("Access denied. Admin permission required.");
                    continue;
                }

                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                if (manager.studentIdExists(id)) {
                    System.out.println("Student ID already exists.");
                    continue;
                }


                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                if (!manager.isValidName(name)) {
                    System.out.println("Invalid student name.");
                    continue;
                }

                System.out.print("Enter student email: ");
                String email = scanner.nextLine();
                
                if (!manager.isValidEmail(email)) {
                    System.out.println("Invalid email address.");
                    continue;
                }

                Student student = new Student(id, name, email);

                manager.addStudent(student);
                fileManager.saveStudent(student);

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

                scanner.nextLine();

                System.out.print("Enter student ID, name, or email: ");
                String search = scanner.nextLine();

                boolean found = false;

                for (Student student : manager.getStudents()) {

                    if (String.valueOf(student.getId()).equalsIgnoreCase(search)
                            || student.getName().equalsIgnoreCase(search)
                            || student.getEmail().equalsIgnoreCase(search)) {

                        System.out.println("\nStudent found:");
                        System.out.println("ID: " + student.getId());
                        System.out.println("Name: " + student.getName());
                        System.out.println("Email: " + student.getEmail());

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Student not found.");
                }

            } else if (choice == 4) {
                System.out.println("Logged out successfully.");
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();


        
    }

}