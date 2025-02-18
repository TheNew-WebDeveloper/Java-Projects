package Basic;

import java.util.Scanner;

public class Basic_Calculator {

    // Method to perform addition
    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    // Method to perform subtraction
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Method to perform multiplication
    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Method to perform division
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error! Division by zero.");
            return Double.NaN;  // Return NaN (Not a Number) to indicate error
        } else {
            return num1 / num2;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input for two numbers
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        // Show available operations
        System.out.println("Select operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");

        // Take user input for the operation
        System.out.print("Enter the operation (1/2/3/4): ");
        int operation = scanner.nextInt();

        double result = 0; // To store the result

        switch (operation) {
            case 1:
                result = add(num1, num2);
                System.out.println("The result of addition is: " + result);
                break;
            case 2:
                result = subtract(num1, num2);
                System.out.println("The result of subtraction is: " + result);
                break;
            case 3:
                result = multiply(num1, num2);
                System.out.println("The result of multiplication is: " + result);
                break;
            case 4:
                result = divide(num1, num2);
                if (!Double.isNaN(result)) {
                    System.out.println("The result of division is: " + result);
                }
                break;
            default:
                System.out.println("Invalid operation selected!");
        }

        scanner.close();
    }
}
