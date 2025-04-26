/**
 * ConstructorDemo.java
 * This program demonstrates constructors in Java classes.
 */
public class ConstructorDemo {
    public static void main(String[] args) {
        System.out.println("--- Default Constructor ---");
        
        // Create a Car object using the default constructor
        CarWithConstructors car1 = new CarWithConstructors();
        
        // Display default values
        System.out.println("Default car details:");
        car1.displayDetails();
        
        System.out.println("\n--- Parameterized Constructor ---");
        
        // Create a Car object using the parameterized constructor
        CarWithConstructors car2 = new CarWithConstructors("Tesla", "Model 3", 2022, "Black");
        
        // Display custom values
        System.out.println("Custom car details:");
        car2.displayDetails();
        
        System.out.println("\n--- Constructor with Partial Parameters ---");
        
        // Create a Car object using the constructor with partial parameters
        CarWithConstructors car3 = new CarWithConstructors("Ford", "Mustang");
        
        // Display values
        System.out.println("Partial custom car details:");
        car3.displayDetails();
        
        System.out.println("\n--- Student Example ---");
        
        // Create Student objects using different constructors
        Student student1 = new Student();
        Student student2 = new Student("Alice Johnson");
        Student student3 = new Student("Bob Smith", 20);
        Student student4 = new Student("Charlie Brown", 22, "Computer Science");
        
        // Display student information
        System.out.println("Student 1:");
        student1.displayInfo();
        
        System.out.println("\nStudent 2:");
        student2.displayInfo();
        
        System.out.println("\nStudent 3:");
        student3.displayInfo();
        
        System.out.println("\nStudent 4:");
        student4.displayInfo();
    }
}

/**
 * A Car class with different constructors.
 */
class CarWithConstructors {
    // Attributes
    String make;
    String model;
    int year;
    String color;
    double fuelLevel;
    
    // Default constructor (no parameters)
    public CarWithConstructors() {
        make = "Unknown";
        model = "Unknown";
        year = 2023;
        color = "White";
        fuelLevel = 0.0;
        System.out.println("Default constructor called");
    }
    
    // Constructor with all parameters
    public CarWithConstructors(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.fuelLevel = 1.0; // New cars come with a full tank
        System.out.println("Full parameterized constructor called");
    }
    
    // Constructor with partial parameters
    public CarWithConstructors(String make, String model) {
        this.make = make;
        this.model = model;
        this.year = 2023; // Default current year
        this.color = "Silver"; // Default color
        this.fuelLevel = 0.5; // Half tank
        System.out.println("Partial parameterized constructor called");
    }
    
    // Method to display car details
    public void displayDetails() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Fuel Level: " + fuelLevel);
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

/**
 * A Student class with different constructors to demonstrate constructor overloading.
 */
class Student {
    // Attributes
    String name;
    int age;
    String major;
    
    // Default constructor
    public Student() {
        name = "Unknown";
        age = 18;
        major = "Undeclared";
        System.out.println("Student default constructor called");
    }
    
    // Constructor with name only
    public Student(String name) {
        this.name = name;
        age = 18;
        major = "Undeclared";
        System.out.println("Student constructor with name called");
    }
    
    // Constructor with name and age
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        major = "Undeclared";
        System.out.println("Student constructor with name and age called");
    }
    
    // Constructor with all parameters
    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
        System.out.println("Student constructor with all parameters called");
    }
    
    // Method to display student information
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Major: " + major);
    }
}
