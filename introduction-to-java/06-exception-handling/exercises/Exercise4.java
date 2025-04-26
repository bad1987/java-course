/**
 * Exercise 4: Debugging and Exception Analysis
 * 
 * Instructions:
 * 1. The code below contains several bugs and issues related to exception handling.
 *    Your task is to identify and fix these issues.
 * 
 * 2. Common issues to look for:
 *    - Missing exception handling
 *    - Improper exception handling (e.g., catching Exception instead of specific exceptions)
 *    - Resource leaks (not closing files, streams, etc.)
 *    - Swallowed exceptions (catching exceptions but not doing anything with them)
 *    - Inappropriate exception propagation
 *    - Logic errors in try-catch-finally blocks
 * 
 * 3. For each issue you find:
 *    - Add a comment explaining the issue
 *    - Fix the code
 *    - Add a comment explaining your fix
 * 
 * 4. After fixing all issues, add proper exception handling where needed and
 *    ensure that the program runs correctly.
 * 
 * 5. Write a brief report at the end of the file summarizing:
 *    - The issues you found
 *    - How you fixed them
 *    - Best practices for exception handling that were violated
 */
import java.io.*;
import java.util.*;

public class Exercise4 {
    public static void main(String[] args) {
        // TODO: Debug and fix the issues in this program
        
        // Issue 1: Missing exception handling for user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        System.out.println("You entered: " + number);
        
        // Issue 2: Resource leak (scanner not closed)
        
        // Issue 3: Inappropriate exception handling
        try {
            readFile("data.txt");
        } catch (Exception e) {
            System.out.println("Error");
        }
        
        // Issue 4: Swallowed exception
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            // Do nothing
        }
        
        // Issue 5: Logic error in try-catch-finally
        FileInputStream file = null;
        try {
            file = new FileInputStream("test.txt");
            // Read from file
            return; // This causes the finally block to execute but the method to exit
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            file.close(); // This will throw NullPointerException if file is null
        }
        
        // Issue 6: Inappropriate exception propagation
        processData();
        
        // Issue 7: Not using try-with-resources
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line = reader.readLine();
        System.out.println(line);
        reader.close();
    }
    
    public static void readFile(String filename) throws IOException {
        // Read file implementation
    }
    
    public static int divide(int a, int b) {
        return a / b;
    }
    
    public static void processData() {
        try {
            // Some processing that might throw exceptions
            throw new RuntimeException("Processing error");
        } catch (RuntimeException e) {
            throw e; // Just rethrows without adding context
        }
    }
}

// TODO: Add your report here
