/**
 * ConditionalStatements.java
 * This program demonstrates the use of conditional statements in Java.
 */
public class ConditionalStatements {
    public static void main(String[] args) {
        System.out.println("--- Simple if Statement ---");
        int age = 18;
        if (age >= 18) {
            System.out.println("You are an adult.");
        }
        
        System.out.println("\n--- if-else Statement ---");
        int temperature = 15;
        if (temperature > 20) {
            System.out.println("It's warm outside.");
        } else {
            System.out.println("It's cold outside.");
        }
        
        System.out.println("\n--- if-else-if Ladder ---");
        int score = 85;
        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else if (score >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
        
        System.out.println("\n--- Nested if Statements ---");
        boolean hasLicense = true;
        if (age >= 18) {
            System.out.println("Age requirement met.");
            if (hasLicense) {
                System.out.println("You can drive.");
            } else {
                System.out.println("You need a license to drive.");
            }
        } else {
            System.out.println("You must be 18 or older to drive.");
        }
        
        System.out.println("\n--- Ternary Operator ---");
        int number = 7;
        String result = (number % 2 == 0) ? "even" : "odd";
        System.out.println(number + " is " + result);
    }
}
