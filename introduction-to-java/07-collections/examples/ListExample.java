/**
 * ListExample.java
 * This program demonstrates the use of List implementations in Java.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class ListExample {
    public static void main(String[] args) {
        System.out.println("--- List Examples ---");
        
        // Example 1: ArrayList Basics
        System.out.println("\nExample 1: ArrayList Basics");
        arrayListBasics();
        
        // Example 2: LinkedList Basics
        System.out.println("\nExample 2: LinkedList Basics");
        linkedListBasics();
        
        // Example 3: ArrayList Operations
        System.out.println("\nExample 3: ArrayList Operations");
        arrayListOperations();
        
        // Example 4: LinkedList Specific Operations
        System.out.println("\nExample 4: LinkedList Specific Operations");
        linkedListSpecificOperations();
        
        // Example 5: Performance Comparison
        System.out.println("\nExample 5: Performance Comparison");
        performanceComparison();
        
        // Example 6: List of Custom Objects
        System.out.println("\nExample 6: List of Custom Objects");
        listOfCustomObjects();
    }
    
    /**
     * Demonstrates basic ArrayList operations.
     */
    public static void arrayListBasics() {
        // Creating an ArrayList
        List<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        System.out.println("Fruits list: " + fruits);
        
        // Accessing elements
        String firstFruit = fruits.get(0);
        System.out.println("First fruit: " + firstFruit);
        
        // Size of the list
        System.out.println("Number of fruits: " + fruits.size());
        
        // Checking if an element exists
        boolean containsBanana = fruits.contains("Banana");
        System.out.println("Contains Banana? " + containsBanana);
        
        // Finding the index of an element
        int cherryIndex = fruits.indexOf("Cherry");
        System.out.println("Index of Cherry: " + cherryIndex);
        
        // Adding an element at a specific position
        fruits.add(1, "Blueberry");
        System.out.println("After adding Blueberry at index 1: " + fruits);
        
        // Removing elements
        fruits.remove("Banana");
        System.out.println("After removing Banana: " + fruits);
        
        fruits.remove(0);  // Remove by index
        System.out.println("After removing element at index 0: " + fruits);
        
        // Clearing the list
        fruits.clear();
        System.out.println("After clearing the list: " + fruits);
        System.out.println("Is the list empty? " + fruits.isEmpty());
    }
    
    /**
     * Demonstrates basic LinkedList operations.
     */
    public static void linkedListBasics() {
        // Creating a LinkedList
        List<String> names = new LinkedList<>();
        
        // Adding elements
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("Names list: " + names);
        
        // All the basic operations are the same as ArrayList
        // Accessing elements
        String secondName = names.get(1);
        System.out.println("Second name: " + secondName);
        
        // Size of the list
        System.out.println("Number of names: " + names.size());
        
        // Checking if an element exists
        boolean containsDavid = names.contains("David");
        System.out.println("Contains David? " + containsDavid);
        
        // Adding an element at a specific position
        names.add(1, "Dave");
        System.out.println("After adding Dave at index 1: " + names);
        
        // Removing elements
        names.remove("Bob");
        System.out.println("After removing Bob: " + names);
        
        names.remove(0);  // Remove by index
        System.out.println("After removing element at index 0: " + names);
    }
    
    /**
     * Demonstrates more advanced ArrayList operations.
     */
    public static void arrayListOperations() {
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        System.out.println("Colors: " + colors);
        
        // Creating a sublist
        List<String> subColors = colors.subList(0, 2);  // From index 0 (inclusive) to 2 (exclusive)
        System.out.println("Sublist: " + subColors);
        
        // Modifying the sublist affects the original list
        subColors.add("Yellow");
        System.out.println("Sublist after adding Yellow: " + subColors);
        System.out.println("Original list after modifying sublist: " + colors);
        
        // Converting list to array
        String[] colorsArray = colors.toArray(new String[0]);
        System.out.print("Colors array: ");
        for (String color : colorsArray) {
            System.out.print(color + " ");
        }
        System.out.println();
        
        // Creating a list from an existing collection
        List<String> moreColors = new ArrayList<>();
        moreColors.add("Purple");
        moreColors.add("Orange");
        
        List<String> allColors = new ArrayList<>(colors);  // Create a new list with all elements from colors
        allColors.addAll(moreColors);  // Add all elements from moreColors
        System.out.println("All colors: " + allColors);
        
        // Removing all elements that exist in another collection
        allColors.removeAll(colors);
        System.out.println("After removing colors: " + allColors);
        
        // Retaining only elements that exist in another collection
        allColors = new ArrayList<>(colors);
        allColors.addAll(moreColors);
        List<String> retainList = new ArrayList<>();
        retainList.add("Red");
        retainList.add("Purple");
        
        allColors.retainAll(retainList);
        System.out.println("After retaining only Red and Purple: " + allColors);
        
        // Using Iterator to remove elements while iterating
        List<String> numbers = new ArrayList<>();
        numbers.add("One");
        numbers.add("Two");
        numbers.add("Three");
        numbers.add("Four");
        
        Iterator<String> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            String number = iterator.next();
            if (number.contains("o") || number.contains("O")) {
                iterator.remove();  // Safe way to remove while iterating
            }
        }
        System.out.println("After removing items containing 'o' or 'O': " + numbers);
    }
    
    /**
     * Demonstrates LinkedList-specific operations.
     */
    public static void linkedListSpecificOperations() {
        // Creating a LinkedList
        LinkedList<String> queue = new LinkedList<>();
        
        // Adding elements using LinkedList-specific methods
        queue.addFirst("First");
        queue.addLast("Last");
        queue.add("Middle");  // Adds to the end (same as addLast)
        System.out.println("Queue: " + queue);
        
        // Retrieving elements without removing
        String first = queue.getFirst();
        String last = queue.getLast();
        System.out.println("First element: " + first);
        System.out.println("Last element: " + last);
        
        // Retrieving and removing elements
        String removedFirst = queue.removeFirst();
        String removedLast = queue.removeLast();
        System.out.println("Removed first element: " + removedFirst);
        System.out.println("Removed last element: " + removedLast);
        System.out.println("Queue after removals: " + queue);
        
        // Using LinkedList as a Queue
        LinkedList<String> taskQueue = new LinkedList<>();
        
        // Adding tasks to the queue
        taskQueue.offer("Task 1");  // Adds to the end
        taskQueue.offer("Task 2");
        taskQueue.offer("Task 3");
        System.out.println("Task queue: " + taskQueue);
        
        // Processing tasks from the queue
        String nextTask = taskQueue.poll();  // Retrieves and removes the head
        System.out.println("Processing: " + nextTask);
        System.out.println("Remaining tasks: " + taskQueue);
        
        // Peeking at the next task without removing
        String peekTask = taskQueue.peek();
        System.out.println("Next task (peek): " + peekTask);
        System.out.println("Task queue after peek: " + taskQueue);
        
        // Using LinkedList as a Stack
        LinkedList<String> stack = new LinkedList<>();
        
        // Pushing elements onto the stack
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");
        System.out.println("Stack: " + stack);
        
        // Popping elements from the stack
        String poppedItem = stack.pop();
        System.out.println("Popped item: " + poppedItem);
        System.out.println("Stack after pop: " + stack);
    }
    
    /**
     * Compares the performance of ArrayList and LinkedList for different operations.
     */
    public static void performanceComparison() {
        final int SIZE = 100000;
        
        // ArrayList for sequential access
        List<Integer> arrayList = new ArrayList<>();
        long startTime = System.nanoTime();
        
        // Adding elements to the end
        for (int i = 0; i < SIZE; i++) {
            arrayList.add(i);
        }
        
        long endTime = System.nanoTime();
        System.out.println("ArrayList add to end: " + (endTime - startTime) / 1000000 + " ms");
        
        // LinkedList for sequential access
        List<Integer> linkedList = new LinkedList<>();
        startTime = System.nanoTime();
        
        // Adding elements to the end
        for (int i = 0; i < SIZE; i++) {
            linkedList.add(i);
        }
        
        endTime = System.nanoTime();
        System.out.println("LinkedList add to end: " + (endTime - startTime) / 1000000 + " ms");
        
        // ArrayList for random access
        startTime = System.nanoTime();
        
        // Accessing elements randomly
        for (int i = 0; i < 1000; i++) {
            int index = (int) (Math.random() * SIZE);
            int value = arrayList.get(index);
        }
        
        endTime = System.nanoTime();
        System.out.println("ArrayList random access: " + (endTime - startTime) / 1000000 + " ms");
        
        // LinkedList for random access
        startTime = System.nanoTime();
        
        // Accessing elements randomly
        for (int i = 0; i < 1000; i++) {
            int index = (int) (Math.random() * SIZE);
            int value = linkedList.get(index);
        }
        
        endTime = System.nanoTime();
        System.out.println("LinkedList random access: " + (endTime - startTime) / 1000000 + " ms");
        
        // ArrayList for insertion at the beginning
        startTime = System.nanoTime();
        
        // Adding elements to the beginning
        for (int i = 0; i < 1000; i++) {
            arrayList.add(0, i);
        }
        
        endTime = System.nanoTime();
        System.out.println("ArrayList add to beginning: " + (endTime - startTime) / 1000000 + " ms");
        
        // LinkedList for insertion at the beginning
        startTime = System.nanoTime();
        
        // Adding elements to the beginning
        for (int i = 0; i < 1000; i++) {
            linkedList.add(0, i);
        }
        
        endTime = System.nanoTime();
        System.out.println("LinkedList add to beginning: " + (endTime - startTime) / 1000000 + " ms");
    }
    
    /**
     * Demonstrates using a List to store custom objects.
     */
    public static void listOfCustomObjects() {
        // Creating a list of Person objects
        List<Person> people = new ArrayList<>();
        
        // Adding Person objects to the list
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        
        // Displaying all people
        System.out.println("People in the list:");
        for (Person person : people) {
            System.out.println(person);
        }
        
        // Finding a person by name
        String nameToFind = "Bob";
        Person foundPerson = null;
        
        for (Person person : people) {
            if (person.getName().equals(nameToFind)) {
                foundPerson = person;
                break;
            }
        }
        
        if (foundPerson != null) {
            System.out.println("\nFound person: " + foundPerson);
        } else {
            System.out.println("\nPerson not found: " + nameToFind);
        }
        
        // Updating a person's age
        if (foundPerson != null) {
            foundPerson.setAge(26);
            System.out.println("Updated age: " + foundPerson);
        }
        
        // Removing a person from the list
        people.remove(foundPerson);
        System.out.println("\nAfter removing " + nameToFind + ", people in the list:");
        for (Person person : people) {
            System.out.println(person);
        }
    }
}

/**
 * A simple Person class for demonstrating lists of custom objects.
 */
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
