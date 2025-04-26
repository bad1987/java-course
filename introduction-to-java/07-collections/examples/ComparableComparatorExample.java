/**
 * ComparableComparatorExample.java
 * This program demonstrates the use of Comparable and Comparator interfaces in Java.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ComparableComparatorExample {
    public static void main(String[] args) {
        System.out.println("--- Comparable and Comparator Examples ---");
        
        // Example 1: Natural Ordering with Comparable
        System.out.println("\nExample 1: Natural Ordering with Comparable");
        naturalOrderingWithComparable();
        
        // Example 2: Custom Ordering with Comparator
        System.out.println("\nExample 2: Custom Ordering with Comparator");
        customOrderingWithComparator();
        
        // Example 3: Multiple Comparators
        System.out.println("\nExample 3: Multiple Comparators");
        multipleComparators();
        
        // Example 4: Comparator Composition
        System.out.println("\nExample 4: Comparator Composition");
        comparatorComposition();
        
        // Example 5: Comparable vs Comparator
        System.out.println("\nExample 5: Comparable vs Comparator");
        comparableVsComparator();
        
        // Example 6: Sorting Collections
        System.out.println("\nExample 6: Sorting Collections");
        sortingCollections();
        
        // Example 7: Using Comparators with TreeSet
        System.out.println("\nExample 7: Using Comparators with TreeSet");
        comparatorsWithTreeSet();
    }
    
    /**
     * Demonstrates natural ordering using the Comparable interface.
     */
    public static void naturalOrderingWithComparable() {
        // Creating a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("David", 28));
        
        System.out.println("Unsorted people list:");
        for (Person person : people) {
            System.out.println("- " + person);
        }
        
        // Sorting the list using natural ordering (defined by Person's compareTo method)
        Collections.sort(people);
        
        System.out.println("\nSorted people list (by age):");
        for (Person person : people) {
            System.out.println("- " + person);
        }
        
        // Creating a list of Book objects
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
        books.add(new Book("1984", "George Orwell", 1949));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        
        System.out.println("\nUnsorted books list:");
        for (Book book : books) {
            System.out.println("- " + book);
        }
        
        // Sorting the list using natural ordering (defined by Book's compareTo method)
        Collections.sort(books);
        
        System.out.println("\nSorted books list (by title):");
        for (Book book : books) {
            System.out.println("- " + book);
        }
    }
    
    /**
     * Demonstrates custom ordering using the Comparator interface.
     */
    public static void customOrderingWithComparator() {
        // Creating a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("David", 28));
        
        // Creating a comparator to sort by name
        Comparator<Person> nameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };
        
        // Sorting the list using the name comparator
        Collections.sort(people, nameComparator);
        
        System.out.println("People sorted by name:");
        for (Person person : people) {
            System.out.println("- " + person);
        }
        
        // Creating a list of Book objects
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
        books.add(new Book("1984", "George Orwell", 1949));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        
        // Creating a comparator to sort by publication year
        Comparator<Book> yearComparator = new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getYear() - b2.getYear();
            }
        };
        
        // Sorting the list using the year comparator
        Collections.sort(books, yearComparator);
        
        System.out.println("\nBooks sorted by publication year:");
        for (Book book : books) {
            System.out.println("- " + book);
        }
        
        // Creating a comparator to sort by author
        Comparator<Book> authorComparator = new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getAuthor().compareTo(b2.getAuthor());
            }
        };
        
        // Sorting the list using the author comparator
        Collections.sort(books, authorComparator);
        
        System.out.println("\nBooks sorted by author:");
        for (Book book : books) {
            System.out.println("- " + book);
        }
    }
    
    /**
     * Demonstrates using multiple comparators.
     */
    public static void multipleComparators() {
        // Creating a list of Employee objects
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "Engineering", 75000));
        employees.add(new Employee("Bob", "Marketing", 65000));
        employees.add(new Employee("Charlie", "Engineering", 80000));
        employees.add(new Employee("David", "Sales", 70000));
        employees.add(new Employee("Eve", "Marketing", 65000));
        
        System.out.println("Unsorted employees list:");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
        
        // Creating a comparator to sort by department
        Comparator<Employee> departmentComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getDepartment().compareTo(e2.getDepartment());
            }
        };
        
        // Sorting by department
        Collections.sort(employees, departmentComparator);
        
        System.out.println("\nEmployees sorted by department:");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
        
        // Creating a comparator to sort by salary (descending)
        Comparator<Employee> salaryComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e2.getSalary() - e1.getSalary();  // Note: descending order
            }
        };
        
        // Sorting by salary (descending)
        Collections.sort(employees, salaryComparator);
        
        System.out.println("\nEmployees sorted by salary (descending):");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
        
        // Creating a comparator to sort by department, then by salary (descending)
        Comparator<Employee> departmentThenSalaryComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                int departmentComparison = e1.getDepartment().compareTo(e2.getDepartment());
                if (departmentComparison != 0) {
                    return departmentComparison;
                }
                return e2.getSalary() - e1.getSalary();  // Note: descending order
            }
        };
        
        // Sorting by department, then by salary
        Collections.sort(employees, departmentThenSalaryComparator);
        
        System.out.println("\nEmployees sorted by department, then by salary (descending):");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
    }
    
    /**
     * Demonstrates comparator composition using Java 8+ features.
     */
    public static void comparatorComposition() {
        // Creating a list of Employee objects
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "Engineering", 75000));
        employees.add(new Employee("Bob", "Marketing", 65000));
        employees.add(new Employee("Charlie", "Engineering", 80000));
        employees.add(new Employee("David", "Sales", 70000));
        employees.add(new Employee("Eve", "Marketing", 65000));
        
        // Using Comparator.comparing (Java 8+)
        Comparator<Employee> byDepartment = Comparator.comparing(Employee::getDepartment);
        
        // Sorting by department
        Collections.sort(employees, byDepartment);
        
        System.out.println("Employees sorted by department (using Comparator.comparing):");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
        
        // Using reversed() to sort in descending order
        Comparator<Employee> bySalaryDesc = Comparator.comparing(Employee::getSalary).reversed();
        
        // Sorting by salary (descending)
        Collections.sort(employees, bySalaryDesc);
        
        System.out.println("\nEmployees sorted by salary (descending, using reversed()):");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
        
        // Composing comparators with thenComparing
        Comparator<Employee> byDeptThenSalaryDesc = Comparator
            .comparing(Employee::getDepartment)
            .thenComparing(Comparator.comparing(Employee::getSalary).reversed());
        
        // Sorting by department, then by salary (descending)
        Collections.sort(employees, byDeptThenSalaryDesc);
        
        System.out.println("\nEmployees sorted by department, then by salary (descending, using thenComparing):");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
        
        // Composing multiple comparators
        Comparator<Employee> complex = Comparator
            .comparing(Employee::getDepartment)
            .thenComparing(Employee::getSalary, Comparator.reverseOrder())
            .thenComparing(Employee::getName);
        
        // Sorting with the complex comparator
        Collections.sort(employees, complex);
        
        System.out.println("\nEmployees sorted with complex comparator:");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
    }
    
    /**
     * Compares the use of Comparable and Comparator.
     */
    public static void comparableVsComparator() {
        // Creating a list of Person objects (implements Comparable)
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        
        // Natural ordering (using Comparable)
        Collections.sort(people);
        
        System.out.println("People sorted by natural ordering (age):");
        for (Person person : people) {
            System.out.println("- " + person);
        }
        
        // Custom ordering (using Comparator)
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        
        System.out.println("\nPeople sorted by custom ordering (name):");
        for (Person person : people) {
            System.out.println("- " + person);
        }
        
        // Creating a list of Product objects (does not implement Comparable)
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Phone", 699.99));
        products.add(new Product("Tablet", 499.99));
        
        // Cannot use natural ordering (no Comparable implementation)
        // Collections.sort(products);  // This would cause a compilation error
        
        // Must use a Comparator
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        
        System.out.println("\nProducts sorted by price:");
        for (Product product : products) {
            System.out.println("- " + product);
        }
        
        // Alternative: Using lambda expression (Java 8+)
        Collections.sort(products, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        
        System.out.println("\nProducts sorted by name (using lambda):");
        for (Product product : products) {
            System.out.println("- " + product);
        }
    }
    
    /**
     * Demonstrates sorting different types of collections.
     */
    public static void sortingCollections() {
        // Sorting arrays
        Integer[] numbers = {5, 2, 8, 1, 9, 3};
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        // Sorting with a custom comparator
        Arrays.sort(numbers, Collections.reverseOrder());
        System.out.println("Array sorted in reverse: " + Arrays.toString(numbers));
        
        // Sorting a List
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Fig");
        
        System.out.println("\nOriginal list: " + fruits);
        
        Collections.sort(fruits);
        System.out.println("Sorted list: " + fruits);
        
        Collections.sort(fruits, Collections.reverseOrder());
        System.out.println("List sorted in reverse: " + fruits);
        
        // Sorting part of a list
        List<String> names = new ArrayList<>();
        names.add("Zach");
        names.add("Bob");
        names.add("Alice");
        names.add("Eve");
        names.add("Charlie");
        
        System.out.println("\nOriginal names list: " + names);
        
        // Sort only the first 3 elements
        Collections.sort(names.subList(0, 3));
        System.out.println("After sorting first 3 elements: " + names);
        
        // Sort the entire list
        Collections.sort(names);
        System.out.println("After sorting entire list: " + names);
        
        // Binary search (requires a sorted list)
        int index = Collections.binarySearch(names, "Eve");
        System.out.println("Index of 'Eve': " + index);
    }
    
    /**
     * Demonstrates using comparators with TreeSet.
     */
    public static void comparatorsWithTreeSet() {
        // TreeSet with natural ordering
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        
        System.out.println("TreeSet with natural ordering: " + numbers);
        
        // TreeSet with custom comparator (reverse order)
        TreeSet<Integer> reverseNumbers = new TreeSet<>(Collections.reverseOrder());
        reverseNumbers.add(5);
        reverseNumbers.add(2);
        reverseNumbers.add(8);
        reverseNumbers.add(1);
        reverseNumbers.add(9);
        
        System.out.println("TreeSet with reverse ordering: " + reverseNumbers);
        
        // TreeSet with Person objects (uses natural ordering)
        TreeSet<Person> people = new TreeSet<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        
        System.out.println("\nTreeSet of Person objects (sorted by age):");
        for (Person person : people) {
            System.out.println("- " + person);
        }
        
        // TreeSet with Person objects and custom comparator
        TreeSet<Person> peopleByName = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        
        peopleByName.add(new Person("Alice", 30));
        peopleByName.add(new Person("Bob", 25));
        peopleByName.add(new Person("Charlie", 35));
        
        System.out.println("\nTreeSet of Person objects (sorted by name):");
        for (Person person : peopleByName) {
            System.out.println("- " + person);
        }
        
        // TreeSet with Product objects (requires a comparator)
        TreeSet<Product> products = new TreeSet<>(Comparator.comparing(Product::getPrice));
        
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Phone", 699.99));
        products.add(new Product("Tablet", 499.99));
        
        System.out.println("\nTreeSet of Product objects (sorted by price):");
        for (Product product : products) {
            System.out.println("- " + product);
        }
    }
}

/**
 * A Person class that implements Comparable for natural ordering by age.
 */
class Person implements Comparable<Person> {
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
    
    @Override
    public int compareTo(Person other) {
        // Natural ordering by age
        return this.age - other.age;
    }
}

/**
 * A Book class that implements Comparable for natural ordering by title.
 */
class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;
    
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public int getYear() {
        return year;
    }
    
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', year=" + year + "}";
    }
    
    @Override
    public int compareTo(Book other) {
        // Natural ordering by title
        return this.title.compareTo(other.title);
    }
}

/**
 * An Employee class for demonstrating multiple comparators.
 */
class Employee {
    private String name;
    private String department;
    private int salary;
    
    public Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public int getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}

/**
 * A Product class that does not implement Comparable.
 */
class Product {
    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}
