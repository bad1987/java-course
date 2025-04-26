/**
 * SwitchExample.java
 * This program demonstrates the use of switch statements in Java.
 */
public class SwitchExample {
    public static void main(String[] args) {
        System.out.println("--- Basic Switch Statement ---");
        int day = 3;
        String dayName;
        
        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
            default:
                dayName = "Invalid day";
        }
        
        System.out.println("Day " + day + " is " + dayName);
        
        System.out.println("\n--- Switch with String (Java 7+) ---");
        String fruit = "Apple";
        
        switch (fruit) {
            case "Apple":
                System.out.println("Red fruit");
                break;
            case "Banana":
                System.out.println("Yellow fruit");
                break;
            case "Orange":
                System.out.println("Orange fruit");
                break;
            default:
                System.out.println("Unknown fruit");
        }
        
        System.out.println("\n--- Fall-through Behavior ---");
        int month = 8;
        String season;
        
        switch (month) {
            case 12:
            case 1:
            case 2:
                season = "Winter";
                break;
            case 3:
            case 4:
            case 5:
                season = "Spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "Summer";
                break;
            case 9:
            case 10:
            case 11:
                season = "Fall";
                break;
            default:
                season = "Invalid month";
        }
        
        System.out.println("Month " + month + " is in " + season);
        
        // Uncomment this section if using Java 12 or higher
        /*
        System.out.println("\n--- Switch Expressions (Java 12+) ---");
        int dayNumber = 3;
        String dayType = switch (dayNumber) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> "Invalid day";
        };
        
        System.out.println("Day " + dayNumber + " is a " + dayType);
        */
    }
}
