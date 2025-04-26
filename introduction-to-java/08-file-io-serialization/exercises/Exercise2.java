/**
 * Exercise 2: Simple Text Editor with File Operations
 * 
 * Instructions:
 * 1. Create a simple text editor application that supports the following operations:
 *    - Creating a new file
 *    - Opening an existing file
 *    - Editing the content
 *    - Saving the file
 *    - Saving the file with a different name (Save As)
 * 
 * 2. Implement the following features:
 *    - Text area for editing the content
 *    - File menu with New, Open, Save, Save As, and Exit options
 *    - Edit menu with Cut, Copy, Paste, and Select All options
 *    - Status bar showing the current file name and modification status
 *    - Confirmation dialog when closing with unsaved changes
 *    - Support for different character encodings
 * 
 * 3. Create a TextEditor class that manages the file operations:
 *    - newFile(): Creates a new empty file
 *    - openFile(File file): Opens and reads the content of a file
 *    - saveFile(File file): Saves the current content to a file
 *    - isModified(): Returns whether the content has been modified
 *    - setEncoding(String encoding): Sets the character encoding to use
 * 
 * 4. Implement proper exception handling for all file operations.
 * 
 * 5. Create a simple console-based version of the text editor that demonstrates
 *    the file operations.
 * 
 * 6. Bonus: Implement a graphical user interface using JavaFX or Swing.
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("Simple Text Editor");
        System.out.println("=================");
        
        Scanner scanner = new Scanner(System.in);
        
        // TODO: Implement the TextEditor class
        // TODO: Implement the console-based text editor
        
        try {
            boolean running = true;
            
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. New File");
                System.out.println("2. Open File");
                System.out.println("3. Edit Content");
                System.out.println("4. Save File");
                System.out.println("5. Save As");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                
                switch (choice) {
                    case 1:
                        // New File
                        System.out.println("New file created.");
                        break;
                    case 2:
                        // Open File
                        System.out.print("Enter file path to open: ");
                        String openPath = scanner.nextLine();
                        System.out.println("Opening file: " + openPath);
                        break;
                    case 3:
                        // Edit Content
                        System.out.println("Enter content (type 'END' on a new line to finish):");
                        break;
                    case 4:
                        // Save File
                        System.out.println("Saving file...");
                        break;
                    case 5:
                        // Save As
                        System.out.print("Enter file path to save as: ");
                        String savePath = scanner.nextLine();
                        System.out.println("Saving file as: " + savePath);
                        break;
                    case 6:
                        // Exit
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

// TODO: Implement the TextEditor class
