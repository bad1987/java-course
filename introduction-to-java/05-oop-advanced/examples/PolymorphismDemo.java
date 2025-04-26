/**
 * PolymorphismDemo.java
 * This program demonstrates polymorphism in Java.
 */
public class PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("--- Basic Polymorphism ---");
        
        // Create an array of Shape references
        Shape[] shapes = new Shape[3];
        
        // Assign different types of shapes to the array
        shapes[0] = new Circle(5.0);
        shapes[1] = new Rectangle(4.0, 6.0);
        shapes[2] = new Triangle(3.0, 4.0);
        
        // Calculate and display areas polymorphically
        for (int i = 0; i < shapes.length; i++) {
            System.out.println("Shape " + (i + 1) + " area: " + shapes[i].calculateArea());
        }
        
        System.out.println("\n--- Dynamic Method Dispatch ---");
        
        // Variable of type Shape can reference any subclass object
        Shape shape = new Circle(3.0);
        System.out.println("Shape is a Circle. Area: " + shape.calculateArea());
        
        // Change the reference to a different subclass
        shape = new Rectangle(2.0, 5.0);
        System.out.println("Shape is now a Rectangle. Area: " + shape.calculateArea());
        
        System.out.println("\n--- Polymorphism with Methods ---");
        
        // Create different types of animals
        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Whiskers");
        Animal bird = new Bird("Tweety");
        
        // Call methods polymorphically
        System.out.println("Making animals speak:");
        makeAnimalSpeak(dog);
        makeAnimalSpeak(cat);
        makeAnimalSpeak(bird);
        
        System.out.println("\n--- Polymorphism with Interfaces ---");
        
        // Create objects that implement the Drawable interface
        Drawable circle = new DrawableCircle(4.0);
        Drawable rectangle = new DrawableRectangle(3.0, 5.0);
        Drawable triangle = new DrawableTriangle(6.0, 8.0);
        
        // Call draw method polymorphically
        System.out.println("Drawing shapes:");
        drawShape(circle);
        drawShape(rectangle);
        drawShape(triangle);
        
        System.out.println("\n--- Real-world Example: Payment Processing ---");
        
        // Create different payment methods
        Payment creditCard = new CreditCardPayment("1234-5678-9012-3456", "John Doe", "12/25", "123");
        Payment payPal = new PayPalPayment("john.doe@example.com");
        Payment bankTransfer = new BankTransferPayment("GB29NWBK60161331926819", "John Doe");
        
        // Process payments polymorphically
        processPayment(creditCard, 100.0);
        processPayment(payPal, 75.50);
        processPayment(bankTransfer, 250.0);
    }
    
    // Method that takes an Animal parameter but works with any subclass
    public static void makeAnimalSpeak(Animal animal) {
        animal.speak();
    }
    
    // Method that takes a Drawable parameter but works with any implementing class
    public static void drawShape(Drawable drawable) {
        drawable.draw();
    }
    
    // Method that processes any type of payment
    public static void processPayment(Payment payment, double amount) {
        System.out.println("Processing payment of $" + amount);
        payment.processPayment(amount);
        System.out.println();
    }
}

/**
 * Base class for shapes.
 */
class Shape {
    public double calculateArea() {
        return 0.0;  // Default implementation
    }
}

/**
 * Circle class extending Shape.
 */
class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

/**
 * Rectangle class extending Shape.
 */
class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
}

/**
 * Triangle class extending Shape.
 */
class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

/**
 * Base class for animals.
 */
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void speak() {
        System.out.println("Animal makes a sound");
    }
}

/**
 * Dog class extending Animal.
 */
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void speak() {
        System.out.println(name + " says Woof!");
    }
}

/**
 * Cat class extending Animal.
 */
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void speak() {
        System.out.println(name + " says Meow!");
    }
}

/**
 * Bird class extending Animal.
 */
class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }
    
    @Override
    public void speak() {
        System.out.println(name + " says Tweet!");
    }
}

/**
 * Drawable interface.
 */
interface Drawable {
    void draw();
}

/**
 * DrawableCircle implementing Drawable.
 */
class DrawableCircle implements Drawable {
    private double radius;
    
    public DrawableCircle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}

/**
 * DrawableRectangle implementing Drawable.
 */
class DrawableRectangle implements Drawable {
    private double length;
    private double width;
    
    public DrawableRectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with length " + length + " and width " + width);
    }
}

/**
 * DrawableTriangle implementing Drawable.
 */
class DrawableTriangle implements Drawable {
    private double base;
    private double height;
    
    public DrawableTriangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a triangle with base " + base + " and height " + height);
    }
}

/**
 * Payment abstract class for payment processing.
 */
abstract class Payment {
    public abstract void processPayment(double amount);
}

/**
 * CreditCardPayment extending Payment.
 */
class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;
    private String cvv;
    
    public CreditCardPayment(String cardNumber, String cardholderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public void processPayment(double amount) {
        // In a real application, this would connect to a payment gateway
        System.out.println("Processing credit card payment");
        System.out.println("Cardholder: " + cardholderName);
        System.out.println("Card number: " + maskCardNumber(cardNumber));
        System.out.println("Amount: $" + amount);
        System.out.println("Payment successful");
    }
    
    private String maskCardNumber(String cardNumber) {
        // Show only the last 4 digits
        return "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length() - 4);
    }
}

/**
 * PayPalPayment extending Payment.
 */
class PayPalPayment extends Payment {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void processPayment(double amount) {
        // In a real application, this would connect to PayPal's API
        System.out.println("Processing PayPal payment");
        System.out.println("Email: " + email);
        System.out.println("Amount: $" + amount);
        System.out.println("Payment successful");
    }
}

/**
 * BankTransferPayment extending Payment.
 */
class BankTransferPayment extends Payment {
    private String accountNumber;
    private String accountName;
    
    public BankTransferPayment(String accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }
    
    @Override
    public void processPayment(double amount) {
        // In a real application, this would connect to a banking API
        System.out.println("Processing bank transfer");
        System.out.println("Account name: " + accountName);
        System.out.println("Account number: " + accountNumber);
        System.out.println("Amount: $" + amount);
        System.out.println("Payment successful");
    }
}
