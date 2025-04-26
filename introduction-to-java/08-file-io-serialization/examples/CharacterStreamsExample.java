/**
 * CharacterStreamsExample.java
 * This program demonstrates reading from and writing to files using character streams.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharacterStreamsExample {
    public static void main(String[] args) {
        System.out.println("--- Character Streams Examples ---");
        
        // Example 1: Writing to a file using FileWriter
        System.out.println("\nExample 1: Writing to a file using FileWriter");
        writeToFile();
        
        // Example 2: Reading from a file using FileReader
        System.out.println("\nExample 2: Reading from a file using FileReader");
        readFromFile();
        
        // Example 3: Using BufferedWriter for better performance
        System.out.println("\nExample 3: Using BufferedWriter for better performance");
        writeWithBuffering();
        
        // Example 4: Using BufferedReader for better performance
        System.out.println("\nExample 4: Using BufferedReader for better performance");
        readWithBuffering();
        
        // Example 5: Reading and writing with different character encodings
        System.out.println("\nExample 5: Reading and writing with different character encodings");
        workWithCharacterEncodings();
        
        // Example 6: Working with StringReader and StringWriter
        System.out.println("\nExample 6: Working with StringReader and StringWriter");
        workWithStringReaderWriter();
        
        // Example 7: Copying a text file using character streams
        System.out.println("\nExample 7: Copying a text file using character streams");
        copyTextFile("source_text.txt", "destination_text.txt");
    }
    
    /**
     * Demonstrates writing to a file using FileWriter.
     */
    public static void writeToFile() {
        // Data to write
        String data = "Hello, World! This is a test of FileWriter.\n";
        data += "FileWriter is used for writing text to character files.";
        
        // Create a FileWriter
        try (FileWriter writer = new FileWriter("char_output.txt")) {
            // Write the string to the file
            writer.write(data);
            
            System.out.println("Data written to char_output.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
        // Append to an existing file
        try (FileWriter writer = new FileWriter("char_output.txt", true)) {
            // Data to append
            String appendData = "\nThis line is appended to the file using FileWriter.";
            
            // Write the string to the file
            writer.write(appendData);
            
            System.out.println("Data appended to char_output.txt");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading from a file using FileReader.
     */
    public static void readFromFile() {
        // Create a FileReader
        try (FileReader reader = new FileReader("char_output.txt")) {
            // Read data character by character
            System.out.println("Reading file character by character:");
            int character;
            while ((character = reader.read()) != -1) {
                // Print the character
                System.out.print((char) character);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        
        // Reading into a character array
        try (FileReader reader = new FileReader("char_output.txt")) {
            // Create a character array to hold the data
            char[] buffer = new char[1024];
            
            // Read data into the buffer
            int charsRead = reader.read(buffer);
            
            // Convert to string and print
            if (charsRead > 0) {
                String content = new String(buffer, 0, charsRead);
                System.out.println("\nReading file into buffer:");
                System.out.println(content);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates writing to a file with buffering for better performance.
     */
    public static void writeWithBuffering() {
        // Data to write
        String data = "This is a test of BufferedWriter.\n";
        data += "Buffering improves performance by reducing the number of I/O operations.\n";
        data += "It does this by writing data in larger chunks rather than character by character.";
        
        // Create a BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("buffered_char_output.txt"))) {
            
            // Write the string to the file
            writer.write(data);
            
            // Add a new line
            writer.newLine();
            
            // Write more data
            writer.write("This line was added after a newLine() call.");
            
            // Flush the buffer to ensure all data is written
            writer.flush();
            
            System.out.println("Data written to buffered_char_output.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading from a file with buffering for better performance.
     */
    public static void readWithBuffering() {
        // Create a BufferedReader
        try (BufferedReader reader = new BufferedReader(
                new FileReader("buffered_char_output.txt"))) {
            
            // Read data line by line
            System.out.println("Reading file line by line:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        
        // Reading a specific number of characters
        try (BufferedReader reader = new BufferedReader(
                new FileReader("buffered_char_output.txt"))) {
            
            // Create a character array to hold the data
            char[] buffer = new char[20];
            
            // Read the first 20 characters
            int charsRead = reader.read(buffer);
            
            // Convert to string and print
            if (charsRead > 0) {
                String content = new String(buffer, 0, charsRead);
                System.out.println("\nFirst 20 characters:");
                System.out.println(content);
            }
            
            // Skip some characters
            reader.skip(10);
            
            // Read the next 20 characters
            charsRead = reader.read(buffer);
            
            // Convert to string and print
            if (charsRead > 0) {
                String content = new String(buffer, 0, charsRead);
                System.out.println("\nNext 20 characters (after skipping 10):");
                System.out.println(content);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading and writing with different character encodings.
     */
    public static void workWithCharacterEncodings() {
        // Data with non-ASCII characters
        String data = "Hello, World! こんにちは世界! Привет, мир! 你好，世界!";
        
        // Write with UTF-8 encoding
        try (FileWriter writer = new FileWriter("utf8_output.txt", StandardCharsets.UTF_8)) {
            writer.write(data);
            System.out.println("Data written with UTF-8 encoding");
        } catch (IOException e) {
            System.out.println("Error writing with UTF-8: " + e.getMessage());
        }
        
        // Read with UTF-8 encoding
        try (FileReader reader = new FileReader("utf8_output.txt", StandardCharsets.UTF_8)) {
            char[] buffer = new char[1024];
            int charsRead = reader.read(buffer);
            
            if (charsRead > 0) {
                String content = new String(buffer, 0, charsRead);
                System.out.println("\nData read with UTF-8 encoding:");
                System.out.println(content);
            }
        } catch (IOException e) {
            System.out.println("Error reading with UTF-8: " + e.getMessage());
        }
        
        // List available charsets
        System.out.println("\nAvailable character encodings:");
        for (String name : new String[] {"UTF-8", "UTF-16", "ISO-8859-1", "US-ASCII"}) {
            Charset charset = Charset.forName(name);
            System.out.println("- " + charset.name() + ": " + charset.displayName());
        }
    }
    
    /**
     * Demonstrates working with StringReader and StringWriter.
     */
    public static void workWithStringReaderWriter() {
        // Create a StringWriter
        StringWriter stringWriter = new StringWriter();
        
        // Write data to the StringWriter
        try {
            stringWriter.write("Hello, ");
            stringWriter.write("StringWriter!");
            
            // Get the string
            String content = stringWriter.toString();
            System.out.println("StringWriter content: " + content);
            
            // Write the content to a file
            try (FileWriter writer = new FileWriter("stringwriter_output.txt")) {
                writer.write(content);
                System.out.println("Content written to stringwriter_output.txt");
            }
        } catch (IOException e) {
            System.out.println("Error working with StringWriter: " + e.getMessage());
        } finally {
            try {
                stringWriter.close();
            } catch (IOException e) {
                System.out.println("Error closing StringWriter: " + e.getMessage());
            }
        }
        
        // Create a StringReader
        String inputData = "This is a test of StringReader.";
        StringReader stringReader = new StringReader(inputData);
        
        // Read data from the StringReader
        try {
            int character;
            System.out.println("\nReading from StringReader:");
            while ((character = stringReader.read()) != -1) {
                System.out.print((char) character);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error reading from StringReader: " + e.getMessage());
        } finally {
            stringReader.close();
        }
        
        // Using StringReader with BufferedReader
        stringReader = new StringReader(inputData);
        try (BufferedReader bufferedReader = new BufferedReader(stringReader)) {
            System.out.println("\nReading from StringReader with BufferedReader:");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from BufferedReader: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates copying a text file using character streams.
     */
    public static void copyTextFile(String sourceFile, String destinationFile) {
        // First, create a source file with some content
        try (FileWriter writer = new FileWriter(sourceFile)) {
            String content = "This is the source text file content.\n";
            content += "It will be copied to the destination file.\n";
            content += "Using character streams for the copy operation.";
            
            writer.write(content);
            System.out.println("Source file created: " + sourceFile);
        } catch (IOException e) {
            System.out.println("Error creating source file: " + e.getMessage());
            return;
        }
        
        // Now copy the file using BufferedReader and BufferedWriter
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            
            System.out.println("File copied from " + sourceFile + " to " + destinationFile);
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        
        // Verify the copy by reading the destination file
        try (BufferedReader reader = new BufferedReader(new FileReader(destinationFile))) {
            System.out.println("\nDestination file content:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading destination file: " + e.getMessage());
        }
    }
}
