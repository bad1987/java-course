/**
 * AtomicVariablesExample.java
 * This program demonstrates the use of atomic variables in Java.
 */
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.LongAdder;

public class AtomicVariablesExample {
    public static void main(String[] args) {
        System.out.println("--- Atomic Variables Examples ---");
        
        // Example 1: AtomicInteger
        System.out.println("\nExample 1: AtomicInteger");
        atomicIntegerExample();
        
        // Example 2: AtomicBoolean
        System.out.println("\nExample 2: AtomicBoolean");
        atomicBooleanExample();
        
        // Example 3: AtomicReference
        System.out.println("\nExample 3: AtomicReference");
        atomicReferenceExample();
        
        // Example 4: AtomicIntegerArray
        System.out.println("\nExample 4: AtomicIntegerArray");
        atomicIntegerArrayExample();
        
        // Example 5: LongAdder (Java 8+)
        System.out.println("\nExample 5: LongAdder");
        longAdderExample();
        
        // Example 6: Compare and Set
        System.out.println("\nExample 6: Compare and Set");
        compareAndSetExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates the use of AtomicInteger.
     */
    private static void atomicIntegerExample() {
        // Create an AtomicInteger
        AtomicInteger atomicInt = new AtomicInteger(0);
        
        // Create threads that increment the atomic integer
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicInt.incrementAndGet();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicInt.incrementAndGet();
            }
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
        
        // The final value should be 2000
        System.out.println("Final value: " + atomicInt.get());
        
        // Demonstrate various AtomicInteger operations
        System.out.println("\nAtomicInteger operations:");
        
        // get and set
        atomicInt.set(10);
        System.out.println("After set(10): " + atomicInt.get());
        
        // getAndIncrement
        int oldValue = atomicInt.getAndIncrement();
        System.out.println("getAndIncrement: oldValue = " + oldValue + ", newValue = " + atomicInt.get());
        
        // incrementAndGet
        int newValue = atomicInt.incrementAndGet();
        System.out.println("incrementAndGet: newValue = " + newValue);
        
        // getAndDecrement
        oldValue = atomicInt.getAndDecrement();
        System.out.println("getAndDecrement: oldValue = " + oldValue + ", newValue = " + atomicInt.get());
        
        // decrementAndGet
        newValue = atomicInt.decrementAndGet();
        System.out.println("decrementAndGet: newValue = " + newValue);
        
        // getAndAdd
        oldValue = atomicInt.getAndAdd(5);
        System.out.println("getAndAdd(5): oldValue = " + oldValue + ", newValue = " + atomicInt.get());
        
        // addAndGet
        newValue = atomicInt.addAndGet(5);
        System.out.println("addAndGet(5): newValue = " + newValue);
        
        // getAndSet
        oldValue = atomicInt.getAndSet(100);
        System.out.println("getAndSet(100): oldValue = " + oldValue + ", newValue = " + atomicInt.get());
        
        // compareAndSet
        boolean success = atomicInt.compareAndSet(100, 200);
        System.out.println("compareAndSet(100, 200): success = " + success + ", newValue = " + atomicInt.get());
        
        success = atomicInt.compareAndSet(100, 300);
        System.out.println("compareAndSet(100, 300): success = " + success + ", value unchanged = " + atomicInt.get());
        
        // updateAndGet (Java 8+)
        newValue = atomicInt.updateAndGet(x -> x * 2);
        System.out.println("updateAndGet(x -> x * 2): newValue = " + newValue);
        
        // accumulateAndGet (Java 8+)
        newValue = atomicInt.accumulateAndGet(10, (x, y) -> x + y);
        System.out.println("accumulateAndGet(10, (x, y) -> x + y): newValue = " + newValue);
    }
    
    /**
     * Demonstrates the use of AtomicBoolean.
     */
    private static void atomicBooleanExample() {
        // Create an AtomicBoolean
        AtomicBoolean atomicBool = new AtomicBoolean(false);
        
        // Create a thread that sets the flag
        Thread setterThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Setter thread: Setting flag to true");
                atomicBool.set(true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Create a thread that waits for the flag
        Thread waiterThread = new Thread(() -> {
            System.out.println("Waiter thread: Waiting for flag to be set...");
            
            // Spin until the flag is set
            while (!atomicBool.get()) {
                // Busy-wait (not recommended in practice)
                Thread.yield();
            }
            
            System.out.println("Waiter thread: Flag was set, exiting.");
        });
        
        // Start the threads
        waiterThread.start();
        setterThread.start();
        
        // Wait for the threads to finish
        try {
            waiterThread.join();
            setterThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate AtomicBoolean operations
        System.out.println("\nAtomicBoolean operations:");
        
        // get and set
        atomicBool.set(false);
        System.out.println("After set(false): " + atomicBool.get());
        
        // getAndSet
        boolean oldValue = atomicBool.getAndSet(true);
        System.out.println("getAndSet(true): oldValue = " + oldValue + ", newValue = " + atomicBool.get());
        
        // compareAndSet
        boolean success = atomicBool.compareAndSet(true, false);
        System.out.println("compareAndSet(true, false): success = " + success + ", newValue = " + atomicBool.get());
        
        success = atomicBool.compareAndSet(true, true);
        System.out.println("compareAndSet(true, true): success = " + success + ", value unchanged = " + atomicBool.get());
    }
    
    /**
     * Demonstrates the use of AtomicReference.
     */
    private static void atomicReferenceExample() {
        // Create a class for the reference
        class Person {
            private final String name;
            private final int age;
            
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
        
        // Create an AtomicReference
        AtomicReference<Person> atomicRef = new AtomicReference<>(new Person("Alice", 30));
        
        // Create threads that update the reference
        Thread thread1 = new Thread(() -> {
            Person oldPerson = atomicRef.get();
            Person newPerson = new Person(oldPerson.getName(), oldPerson.getAge() + 1);
            
            boolean success = atomicRef.compareAndSet(oldPerson, newPerson);
            System.out.println("Thread 1: CAS success = " + success + ", new value = " + atomicRef.get());
        });
        
        Thread thread2 = new Thread(() -> {
            Person oldPerson = atomicRef.get();
            Person newPerson = new Person(oldPerson.getName() + " Smith", oldPerson.getAge());
            
            boolean success = atomicRef.compareAndSet(oldPerson, newPerson);
            System.out.println("Thread 2: CAS success = " + success + ", new value = " + atomicRef.get());
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
        
        // Demonstrate AtomicReference operations
        System.out.println("\nAtomicReference operations:");
        
        // get and set
        Person bob = new Person("Bob", 25);
        atomicRef.set(bob);
        System.out.println("After set(bob): " + atomicRef.get());
        
        // getAndSet
        Person oldPerson = atomicRef.getAndSet(new Person("Charlie", 35));
        System.out.println("getAndSet: oldValue = " + oldPerson + ", newValue = " + atomicRef.get());
        
        // compareAndSet
        Person charlie = atomicRef.get();
        boolean success = atomicRef.compareAndSet(charlie, new Person("David", 40));
        System.out.println("compareAndSet: success = " + success + ", newValue = " + atomicRef.get());
        
        // updateAndGet (Java 8+)
        Person updatedPerson = atomicRef.updateAndGet(person -> new Person(person.getName(), person.getAge() + 5));
        System.out.println("updateAndGet: newValue = " + updatedPerson);
        
        // getAndUpdate (Java 8+)
        Person oldUpdatedPerson = atomicRef.getAndUpdate(person -> new Person(person.getName() + " Jr.", person.getAge()));
        System.out.println("getAndUpdate: oldValue = " + oldUpdatedPerson + ", newValue = " + atomicRef.get());
    }
    
    /**
     * Demonstrates the use of AtomicIntegerArray.
     */
    private static void atomicIntegerArrayExample() {
        // Create an AtomicIntegerArray
        AtomicIntegerArray atomicArray = new AtomicIntegerArray(5);
        
        // Initialize the array
        for (int i = 0; i < atomicArray.length(); i++) {
            atomicArray.set(i, i * 10);
        }
        
        // Print the initial array
        System.out.println("Initial array:");
        for (int i = 0; i < atomicArray.length(); i++) {
            System.out.println("atomicArray[" + i + "] = " + atomicArray.get(i));
        }
        
        // Create threads that update the array
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < atomicArray.length(); i++) {
                int oldValue = atomicArray.getAndIncrement(i);
                System.out.println("Thread 1: atomicArray[" + i + "] incremented from " + oldValue + " to " + atomicArray.get(i));
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < atomicArray.length(); i++) {
                int newValue = atomicArray.incrementAndGet(i);
                System.out.println("Thread 2: atomicArray[" + i + "] incremented to " + newValue);
            }
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
        
        // Print the final array
        System.out.println("\nFinal array:");
        for (int i = 0; i < atomicArray.length(); i++) {
            System.out.println("atomicArray[" + i + "] = " + atomicArray.get(i));
        }
        
        // Demonstrate AtomicIntegerArray operations
        System.out.println("\nAtomicIntegerArray operations:");
        
        // getAndSet
        int oldValue = atomicArray.getAndSet(0, 100);
        System.out.println("getAndSet(0, 100): oldValue = " + oldValue + ", newValue = " + atomicArray.get(0));
        
        // compareAndSet
        boolean success = atomicArray.compareAndSet(1, atomicArray.get(1), 200);
        System.out.println("compareAndSet(1, " + atomicArray.get(1) + ", 200): success = " + success + ", newValue = " + atomicArray.get(1));
        
        // getAndAdd
        oldValue = atomicArray.getAndAdd(2, 30);
        System.out.println("getAndAdd(2, 30): oldValue = " + oldValue + ", newValue = " + atomicArray.get(2));
        
        // addAndGet
        int newValue = atomicArray.addAndGet(3, 40);
        System.out.println("addAndGet(3, 40): newValue = " + newValue);
        
        // getAndUpdate (Java 8+)
        oldValue = atomicArray.getAndUpdate(4, x -> x * 2);
        System.out.println("getAndUpdate(4, x -> x * 2): oldValue = " + oldValue + ", newValue = " + atomicArray.get(4));
    }
    
    /**
     * Demonstrates the use of LongAdder (Java 8+).
     */
    private static void longAdderExample() {
        // Create a LongAdder
        LongAdder adder = new LongAdder();
        
        // Create threads that increment the adder
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    adder.increment();
                }
            });
        }
        
        // Start the threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // The final value should be 10000
        System.out.println("Final value: " + adder.sum());
        
        // Compare LongAdder with AtomicLong
        System.out.println("\nComparing LongAdder with AtomicLong:");
        
        // Create a LongAdder and an AtomicLong
        LongAdder longAdder = new LongAdder();
        AtomicLong atomicLong = new AtomicLong(0);
        
        // Measure time for LongAdder
        long startTime = System.nanoTime();
        
        Thread[] adderThreads = new Thread[10];
        for (int i = 0; i < adderThreads.length; i++) {
            adderThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    longAdder.increment();
                }
            });
        }
        
        for (Thread thread : adderThreads) {
            thread.start();
        }
        
        for (Thread thread : adderThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        long adderTime = System.nanoTime() - startTime;
        System.out.println("LongAdder time: " + adderTime + " ns, value: " + longAdder.sum());
        
        // Measure time for AtomicLong
        startTime = System.nanoTime();
        
        Thread[] atomicThreads = new Thread[10];
        for (int i = 0; i < atomicThreads.length; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    atomicLong.incrementAndGet();
                }
            });
        }
        
        for (Thread thread : atomicThreads) {
            thread.start();
        }
        
        for (Thread thread : atomicThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        long atomicTime = System.nanoTime() - startTime;
        System.out.println("AtomicLong time: " + atomicTime + " ns, value: " + atomicLong.get());
        
        System.out.println("LongAdder is " + (atomicTime / (double) adderTime) + "x faster than AtomicLong");
        
        // Demonstrate LongAdder operations
        System.out.println("\nLongAdder operations:");
        
        LongAdder adder2 = new LongAdder();
        
        // increment
        adder2.increment();
        System.out.println("After increment(): " + adder2.sum());
        
        // add
        adder2.add(10);
        System.out.println("After add(10): " + adder2.sum());
        
        // decrement
        adder2.decrement();
        System.out.println("After decrement(): " + adder2.sum());
        
        // reset
        adder2.reset();
        System.out.println("After reset(): " + adder2.sum());
        
        // sumThenReset
        adder2.add(5);
        long sum = adder2.sumThenReset();
        System.out.println("sumThenReset(): sum = " + sum + ", new sum = " + adder2.sum());
    }
    
    /**
     * Demonstrates the use of compare-and-set operations.
     */
    private static void compareAndSetExample() {
        // Create an AtomicInteger
        AtomicInteger atomicInt = new AtomicInteger(0);
        
        // Implement a simple spin lock using compareAndSet
        class SpinLock {
            private final AtomicBoolean locked = new AtomicBoolean(false);
            
            public void lock() {
                // Spin until the lock is acquired
                while (!locked.compareAndSet(false, true)) {
                    // Busy-wait (not recommended in practice)
                    Thread.yield();
                }
            }
            
            public void unlock() {
                locked.set(false);
            }
        }
        
        // Create a spin lock
        SpinLock lock = new SpinLock();
        
        // Create threads that use the lock
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 1: Acquired lock");
                
                // Critical section
                for (int i = 0; i < 5; i++) {
                    int value = atomicInt.getAndIncrement();
                    System.out.println("Thread 1: Incremented from " + value + " to " + atomicInt.get());
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                lock.unlock();
                System.out.println("Thread 1: Released lock");
            }
        });
        
        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 2: Acquired lock");
                
                // Critical section
                for (int i = 0; i < 5; i++) {
                    int value = atomicInt.getAndIncrement();
                    System.out.println("Thread 2: Incremented from " + value + " to " + atomicInt.get());
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                lock.unlock();
                System.out.println("Thread 2: Released lock");
            }
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
        
        System.out.println("Final value: " + atomicInt.get());
        
        // Implement a non-blocking counter using compareAndSet
        System.out.println("\nNon-blocking counter example:");
        
        class NonBlockingCounter {
            private final AtomicInteger value = new AtomicInteger(0);
            
            public int increment() {
                int current;
                int next;
                do {
                    current = value.get();
                    next = current + 1;
                } while (!value.compareAndSet(current, next));
                return next;
            }
            
            public int get() {
                return value.get();
            }
        }
        
        // Create a non-blocking counter
        NonBlockingCounter counter = new NonBlockingCounter();
        
        // Create threads that increment the counter
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
        }
        
        // Start the threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // The final value should be 5000
        System.out.println("Final counter value: " + counter.get());
    }
}
