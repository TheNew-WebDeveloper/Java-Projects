package Intermediate.Employee_Management_System;

import java.util.Scanner;

public class Employee_Management {
	public static void main(String[] args) {
        Management_System system = new Management_System();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.addEmployee();
                    break;
                case 2:
                    system.viewEmployees();
                    break;
                case 3:
                    system.updateEmployee();
                    break;
                case 4:
                    system.deleteEmployee();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
