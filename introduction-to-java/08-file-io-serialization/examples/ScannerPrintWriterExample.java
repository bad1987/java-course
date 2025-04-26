/**
 * ScannerPrintWriterExample.java
 * This program demonstrates using Scanner for reading and PrintWriter for writing formatted text.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerPrintWriterExample {
    public static void main(String[] args) {
        System.out.println("--- Scanner and PrintWriter Examples ---");
        
        // Example 1: Writing formatted output with PrintWriter
        System.out.println("\nExample 1: Writing formatted output with PrintWriter");
        writeFormattedOutput();
        
        // Example 2: Reading with Scanner
        System.out.println("\nExample 2: Reading with Scanner");
        readWithScanner();
        
        // Example 3: Reading different data types with Scanner
        System.out.println("\nExample 3: Reading different data types with Scanner");
        readDifferentDataTypes();
        
        // Example 4: Using Scanner with delimiters
        System.out.println("\nExample 4: Using Scanner with delimiters");
        useDelimiters();
        
        // Example 5: Reading CSV data with Scanner
        System.out.println("\nExample 5: Reading CSV data with Scanner");
        readCSVData();
        
        // Example 6: Using Scanner with regular expressions
        System.out.println("\nExample 6: Using Scanner with regular expressions");
        useRegularExpressions();
        
        // Example 7: Processing a data file
        System.out.println("\nExample 7: Processing a data file");
        processDataFile();
    }
    
    /**
     * Demonstrates writing formatted output with PrintWriter.
     */
    public static void writeFormattedOutput() {
        // Create a PrintWriter
        try (PrintWriter writer = new PrintWriter("formatted_output.txt")) {
            // Write simple text
            writer.println("This is a simple line of text.");
            writer.println("This is another line of text.");
            
            // Write formatted text
            writer.printf("Formatted integer: %d%n", 42);
            writer.printf("Formatted float with 2 decimal places: %.2f%n", 3.14159);
            writer.printf("Formatted string with padding: %10s%n", "Hello");
            writer.printf("Left-justified string: %-10s%n", "Hello");
            
            // Write multiple values
            writer.printf("Name: %s, Age: %d, GPA: %.2f%n", "Alice", 20, 3.75);
            writer.printf("Name: %s, Age: %d, GPA: %.2f%n", "Bob", 22, 3.45);
            
            // Write a table
            writer.println("\nStudent Information:");
            writer.printf("%-15s %-5s %-10s%n", "Name", "Age", "GPA");
            writer.printf("%-15s %-5d %-10.2f%n", "Alice", 20, 3.75);
            writer.printf("%-15s %-5d %-10.2f%n", "Bob", 22, 3.45);
            writer.printf("%-15s %-5d %-10.2f%n", "Charlie", 21, 3.90);
            
            System.out.println("Formatted output written to formatted_output.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading with Scanner.
     */
    public static void readWithScanner() {
        // Create a Scanner to read from the file we just created
        try (Scanner scanner = new Scanner(new File("formatted_output.txt"))) {
            System.out.println("Reading file line by line:");
            
            // Read and print each line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading different data types with Scanner.
     */
    public static void readDifferentDataTypes() {
        // First, create a file with different data types
        try (PrintWriter writer = new PrintWriter("data_types.txt")) {
            writer.println("42");
            writer.println("3.14159");
            writer.println("true");
            writer.println("Hello");
            
            System.out.println("Data types file created: data_types.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
        
        // Now read the file with Scanner
        try (Scanner scanner = new Scanner(new File("data_types.txt"))) {
            // Read an integer
            if (scanner.hasNextInt()) {
                int intValue = scanner.nextInt();
                System.out.println("Integer value: " + intValue);
            }
            
            // Read a double
            if (scanner.hasNextDouble()) {
                double doubleValue = scanner.nextDouble();
                System.out.println("Double value: " + doubleValue);
            }
            
            // Read a boolean
            if (scanner.hasNextBoolean()) {
                boolean booleanValue = scanner.nextBoolean();
                System.out.println("Boolean value: " + booleanValue);
            }
            
            // Read a string
            if (scanner.hasNext()) {
                String stringValue = scanner.next();
                System.out.println("String value: " + stringValue);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
        
        // Reading mixed data types
        try (PrintWriter writer = new PrintWriter("mixed_data.txt")) {
            writer.println("Name: Alice Age: 30 GPA: 3.75");
            writer.println("Name: Bob Age: 25 GPA: 3.45");
            
            System.out.println("\nMixed data file created: mixed_data.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
        
        // Read the mixed data
        try (Scanner scanner = new Scanner(new File("mixed_data.txt"))) {
            System.out.println("\nReading mixed data:");
            
            while (scanner.hasNextLine()) {
                Scanner lineScanner = new Scanner(scanner.nextLine());
                
                // Skip "Name:"
                lineScanner.next();
                
                // Read the name
                String name = lineScanner.next();
                
                // Skip "Age:"
                lineScanner.next();
                
                // Read the age
                int age = lineScanner.nextInt();
                
                // Skip "GPA:"
                lineScanner.next();
                
                // Read the GPA
                double gpa = lineScanner.nextDouble();
                
                System.out.printf("Name: %s, Age: %d, GPA: %.2f%n", name, age, gpa);
                
                lineScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates using Scanner with delimiters.
     */
    public static void useDelimiters() {
        // Create a file with delimited data
        try (PrintWriter writer = new PrintWriter("delimited_data.txt")) {
            writer.println("apple,banana,cherry,date");
            writer.println("red,green,blue,yellow");
            writer.println("1;2;3;4");
            
            System.out.println("Delimited data file created: delimited_data.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
        
        // Read the file with comma delimiter
        try (Scanner scanner = new Scanner(new File("delimited_data.txt"))) {
            System.out.println("\nReading with comma delimiter:");
            
            scanner.useDelimiter(",|\\n");  // Use comma or newline as delimiter
            
            while (scanner.hasNext()) {
                String token = scanner.next();
                System.out.println("Token: " + token);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
        
        // Read the file line by line and use different delimiters
        try (Scanner scanner = new Scanner(new File("delimited_data.txt"))) {
            System.out.println("\nReading line by line with different delimiters:");
            
            // First line with comma delimiter
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                
                System.out.println("Fruits:");
                while (lineScanner.hasNext()) {
                    System.out.println("- " + lineScanner.next());
                }
                
                lineScanner.close();
            }
            
            // Second line with comma delimiter
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                
                System.out.println("\nColors:");
                while (lineScanner.hasNext()) {
                    System.out.println("- " + lineScanner.next());
                }
                
                lineScanner.close();
            }
            
            // Third line with semicolon delimiter
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                
                System.out.println("\nNumbers:");
                while (lineScanner.hasNextInt()) {
                    System.out.println("- " + lineScanner.nextInt());
                }
                
                lineScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading CSV data with Scanner.
     */
    public static void readCSVData() {
        // Create a CSV file
        try (PrintWriter writer = new PrintWriter("data.csv")) {
            writer.println("ID,Name,Age,Salary");
            writer.println("1,Alice,30,75000.50");
            writer.println("2,Bob,25,65000.75");
            writer.println("3,Charlie,35,85000.25");
            
            System.out.println("CSV file created: data.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
        
        // Read the CSV file
        try (Scanner scanner = new Scanner(new File("data.csv"))) {
            System.out.println("\nReading CSV data:");
            
            // Read the header
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine();
                System.out.println("Header: " + header);
            }
            
            // Read the data rows
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                
                int id = lineScanner.nextInt();
                String name = lineScanner.next();
                int age = lineScanner.nextInt();
                double salary = lineScanner.nextDouble();
                
                System.out.printf("ID: %d, Name: %s, Age: %d, Salary: $%.2f%n", 
                                 id, name, age, salary);
                
                lineScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates using Scanner with regular expressions.
     */
    public static void useRegularExpressions() {
        // Create a file with text for regex matching
        try (PrintWriter writer = new PrintWriter("regex_data.txt")) {
            writer.println("Email: alice@example.com");
            writer.println("Phone: (123) 456-7890");
            writer.println("Date: 2023-05-15");
            writer.println("URL: https://www.example.com");
            writer.println("IP Address: 192.168.1.1");
            
            System.out.println("Regex data file created: regex_data.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
        
        // Read the file and extract data using regex
        try (Scanner scanner = new Scanner(new File("regex_data.txt"))) {
            System.out.println("\nExtracting data with regular expressions:");
            
            // Email regex pattern
            String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
            
            // Phone regex pattern
            String phonePattern = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
            
            // Date regex pattern
            String datePattern = "\\d{4}-\\d{2}-\\d{2}";
            
            // URL regex pattern
            String urlPattern = "https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/[a-zA-Z0-9./-]*)?";
            
            // IP address regex pattern
            String ipPattern = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Check for email
                Scanner emailScanner = new Scanner(line);
                if (emailScanner.findInLine(emailPattern) != null) {
                    System.out.println("Found email: " + emailScanner.match().group());
                }
                emailScanner.close();
                
                // Check for phone
                Scanner phoneScanner = new Scanner(line);
                if (phoneScanner.findInLine(phonePattern) != null) {
                    System.out.println("Found phone: " + phoneScanner.match().group());
                }
                phoneScanner.close();
                
                // Check for date
                Scanner dateScanner = new Scanner(line);
                if (dateScanner.findInLine(datePattern) != null) {
                    System.out.println("Found date: " + dateScanner.match().group());
                }
                dateScanner.close();
                
                // Check for URL
                Scanner urlScanner = new Scanner(line);
                if (urlScanner.findInLine(urlPattern) != null) {
                    System.out.println("Found URL: " + urlScanner.match().group());
                }
                urlScanner.close();
                
                // Check for IP address
                Scanner ipScanner = new Scanner(line);
                if (ipScanner.findInLine(ipPattern) != null) {
                    System.out.println("Found IP address: " + ipScanner.match().group());
                }
                ipScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates processing a data file with Scanner and PrintWriter.
     */
    public static void processDataFile() {
        // Create a data file
        try (PrintWriter writer = new PrintWriter("student_data.txt")) {
            writer.println("# Student Data");
            writer.println("# Format: ID, Name, Age, GPA");
            writer.println("1, Alice, 20, 3.75");
            writer.println("2, Bob, 22, 3.45");
            writer.println("3, Charlie, 21, 3.90");
            writer.println("4, David, 23, 3.20");
            writer.println("5, Eve, 19, 4.00");
            
            System.out.println("Student data file created: student_data.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
        
        // Process the data file
        try (Scanner scanner = new Scanner(new File("student_data.txt"));
             PrintWriter writer = new PrintWriter("processed_data.txt")) {
            
            // Write header to output file
            writer.println("Student Report");
            writer.println("=============");
            writer.println();
            
            // Skip comment lines and process data
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Skip comment lines
                if (line.startsWith("#")) {
                    continue;
                }
                
                // Process data line
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",\\s*");
                
                if (lineScanner.hasNextInt()) {
                    int id = lineScanner.nextInt();
                    String name = lineScanner.next();
                    int age = lineScanner.nextInt();
                    double gpa = lineScanner.nextDouble();
                    
                    // Write formatted output
                    writer.printf("Student ID: %d%n", id);
                    writer.printf("Name: %s%n", name);
                    writer.printf("Age: %d%n", age);
                    writer.printf("GPA: %.2f%n", gpa);
                    
                    // Add honor status based on GPA
                    if (gpa >= 3.5) {
                        writer.println("Honor Status: Dean's List");
                    } else if (gpa >= 3.0) {
                        writer.println("Honor Status: Good Standing");
                    } else {
                        writer.println("Honor Status: Academic Probation");
                    }
                    
                    writer.println();
                }
                
                lineScanner.close();
            }
            
            System.out.println("Data processed and written to processed_data.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
        
        // Display the processed file
        try (Scanner scanner = new Scanner(new File("processed_data.txt"))) {
            System.out.println("\nProcessed data file content:");
            
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading processed file: " + e.getMessage());
        }
    }
}
