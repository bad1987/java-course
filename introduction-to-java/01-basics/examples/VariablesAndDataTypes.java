/**
 * VariablesAndDataTypes.java
 * This program demonstrates the use of variables and different data types in Java.
 */
public class VariablesAndDataTypes {
    public static void main(String[] args) {
        // Primitive Data Types
        
        // Integer types
        byte smallNumber = 127;                // 8-bit, range: -128 to 127
        short mediumNumber = 32000;            // 16-bit, range: -32,768 to 32,767
        int regularNumber = 2000000000;        // 32-bit, range: ~-2 billion to ~2 billion
        long largeNumber = 9000000000000000000L; // 64-bit, note the 'L' suffix
        
        // Floating-point types
        float decimalNumber = 3.14f;           // 32-bit, note the 'f' suffix
        double preciseDecimal = 3.14159265359; // 64-bit, more precision
        
        // Character type
        char singleCharacter = 'A';            // 16-bit Unicode character
        
        // Boolean type
        boolean isJavaFun = true;              // true or false
        
        // Reference Data Type
        String message = "Hello, Java!";       // String is a class, not a primitive type
        
        // Constants (values that cannot be changed)
        final double PI = 3.14159;
        
        // Printing all variables
        System.out.println("--- Primitive Data Types ---");
        System.out.println("byte: " + smallNumber);
        System.out.println("short: " + mediumNumber);
        System.out.println("int: " + regularNumber);
        System.out.println("long: " + largeNumber);
        System.out.println("float: " + decimalNumber);
        System.out.println("double: " + preciseDecimal);
        System.out.println("char: " + singleCharacter);
        System.out.println("boolean: " + isJavaFun);
        
        System.out.println("\n--- Reference Data Type ---");
        System.out.println("String: " + message);
        
        System.out.println("\n--- Constant ---");
        System.out.println("PI: " + PI);
        
        // Type conversion (casting)
        int intValue = 100;
        long longValue = intValue;      // Implicit casting (widening)
        
        double doubleValue = 100.5;
        int convertedInt = (int) doubleValue;  // Explicit casting (narrowing)
        
        System.out.println("\n--- Type Conversion ---");
        System.out.println("Implicit casting (int to long): " + longValue);
        System.out.println("Explicit casting (double to int): " + convertedInt);
    }
}
