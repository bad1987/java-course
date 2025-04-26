/**
 * ClassesAndObjects.java
 * This program demonstrates the basics of classes and objects in Java.
 */
public class ClassesAndObjects {
    public static void main(String[] args) {
        System.out.println("--- Creating and Using Objects ---");
        
        // Create a Car object
        Car myCar = new Car();
        
        // Set attributes
        myCar.make = "Toyota";
        myCar.model = "Corolla";
        myCar.year = 2020;
        myCar.color = "Blue";
        myCar.fuelLevel = 0.5;
        
        // Access attributes
        System.out.println("My car details:");
        System.out.println("Make: " + myCar.make);
        System.out.println("Model: " + myCar.model);
        System.out.println("Year: " + myCar.year);
        System.out.println("Color: " + myCar.color);
        System.out.println("Fuel Level: " + myCar.fuelLevel);
        
        // Call methods
        myCar.drive();
        System.out.println("Fuel Level after driving: " + myCar.fuelLevel);
        
        myCar.refuel();
        System.out.println("Fuel Level after refueling: " + myCar.fuelLevel);
        
        System.out.println("\n--- Multiple Objects ---");
        
        // Create another Car object
        Car friendsCar = new Car();
        
        // Set attributes for the second car
        friendsCar.make = "Honda";
        friendsCar.model = "Civic";
        friendsCar.year = 2019;
        friendsCar.color = "Red";
        friendsCar.fuelLevel = 0.75;
        
        // Display information about both cars
        System.out.println("My car: " + myCar.make + " " + myCar.model);
        System.out.println("Friend's car: " + friendsCar.make + " " + friendsCar.model);
        
        // Each object has its own state
        myCar.drive();
        System.out.println("My car's fuel level: " + myCar.fuelLevel);
        System.out.println("Friend's car's fuel level: " + friendsCar.fuelLevel + " (unchanged)");
        
        System.out.println("\n--- Creating a Different Type of Object ---");
        
        // Create a Rectangle object
        Rectangle rectangle = new Rectangle();
        rectangle.width = 5.0;
        rectangle.height = 3.0;
        
        // Call methods on the Rectangle object
        System.out.println("Rectangle width: " + rectangle.width);
        System.out.println("Rectangle height: " + rectangle.height);
        System.out.println("Rectangle area: " + rectangle.calculateArea());
        System.out.println("Rectangle perimeter: " + rectangle.calculatePerimeter());
        
        // Modify the rectangle
        rectangle.width = 10.0;
        System.out.println("New rectangle area: " + rectangle.calculateArea());
    }
}

/**
 * A simple Car class to demonstrate class definition and object creation.
 */
class Car {
    // Attributes (instance variables)
    String make;
    String model;
    int year;
    String color;
    double fuelLevel; // 0.0 to 1.0 representing empty to full
    
    // Methods
    void drive() {
        System.out.println("The " + color + " " + make + " " + model + " is driving.");
        fuelLevel -= 0.1; // Reduce fuel level when driving
    }
    
    void refuel() {
        System.out.println("Refueling the car.");
        fuelLevel = 1.0; // Full tank
    }
}

/**
 * A Rectangle class to demonstrate another example of a class.
 */
class Rectangle {
    // Attributes
    double width;
    double height;
    
    // Methods
    double calculateArea() {
        return width * height;
    }
    
    double calculatePerimeter() {
        return 2 * (width + height);
    }
}
