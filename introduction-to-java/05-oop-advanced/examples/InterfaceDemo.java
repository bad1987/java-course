/**
 * InterfaceDemo.java
 * This program demonstrates interfaces in Java.
 */
public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("--- Basic Interface Implementation ---");
        
        // Create objects that implement the Drawable interface
        Circle circle = new Circle(5.0);
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        
        // Call methods from the interface
        circle.draw();
        rectangle.draw();
        
        // Call methods from the implementing classes
        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Rectangle area: " + rectangle.calculateArea());
        
        System.out.println("\n--- Interface as a Type ---");
        
        // Use the interface as a type
        Drawable drawable1 = circle;
        Drawable drawable2 = rectangle;
        
        // Call methods through the interface reference
        drawable1.draw();
        drawable2.draw();
        
        System.out.println("\n--- Multiple Interfaces ---");
        
        // Create an object that implements multiple interfaces
        Square square = new Square(5.0);
        
        // Call methods from different interfaces
        square.draw();  // From Drawable
        square.resize(2.0);  // From Resizable
        square.draw();  // Draw again after resizing
        
        System.out.println("\n--- Default Methods in Interfaces ---");
        
        // Call default methods from interfaces
        circle.displayCategory();
        rectangle.displayCategory();
        square.displayCategory();
        
        System.out.println("\n--- Static Methods in Interfaces ---");
        
        // Call static methods from interfaces
        boolean isCircleDrawable = Drawable.isDrawable(circle);
        boolean isStringDrawable = Drawable.isDrawable("Not drawable");
        
        System.out.println("Is circle drawable? " + isCircleDrawable);
        System.out.println("Is string drawable? " + isStringDrawable);
        
        System.out.println("\n--- Functional Interfaces and Lambda Expressions ---");
        
        // Use a functional interface with lambda expressions
        Calculable addition = (a, b) -> a + b;
        Calculable subtraction = (a, b) -> a - b;
        Calculable multiplication = (a, b) -> a * b;
        Calculable division = (a, b) -> a / b;
        
        // Use the lambda expressions
        double result1 = performCalculation(10.0, 5.0, addition);
        double result2 = performCalculation(10.0, 5.0, subtraction);
        double result3 = performCalculation(10.0, 5.0, multiplication);
        double result4 = performCalculation(10.0, 5.0, division);
        
        System.out.println("10 + 5 = " + result1);
        System.out.println("10 - 5 = " + result2);
        System.out.println("10 * 5 = " + result3);
        System.out.println("10 / 5 = " + result4);
        
        System.out.println("\n--- Interface Inheritance ---");
        
        // Create an object that implements an extended interface
        AdvancedShape advancedCircle = new AdvancedCircle(4.0);
        
        // Call methods from both interfaces
        advancedCircle.draw();  // From Drawable
        advancedCircle.rotate(45);  // From Rotatable
        advancedCircle.moveToPosition(10, 20);  // From AdvancedShape
        
        System.out.println("\n--- Real-world Example: Media Player ---");
        
        // Create different media types
        AudioPlayer audioPlayer = new AudioPlayer("Song.mp3");
        VideoPlayer videoPlayer = new VideoPlayer("Movie.mp4");
        
        // Use a common interface to play different media types
        playMedia(audioPlayer);
        playMedia(videoPlayer);
    }
    
    // Method that takes a Calculable functional interface
    public static double performCalculation(double a, double b, Calculable operation) {
        return operation.calculate(a, b);
    }
    
    // Method that takes a Playable interface
    public static void playMedia(Playable media) {
        media.play();
    }
}

/**
 * Drawable interface.
 */
interface Drawable {
    // Constant (implicitly public, static, and final)
    String CATEGORY = "Graphics";
    
    // Abstract method (implicitly public and abstract)
    void draw();
    
    // Default method (with implementation)
    default void displayCategory() {
        System.out.println("Category: " + CATEGORY);
    }
    
    // Static method
    static boolean isDrawable(Object obj) {
        return obj instanceof Drawable;
    }
}

/**
 * Resizable interface.
 */
interface Resizable {
    void resize(double factor);
}

/**
 * Rotatable interface.
 */
interface Rotatable {
    void rotate(double degrees);
}

/**
 * AdvancedShape interface that extends other interfaces.
 */
interface AdvancedShape extends Drawable, Rotatable {
    void moveToPosition(double x, double y);
}

/**
 * Circle class implementing Drawable.
 */
class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
    
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

/**
 * Rectangle class implementing Drawable.
 */
class Rectangle implements Drawable {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with length " + length + " and width " + width);
    }
    
    public double calculateArea() {
        return length * width;
    }
}

/**
 * Square class implementing multiple interfaces.
 */
class Square implements Drawable, Resizable {
    private double side;
    
    public Square(double side) {
        this.side = side;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a square with side " + side);
    }
    
    @Override
    public void resize(double factor) {
        side *= factor;
        System.out.println("Resized square to side " + side);
    }
    
    public double calculateArea() {
        return side * side;
    }
}

/**
 * AdvancedCircle implementing the AdvancedShape interface.
 */
class AdvancedCircle implements AdvancedShape {
    private double radius;
    private double x;
    private double y;
    private double rotationAngle;
    
    public AdvancedCircle(double radius) {
        this.radius = radius;
        this.x = 0;
        this.y = 0;
        this.rotationAngle = 0;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing an advanced circle with radius " + radius);
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Rotation: " + rotationAngle + " degrees");
    }
    
    @Override
    public void rotate(double degrees) {
        this.rotationAngle = degrees;
        System.out.println("Rotated circle to " + degrees + " degrees");
    }
    
    @Override
    public void moveToPosition(double x, double y) {
        this.x = x;
        this.y = y;
        System.out.println("Moved circle to position (" + x + ", " + y + ")");
    }
}

/**
 * Functional interface with a single abstract method.
 */
@FunctionalInterface
interface Calculable {
    double calculate(double a, double b);
}

/**
 * Playable interface for media players.
 */
interface Playable {
    void play();
    void pause();
    void stop();
}

/**
 * AudioPlayer implementing Playable.
 */
class AudioPlayer implements Playable {
    private String filename;
    
    public AudioPlayer(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void play() {
        System.out.println("Playing audio: " + filename);
    }
    
    @Override
    public void pause() {
        System.out.println("Pausing audio: " + filename);
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping audio: " + filename);
    }
}

/**
 * VideoPlayer implementing Playable.
 */
class VideoPlayer implements Playable {
    private String filename;
    
    public VideoPlayer(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void play() {
        System.out.println("Playing video: " + filename);
    }
    
    @Override
    public void pause() {
        System.out.println("Pausing video: " + filename);
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping video: " + filename);
    }
}
