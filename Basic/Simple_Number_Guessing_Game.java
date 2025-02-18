package Basic;

import java.util.Random;
import java.util.Scanner;

public class Simple_Number_Guessing_Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Generate a random number between 1 and 100
        int targetNumber = random.nextInt(100) + 1;
        int attemptsLeft = 10;  // Number of attempts allowed
        boolean hasGuessedCorrectly = false;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have chosen a number between 1 and 100.");
        System.out.println("You have " + attemptsLeft + " attempts to guess it.");

        // Game loop
        while (attemptsLeft > 0 && !hasGuessedCorrectly) {
            System.out.print("\nEnter your guess: ");
            
            // Check if the input is a valid integer
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();  // Clear the invalid input
                continue;
            }
            
            int userGuess = scanner.nextInt();
            
            // Check if the guess is within the valid range
            if (userGuess < 1 || userGuess > 100) {
                System.out.println("Please guess a number between 1 and 100.");
                continue;
            }
            
            // Compare the guess with the target number
            if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                hasGuessedCorrectly = true;  // The user guessed correctly
                System.out.println("Congratulations! You've guessed the number correctly!");
                break;
            }
            
            // Decrease the number of attempts
            attemptsLeft--;
            System.out.println("You have " + attemptsLeft + " attempts left.");
        }
        
        // If the user runs out of attempts
        if (!hasGuessedCorrectly) {
            System.out.println("\nSorry, you've run out of attempts. The correct number was " + targetNumber + ".");
        }

        scanner.close();
    }
}
