/**
 * PackageDemo.java
 * This program demonstrates packages and imports in Java.
 * 
 * Note: In a real project, this file would be in a specific package,
 * and the imported classes would be in their own package files.
 * For demonstration purposes, we'll include all classes in this file
 * and simulate the package structure with comments.
 */

// Simulating: package com.example.demo;

// Simulating imports:
// import com.example.shapes.Circle;
// import com.example.shapes.Rectangle;
// import com.example.utils.MathUtils;
// import static com.example.utils.StringUtils.capitalize;

public class PackageDemo {
    public static void main(String[] args) {
        System.out.println("--- Using Classes from Different Packages ---");
        
        // Create objects from the shapes package
        Circle circle = new Circle(5.0);
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        
        // Calculate and display areas
        System.out.println("Circle area: " + circle.getArea());
        System.out.println("Rectangle area: " + rectangle.getArea());
        
        System.out.println("\n--- Using Utility Classes ---");
        
        // Use methods from the MathUtils class
        double hypotenuse = MathUtils.calculateHypotenuse(3.0, 4.0);
        System.out.println("Hypotenuse of right triangle with sides 3 and 4: " + hypotenuse);
        
        int factorial = MathUtils.factorial(5);
        System.out.println("Factorial of 5: " + factorial);
        
        System.out.println("\n--- Using Static Imports ---");
        
        // Use static methods as if they were defined in this class
        String name = "john doe";
        String capitalizedName = StringUtils.capitalize(name);
        System.out.println("Capitalized name: " + capitalizedName);
        
        System.out.println("\n--- Package Access Control ---");
        
        // Create a product
        Product product = new Product("Laptop", 999.99);
        
        // Access public methods
        System.out.println("Product name: " + product.getName());
        System.out.println("Product price: $" + product.getPrice());
        
        // Cannot access package-private methods from outside the package
        // product.internalMethod();  // This would cause a compilation error
        
        System.out.println("\n--- Fully Qualified Names ---");
        
        // Using fully qualified names without imports
        java.util.Date today = new java.util.Date();
        System.out.println("Today's date: " + today);
        
        java.util.Random random = new java.util.Random();
        System.out.println("Random number: " + random.nextInt(100));
    }
}

/**
 * Simulating: package com.example.shapes;
 */
class Circle {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    // Package-private method (accessible only within the same package)
    void internalMethod() {
        System.out.println("This method is only accessible within the shapes package");
    }
}

/**
 * Simulating: package com.example.shapes;
 */
class Rectangle {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getArea() {
        return length * width;
    }
    
    // Package-private method (accessible only within the same package)
    void internalMethod() {
        System.out.println("This method is only accessible within the shapes package");
    }
}

/**
 * Simulating: package com.example.utils;
 */
class MathUtils {
    // Private constructor to prevent instantiation
    private MathUtils() {
        // This utility class should not be instantiated
    }
    
    // Public static methods
    public static double calculateHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }
    
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Simulating: package com.example.utils;
 */
class StringUtils {
    // Private constructor to prevent instantiation
    private StringUtils() {
        // This utility class should not be instantiated
    }
    
    // Public static methods
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        StringBuilder result = new StringBuilder();
        String[] words = str.split("\\s");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        
        return result.toString().trim();
    }
    
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }
}

/**
 * Simulating: package com.example.model;
 */
class Product {
    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    // Public getters
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Package-private method (accessible only within the same package)
    void internalMethod() {
        System.out.println("This method is only accessible within the model package");
    }
}
