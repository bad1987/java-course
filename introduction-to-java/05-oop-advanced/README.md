# Advanced Object-Oriented Programming in Java

Welcome to the fifth lesson in our Java programming course! In this section, we'll explore advanced object-oriented programming concepts in Java. These concepts build upon the fundamentals you learned in the previous section and will enable you to create more sophisticated and flexible applications.

## 1. Inheritance

Inheritance is a mechanism that allows a class to inherit properties and behaviors from another class. It promotes code reuse and establishes a relationship between a parent class (superclass) and a child class (subclass).

### Basic Inheritance

```java
// Parent class (superclass)
public class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println(name + " is eating.");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

// Child class (subclass)
public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // Call the parent class constructor
        this.breed = breed;
    }
    
    public void bark() {
        System.out.println(name + " is barking.");
    }
}
```

### Using Inheritance

```java
// Create a Dog object
Dog myDog = new Dog("Buddy", 3, "Golden Retriever");

// Call methods from the parent class
myDog.eat();    // Output: Buddy is eating.
myDog.sleep();  // Output: Buddy is sleeping.

// Call method from the child class
myDog.bark();   // Output: Buddy is barking.
```

### Key Points About Inheritance

1. **`extends` Keyword**: Used to establish inheritance between classes.
2. **`super` Keyword**: Used to call the parent class constructor or methods.
3. **Access to Parent Members**: A subclass inherits all non-private members (fields and methods) from its superclass.
4. **Single Inheritance**: Java supports single inheritance for classes (a class can extend only one class).
5. **Multiple Inheritance**: Java doesn't support multiple inheritance for classes (a class cannot extend multiple classes), but it can be achieved through interfaces.

## 2. Method Overriding

Method overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. This allows a subclass to provide its own behavior for an inherited method.

### Example of Method Overriding

```java
// Parent class
public class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

// Child class overriding the makeSound method
public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override  // This annotation is optional but recommended
    public void makeSound() {
        System.out.println(name + " meows");
    }
}

// Another child class overriding the makeSound method
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks");
    }
}
```

### Using Method Overriding

```java
Animal genericAnimal = new Animal("Generic");
Cat myCat = new Cat("Whiskers");
Dog myDog = new Dog("Rex");

genericAnimal.makeSound();  // Output: Animal makes a sound
myCat.makeSound();          // Output: Whiskers meows
myDog.makeSound();          // Output: Rex barks
```

### Rules for Method Overriding

1. The method in the subclass must have the same name as the method in the superclass.
2. The method in the subclass must have the same parameter list as the method in the superclass.
3. The return type must be the same or a subtype of the return type in the superclass (covariant return type).
4. The access level cannot be more restrictive than the overridden method's access level.
5. The overriding method cannot throw broader checked exceptions than the overridden method.

## 3. The `super` Keyword

The `super` keyword refers to the superclass (parent) of the current object. It is used to:

1. Call the superclass constructor
2. Access superclass methods
3. Access superclass fields

### Using `super` to Call the Superclass Constructor

```java
public class Vehicle {
    protected String make;
    protected String model;
    
    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }
}

public class Car extends Vehicle {
    private int numDoors;
    
    public Car(String make, String model, int numDoors) {
        super(make, model);  // Call the parent constructor
        this.numDoors = numDoors;
    }
}
```

### Using `super` to Access Superclass Methods

```java
public class Animal {
    public void eat() {
        System.out.println("Animal is eating");
    }
}

public class Dog extends Animal {
    @Override
    public void eat() {
        super.eat();  // Call the parent's eat method
        System.out.println("Dog is eating a bone");
    }
}
```

### Using `super` to Access Superclass Fields

```java
public class Person {
    protected String name;
    
    public Person(String name) {
        this.name = name;
    }
}

public class Employee extends Person {
    private String name;  // This hides the parent's name field
    
    public Employee(String personName, String employeeName) {
        super(personName);
        this.name = employeeName;
    }
    
    public void displayNames() {
        System.out.println("Person name: " + super.name);
        System.out.println("Employee name: " + this.name);
    }
}
```

## 4. Polymorphism

Polymorphism allows objects of different classes to be treated as objects of a common superclass. The most common use of polymorphism is when a parent class reference is used to refer to a child class object.

### Example of Polymorphism

```java
// Parent class
public class Shape {
    public double calculateArea() {
        return 0;  // Default implementation
    }
}

// Child classes
public class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class Rectangle extends Shape {
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
```

### Using Polymorphism

```java
// Create an array of Shape references
Shape[] shapes = new Shape[3];

// Assign different types of shapes to the array
shapes[0] = new Circle(5.0);
shapes[1] = new Rectangle(4.0, 6.0);
shapes[2] = new Circle(3.0);

// Calculate and display areas polymorphically
for (Shape shape : shapes) {
    System.out.println("Area: " + shape.calculateArea());
}
```

### Benefits of Polymorphism

1. **Flexibility**: Code written to use a superclass can work with any subclass.
2. **Extensibility**: New subclasses can be added without changing existing code.
3. **Simplicity**: Complex inheritance hierarchies can be treated uniformly.

## 5. Abstract Classes

An abstract class is a class that cannot be instantiated and is designed to be subclassed. It may contain both abstract methods (methods without a body) and concrete methods (methods with a body).

### Example of an Abstract Class

```java
// Abstract class
public abstract class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    // Abstract method (no implementation)
    public abstract void makeSound();
    
    // Concrete method (with implementation)
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

// Concrete subclass
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    // Must implement the abstract method
    @Override
    public void makeSound() {
        System.out.println(name + " barks");
    }
}
```

### Using Abstract Classes

```java
// Cannot instantiate an abstract class
// Animal animal = new Animal("Generic");  // This would cause a compilation error

// Can create an instance of a concrete subclass
Dog dog = new Dog("Rex");
dog.makeSound();  // Output: Rex barks
dog.sleep();      // Output: Rex is sleeping
```

### Key Points About Abstract Classes

1. Abstract classes cannot be instantiated.
2. Abstract classes may contain abstract methods and concrete methods.
3. A class that extends an abstract class must implement all of its abstract methods, or it must also be declared abstract.
4. Abstract classes can have constructors, which are called when a concrete subclass is instantiated.

## 6. Interfaces

An interface is a reference type in Java that contains only constants, method signatures, default methods, static methods, and nested types. It cannot be instantiated—it can only be implemented by classes or extended by other interfaces.

### Example of an Interface

```java
// Interface definition
public interface Drawable {
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

// Class implementing the interface
public class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    // Must implement the abstract method
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}
```

### Using Interfaces

```java
// Create an object that implements the interface
Circle circle = new Circle(5.0);

// Call the implemented method
circle.draw();  // Output: Drawing a circle with radius 5.0

// Call the default method
circle.displayCategory();  // Output: Category: Graphics

// Use the static method
boolean isDrawable = Drawable.isDrawable(circle);  // true
```

### Multiple Interfaces

A class can implement multiple interfaces, which allows for a form of multiple inheritance in Java:

```java
public interface Drawable {
    void draw();
}

public interface Resizable {
    void resize(double factor);
}

// Class implementing multiple interfaces
public class Rectangle implements Drawable, Resizable {
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
    
    @Override
    public void resize(double factor) {
        length *= factor;
        width *= factor;
        System.out.println("Resized rectangle: length = " + length + ", width = " + width);
    }
}
```

### Functional Interfaces

A functional interface is an interface that contains exactly one abstract method. These interfaces can be used with lambda expressions and method references in Java 8 and later.

```java
// Functional interface
@FunctionalInterface
public interface Calculable {
    double calculate(double x, double y);
}

// Using a functional interface with a lambda expression
public class Calculator {
    public static double performOperation(double x, double y, Calculable operation) {
        return operation.calculate(x, y);
    }
    
    public static void main(String[] args) {
        // Addition using lambda
        double sum = performOperation(5.0, 3.0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);  // Output: Sum: 8.0
        
        // Multiplication using lambda
        double product = performOperation(5.0, 3.0, (a, b) -> a * b);
        System.out.println("Product: " + product);  // Output: Product: 15.0
    }
}
```

## 7. Packages and Imports

Packages in Java are used to organize classes and interfaces into namespaces. They help prevent naming conflicts and provide access control.

### Creating a Package

To create a package, you use the `package` statement at the beginning of your Java file:

```java
// File: com/example/shapes/Circle.java
package com.example.shapes;

public class Circle {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
```

### Importing Classes

To use a class from another package, you need to import it:

```java
// File: com/example/app/Main.java
package com.example.app;

// Import a specific class
import com.example.shapes.Circle;

// Import all classes from a package
// import com.example.shapes.*;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0);
        System.out.println("Area: " + circle.getArea());
    }
}
```

### Types of Imports

1. **Single Type Import**: Imports a single class or interface.
   ```java
   import java.util.ArrayList;
   ```

2. **Import on Demand**: Imports all classes from a package.
   ```java
   import java.util.*;
   ```

3. **Static Import**: Imports static members (fields and methods) from a class.
   ```java
   import static java.lang.Math.PI;
   import static java.lang.Math.sqrt;
   ```

### The `java.lang` Package

The `java.lang` package is automatically imported into all Java programs. It contains fundamental classes like `String`, `Object`, `System`, etc.

## 8. Final Keyword

The `final` keyword in Java can be applied to classes, methods, and variables, with different effects:

### Final Classes

A final class cannot be subclassed (extended):

```java
public final class ImmutableString {
    private final String value;
    
    public ImmutableString(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}

// This would cause a compilation error
// public class ExtendedString extends ImmutableString { }
```

### Final Methods

A final method cannot be overridden by subclasses:

```java
public class Parent {
    public final void cannotOverride() {
        System.out.println("This method cannot be overridden");
    }
}

public class Child extends Parent {
    // This would cause a compilation error
    // @Override
    // public void cannotOverride() { }
}
```

### Final Variables

A final variable can only be assigned once:

```java
public class Constants {
    // Final primitive variable
    public final int MAX_SIZE = 100;
    
    // Final reference variable (the reference cannot change, but the object's state can)
    public final StringBuilder builder = new StringBuilder();
    
    public void demonstrate() {
        // This would cause a compilation error
        // MAX_SIZE = 200;
        
        // This is allowed (modifying the object's state)
        builder.append("Hello");
        
        // This would cause a compilation error (changing the reference)
        // builder = new StringBuilder();
    }
}
```

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `InheritanceDemo.java`: Demonstrates inheritance and method overriding
- `PolymorphismDemo.java`: Shows polymorphic behavior with different shapes
- `AbstractClassDemo.java`: Uses abstract classes to define common behavior
- `InterfaceDemo.java`: Implements and uses interfaces
- `PackageDemo.java`: Organizes classes into packages
- `FinalKeywordDemo.java`: Shows different uses of the `final` keyword

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Create a hierarchy of shape classes with inheritance
2. `Exercise2.java`: Implement a banking system using polymorphism
3. `Exercise3.java`: Design a media player with interfaces
4. `Exercise4.java`: Create a package structure for a library management system

Complete these exercises to practice what you've learned. Advanced OOP concepts are best understood through application and practice!

## Next Steps

After completing this section, proceed to the "Exception Handling" section to learn about handling errors and exceptions in Java. This will help you write more robust and error-resistant code.
