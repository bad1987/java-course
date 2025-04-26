/**
 * Exercise 2: File Reader with Exception Handling
 * 
 * Instructions:
 * 1. Create a FileProcessor class with the following methods:
 *    - readFile(String filename): Reads and returns the contents of a file as a String
 *    - countWords(String filename): Counts and returns the number of words in a file
 *    - countLines(String filename): Counts and returns the number of lines in a file
 *    - findAndReplace(String filename, String oldText, String newText): Replaces all occurrences
 *      of oldText with newText in the file and saves the changes
 * 
 * 2. Implement proper exception handling for:
 *    - FileNotFoundException: When the file doesn't exist
 *    - IOException: When there's an error reading from or writing to the file
 *    - SecurityException: When there's no permission to access the file
 *    - Any other exceptions that might occur
 * 
 * 3. Use try-with-resources to ensure that all file resources are properly closed
 * 
 * 4. Create a FileProcessorApp class with a main method that:
 *    - Creates a FileProcessor object
 *    - Provides a menu for the user to select an operation
 *    - Prompts the user for the necessary inputs
 *    - Performs the selected operation and displays the result
 *    - Handles all potential exceptions with appropriate error messages
 *    - Allows the user to perform multiple operations until they choose to exit
 * 
 * 5. Create a test file with some sample text to use with your program
 * 
 * 6. Implement a custom exception called FileProcessingException that wraps any
 *    underlying exceptions and provides more context about the operation that failed.
 */
import java.io.*;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        // TODO: Implement the FileProcessorApp
    }
}

// TODO: Implement the FileProcessor class

// TODO: Implement the FileProcessingException class
