/**
 * Exercise 2: Calculator Methods
 * 
 * Instructions:
 * 1. Create a calculator program that uses methods for different operations
 * 2. Implement methods for:
 *    - Addition
 *    - Subtraction
 *    - Multiplication
 *    - Division
 *    - Power (exponentiation)
 *    - Square root
 *    - Factorial
 * 3. Create a menu system that allows the user to select an operation
 * 4. Handle edge cases (like division by zero)
 * 5. Allow the user to perform multiple calculations
 */
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;
        
        while (continueCalculating) {
            // TODO: Display a menu of available operations
            
            // TODO: Prompt the user to select an operation
            
            // TODO: Based on the selected operation, prompt for the required inputs
            
            // TODO: Call the appropriate method and display the result
            
            // TODO: Ask the user if they want to perform another calculation
            
            // TODO: Update the continueCalculating variable based on the user's response
        }
        
        // Don't forget to close the scanner
        scanner.close();
    }
    
    // TODO: Implement the add method
    
    // TODO: Implement the subtract method
    
    // TODO: Implement the multiply method
    
    // TODO: Implement the divide method (handle division by zero)
    
    // TODO: Implement the power method
    
    // TODO: Implement the squareRoot method
    
    // TODO: Implement the factorial method
}

// Expected output (example):
// Calculator Menu:
// 1. Addition
// 2. Subtraction
// 3. Multiplication
// 4. Division
// 5. Power
// 6. Square Root
// 7. Factorial
// Select an operation (1-7): 1
// Enter first number: 5
// Enter second number: 3
// Result: 5 + 3 = 8
// 
// Do you want to perform another calculation? (yes/no): yes
// 
// Calculator Menu:
// ...
// Select an operation (1-7): 7
// Enter a number: 5
// Result: 5! = 120
// 
// Do you want to perform another calculation? (yes/no): no
// Thank you for using the calculator!
