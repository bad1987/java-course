/**
 * MethodBasics.java
 * This program demonstrates the basics of defining and calling methods in Java.
 */
public class MethodBasics {
    public static void main(String[] args) {
        System.out.println("--- Calling Methods ---");
        
        // Call a simple method with no parameters
        sayHello();
        
        // Call a method with parameters
        greet("Alice");
        greet("Bob");
        
        // Call a method with multiple parameters
        displayInfo("Charlie", 25);
        
        System.out.println("\n--- Methods with Return Values ---");
        
        // Call a method that returns a value
        int sum = add(5, 3);
        System.out.println("5 + 3 = " + sum);
        
        // Use the returned value directly in an expression
        System.out.println("10 + 20 = " + add(10, 20));
        
        // Call a method that returns a double
        double circleArea = calculateCircleArea(5.0);
        System.out.println("Area of circle with radius 5.0: " + circleArea);
        
        // Call a method that returns a boolean
        boolean isEven = isEvenNumber(8);
        System.out.println("Is 8 even? " + isEven);
        System.out.println("Is 7 even? " + isEvenNumber(7));
        
        System.out.println("\n--- Method Chaining ---");
        
        // Call methods in sequence
        int result = multiply(add(3, 4), subtract(10, 5));
        System.out.println("multiply(add(3, 4), subtract(10, 5)) = " + result);
        
        System.out.println("\n--- Void Methods ---");
        
        // Call void methods
        printLine();
        printMessage("This is a custom message");
        printLine();
        
        System.out.println("\n--- Methods with Variable Scope ---");
        
        // Variable scope demonstration
        int x = 10;
        System.out.println("Before calling method: x = " + x);
        
        // This method has a local variable x that doesn't affect the x in main
        demonstrateScope();
        
        System.out.println("After calling method: x = " + x);
    }
    
    // Simple method with no parameters and no return value
    public static void sayHello() {
        System.out.println("Hello, World!");
    }
    
    // Method with one parameter
    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
    
    // Method with multiple parameters
    public static void displayInfo(String name, int age) {
        System.out.println(name + " is " + age + " years old.");
    }
    
    // Method that returns an int value
    public static int add(int a, int b) {
        int sum = a + b;
        return sum;
    }
    
    // Method that returns a double value
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    // Method that returns a boolean value
    public static boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }
    
    // Additional methods for method chaining example
    public static int subtract(int a, int b) {
        return a - b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    // Void method examples
    public static void printLine() {
        System.out.println("------------------------");
    }
    
    public static void printMessage(String message) {
        System.out.println("MESSAGE: " + message);
    }
    
    // Method to demonstrate variable scope
    public static void demonstrateScope() {
        int x = 20;  // This is a different variable than the x in main
        System.out.println("Inside method: x = " + x);
    }
}
