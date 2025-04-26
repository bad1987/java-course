/**
 * Exercise 2: Banking System
 * 
 * Instructions:
 * 1. Create an abstract class called BankAccount with the following:
 *    - Properties: accountNumber, accountHolder, balance
 *    - Constructor to initialize all properties
 *    - Abstract method: calculateInterest() that returns the monthly interest
 *    - Concrete methods:
 *      * deposit(double amount): adds amount to balance
 *      * withdraw(double amount): subtracts amount from balance if sufficient funds
 *      * getBalance(): returns the current balance
 *      * displayInfo(): prints account information
 * 
 * 2. Create the following concrete subclasses of BankAccount:
 *    - SavingsAccount: with interestRate as an additional property
 *      * Implements calculateInterest() to return balance * interestRate / 12
 *      * Overrides withdraw() to allow only a limited number of withdrawals per month
 * 
 *    - CheckingAccount: with transactionFee as an additional property
 *      * Implements calculateInterest() to return a fixed amount (e.g., $5)
 *      * Overrides withdraw() to deduct the transaction fee for each withdrawal
 * 
 *    - FixedDepositAccount: with lockPeriodMonths as an additional property
 *      * Implements calculateInterest() to return a higher interest rate
 *      * Overrides withdraw() to prevent withdrawals before the lock period ends
 * 
 * 3. Create a Bank class with the following:
 *    - An array to store multiple bank accounts
 *    - Methods to add accounts, find accounts by number, and calculate total balance
 *    - Method to apply monthly interest to all accounts
 * 
 * 4. In the main method:
 *    - Create several accounts of different types
 *    - Perform various operations (deposit, withdraw, etc.)
 *    - Display account information
 *    - Apply monthly interest and show updated balances
 */
public class Exercise2 {
    public static void main(String[] args) {
        // TODO: Create a Bank object
        
        // TODO: Create different types of accounts
        
        // TODO: Add accounts to the bank
        
        // TODO: Perform operations on the accounts
        
        // TODO: Display account information
        
        // TODO: Apply monthly interest and show updated balances
    }
}

// TODO: Create the abstract BankAccount class

// TODO: Create the SavingsAccount class

// TODO: Create the CheckingAccount class

// TODO: Create the FixedDepositAccount class

// TODO: Create the Bank class
