# Arrays and Methods in Java

Welcome to the third lesson in our Java programming course! In this section, you'll learn about arrays for storing collections of data and methods for organizing code into reusable blocks. These concepts are essential for writing more complex and efficient programs.

## 1. Arrays

Arrays are used to store multiple values of the same type in a single variable. They provide a convenient way to group related data together.

### Creating and Initializing Arrays

There are several ways to create arrays in Java:

#### Declaration and Initialization in Separate Steps:

```java
// Declare an array of integers
int[] numbers;

// Create an array with space for 5 integers
numbers = new int[5];

// Now the array contains 5 integers, all initialized to 0
```

#### Declaration and Initialization in One Step:

```java
// Declare and create an array in one step
int[] numbers = new int[5];

// Initialize array elements individually
numbers[0] = 10;
numbers[1] = 20;
numbers[2] = 30;
numbers[3] = 40;
numbers[4] = 50;
```

#### Array Initialization with Values:

```java
// Declare, create, and initialize an array in one step
int[] numbers = {10, 20, 30, 40, 50};

// This creates an array of length 5 with the specified values
```

### Accessing Array Elements

Array elements are accessed using their index, which starts at 0:

```java
int[] numbers = {10, 20, 30, 40, 50};

// Access the first element (index 0)
System.out.println("First element: " + numbers[0]);  // Output: 10

// Access the third element (index 2)
System.out.println("Third element: " + numbers[2]);  // Output: 30

// Modify an element
numbers[1] = 25;
System.out.println("Modified second element: " + numbers[1]);  // Output: 25
```

### Array Length

The length of an array is fixed when it's created and can be accessed using the `length` property:

```java
int[] numbers = {10, 20, 30, 40, 50};
System.out.println("Array length: " + numbers.length);  // Output: 5
```

### Iterating Through Arrays

There are several ways to iterate through arrays:

#### Using a for Loop:

```java
int[] numbers = {10, 20, 30, 40, 50};

// Using a standard for loop
for (int i = 0; i < numbers.length; i++) {
    System.out.println("Element at index " + i + ": " + numbers[i]);
}
```

#### Using an Enhanced for Loop (for-each):

```java
int[] numbers = {10, 20, 30, 40, 50};

// Using an enhanced for loop
for (int number : numbers) {
    System.out.println("Element: " + number);
}
```

### Multidimensional Arrays

Java supports multidimensional arrays, which are essentially arrays of arrays:

#### Two-Dimensional Arrays:

```java
// Declare and create a 2D array (3 rows, 4 columns)
int[][] matrix = new int[3][4];

// Initialize a specific element
matrix[1][2] = 42;

// Access an element
System.out.println("Element at row 1, column 2: " + matrix[1][2]);  // Output: 42

// Initialize a 2D array with values
int[][] anotherMatrix = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};
```

#### Iterating Through 2D Arrays:

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Using nested for loops
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();  // New line after each row
}

// Output:
// 1 2 3
// 4 5 6
// 7 8 9
```

### Common Array Operations

#### Copying Arrays:

```java
// Using Arrays.copyOf()
int[] original = {1, 2, 3, 4, 5};
int[] copy = Arrays.copyOf(original, original.length);

// Using System.arraycopy()
int[] source = {1, 2, 3, 4, 5};
int[] destination = new int[5];
System.arraycopy(source, 0, destination, 0, source.length);
```

#### Sorting Arrays:

```java
int[] numbers = {5, 2, 9, 1, 7};
Arrays.sort(numbers);
// Now numbers contains {1, 2, 5, 7, 9}
```

#### Searching in Arrays:

```java
int[] numbers = {1, 2, 5, 7, 9};
int index = Arrays.binarySearch(numbers, 5);
System.out.println("Index of 5: " + index);  // Output: 2
```

## 2. Methods

Methods are blocks of code that perform a specific task. They help organize code, make it reusable, and improve readability.

### Defining Methods

The basic syntax for defining a method:

```java
accessModifier returnType methodName(parameterList) {
    // Method body
    // Code to be executed
    return value;  // If the return type is not void
}
```

Example:

```java
// A simple method that adds two numbers
public static int add(int a, int b) {
    int sum = a + b;
    return sum;
}
```

Components of a method:
- **Access Modifier**: Determines the visibility of the method (e.g., `public`, `private`)
- **Return Type**: The data type of the value returned by the method (or `void` if no value is returned)
- **Method Name**: The name used to call the method
- **Parameter List**: Input values the method needs to perform its task
- **Method Body**: The code that executes when the method is called
- **Return Statement**: Returns a value to the caller (if the return type is not `void`)

### Calling Methods

To use a method, you call it by its name and provide any required arguments:

```java
// Define a method
public static int add(int a, int b) {
    return a + b;
}

// Call the method
int result = add(5, 3);
System.out.println("5 + 3 = " + result);  // Output: 5 + 3 = 8
```

### Method Parameters

Parameters are variables that receive values when the method is called:

```java
// Method with parameters
public static void greet(String name, int age) {
    System.out.println("Hello, " + name + "! You are " + age + " years old.");
}

// Call the method with arguments
greet("Alice", 25);  // Output: Hello, Alice! You are 25 years old.
```

### Return Values

Methods can return values using the `return` statement:

```java
// Method that returns a value
public static double calculateArea(double radius) {
    return Math.PI * radius * radius;
}

// Call the method and use the returned value
double area = calculateArea(5.0);
System.out.println("Area of circle: " + area);
```

### Void Methods

Methods that don't return a value have a return type of `void`:

```java
// Void method (doesn't return a value)
public static void printMessage(String message) {
    System.out.println("Message: " + message);
    // No return statement needed
}

// Call the void method
printMessage("Hello, World!");  // Output: Message: Hello, World!
```

### Method Overloading

Method overloading allows you to define multiple methods with the same name but different parameters:

```java
// Method to add two integers
public static int add(int a, int b) {
    return a + b;
}

// Overloaded method to add three integers
public static int add(int a, int b, int c) {
    return a + b + c;
}

// Overloaded method to add two doubles
public static double add(double a, double b) {
    return a + b;
}

// Call the different versions
System.out.println(add(5, 3));         // Calls the first method: 8
System.out.println(add(5, 3, 2));      // Calls the second method: 10
System.out.println(add(5.5, 3.5));     // Calls the third method: 9.0
```

The compiler determines which method to call based on the number and types of arguments.

### Passing Arrays to Methods

Arrays can be passed to methods just like any other variable:

```java
// Method that accepts an array parameter
public static double calculateAverage(int[] numbers) {
    int sum = 0;
    for (int number : numbers) {
        sum += number;
    }
    return (double) sum / numbers.length;
}

// Call the method with an array
int[] scores = {85, 90, 78, 92, 88};
double average = calculateAverage(scores);
System.out.println("Average score: " + average);
```

### Recursion Basics

Recursion is when a method calls itself:

```java
// Recursive method to calculate factorial
public static int factorial(int n) {
    // Base case
    if (n == 0 || n == 1) {
        return 1;
    }
    // Recursive case
    else {
        return n * factorial(n - 1);
    }
}

// Call the recursive method
int result = factorial(5);  // 5! = 5 * 4 * 3 * 2 * 1 = 120
System.out.println("5! = " + result);
```

Every recursive method needs:
1. A base case that stops the recursion
2. A recursive case that calls the method with a modified argument

## 3. Java Built-in Methods

Java provides many built-in methods that you can use in your programs.

### String Methods

Strings in Java come with many useful methods:

```java
String text = "Hello, Java!";

// Length of a string
System.out.println("Length: " + text.length());  // Output: 12

// Convert to uppercase/lowercase
System.out.println("Uppercase: " + text.toUpperCase());
System.out.println("Lowercase: " + text.toLowerCase());

// Check if a string contains a substring
System.out.println("Contains 'Java': " + text.contains("Java"));  // Output: true

// Replace parts of a string
String newText = text.replace("Java", "World");
System.out.println("After replace: " + newText);  // Output: Hello, World!

// Split a string
String sentence = "Java is a programming language";
String[] words = sentence.split(" ");
for (String word : words) {
    System.out.println(word);
}
```

### Math Class Methods

The `Math` class provides mathematical functions:

```java
// Basic math operations
System.out.println("Absolute value: " + Math.abs(-5));       // Output: 5
System.out.println("Maximum: " + Math.max(10, 20));          // Output: 20
System.out.println("Minimum: " + Math.min(10, 20));          // Output: 10
System.out.println("Square root: " + Math.sqrt(25));         // Output: 5.0
System.out.println("Power: " + Math.pow(2, 3));              // Output: 8.0

// Rounding
System.out.println("Round: " + Math.round(3.7));             // Output: 4
System.out.println("Ceiling: " + Math.ceil(3.1));            // Output: 4.0
System.out.println("Floor: " + Math.floor(3.9));             // Output: 3.0

// Trigonometric functions
System.out.println("Sine: " + Math.sin(Math.PI / 2));        // Output: 1.0
System.out.println("Cosine: " + Math.cos(0));                // Output: 1.0

// Random number
System.out.println("Random: " + Math.random());              // Output: Random number between 0.0 and 1.0
```

### Arrays Utility Methods

The `Arrays` class provides utility methods for working with arrays:

```java
import java.util.Arrays;

int[] numbers = {5, 2, 9, 1, 7};

// Sort an array
Arrays.sort(numbers);
System.out.println("Sorted array: " + Arrays.toString(numbers));  // Output: [1, 2, 5, 7, 9]

// Binary search (array must be sorted)
int index = Arrays.binarySearch(numbers, 5);
System.out.println("Index of 5: " + index);  // Output: 2

// Fill an array with a value
int[] newArray = new int[5];
Arrays.fill(newArray, 10);
System.out.println("Filled array: " + Arrays.toString(newArray));  // Output: [10, 10, 10, 10, 10]

// Compare arrays
int[] array1 = {1, 2, 3};
int[] array2 = {1, 2, 3};
System.out.println("Arrays equal: " + Arrays.equals(array1, array2));  // Output: true

// Copy an array
int[] copy = Arrays.copyOf(numbers, numbers.length);
System.out.println("Copied array: " + Arrays.toString(copy));
```

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `ArrayBasics.java`: Creating, accessing, and iterating through arrays
- `MultidimensionalArrays.java`: Working with 2D arrays
- `ArrayOperations.java`: Common operations like sorting and searching
- `MethodBasics.java`: Defining and calling methods
- `MethodOverloading.java`: Creating methods with the same name but different parameters
- `RecursionExamples.java`: Solving problems using recursive methods
- `StringMethodsDemo.java`: Using built-in String methods
- `MathMethodsDemo.java`: Using the Math class

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Create and manipulate arrays to store student scores
2. `Exercise2.java`: Implement methods to perform various calculations
3. `Exercise3.java`: Create a simple word processing program using String methods
4. `Exercise4.java`: Solve problems using recursion

Complete these exercises to practice what you've learned. Remember, the best way to learn programming is by writing code!

## Next Steps

After completing this section, you'll be ready to move on to object-oriented programming concepts, where you'll learn about classes, objects, and the principles of OOP. These concepts will build on the foundation you've established with arrays and methods, allowing you to create more complex and organized programs.
