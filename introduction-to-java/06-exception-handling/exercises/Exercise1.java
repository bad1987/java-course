/**
 * Exercise 1: Calculator with Exception Handling
 * 
 * Instructions:
 * 1. Create a Calculator class with the following methods:
 *    - add(double a, double b): Returns a + b
 *    - subtract(double a, double b): Returns a - b
 *    - multiply(double a, double b): Returns a * b
 *    - divide(double a, double b): Returns a / b, but throws an ArithmeticException if b is zero
 *    - power(double base, double exponent): Returns base raised to the power of exponent
 *    - squareRoot(double a): Returns the square root of a, but throws an IllegalArgumentException if a is negative
 * 
 * 2. Create a CalculatorApp class with a main method that:
 *    - Creates a Calculator object
 *    - Prompts the user to enter an operation (+, -, *, /, ^, sqrt)
 *    - Prompts the user to enter the required numbers
 *    - Performs the calculation and displays the result
 *    - Handles all potential exceptions with appropriate error messages
 *    - Allows the user to perform multiple calculations until they choose to exit
 * 
 * 3. Implement proper exception handling for:
 *    - ArithmeticException (division by zero)
 *    - IllegalArgumentException (negative square root)
 *    - NumberFormatException (invalid number input)
 *    - InputMismatchException (wrong input type)
 *    - Any other exceptions that might occur
 * 
 * 4. Use a finally block to clean up resources (e.g., close the Scanner)
 * 
 * 5. Implement a custom exception called InvalidOperationException that is thrown when
 *    the user enters an invalid operation.
 */
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        // TODO: Implement the CalculatorApp
    }
}

// TODO: Implement the Calculator class

// TODO: Implement the InvalidOperationException class
