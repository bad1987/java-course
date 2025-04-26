/**
 * ThisKeywordDemo.java
 * This program demonstrates the use of the 'this' keyword in Java.
 */
public class ThisKeywordDemo {
    public static void main(String[] args) {
        System.out.println("--- Using 'this' to Distinguish Variables ---");
        
        // Create a Rectangle object
        Rectangle rect = new Rectangle(5.0, 3.0);
        
        // Display rectangle properties
        System.out.println("Rectangle dimensions: " + rect.getLength() + " x " + rect.getWidth());
        System.out.println("Area: " + rect.calculateArea());
        
        System.out.println("\n--- Using 'this' to Call Another Constructor ---");
        
        // Create a Circle object using different constructors
        Circle circle1 = new Circle();
        System.out.println("Circle 1 - Radius: " + circle1.getRadius());
        
        Circle circle2 = new Circle(5.0);
        System.out.println("Circle 2 - Radius: " + circle2.getRadius());
        
        Circle circle3 = new Circle(7.0, "Blue");
        System.out.println("Circle 3 - Radius: " + circle3.getRadius() + ", Color: " + circle3.getColor());
        
        System.out.println("\n--- Using 'this' to Pass Current Object ---");
        
        // Create a Person object
        PersonWithThis person1 = new PersonWithThis("Alice", 30);
        PersonWithThis person2 = new PersonWithThis("Bob", 25);
        
        // Demonstrate passing 'this' to another method
        person1.compareAge(person2);
        person2.compareAge(person1);
        
        System.out.println("\n--- Using 'this' in Method Chaining ---");
        
        // Create a StringBuilder-like class and demonstrate method chaining
        TextBuilder builder = new TextBuilder();
        
        // Chain method calls using 'this'
        builder.append("Hello")
               .append(" ")
               .append("World")
               .append("!")
               .printText();
    }
}

/**
 * A Rectangle class demonstrating the use of 'this' to distinguish between
 * instance variables and parameters with the same name.
 */
class Rectangle {
    private double length;
    private double width;
    
    // Constructor with parameters that have the same names as instance variables
    public Rectangle(double length, double width) {
        // Using 'this' to refer to instance variables
        this.length = length;
        this.width = width;
    }
    
    // Getters
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    // Setters
    public void setLength(double length) {
        this.length = length;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
    
    // Calculate area
    public double calculateArea() {
        return length * width;
    }
}

/**
 * A Circle class demonstrating the use of 'this' to call another constructor.
 */
class Circle {
    private double radius;
    private String color;
    
    // Default constructor
    public Circle() {
        // Call another constructor with default values
        this(1.0, "Red");
        System.out.println("Default constructor called");
    }
    
    // Constructor with one parameter
    public Circle(double radius) {
        // Call the full constructor with a default color
        this(radius, "Red");
        System.out.println("Constructor with radius called");
    }
    
    // Constructor with all parameters
    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
        System.out.println("Constructor with radius and color called");
    }
    
    // Getters
    public double getRadius() {
        return radius;
    }
    
    public String getColor() {
        return color;
    }
    
    // Calculate area
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

/**
 * A Person class demonstrating the use of 'this' to pass the current object.
 */
class PersonWithThis {
    private String name;
    private int age;
    
    public PersonWithThis(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Method that compares the current person's age with another person
    public void compareAge(PersonWithThis otherPerson) {
        if (this.age > otherPerson.age) {
            System.out.println(this.name + " is older than " + otherPerson.name);
        } else if (this.age < otherPerson.age) {
            System.out.println(this.name + " is younger than " + otherPerson.name);
        } else {
            System.out.println(this.name + " is the same age as " + otherPerson.name);
        }
    }
    
    // Method that uses 'this' to call another method
    public void celebrateBirthday() {
        this.age++;
        System.out.println(this.name + " is now " + this.age + " years old.");
    }
}

/**
 * A TextBuilder class demonstrating the use of 'this' for method chaining.
 */
class TextBuilder {
    private StringBuilder text;
    
    public TextBuilder() {
        text = new StringBuilder();
    }
    
    // Method that returns 'this' to enable method chaining
    public TextBuilder append(String str) {
        text.append(str);
        return this;  // Return the current object
    }
    
    // Method to print the current text
    public void printText() {
        System.out.println("Text: " + text.toString());
    }
    
    // Method to get the text
    public String getText() {
        return text.toString();
    }
}
