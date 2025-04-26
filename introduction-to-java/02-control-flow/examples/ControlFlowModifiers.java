/**
 * ControlFlowModifiers.java
 * This program demonstrates the use of break, continue, and labeled statements.
 */
public class ControlFlowModifiers {
    public static void main(String[] args) {
        System.out.println("--- break Statement ---");
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                System.out.println("Breaking the loop at i = " + i);
                break; // Exit the loop when i equals 6
            }
            System.out.println("Count: " + i);
        }
        
        System.out.println("\n--- continue Statement ---");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                System.out.println("Skipping iteration at i = " + i);
                continue; // Skip iteration when i equals 3
            }
            System.out.println("Count: " + i);
        }
        
        System.out.println("\n--- return Statement Example ---");
        checkNumber(5);
        checkNumber(-3);
        
        System.out.println("\n--- Labeled break Statement ---");
        outerLoop: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("Breaking out of both loops at i = " + i + ", j = " + j);
                    break outerLoop; // Break out of both loops
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }
        
        System.out.println("\n--- Labeled continue Statement ---");
        outerLoop: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    System.out.println("Continuing outer loop at i = " + i + ", j = " + j);
                    continue outerLoop; // Skip to the next iteration of the outer loop
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }
    }
    
    public static void checkNumber(int number) {
        if (number < 0) {
            System.out.println("Negative number detected: " + number);
            return; // Exit the method
        }
        System.out.println("Processing positive number: " + number);
    }
}
