/**
 * ArrayOperations.java
 * This program demonstrates common operations on arrays using the Arrays class.
 */
import java.util.Arrays;

public class ArrayOperations {
    public static void main(String[] args) {
        System.out.println("--- Arrays Utility Class ---");
        
        int[] numbers = {5, 2, 9, 1, 7, 3, 8, 4, 6};
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        System.out.println("\n--- Sorting Arrays ---");
        
        // Sort the array
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        System.out.println("\n--- Searching in Arrays ---");
        
        // Binary search (array must be sorted)
        int index = Arrays.binarySearch(numbers, 7);
        System.out.println("Index of 7: " + index);
        
        // Search for a value that doesn't exist
        index = Arrays.binarySearch(numbers, 10);
        System.out.println("Index of 10 (not in array): " + index);
        // Note: Returns -(insertion point) - 1
        
        System.out.println("\n--- Filling Arrays ---");
        
        // Create a new array
        int[] filledArray = new int[5];
        
        // Fill the entire array with a value
        Arrays.fill(filledArray, 42);
        System.out.println("Array filled with 42: " + Arrays.toString(filledArray));
        
        // Fill a range of the array
        Arrays.fill(filledArray, 1, 4, 99);
        System.out.println("Array with range filled: " + Arrays.toString(filledArray));
        
        System.out.println("\n--- Copying Arrays ---");
        
        // Using Arrays.copyOf()
        int[] copy1 = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Full copy: " + Arrays.toString(copy1));
        
        // Copy with a new length (truncate or pad with zeros)
        int[] shorterCopy = Arrays.copyOf(numbers, 5);
        System.out.println("Shorter copy: " + Arrays.toString(shorterCopy));
        
        int[] longerCopy = Arrays.copyOf(numbers, 12);
        System.out.println("Longer copy (padded with zeros): " + Arrays.toString(longerCopy));
        
        // Copy a range using Arrays.copyOfRange()
        int[] rangeCopy = Arrays.copyOfRange(numbers, 2, 6);
        System.out.println("Range copy (index 2 to 5): " + Arrays.toString(rangeCopy));
        
        // Using System.arraycopy()
        int[] source = {1, 2, 3, 4, 5};
        int[] destination = new int[5];
        System.arraycopy(source, 0, destination, 0, source.length);
        System.out.println("Copy using System.arraycopy(): " + Arrays.toString(destination));
        
        // Partial copy using System.arraycopy()
        int[] dest2 = new int[10];
        Arrays.fill(dest2, -1);  // Fill with -1 to see the difference
        System.arraycopy(source, 1, dest2, 5, 3);
        System.out.println("Partial copy to specific position: " + Arrays.toString(dest2));
        
        System.out.println("\n--- Comparing Arrays ---");
        
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {1, 2, 3, 4, 5};
        int[] array3 = {1, 2, 3, 4, 6};
        
        boolean areEqual = Arrays.equals(array1, array2);
        System.out.println("array1 equals array2: " + areEqual);
        
        areEqual = Arrays.equals(array1, array3);
        System.out.println("array1 equals array3: " + areEqual);
        
        System.out.println("\n--- Arrays with Objects ---");
        
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};
        System.out.println("String array: " + Arrays.toString(names));
        
        // Sort strings (alphabetically)
        Arrays.sort(names);
        System.out.println("Sorted string array: " + Arrays.toString(names));
        
        // Search in string array
        index = Arrays.binarySearch(names, "Charlie");
        System.out.println("Index of 'Charlie': " + index);
    }
}
