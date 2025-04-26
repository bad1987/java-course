/**
 * SetExample.java
 * This program demonstrates the use of Set implementations in Java.
 */
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class SetExample {
    public static void main(String[] args) {
        System.out.println("--- Set Examples ---");
        
        // Example 1: HashSet Basics
        System.out.println("\nExample 1: HashSet Basics");
        hashSetBasics();
        
        // Example 2: TreeSet Basics
        System.out.println("\nExample 2: TreeSet Basics");
        treeSetBasics();
        
        // Example 3: LinkedHashSet Basics
        System.out.println("\nExample 3: LinkedHashSet Basics");
        linkedHashSetBasics();
        
        // Example 4: Set Operations
        System.out.println("\nExample 4: Set Operations");
        setOperations();
        
        // Example 5: Custom Objects in Sets
        System.out.println("\nExample 5: Custom Objects in Sets");
        customObjectsInSets();
        
        // Example 6: Performance Comparison
        System.out.println("\nExample 6: Performance Comparison");
        performanceComparison();
    }
    
    /**
     * Demonstrates basic HashSet operations.
     */
    public static void hashSetBasics() {
        // Creating a HashSet
        Set<String> fruits = new HashSet<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Apple");  // Duplicate, will not be added
        
        // Displaying the set
        System.out.println("Fruits set: " + fruits);
        
        // Size of the set
        System.out.println("Number of fruits: " + fruits.size());
        
        // Checking if an element exists
        boolean containsBanana = fruits.contains("Banana");
        System.out.println("Contains Banana? " + containsBanana);
        
        // Removing an element
        fruits.remove("Banana");
        System.out.println("After removing Banana: " + fruits);
        
        // Iterating through the set
        System.out.println("Iterating through the set:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        
        // Using an iterator
        System.out.println("Using an iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("- " + fruit);
        }
        
        // Clearing the set
        fruits.clear();
        System.out.println("After clearing the set: " + fruits);
        System.out.println("Is the set empty? " + fruits.isEmpty());
    }
    
    /**
     * Demonstrates basic TreeSet operations.
     */
    public static void treeSetBasics() {
        // Creating a TreeSet
        Set<String> names = new TreeSet<>();
        
        // Adding elements
        names.add("Charlie");
        names.add("Alice");
        names.add("Bob");
        names.add("Alice");  // Duplicate, will not be added
        
        // Displaying the set (elements are sorted)
        System.out.println("Names set (sorted): " + names);
        
        // Size of the set
        System.out.println("Number of names: " + names.size());
        
        // Checking if an element exists
        boolean containsDavid = names.contains("David");
        System.out.println("Contains David? " + containsDavid);
        
        // Removing an element
        names.remove("Bob");
        System.out.println("After removing Bob: " + names);
        
        // Iterating through the set (in sorted order)
        System.out.println("Iterating through the set (sorted order):");
        for (String name : names) {
            System.out.println("- " + name);
        }
        
        // TreeSet with custom sorting (using TreeSet-specific methods)
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        
        System.out.println("Numbers set (sorted): " + numbers);
        
        // Finding the first and last elements
        System.out.println("First number: " + numbers.first());
        System.out.println("Last number: " + numbers.last());
        
        // Finding elements less than or greater than a value
        System.out.println("Numbers less than 5: " + numbers.headSet(5));
        System.out.println("Numbers greater than or equal to 5: " + numbers.tailSet(5));
        
        // Finding a subset
        System.out.println("Numbers between 2 and 8 (inclusive): " + numbers.subSet(2, 9));
    }
    
    /**
     * Demonstrates basic LinkedHashSet operations.
     */
    public static void linkedHashSetBasics() {
        // Creating a LinkedHashSet
        Set<String> colors = new LinkedHashSet<>();
        
        // Adding elements
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Red");  // Duplicate, will not be added
        
        // Displaying the set (elements maintain insertion order)
        System.out.println("Colors set (insertion order): " + colors);
        
        // Size of the set
        System.out.println("Number of colors: " + colors.size());
        
        // Checking if an element exists
        boolean containsYellow = colors.contains("Yellow");
        System.out.println("Contains Yellow? " + containsYellow);
        
        // Removing an element
        colors.remove("Green");
        System.out.println("After removing Green: " + colors);
        
        // Adding more elements
        colors.add("Yellow");
        colors.add("Purple");
        
        // Iterating through the set (in insertion order)
        System.out.println("Iterating through the set (insertion order):");
        for (String color : colors) {
            System.out.println("- " + color);
        }
    }
    
    /**
     * Demonstrates set operations like union, intersection, and difference.
     */
    public static void setOperations() {
        // Creating two sets
        Set<String> set1 = new HashSet<>();
        set1.add("A");
        set1.add("B");
        set1.add("C");
        
        Set<String> set2 = new HashSet<>();
        set2.add("B");
        set2.add("C");
        set2.add("D");
        
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        
        // Union (all elements from both sets)
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);
        
        // Intersection (elements common to both sets)
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Difference (elements in set1 but not in set2)
        Set<String> difference1 = new HashSet<>(set1);
        difference1.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference1);
        
        // Difference (elements in set2 but not in set1)
        Set<String> difference2 = new HashSet<>(set2);
        difference2.removeAll(set1);
        System.out.println("Difference (set2 - set1): " + difference2);
        
        // Symmetric difference (elements in either set but not in both)
        Set<String> symmetricDifference = new HashSet<>(set1);
        symmetricDifference.addAll(set2);  // Union
        
        Set<String> temp = new HashSet<>(set1);
        temp.retainAll(set2);  // Intersection
        
        symmetricDifference.removeAll(temp);  // Remove the intersection
        System.out.println("Symmetric difference: " + symmetricDifference);
    }
    
    /**
     * Demonstrates using sets with custom objects.
     */
    public static void customObjectsInSets() {
        // Creating a set of Person objects
        Set<Person> people = new HashSet<>();
        
        // Adding Person objects to the set
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("Alice", 30));  // This should be considered a duplicate
        
        // Displaying the set
        System.out.println("People in the set:");
        for (Person person : people) {
            System.out.println("- " + person);
        }
        
        System.out.println("Number of people: " + people.size());
        
        // Note: Without proper equals() and hashCode() implementations,
        // duplicate Person objects will be added to the HashSet
        
        // Creating a set of Employee objects (with proper equals and hashCode)
        Set<Employee> employees = new HashSet<>();
        
        // Adding Employee objects to the set
        employees.add(new Employee("Alice", "Engineering"));
        employees.add(new Employee("Bob", "Marketing"));
        employees.add(new Employee("Charlie", "Sales"));
        employees.add(new Employee("Alice", "Engineering"));  // This should be considered a duplicate
        
        // Displaying the set
        System.out.println("\nEmployees in the set:");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
        
        System.out.println("Number of employees: " + employees.size());
    }
    
    /**
     * Compares the performance of different Set implementations.
     */
    public static void performanceComparison() {
        final int SIZE = 100000;
        
        // HashSet performance
        Set<Integer> hashSet = new HashSet<>();
        long startTime = System.nanoTime();
        
        // Adding elements
        for (int i = 0; i < SIZE; i++) {
            hashSet.add(i);
        }
        
        // Checking for existence
        for (int i = 0; i < SIZE; i++) {
            hashSet.contains(i);
        }
        
        long endTime = System.nanoTime();
        System.out.println("HashSet time: " + (endTime - startTime) / 1000000 + " ms");
        
        // TreeSet performance
        Set<Integer> treeSet = new TreeSet<>();
        startTime = System.nanoTime();
        
        // Adding elements
        for (int i = 0; i < SIZE; i++) {
            treeSet.add(i);
        }
        
        // Checking for existence
        for (int i = 0; i < SIZE; i++) {
            treeSet.contains(i);
        }
        
        endTime = System.nanoTime();
        System.out.println("TreeSet time: " + (endTime - startTime) / 1000000 + " ms");
        
        // LinkedHashSet performance
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        startTime = System.nanoTime();
        
        // Adding elements
        for (int i = 0; i < SIZE; i++) {
            linkedHashSet.add(i);
        }
        
        // Checking for existence
        for (int i = 0; i < SIZE; i++) {
            linkedHashSet.contains(i);
        }
        
        endTime = System.nanoTime();
        System.out.println("LinkedHashSet time: " + (endTime - startTime) / 1000000 + " ms");
    }
}

/**
 * A simple Person class without proper equals() and hashCode() implementations.
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
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
    
    // Note: No equals() or hashCode() implementation
}

/**
 * An Employee class with proper equals() and hashCode() implementations.
 */
class Employee {
    private String name;
    private String department;
    
    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "'}";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Employee employee = (Employee) o;
        
        if (!name.equals(employee.name)) return false;
        return department.equals(employee.department);
    }
    
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + department.hashCode();
        return result;
    }
}
