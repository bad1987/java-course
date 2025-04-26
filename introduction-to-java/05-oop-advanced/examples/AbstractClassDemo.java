/**
 * AbstractClassDemo.java
 * This program demonstrates abstract classes in Java.
 */
public class AbstractClassDemo {
    public static void main(String[] args) {
        System.out.println("--- Abstract Classes and Methods ---");
        
        // Cannot instantiate an abstract class
        // Animal animal = new Animal("Generic");  // This would cause a compilation error
        
        // Create instances of concrete subclasses
        Dog dog = new Dog("Buddy");
        Cat cat = new Cat("Whiskers");
        
        // Call abstract methods (implemented by subclasses)
        dog.makeSound();
        cat.makeSound();
        
        // Call concrete methods from the abstract class
        dog.eat();
        cat.sleep();
        
        System.out.println("\n--- Abstract Class as a Type ---");
        
        // Use the abstract class as a type
        Animal dogAsAnimal = dog;
        Animal catAsAnimal = cat;
        
        // Call methods polymorphically
        dogAsAnimal.makeSound();
        catAsAnimal.makeSound();
        
        System.out.println("\n--- Abstract Shape Example ---");
        
        // Create concrete shape objects
        Circle circle = new Circle(5.0);
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        Triangle triangle = new Triangle(3.0, 4.0);
        
        // Display information about each shape
        displayShapeInfo(circle);
        displayShapeInfo(rectangle);
        displayShapeInfo(triangle);
        
        System.out.println("\n--- Abstract Class with Constructors ---");
        
        // Create a vehicle
        Car car = new Car("Toyota", "Corolla", 4);
        car.start();
        car.stop();
        car.displayInfo();
        
        System.out.println("\n--- Abstract Class Hierarchy ---");
        
        // Create employees
        Manager manager = new Manager("John Smith", 50000, "Sales");
        Developer developer = new Developer("Jane Doe", 60000, "Java");
        
        // Process payroll for different employee types
        processPayroll(manager);
        processPayroll(developer);
    }
    
    // Method that takes an abstract Shape parameter
    public static void displayShapeInfo(Shape shape) {
        System.out.println("Shape type: " + shape.getClass().getSimpleName());
        System.out.println("Area: " + shape.calculateArea());
        System.out.println("Perimeter: " + shape.calculatePerimeter());
        shape.draw();
        System.out.println();
    }
    
    // Method that takes an abstract Employee parameter
    public static void processPayroll(Employee employee) {
        System.out.println("Processing payroll for: " + employee.getName());
        double salary = employee.calculateSalary();
        System.out.println("Monthly salary: $" + salary);
        employee.displayInfo();
        System.out.println();
    }
}

/**
 * Abstract Animal class.
 */
abstract class Animal {
    protected String name;
    
    // Abstract classes can have constructors
    public Animal(String name) {
        this.name = name;
    }
    
    // Abstract method (no implementation)
    public abstract void makeSound();
    
    // Concrete methods (with implementation)
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    // Getter for name
    public String getName() {
        return name;
    }
}

/**
 * Concrete Dog class extending Animal.
 */
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    // Must implement the abstract method
    @Override
    public void makeSound() {
        System.out.println(name + " says Woof!");
    }
}

/**
 * Concrete Cat class extending Animal.
 */
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    // Must implement the abstract method
    @Override
    public void makeSound() {
        System.out.println(name + " says Meow!");
    }
}

/**
 * Abstract Shape class.
 */
abstract class Shape {
    // Abstract methods
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    public abstract void draw();
    
    // Concrete method
    public void displayArea() {
        System.out.println("Area: " + calculateArea());
    }
}

/**
 * Concrete Circle class extending Shape.
 */
class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}

/**
 * Concrete Rectangle class extending Shape.
 */
class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with length " + length + " and width " + width);
    }
}

/**
 * Concrete Triangle class extending Shape.
 */
class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
    
    @Override
    public double calculatePerimeter() {
        // Assuming a right triangle for simplicity
        double hypotenuse = Math.sqrt(base * base + height * height);
        return base + height + hypotenuse;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a triangle with base " + base + " and height " + height);
    }
}

/**
 * Abstract Vehicle class with constructor.
 */
abstract class Vehicle {
    protected String make;
    protected String model;
    
    // Abstract classes can have constructors
    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }
    
    // Abstract methods
    public abstract void start();
    public abstract void stop();
    
    // Concrete method
    public void displayInfo() {
        System.out.println("Vehicle: " + make + " " + model);
    }
}

/**
 * Concrete Car class extending Vehicle.
 */
class Car extends Vehicle {
    private int numDoors;
    
    public Car(String make, String model, int numDoors) {
        super(make, model);
        this.numDoors = numDoors;
    }
    
    @Override
    public void start() {
        System.out.println("Starting the car's engine");
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping the car's engine");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of doors: " + numDoors);
    }
}

/**
 * Abstract Employee class for payroll processing.
 */
abstract class Employee {
    protected String name;
    protected double baseSalary;
    
    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }
    
    // Abstract method for salary calculation
    public abstract double calculateSalary();
    
    // Abstract method for displaying employee info
    public abstract void displayInfo();
    
    // Concrete method
    public String getName() {
        return name;
    }
}

/**
 * Concrete Manager class extending Employee.
 */
class Manager extends Employee {
    private String department;
    
    public Manager(String name, double baseSalary, String department) {
        super(name, baseSalary);
        this.department = department;
    }
    
    @Override
    public double calculateSalary() {
        // Managers get a 20% bonus
        return baseSalary * 1.2;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Manager: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: $" + baseSalary);
    }
}

/**
 * Concrete Developer class extending Employee.
 */
class Developer extends Employee {
    private String programmingLanguage;
    
    public Developer(String name, double baseSalary, String programmingLanguage) {
        super(name, baseSalary);
        this.programmingLanguage = programmingLanguage;
    }
    
    @Override
    public double calculateSalary() {
        // Developers get a 10% bonus
        return baseSalary * 1.1;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Developer: " + name);
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Base Salary: $" + baseSalary);
    }
}
