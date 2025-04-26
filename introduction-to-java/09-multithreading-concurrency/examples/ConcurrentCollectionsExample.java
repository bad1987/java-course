/**
 * ConcurrentCollectionsExample.java
 * This program demonstrates the use of concurrent collections in Java.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcurrentCollectionsExample {
    public static void main(String[] args) {
        System.out.println("--- Concurrent Collections Examples ---");
        
        // Example 1: ConcurrentHashMap
        System.out.println("\nExample 1: ConcurrentHashMap");
        concurrentHashMapExample();
        
        // Example 2: CopyOnWriteArrayList
        System.out.println("\nExample 2: CopyOnWriteArrayList");
        copyOnWriteArrayListExample();
        
        // Example 3: CopyOnWriteArraySet
        System.out.println("\nExample 3: CopyOnWriteArraySet");
        copyOnWriteArraySetExample();
        
        // Example 4: Synchronized Collections
        System.out.println("\nExample 4: Synchronized Collections");
        synchronizedCollectionsExample();
        
        // Example 5: Concurrent Modification Exception
        System.out.println("\nExample 5: Concurrent Modification Exception");
        concurrentModificationExceptionExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates the use of ConcurrentHashMap.
     */
    private static void concurrentHashMapExample() {
        // Create a ConcurrentHashMap
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // Create threads that add to the map
        Thread writerThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                concurrentMap.put("Key-" + i, i);
                System.out.println("Writer 1: Added Key-" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        Thread writerThread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                concurrentMap.put("Key-" + i, i);
                System.out.println("Writer 2: Added Key-" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Create a thread that reads from the map
        Thread readerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Reader: Map size = " + concurrentMap.size());
                
                // Iterate over the map
                for (Map.Entry<String, Integer> entry : concurrentMap.entrySet()) {
                    System.out.println("Reader: " + entry.getKey() + " = " + entry.getValue());
                }
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Start the threads
        writerThread1.start();
        writerThread2.start();
        readerThread.start();
        
        // Wait for the threads to finish
        try {
            writerThread1.join();
            writerThread2.join();
            readerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate atomic operations
        System.out.println("\nAtomic operations on ConcurrentHashMap:");
        
        // putIfAbsent
        Integer oldValue = concurrentMap.putIfAbsent("Key-5", 500);
        System.out.println("putIfAbsent: Old value = " + oldValue);
        
        // replace
        boolean replaced = concurrentMap.replace("Key-10", 10, 1000);
        System.out.println("replace: Replaced = " + replaced + ", New value = " + concurrentMap.get("Key-10"));
        
        // remove
        boolean removed = concurrentMap.remove("Key-15", 15);
        System.out.println("remove: Removed = " + removed + ", Key-15 exists = " + concurrentMap.containsKey("Key-15"));
        
        // compute
        concurrentMap.compute("Key-1", (key, value) -> (value == null) ? 1 : value * 10);
        System.out.println("compute: New value for Key-1 = " + concurrentMap.get("Key-1"));
        
        // computeIfAbsent
        concurrentMap.computeIfAbsent("Key-100", key -> Integer.parseInt(key.split("-")[1]) * 2);
        System.out.println("computeIfAbsent: Value for Key-100 = " + concurrentMap.get("Key-100"));
        
        // computeIfPresent
        concurrentMap.computeIfPresent("Key-2", (key, value) -> value + 200);
        System.out.println("computeIfPresent: New value for Key-2 = " + concurrentMap.get("Key-2"));
        
        // merge
        concurrentMap.merge("Key-3", 300, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("merge: New value for Key-3 = " + concurrentMap.get("Key-3"));
        
        // forEach
        System.out.println("\nforEach on ConcurrentHashMap:");
        concurrentMap.forEach((key, value) -> {
            if (value > 100) {
                System.out.println(key + " = " + value);
            }
        });
    }
    
    /**
     * Demonstrates the use of CopyOnWriteArrayList.
     */
    private static void copyOnWriteArrayListExample() {
        // Create a CopyOnWriteArrayList
        List<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        
        // Add some initial elements
        copyOnWriteList.add("Apple");
        copyOnWriteList.add("Banana");
        copyOnWriteList.add("Cherry");
        
        // Create a thread that iterates over the list
        Thread iteratorThread = new Thread(() -> {
            // Get an iterator
            Iterator<String> iterator = copyOnWriteList.iterator();
            
            System.out.println("Iterator: Starting iteration");
            
            // Iterate over the list
            while (iterator.hasNext()) {
                String element = iterator.next();
                System.out.println("Iterator: " + element);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                // Note: The following would throw UnsupportedOperationException
                // iterator.remove();
            }
            
            System.out.println("Iterator: Finished iteration");
        });
        
        // Create a thread that modifies the list
        Thread modifierThread = new Thread(() -> {
            try {
                // Wait for the iterator to start
                Thread.sleep(250);
                
                // Add a new element
                copyOnWriteList.add("Date");
                System.out.println("Modifier: Added 'Date'");
                
                Thread.sleep(500);
                
                // Remove an element
                copyOnWriteList.remove("Banana");
                System.out.println("Modifier: Removed 'Banana'");
                
                Thread.sleep(500);
                
                // Add another element
                copyOnWriteList.add("Elderberry");
                System.out.println("Modifier: Added 'Elderberry'");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Start the threads
        iteratorThread.start();
        modifierThread.start();
        
        // Wait for the threads to finish
        try {
            iteratorThread.join();
            modifierThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Show the final state of the list
        System.out.println("\nFinal list contents:");
        for (String element : copyOnWriteList) {
            System.out.println(element);
        }
        
        // Demonstrate that the iterator reflects a snapshot of the list
        System.out.println("\nDemonstrating iterator snapshot:");
        
        List<Integer> numbers = new CopyOnWriteArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        
        // Get an iterator
        Iterator<Integer> iterator = numbers.iterator();
        
        // Modify the list
        numbers.add(4);
        numbers.remove(0);  // Remove 1
        
        System.out.println("Modified list: " + numbers);
        
        System.out.println("Iterator elements:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    
    /**
     * Demonstrates the use of CopyOnWriteArraySet.
     */
    private static void copyOnWriteArraySetExample() {
        // Create a CopyOnWriteArraySet
        Set<String> copyOnWriteSet = new CopyOnWriteArraySet<>();
        
        // Add some initial elements
        copyOnWriteSet.add("Red");
        copyOnWriteSet.add("Green");
        copyOnWriteSet.add("Blue");
        
        // Create a thread that iterates over the set
        Thread iteratorThread = new Thread(() -> {
            // Get an iterator
            Iterator<String> iterator = copyOnWriteSet.iterator();
            
            System.out.println("Iterator: Starting iteration");
            
            // Iterate over the set
            while (iterator.hasNext()) {
                String element = iterator.next();
                System.out.println("Iterator: " + element);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            System.out.println("Iterator: Finished iteration");
        });
        
        // Create a thread that modifies the set
        Thread modifierThread = new Thread(() -> {
            try {
                // Wait for the iterator to start
                Thread.sleep(250);
                
                // Add a new element
                copyOnWriteSet.add("Yellow");
                System.out.println("Modifier: Added 'Yellow'");
                
                Thread.sleep(500);
                
                // Remove an element
                copyOnWriteSet.remove("Green");
                System.out.println("Modifier: Removed 'Green'");
                
                Thread.sleep(500);
                
                // Add another element
                copyOnWriteSet.add("Purple");
                System.out.println("Modifier: Added 'Purple'");
                
                // Try to add a duplicate
                boolean added = copyOnWriteSet.add("Red");
                System.out.println("Modifier: Added 'Red' again = " + added);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Start the threads
        iteratorThread.start();
        modifierThread.start();
        
        // Wait for the threads to finish
        try {
            iteratorThread.join();
            modifierThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Show the final state of the set
        System.out.println("\nFinal set contents:");
        for (String element : copyOnWriteSet) {
            System.out.println(element);
        }
    }
    
    /**
     * Demonstrates the use of synchronized collections.
     */
    private static void synchronizedCollectionsExample() {
        // Create synchronized collections
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        
        // Add elements to the synchronized list
        synchronizedList.add("One");
        synchronizedList.add("Two");
        synchronizedList.add("Three");
        
        // Add elements to the synchronized map
        synchronizedMap.put("One", 1);
        synchronizedMap.put("Two", 2);
        synchronizedMap.put("Three", 3);
        
        // Add elements to the synchronized set
        synchronizedSet.add("One");
        synchronizedSet.add("Two");
        synchronizedSet.add("Three");
        
        // Create threads that access the synchronized collections
        Thread thread1 = new Thread(() -> {
            // Synchronized block for iteration
            synchronized (synchronizedList) {
                System.out.println("Thread 1: Iterating over synchronized list");
                for (String element : synchronizedList) {
                    System.out.println("Thread 1: " + element);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
            // Synchronized block for iteration
            synchronized (synchronizedMap) {
                System.out.println("Thread 1: Iterating over synchronized map");
                for (Map.Entry<String, Integer> entry : synchronizedMap.entrySet()) {
                    System.out.println("Thread 1: " + entry.getKey() + " = " + entry.getValue());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        
        Thread thread2 = new Thread(() -> {
            // Modify the synchronized list
            synchronizedList.add("Four");
            System.out.println("Thread 2: Added 'Four' to list");
            
            // Modify the synchronized map
            synchronizedMap.put("Four", 4);
            System.out.println("Thread 2: Added 'Four' = 4 to map");
            
            // Modify the synchronized set
            synchronizedSet.add("Four");
            System.out.println("Thread 2: Added 'Four' to set");
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Remove elements
            synchronizedList.remove("Two");
            System.out.println("Thread 2: Removed 'Two' from list");
            
            synchronizedMap.remove("Two");
            System.out.println("Thread 2: Removed 'Two' from map");
            
            synchronizedSet.remove("Two");
            System.out.println("Thread 2: Removed 'Two' from set");
        });
        
        // Start the threads
        thread1.start();
        thread2.start();
        
        // Wait for the threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Show the final state of the collections
        System.out.println("\nFinal list contents: " + synchronizedList);
        System.out.println("Final map contents: " + synchronizedMap);
        System.out.println("Final set contents: " + synchronizedSet);
    }
    
    /**
     * Demonstrates ConcurrentModificationException with non-concurrent collections.
     */
    private static void concurrentModificationExceptionExample() {
        // Create a regular ArrayList
        List<String> regularList = new ArrayList<>();
        regularList.add("One");
        regularList.add("Two");
        regularList.add("Three");
        
        // Try to modify the list while iterating
        System.out.println("Attempting to modify ArrayList during iteration:");
        try {
            for (String element : regularList) {
                System.out.println("Element: " + element);
                if (element.equals("Two")) {
                    regularList.remove(element);  // This will throw ConcurrentModificationException
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getClass().getName() + " - " + e.getMessage());
        }
        
        // Create a CopyOnWriteArrayList for comparison
        List<String> cowList = new CopyOnWriteArrayList<>(regularList);
        
        // Modify the list while iterating (this works with CopyOnWriteArrayList)
        System.out.println("\nModifying CopyOnWriteArrayList during iteration:");
        for (String element : cowList) {
            System.out.println("Element: " + element);
            if (element.equals("Two")) {
                cowList.remove(element);  // This works fine
                System.out.println("Removed 'Two'");
            }
        }
        
        System.out.println("Final CopyOnWriteArrayList contents: " + cowList);
        
        // Safe way to remove elements from a regular ArrayList
        System.out.println("\nSafe way to remove elements from ArrayList:");
        
        // Method 1: Using Iterator's remove method
        List<String> list1 = new ArrayList<>(List.of("One", "Two", "Three"));
        Iterator<String> iterator = list1.iterator();
        
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Element: " + element);
            if (element.equals("Two")) {
                iterator.remove();  // Safe way to remove
                System.out.println("Removed 'Two'");
            }
        }
        
        System.out.println("Final list1 contents: " + list1);
        
        // Method 2: Using removeIf (Java 8+)
        List<String> list2 = new ArrayList<>(List.of("One", "Two", "Three"));
        list2.removeIf(element -> element.equals("Two"));
        System.out.println("Final list2 contents after removeIf: " + list2);
    }
}
