package assignments.Employee_Management_System;

import java.util.ArrayList;
import java.util.Scanner;

public class Management_System {
	private ArrayList<Employee> employeeList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Create a new employee
    public void addEmployee() {
        System.out.println("Enter Employee ID:");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Enter Employee Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Employee Salary:");
        double salary = scanner.nextDouble();

        Employee newEmployee = new Employee(name, id, salary);
        employeeList.add(newEmployee);
        System.out.println("Employee added successfully!");
    }

    // View all employees
    public void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employeeList) {
                employee.display();
            }
        }
    }

    // Update an employee's details
    public void updateEmployee() {
        System.out.println("Enter Employee ID to update:");
        int id = scanner.nextInt();
        boolean found = false;

        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                found = true;
                scanner.nextLine();  // Consume newline
                System.out.println("Enter new Name:");
                String name = scanner.nextLine();
                System.out.println("Enter new Salary:");
                double salary = scanner.nextDouble();

                employee.setName(name);
                employee.setSalary(salary);
                System.out.println("Employee details updated successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found!");
        }
    }

    // Delete an employee
    public void deleteEmployee() {
        System.out.println("Enter Employee ID to delete:");
        int id = scanner.nextInt();
        boolean found = false;

        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeList.remove(employee);
                found = true;
                System.out.println("Employee deleted successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found!");
        }
    }

}