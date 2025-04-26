/**
 * BasicOperators.java
 * This program demonstrates the use of various operators in Java.
 */
public class BasicOperators {
    public static void main(String[] args) {
        // Variables for demonstration
        int a = 10;
        int b = 3;
        
        // Arithmetic Operators
        System.out.println("--- Arithmetic Operators ---");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));    // Addition
        System.out.println("a - b = " + (a - b));    // Subtraction
        System.out.println("a * b = " + (a * b));    // Multiplication
        System.out.println("a / b = " + (a / b));    // Division (integer division)
        System.out.println("a % b = " + (a % b));    // Modulus (remainder)
        
        // Increment and Decrement
        System.out.println("\n--- Increment and Decrement ---");
        int c = a;  // c = 10
        System.out.println("c = " + c);
        System.out.println("++c = " + (++c));   // Pre-increment: increment then use
        System.out.println("c = " + c);         // c is now 11
        
        c = a;  // Reset c to 10
        System.out.println("c = " + c);
        System.out.println("c++ = " + (c++));   // Post-increment: use then increment
        System.out.println("c = " + c);         // c is now 11
        
        c = a;  // Reset c to 10
        System.out.println("c = " + c);
        System.out.println("--c = " + (--c));   // Pre-decrement: decrement then use
        System.out.println("c = " + c);         // c is now 9
        
        c = a;  // Reset c to 10
        System.out.println("c = " + c);
        System.out.println("c-- = " + (c--));   // Post-decrement: use then decrement
        System.out.println("c = " + c);         // c is now 9
        
        // Assignment Operators
        System.out.println("\n--- Assignment Operators ---");
        int d = a;  // Simple assignment
        System.out.println("d = a: " + d);
        
        d += b;     // d = d + b
        System.out.println("d += b: " + d);
        
        d -= b;     // d = d - b
        System.out.println("d -= b: " + d);
        
        d *= b;     // d = d * b
        System.out.println("d *= b: " + d);
        
        d /= b;     // d = d / b
        System.out.println("d /= b: " + d);
        
        d %= b;     // d = d % b
        System.out.println("d %= b: " + d);
        
        // Comparison Operators
        System.out.println("\n--- Comparison Operators ---");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a == b: " + (a == b));   // Equal to
        System.out.println("a != b: " + (a != b));   // Not equal to
        System.out.println("a > b: " + (a > b));     // Greater than
        System.out.println("a < b: " + (a < b));     // Less than
        System.out.println("a >= b: " + (a >= b));   // Greater than or equal to
        System.out.println("a <= b: " + (a <= b));   // Less than or equal to
        
        // Logical Operators
        System.out.println("\n--- Logical Operators ---");
        boolean condition1 = true;
        boolean condition2 = false;
        
        System.out.println("condition1 = " + condition1 + ", condition2 = " + condition2);
        System.out.println("condition1 && condition2: " + (condition1 && condition2));  // Logical AND
        System.out.println("condition1 || condition2: " + (condition1 || condition2));  // Logical OR
        System.out.println("!condition1: " + (!condition1));                           // Logical NOT
    }
}
