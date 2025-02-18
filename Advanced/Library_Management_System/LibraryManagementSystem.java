package assignments.Library_Management_System;

import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {
	
	public static void addBook(String title, String author, int availableCopies) {
	    String query = "INSERT INTO books (title, author, available_copies) VALUES (?, ?, ?)";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setString(1, title);
	        stmt.setString(2, author);
	        stmt.setInt(3, availableCopies);
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Book added successfully!");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error adding book: " + e.getMessage());
	    }
	}
	
	//Borrow Book Function
	public static void borrowBook(int bookId, int userId) {
	    String checkAvailabilityQuery = "SELECT available_copies FROM books WHERE book_id = ?";
	    String checkUserQuery = "SELECT user_id FROM users WHERE user_id = ?";
	    String borrowQuery = "INSERT INTO transactions (book_id, user_id, transaction_type) VALUES (?, ?, 'borrow')";
	    String updateCopiesQuery = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ? AND available_copies > 0";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStmt = connection.prepareStatement(checkAvailabilityQuery);
	         PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery);
	         PreparedStatement borrowStmt = connection.prepareStatement(borrowQuery);
	         PreparedStatement updateStmt = connection.prepareStatement(updateCopiesQuery)) {

	        // Check if the user exists
	        checkUserStmt.setInt(1, userId);
	        ResultSet userRs = checkUserStmt.executeQuery();
	        if (!userRs.next()) {
	            System.out.println("Error: User with ID " + userId + " does not exist.");
	            return; // Exit if user doesn't exist
	        }

	        // Check if the book is available
	        checkStmt.setInt(1, bookId);
	        ResultSet rs = checkStmt.executeQuery();
	        if (rs.next() && rs.getInt("available_copies") > 0) {
	            // Borrow the book
	            borrowStmt.setInt(1, bookId);
	            borrowStmt.setInt(2, userId);
	            borrowStmt.executeUpdate();

	            // Update the available copies in the books table
	            updateStmt.setInt(1, bookId);
	            updateStmt.executeUpdate();

	            System.out.println("Book borrowed successfully!");
	        } else {
	            System.out.println("Sorry, the book is not available.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error borrowing book: " + e.getMessage());
	    }
	}

	
	//Returning Book
	public static void returnBook(int bookId, int userId) {
	    String returnQuery = "INSERT INTO transactions (book_id, user_id, transaction_type) VALUES (?, ?, 'return')";
	    String updateCopiesQuery = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement returnStmt = connection.prepareStatement(returnQuery);
	         PreparedStatement updateStmt = connection.prepareStatement(updateCopiesQuery)) {

	        returnStmt.setInt(1, bookId);
	        returnStmt.setInt(2, userId);
	        returnStmt.executeUpdate();

	        updateStmt.setInt(1, bookId);
	        updateStmt.executeUpdate();

	        System.out.println("Book returned successfully!");
	    } catch (SQLException e) {
	        System.out.println("Error returning book: " + e.getMessage());
	    }
	}
	
	//View Books
	public static void viewBooks() {
	    String query = "SELECT * FROM books";

	    try (Connection connection = DatabaseConnection.getConnection();
	         Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            System.out.println("Book ID: " + rs.getInt("book_id") + ", Title: " + rs.getString("title")
	                    + ", Author: " + rs.getString("author") + ", Available Copies: " + rs.getInt("available_copies"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Error fetching books: " + e.getMessage());
	    }
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter available copies: ");
                    int availableCopies = scanner.nextInt();
                    addBook(title, author, availableCopies);
                    break;

                case 2:
                    System.out.print("Enter book ID to borrow: ");
                    int bookIdToBorrow = scanner.nextInt();
                    System.out.print("Enter user ID: ");
                    int userIdToBorrow = scanner.nextInt();
                    borrowBook(bookIdToBorrow, userIdToBorrow);
                    break;

                case 3:
                    System.out.print("Enter book ID to return: ");
                    int bookIdToReturn = scanner.nextInt();
                    System.out.print("Enter user ID: ");
                    int userIdToReturn = scanner.nextInt();
                    returnBook(bookIdToReturn, userIdToReturn);
                    break;

                case 4:
                    viewBooks();
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
