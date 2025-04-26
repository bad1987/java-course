/**
 * MapExample.java
 * This program demonstrates the use of Map implementations in Java.
 */
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.Collection;

public class MapExample {
    public static void main(String[] args) {
        System.out.println("--- Map Examples ---");
        
        // Example 1: HashMap Basics
        System.out.println("\nExample 1: HashMap Basics");
        hashMapBasics();
        
        // Example 2: TreeMap Basics
        System.out.println("\nExample 2: TreeMap Basics");
        treeMapBasics();
        
        // Example 3: LinkedHashMap Basics
        System.out.println("\nExample 3: LinkedHashMap Basics");
        linkedHashMapBasics();
        
        // Example 4: Map Operations
        System.out.println("\nExample 4: Map Operations");
        mapOperations();
        
        // Example 5: Iterating Through Maps
        System.out.println("\nExample 5: Iterating Through Maps");
        iteratingThroughMaps();
        
        // Example 6: Maps with Custom Objects
        System.out.println("\nExample 6: Maps with Custom Objects");
        mapsWithCustomObjects();
        
        // Example 7: Nested Maps
        System.out.println("\nExample 7: Nested Maps");
        nestedMaps();
    }
    
    /**
     * Demonstrates basic HashMap operations.
     */
    public static void hashMapBasics() {
        // Creating a HashMap
        Map<String, Integer> ages = new HashMap<>();
        
        // Adding key-value pairs
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 35);
        
        // Displaying the map
        System.out.println("Ages map: " + ages);
        
        // Size of the map
        System.out.println("Number of entries: " + ages.size());
        
        // Accessing values by key
        int bobAge = ages.get("Bob");
        System.out.println("Bob's age: " + bobAge);
        
        // Checking if a key exists
        boolean containsAlice = ages.containsKey("Alice");
        System.out.println("Contains Alice? " + containsAlice);
        
        // Checking if a value exists
        boolean contains30 = ages.containsValue(30);
        System.out.println("Contains age 30? " + contains30);
        
        // Updating a value
        ages.put("Alice", 26);  // Overwrites the existing value
        System.out.println("Alice's updated age: " + ages.get("Alice"));
        
        // Removing an entry
        ages.remove("Charlie");
        System.out.println("After removing Charlie: " + ages);
        
        // Getting a default value if key doesn't exist
        int davidAge = ages.getOrDefault("David", 0);
        System.out.println("David's age (default): " + davidAge);
        
        // Clearing the map
        ages.clear();
        System.out.println("After clearing the map: " + ages);
        System.out.println("Is the map empty? " + ages.isEmpty());
    }
    
    /**
     * Demonstrates basic TreeMap operations.
     */
    public static void treeMapBasics() {
        // Creating a TreeMap
        Map<String, Double> grades = new TreeMap<>();
        
        // Adding key-value pairs
        grades.put("Charlie", 85.5);
        grades.put("Alice", 92.3);
        grades.put("Bob", 88.7);
        
        // Displaying the map (keys are sorted)
        System.out.println("Grades map (sorted by keys): " + grades);
        
        // Size of the map
        System.out.println("Number of entries: " + grades.size());
        
        // Accessing values by key
        double aliceGrade = grades.get("Alice");
        System.out.println("Alice's grade: " + aliceGrade);
        
        // Checking if a key exists
        boolean containsDavid = grades.containsKey("David");
        System.out.println("Contains David? " + containsDavid);
        
        // Updating a value
        grades.put("Bob", 90.0);
        System.out.println("Bob's updated grade: " + grades.get("Bob"));
        
        // TreeMap-specific operations
        TreeMap<String, Double> treeGrades = (TreeMap<String, Double>) grades;
        
        // Getting the first and last entries
        Map.Entry<String, Double> firstEntry = treeGrades.firstEntry();
        Map.Entry<String, Double> lastEntry = treeGrades.lastEntry();
        
        System.out.println("First entry: " + firstEntry.getKey() + " = " + firstEntry.getValue());
        System.out.println("Last entry: " + lastEntry.getKey() + " = " + lastEntry.getValue());
        
        // Getting entries with keys less than or greater than a value
        Map<String, Double> headMap = treeGrades.headMap("C");
        Map<String, Double> tailMap = treeGrades.tailMap("C");
        
        System.out.println("Entries with keys before 'C': " + headMap);
        System.out.println("Entries with keys after or equal to 'C': " + tailMap);
    }
    
    /**
     * Demonstrates basic LinkedHashMap operations.
     */
    public static void linkedHashMapBasics() {
        // Creating a LinkedHashMap
        Map<String, String> capitals = new LinkedHashMap<>();
        
        // Adding key-value pairs
        capitals.put("USA", "Washington D.C.");
        capitals.put("UK", "London");
        capitals.put("France", "Paris");
        capitals.put("Germany", "Berlin");
        
        // Displaying the map (maintains insertion order)
        System.out.println("Capitals map (insertion order): " + capitals);
        
        // Size of the map
        System.out.println("Number of entries: " + capitals.size());
        
        // Accessing values by key
        String ukCapital = capitals.get("UK");
        System.out.println("UK capital: " + ukCapital);
        
        // Removing an entry
        capitals.remove("France");
        System.out.println("After removing France: " + capitals);
        
        // Adding more entries
        capitals.put("Japan", "Tokyo");
        capitals.put("Australia", "Canberra");
        
        // Displaying the map (new entries are added at the end)
        System.out.println("Updated capitals map: " + capitals);
        
        // Creating a LinkedHashMap with access order (instead of insertion order)
        Map<String, Integer> cache = new LinkedHashMap<>(16, 0.75f, true);
        
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        
        System.out.println("Cache (initial): " + cache);
        
        // Accessing elements changes their order
        cache.get("A");
        System.out.println("Cache (after accessing A): " + cache);
        
        cache.get("B");
        System.out.println("Cache (after accessing B): " + cache);
    }
    
    /**
     * Demonstrates various Map operations.
     */
    public static void mapOperations() {
        Map<String, Integer> scores = new HashMap<>();
        
        // Adding entries
        scores.put("Alice", 95);
        scores.put("Bob", 80);
        scores.put("Charlie", 85);
        
        System.out.println("Initial scores: " + scores);
        
        // putIfAbsent - adds a key-value pair only if the key doesn't exist
        scores.putIfAbsent("Alice", 100);  // Won't change Alice's score
        scores.putIfAbsent("David", 90);   // Will add David
        
        System.out.println("After putIfAbsent: " + scores);
        
        // compute - computes a new value for a given key
        scores.compute("Bob", (key, oldValue) -> oldValue + 5);
        
        System.out.println("After compute (Bob + 5): " + scores);
        
        // computeIfPresent - computes a new value if the key exists
        scores.computeIfPresent("Charlie", (key, oldValue) -> oldValue + 10);
        scores.computeIfPresent("Eve", (key, oldValue) -> 70);  // Won't do anything
        
        System.out.println("After computeIfPresent: " + scores);
        
        // computeIfAbsent - computes a value if the key doesn't exist
        scores.computeIfAbsent("Eve", key -> 70);  // Will add Eve
        scores.computeIfAbsent("Alice", key -> 100);  // Won't change Alice's score
        
        System.out.println("After computeIfAbsent: " + scores);
        
        // merge - merges the current value with a new value
        scores.merge("Alice", 5, (oldValue, value) -> oldValue + value);  // Adds 5 to Alice's score
        scores.merge("Frank", 60, (oldValue, value) -> oldValue + value);  // Adds Frank with 60
        
        System.out.println("After merge: " + scores);
        
        // replace - replaces the value for a key
        scores.replace("Bob", 90);
        System.out.println("After replace (Bob): " + scores);
        
        // replace with old value check
        scores.replace("Charlie", 85, 95);  // Won't replace (current value is not 85)
        scores.replace("Charlie", 95, 100);  // Will replace
        
        System.out.println("After conditional replace (Charlie): " + scores);
        
        // replaceAll - replaces all values using a function
        scores.replaceAll((key, value) -> value + 2);
        
        System.out.println("After replaceAll (all scores + 2): " + scores);
    }
    
    /**
     * Demonstrates different ways to iterate through a Map.
     */
    public static void iteratingThroughMaps() {
        Map<String, String> countries = new HashMap<>();
        countries.put("US", "United States");
        countries.put("CA", "Canada");
        countries.put("MX", "Mexico");
        countries.put("BR", "Brazil");
        
        // 1. Iterating through the entry set
        System.out.println("Iterating through entry set:");
        Set<Map.Entry<String, String>> entries = countries.entrySet();
        
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // 2. Iterating through the key set
        System.out.println("\nIterating through key set:");
        Set<String> keys = countries.keySet();
        
        for (String key : keys) {
            System.out.println(key + ": " + countries.get(key));
        }
        
        // 3. Iterating through the values
        System.out.println("\nIterating through values:");
        Collection<String> values = countries.values();
        
        for (String value : values) {
            System.out.println(value);
        }
        
        // 4. Using forEach (Java 8+)
        System.out.println("\nUsing forEach:");
        countries.forEach((key, value) -> System.out.println(key + ": " + value));
        
        // 5. Using Stream API (Java 8+)
        System.out.println("\nUsing Stream API:");
        countries.entrySet().stream()
                .filter(entry -> entry.getKey().length() == 2)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
    
    /**
     * Demonstrates using Maps with custom objects as keys and values.
     */
    public static void mapsWithCustomObjects() {
        // Map with custom objects as values
        Map<String, Student> students = new HashMap<>();
        
        // Adding students to the map
        students.put("S001", new Student("Alice", "Computer Science"));
        students.put("S002", new Student("Bob", "Mathematics"));
        students.put("S003", new Student("Charlie", "Physics"));
        
        // Accessing a student
        Student bob = students.get("S002");
        System.out.println("Student S002: " + bob);
        
        // Updating a student
        students.get("S001").setMajor("Data Science");
        System.out.println("Updated Alice's major: " + students.get("S001"));
        
        // Map with custom objects as keys
        Map<Course, Integer> courseEnrollments = new HashMap<>();
        
        // Adding courses and enrollments
        courseEnrollments.put(new Course("CS101", "Introduction to Programming"), 150);
        courseEnrollments.put(new Course("MATH201", "Calculus II"), 75);
        courseEnrollments.put(new Course("PHYS101", "Physics I"), 100);
        
        // This will work correctly only if Course has proper equals() and hashCode() implementations
        System.out.println("Course enrollments: " + courseEnrollments);
        
        // Trying to access a course
        int enrollment = courseEnrollments.getOrDefault(
            new Course("CS101", "Introduction to Programming"), 0);
        System.out.println("CS101 enrollment: " + enrollment);
    }
    
    /**
     * Demonstrates using nested Maps.
     */
    public static void nestedMaps() {
        // Creating a nested map: country -> (city -> population)
        Map<String, Map<String, Integer>> countryData = new HashMap<>();
        
        // Adding data for USA
        Map<String, Integer> usaCities = new HashMap<>();
        usaCities.put("New York", 8419000);
        usaCities.put("Los Angeles", 3980000);
        usaCities.put("Chicago", 2716000);
        countryData.put("USA", usaCities);
        
        // Adding data for UK
        Map<String, Integer> ukCities = new HashMap<>();
        ukCities.put("London", 8982000);
        ukCities.put("Manchester", 553230);
        ukCities.put("Birmingham", 1141400);
        countryData.put("UK", ukCities);
        
        // Displaying the nested map
        System.out.println("Country data:");
        for (Map.Entry<String, Map<String, Integer>> countryEntry : countryData.entrySet()) {
            String country = countryEntry.getKey();
            Map<String, Integer> cities = countryEntry.getValue();
            
            System.out.println(country + ":");
            for (Map.Entry<String, Integer> cityEntry : cities.entrySet()) {
                System.out.println("  " + cityEntry.getKey() + ": " + cityEntry.getValue());
            }
        }
        
        // Accessing specific data
        int londonPopulation = countryData.get("UK").get("London");
        System.out.println("\nLondon population: " + londonPopulation);
        
        // Adding a new city to an existing country
        countryData.get("USA").put("Houston", 2320000);
        
        // Adding a new country
        Map<String, Integer> canadaCities = new HashMap<>();
        canadaCities.put("Toronto", 2930000);
        canadaCities.put("Montreal", 1780000);
        countryData.put("Canada", canadaCities);
        
        // Checking if a country exists
        boolean hasJapan = countryData.containsKey("Japan");
        System.out.println("Has Japan? " + hasJapan);
        
        // Checking if a city exists in a country
        boolean hasBoston = countryData.get("USA").containsKey("Boston");
        System.out.println("USA has Boston? " + hasBoston);
        
        // Getting the total population of all cities in the UK
        int totalUKPopulation = 0;
        for (int population : countryData.get("UK").values()) {
            totalUKPopulation += population;
        }
        System.out.println("Total population of UK cities: " + totalUKPopulation);
    }
}

/**
 * A simple Student class for demonstrating maps with custom objects as values.
 */
class Student {
    private String name;
    private String major;
    
    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }
    
    public String getName() {
        return name;
    }
    
    public String getMajor() {
        return major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', major='" + major + "'}";
    }
}

/**
 * A Course class with proper equals() and hashCode() implementations for use as a map key.
 */
class Course {
    private String code;
    private String title;
    
    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        return "Course{code='" + code + "', title='" + title + "'}";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Course course = (Course) o;
        
        if (!code.equals(course.code)) return false;
        return title.equals(course.title);
    }
    
    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
