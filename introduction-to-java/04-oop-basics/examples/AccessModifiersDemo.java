/**
 * AccessModifiersDemo.java
 * This program demonstrates access modifiers in Java.
 */
public class AccessModifiersDemo {
    public static void main(String[] args) {
        System.out.println("--- Public Access ---");
        
        // Create a BankAccount object
        BankAccount account = new BankAccount("123456789", "John Doe");
        
        // These would cause compilation errors because the attributes are private
        // account.accountNumber = "987654321";  // Error: accountNumber has private access
        // account.balance = 1000000;            // Error: balance has private access
        
        // Instead, we use the public methods
        account.deposit(500);
        System.out.println("Balance after deposit: $" + account.getBalance());
        
        account.withdraw(200);
        System.out.println("Balance after withdrawal: $" + account.getBalance());
        
        System.out.println(account.getAccountInfo());
        
        System.out.println("\n--- Private Methods ---");
        
        // This would cause a compilation error because the method is private
        // account.updateTransactionCount();  // Error: updateTransactionCount() has private access
        
        // We can only call public methods
        account.deposit(100);
        System.out.println("Transaction count: " + account.getTransactionCount());
        
        System.out.println("\n--- Default Access ---");
        
        // Create a Product object
        Product product = new Product("Laptop", 999.99);
        
        // We can access methods with default access from the same package
        product.displayInfo();
        product.applyDiscount(10);
        product.displayInfo();
        
        System.out.println("\n--- Protected Access ---");
        
        // Create a Vehicle object
        Vehicle vehicle = new Vehicle("Toyota", "Camry");
        
        // Protected members are accessible in the same package
        vehicle.startEngine();
        vehicle.displayInfo();
        
        // Create a Car object (subclass of Vehicle)
        Car car = new Car("Honda", "Civic", 4);
        car.startEngine();  // Inherited protected method
        car.displayInfo();  // Overridden method
    }
}

/**
 * A BankAccount class to demonstrate private access modifier.
 */
class BankAccount {
    // Private attributes - can only be accessed within this class
    private String accountNumber;
    private double balance;
    private String ownerName;
    private int transactionCount;
    
    // Public constructor
    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
        this.transactionCount = 0;
    }
    
    // Public methods - interface for interacting with the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
            updateTransactionCount();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            updateTransactionCount();
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountInfo() {
        return "Account: " + accountNumber + ", Owner: " + ownerName + ", Balance: $" + balance;
    }
    
    public int getTransactionCount() {
        return transactionCount;
    }
    
    // Private method - can only be called from within this class
    private void updateTransactionCount() {
        transactionCount++;
        System.out.println("Transaction recorded. Total transactions: " + transactionCount);
    }
}

/**
 * A Product class to demonstrate default access modifier.
 */
class Product {
    // Default access attributes (no modifier)
    String name;
    double price;
    
    // Default access constructor
    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    // Default access method
    void displayInfo() {
        System.out.println("Product: " + name + ", Price: $" + price);
    }
    
    // Default access method
    void applyDiscount(double percentDiscount) {
        if (percentDiscount > 0 && percentDiscount <= 100) {
            price = price * (1 - percentDiscount / 100);
            System.out.println("Applied " + percentDiscount + "% discount.");
        } else {
            System.out.println("Invalid discount percentage.");
        }
    }
}

/**
 * A Vehicle class to demonstrate protected access modifier.
 */
class Vehicle {
    // Protected attributes - accessible in this class, subclasses, and same package
    protected String make;
    protected String model;
    protected boolean engineRunning;
    
    // Constructor
    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
        this.engineRunning = false;
    }
    
    // Protected method
    protected void startEngine() {
        engineRunning = true;
        System.out.println("Engine started.");
    }
    
    // Public method
    public void displayInfo() {
        System.out.println("Vehicle: " + make + " " + model);
        System.out.println("Engine running: " + engineRunning);
    }
}

/**
 * A Car class that extends Vehicle to demonstrate inheritance with protected members.
 */
class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String make, String model, int numberOfDoors) {
        super(make, model);  // Call the parent class constructor
        this.numberOfDoors = numberOfDoors;
    }
    
    // Override the displayInfo method
    @Override
    public void displayInfo() {
        System.out.println("Car: " + make + " " + model);  // Can access protected attributes
        System.out.println("Number of doors: " + numberOfDoors);
        System.out.println("Engine running: " + engineRunning);  // Can access protected attributes
    }
}
