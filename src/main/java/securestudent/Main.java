package securestudent;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                System.out.println("Add student selected.");
            } else if (choice == 2) {
                System.out.println("View students selected.");
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