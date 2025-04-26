/**
 * ArrayBasics.java
 * This program demonstrates the basics of working with arrays in Java.
 */
public class ArrayBasics {
    public static void main(String[] args) {
        System.out.println("--- Creating and Initializing Arrays ---");
        
        // Declaration and initialization in separate steps
        int[] numbers;
        numbers = new int[5];
        
        // Initialize array elements
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;
        
        // Print the array elements
        System.out.println("First array elements:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers[" + i + "] = " + numbers[i]);
        }
        
        // Declaration and initialization in one step
        int[] scores = {85, 90, 78, 92, 88};
        
        System.out.println("\nSecond array elements:");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("scores[" + i + "] = " + scores[i]);
        }
        
        System.out.println("\n--- Accessing Array Elements ---");
        
        // Access specific elements
        System.out.println("First element: " + scores[0]);
        System.out.println("Third element: " + scores[2]);
        
        // Modify an element
        scores[1] = 95;
        System.out.println("Modified second element: " + scores[1]);
        
        System.out.println("\n--- Array Length ---");
        System.out.println("Length of scores array: " + scores.length);
        
        System.out.println("\n--- Iterating Through Arrays ---");
        
        // Using a standard for loop
        System.out.println("Using standard for loop:");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("Element at index " + i + ": " + scores[i]);
        }
        
        // Using an enhanced for loop (for-each)
        System.out.println("\nUsing enhanced for loop:");
        for (int score : scores) {
            System.out.println("Score: " + score);
        }
        
        System.out.println("\n--- Arrays of Different Types ---");
        
        // String array
        String[] fruits = {"Apple", "Banana", "Orange", "Mango", "Grapes"};
        System.out.println("Fruits:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        // Double array
        double[] prices = {19.99, 29.99, 15.50, 9.99, 49.99};
        System.out.println("\nPrices:");
        for (double price : prices) {
            System.out.println("$" + price);
        }
        
        // Boolean array
        boolean[] flags = {true, false, true, true, false};
        System.out.println("\nFlags:");
        for (int i = 0; i < flags.length; i++) {
            System.out.println("flags[" + i + "] = " + flags[i]);
        }
        
        System.out.println("\n--- Common Array Operations ---");
        
        // Find the sum of elements
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        System.out.println("Sum of scores: " + sum);
        
        // Find the average
        double average = (double) sum / scores.length;
        System.out.println("Average score: " + average);
        
        // Find the maximum value
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        System.out.println("Maximum score: " + max);
        
        // Find the minimum value
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        System.out.println("Minimum score: " + min);
    }
}
