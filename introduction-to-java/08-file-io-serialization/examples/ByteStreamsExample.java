/**
 * ByteStreamsExample.java
 * This program demonstrates reading from and writing to files using byte streams.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteStreamsExample {
    public static void main(String[] args) {
        System.out.println("--- Byte Streams Examples ---");
        
        // Example 1: Writing to a file using FileOutputStream
        System.out.println("\nExample 1: Writing to a file using FileOutputStream");
        writeToFile();
        
        // Example 2: Reading from a file using FileInputStream
        System.out.println("\nExample 2: Reading from a file using FileInputStream");
        readFromFile();
        
        // Example 3: Using BufferedOutputStream for better performance
        System.out.println("\nExample 3: Using BufferedOutputStream for better performance");
        writeWithBuffering();
        
        // Example 4: Using BufferedInputStream for better performance
        System.out.println("\nExample 4: Using BufferedInputStream for better performance");
        readWithBuffering();
        
        // Example 5: Copying a file using byte streams
        System.out.println("\nExample 5: Copying a file using byte streams");
        copyFile("source.txt", "destination.txt");
        
        // Example 6: Working with ByteArrayInputStream and ByteArrayOutputStream
        System.out.println("\nExample 6: Working with ByteArrayInputStream and ByteArrayOutputStream");
        workWithByteArrayStreams();
        
        // Example 7: Reading and writing binary data
        System.out.println("\nExample 7: Reading and writing binary data");
        workWithBinaryData();
    }
    
    /**
     * Demonstrates writing to a file using FileOutputStream.
     */
    public static void writeToFile() {
        // Data to write
        String data = "Hello, World! This is a test of FileOutputStream.";
        
        // Create a FileOutputStream
        try (FileOutputStream fos = new FileOutputStream("output.txt")) {
            // Convert string to bytes
            byte[] bytes = data.getBytes();
            
            // Write bytes to the file
            fos.write(bytes);
            
            System.out.println("Data written to output.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
        // Append to an existing file
        try (FileOutputStream fos = new FileOutputStream("output.txt", true)) {
            // Data to append
            String appendData = "\nThis line is appended to the file.";
            byte[] bytes = appendData.getBytes();
            
            // Write bytes to the file
            fos.write(bytes);
            
            System.out.println("Data appended to output.txt");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading from a file using FileInputStream.
     */
    public static void readFromFile() {
        // Create a FileInputStream
        try (FileInputStream fis = new FileInputStream("output.txt")) {
            // Read data byte by byte
            System.out.println("Reading file byte by byte:");
            int data;
            while ((data = fis.read()) != -1) {
                // Convert byte to char and print
                System.out.print((char) data);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        
        // Reading into a byte array
        try (FileInputStream fis = new FileInputStream("output.txt")) {
            // Create a byte array to hold the data
            byte[] buffer = new byte[1024];
            
            // Read data into the buffer
            int bytesRead = fis.read(buffer);
            
            // Convert bytes to string and print
            if (bytesRead > 0) {
                String content = new String(buffer, 0, bytesRead);
                System.out.println("\nReading file into buffer:");
                System.out.println(content);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        
        // Reading the entire file
        try (FileInputStream fis = new FileInputStream("output.txt")) {
            // Get the file size
            int fileSize = fis.available();
            
            // Create a byte array of the file size
            byte[] allBytes = new byte[fileSize];
            
            // Read the entire file
            fis.read(allBytes);
            
            // Convert bytes to string and print
            String content = new String(allBytes);
            System.out.println("\nReading entire file:");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates writing to a file with buffering for better performance.
     */
    public static void writeWithBuffering() {
        // Data to write
        String data = "This is a test of BufferedOutputStream.\n";
        data += "Buffering improves performance by reducing the number of I/O operations.\n";
        data += "It does this by writing data in larger chunks rather than byte by byte.";
        
        // Create a BufferedOutputStream
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("buffered_output.txt"))) {
            
            // Convert string to bytes
            byte[] bytes = data.getBytes();
            
            // Write bytes to the file
            bos.write(bytes);
            
            // Flush the buffer to ensure all data is written
            bos.flush();
            
            System.out.println("Data written to buffered_output.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading from a file with buffering for better performance.
     */
    public static void readWithBuffering() {
        // Create a BufferedInputStream
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("buffered_output.txt"))) {
            
            // Read data byte by byte
            System.out.println("Reading file with buffering:");
            int data;
            while ((data = bis.read()) != -1) {
                // Convert byte to char and print
                System.out.print((char) data);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        
        // Reading into a byte array with buffering
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("buffered_output.txt"))) {
            
            // Create a byte array to hold the data
            byte[] buffer = new byte[1024];
            
            // Read data into the buffer
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            
            while ((bytesRead = bis.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            
            // Convert bytes to string and print
            String content = output.toString();
            System.out.println("\nReading file into buffer with buffering:");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates copying a file using byte streams.
     */
    public static void copyFile(String sourceFile, String destinationFile) {
        // First, create a source file with some content
        try (FileOutputStream fos = new FileOutputStream(sourceFile)) {
            String content = "This is the source file content.\n";
            content += "It will be copied to the destination file.\n";
            content += "Using byte streams for the copy operation.";
            
            fos.write(content.getBytes());
            System.out.println("Source file created: " + sourceFile);
        } catch (IOException e) {
            System.out.println("Error creating source file: " + e.getMessage());
            return;
        }
        
        // Now copy the file
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {
            
            // Create a buffer for the copy operation
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            // Read from source and write to destination
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
            System.out.println("File copied from " + sourceFile + " to " + destinationFile);
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        
        // Verify the copy by reading the destination file
        try (FileInputStream fis = new FileInputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            
            if (bytesRead > 0) {
                String content = new String(buffer, 0, bytesRead);
                System.out.println("\nDestination file content:");
                System.out.println(content);
            }
        } catch (IOException e) {
            System.out.println("Error reading destination file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates working with ByteArrayInputStream and ByteArrayOutputStream.
     */
    public static void workWithByteArrayStreams() {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        // Write data to the ByteArrayOutputStream
        try {
            baos.write("Hello, ".getBytes());
            baos.write("ByteArrayOutputStream!".getBytes());
            
            // Get the byte array
            byte[] bytes = baos.toByteArray();
            
            // Convert to string and print
            String content = new String(bytes);
            System.out.println("ByteArrayOutputStream content: " + content);
            
            // Write the content to a file
            try (FileOutputStream fos = new FileOutputStream("bytearray_output.txt")) {
                baos.writeTo(fos);
                System.out.println("Content written to bytearray_output.txt");
            }
        } catch (IOException e) {
            System.out.println("Error working with ByteArrayOutputStream: " + e.getMessage());
        }
        
        // Create a ByteArrayInputStream
        String inputData = "This is a test of ByteArrayInputStream.";
        ByteArrayInputStream bais = new ByteArrayInputStream(inputData.getBytes());
        
        // Read data from the ByteArrayInputStream
        int data;
        System.out.println("\nReading from ByteArrayInputStream:");
        while ((data = bais.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        
        // Reset the ByteArrayInputStream to read from the beginning again
        bais.reset();
        
        // Read into a byte array
        byte[] buffer = new byte[1024];
        int bytesRead = bais.read(buffer);
        
        if (bytesRead > 0) {
            String content = new String(buffer, 0, bytesRead);
            System.out.println("\nAfter reset, read again: " + content);
        }
    }
    
    /**
     * Demonstrates reading and writing binary data.
     */
    public static void workWithBinaryData() {
        // Create a binary file
        try (FileOutputStream fos = new FileOutputStream("binary_data.bin")) {
            // Create some binary data
            byte[] binaryData = new byte[256];
            for (int i = 0; i < 256; i++) {
                binaryData[i] = (byte) i;
            }
            
            // Write the binary data to the file
            fos.write(binaryData);
            
            System.out.println("Binary data written to binary_data.bin");
        } catch (IOException e) {
            System.out.println("Error writing binary data: " + e.getMessage());
        }
        
        // Read the binary data
        try (FileInputStream fis = new FileInputStream("binary_data.bin")) {
            // Create a buffer to hold the data
            byte[] buffer = new byte[256];
            
            // Read the data
            int bytesRead = fis.read(buffer);
            
            // Display the binary data
            System.out.println("\nBinary data read from file (first 20 bytes):");
            for (int i = 0; i < 20 && i < bytesRead; i++) {
                // Convert to unsigned int (0-255)
                int unsignedByte = buffer[i] & 0xFF;
                System.out.printf("%02X ", unsignedByte);
            }
            System.out.println();
            
            // Count the number of bytes
            System.out.println("Total bytes read: " + bytesRead);
        } catch (IOException e) {
            System.out.println("Error reading binary data: " + e.getMessage());
        }
    }
}
