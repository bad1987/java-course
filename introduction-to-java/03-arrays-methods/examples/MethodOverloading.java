/**
 * MethodOverloading.java
 * This program demonstrates method overloading in Java.
 */
public class MethodOverloading {
    public static void main(String[] args) {
        System.out.println("--- Method Overloading ---");
        
        // Call overloaded methods with different parameter types and counts
        
        // Overloaded print methods
        print("Hello, World!");
        print(42);
        print(3.14159);
        print(true);
        
        System.out.println("\n--- Overloaded add Methods ---");
        
        // Call overloaded add methods
        System.out.println("add(5, 3) = " + add(5, 3));
        System.out.println("add(5, 3, 2) = " + add(5, 3, 2));
        System.out.println("add(5.5, 3.5) = " + add(5.5, 3.5));
        System.out.println("add(\"Hello, \", \"World!\") = " + add("Hello, ", "World!"));
        
        System.out.println("\n--- Overloaded Methods with Different Parameter Orders ---");
        
        // Call methods with different parameter orders
        displayInfo("Alice", 25);
        displayInfo(30, "Bob");
        
        System.out.println("\n--- Practical Example: Area Calculation ---");
        
        // Calculate areas using overloaded methods
        System.out.println("Area of square with side 5: " + calculateArea(5));
        System.out.println("Area of rectangle with length 5 and width 3: " + calculateArea(5, 3));
        System.out.println("Area of circle with radius 4: " + calculateArea(4.0));
        System.out.println("Area of triangle with base 6 and height 8: " + calculateArea(6.0, 8.0, "triangle"));
    }
    
    // Overloaded print methods
    public static void print(String message) {
        System.out.println("String: " + message);
    }
    
    public static void print(int number) {
        System.out.println("Integer: " + number);
    }
    
    public static void print(double number) {
        System.out.println("Double: " + number);
    }
    
    public static void print(boolean value) {
        System.out.println("Boolean: " + value);
    }
    
    // Overloaded add methods
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static double add(double a, double b) {
        return a + b;
    }
    
    public static String add(String a, String b) {
        return a + b;
    }
    
    // Methods with different parameter orders
    public static void displayInfo(String name, int age) {
        System.out.println("Name: " + name + ", Age: " + age);
    }
    
    public static void displayInfo(int age, String name) {
        System.out.println("Age: " + age + ", Name: " + name);
    }
    
    // Practical example: Area calculation methods
    public static double calculateArea(int side) {
        // Area of a square
        return side * side;
    }
    
    public static double calculateArea(int length, int width) {
        // Area of a rectangle
        return length * width;
    }
    
    public static double calculateArea(double radius) {
        // Area of a circle
        return Math.PI * radius * radius;
    }
    
    public static double calculateArea(double base, double height, String shape) {
        // Area of a triangle
        if (shape.equalsIgnoreCase("triangle")) {
            return 0.5 * base * height;
        } else {
            return -1; // Invalid shape
        }
    }
}
