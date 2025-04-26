/**
 * StaticMembersDemo.java
 * This program demonstrates static variables and methods in Java.
 */
public class StaticMembersDemo {
    public static void main(String[] args) {
        System.out.println("--- Static Variables ---");
        
        // Access static variable without creating an instance
        System.out.println("Value of PI: " + MathUtils.PI);
        
        System.out.println("\n--- Static Methods ---");
        
        // Call static methods without creating an instance
        double circleArea = MathUtils.calculateCircleArea(5.0);
        System.out.println("Circle area with radius 5.0: " + circleArea);
        
        double rectangleArea = MathUtils.calculateRectangleArea(4.0, 6.0);
        System.out.println("Rectangle area with length 4.0 and width 6.0: " + rectangleArea);
        
        // Check the operation count
        System.out.println("Operations performed: " + MathUtils.getOperationCount());
        
        System.out.println("\n--- Static Counter Example ---");
        
        // Create multiple Counter objects
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        Counter counter3 = new Counter();
        
        // Display the count
        System.out.println("Number of Counter objects created: " + Counter.getCount());
        
        System.out.println("\n--- Static vs. Instance Variables ---");
        
        // Create Student objects
        Student student1 = new Student("Alice", 85);
        Student student2 = new Student("Bob", 92);
        Student student3 = new Student("Charlie", 78);
        
        // Display student information
        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();
        
        // Display class average (static method)
        System.out.println("\nClass average: " + Student.getClassAverage());
        
        System.out.println("\n--- Static Initialization Block ---");
        
        // Access Database class to trigger static initialization
        System.out.println("Database URL: " + Database.getUrl());
        System.out.println("Database username: " + Database.getUsername());
        
        System.out.println("\n--- Static Import Example ---");
        
        // Using static methods from MathUtils
        double hypotenuse = MathUtils.calculateHypotenuse(3.0, 4.0);
        System.out.println("Hypotenuse of right triangle with sides 3.0 and 4.0: " + hypotenuse);
    }
}

/**
 * A utility class with static methods and variables.
 */
class MathUtils {
    // Static constant
    public static final double PI = 3.14159265359;
    
    // Static variable to track operations
    private static int operationCount = 0;
    
    // Static method to calculate circle area
    public static double calculateCircleArea(double radius) {
        operationCount++;
        return PI * radius * radius;
    }
    
    // Static method to calculate rectangle area
    public static double calculateRectangleArea(double length, double width) {
        operationCount++;
        return length * width;
    }
    
    // Static method to calculate hypotenuse of a right triangle
    public static double calculateHypotenuse(double a, double b) {
        operationCount++;
        return Math.sqrt(a * a + b * b);
    }
    
    // Static method to get the operation count
    public static int getOperationCount() {
        return operationCount;
    }
}

/**
 * A Counter class that keeps track of how many instances have been created.
 */
class Counter {
    // Static variable shared by all instances
    private static int count = 0;
    
    // Instance variable unique to each instance
    private int id;
    
    // Constructor increments the static count
    public Counter() {
        count++;
        id = count;
        System.out.println("Created Counter #" + id);
    }
    
    // Static method to get the count
    public static int getCount() {
        return count;
    }
    
    // Instance method to get this counter's ID
    public int getId() {
        return id;
    }
}

/**
 * A Student class demonstrating both static and instance variables.
 */
class Student {
    // Static variables (shared by all students)
    private static int totalStudents = 0;
    private static double totalScore = 0;
    
    // Instance variables (unique to each student)
    private String name;
    private double score;
    
    // Constructor
    public Student(String name, double score) {
        this.name = name;
        this.score = score;
        
        // Update static variables
        totalStudents++;
        totalScore += score;
    }
    
    // Instance method
    public void displayInfo() {
        System.out.println("\nStudent: " + name);
        System.out.println("Score: " + score);
        System.out.println("This student's score is " + 
                          (score > getClassAverage() ? "above" : "below") + 
                          " the class average.");
    }
    
    // Static method to calculate class average
    public static double getClassAverage() {
        if (totalStudents == 0) {
            return 0;
        }
        return totalScore / totalStudents;
    }
    
    // Static method to get total number of students
    public static int getTotalStudents() {
        return totalStudents;
    }
}

/**
 * A Database class demonstrating static initialization block.
 */
class Database {
    // Static variables
    private static String url;
    private static String username;
    private static String password;
    
    // Static initialization block - runs when the class is loaded
    static {
        System.out.println("Static initialization block executed");
        // In a real application, these might be loaded from a configuration file
        url = "jdbc:mysql://localhost:3306/mydb";
        username = "admin";
        password = "securepassword";
        
        // Simulate database connection
        System.out.println("Database connection initialized");
    }
    
    // Private constructor to prevent instantiation
    private Database() {
        // This class should not be instantiated
    }
    
    // Static methods to access the database properties
    public static String getUrl() {
        return url;
    }
    
    public static String getUsername() {
        return username;
    }
    
    // Note: We don't provide a getter for the password for security reasons
}
