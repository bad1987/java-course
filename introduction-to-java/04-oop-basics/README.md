# Introduction to Object-Oriented Programming in Java

Welcome to the fourth lesson in our Java programming course! In this section, you'll learn about Object-Oriented Programming (OOP), a programming paradigm that uses "objects" to design applications and organize code. Java is fundamentally an object-oriented language, so understanding these concepts is essential for becoming proficient in Java programming.

## 1. Classes and Objects

Object-Oriented Programming revolves around two main concepts: classes and objects.

### What is a Class?

A class is a blueprint or template that defines the properties (attributes) and behaviors (methods) that objects of that type will have. Think of a class as a "type" of thing.

```java
// Basic class definition
public class Car {
    // Attributes (also called fields or instance variables)
    String make;
    String model;
    int year;
    String color;
    double fuelLevel;
    
    // Methods (behaviors)
    public void drive() {
        System.out.println("The " + color + " " + make + " " + model + " is driving.");
        fuelLevel -= 0.1; // Reduce fuel level when driving
    }
    
    public void refuel() {
        System.out.println("Refueling the car.");
        fuelLevel = 1.0; // Full tank
    }
}
```

### What is an Object?

An object is an instance of a class. If a class is a blueprint, an object is the actual "thing" built from that blueprint. You can create multiple objects from a single class, each with its own set of attribute values.

```java
// Creating objects (instances) of the Car class
Car myCar = new Car();
Car friendsCar = new Car();

// Setting attributes for myCar
myCar.make = "Toyota";
myCar.model = "Corolla";
myCar.year = 2020;
myCar.color = "Blue";
myCar.fuelLevel = 0.5;

// Setting attributes for friendsCar
friendsCar.make = "Honda";
friendsCar.model = "Civic";
friendsCar.year = 2019;
friendsCar.color = "Red";
friendsCar.fuelLevel = 0.75;

// Using object methods
myCar.drive();      // Output: The Blue Toyota Corolla is driving.
friendsCar.refuel(); // Output: Refueling the car.
```

## 2. Constructors

A constructor is a special method that is called when an object is created. It's used to initialize the object's attributes and perform any setup required for the object to be in a valid state.

### Default Constructor

If you don't define any constructors, Java provides a default constructor that takes no arguments and initializes attributes to their default values.

### Custom Constructors

You can define your own constructors to initialize objects with specific values:

```java
public class Car {
    String make;
    String model;
    int year;
    String color;
    double fuelLevel;
    
    // Constructor with no parameters
    public Car() {
        make = "Unknown";
        model = "Unknown";
        year = 2023;
        color = "White";
        fuelLevel = 0.0;
    }
    
    // Constructor with parameters
    public Car(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.fuelLevel = 1.0; // New cars come with a full tank
    }
    
    // Methods
    public void drive() {
        System.out.println("The " + color + " " + make + " " + model + " is driving.");
        fuelLevel -= 0.1;
    }
    
    public void refuel() {
        System.out.println("Refueling the car.");
        fuelLevel = 1.0;
    }
}
```

### Using Constructors

```java
// Using the no-parameter constructor
Car defaultCar = new Car();
System.out.println(defaultCar.make); // Output: Unknown

// Using the parameterized constructor
Car customCar = new Car("Tesla", "Model 3", 2022, "Black");
System.out.println(customCar.make); // Output: Tesla
```

## 3. Access Modifiers

Access modifiers control the visibility and accessibility of classes, attributes, and methods.

### Types of Access Modifiers

1. **public**: Accessible from any class
2. **private**: Accessible only within the same class
3. **protected**: Accessible within the same package and by subclasses
4. **default** (no modifier): Accessible only within the same package

### Example with Access Modifiers

```java
public class BankAccount {
    // Private attributes - can only be accessed within this class
    private String accountNumber;
    private double balance;
    private String ownerName;
    
    // Public constructor
    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }
    
    // Public methods - interface for interacting with the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
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
}
```

### Using the BankAccount Class

```java
BankAccount account = new BankAccount("123456789", "John Doe");

// These would cause compilation errors because the attributes are private
// account.accountNumber = "987654321";
// account.balance = 1000000;

// Instead, we use the public methods
account.deposit(500);
System.out.println(account.getBalance()); // Output: 500.0
account.withdraw(200);
System.out.println(account.getBalance()); // Output: 300.0
System.out.println(account.getAccountInfo());
```

## 4. Encapsulation

Encapsulation is the practice of hiding the internal state of an object and requiring all interaction to be performed through an object's methods. This is typically achieved by making attributes private and providing public getter and setter methods.

### Benefits of Encapsulation

1. **Data Hiding**: Internal representation of an object is hidden
2. **Increased Flexibility**: Implementation can change without affecting the public interface
3. **Improved Maintainability**: Control over how data is accessed and modified
4. **Data Validation**: Can validate data before changing attributes

### Example of Encapsulation

```java
public class Person {
    // Private attributes
    private String name;
    private int age;
    private String email;
    
    // Constructor
    public Person(String name, int age, String email) {
        this.name = name;
        setAge(age); // Using the setter for validation
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
            System.out.println("Invalid name.");
        }
    }
    
    public void setAge(int age) {
        if (age >= 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Age must be between 0 and 120.");
        }
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email format.");
        }
    }
    
    // Other methods
    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", Email: " + email;
    }
}
```

### Using the Person Class

```java
Person person = new Person("Alice", 30, "alice@example.com");
System.out.println(person.getDetails());

// Using getters
System.out.println("Name: " + person.getName());

// Using setters with validation
person.setAge(150); // Output: Invalid age. Age must be between 0 and 120.
person.setEmail("invalid-email"); // Output: Invalid email format.

person.setAge(35); // Valid age
System.out.println("New age: " + person.getAge()); // Output: 35
```

## 5. The `this` Keyword

The `this` keyword refers to the current object instance. It's commonly used to:

1. Distinguish between instance variables and parameters with the same name
2. Call another constructor in the same class
3. Pass the current object as a parameter to another method

### Example of `this` Keyword

```java
public class Rectangle {
    private double length;
    private double width;
    
    // Constructor with parameters that have the same names as instance variables
    public Rectangle(double length, double width) {
        // Using 'this' to refer to instance variables
        this.length = length;
        this.width = width;
    }
    
    // Constructor that calls another constructor
    public Rectangle() {
        // Call the other constructor with default values
        this(1.0, 1.0);
    }
    
    // Method that uses 'this' to pass the current object
    public void printArea() {
        double area = calculateArea(this);
        System.out.println("Area: " + area);
    }
    
    private double calculateArea(Rectangle rect) {
        return rect.length * rect.width;
    }
    
    // Getters and setters
    public double getLength() {
        return length;
    }
    
    public void setLength(double length) {
        this.length = length;
    }
    
    public double getWidth() {
        return width;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
}
```

## 6. Static Members

Static members (variables and methods) belong to the class rather than to any specific instance of the class. They are shared among all instances of the class.

### Static Variables

Static variables (also called class variables) are shared by all instances of a class. They are initialized only once, at the start of the execution.

### Static Methods

Static methods belong to the class rather than to any specific instance. They can only access static variables and call other static methods directly.

### Example of Static Members

```java
public class MathUtils {
    // Static variable
    public static final double PI = 3.14159265359;
    
    // Static counter to track how many times methods are called
    private static int operationCount = 0;
    
    // Static method
    public static double calculateCircleArea(double radius) {
        operationCount++;
        return PI * radius * radius;
    }
    
    public static double calculateRectangleArea(double length, double width) {
        operationCount++;
        return length * width;
    }
    
    // Static method to get the operation count
    public static int getOperationCount() {
        return operationCount;
    }
}
```

### Using Static Members

```java
// Using static methods without creating an instance
double circleArea = MathUtils.calculateCircleArea(5.0);
System.out.println("Circle area: " + circleArea);

double rectangleArea = MathUtils.calculateRectangleArea(4.0, 6.0);
System.out.println("Rectangle area: " + rectangleArea);

// Accessing static variable
System.out.println("Value of PI: " + MathUtils.PI);

// Getting the operation count
System.out.println("Operations performed: " + MathUtils.getOperationCount());
```

## 7. Object Relationships

Objects can have relationships with other objects. The most common types of relationships are:

### Association

A relationship where objects are aware of each other but have independent lifecycles.

```java
public class Student {
    private String name;
    private Course[] courses;
    
    // Methods to add/remove courses
}

public class Course {
    private String name;
    private Student[] students;
    
    // Methods to add/remove students
}
```

### Aggregation

A special form of association where one object "has" another object, but the contained object can exist independently.

```java
public class Department {
    private String name;
    private Employee[] employees;
    
    // Methods to add/remove employees
}

public class Employee {
    private String name;
    // Employee can exist without being in a department
}
```

### Composition

A stronger form of aggregation where the contained object cannot exist without the container object.

```java
public class Car {
    private Engine engine;
    
    public Car() {
        // Engine is created when Car is created
        engine = new Engine();
    }
}

public class Engine {
    // Engine cannot exist without a Car
}
```

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `ClassesAndObjects.java`: Basic class and object creation
- `ConstructorDemo.java`: Different types of constructors
- `AccessModifiersDemo.java`: Using access modifiers
- `EncapsulationDemo.java`: Implementing encapsulation with getters and setters
- `ThisKeywordDemo.java`: Using the `this` keyword
- `StaticMembersDemo.java`: Working with static variables and methods
- `ObjectRelationshipsDemo.java`: Demonstrating different object relationships

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Create a `Book` class with appropriate attributes and methods
2. `Exercise2.java`: Implement a `BankAccount` class with proper encapsulation
3. `Exercise3.java`: Design a `Student` and `Course` system with object relationships
4. `Exercise4.java`: Create a utility class with static methods for common operations

Complete these exercises to practice what you've learned. Remember, object-oriented programming is best learned through practice and application!

## Next Steps

After completing this section, proceed to the "Advanced OOP Concepts" section to learn about inheritance, polymorphism, abstract classes, and interfaces. These concepts will build on the foundation you've established with basic OOP principles.
