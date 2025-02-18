package Intermediate.Simple_Banking_Application;

import java.util.Scanner;

public class Banking_App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000);  // Initial balance set to $1000 for demonstration

        int choice;
        do {
            // Display menu
            System.out.println("\nSimple Banking Application");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Deposit operation
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    // Withdraw operation
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    // Check balance
                    account.checkBalance();
                    break;

                case 4:
                    // Exit
                    System.out.println("Thank you for using the banking system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}
