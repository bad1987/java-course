/**
 * RecursionExamples.java
 * This program demonstrates recursion in Java.
 */
public class RecursionExamples {
    public static void main(String[] args) {
        System.out.println("--- Factorial Calculation ---");
        
        // Calculate factorials using recursion
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + "! = " + factorial(i));
        }
        
        System.out.println("\n--- Fibonacci Sequence ---");
        
        // Generate Fibonacci numbers using recursion
        System.out.println("First 10 Fibonacci numbers:");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        
        System.out.println("\n--- Countdown Example ---");
        
        // Recursive countdown
        countdown(5);
        
        System.out.println("\n--- Sum of Array Elements ---");
        
        // Calculate sum of array elements using recursion
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = sumArray(numbers, 0);
        System.out.println("Sum of array elements: " + sum);
        
        System.out.println("\n--- Power Calculation ---");
        
        // Calculate powers using recursion
        System.out.println("2^8 = " + power(2, 8));
        System.out.println("5^3 = " + power(5, 3));
        
        System.out.println("\n--- Greatest Common Divisor (GCD) ---");
        
        // Calculate GCD using recursion
        System.out.println("GCD of 48 and 18: " + gcd(48, 18));
        System.out.println("GCD of 100 and 45: " + gcd(100, 45));
        
        System.out.println("\n--- Binary Search ---");
        
        // Perform binary search using recursion
        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target = 23;
        int index = binarySearch(sortedArray, target, 0, sortedArray.length - 1);
        System.out.println("Index of " + target + ": " + index);
    }
    
    // Factorial calculation using recursion
    public static long factorial(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        // Recursive case
        else {
            return n * factorial(n - 1);
        }
    }
    
    // Fibonacci sequence using recursion
    public static int fibonacci(int n) {
        // Base cases
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        // Recursive case
        else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    
    // Countdown using recursion
    public static void countdown(int n) {
        // Base case
        if (n <= 0) {
            System.out.println("Blastoff!");
        }
        // Recursive case
        else {
            System.out.println(n);
            countdown(n - 1);
        }
    }
    
    // Sum of array elements using recursion
    public static int sumArray(int[] array, int index) {
        // Base case
        if (index >= array.length) {
            return 0;
        }
        // Recursive case
        else {
            return array[index] + sumArray(array, index + 1);
        }
    }
    
    // Power calculation using recursion
    public static long power(int base, int exponent) {
        // Base case
        if (exponent == 0) {
            return 1;
        }
        // Recursive case
        else {
            return base * power(base, exponent - 1);
        }
    }
    
    // Greatest Common Divisor (GCD) using recursion (Euclidean algorithm)
    public static int gcd(int a, int b) {
        // Base case
        if (b == 0) {
            return a;
        }
        // Recursive case
        else {
            return gcd(b, a % b);
        }
    }
    
    // Binary search using recursion
    public static int binarySearch(int[] array, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }
        
        // Calculate middle index
        int mid = left + (right - left) / 2;
        
        // Check if target is at the middle
        if (array[mid] == target) {
            return mid;
        }
        // If target is smaller, search in the left half
        else if (array[mid] > target) {
            return binarySearch(array, target, left, mid - 1);
        }
        // If target is larger, search in the right half
        else {
            return binarySearch(array, target, mid + 1, right);
        }
    }
}
