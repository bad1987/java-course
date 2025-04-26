/**
 * Exercise 4: Number Guessing Game
 * 
 * Instructions:
 * 1. Create a number guessing game where the computer generates a random number
 *    between 1 and 100, and the user tries to guess it
 * 2. After each guess, tell the user if their guess is:
 *    - Too high
 *    - Too low
 *    - Correct
 * 3. Keep track of the number of guesses the user makes
 * 4. When the user guesses correctly, display a congratulatory message and the
 *    number of guesses it took
 * 5. Allow the user to play again if they want
 */
import java.util.Scanner;
import java.util.Random;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain = true;
        
        while (playAgain) {
            // TODO: Generate a random number between 1 and 100
            
            // TODO: Initialize a counter for the number of guesses
            
            // TODO: Create a loop that continues until the user guesses correctly
            
            // TODO: Inside the loop, prompt the user to enter a guess
            
            // TODO: Read the guess as an integer
            
            // TODO: Increment the guess counter
            
            // TODO: Check if the guess is correct, too high, or too low and provide feedback
            
            // TODO: After the user guesses correctly, display a congratulatory message
            //       and the number of guesses it took
            
            // TODO: Ask the user if they want to play again
            
            // TODO: Read the user's response and update the playAgain variable
        }
        
        // Don't forget to close the scanner
        scanner.close();
    }
}

// Expected output (example):
// I'm thinking of a number between 1 and 100.
// Enter your guess: 50
// Too high! Try again.
// Enter your guess: 25
// Too low! Try again.
// Enter your guess: 37
// Correct! You guessed the number in 3 tries.
// Do you want to play again? (yes/no): no
// Thanks for playing!
