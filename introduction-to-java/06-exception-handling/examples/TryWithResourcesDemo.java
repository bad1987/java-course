/**
 * TryWithResourcesDemo.java
 * This program demonstrates the try-with-resources statement introduced in Java 7.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TryWithResourcesDemo {
    public static void main(String[] args) {
        System.out.println("--- Try-With-Resources Examples ---");
        
        // Example 1: Basic try-with-resources
        System.out.println("\nExample 1: Basic Try-With-Resources");
        readFileWithTryWithResources("example.txt");
        
        // Example 2: Multiple resources
        System.out.println("\nExample 2: Multiple Resources");
        copyFile("source.txt", "destination.txt");
        
        // Example 3: Try-with-resources with catch and finally
        System.out.println("\nExample 3: Try-With-Resources with Catch and Finally");
        readFileWithCatchAndFinally("example.txt");
        
        // Example 4: Custom AutoCloseable resource
        System.out.println("\nExample 4: Custom AutoCloseable Resource");
        useCustomResource();
        
        // Example 5: Scanner with try-with-resources
        System.out.println("\nExample 5: Scanner with Try-With-Resources");
        readUserInput();
    }
    
    /**
     * Demonstrates a basic try-with-resources statement to read a file.
     */
    public static void readFileWithTryWithResources(String filename) {
        System.out.println("Attempting to read file: " + filename);
        
        // The BufferedReader will be automatically closed when the try block exits
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("File read successfully");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        System.out.println("After try-with-resources (reader is automatically closed)");
    }
    
    /**
     * Demonstrates using multiple resources in a try-with-resources statement.
     */
    public static void copyFile(String sourceFile, String destinationFile) {
        System.out.println("Attempting to copy from " + sourceFile + " to " + destinationFile);
        
        // Both reader and writer will be automatically closed when the try block exits
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             FileWriter writer = new FileWriter(destinationFile)) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + System.lineSeparator());
            }
            
            System.out.println("File copied successfully");
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        
        System.out.println("After try-with-resources (both reader and writer are automatically closed)");
    }
    
    /**
     * Demonstrates try-with-resources with additional catch and finally blocks.
     */
    public static void readFileWithCatchAndFinally(String filename) {
        System.out.println("Attempting to read file with catch and finally: " + filename);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("File read successfully");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            // This finally block executes after the resource is closed
            System.out.println("Finally block executes after resource is closed");
        }
        
        System.out.println("After try-with-resources with catch and finally");
    }
    
    /**
     * Demonstrates using a custom class that implements AutoCloseable.
     */
    public static void useCustomResource() {
        System.out.println("Using custom AutoCloseable resource");
        
        try (DatabaseConnection connection = new DatabaseConnection("jdbc:mysql://localhost:3306/mydb")) {
            connection.executeQuery("SELECT * FROM users");
            System.out.println("Query executed successfully");
        } catch (Exception e) {
            System.out.println("Error using database connection: " + e.getMessage());
        }
        
        System.out.println("After using custom resource (connection is automatically closed)");
    }
    
    /**
     * Demonstrates using Scanner with try-with-resources.
     */
    public static void readUserInput() {
        System.out.println("Reading user input with try-with-resources");
        
        // In a real application, you would use System.in
        // Here we're using a string as input for demonstration purposes
        String input = "42\nJava\n3.14";
        
        try (Scanner scanner = new Scanner(input)) {
            System.out.println("Reading an integer:");
            int number = scanner.nextInt();
            System.out.println("You entered: " + number);
            
            System.out.println("Reading a string:");
            scanner.nextLine();  // Consume the newline
            String text = scanner.nextLine();
            System.out.println("You entered: " + text);
            
            System.out.println("Reading a double:");
            double decimal = scanner.nextDouble();
            System.out.println("You entered: " + decimal);
        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
        
        System.out.println("After try-with-resources (scanner is automatically closed)");
    }
}

/**
 * A custom class that implements AutoCloseable for use with try-with-resources.
 */
class DatabaseConnection implements AutoCloseable {
    private String url;
    private boolean isOpen;
    
    public DatabaseConnection(String url) {
        this.url = url;
        this.isOpen = true;
        System.out.println("Database connection opened to " + url);
    }
    
    public void executeQuery(String query) {
        if (!isOpen) {
            throw new IllegalStateException("Connection is closed");
        }
        System.out.println("Executing query: " + query);
        // Simulate query execution
    }
    
    @Override
    public void close() throws Exception {
        if (isOpen) {
            System.out.println("Closing database connection to " + url);
            isOpen = false;
            // Clean up resources
        }
    }
}
