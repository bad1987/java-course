/**
 * CustomExceptionDemo.java
 * This program demonstrates creating and using custom exceptions in Java.
 */
public class CustomExceptionDemo {
    public static void main(String[] args) {
        System.out.println("--- Custom Exception Examples ---");
        
        // Example 1: Using a custom checked exception
        System.out.println("\nExample 1: Custom Checked Exception");
        BankAccount account1 = new BankAccount("123456", "John Doe", 1000.0);
        
        try {
            account1.withdraw(500.0);  // This should work
            System.out.println("Withdrawal successful. New balance: $" + account1.getBalance());
            
            account1.withdraw(800.0);  // This should throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Deficit amount: $" + e.getDeficit());
        }
        
        // Example 2: Using a custom unchecked exception
        System.out.println("\nExample 2: Custom Unchecked Exception");
        try {
            validateUsername("john_doe");  // This should work
            System.out.println("Username is valid");
            
            validateUsername("j");  // This should throw InvalidUsernameException
        } catch (InvalidUsernameException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Username validation error code: " + e.getErrorCode());
        }
        
        // Example 3: Exception with custom constructors
        System.out.println("\nExample 3: Exception with Custom Constructors");
        try {
            Product product = new Product("Laptop", 999.99);
            product.setPrice(-100.0);  // This should throw InvalidProductException
        } catch (InvalidProductException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Example 4: Hierarchical custom exceptions
        System.out.println("\nExample 4: Hierarchical Custom Exceptions");
        try {
            processPayment(500.0, "expired_card");
        } catch (PaymentException e) {
            System.out.println("Payment error: " + e.getMessage());
            
            if (e instanceof CardExpiredException) {
                System.out.println("Please update your card information");
            } else if (e instanceof InsufficientCreditException) {
                InsufficientCreditException ice = (InsufficientCreditException) e;
                System.out.println("Available credit: $" + ice.getAvailableCredit());
                System.out.println("Required amount: $" + ice.getRequiredAmount());
            }
        }
        
        // Example 5: Real-world application
        System.out.println("\nExample 5: Real-world Application");
        UserManager userManager = new UserManager();
        
        try {
            userManager.createUser("alice", "password123");  // This should work
            System.out.println("User created successfully");
            
            userManager.createUser("alice", "newpassword");  // This should throw UserAlreadyExistsException
        } catch (UserException e) {
            System.out.println("User error: " + e.getMessage());
            
            if (e instanceof UserAlreadyExistsException) {
                System.out.println("Please choose a different username");
            } else if (e instanceof InvalidPasswordException) {
                System.out.println("Password requirements: " + 
                                  ((InvalidPasswordException) e).getRequirements());
            }
        }
    }
    
    /**
     * Validates a username and throws a custom unchecked exception if invalid.
     */
    public static void validateUsername(String username) {
        if (username == null || username.length() < 3) {
            throw new InvalidUsernameException("Username must be at least 3 characters long", 1001);
        }
        
        if (username.contains(" ")) {
            throw new InvalidUsernameException("Username cannot contain spaces", 1002);
        }
    }
    
    /**
     * Simulates processing a payment with potential exceptions.
     */
    public static void processPayment(double amount, String cardStatus) throws PaymentException {
        if (cardStatus.equals("expired_card")) {
            throw new CardExpiredException("Your card has expired");
        }
        
        if (cardStatus.equals("insufficient_credit")) {
            double availableCredit = 300.0;  // Simulated available credit
            throw new InsufficientCreditException("Insufficient credit available", 
                                                 availableCredit, amount);
        }
        
        System.out.println("Payment processed successfully");
    }
}

/**
 * Custom checked exception for insufficient funds in a bank account.
 */
class InsufficientFundsException extends Exception {
    private double deficit;
    
    public InsufficientFundsException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }
    
    public double getDeficit() {
        return deficit;
    }
}

/**
 * Custom unchecked exception for invalid username.
 */
class InvalidUsernameException extends RuntimeException {
    private int errorCode;
    
    public InvalidUsernameException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
}

/**
 * Custom exception for invalid product data.
 */
class InvalidProductException extends Exception {
    // Constructor with just a message
    public InvalidProductException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public InvalidProductException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Base class for payment-related exceptions.
 */
class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}

/**
 * Exception for expired credit card.
 */
class CardExpiredException extends PaymentException {
    public CardExpiredException(String message) {
        super(message);
    }
}

/**
 * Exception for insufficient credit.
 */
class InsufficientCreditException extends PaymentException {
    private double availableCredit;
    private double requiredAmount;
    
    public InsufficientCreditException(String message, double availableCredit, double requiredAmount) {
        super(message);
        this.availableCredit = availableCredit;
        this.requiredAmount = requiredAmount;
    }
    
    public double getAvailableCredit() {
        return availableCredit;
    }
    
    public double getRequiredAmount() {
        return requiredAmount;
    }
}

/**
 * Base class for user-related exceptions.
 */
class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }
}

/**
 * Exception for when a user already exists.
 */
class UserAlreadyExistsException extends UserException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

/**
 * Exception for invalid password.
 */
class InvalidPasswordException extends UserException {
    private String requirements;
    
    public InvalidPasswordException(String message, String requirements) {
        super(message);
        this.requirements = requirements;
    }
    
    public String getRequirements() {
        return requirements;
    }
}

/**
 * A simple bank account class that uses custom exceptions.
 */
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        balance += amount;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (amount > balance) {
            double deficit = amount - balance;
            throw new InsufficientFundsException(
                "Insufficient funds for withdrawal of $" + amount, deficit);
        }
        
        balance -= amount;
    }
    
    public double getBalance() {
        return balance;
    }
}

/**
 * A product class that uses custom exceptions.
 */
class Product {
    private String name;
    private double price;
    
    public Product(String name, double price) throws InvalidProductException {
        if (name == null || name.isEmpty()) {
            throw new InvalidProductException("Product name cannot be empty");
        }
        
        if (price < 0) {
            throw new InvalidProductException("Product price cannot be negative");
        }
        
        this.name = name;
        this.price = price;
    }
    
    public void setPrice(double price) throws InvalidProductException {
        if (price < 0) {
            throw new InvalidProductException("Product price cannot be negative");
        }
        
        this.price = price;
    }
}

/**
 * A user manager class that uses custom exceptions.
 */
class UserManager {
    private String[] existingUsers = {"john", "mary", "bob"};
    
    public void createUser(String username, String password) throws UserException {
        // Check if user already exists
        for (String user : existingUsers) {
            if (user.equals(username)) {
                throw new UserAlreadyExistsException("User '" + username + "' already exists");
            }
        }
        
        // Validate password
        if (password.length() < 8) {
            throw new InvalidPasswordException(
                "Password is too short", 
                "Password must be at least 8 characters long");
        }
        
        // Create the user...
        System.out.println("Creating user: " + username);
    }
}
