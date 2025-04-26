/**
 * BasicExceptionHandling.java
 * This program demonstrates basic exception handling in Java using try-catch blocks.
 */
import java.util.Scanner;

public class BasicExceptionHandling {
    public static void main(String[] args) {
        System.out.println("--- Basic Exception Handling ---");
        
        // Example 1: Handling ArithmeticException
        System.out.println("\nExample 1: Division by Zero");
        divideNumbers(10, 2);  // This will work fine
        divideNumbers(10, 0);  // This will throw an exception
        
        // Example 2: Handling ArrayIndexOutOfBoundsException
        System.out.println("\nExample 2: Array Index Out of Bounds");
        accessArrayElement(new int[]{1, 2, 3, 4, 5}, 2);  // This will work fine
        accessArrayElement(new int[]{1, 2, 3, 4, 5}, 10);  // This will throw an exception
        
        // Example 3: Handling NullPointerException
        System.out.println("\nExample 3: Null Pointer");
        String validString = "Hello, World!";
        String nullString = null;
        
        getStringLength(validString);  // This will work fine
        getStringLength(nullString);   // This will throw an exception
        
        // Example 4: Handling NumberFormatException
        System.out.println("\nExample 4: Number Format");
        convertToInteger("123");    // This will work fine
        convertToInteger("abc");    // This will throw an exception
        
        // Example 5: Handling exceptions with user input
        System.out.println("\nExample 5: User Input");
        getUserAge();
    }
    
    /**
     * Divides two numbers and handles potential ArithmeticException.
     */
    public static void divideNumbers(int dividend, int divisor) {
        try {
            int result = dividend / divisor;
            System.out.println(dividend + " / " + divisor + " = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
            System.out.println("Exception message: " + e.getMessage());
        }
    }
    
    /**
     * Accesses an array element and handles potential ArrayIndexOutOfBoundsException.
     */
    public static void accessArrayElement(int[] array, int index) {
        try {
            int value = array[index];
            System.out.println("Value at index " + index + ": " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index " + index + " is out of bounds for array of length " + array.length);
            System.out.println("Exception message: " + e.getMessage());
        }
    }
    
    /**
     * Gets the length of a string and handles potential NullPointerException.
     */
    public static void getStringLength(String str) {
        try {
            int length = str.length();
            System.out.println("Length of \"" + str + "\": " + length);
        } catch (NullPointerException e) {
            System.out.println("Error: Cannot get length of null string");
            System.out.println("Exception message: " + e.getMessage());
        }
    }
    
    /**
     * Converts a string to an integer and handles potential NumberFormatException.
     */
    public static void convertToInteger(String str) {
        try {
            int number = Integer.parseInt(str);
            System.out.println("Converted \"" + str + "\" to integer: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Error: Cannot convert \"" + str + "\" to an integer");
            System.out.println("Exception message: " + e.getMessage());
        }
    }
    
    /**
     * Gets user age from input and handles potential exceptions.
     */
    public static void getUserAge() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            
            if (age < 0) {
                System.out.println("Error: Age cannot be negative");
            } else if (age > 120) {
                System.out.println("Error: Age seems unrealistic");
            } else {
                System.out.println("Your age is: " + age);
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter a valid integer for age.");
            System.out.println("Exception message: " + e.getMessage());
        }
        
        // Note: In a real application, you would close the scanner.
        // We're not closing it here to avoid closing System.in.
    }
}
