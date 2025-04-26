/**
 * Exercise 4: Recursive Problems
 * 
 * Instructions:
 * 1. Implement solutions to the following problems using recursion:
 *    a) Calculate the sum of digits in a number
 *       Example: sumOfDigits(123) = 1 + 2 + 3 = 6
 *    
 *    b) Check if a string is a palindrome using recursion
 *       Example: isPalindrome("racecar") = true
 *    
 *    c) Calculate the sum of elements in an array using recursion
 *       Example: sumArray([1, 2, 3, 4, 5]) = 15
 *    
 *    d) Implement a recursive binary search
 *       Example: binarySearch([1, 2, 3, 4, 5], 3) = 2 (index of 3)
 * 
 * 2. Create a menu system to test each recursive function
 * 3. Add appropriate comments explaining how each recursive solution works
 */
import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;
        
        while (continueProgram) {
            // TODO: Display a menu of available recursive functions
            
            // TODO: Prompt the user to select a function
            
            // TODO: Based on the selected function, prompt for the required inputs
            
            // TODO: Call the appropriate recursive method and display the result
            
            // TODO: Ask the user if they want to test another function
            
            // TODO: Update the continueProgram variable based on the user's response
        }
        
        // Don't forget to close the scanner
        scanner.close();
    }
    
    // TODO: Implement the sumOfDigits recursive method
    
    // TODO: Implement the isPalindrome recursive method
    
    // TODO: Implement the sumArray recursive method
    
    // TODO: Implement the binarySearch recursive method
}

// Expected output (example):
// Recursive Functions Menu:
// 1. Sum of Digits
// 2. Palindrome Check
// 3. Sum of Array
// 4. Binary Search
// Select a function (1-4): 1
// Enter a number: 12345
// Sum of digits in 12345 is 15
// 
// Do you want to test another function? (yes/no): yes
// 
// Recursive Functions Menu:
// ...
// Select a function (1-4): 2
// Enter a string: racecar
// "racecar" is a palindrome
// 
// Do you want to test another function? (yes/no): no
// Thank you for testing the recursive functions!
