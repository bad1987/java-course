# Control Flow in Java

Welcome to the second lesson in our Java programming course! In this section, you'll learn about control flow statements, which allow your programs to make decisions and repeat actions. These concepts are fundamental to creating dynamic and interactive programs.

## 1. Conditional Statements

Conditional statements allow your program to execute different code blocks based on whether a condition is true or false.

### Simple if Statement

The simplest form of conditional execution:

```java
if (condition) {
    // Code to execute if condition is true
}
```

Example:
```java
int age = 18;

if (age >= 18) {
    System.out.println("You are an adult.");
}
```

In this example, "You are an adult" will be printed because the condition `age >= 18` is true.

### if-else Statement

Executes one block of code if the condition is true and another if it's false:

```java
if (condition) {
    // Code to execute if condition is true
} else {
    // Code to execute if condition is false
}
```

Example:
```java
int age = 16;

if (age >= 18) {
    System.out.println("You are an adult.");
} else {
    System.out.println("You are a minor.");
}
```

In this example, "You are a minor" will be printed because the condition is false.

### if-else-if Ladder

Used when you need to check multiple conditions:

```java
if (condition1) {
    // Code to execute if condition1 is true
} else if (condition2) {
    // Code to execute if condition1 is false and condition2 is true
} else if (condition3) {
    // Code to execute if condition1 and condition2 are false and condition3 is true
} else {
    // Code to execute if all conditions are false
}
```

Example:
```java
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
```

In this example, "Grade: B" will be printed because the score is 85.

### Nested if Statements

You can place if statements inside other if statements:

```java
if (outerCondition) {
    // Code for outer condition
    if (innerCondition) {
        // Code for both outer and inner conditions
    }
}
```

Example:
```java
int age = 25;
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
```

This will print:
```
Age requirement met.
You can drive.
```

### Ternary Operator

A shorthand for simple if-else statements:

```java
variable = (condition) ? valueIfTrue : valueIfFalse;
```

Example:
```java
int age = 20;
String status = (age >= 18) ? "adult" : "minor";
System.out.println("You are a " + status);
```

This will print "You are a adult".

## 2. Switch Statements

The switch statement is an alternative to if-else-if ladder when you need to select one of many code blocks to execute.

### Basic Switch Syntax

```java
switch (expression) {
    case value1:
        // Code to execute if expression equals value1
        break;
    case value2:
        // Code to execute if expression equals value2
        break;
    // More cases...
    default:
        // Code to execute if expression doesn't match any case
}
```

Example:
```java
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

System.out.println("Day: " + dayName);
```

This will print "Day: Wednesday".

### Important Points About Switch

1. The expression must evaluate to a byte, short, char, int, enum, String (Java 7+), or wrapper classes of these types.
2. The `break` statement is used to exit the switch block. Without it, execution will "fall through" to the next case.
3. The `default` case is optional and executes when no case matches.

### Fall-through Behavior

Without a break statement, execution continues to the next case:

```java
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

System.out.println("Season: " + season);
```

This will print "Season: Summer".

### Switch Expressions (Java 12+)

Modern Java introduced enhanced switch expressions:

```java
// Traditional switch statement
int day = 3;
String dayType;
switch (day) {
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        dayType = "Weekday";
        break;
    case 6:
    case 7:
        dayType = "Weekend";
        break;
    default:
        dayType = "Invalid day";
}

// Modern switch expression (Java 12+)
dayType = switch (day) {
    case 1, 2, 3, 4, 5 -> "Weekday";
    case 6, 7 -> "Weekend";
    default -> "Invalid day";
};
```

## 3. Loops

Loops allow you to execute a block of code repeatedly.

### for Loop

Used when you know how many times you want to execute a block of code:

```java
for (initialization; condition; update) {
    // Code to repeat
}
```

Example:
```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Count: " + i);
}
```

This will print:
```
Count: 1
Count: 2
Count: 3
Count: 4
Count: 5
```

The loop components:
- **Initialization**: Executed once before the loop starts (`int i = 1`)
- **Condition**: Checked before each iteration; loop continues if true (`i <= 5`)
- **Update**: Executed after each iteration (`i++`)

### while Loop

Used when you want to repeat a block of code as long as a condition is true:

```java
while (condition) {
    // Code to repeat
}
```

Example:
```java
int count = 1;
while (count <= 5) {
    System.out.println("Count: " + count);
    count++;
}
```

This produces the same output as the for loop example.

### do-while Loop

Similar to while, but guarantees that the code block executes at least once:

```java
do {
    // Code to repeat
} while (condition);
```

Example:
```java
int count = 1;
do {
    System.out.println("Count: " + count);
    count++;
} while (count <= 5);
```

The key difference from a while loop is that the condition is checked after the code block executes.

### Enhanced for Loop (for-each)

Used to iterate through arrays and collections:

```java
for (elementType element : collection) {
    // Code to process element
}
```

Example:
```java
int[] numbers = {1, 2, 3, 4, 5};
for (int number : numbers) {
    System.out.println("Number: " + number);
}
```

This will print each number in the array.

## 4. Control Flow Modifiers

These statements modify the normal flow of control in loops and other control structures.

### break Statement

Terminates the loop or switch statement:

```java
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        break; // Exit the loop when i equals 5
    }
    System.out.println("Count: " + i);
}
```

This will print:
```
Count: 1
Count: 2
Count: 3
Count: 4
```

### continue Statement

Skips the current iteration and continues with the next one:

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        continue; // Skip iteration when i equals 3
    }
    System.out.println("Count: " + i);
}
```

This will print:
```
Count: 1
Count: 2
Count: 4
Count: 5
```

### return Statement

Exits the current method (we'll cover this more in the Methods section):

```java
public static void checkNumber(int number) {
    if (number < 0) {
        System.out.println("Negative number detected");
        return; // Exit the method
    }
    System.out.println("Processing positive number: " + number);
}
```

### Labeled Statements

Allow you to break or continue outer loops:

```java
outerLoop: for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        if (i == 2 && j == 2) {
            break outerLoop; // Break out of both loops
        }
        System.out.println("i = " + i + ", j = " + j);
    }
}
```

This will print:
```
i = 1, j = 1
i = 1, j = 2
i = 1, j = 3
i = 2, j = 1
```

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `ConditionalStatements.java`: Using if, if-else, and if-else-if statements
- `SwitchExample.java`: Using switch statements for different scenarios
- `LoopExamples.java`: Demonstrating various loop types
- `ControlFlowModifiers.java`: Using break, continue, and labeled statements

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Create a program that determines grades based on scores
2. `Exercise2.java`: Build a simple calculator using switch statements
3. `Exercise3.java`: Generate patterns using nested loops
4. `Exercise4.java`: Create a number guessing game using loops and conditionals

Complete these exercises to practice what you've learned. Remember, the best way to learn programming is by writing code!

## Next Steps

After completing this section, proceed to the "Arrays and Methods" section to learn about data structures and code organization. These concepts will help you manage larger amounts of data and write more modular, reusable code.
