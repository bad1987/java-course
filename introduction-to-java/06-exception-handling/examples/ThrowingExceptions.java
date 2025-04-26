/**
 * ThrowingExceptions.java
 * This program demonstrates throwing exceptions and declaring methods with throws.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ThrowingExceptions {
    public static void main(String[] args) {
        System.out.println("--- Throwing Exceptions Examples ---");
        
        // Example 1: Throwing an exception
        System.out.println("\nExample 1: Throwing an Exception");
        try {
            validateAge(15);  // This is valid
            validateAge(-5);  // This will throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        
        // Example 2: Declaring a method with throws
        System.out.println("\nExample 2: Method with throws Declaration");
        try {
            readFile("example.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
        
        // Example 3: Rethrowing exceptions
        System.out.println("\nExample 3: Rethrowing Exceptions");
        try {
            processFile("data.txt");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
        
        // Example 4: Throwing a checked exception
        System.out.println("\nExample 4: Throwing Checked Exceptions");
        try {
            validateFileName("valid.txt");  // This is valid
            validateFileName("");  // This will throw an exception
        } catch (IOException e) {
            System.out.println("Invalid filename: " + e.getMessage());
        }
        
        // Example 5: Exception chaining
        System.out.println("\nExample 5: Exception Chaining");
        try {
            processUserData("user123");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            
            // Print the cause of the exception
            if (e.getCause() != null) {
                System.out.println("Caused by: " + e.getCause().getMessage());
            }
        }
    }
    
    /**
     * Validates age and throws an exception if it's invalid.
     * 
     * @param age The age to validate
     * @throws IllegalArgumentException If age is negative
     */
    public static void validateAge(int age) {
        System.out.println("Validating age: " + age);
        
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative: " + age);
        }
        
        System.out.println("Age is valid: " + age);
    }
    
    /**
     * Reads a file and declares that it might throw exceptions.
     * 
     * @param filename The name of the file to read
     * @throws FileNotFoundException If the file doesn't exist
     * @throws IOException If there's an error reading the file
     */
    public static void readFile(String filename) throws FileNotFoundException, IOException {
        System.out.println("Attempting to read file: " + filename);
        
        FileInputStream file = new FileInputStream(filename);
        
        try {
            // Read from the file...
            byte[] data = new byte[100];
            int bytesRead = file.read(data);
            
            System.out.println("Read " + bytesRead + " bytes from the file");
        } finally {
            file.close();
        }
    }
    
    /**
     * Processes a file and rethrows exceptions.
     * 
     * @param filename The name of the file to process
     * @throws IOException If there's an error processing the file
     */
    public static void processFile(String filename) throws IOException {
        System.out.println("Processing file: " + filename);
        
        try {
            // Try to read the file
            FileInputStream file = new FileInputStream(filename);
            
            // Process the file...
            
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException, rethrowing as IOException");
            
            // Rethrow as a different exception
            throw new IOException("Could not find file: " + filename, e);
        }
    }
    
    /**
     * Validates a filename and throws a checked exception if invalid.
     * 
     * @param filename The filename to validate
     * @throws IOException If the filename is empty or null
     */
    public static void validateFileName(String filename) throws IOException {
        System.out.println("Validating filename: " + filename);
        
        if (filename == null || filename.isEmpty()) {
            throw new IOException("Filename cannot be empty");
        }
        
        System.out.println("Filename is valid: " + filename);
    }
    
    /**
     * Demonstrates exception chaining.
     * 
     * @param userId The user ID to process
     * @throws Exception If there's an error processing the user data
     */
    public static void processUserData(String userId) throws Exception {
        System.out.println("Processing user data for: " + userId);
        
        try {
            // Simulate a database operation
            if (userId.equals("user123")) {
                // Simulate a database error
                throw new RuntimeException("Database connection failed");
            }
            
            // Process user data...
            System.out.println("User data processed successfully");
        } catch (RuntimeException e) {
            // Chain the exception with a more specific one
            throw new Exception("Failed to process user data for " + userId, e);
        }
    }
}
