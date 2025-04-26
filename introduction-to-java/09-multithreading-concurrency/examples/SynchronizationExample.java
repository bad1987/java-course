/**
 * SynchronizationExample.java
 * This program demonstrates thread synchronization techniques in Java.
 */
public class SynchronizationExample {
    public static void main(String[] args) {
        System.out.println("--- Thread Synchronization Examples ---");
        
        // Example 1: Race condition without synchronization
        System.out.println("\nExample 1: Race condition without synchronization");
        raceConditionExample();
        
        // Example 2: Synchronized methods
        System.out.println("\nExample 2: Synchronized methods");
        synchronizedMethodsExample();
        
        // Example 3: Synchronized blocks
        System.out.println("\nExample 3: Synchronized blocks");
        synchronizedBlocksExample();
        
        // Example 4: Static synchronization
        System.out.println("\nExample 4: Static synchronization");
        staticSynchronizationExample();
        
        // Example 5: The volatile keyword
        System.out.println("\nExample 5: The volatile keyword");
        volatileExample();
        
        // Example 6: Thread-safe singleton pattern
        System.out.println("\nExample 6: Thread-safe singleton pattern");
        threadSafeSingletonExample();
        
        // Example 7: Deadlock
        System.out.println("\nExample 7: Deadlock");
        deadlockExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates a race condition without synchronization.
     */
    private static void raceConditionExample() {
        // Create a counter without synchronization
        class UnsafeCounter {
            private int count = 0;
            
            public void increment() {
                count++;  // Not atomic: read, increment, write
            }
            
            public int getCount() {
                return count;
            }
        }
        
        // Create a shared counter
        UnsafeCounter counter = new UnsafeCounter();
        
        // Create threads that increment the counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
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
        
        // The expected count is 20000, but due to the race condition,
        // the actual count may be less
        System.out.println("Expected count: 20000");
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Race condition occurred: " + (counter.getCount() != 20000));
    }
    
    /**
     * Demonstrates synchronized methods.
     */
    private static void synchronizedMethodsExample() {
        // Create a counter with synchronized methods
        class SafeCounter {
            private int count = 0;
            
            // Synchronized method - only one thread can execute this at a time
            public synchronized void increment() {
                count++;
            }
            
            public synchronized int getCount() {
                return count;
            }
        }
        
        // Create a shared counter
        SafeCounter counter = new SafeCounter();
        
        // Create threads that increment the counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
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
        
        // With synchronized methods, the count should be correct
        System.out.println("Expected count: 20000");
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Race condition prevented: " + (counter.getCount() == 20000));
    }
    
    /**
     * Demonstrates synchronized blocks.
     */
    private static void synchronizedBlocksExample() {
        // Create a counter with synchronized blocks
        class BlockCounter {
            private int count = 0;
            private final Object lock = new Object();  // Lock object
            
            public void increment() {
                // Synchronize on the lock object
                synchronized (lock) {
                    count++;
                }
            }
            
            public int getCount() {
                synchronized (lock) {
                    return count;
                }
            }
        }
        
        // Create a shared counter
        BlockCounter counter = new BlockCounter();
        
        // Create threads that increment the counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
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
        
        // With synchronized blocks, the count should be correct
        System.out.println("Expected count: 20000");
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Race condition prevented: " + (counter.getCount() == 20000));
        
        // Example of fine-grained synchronization
        class BankAccount {
            private double balance;
            private final Object balanceLock = new Object();
            private String owner;
            private final Object ownerLock = new Object();
            
            public BankAccount(String owner, double initialBalance) {
                this.owner = owner;
                this.balance = initialBalance;
            }
            
            public void deposit(double amount) {
                // Only synchronize on the balance lock
                synchronized (balanceLock) {
                    balance += amount;
                }
            }
            
            public boolean withdraw(double amount) {
                synchronized (balanceLock) {
                    if (balance >= amount) {
                        balance -= amount;
                        return true;
                    }
                    return false;
                }
            }
            
            public double getBalance() {
                synchronized (balanceLock) {
                    return balance;
                }
            }
            
            public String getOwner() {
                synchronized (ownerLock) {
                    return owner;
                }
            }
            
            public void setOwner(String owner) {
                synchronized (ownerLock) {
                    this.owner = owner;
                }
            }
        }
        
        // Create a bank account
        BankAccount account = new BankAccount("Alice", 1000.0);
        
        // Create threads that deposit and withdraw
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(100.0);
                System.out.println("Deposited 100.0, new balance: " + account.getBalance());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Deposit thread interrupted.");
                }
            }
        });
        
        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                boolean success = account.withdraw(150.0);
                System.out.println("Withdraw 150.0 " + (success ? "succeeded" : "failed") + 
                                  ", new balance: " + account.getBalance());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Withdraw thread interrupted.");
                }
            }
        });
        
        // Start the threads
        depositThread.start();
        withdrawThread.start();
        
        // Wait for the threads to finish
        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Final balance: " + account.getBalance());
    }
    
    /**
     * Demonstrates static synchronization.
     */
    private static void staticSynchronizationExample() {
        // Class with static synchronized methods
        class StaticCounter {
            private static int count = 0;
            
            // Synchronized on StaticCounter.class
            public static synchronized void increment() {
                count++;
            }
            
            public static synchronized int getCount() {
                return count;
            }
            
            // Reset the counter for demonstration purposes
            public static synchronized void reset() {
                count = 0;
            }
        }
        
        // Reset the counter
        StaticCounter.reset();
        
        // Create threads that increment the static counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                StaticCounter.increment();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                StaticCounter.increment();
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
        
        // With static synchronized methods, the count should be correct
        System.out.println("Expected count: 20000");
        System.out.println("Actual count: " + StaticCounter.getCount());
        System.out.println("Race condition prevented: " + (StaticCounter.getCount() == 20000));
        
        // Example of synchronizing on the class object
        class AnotherCounter {
            private static int count = 0;
            
            public static void increment() {
                // Synchronize on the class object
                synchronized (AnotherCounter.class) {
                    count++;
                }
            }
            
            public static int getCount() {
                synchronized (AnotherCounter.class) {
                    return count;
                }
            }
            
            // Reset the counter for demonstration purposes
            public static void reset() {
                synchronized (AnotherCounter.class) {
                    count = 0;
                }
            }
        }
        
        // Reset the counter
        AnotherCounter.reset();
        
        // Create threads that increment the static counter
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                AnotherCounter.increment();
            }
        });
        
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                AnotherCounter.increment();
            }
        });
        
        // Start the threads
        thread3.start();
        thread4.start();
        
        // Wait for the threads to finish
        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // With synchronized blocks on the class object, the count should be correct
        System.out.println("Expected count: 20000");
        System.out.println("Actual count: " + AnotherCounter.getCount());
        System.out.println("Race condition prevented: " + (AnotherCounter.getCount() == 20000));
    }
    
    /**
     * Demonstrates the volatile keyword.
     */
    private static void volatileExample() {
        // Class with a volatile flag
        class VolatileFlag {
            // The volatile keyword ensures that the flag is always read from
            // and written to main memory, not from thread-local caches
            private volatile boolean flag = false;
            
            public void setFlag(boolean value) {
                flag = value;
            }
            
            public boolean isFlag() {
                return flag;
            }
        }
        
        // Create a shared flag
        VolatileFlag flag = new VolatileFlag();
        
        // Create a thread that waits for the flag to be set
        Thread waiter = new Thread(() -> {
            System.out.println("Waiter thread: Waiting for flag to be set...");
            
            // Without volatile, this might never see the updated flag value
            // due to caching, and the loop would never exit
            while (!flag.isFlag()) {
                // Busy-wait (not recommended in practice)
            }
            
            System.out.println("Waiter thread: Flag was set, exiting.");
        });
        
        // Start the waiter thread
        waiter.start();
        
        // Sleep briefly to let the waiter thread start
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Set the flag
        System.out.println("Main thread: Setting the flag...");
        flag.setFlag(true);
        
        // Wait for the waiter thread to finish
        try {
            waiter.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Volatile example completed.");
        
        // Example of a non-atomic operation on a volatile variable
        class VolatileCounter {
            private volatile int count = 0;
            
            public void increment() {
                count++;  // Not atomic, even though count is volatile
            }
            
            public int getCount() {
                return count;
            }
        }
        
        // Create a shared counter
        VolatileCounter counter = new VolatileCounter();
        
        // Create threads that increment the counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
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
        
        // Even with volatile, the count may be incorrect because increment is not atomic
        System.out.println("Expected count: 20000");
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Race condition may still occur with volatile: " + 
                          (counter.getCount() != 20000));
    }
    
    /**
     * Demonstrates thread-safe singleton pattern.
     */
    private static void threadSafeSingletonExample() {
        // Double-checked locking singleton
        class Singleton {
            // The volatile keyword is necessary to make double-checked locking work correctly
            private static volatile Singleton instance;
            
            private Singleton() {
                System.out.println("Singleton instance created.");
            }
            
            public static Singleton getInstance() {
                // First check (not synchronized)
                if (instance == null) {
                    // Synchronize only if instance is null
                    synchronized (Singleton.class) {
                        // Second check (synchronized)
                        if (instance == null) {
                            instance = new Singleton();
                        }
                    }
                }
                return instance;
            }
            
            public void doSomething() {
                System.out.println("Singleton is doing something.");
            }
        }
        
        // Create threads that get the singleton instance
        Thread thread1 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            singleton.doSomething();
        });
        
        Thread thread2 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            singleton.doSomething();
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
        
        // Get the singleton instance in the main thread
        Singleton singleton = Singleton.getInstance();
        singleton.doSomething();
        
        System.out.println("Thread-safe singleton example completed.");
        
        // Enum singleton (thread-safe by design)
        enum EnumSingleton {
            INSTANCE;
            
            EnumSingleton() {
                System.out.println("EnumSingleton instance created.");
            }
            
            public void doSomething() {
                System.out.println("EnumSingleton is doing something.");
            }
        }
        
        // Use the enum singleton
        EnumSingleton.INSTANCE.doSomething();
    }
    
    /**
     * Demonstrates a deadlock situation.
     */
    private static void deadlockExample() {
        // Create two lock objects
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        
        // Create a thread that acquires lock1, then lock2
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1: Attempting to acquire lock1...");
            synchronized (lock1) {
                System.out.println("Thread 1: Acquired lock1");
                
                // Sleep to give thread2 a chance to acquire lock2
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 interrupted.");
                }
                
                System.out.println("Thread 1: Attempting to acquire lock2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Acquired lock2");
                    
                    // Do something with both locks
                    System.out.println("Thread 1: Has both locks");
                }
            }
        });
        
        // Create a thread that acquires lock2, then lock1
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Attempting to acquire lock2...");
            synchronized (lock2) {
                System.out.println("Thread 2: Acquired lock2");
                
                // Sleep to give thread1 a chance to acquire lock1
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread 2 interrupted.");
                }
                
                System.out.println("Thread 2: Attempting to acquire lock1...");
                synchronized (lock1) {
                    System.out.println("Thread 2: Acquired lock1");
                    
                    // Do something with both locks
                    System.out.println("Thread 2: Has both locks");
                }
            }
        });
        
        // Start the threads
        thread1.start();
        thread2.start();
        
        // Wait for a while to let the deadlock occur
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Check if the threads are still alive (deadlocked)
        boolean deadlocked = thread1.isAlive() && thread2.isAlive();
        System.out.println("Deadlock detected: " + deadlocked);
        
        if (deadlocked) {
            System.out.println("Interrupting the deadlocked threads...");
            thread1.interrupt();
            thread2.interrupt();
        }
        
        // Wait for the threads to finish
        try {
            thread1.join(1000);
            thread2.join(1000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Deadlock example completed.");
        System.out.println("Note: In a real application, you should avoid deadlocks by:");
        System.out.println("1. Acquiring locks in a fixed, global order");
        System.out.println("2. Using tryLock() with timeout");
        System.out.println("3. Avoiding calling unknown methods while holding locks");
    }
}
