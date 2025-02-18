package Basic;

import java.util.Scanner;

public class Factorial_Calculator {

    // Recursive method to calculate factorial
    public static int factorial(int n) {
        // Base case: factorial of 0 or 1 is 1
        if (n == 0 || n == 1) {
            return 1;
        } 
        // Handle negative numbers
        else if (n < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
            return -1;  // Return -1 to indicate an error for negative input
        } 
        else {
            return n * factorial(n - 1);  // Recursive call
        }
    }

    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a number
        System.out.print("Enter a number to calculate its factorial: ");
        int number = scanner.nextInt();

        // Call the factorial method and display the result
        int result = factorial(number);
        if (result != -1) {
            System.out.println("Factorial of " + number + " is: " + result);
        }

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
