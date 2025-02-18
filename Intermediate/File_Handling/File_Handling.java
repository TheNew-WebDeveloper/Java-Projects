package Intermediate.File_Handling;

import java.io.*;
import java.util.*;

public class File_Handling {

    // Method to read a file, count lines and words, and write the output to another file
    public static void processFile(String inputFilePath, String outputFilePath) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        int lineCount = 0;
        int wordCount = 0;

        try {
            // Initialize BufferedReader to read from the input file
            reader = new BufferedReader(new FileReader(inputFilePath));

            // Initialize BufferedWriter to write to the output file
            writer = new BufferedWriter(new FileWriter(outputFilePath));

            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++; // Increment line count for each line
                String[] words = line.split("\\s+"); // Split line into words
                wordCount += words.length; // Increment word count by the number of words in the line
            }

            // Write the results to the output file
            writer.write("Total number of lines: " + lineCount + "\n");
            writer.write("Total number of words: " + wordCount + "\n");

            System.out.println("Processing complete. Results written to " + outputFilePath);

        } catch (FileNotFoundException e) {
            System.out.println("Error: The input file was not found.");
        } catch (IOException e) {
            System.out.println("Error: An I/O error occurred.");
        } finally {
            // Close the file resources
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error: Unable to close file resources.");
            }
        }
    }

    public static void main(String[] args) {
        // Specify the input and output file paths
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input file path: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Enter the output file path: ");
        String outputFilePath = scanner.nextLine();

        // Process the file and write the results
        processFile(inputFilePath, outputFilePath);

        scanner.close();
    }
}
