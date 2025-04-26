/**
 * EncapsulationDemo.java
 * This program demonstrates encapsulation in Java.
 */
public class EncapsulationDemo {
    public static void main(String[] args) {
        System.out.println("--- Encapsulation with Person Class ---");
        
        // Create a Person object
        Person person = new Person("Alice", 30, "alice@example.com");
        
        // Display initial details
        System.out.println("Initial details:");
        System.out.println(person.getDetails());
        
        System.out.println("\n--- Using Getters ---");
        
        // Access attributes using getter methods
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Email: " + person.getEmail());
        
        System.out.println("\n--- Using Setters with Validation ---");
        
        // Try to set invalid values
        System.out.println("Attempting to set invalid age (150):");
        person.setAge(150);  // Should print error message
        
        System.out.println("\nAttempting to set invalid email (invalid-email):");
        person.setEmail("invalid-email");  // Should print error message
        
        // Set valid values
        System.out.println("\nSetting valid values:");
        person.setName("Alice Smith");
        person.setAge(35);
        person.setEmail("alice.smith@example.com");
        
        // Display updated details
        System.out.println("\nUpdated details:");
        System.out.println(person.getDetails());
        
        System.out.println("\n--- Employee Example ---");
        
        // Create an Employee object
        Employee employee = new Employee("E12345", "Bob Johnson", 50000);
        
        // Display initial details
        System.out.println("Initial employee details:");
        employee.displayInfo();
        
        // Try to set invalid salary
        System.out.println("\nAttempting to set negative salary:");
        employee.setSalary(-5000);  // Should print error message
        
        // Set valid salary and give raise
        System.out.println("\nGiving employee a raise:");
        employee.setSalary(55000);
        employee.displayInfo();
        
        System.out.println("\n--- Product Inventory Example ---");
        
        // Create a Product object
        ProductWithEncapsulation product = new ProductWithEncapsulation("Laptop", 999.99, 10);
        
        // Display initial details
        System.out.println("Initial product details:");
        product.displayInfo();
        
        // Try to update stock
        System.out.println("\nAttempting to set negative stock:");
        product.setStock(-5);  // Should print error message
        
        System.out.println("\nAdding 5 items to stock:");
        product.addStock(5);
        product.displayInfo();
        
        System.out.println("\nRemoving 3 items from stock:");
        product.removeStock(3);
        product.displayInfo();
        
        System.out.println("\nAttempting to remove more items than in stock:");
        product.removeStock(15);  // Should print error message
    }
}

/**
 * A Person class demonstrating encapsulation with private attributes and public getters/setters.
 */
class Person {
    // Private attributes
    private String name;
    private int age;
    private String email;
    
    // Constructor
    public Person(String name, int age, String email) {
        this.name = name;
        setAge(age);  // Using the setter for validation
        this.email = email;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Setter methods with validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Invalid name.");
        }
    }
    
    public void setAge(int age) {
        if (age >= 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("Error: Invalid age. Age must be between 0 and 120.");
        }
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Error: Invalid email format.");
        }
    }
    
    // Other methods
    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", Email: " + email;
    }
}

/**
 * An Employee class demonstrating encapsulation with salary information.
 */
class Employee {
    private String employeeId;
    private String name;
    private double salary;
    
    public Employee(String employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        setSalary(salary);
    }
    
    // Getters
    public String getEmployeeId() {
        return employeeId;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // Setters
    public void setEmployeeId(String employeeId) {
        if (employeeId != null && !employeeId.isEmpty()) {
            this.employeeId = employeeId;
        } else {
            System.out.println("Error: Invalid employee ID.");
        }
    }
    
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Invalid name.");
        }
    }
    
    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary must be positive.");
        }
    }
    
    // Display employee information
    public void displayInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + salary);
    }
}

/**
 * A Product class demonstrating encapsulation with inventory management.
 */
class ProductWithEncapsulation {
    private String name;
    private double price;
    private int stock;
    
    public ProductWithEncapsulation(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        setStock(stock);
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getStock() {
        return stock;
    }
    
    // Setters
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Invalid product name.");
        }
    }
    
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Error: Price cannot be negative.");
        }
    }
    
    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("Error: Stock cannot be negative.");
        }
    }
    
    // Additional methods for inventory management
    public void addStock(int quantity) {
        if (quantity > 0) {
            stock += quantity;
            System.out.println("Added " + quantity + " items to stock.");
        } else {
            System.out.println("Error: Quantity to add must be positive.");
        }
    }
    
    public void removeStock(int quantity) {
        if (quantity > 0) {
            if (quantity <= stock) {
                stock -= quantity;
                System.out.println("Removed " + quantity + " items from stock.");
            } else {
                System.out.println("Error: Not enough items in stock.");
            }
        } else {
            System.out.println("Error: Quantity to remove must be positive.");
        }
    }
    
    // Display product information
    public void displayInfo() {
        System.out.println("Product: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stock + " units");
    }
}
