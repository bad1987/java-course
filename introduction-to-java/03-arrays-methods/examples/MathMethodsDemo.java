/**
 * MathMethodsDemo.java
 * This program demonstrates common methods from the Math class in Java.
 */
public class MathMethodsDemo {
    public static void main(String[] args) {
        System.out.println("--- Basic Math Operations ---");
        
        // Absolute value
        System.out.println("Absolute value of -5: " + Math.abs(-5));
        System.out.println("Absolute value of 5: " + Math.abs(5));
        System.out.println("Absolute value of -3.14: " + Math.abs(-3.14));
        
        // Maximum and minimum
        System.out.println("\nMaximum of 10 and 20: " + Math.max(10, 20));
        System.out.println("Minimum of 10 and 20: " + Math.min(10, 20));
        
        // Power and square root
        System.out.println("\n2 raised to power 3: " + Math.pow(2, 3));
        System.out.println("Square root of 25: " + Math.sqrt(25));
        System.out.println("Cube root of 27: " + Math.cbrt(27));
        
        System.out.println("\n--- Rounding Methods ---");
        
        double value = 3.75;
        
        // Round to nearest integer
        System.out.println("Round " + value + ": " + Math.round(value));
        
        // Round down (floor)
        System.out.println("Floor " + value + ": " + Math.floor(value));
        
        // Round up (ceiling)
        System.out.println("Ceiling " + value + ": " + Math.ceil(value));
        
        // Different values
        System.out.println("\nRound 3.49: " + Math.round(3.49));
        System.out.println("Round 3.50: " + Math.round(3.50));
        System.out.println("Round 3.51: " + Math.round(3.51));
        
        System.out.println("\nFloor -3.75: " + Math.floor(-3.75));
        System.out.println("Ceiling -3.75: " + Math.ceil(-3.75));
        
        System.out.println("\n--- Trigonometric Functions ---");
        
        // Convert degrees to radians
        double degrees = 45;
        double radians = Math.toRadians(degrees);
        System.out.println(degrees + " degrees = " + radians + " radians");
        
        // Sine, cosine, tangent
        System.out.println("sin(45°): " + Math.sin(radians));
        System.out.println("cos(45°): " + Math.cos(radians));
        System.out.println("tan(45°): " + Math.tan(radians));
        
        // Convert radians to degrees
        double angleRadians = Math.PI / 4;
        double angleDegrees = Math.toDegrees(angleRadians);
        System.out.println("\n" + angleRadians + " radians = " + angleDegrees + " degrees");
        
        System.out.println("\n--- Logarithmic Functions ---");
        
        // Natural logarithm (base e)
        System.out.println("Natural log of 10: " + Math.log(10));
        
        // Base 10 logarithm
        System.out.println("Base 10 log of 100: " + Math.log10(100));
        
        // Exponential function (e^x)
        System.out.println("e^2: " + Math.exp(2));
        
        System.out.println("\n--- Constants ---");
        
        // Mathematical constants
        System.out.println("Pi: " + Math.PI);
        System.out.println("e: " + Math.E);
        
        System.out.println("\n--- Random Numbers ---");
        
        // Random number between 0.0 (inclusive) and 1.0 (exclusive)
        System.out.println("Random number: " + Math.random());
        
        // Generate random integers in a range
        int min = 1;
        int max = 100;
        
        // Random integer between min and max (inclusive)
        int randomInt1 = min + (int)(Math.random() * ((max - min) + 1));
        int randomInt2 = min + (int)(Math.random() * ((max - min) + 1));
        int randomInt3 = min + (int)(Math.random() * ((max - min) + 1));
        
        System.out.println("Random integers between " + min + " and " + max + ":");
        System.out.println(randomInt1);
        System.out.println(randomInt2);
        System.out.println(randomInt3);
        
        System.out.println("\n--- Practical Examples ---");
        
        // Calculate hypotenuse of a right triangle
        double a = 3;
        double b = 4;
        double hypotenuse = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        System.out.println("Hypotenuse of right triangle with sides " + a + " and " + b + ": " + hypotenuse);
        
        // Calculate distance between two points
        double x1 = 1, y1 = 2;
        double x2 = 4, y2 = 6;
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("Distance between points (" + x1 + "," + y1 + ") and (" + x2 + "," + y2 + "): " + distance);
    }
}
