# Java Basics

Welcome to the first lesson in our Java programming course! This section covers the fundamental concepts of Java programming that will serve as the foundation for everything else you'll learn. Take your time to understand these concepts thoroughly before moving on.

## 1. Setting up Java Environment

Before you can start programming in Java, you need to set up your development environment.

### What is JDK?

The **Java Development Kit (JDK)** is a software package that provides everything you need to develop Java applications:
- The Java compiler (javac) to convert your code into bytecode
- The Java Virtual Machine (JVM) to run your programs
- Libraries and tools for development

### Installation Steps:

1. **Download the JDK**: Visit [Oracle's website](https://www.oracle.com/java/technologies/downloads/) or use OpenJDK
2. **Install the JDK**: Follow the installation wizard for your operating system
3. **Set up PATH variables**: This allows your computer to find Java commands from any directory
   - On Windows: Edit environment variables and add the JDK's bin directory to PATH
   - On macOS/Linux: Edit your .bash_profile or .bashrc file to add the JDK path

### Verifying Installation:

Open a command prompt or terminal and type:
```
java -version
javac -version
```

If you see version information instead of errors, your installation is successful!

### Integrated Development Environments (IDEs):

While you can write Java code in any text editor, IDEs make development easier:
- **IntelliJ IDEA**: Feature-rich, professional IDE (Community edition is free)
- **Eclipse**: Free, open-source IDE with many plugins
- **Visual Studio Code**: Lightweight editor with Java extensions

## 2. Your First Java Program: Hello World

Let's create your first Java program to understand the basic structure.

### The Hello World Program:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Understanding the Structure:

- **`public class HelloWorld`**: Defines a class named HelloWorld
  - In Java, everything is inside classes
  - The class name must match the filename (HelloWorld.java)

- **`public static void main(String[] args)`**: The main method
  - Every Java program starts execution from the main method
  - `public`: Accessible from anywhere
  - `static`: Belongs to the class, not an instance
  - `void`: Doesn't return any value
  - `main`: The name Java looks for to start execution
  - `String[] args`: Command-line arguments (we'll learn more later)

- **`System.out.println("Hello, World!");`**: Prints text to the console
  - `System`: A built-in class
  - `out`: The standard output stream
  - `println()`: A method that prints text and adds a new line

### Compiling and Running:

1. **Save the file** as HelloWorld.java
2. **Compile it** by opening a terminal and typing:
   ```
   javac HelloWorld.java
   ```
   This creates a HelloWorld.class file containing bytecode

3. **Run it** by typing:
   ```
   java HelloWorld
   ```

You should see "Hello, World!" printed on the screen. Congratulations on your first Java program!

## 3. Variables and Data Types

Variables are containers for storing data values. In Java, every variable has a specific type.

### Variable Declaration and Initialization:

```java
// Declaration
int age;

// Initialization
age = 25;

// Declaration and initialization in one line
int score = 95;
```

### Primitive Data Types:

Java has eight primitive data types for storing simple values:

1. **Integer Types**:
   - `byte`: 8-bit integer (-128 to 127)
   - `short`: 16-bit integer (-32,768 to 32,767)
   - `int`: 32-bit integer (about ±2 billion) - most commonly used
   - `long`: 64-bit integer (very large numbers) - use suffix 'L': `long population = 7800000000L;`

2. **Floating-Point Types**:
   - `float`: 32-bit decimal number - use suffix 'f': `float price = 19.99f;`
   - `double`: 64-bit decimal number (more precise) - default for decimals: `double pi = 3.14159;`

3. **Character Type**:
   - `char`: Stores a single character in single quotes: `char grade = 'A';`

4. **Boolean Type**:
   - `boolean`: Stores true or false values: `boolean isActive = true;`

### Reference Data Types:

These are more complex types that reference objects:

1. **String**: Stores text (sequence of characters)
   ```java
   String name = "John Doe";
   ```

2. **Arrays**: Stores multiple values of the same type (we'll cover these later)

3. **Classes**: Custom data types you define (we'll cover these in OOP sections)

### Constants:

Values that cannot be changed after initialization:

```java
final double PI = 3.14159;
// PI = 3.14; // This would cause an error
```

### Type Conversion:

Sometimes you need to convert between data types:

1. **Implicit conversion** (widening): Automatically done when converting to a larger data type
   ```java
   int myInt = 100;
   long myLong = myInt; // Automatic
   ```

2. **Explicit conversion** (narrowing): Requires casting when converting to a smaller data type
   ```java
   double myDouble = 9.78;
   int myInt = (int) myDouble; // Manual casting, myInt becomes 9
   ```

## 4. Basic Operators

Operators are symbols that perform operations on variables and values.

### Arithmetic Operators:

Used for mathematical calculations:
- `+` Addition: `int sum = 5 + 3;` (result: 8)
- `-` Subtraction: `int difference = 5 - 3;` (result: 2)
- `*` Multiplication: `int product = 5 * 3;` (result: 15)
- `/` Division: `int quotient = 10 / 3;` (result: 3, integer division)
- `%` Modulus (remainder): `int remainder = 10 % 3;` (result: 1)

### Increment and Decrement:

- `++` Increment: Adds 1 to a variable
  ```java
  int count = 5;
  count++; // Now count is 6
  ```
- `--` Decrement: Subtracts 1 from a variable
  ```java
  int count = 5;
  count--; // Now count is 4
  ```

Note the difference between prefix and postfix:
- Prefix (`++count`): Increment first, then use the value
- Postfix (`count++`): Use the value first, then increment

### Assignment Operators:

- `=` Simple assignment: `int x = 10;`
- `+=` Add and assign: `x += 5;` (same as `x = x + 5;`)
- `-=` Subtract and assign: `x -= 5;` (same as `x = x - 5;`)
- `*=` Multiply and assign: `x *= 5;` (same as `x = x * 5;`)
- `/=` Divide and assign: `x /= 5;` (same as `x = x / 5;`)
- `%=` Modulus and assign: `x %= 5;` (same as `x = x % 5;`)

### Comparison Operators:

Used to compare values (result is a boolean):
- `==` Equal to: `5 == 5` (true)
- `!=` Not equal to: `5 != 3` (true)
- `>` Greater than: `5 > 3` (true)
- `<` Less than: `5 < 3` (false)
- `>=` Greater than or equal to: `5 >= 5` (true)
- `<=` Less than or equal to: `3 <= 5` (true)

### Logical Operators:

Used to combine boolean values:
- `&&` Logical AND: `(5 > 3) && (10 > 5)` (true, both conditions are true)
- `||` Logical OR: `(5 > 10) || (10 > 5)` (true, at least one condition is true)
- `!` Logical NOT: `!(5 > 10)` (true, reverses the boolean value)

## 5. Input and Output

Communication with the user is essential in programming.

### Output with System.out:

Java provides several ways to display output:

1. **println()**: Prints and then moves to a new line
   ```java
   System.out.println("Hello, World!");
   ```

2. **print()**: Prints without moving to a new line
   ```java
   System.out.print("Hello, ");
   System.out.print("World!");
   // Output: Hello, World!
   ```

3. **printf()**: Formatted output (similar to C's printf)
   ```java
   System.out.printf("Name: %s, Age: %d%n", "John", 25);
   // Output: Name: John, Age: 25
   ```
   Common format specifiers:
   - `%s` for strings
   - `%d` for integers
   - `%f` for floating-point numbers
   - `%n` for a new line

### Input with Scanner:

To read user input, we use the Scanner class:

```java
import java.util.Scanner; // Import at the top of your file

// In your method:
Scanner scanner = new Scanner(System.in);

System.out.print("Enter your name: ");
String name = scanner.nextLine(); // Read a line of text

System.out.print("Enter your age: ");
int age = scanner.nextInt(); // Read an integer

System.out.println("Hello, " + name + "! You are " + age + " years old.");

// Always close the scanner when done
scanner.close();
```

Important Scanner methods:
- `nextLine()`: Reads a line of text
- `next()`: Reads a single word
- `nextInt()`: Reads an integer
- `nextDouble()`: Reads a double
- `nextBoolean()`: Reads a boolean

**Note**: After using `nextInt()`, `nextDouble()`, etc., call `nextLine()` to consume the newline character before reading more text.

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `HelloWorld.java`: Basic program structure
- `VariablesAndDataTypes.java`: Working with different data types
- `BasicOperators.java`: Using various operators
- `InputAndOutput.java`: Reading input and displaying output

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Create a personalized Hello World program
2. `Exercise2.java`: Calculate the area and perimeter of a rectangle
3. `Exercise3.java`: Create an interactive program that calculates age

Complete these exercises to practice what you've learned. Remember, programming is learned by doing!

## Next Steps

After completing this section, proceed to the "Control Flow" section to learn about conditional statements and loops. These concepts will allow you to make decisions in your code and repeat actions.
