# Java Collections Framework

Welcome to the seventh lesson in our Java programming course! In this section, you'll learn about the Java Collections Framework, which provides a set of classes and interfaces for storing and manipulating groups of objects. Collections are essential for managing data in Java applications, offering efficient ways to store, retrieve, and process data.

## 1. Introduction to Collections

The Java Collections Framework is a unified architecture for representing and manipulating collections. It contains:

- **Interfaces**: Abstract data types representing collections
- **Implementations**: Concrete implementations of the collection interfaces
- **Algorithms**: Methods that perform useful computations on collections

### Collection Hierarchy

The main interfaces in the Collections Framework include:

- **Collection**: The root interface with basic methods like `add()`, `remove()`, and `contains()`
  - **List**: An ordered collection that allows duplicate elements
  - **Set**: A collection that cannot contain duplicate elements
  - **Queue**: A collection designed for holding elements prior to processing

- **Map**: An object that maps keys to values (not a true Collection)

## 2. Lists

A List is an ordered collection that can contain duplicate elements. The main implementations are:

### ArrayList

An ArrayList is a resizable array implementation of the List interface:

```java
// Creating an ArrayList
List<String> names = new ArrayList<>();

// Adding elements
names.add("Alice");
names.add("Bob");
names.add("Charlie");

// Accessing elements by index
String firstPerson = names.get(0);  // "Alice"

// Size of the list
int size = names.size();  // 3

// Iterating through the list
for (String name : names) {
    System.out.println(name);
}

// Removing elements
names.remove("Bob");
names.remove(0);  // Removes "Alice"

// Checking if an element exists
boolean containsDavid = names.contains("David");  // false
```

### LinkedList

A LinkedList is a doubly-linked list implementation of the List interface:

```java
// Creating a LinkedList
List<Integer> numbers = new LinkedList<>();

// Adding elements
numbers.add(10);
numbers.add(20);
numbers.add(30);

// LinkedList specific operations
LinkedList<Integer> linkedNumbers = (LinkedList<Integer>) numbers;
linkedNumbers.addFirst(5);  // Adds at the beginning
linkedNumbers.addLast(40);  // Adds at the end
int first = linkedNumbers.getFirst();  // 5
int last = linkedNumbers.getLast();    // 40
```

### Comparing ArrayList and LinkedList

- **ArrayList**:
  - Faster for random access (get operations)
  - Better for storing and accessing data
  - Uses more memory when capacity increases

- **LinkedList**:
  - Faster for insertions and deletions
  - Better for manipulating data
  - Uses more memory per element (stores references to previous and next elements)

## 3. Sets

A Set is a collection that cannot contain duplicate elements. The main implementations are:

### HashSet

A HashSet is an unordered Set implementation backed by a hash table:

```java
// Creating a HashSet
Set<String> fruits = new HashSet<>();

// Adding elements
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");
fruits.add("Apple");  // Duplicate, will not be added

// Size of the set
int size = fruits.size();  // 3, not 4 (no duplicates)

// Checking if an element exists
boolean containsApple = fruits.contains("Apple");  // true

// Removing elements
fruits.remove("Banana");

// Iterating through the set
for (String fruit : fruits) {
    System.out.println(fruit);
}
```

### TreeSet

A TreeSet is a sorted Set implementation backed by a tree structure:

```java
// Creating a TreeSet
Set<String> names = new TreeSet<>();

// Adding elements
names.add("Charlie");
names.add("Alice");
names.add("Bob");

// Elements are automatically sorted
// Iterating will print: Alice, Bob, Charlie
for (String name : names) {
    System.out.println(name);
}
```

### LinkedHashSet

A LinkedHashSet is a Set implementation that maintains insertion order:

```java
// Creating a LinkedHashSet
Set<String> colors = new LinkedHashSet<>();

// Adding elements
colors.add("Red");
colors.add("Green");
colors.add("Blue");

// Iterating will maintain insertion order: Red, Green, Blue
for (String color : colors) {
    System.out.println(color);
}
```

## 4. Maps

A Map is an object that maps keys to values. A map cannot contain duplicate keys, and each key can map to at most one value. The main implementations are:

### HashMap

A HashMap is an unordered Map implementation backed by a hash table:

```java
// Creating a HashMap
Map<String, Integer> ages = new HashMap<>();

// Adding key-value pairs
ages.put("Alice", 25);
ages.put("Bob", 30);
ages.put("Charlie", 35);

// Accessing values by key
int aliceAge = ages.get("Alice");  // 25

// Checking if a key exists
boolean containsBob = ages.containsKey("Bob");  // true

// Checking if a value exists
boolean contains30 = ages.containsValue(30);  // true

// Removing entries
ages.remove("Bob");

// Size of the map
int size = ages.size();  // 2 after removing Bob

// Iterating through the map
for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// Iterating through keys
for (String name : ages.keySet()) {
    System.out.println(name);
}

// Iterating through values
for (int age : ages.values()) {
    System.out.println(age);
}
```

### TreeMap

A TreeMap is a sorted Map implementation backed by a tree structure:

```java
// Creating a TreeMap
Map<String, Double> grades = new TreeMap<>();

// Adding key-value pairs
grades.put("Charlie", 85.5);
grades.put("Alice", 92.3);
grades.put("Bob", 88.7);

// Keys are automatically sorted
// Iterating will print: Alice, Bob, Charlie
for (String name : grades.keySet()) {
    System.out.println(name + ": " + grades.get(name));
}
```

### LinkedHashMap

A LinkedHashMap is a Map implementation that maintains insertion order:

```java
// Creating a LinkedHashMap
Map<String, String> capitals = new LinkedHashMap<>();

// Adding key-value pairs
capitals.put("USA", "Washington D.C.");
capitals.put("UK", "London");
capitals.put("France", "Paris");

// Iterating will maintain insertion order
for (Map.Entry<String, String> entry : capitals.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

## 5. Queues and Deques

### Queue

A Queue is a collection designed for holding elements prior to processing. It follows the First-In-First-Out (FIFO) principle:

```java
// Creating a Queue (using LinkedList implementation)
Queue<String> queue = new LinkedList<>();

// Adding elements
queue.add("First");
queue.add("Second");
queue.add("Third");

// Examining the head of the queue without removing it
String head = queue.peek();  // "First"

// Removing and returning the head of the queue
String removed = queue.poll();  // "First"

// Size of the queue
int size = queue.size();  // 2 after removing "First"
```

### PriorityQueue

A PriorityQueue is a queue that orders elements according to their natural ordering or a specified comparator:

```java
// Creating a PriorityQueue
Queue<Integer> priorityQueue = new PriorityQueue<>();

// Adding elements
priorityQueue.add(30);
priorityQueue.add(10);
priorityQueue.add(20);

// Elements are dequeued in priority order
while (!priorityQueue.isEmpty()) {
    System.out.println(priorityQueue.poll());  // Prints: 10, 20, 30
}
```

### Deque

A Deque (double-ended queue) is a linear collection that supports element insertion and removal at both ends:

```java
// Creating a Deque
Deque<String> deque = new ArrayDeque<>();

// Adding elements at both ends
deque.addFirst("First");
deque.addLast("Last");

// Examining elements at both ends without removing
String first = deque.peekFirst();  // "First"
String last = deque.peekLast();    // "Last"

// Removing elements from both ends
String removedFirst = deque.pollFirst();  // "First"
String removedLast = deque.pollLast();    // "Last"
```

## 6. Utility Classes

### Collections

The Collections class provides static methods for operating on collections:

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(3);
numbers.add(1);
numbers.add(2);

// Sorting a list
Collections.sort(numbers);  // [1, 2, 3]

// Binary search (on a sorted list)
int index = Collections.binarySearch(numbers, 2);  // 1

// Finding min and max
int min = Collections.min(numbers);  // 1
int max = Collections.max(numbers);  // 3

// Reversing a list
Collections.reverse(numbers);  // [3, 2, 1]

// Shuffling a list
Collections.shuffle(numbers);

// Creating unmodifiable collections
List<String> immutableList = Collections.unmodifiableList(new ArrayList<>());

// Creating singleton collections
Set<String> singletonSet = Collections.singleton("Only Element");
```

### Arrays

The Arrays class provides static methods for operating on arrays:

```java
// Creating and initializing an array
int[] numbers = {3, 1, 4, 1, 5, 9};

// Sorting an array
Arrays.sort(numbers);

// Binary search
int index = Arrays.binarySearch(numbers, 4);

// Converting array to List
List<Integer> list = Arrays.asList(1, 2, 3);

// Comparing arrays
int[] array1 = {1, 2, 3};
int[] array2 = {1, 2, 3};
boolean areEqual = Arrays.equals(array1, array2);  // true

// Filling an array
int[] filledArray = new int[5];
Arrays.fill(filledArray, 10);  // [10, 10, 10, 10, 10]
```

## 7. Iterators

An Iterator is an interface that provides methods to iterate over any Collection:

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Charlie");

// Using Iterator
Iterator<String> iterator = names.iterator();
while (iterator.hasNext()) {
    String name = iterator.next();
    System.out.println(name);
    
    // Remove elements while iterating
    if (name.equals("Bob")) {
        iterator.remove();
    }
}
```

### ListIterator

A ListIterator is an iterator for lists that allows bidirectional traversal and modification:

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Charlie");

// Using ListIterator
ListIterator<String> listIterator = names.listIterator();

// Forward traversal
while (listIterator.hasNext()) {
    int index = listIterator.nextIndex();
    String name = listIterator.next();
    System.out.println(index + ": " + name);
}

// Backward traversal
while (listIterator.hasPrevious()) {
    int index = listIterator.previousIndex();
    String name = listIterator.previous();
    System.out.println(index + ": " + name);
}
```

## 8. Comparable and Comparator

### Comparable

The Comparable interface is used to define the natural ordering of a class:

```java
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    
    // Constructor, getters, setters...
    
    @Override
    public int compareTo(Person other) {
        // Compare by age
        return this.age - other.age;
    }
}

// Using Comparable
List<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));
people.add(new Person("Charlie", 35));

Collections.sort(people);  // Sorts by age (natural ordering)
```

### Comparator

The Comparator interface is used to define custom ordering:

```java
// Comparator for sorting by name
Comparator<Person> nameComparator = new Comparator<Person>() {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
};

// Using Comparator
Collections.sort(people, nameComparator);  // Sorts by name

// Using lambda expression (Java 8+)
Collections.sort(people, (p1, p2) -> p1.getName().compareTo(p2.getName()));

// Using Comparator.comparing (Java 8+)
Collections.sort(people, Comparator.comparing(Person::getName));
```

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `ListExample.java`: Demonstrates ArrayList and LinkedList
- `SetExample.java`: Shows HashSet, TreeSet, and LinkedHashSet
- `MapExample.java`: Illustrates HashMap, TreeMap, and LinkedHashMap
- `QueueExample.java`: Covers Queue, PriorityQueue, and Deque
- `UtilityClassesExample.java`: Demonstrates Collections and Arrays utility classes
- `IteratorExample.java`: Shows how to use Iterator and ListIterator
- `ComparableComparatorExample.java`: Illustrates sorting with Comparable and Comparator

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Implement a student management system using Lists
2. `Exercise2.java`: Create a unique word counter using Sets
3. `Exercise3.java`: Build a dictionary application using Maps
4. `Exercise4.java`: Implement a task scheduler using Queues and Priority Queues

Complete these exercises to practice what you've learned. The Java Collections Framework is a fundamental part of Java programming and is used extensively in real-world applications!

## Next Steps

After completing this section, proceed to the "File I/O and Serialization" section to learn about reading from and writing to files in Java. This will allow you to persist your collections and other data structures to disk.
