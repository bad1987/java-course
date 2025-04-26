/**
 * LoopExamples.java
 * This program demonstrates the use of different types of loops in Java.
 */
public class LoopExamples {
    public static void main(String[] args) {
        System.out.println("--- for Loop ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }
        
        System.out.println("\n--- while Loop ---");
        int count = 1;
        while (count <= 5) {
            System.out.println("Count: " + count);
            count++;
        }
        
        System.out.println("\n--- do-while Loop ---");
        int num = 1;
        do {
            System.out.println("Number: " + num);
            num++;
        } while (num <= 5);
        
        System.out.println("\n--- Enhanced for Loop (for-each) ---");
        int[] numbers = {10, 20, 30, 40, 50};
        for (int number : numbers) {
            System.out.println("Value: " + number);
        }
        
        System.out.println("\n--- Nested Loops ---");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.println("i = " + i + ", j = " + j);
            }
        }
        
        System.out.println("\n--- Pattern Printing with Loops ---");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
