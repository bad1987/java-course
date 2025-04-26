/**
 * IteratorExample.java
 * This program demonstrates the use of Iterator and ListIterator in Java.
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class IteratorExample {
    public static void main(String[] args) {
        System.out.println("--- Iterator Examples ---");
        
        // Example 1: Basic Iterator Usage
        System.out.println("\nExample 1: Basic Iterator Usage");
        basicIteratorUsage();
        
        // Example 2: Removing Elements with Iterator
        System.out.println("\nExample 2: Removing Elements with Iterator");
        removingElementsWithIterator();
        
        // Example 3: ListIterator Basics
        System.out.println("\nExample 3: ListIterator Basics");
        listIteratorBasics();
        
        // Example 4: Modifying Elements with ListIterator
        System.out.println("\nExample 4: Modifying Elements with ListIterator");
        modifyingElementsWithListIterator();
        
        // Example 5: Adding Elements with ListIterator
        System.out.println("\nExample 5: Adding Elements with ListIterator");
        addingElementsWithListIterator();
        
        // Example 6: Iterating in Both Directions
        System.out.println("\nExample 6: Iterating in Both Directions");
        iteratingInBothDirections();
        
        // Example 7: Iterator vs. Enhanced For Loop
        System.out.println("\nExample 7: Iterator vs. Enhanced For Loop");
        iteratorVsEnhancedForLoop();
    }
    
    /**
     * Demonstrates basic usage of Iterator.
     */
    public static void basicIteratorUsage() {
        // Creating a list
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        
        System.out.println("Fruits list: " + fruits);
        
        // Getting an iterator
        Iterator<String> iterator = fruits.iterator();
        
        // Iterating through the list
        System.out.println("Iterating through the list:");
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("- " + fruit);
        }
        
        // Iterator is exhausted after use
        System.out.println("Has more elements? " + iterator.hasNext());
        
        // Getting a new iterator
        iterator = fruits.iterator();
        System.out.println("New iterator has more elements? " + iterator.hasNext());
        
        // Using iterator with a Set
        Set<Integer> numbers = new HashSet<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        
        System.out.println("\nNumbers set: " + numbers);
        
        // Getting an iterator for the set
        Iterator<Integer> numberIterator = numbers.iterator();
        
        // Iterating through the set
        System.out.println("Iterating through the set:");
        while (numberIterator.hasNext()) {
            Integer number = numberIterator.next();
            System.out.println("- " + number);
        }
    }
    
    /**
     * Demonstrates removing elements using an Iterator.
     */
    public static void removingElementsWithIterator() {
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        colors.add("Orange");
        
        System.out.println("Colors list: " + colors);
        
        // Removing elements that start with 'B' or 'Y'
        Iterator<String> iterator = colors.iterator();
        
        System.out.println("Removing colors that start with 'B' or 'Y':");
        while (iterator.hasNext()) {
            String color = iterator.next();
            if (color.startsWith("B") || color.startsWith("Y")) {
                System.out.println("Removing: " + color);
                iterator.remove();
            }
        }
        
        System.out.println("Colors after removal: " + colors);
        
        // Incorrect way to remove elements during iteration
        List<String> shapes = new ArrayList<>();
        shapes.add("Circle");
        shapes.add("Square");
        shapes.add("Triangle");
        shapes.add("Rectangle");
        
        System.out.println("\nShapes list: " + shapes);
        
        try {
            System.out.println("Attempting to remove elements incorrectly:");
            for (String shape : shapes) {
                if (shape.length() > 6) {
                    // This will throw ConcurrentModificationException
                    shapes.remove(shape);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        // Correct way using iterator
        Iterator<String> shapeIterator = shapes.iterator();
        
        System.out.println("Removing shapes with length > 6 correctly:");
        while (shapeIterator.hasNext()) {
            String shape = shapeIterator.next();
            if (shape.length() > 6) {
                System.out.println("Removing: " + shape);
                shapeIterator.remove();
            }
        }
        
        System.out.println("Shapes after removal: " + shapes);
    }
    
    /**
     * Demonstrates basic usage of ListIterator.
     */
    public static void listIteratorBasics() {
        // Creating a list
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");
        
        System.out.println("Names list: " + names);
        
        // Getting a ListIterator
        ListIterator<String> listIterator = names.listIterator();
        
        // Forward iteration
        System.out.println("Forward iteration:");
        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            String name = listIterator.next();
            System.out.println("Index " + index + ": " + name);
        }
        
        // Backward iteration
        System.out.println("\nBackward iteration:");
        while (listIterator.hasPrevious()) {
            int index = listIterator.previousIndex();
            String name = listIterator.previous();
            System.out.println("Index " + index + ": " + name);
        }
        
        // Getting a ListIterator at a specific position
        ListIterator<String> positionedIterator = names.listIterator(2);
        
        System.out.println("\nStarting iteration from position 2:");
        while (positionedIterator.hasNext()) {
            int index = positionedIterator.nextIndex();
            String name = positionedIterator.next();
            System.out.println("Index " + index + ": " + name);
        }
    }
    
    /**
     * Demonstrates modifying elements using a ListIterator.
     */
    public static void modifyingElementsWithListIterator() {
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        words.add("date");
        
        System.out.println("Words list: " + words);
        
        // Using ListIterator to capitalize all words
        ListIterator<String> iterator = words.listIterator();
        
        System.out.println("Capitalizing all words:");
        while (iterator.hasNext()) {
            String word = iterator.next();
            String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1);
            iterator.set(capitalized);  // Replace the current element
        }
        
        System.out.println("Words after capitalization: " + words);
        
        // Using ListIterator to add "fruit" after each word
        iterator = words.listIterator();
        
        System.out.println("\nAdding 'fruit' after each word:");
        while (iterator.hasNext()) {
            String word = iterator.next();
            iterator.set(word + " fruit");
        }
        
        System.out.println("Words after modification: " + words);
    }
    
    /**
     * Demonstrates adding elements using a ListIterator.
     */
    public static void addingElementsWithListIterator() {
        List<String> numbers = new ArrayList<>();
        numbers.add("One");
        numbers.add("Three");
        numbers.add("Five");
        
        System.out.println("Numbers list: " + numbers);
        
        // Using ListIterator to add elements
        ListIterator<String> iterator = numbers.listIterator();
        
        // Add "Zero" at the beginning
        iterator.add("Zero");
        
        // Move to "One"
        iterator.next();
        
        // Add "Two" after "One"
        iterator.add("Two");
        
        // Move to "Three"
        iterator.next();
        
        // Add "Four" after "Three"
        iterator.add("Four");
        
        System.out.println("Numbers after adding elements: " + numbers);
        
        // Adding elements while iterating
        List<Integer> sequence = new ArrayList<>();
        sequence.add(1);
        sequence.add(5);
        sequence.add(9);
        
        System.out.println("\nSequence: " + sequence);
        
        // Add missing numbers to create a sequence from 1 to 9
        ListIterator<Integer> seqIterator = sequence.listIterator();
        
        System.out.println("Adding missing numbers to create a sequence from 1 to 9:");
        int expected = 1;
        
        while (seqIterator.hasNext()) {
            int current = seqIterator.next();
            
            // Add missing numbers between expected and current
            while (expected < current) {
                seqIterator.previous();  // Move back to insert before current
                seqIterator.add(expected);
                seqIterator.next();  // Move forward again
                expected++;
            }
            
            expected = current + 1;
        }
        
        System.out.println("Complete sequence: " + sequence);
    }
    
    /**
     * Demonstrates iterating in both directions using a ListIterator.
     */
    public static void iteratingInBothDirections() {
        List<String> letters = new LinkedList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        
        System.out.println("Letters list: " + letters);
        
        // Getting a ListIterator
        ListIterator<String> iterator = letters.listIterator();
        
        // Move forward to the middle
        System.out.println("Moving forward to the middle:");
        while (iterator.nextIndex() < letters.size() / 2) {
            System.out.println("Next: " + iterator.next());
        }
        
        // Now move backward to the beginning
        System.out.println("\nMoving backward to the beginning:");
        while (iterator.hasPrevious()) {
            System.out.println("Previous: " + iterator.previous());
        }
        
        // Now move forward to the end
        System.out.println("\nMoving forward to the end:");
        while (iterator.hasNext()) {
            System.out.println("Next: " + iterator.next());
        }
        
        // Zigzag traversal
        System.out.println("\nZigzag traversal:");
        iterator = letters.listIterator();
        
        // Forward
        System.out.println("Forward:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        // Backward
        System.out.println("Backward:");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        
        // Forward again
        System.out.println("Forward again:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    
    /**
     * Compares Iterator with enhanced for loop.
     */
    public static void iteratorVsEnhancedForLoop() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        items.add("Item 5");
        
        System.out.println("Items list: " + items);
        
        // Using enhanced for loop
        System.out.println("\nUsing enhanced for loop:");
        for (String item : items) {
            System.out.println("- " + item);
        }
        
        // Using Iterator
        System.out.println("\nUsing Iterator:");
        Iterator<String> iterator = items.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println("- " + item);
        }
        
        // Removing elements - can't use enhanced for loop
        System.out.println("\nRemoving even-indexed items:");
        
        // This would cause ConcurrentModificationException
        // for (String item : items) {
        //     if (items.indexOf(item) % 2 == 0) {
        //         items.remove(item);
        //     }
        // }
        
        // Using Iterator to remove elements
        iterator = items.iterator();
        int index = 0;
        
        while (iterator.hasNext()) {
            iterator.next();
            if (index % 2 == 0) {
                iterator.remove();
            }
            index++;
        }
        
        System.out.println("Items after removal: " + items);
        
        // Performance comparison
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            largeList.add(i);
        }
        
        // Using enhanced for loop
        long startTime = System.nanoTime();
        int sum1 = 0;
        for (int num : largeList) {
            sum1 += num;
        }
        long endTime = System.nanoTime();
        System.out.println("\nEnhanced for loop time: " + (endTime - startTime) / 1000000 + " ms");
        
        // Using Iterator
        startTime = System.nanoTime();
        int sum2 = 0;
        Iterator<Integer> it = largeList.iterator();
        while (it.hasNext()) {
            sum2 += it.next();
        }
        endTime = System.nanoTime();
        System.out.println("Iterator time: " + (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("Sums equal: " + (sum1 == sum2));
    }
}
