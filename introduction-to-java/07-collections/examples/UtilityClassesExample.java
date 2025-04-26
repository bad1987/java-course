/**
 * UtilityClassesExample.java
 * This program demonstrates the use of Collections and Arrays utility classes in Java.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Comparator;
import java.util.Random;

public class UtilityClassesExample {
    public static void main(String[] args) {
        System.out.println("--- Utility Classes Examples ---");
        
        // Example 1: Collections Utility Methods
        System.out.println("\nExample 1: Collections Utility Methods");
        collectionsUtilityMethods();
        
        // Example 2: Arrays Utility Methods
        System.out.println("\nExample 2: Arrays Utility Methods");
        arraysUtilityMethods();
        
        // Example 3: Unmodifiable Collections
        System.out.println("\nExample 3: Unmodifiable Collections");
        unmodifiableCollections();
        
        // Example 4: Singleton Collections
        System.out.println("\nExample 4: Singleton Collections");
        singletonCollections();
        
        // Example 5: Empty Collections
        System.out.println("\nExample 5: Empty Collections");
        emptyCollections();
        
        // Example 6: Collection Algorithms
        System.out.println("\nExample 6: Collection Algorithms");
        collectionAlgorithms();
    }
    
    /**
     * Demonstrates utility methods from the Collections class.
     */
    public static void collectionsUtilityMethods() {
        // Creating a list
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        numbers.add(3);
        
        System.out.println("Original list: " + numbers);
        
        // Sorting a list
        Collections.sort(numbers);
        System.out.println("Sorted list: " + numbers);
        
        // Sorting in reverse order
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("Reverse sorted list: " + numbers);
        
        // Shuffling a list
        Collections.shuffle(numbers);
        System.out.println("Shuffled list: " + numbers);
        
        // Shuffling with a specific Random instance
        Collections.shuffle(numbers, new Random(42));  // Using a seed for reproducible results
        System.out.println("Shuffled list with seed: " + numbers);
        
        // Reversing a list
        Collections.reverse(numbers);
        System.out.println("Reversed list: " + numbers);
        
        // Rotating a list
        Collections.rotate(numbers, 2);  // Rotate right by 2 positions
        System.out.println("Rotated list (right by 2): " + numbers);
        
        // Swapping elements
        Collections.swap(numbers, 0, 3);
        System.out.println("After swapping elements 0 and 3: " + numbers);
        
        // Replacing all occurrences
        Collections.replaceAll(numbers, 9, 99);
        System.out.println("After replacing 9 with 99: " + numbers);
        
        // Finding min and max
        Integer min = Collections.min(numbers);
        Integer max = Collections.max(numbers);
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        
        // Finding min and max with a custom comparator
        min = Collections.min(numbers, Collections.reverseOrder());
        max = Collections.max(numbers, Collections.reverseOrder());
        System.out.println("Minimum (with reverse comparator): " + min);
        System.out.println("Maximum (with reverse comparator): " + max);
        
        // Frequency of an element
        int frequency = Collections.frequency(numbers, 3);
        System.out.println("Frequency of 3: " + frequency);
        
        // Binary search (requires a sorted list)
        Collections.sort(numbers);
        System.out.println("Sorted list for binary search: " + numbers);
        int index = Collections.binarySearch(numbers, 3);
        System.out.println("Index of 3: " + index);
        
        // Filling a list
        Collections.fill(numbers, 7);
        System.out.println("List after filling with 7: " + numbers);
        
        // Copying a list
        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> dest = Arrays.asList(0, 0, 0, 0, 0);
        Collections.copy(dest, source);
        System.out.println("Source list: " + source);
        System.out.println("Destination list after copy: " + dest);
    }
    
    /**
     * Demonstrates utility methods from the Arrays class.
     */
    public static void arraysUtilityMethods() {
        // Creating and initializing arrays
        int[] numbers = {5, 2, 8, 1, 9, 3};
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        // Sorting an array
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        // Sorting a range of an array
        int[] numbers2 = {5, 2, 8, 1, 9, 3};
        Arrays.sort(numbers2, 1, 4);  // Sort from index 1 (inclusive) to 4 (exclusive)
        System.out.println("Partially sorted array: " + Arrays.toString(numbers2));
        
        // Binary search
        int index = Arrays.binarySearch(numbers, 8);
        System.out.println("Index of 8: " + index);
        
        // Filling an array
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 7);
        System.out.println("Array filled with 7: " + Arrays.toString(filledArray));
        
        // Filling a range of an array
        int[] partiallyFilledArray = new int[5];
        Arrays.fill(partiallyFilledArray, 1, 4, 9);  // Fill from index 1 (inclusive) to 4 (exclusive)
        System.out.println("Partially filled array: " + Arrays.toString(partiallyFilledArray));
        
        // Copying arrays
        int[] copy = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Copied array: " + Arrays.toString(copy));
        
        // Copying with a new length
        int[] shorterCopy = Arrays.copyOf(numbers, 3);  // First 3 elements
        System.out.println("Shorter copy: " + Arrays.toString(shorterCopy));
        
        int[] longerCopy = Arrays.copyOf(numbers, 8);  // Pads with zeros
        System.out.println("Longer copy: " + Arrays.toString(longerCopy));
        
        // Copying a range
        int[] rangeCopy = Arrays.copyOfRange(numbers, 2, 5);  // From index 2 (inclusive) to 5 (exclusive)
        System.out.println("Range copy: " + Arrays.toString(rangeCopy));
        
        // Comparing arrays
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        int[] array3 = {1, 2, 4};
        
        boolean equals12 = Arrays.equals(array1, array2);
        boolean equals13 = Arrays.equals(array1, array3);
        
        System.out.println("array1 equals array2: " + equals12);
        System.out.println("array1 equals array3: " + equals13);
        
        // Comparing arrays lexicographically
        int compare13 = Arrays.compare(array1, array3);
        System.out.println("array1 compared to array3: " + compare13);
        
        // Converting array to List
        Integer[] boxedNumbers = {1, 2, 3, 4, 5};
        List<Integer> numbersList = Arrays.asList(boxedNumbers);
        System.out.println("Array converted to List: " + numbersList);
        
        // Note: The list returned by asList is backed by the array
        boxedNumbers[0] = 99;
        System.out.println("List after modifying the array: " + numbersList);
        
        // Creating a mutable ArrayList from an array
        List<Integer> mutableList = new ArrayList<>(Arrays.asList(boxedNumbers));
        System.out.println("Mutable list from array: " + mutableList);
        
        // Sorting with a custom comparator
        Integer[] numbers3 = {5, 2, 8, 1, 9, 3};
        Arrays.sort(numbers3, Comparator.reverseOrder());
        System.out.println("Array sorted in reverse: " + Arrays.toString(numbers3));
        
        // Parallel sorting (for large arrays)
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = 1000 - i;
        }
        Arrays.parallelSort(largeArray);
        System.out.println("First 10 elements after parallel sort: " + 
                          Arrays.toString(Arrays.copyOf(largeArray, 10)));
    }
    
    /**
     * Demonstrates creating unmodifiable collections.
     */
    public static void unmodifiableCollections() {
        // Creating a list
        List<String> mutableList = new ArrayList<>();
        mutableList.add("Apple");
        mutableList.add("Banana");
        mutableList.add("Cherry");
        
        System.out.println("Mutable list: " + mutableList);
        
        // Creating an unmodifiable view of the list
        List<String> unmodifiableList = Collections.unmodifiableList(mutableList);
        System.out.println("Unmodifiable list: " + unmodifiableList);
        
        // Modifying the original list affects the unmodifiable view
        mutableList.add("Date");
        System.out.println("Unmodifiable list after modifying original: " + unmodifiableList);
        
        // Attempting to modify the unmodifiable list directly
        try {
            unmodifiableList.add("Elderberry");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify unmodifiable list: " + e.getMessage());
        }
        
        // Creating unmodifiable Set and Map
        Set<String> unmodifiableSet = Collections.unmodifiableSet(
            Set.of("Red", "Green", "Blue"));
        
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(
            Map.of("One", 1, "Two", 2, "Three", 3));
        
        System.out.println("Unmodifiable set: " + unmodifiableSet);
        System.out.println("Unmodifiable map: " + unmodifiableMap);
        
        // Java 9+ factory methods for creating immutable collections
        List<String> immutableList = List.of("Alpha", "Beta", "Gamma");
        Set<String> immutableSet = Set.of("X", "Y", "Z");
        Map<String, Integer> immutableMap = Map.of("A", 1, "B", 2, "C", 3);
        
        System.out.println("Immutable list: " + immutableList);
        System.out.println("Immutable set: " + immutableSet);
        System.out.println("Immutable map: " + immutableMap);
    }
    
    /**
     * Demonstrates creating singleton collections.
     */
    public static void singletonCollections() {
        // Creating singleton collections
        List<String> singletonList = Collections.singletonList("Only Element");
        Set<Integer> singletonSet = Collections.singleton(42);
        Map<String, Boolean> singletonMap = Collections.singletonMap("key", true);
        
        System.out.println("Singleton list: " + singletonList);
        System.out.println("Singleton set: " + singletonSet);
        System.out.println("Singleton map: " + singletonMap);
        
        // Attempting to modify a singleton collection
        try {
            singletonList.add("Another Element");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify singleton list: " + e.getMessage());
        }
        
        // Using singleton collections
        processItems(singletonList);
        checkNumbers(singletonSet);
        processFlags(singletonMap);
    }
    
    /**
     * Demonstrates creating empty collections.
     */
    public static void emptyCollections() {
        // Creating empty collections
        List<String> emptyList = Collections.emptyList();
        Set<Integer> emptySet = Collections.emptySet();
        Map<String, Double> emptyMap = Collections.emptyMap();
        
        System.out.println("Empty list: " + emptyList);
        System.out.println("Empty set: " + emptySet);
        System.out.println("Empty map: " + emptyMap);
        
        // Using empty collections as return values
        List<String> result = findMatches("xyz");
        System.out.println("Search result: " + result);
        
        // Attempting to modify an empty collection
        try {
            emptyList.add("Element");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify empty list: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates various collection algorithms.
     */
    public static void collectionAlgorithms() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(1);
        numbers.add(5);
        numbers.add(9);
        
        System.out.println("Original list: " + numbers);
        
        // Finding the maximum element
        Integer max = Collections.max(numbers);
        System.out.println("Maximum element: " + max);
        
        // Finding the minimum element
        Integer min = Collections.min(numbers);
        System.out.println("Minimum element: " + min);
        
        // Finding the maximum with a custom comparator
        Integer maxByDigitSum = Collections.max(numbers, Comparator.comparing(UtilityClassesExample::digitSum));
        System.out.println("Maximum by digit sum: " + maxByDigitSum);
        
        // Counting occurrences
        int occurrences = Collections.frequency(numbers, 1);
        System.out.println("Occurrences of 1: " + occurrences);
        
        // Checking if two collections have elements in common
        List<Integer> otherList = List.of(5, 9, 2);
        boolean disjoint = Collections.disjoint(numbers, otherList);
        System.out.println("Collections are disjoint: " + disjoint);
        
        // Finding the index of the first occurrence of a sublist
        List<Integer> subList = List.of(1, 5);
        int indexOfSubList = Collections.indexOfSubList(numbers, subList);
        System.out.println("Index of sublist [1, 5]: " + indexOfSubList);
        
        // Finding the index of the last occurrence of a sublist
        int lastIndexOfSubList = Collections.lastIndexOfSubList(numbers, subList);
        System.out.println("Last index of sublist [1, 5]: " + lastIndexOfSubList);
        
        // Replacing all occurrences of an element
        boolean replaced = Collections.replaceAll(numbers, 1, 10);
        System.out.println("Replaced all 1s with 10s: " + replaced);
        System.out.println("List after replacement: " + numbers);
    }
    
    // Helper methods
    
    /**
     * Example method that processes a list of items.
     */
    private static void processItems(List<String> items) {
        System.out.println("Processing " + items.size() + " items");
    }
    
    /**
     * Example method that checks a set of numbers.
     */
    private static void checkNumbers(Set<Integer> numbers) {
        System.out.println("Checking " + numbers.size() + " numbers");
    }
    
    /**
     * Example method that processes a map of flags.
     */
    private static void processFlags(Map<String, Boolean> flags) {
        System.out.println("Processing " + flags.size() + " flags");
    }
    
    /**
     * Example method that returns an empty list when no matches are found.
     */
    private static List<String> findMatches(String query) {
        // Simulating a search that finds no matches
        return Collections.emptyList();
    }
    
    /**
     * Helper method to calculate the sum of digits in a number.
     */
    private static int digitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
