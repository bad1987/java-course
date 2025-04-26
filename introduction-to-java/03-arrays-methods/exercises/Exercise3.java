/**
 * Exercise 3: Text Analyzer
 * 
 * Instructions:
 * 1. Create a program that analyzes text using String methods
 * 2. Implement the following functionality:
 *    - Count the number of words in the text
 *    - Count the number of characters (excluding spaces)
 *    - Find the most frequent word
 *    - Convert the text to title case (first letter of each word capitalized)
 *    - Check if the text is a palindrome (reads the same forward and backward)
 * 3. Use String methods and arrays to implement these features
 * 4. Create separate methods for each functionality
 */
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // TODO: Prompt the user to enter a text
        
        // TODO: Call methods to analyze the text
        
        // TODO: Display the results of the analysis
        
        // Don't forget to close the scanner
        scanner.close();
    }
    
    // TODO: Implement a method to count words
    
    // TODO: Implement a method to count characters (excluding spaces)
    
    // TODO: Implement a method to find the most frequent word
    
    // TODO: Implement a method to convert text to title case
    
    // TODO: Implement a method to check if the text is a palindrome
}

// Expected output (example):
// Enter a text: The quick brown fox jumps over the lazy dog. The dog was not amused.
// 
// Text Analysis:
// Word count: 13
// Character count (excluding spaces): 51
// Most frequent word: "the" (appears 2 times)
// Title case: "The Quick Brown Fox Jumps Over The Lazy Dog. The Dog Was Not Amused."
// Is palindrome: false
// 
// Enter a text: Madam, I'm Adam.
// 
// Text Analysis:
// Word count: 3
// Character count (excluding spaces): 12
// Most frequent word: All words appear once
// Title case: "Madam, I'm Adam."
// Is palindrome: true (ignoring spaces, punctuation, and case)
