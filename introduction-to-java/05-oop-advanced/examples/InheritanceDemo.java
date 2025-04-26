/**
 * InheritanceDemo.java
 * This program demonstrates inheritance and method overriding in Java.
 */
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("--- Basic Inheritance ---");
        
        // Create a Dog object
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        
        // Access methods from the parent class
        System.out.println("Dog's name: " + dog.getName());
        System.out.println("Dog's age: " + dog.getAge());
        dog.eat();
        dog.sleep();
        
        // Access method from the child class
        dog.bark();
        
        System.out.println("\n--- Method Overriding ---");
        
        // Create different animal objects
        Animal genericAnimal = new Animal("Generic Animal", 5);
        Cat cat = new Cat("Whiskers", 2, "Siamese");
        
        // Call the makeSound method on different objects
        genericAnimal.makeSound();  // Calls Animal's version
        dog.makeSound();            // Calls Dog's overridden version
        cat.makeSound();            // Calls Cat's overridden version
        
        System.out.println("\n--- Using super Keyword ---");
        
        // Demonstrate super for constructor
        System.out.println("Dog's breed: " + dog.getBreed());
        
        // Demonstrate super for methods
        cat.eat();  // This will call both Animal's eat method and add Cat-specific behavior
        
        System.out.println("\n--- Inheritance Hierarchy ---");
        
        // Create a GoldenRetriever (subclass of Dog)
        GoldenRetriever goldie = new GoldenRetriever("Max", 4, "Golden Retriever", true);
        
        // Access methods from all levels of the hierarchy
        System.out.println("Golden's name: " + goldie.getName());  // From Animal
        goldie.sleep();  // From Animal
        goldie.bark();   // From Dog
        goldie.fetch();  // From GoldenRetriever
        
        System.out.println("\n--- Constructor Chaining ---");
        
        // Create a Vehicle and its subclasses
        Vehicle vehicle = new Vehicle("Generic", "Vehicle");
        Car car = new Car("Toyota", "Corolla", 4);
        ElectricCar electricCar = new ElectricCar("Tesla", "Model 3", 4, 75.0);
        
        // Display information about each vehicle
        System.out.println("\nVehicle Info:");
        vehicle.displayInfo();
        
        System.out.println("\nCar Info:");
        car.displayInfo();
        
        System.out.println("\nElectric Car Info:");
        electricCar.displayInfo();
    }
}

/**
 * Parent class for the animal hierarchy.
 */
class Animal {
    private String name;
    private int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Animal constructor called");
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    public void makeSound() {
        System.out.println(name + " makes a generic animal sound");
    }
}

/**
 * Child class that extends Animal.
 */
class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // Call the parent constructor
        this.breed = breed;
        System.out.println("Dog constructor called");
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void bark() {
        System.out.println(getName() + " is barking");
    }
    
    @Override
    public void makeSound() {
        System.out.println(getName() + " says Woof!");
    }
}

/**
 * Another child class that extends Animal.
 */
class Cat extends Animal {
    private String breed;
    
    public Cat(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
        System.out.println("Cat constructor called");
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void meow() {
        System.out.println(getName() + " is meowing");
    }
    
    @Override
    public void makeSound() {
        System.out.println(getName() + " says Meow!");
    }
    
    @Override
    public void eat() {
        super.eat();  // Call the parent's eat method
        System.out.println(getName() + " eats with delicate bites");
    }
}

/**
 * Grandchild class that extends Dog.
 */
class GoldenRetriever extends Dog {
    private boolean trainedForService;
    
    public GoldenRetriever(String name, int age, String breed, boolean trainedForService) {
        super(name, age, breed);
        this.trainedForService = trainedForService;
        System.out.println("GoldenRetriever constructor called");
    }
    
    public boolean isTrainedForService() {
        return trainedForService;
    }
    
    public void fetch() {
        System.out.println(getName() + " is fetching the ball");
    }
    
    @Override
    public void makeSound() {
        System.out.println(getName() + " says Woof Woof!");
    }
}

/**
 * Base class for vehicles.
 */
class Vehicle {
    protected String make;
    protected String model;
    
    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
        System.out.println("Vehicle constructor called");
    }
    
    public void start() {
        System.out.println("Vehicle starting");
    }
    
    public void stop() {
        System.out.println("Vehicle stopping");
    }
    
    public void displayInfo() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
    }
}

/**
 * Car class that extends Vehicle.
 */
class Car extends Vehicle {
    private int numDoors;
    
    public Car(String make, String model, int numDoors) {
        super(make, model);  // Call parent constructor
        this.numDoors = numDoors;
        System.out.println("Car constructor called");
    }
    
    public int getNumDoors() {
        return numDoors;
    }
    
    @Override
    public void start() {
        System.out.println("Car engine starting");
    }
    
    @Override
    public void stop() {
        System.out.println("Car engine stopping");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method
        System.out.println("Number of doors: " + numDoors);
    }
}

/**
 * ElectricCar class that extends Car.
 */
class ElectricCar extends Car {
    private double batteryCapacity;  // in kWh
    
    public ElectricCar(String make, String model, int numDoors, double batteryCapacity) {
        super(make, model, numDoors);  // Call parent constructor
        this.batteryCapacity = batteryCapacity;
        System.out.println("ElectricCar constructor called");
    }
    
    public double getBatteryCapacity() {
        return batteryCapacity;
    }
    
    @Override
    public void start() {
        System.out.println("Electric car powering up");
    }
    
    @Override
    public void stop() {
        System.out.println("Electric car powering down");
    }
    
    public void charge() {
        System.out.println("Electric car charging");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method
        System.out.println("Battery capacity: " + batteryCapacity + " kWh");
    }
}
