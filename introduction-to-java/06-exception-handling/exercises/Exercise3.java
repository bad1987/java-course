/**
 * Exercise 3: Banking System with Custom Exceptions
 * 
 * Instructions:
 * 1. Create a BankAccount class with the following attributes and methods:
 *    - accountNumber (String)
 *    - accountHolder (String)
 *    - balance (double)
 *    - Constructor to initialize all attributes
 *    - getBalance(): Returns the current balance
 *    - deposit(double amount): Adds amount to balance
 *    - withdraw(double amount): Subtracts amount from balance if sufficient funds
 *    - transfer(BankAccount destination, double amount): Transfers amount to another account
 * 
 * 2. Create the following custom exceptions:
 *    - InsufficientFundsException: Thrown when trying to withdraw more than the balance
 *    - NegativeAmountException: Thrown when trying to deposit or withdraw a negative amount
 *    - AccountNotFoundException: Thrown when trying to find an account that doesn't exist
 *    - InvalidAccountException: Thrown when an account number is invalid
 *    - DailyLimitExceededException: Thrown when a withdrawal exceeds the daily limit
 * 
 * 3. Create a Bank class that:
 *    - Maintains a collection of BankAccount objects
 *    - Has methods to add accounts, find accounts by number, and perform transactions
 *    - Implements proper exception handling for all operations
 *    - Has a daily withdrawal limit per account
 * 
 * 4. Create a BankingApp class with a main method that:
 *    - Creates a Bank object
 *    - Adds several accounts
 *    - Provides a menu for the user to:
 *      * View account details
 *      * Deposit money
 *      * Withdraw money
 *      * Transfer money between accounts
 *    - Handles all potential exceptions with appropriate error messages
 * 
 * 5. Implement a transaction log that records all successful transactions and errors
 * 
 * 6. Create a hierarchy of banking exceptions with a base BankingException class
 *    that all other custom exceptions extend.
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Exercise3 {
    public static void main(String[] args) {
        // TODO: Implement the BankingApp
    }
}

// TODO: Implement the BankAccount class

// TODO: Implement the Bank class

// TODO: Implement the custom exception classes
