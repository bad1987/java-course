/**
 * FinalKeywordDemo.java
 * This program demonstrates the use of the 'final' keyword in Java.
 */
public class FinalKeywordDemo {
    public static void main(String[] args) {
        System.out.println("--- Final Variables ---");
        
        // Create an object with final variables
        Circle circle = new Circle(5.0);
        
        // Cannot modify the value of a final variable
        // circle.PI = 3.14;  // This would cause a compilation error
        
        System.out.println("Circle radius: " + circle.getRadius());
        System.out.println("Circle area: " + circle.calculateArea());
        
        System.out.println("\n--- Final Reference Variables ---");
        
        // Final reference variable
        final StringBuilder builder = new StringBuilder("Hello");
        
        // Can modify the object's state
        builder.append(" World");
        System.out.println("StringBuilder content: " + builder.toString());
        
        // Cannot reassign the reference
        // builder = new StringBuilder("New reference");  // This would cause a compilation error
        
        System.out.println("\n--- Final Method Parameters ---");
        
        // Create a person
        Person person = new Person("John", 30);
        
        // Call method with final parameter
        person.haveBirthday();
        System.out.println("Person's age after birthday: " + person.getAge());
        
        System.out.println("\n--- Final Methods ---");
        
        // Create a vehicle
        Car car = new Car("Toyota", "Corolla");
        
        // Call final method
        car.displayInfo();
        
        // Create a subclass object
        ElectricCar electricCar = new ElectricCar("Tesla", "Model 3");
        
        // Call inherited final method
        electricCar.displayInfo();
        
        System.out.println("\n--- Final Classes ---");
        
        // Create an immutable string wrapper
        ImmutableString immutableString = new ImmutableString("Hello, Java!");
        
        // Access the value
        System.out.println("Immutable string value: " + immutableString.getValue());
        
        // Cannot extend a final class
        // class ExtendedString extends ImmutableString { }  // This would cause a compilation error
        
        System.out.println("\n--- Constants with Final ---");
        
        // Access constants from the Constants class
        System.out.println("Maximum users: " + Constants.MAX_USERS);
        System.out.println("Default timeout: " + Constants.DEFAULT_TIMEOUT + " seconds");
        System.out.println("Application name: " + Constants.APP_NAME);
        
        // Cannot modify constants
        // Constants.MAX_USERS = 200;  // This would cause a compilation error
    }
}

/**
 * Class with final variables.
 */
class Circle {
    // Final constant (convention: uppercase with underscores)
    public static final double PI = 3.14159265359;
    
    // Final instance variable
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;  // Final variable must be initialized exactly once
    }
    
    public double getRadius() {
        return radius;
    }
    
    public double calculateArea() {
        return PI * radius * radius;
    }
}

/**
 * Class with final method parameters.
 */
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Method with final parameter
    public void updateName(final String newName) {
        // Cannot modify the final parameter
        // newName = newName.toUpperCase();  // This would cause a compilation error
        
        this.name = newName;  // But we can use its value
    }
    
    public void haveBirthday() {
        incrementAge(this.age);  // Pass current age to the method
    }
    
    // Method with final parameter
    private void incrementAge(final int currentAge) {
        // Cannot modify the final parameter
        // currentAge++;  // This would cause a compilation error
        
        // But we can use its value to update the instance variable
        this.age = currentAge + 1;
    }
}

/**
 * Class with final methods.
 */
class Vehicle {
    protected String make;
    protected String model;
    
    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }
    
    // Final method that cannot be overridden by subclasses
    public final void displayInfo() {
        System.out.println("Vehicle: " + make + " " + model);
    }
    
    // Regular method that can be overridden
    public void start() {
        System.out.println("Vehicle starting");
    }
}

/**
 * Subclass that inherits a final method.
 */
class Car extends Vehicle {
    public Car(String make, String model) {
        super(make, model);
    }
    
    // Cannot override the final method
    // @Override
    // public void displayInfo() {  // This would cause a compilation error
    //     System.out.println("Car: " + make + " " + model);
    // }
    
    // Can override regular methods
    @Override
    public void start() {
        System.out.println("Car engine starting");
    }
}

/**
 * Another subclass that inherits a final method.
 */
class ElectricCar extends Vehicle {
    public ElectricCar(String make, String model) {
        super(make, model);
    }
    
    // Can override regular methods
    @Override
    public void start() {
        System.out.println("Electric car powering up");
    }
    
    // Can add new methods
    public void charge() {
        System.out.println("Electric car charging");
    }
}

/**
 * Final class that cannot be extended.
 */
final class ImmutableString {
    private final String value;
    
    public ImmutableString(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    // No setters to ensure immutability
}

/**
 * Class with constants.
 */
class Constants {
    // Private constructor to prevent instantiation
    private Constants() {
        // This class should not be instantiated
    }
    
    // Constants (public static final)
    public static final int MAX_USERS = 100;
    public static final int DEFAULT_TIMEOUT = 30;
    public static final String APP_NAME = "Demo Application";
    
    // Compile-time constants
    public static final int DAYS_IN_WEEK = 7;
    public static final double EARTH_RADIUS_KM = 6371.0;
    
    // Runtime constants
    public static final String CURRENT_DATE = java.time.LocalDate.now().toString();
}
