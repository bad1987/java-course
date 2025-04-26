/**
 * InputAndOutput.java
 * This program demonstrates basic input and output operations in Java.
 */
import java.util.Scanner;  // Import the Scanner class for reading input

public class InputAndOutput {
    public static void main(String[] args) {
        // Output examples
        System.out.println("--- Output Examples ---");
        
        // println() - prints and then moves to a new line
        System.out.println("This is printed with println().");
        System.out.println("This is on a new line.");
        
        // print() - prints without moving to a new line
        System.out.print("This is printed with print(). ");
        System.out.print("This continues on the same line.");
        System.out.println(); // Move to a new line
        
        // printf() - formatted output
        System.out.printf("Formatted number: %.2f%n", 3.14159);
        System.out.printf("Formatted text: %s, number: %d%n", "Hello", 42);
        
        // Input examples
        System.out.println("\n--- Input Examples ---");
        
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);
        
        // Reading different types of input
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();  // Read a line of text
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();       // Read an integer
        
        // Note: After reading a number with nextInt(), nextDouble(), etc.,
        // you need to consume the newline character before using nextLine() again
        scanner.nextLine();
        
        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine();  // Read another line of text
        
        System.out.print("Enter your height in meters: ");
        double height = scanner.nextDouble();  // Read a double
        
        // Display the input
        System.out.println("\n--- Your Information ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Favorite Language: " + language);
        System.out.println("Height: " + height + " meters");
        
        // Always close the scanner when done
        scanner.close();
    }
}

/*
Note: When running this program, you'll need to provide input in the console.
Example interaction:

Enter your name: John Doe
Enter your age: 25
Enter your favorite programming language: Java
Enter your height in meters: 1.75

--- Your Information ---
Name: John Doe
Age: 25
Favorite Language: Java
Height: 1.75 meters
*/
