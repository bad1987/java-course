/**
 * SerializationExample.java
 * This program demonstrates object serialization and deserialization in Java.
 */
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SerializationExample {
    public static void main(String[] args) {
        System.out.println("--- Serialization Examples ---");
        
        // Example 1: Basic Serialization and Deserialization
        System.out.println("\nExample 1: Basic Serialization and Deserialization");
        basicSerializationDeserialization();
        
        // Example 2: Serializing Multiple Objects
        System.out.println("\nExample 2: Serializing Multiple Objects");
        serializeMultipleObjects();
        
        // Example 3: Serializing Object Graphs
        System.out.println("\nExample 3: Serializing Object Graphs");
        serializeObjectGraphs();
        
        // Example 4: Transient Fields
        System.out.println("\nExample 4: Transient Fields");
        transientFields();
        
        // Example 5: Custom Serialization
        System.out.println("\nExample 5: Custom Serialization");
        customSerialization();
        
        // Example 6: Serialization with Inheritance
        System.out.println("\nExample 6: Serialization with Inheritance");
        serializationWithInheritance();
        
        // Example 7: Versioning with serialVersionUID
        System.out.println("\nExample 7: Versioning with serialVersionUID");
        versioningWithSerialVersionUID();
    }
    
    /**
     * Demonstrates basic serialization and deserialization.
     */
    public static void basicSerializationDeserialization() {
        // Create a Person object
        Person person = new Person("Alice", 30);
        
        // Serialize the object
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("person.ser"))) {
            
            oos.writeObject(person);
            System.out.println("Person object serialized: " + person);
            
        } catch (IOException e) {
            System.out.println("Error serializing object: " + e.getMessage());
        }
        
        // Deserialize the object
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("person.ser"))) {
            
            Person deserializedPerson = (Person) ois.readObject();
            System.out.println("Person object deserialized: " + deserializedPerson);
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing object: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates serializing multiple objects.
     */
    public static void serializeMultipleObjects() {
        // Create multiple Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        
        // Serialize the objects
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("people.ser"))) {
            
            // Write the number of objects
            oos.writeInt(people.size());
            
            // Write each object
            for (Person person : people) {
                oos.writeObject(person);
            }
            
            System.out.println("Serialized " + people.size() + " Person objects");
            
        } catch (IOException e) {
            System.out.println("Error serializing objects: " + e.getMessage());
        }
        
        // Deserialize the objects
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("people.ser"))) {
            
            // Read the number of objects
            int count = ois.readInt();
            
            // Read each object
            System.out.println("\nDeserialized " + count + " Person objects:");
            for (int i = 0; i < count; i++) {
                Person person = (Person) ois.readObject();
                System.out.println("- " + person);
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing objects: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates serializing object graphs (objects that reference other objects).
     */
    public static void serializeObjectGraphs() {
        // Create an Employee with an Address
        Address address = new Address("123 Main St", "Anytown", "CA", "12345");
        Employee employee = new Employee("Alice", 30, "Engineering", address);
        
        // Serialize the Employee (and its Address)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("employee.ser"))) {
            
            oos.writeObject(employee);
            System.out.println("Employee object serialized: " + employee);
            
        } catch (IOException e) {
            System.out.println("Error serializing object graph: " + e.getMessage());
        }
        
        // Deserialize the Employee (and its Address)
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("employee.ser"))) {
            
            Employee deserializedEmployee = (Employee) ois.readObject();
            System.out.println("\nEmployee object deserialized: " + deserializedEmployee);
            System.out.println("Employee's address: " + deserializedEmployee.getAddress());
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing object graph: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates the use of transient fields (fields that are not serialized).
     */
    public static void transientFields() {
        // Create a User object
        User user = new User("alice", "password123");
        user.setLastLoginTime(new Date());
        
        // Serialize the User
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("user.ser"))) {
            
            oos.writeObject(user);
            System.out.println("User object serialized: " + user);
            
        } catch (IOException e) {
            System.out.println("Error serializing user: " + e.getMessage());
        }
        
        // Deserialize the User
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("user.ser"))) {
            
            User deserializedUser = (User) ois.readObject();
            System.out.println("\nUser object deserialized: " + deserializedUser);
            
            // Note that the password (transient field) is null after deserialization
            System.out.println("Username: " + deserializedUser.getUsername());
            System.out.println("Password: " + deserializedUser.getPassword());
            System.out.println("Last login time: " + deserializedUser.getLastLoginTime());
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing user: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates custom serialization using writeObject and readObject methods.
     */
    public static void customSerialization() {
        // Create a BankAccount object
        BankAccount account = new BankAccount("123456789", "Alice", 1000.0);
        
        // Serialize the BankAccount
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("account.ser"))) {
            
            oos.writeObject(account);
            System.out.println("BankAccount object serialized: " + account);
            
        } catch (IOException e) {
            System.out.println("Error serializing bank account: " + e.getMessage());
        }
        
        // Deserialize the BankAccount
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("account.ser"))) {
            
            BankAccount deserializedAccount = (BankAccount) ois.readObject();
            System.out.println("\nBankAccount object deserialized: " + deserializedAccount);
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing bank account: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates serialization with inheritance.
     */
    public static void serializationWithInheritance() {
        // Create a Manager object (subclass of Employee)
        Address address = new Address("456 Oak St", "Somewhere", "NY", "67890");
        Manager manager = new Manager("Bob", 40, "Sales", address, 5);
        
        // Serialize the Manager
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("manager.ser"))) {
            
            oos.writeObject(manager);
            System.out.println("Manager object serialized: " + manager);
            
        } catch (IOException e) {
            System.out.println("Error serializing manager: " + e.getMessage());
        }
        
        // Deserialize the Manager
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("manager.ser"))) {
            
            Manager deserializedManager = (Manager) ois.readObject();
            System.out.println("\nManager object deserialized: " + deserializedManager);
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing manager: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates versioning with serialVersionUID.
     */
    public static void versioningWithSerialVersionUID() {
        // Create a Product object
        Product product = new Product(1, "Laptop", 999.99);
        
        // Serialize the Product
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("product.ser"))) {
            
            oos.writeObject(product);
            System.out.println("Product object serialized: " + product);
            
        } catch (IOException e) {
            System.out.println("Error serializing product: " + e.getMessage());
        }
        
        // Deserialize the Product
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("product.ser"))) {
            
            Product deserializedProduct = (Product) ois.readObject();
            System.out.println("\nProduct object deserialized: " + deserializedProduct);
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing product: " + e.getMessage());
        }
        
        // Note: If you change the Product class (add/remove fields) without updating
        // the serialVersionUID, deserialization of previously serialized objects will fail.
    }
}

/**
 * A simple Person class that implements Serializable.
 */
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
}

/**
 * An Address class that implements Serializable.
 */
class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String street;
    private String city;
    private String state;
    private String zipCode;
    
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    @Override
    public String toString() {
        return "Address{street='" + street + "', city='" + city + 
               "', state='" + state + "', zipCode='" + zipCode + "'}";
    }
}

/**
 * An Employee class that references an Address.
 */
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private String department;
    private Address address;  // Reference to another serializable object
    
    public Employee(String name, int age, String department, Address address) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.address = address;
    }
    
    public Address getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + 
               ", department='" + department + "'}";
    }
}

/**
 * A User class with a transient password field.
 */
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private transient String password;  // Transient field - not serialized
    private Date lastLoginTime;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    @Override
    public String toString() {
        return "User{username='" + username + "', password='" + password + 
               "', lastLoginTime=" + lastLoginTime + "}";
    }
}

/**
 * A BankAccount class with custom serialization.
 */
class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private transient String securityCode;  // Not serialized
    
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.securityCode = generateSecurityCode();
    }
    
    private String generateSecurityCode() {
        // In a real application, this would generate a secure code
        return "SEC-" + Math.round(Math.random() * 10000);
    }
    
    // Custom serialization method
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        // Call the default serialization implementation
        out.defaultWriteObject();
        
        // Write additional data or transform fields
        out.writeUTF("SECURE-DATA");
        
        // Note: We're not writing securityCode since it's transient
    }
    
    // Custom deserialization method
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Call the default deserialization implementation
        in.defaultReadObject();
        
        // Read the additional data
        String secureData = in.readUTF();
        System.out.println("Read secure data: " + secureData);
        
        // Regenerate the security code
        this.securityCode = generateSecurityCode();
    }
    
    @Override
    public String toString() {
        return "BankAccount{accountNumber='" + accountNumber + 
               "', accountHolder='" + accountHolder + 
               "', balance=" + balance + 
               ", securityCode='" + securityCode + "'}";
    }
}

/**
 * A Manager class that extends Employee.
 */
class Manager extends Employee {
    private static final long serialVersionUID = 1L;
    
    private int teamSize;
    
    public Manager(String name, int age, String department, Address address, int teamSize) {
        super(name, age, department, address);
        this.teamSize = teamSize;
    }
    
    @Override
    public String toString() {
        return super.toString().replace("}", "") + ", teamSize=" + teamSize + "}";
    }
}

/**
 * A Product class with a serialVersionUID.
 */
class Product implements Serializable {
    // If you change the class structure, update this value
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private double price;
    
    // If you add a new field without changing serialVersionUID,
    // deserialization of old objects will still work, but the new field
    // will have its default value.
    // private String category;
    
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}
