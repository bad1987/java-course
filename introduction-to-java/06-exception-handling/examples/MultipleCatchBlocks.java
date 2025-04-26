/**
 * MultipleCatchBlocks.java
 * This program demonstrates using multiple catch blocks to handle different types of exceptions.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MultipleCatchBlocks {
    public static void main(String[] args) {
        System.out.println("--- Multiple Catch Blocks ---");
        
        // Example 1: Basic multiple catch blocks
        System.out.println("\nExample 1: Basic Multiple Catch Blocks");
        divideAndAccessArray();
        
        // Example 2: Catching specific exceptions before general ones
        System.out.println("\nExample 2: Order of Catch Blocks");
        processFile("example.txt");
        
        // Example 3: Multi-catch (Java 7+)
        System.out.println("\nExample 3: Multi-catch Syntax");
        multiCatchExample();
        
        // Example 4: Nested try-catch
        System.out.println("\nExample 4: Nested Try-Catch");
        nestedTryCatch();
        
        // Example 5: Real-world example with multiple exceptions
        System.out.println("\nExample 5: Real-world Example");
        processUserData("123", "25");
        processUserData("abc", "25");
        processUserData("123", "xyz");
    }
    
    /**
     * Demonstrates handling multiple types of exceptions with separate catch blocks.
     */
    public static void divideAndAccessArray() {
        int[] numbers = {1, 2, 3, 4, 5};
        
        try {
            // This might cause ArrayIndexOutOfBoundsException
            int value = numbers[10];
            
            // This might cause ArithmeticException
            int result = 10 / 0;
            
            System.out.println("This line won't execute if an exception is thrown");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds");
            System.out.println("Exception message: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error: Arithmetic exception");
            System.out.println("Exception message: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Some other exception occurred");
            System.out.println("Exception message: " + e.getMessage());
        }
        
        System.out.println("Program continues after exception handling");
    }
    
    /**
     * Demonstrates the importance of ordering catch blocks from most specific to most general.
     */
    public static void processFile(String filename) {
        FileInputStream file = null;
        
        try {
            file = new FileInputStream(filename);
            // Process file...
            System.out.println("File processed successfully");
        } catch (FileNotFoundException e) {
            // More specific exception first
            System.out.println("Error: File not found - " + filename);
            System.out.println("Exception message: " + e.getMessage());
        } catch (IOException e) {
            // More general exception second
            System.out.println("Error: I/O problem with file - " + filename);
            System.out.println("Exception message: " + e.getMessage());
        } catch (Exception e) {
            // Most general exception last
            System.out.println("Error: Something else went wrong");
            System.out.println("Exception message: " + e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.out.println("Error closing file");
                }
            }
        }
    }
    
    /**
     * Demonstrates the multi-catch syntax introduced in Java 7.
     */
    public static void multiCatchExample() {
        try {
            // Code that might throw different exceptions
            int result = 10 / 0;  // ArithmeticException
            
            // This won't execute if the above throws an exception
            int[] arr = new int[5];
            arr[10] = 20;  // ArrayIndexOutOfBoundsException
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            // Handle both exception types the same way
            System.out.println("Error: Either division by zero or array index out of bounds");
            System.out.println("Exception type: " + e.getClass().getSimpleName());
            System.out.println("Exception message: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates nested try-catch blocks.
     */
    public static void nestedTryCatch() {
        try {
            // Outer try block
            System.out.println("Outer try block");
            
            try {
                // Inner try block
                System.out.println("Inner try block");
                int result = 10 / 0;  // This will throw ArithmeticException
                System.out.println("This won't execute");
            } catch (ArithmeticException e) {
                // Inner catch block
                System.out.println("Inner catch: Arithmetic exception");
                System.out.println("Exception message: " + e.getMessage());
            }
            
            // This will execute because the inner exception was caught
            System.out.println("After inner try-catch");
            
            // This will throw ArrayIndexOutOfBoundsException
            int[] arr = new int[5];
            arr[10] = 20;
            
            System.out.println("This won't execute");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Outer catch block
            System.out.println("Outer catch: Array index out of bounds");
            System.out.println("Exception message: " + e.getMessage());
        }
        
        System.out.println("After outer try-catch");
    }
    
    /**
     * A real-world example processing user data with multiple potential exceptions.
     */
    public static void processUserData(String idStr, String ageStr) {
        try {
            System.out.println("Processing user data: ID=" + idStr + ", Age=" + ageStr);
            
            // Parse the ID (might throw NumberFormatException)
            int id = Integer.parseInt(idStr);
            
            // Parse the age (might throw NumberFormatException)
            int age = Integer.parseInt(ageStr);
            
            // Validate the data (might throw IllegalArgumentException)
            if (id <= 0) {
                throw new IllegalArgumentException("ID must be positive");
            }
            
            if (age < 0 || age > 120) {
                throw new IllegalArgumentException("Age must be between 0 and 120");
            }
            
            // Process the valid data
            System.out.println("User data processed successfully: ID=" + id + ", Age=" + age);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format");
            System.out.println("Exception message: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid argument");
            System.out.println("Exception message: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Unexpected exception");
            System.out.println("Exception type: " + e.getClass().getSimpleName());
            System.out.println("Exception message: " + e.getMessage());
        }
        
        System.out.println("Finished processing user data\n");
    }
}
