/**
 * FinallyBlockDemo.java
 * This program demonstrates the use of the finally block in exception handling.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FinallyBlockDemo {
    public static void main(String[] args) {
        System.out.println("--- Finally Block Examples ---");
        
        // Example 1: Basic finally block
        System.out.println("\nExample 1: Basic Finally Block");
        divideWithFinally(10, 2);  // No exception
        divideWithFinally(10, 0);  // With exception
        
        // Example 2: Finally with resource cleanup
        System.out.println("\nExample 2: Resource Cleanup with Finally");
        readFile("example.txt");  // File doesn't exist
        
        // Example 3: Finally always executes
        System.out.println("\nExample 3: Finally Always Executes");
        finallyAlwaysExecutes();
        
        // Example 4: Return in try and finally
        System.out.println("\nExample 4: Return in Try and Finally");
        int result = returnFromTryFinally();
        System.out.println("Result: " + result);
        
        // Example 5: Scanner with finally
        System.out.println("\nExample 5: Scanner with Finally");
        processScannerInput();
    }
    
    /**
     * Demonstrates a basic finally block that executes regardless of whether an exception occurs.
     */
    public static void divideWithFinally(int dividend, int divisor) {
        try {
            System.out.println("Trying to divide " + dividend + " by " + divisor);
            int result = dividend / divisor;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
            System.out.println("Exception message: " + e.getMessage());
        } finally {
            System.out.println("This finally block always executes");
        }
        System.out.println("After try-catch-finally");
    }
    
    /**
     * Demonstrates using finally for resource cleanup when reading a file.
     */
    public static void readFile(String filename) {
        FileInputStream file = null;
        
        try {
            System.out.println("Trying to open file: " + filename);
            file = new FileInputStream(filename);
            
            // Read from the file...
            byte[] data = new byte[100];
            int bytesRead = file.read(data);
            
            System.out.println("Read " + bytesRead + " bytes from the file");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
            System.out.println("Exception message: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: Problem reading from file");
            System.out.println("Exception message: " + e.getMessage());
        } finally {
            // Clean up resources in the finally block
            System.out.println("Executing finally block to clean up resources");
            
            if (file != null) {
                try {
                    file.close();
                    System.out.println("File closed successfully");
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            } else {
                System.out.println("File was never opened, nothing to close");
            }
        }
        
        System.out.println("After file operation");
    }
    
    /**
     * Demonstrates that finally always executes, even when there are return statements.
     */
    public static void finallyAlwaysExecutes() {
        try {
            System.out.println("In try block");
            
            // Return from the try block
            System.out.println("About to return from try block");
            return;  // This would normally exit the method
        } catch (Exception e) {
            System.out.println("In catch block");
        } finally {
            // This will still execute despite the return statement in the try block
            System.out.println("In finally block - this executes even after return");
        }
        
        // This won't execute because of the return in the try block
        System.out.println("After try-catch-finally - this won't execute");
    }
    
    /**
     * Demonstrates the interaction between return statements in try and finally blocks.
     */
    public static int returnFromTryFinally() {
        try {
            System.out.println("In try block");
            return 1;  // This return value will be overridden by the finally block
        } finally {
            System.out.println("In finally block");
            return 2;  // This return value will be used
        }
    }
    
    /**
     * Demonstrates using a Scanner with proper cleanup in a finally block.
     */
    public static void processScannerInput() {
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(System.in);
            System.out.print("Enter a number: ");
            
            // This might throw an InputMismatchException if the user enters non-numeric input
            int number = scanner.nextInt();
            System.out.println("You entered: " + number);
        } catch (Exception e) {
            System.out.println("Error processing input: " + e.getMessage());
        } finally {
            System.out.println("Cleaning up scanner in finally block");
            
            // In a real application, you would close the scanner here
            // We're not closing it to avoid closing System.in
            if (scanner != null) {
                // scanner.close();  // Commented out to avoid closing System.in
                System.out.println("Scanner would be closed here in a real application");
            }
        }
        
        System.out.println("After scanner operation");
    }
}
