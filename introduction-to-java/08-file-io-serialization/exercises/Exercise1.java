/**
 * Exercise 1: File Copy Utility with Progress Reporting
 * 
 * Instructions:
 * 1. Create a file copy utility that copies files from a source to a destination.
 * 2. Implement the following features:
 *    - Support for copying both text and binary files
 *    - Progress reporting (percentage complete)
 *    - Copy speed calculation (bytes per second)
 *    - Estimated time remaining
 *    - Option to cancel the copy operation
 *    - Error handling for common file operations
 * 
 * 3. Create a FileCopyUtility class with the following methods:
 *    - copyFile(File source, File destination): Copies a file with progress reporting
 *    - copyDirectory(File sourceDir, File destDir): Recursively copies a directory
 *    - cancelCopy(): Cancels the current copy operation
 *    - getProgress(): Returns the current progress (0-100)
 *    - getSpeed(): Returns the current copy speed in bytes per second
 *    - getEstimatedTimeRemaining(): Returns the estimated time remaining in seconds
 * 
 * 4. Implement a ProgressListener interface that can be registered with the FileCopyUtility
 *    to receive progress updates.
 * 
 * 5. Create a simple console application that demonstrates the file copy utility:
 *    - Allows the user to specify source and destination files/directories
 *    - Displays progress, speed, and estimated time remaining
 *    - Allows the user to cancel the operation
 *    - Reports success or failure
 * 
 * 6. Bonus: Implement a graphical user interface using JavaFX or Swing that shows
 *    the progress with a progress bar.
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("File Copy Utility");
        System.out.println("=================");
        
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Get source and destination
            System.out.print("Enter source file/directory path: ");
            String sourcePath = scanner.nextLine();
            
            System.out.print("Enter destination file/directory path: ");
            String destPath = scanner.nextLine();
            
            File source = new File(sourcePath);
            File destination = new File(destPath);
            
            // Validate source
            if (!source.exists()) {
                System.out.println("Error: Source does not exist.");
                return;
            }
            
            // TODO: Implement the FileCopyUtility class
            // TODO: Implement the ProgressListener interface
            // TODO: Implement the copy operation with progress reporting
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

// TODO: Implement the FileCopyUtility class

// TODO: Implement the ProgressListener interface
