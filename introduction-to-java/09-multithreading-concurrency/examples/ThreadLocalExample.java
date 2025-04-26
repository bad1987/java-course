/**
 * ThreadLocalExample.java
 * This program demonstrates the use of ThreadLocal variables in Java.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class ThreadLocalExample {
    public static void main(String[] args) {
        System.out.println("--- ThreadLocal Examples ---");
        
        // Example 1: Basic ThreadLocal usage
        System.out.println("\nExample 1: Basic ThreadLocal usage");
        basicThreadLocalExample();
        
        // Example 2: ThreadLocal with initialValue
        System.out.println("\nExample 2: ThreadLocal with initialValue");
        threadLocalWithInitialValueExample();
        
        // Example 3: InheritableThreadLocal
        System.out.println("\nExample 3: InheritableThreadLocal");
        inheritableThreadLocalExample();
        
        // Example 4: ThreadLocal for SimpleDateFormat
        System.out.println("\nExample 4: ThreadLocal for SimpleDateFormat");
        dateFormatThreadLocalExample();
        
        // Example 5: ThreadLocalRandom
        System.out.println("\nExample 5: ThreadLocalRandom");
        threadLocalRandomExample();
        
        // Example 6: Memory leaks with ThreadLocal
        System.out.println("\nExample 6: Memory leaks with ThreadLocal");
        memoryLeakExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates basic usage of ThreadLocal.
     */
    private static void basicThreadLocalExample() {
        // Create a ThreadLocal variable
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        
        // Create threads that use the ThreadLocal
        Thread thread1 = new Thread(() -> {
            // Set a value in the ThreadLocal
            threadLocal.set("Thread 1's value");
            
            // Get the value from the ThreadLocal
            String value = threadLocal.get();
            System.out.println("Thread 1: " + value);
            
            // Remove the value
            threadLocal.remove();
            
            // Get the value again (should be null)
            value = threadLocal.get();
            System.out.println("Thread 1 after remove: " + value);
        });
        
        Thread thread2 = new Thread(() -> {
            // Set a different value in the ThreadLocal
            threadLocal.set("Thread 2's value");
            
            // Get the value from the ThreadLocal
            String value = threadLocal.get();
            System.out.println("Thread 2: " + value);
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
        
        // Get the value in the main thread (should be null)
        String value = threadLocal.get();
        System.out.println("Main thread: " + value);
        
        // Set a value in the main thread
        threadLocal.set("Main thread's value");
        value = threadLocal.get();
        System.out.println("Main thread after set: " + value);
    }
    
    /**
     * Demonstrates ThreadLocal with initialValue.
     */
    private static void threadLocalWithInitialValueExample() {
        // Create a ThreadLocal with an initial value
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
        
        // Create threads that use the ThreadLocal
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                // Get the initial value
                Integer value = threadLocal.get();
                System.out.println("Thread " + threadId + " initial value: " + value);
                
                // Increment the value
                threadLocal.set(value + 1);
                value = threadLocal.get();
                System.out.println("Thread " + threadId + " after increment: " + value);
                
                // Increment again
                threadLocal.set(value + 1);
                value = threadLocal.get();
                System.out.println("Thread " + threadId + " after second increment: " + value);
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
        
        // Get the value in the main thread (should be the initial value)
        Integer value = threadLocal.get();
        System.out.println("Main thread: " + value);
        
        // Create a ThreadLocal with a more complex initial value
        ThreadLocal<StringBuilder> builderThreadLocal = ThreadLocal.withInitial(() -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Initial value for thread ");
            sb.append(Thread.currentThread().getName());
            return sb;
        });
        
        // Create threads that use the StringBuilder ThreadLocal
        Thread[] builderThreads = new Thread[3];
        for (int i = 0; i < builderThreads.length; i++) {
            final int threadId = i;
            builderThreads[i] = new Thread(() -> {
                // Get the initial value
                StringBuilder builder = builderThreadLocal.get();
                System.out.println("Thread " + threadId + " initial builder: " + builder);
                
                // Append to the builder
                builder.append(" - Appended text");
                System.out.println("Thread " + threadId + " after append: " + builder);
            });
            
            // Set the thread name
            builderThreads[i].setName("Builder-" + i);
        }
        
        // Start the threads
        for (Thread thread : builderThreads) {
            thread.start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread thread : builderThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Get the value in the main thread
        StringBuilder builder = builderThreadLocal.get();
        System.out.println("Main thread builder: " + builder);
    }
    
    /**
     * Demonstrates InheritableThreadLocal.
     */
    private static void inheritableThreadLocalExample() {
        // Create an InheritableThreadLocal
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        
        // Set a value in the main thread
        inheritableThreadLocal.set("Value set in main thread");
        
        // Create a thread that inherits the value
        Thread parentThread = new Thread(() -> {
            // Get the inherited value
            String value = inheritableThreadLocal.get();
            System.out.println("Parent thread inherited value: " + value);
            
            // Create a child thread
            Thread childThread = new Thread(() -> {
                // Get the inherited value
                String childValue = inheritableThreadLocal.get();
                System.out.println("Child thread inherited value: " + childValue);
                
                // Change the value
                inheritableThreadLocal.set("Value changed in child thread");
                childValue = inheritableThreadLocal.get();
                System.out.println("Child thread after change: " + childValue);
            });
            
            // Start the child thread
            childThread.start();
            
            // Wait for the child thread to finish
            try {
                childThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Check if the parent thread's value was affected
            value = inheritableThreadLocal.get();
            System.out.println("Parent thread after child change: " + value);
            
            // Change the value in the parent thread
            inheritableThreadLocal.set("Value changed in parent thread");
            value = inheritableThreadLocal.get();
            System.out.println("Parent thread after change: " + value);
        });
        
        // Start the parent thread
        parentThread.start();
        
        // Wait for the parent thread to finish
        try {
            parentThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Check if the main thread's value was affected
        String value = inheritableThreadLocal.get();
        System.out.println("Main thread after parent change: " + value);
        
        // Create a custom InheritableThreadLocal that modifies the inherited value
        InheritableThreadLocal<StringBuilder> customInheritableThreadLocal = new InheritableThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder childValue(StringBuilder parentValue) {
                // Create a new StringBuilder with modified content
                return new StringBuilder(parentValue).append(" (inherited by child)");
            }
        };
        
        // Set a value in the main thread
        customInheritableThreadLocal.set(new StringBuilder("Custom value set in main thread"));
        
        // Create a thread that inherits the modified value
        Thread customParentThread = new Thread(() -> {
            // Get the inherited value
            StringBuilder parentValue = customInheritableThreadLocal.get();
            System.out.println("Custom parent thread inherited value: " + parentValue);
            
            // Create a child thread
            Thread customChildThread = new Thread(() -> {
                // Get the inherited value (modified by childValue)
                StringBuilder childValue = customInheritableThreadLocal.get();
                System.out.println("Custom child thread inherited value: " + childValue);
            });
            
            // Start the child thread
            customChildThread.start();
            
            // Wait for the child thread to finish
            try {
                customChildThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Start the custom parent thread
        customParentThread.start();
        
        // Wait for the custom parent thread to finish
        try {
            customParentThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
    
    /**
     * Demonstrates using ThreadLocal for SimpleDateFormat.
     */
    private static void dateFormatThreadLocalExample() {
        // Create a ThreadLocal for SimpleDateFormat
        ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(
                () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        
        // Create a runnable that uses the date format
        Runnable dateFormatTask = () -> {
            // Get the thread-local date format
            SimpleDateFormat dateFormat = dateFormatThreadLocal.get();
            
            // Format the current date
            String formattedDate = dateFormat.format(new Date());
            System.out.println(Thread.currentThread().getName() + ": " + formattedDate);
            
            // Simulate some work
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Format another date
            formattedDate = dateFormat.format(new Date());
            System.out.println(Thread.currentThread().getName() + ": " + formattedDate);
        };
        
        // Create and start multiple threads
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(dateFormatTask);
            threads[i].setName("DateFormat-" + i);
            threads[i].start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate the problem with sharing SimpleDateFormat across threads
        System.out.println("\nProblem with shared SimpleDateFormat:");
        
        // Create a shared SimpleDateFormat
        SimpleDateFormat sharedDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Create a runnable that uses the shared date format
        Runnable sharedDateFormatTask = () -> {
            // Format the current date using the shared format
            String formattedDate = sharedDateFormat.format(new Date());
            System.out.println(Thread.currentThread().getName() + ": " + formattedDate);
            
            // Simulate some work
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Format another date
            formattedDate = sharedDateFormat.format(new Date());
            System.out.println(Thread.currentThread().getName() + ": " + formattedDate);
        };
        
        // Create and start multiple threads
        Thread[] sharedThreads = new Thread[5];
        for (int i = 0; i < sharedThreads.length; i++) {
            sharedThreads[i] = new Thread(sharedDateFormatTask);
            sharedThreads[i].setName("SharedFormat-" + i);
            sharedThreads[i].start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread thread : sharedThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Note: The shared SimpleDateFormat might produce incorrect results due to concurrent access.");
    }
    
    /**
     * Demonstrates ThreadLocalRandom.
     */
    private static void threadLocalRandomExample() {
        // Create a runnable that uses ThreadLocalRandom
        Runnable randomTask = () -> {
            // Get the thread-local random
            ThreadLocalRandom random = ThreadLocalRandom.current();
            
            // Generate random values
            int randomInt = random.nextInt(100);
            double randomDouble = random.nextDouble();
            long randomLong = random.nextLong(1000);
            
            System.out.println(Thread.currentThread().getName() + ": " +
                              "int=" + randomInt + ", double=" + randomDouble + ", long=" + randomLong);
            
            // Generate random values in a range
            int rangeInt = random.nextInt(10, 20);
            double rangeDouble = random.nextDouble(0.5, 1.5);
            long rangeLong = random.nextLong(100, 200);
            
            System.out.println(Thread.currentThread().getName() + " (range): " +
                              "int=" + rangeInt + ", double=" + rangeDouble + ", long=" + rangeLong);
        };
        
        // Create and start multiple threads
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(randomTask);
            threads[i].setName("Random-" + i);
            threads[i].start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Compare with java.util.Random
        System.out.println("\nComparing with java.util.Random:");
        
        // Create a shared Random
        java.util.Random sharedRandom = new java.util.Random();
        
        // Create a runnable that uses the shared Random
        Runnable sharedRandomTask = () -> {
            // Generate random values
            int randomInt = sharedRandom.nextInt(100);
            double randomDouble = sharedRandom.nextDouble();
            long randomLong = sharedRandom.nextLong() % 1000;
            
            System.out.println(Thread.currentThread().getName() + ": " +
                              "int=" + randomInt + ", double=" + randomDouble + ", long=" + randomLong);
        };
        
        // Create and start multiple threads
        Thread[] sharedThreads = new Thread[5];
        for (int i = 0; i < sharedThreads.length; i++) {
            sharedThreads[i] = new Thread(sharedRandomTask);
            sharedThreads[i].setName("SharedRandom-" + i);
            sharedThreads[i].start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread thread : sharedThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Note: The shared Random might have contention issues due to concurrent access.");
    }
    
    /**
     * Demonstrates potential memory leaks with ThreadLocal.
     */
    private static void memoryLeakExample() {
        // Create a ThreadLocal that holds a large object
        ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
        
        // Create a thread that sets a value but doesn't remove it
        Thread thread = new Thread(() -> {
            // Set a large array in the ThreadLocal
            threadLocal.set(new byte[1024 * 1024]);  // 1MB
            
            System.out.println("Thread set a large array in ThreadLocal");
            
            // The thread exits without calling threadLocal.remove()
            // This can lead to a memory leak if the thread is reused in a thread pool
        });
        
        // Start the thread
        thread.start();
        
        // Wait for the thread to finish
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Thread has exited, but the ThreadLocal reference might still exist");
        System.out.println("This can cause a memory leak if the thread is reused in a thread pool");
        
        // Best practice: Always remove ThreadLocal values when done
        System.out.println("\nBest practice example:");
        
        // Create a ThreadLocal
        ThreadLocal<String> bestPracticeThreadLocal = new ThreadLocal<>();
        
        // Create a thread that properly cleans up
        Thread bestPracticeThread = new Thread(() -> {
            try {
                // Set a value
                bestPracticeThreadLocal.set("Some value");
                
                // Use the value
                System.out.println("Best practice thread: " + bestPracticeThreadLocal.get());
                
                // Do some work
                // ...
            } finally {
                // Always remove the value when done
                bestPracticeThreadLocal.remove();
                System.out.println("Best practice thread: Removed ThreadLocal value");
            }
        });
        
        // Start the thread
        bestPracticeThread.start();
        
        // Wait for the thread to finish
        try {
            bestPracticeThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Using try-with-resources with a custom AutoCloseable
        System.out.println("\nUsing try-with-resources:");
        
        // Create a ThreadLocal wrapper that implements AutoCloseable
        class ThreadLocalResource<T> implements AutoCloseable {
            private final ThreadLocal<T> threadLocal;
            
            public ThreadLocalResource(Supplier<T> initialValueSupplier) {
                this.threadLocal = ThreadLocal.withInitial(initialValueSupplier);
            }
            
            public T get() {
                return threadLocal.get();
            }
            
            public void set(T value) {
                threadLocal.set(value);
            }
            
            @Override
            public void close() {
                threadLocal.remove();
            }
        }
        
        // Create a thread that uses try-with-resources
        Thread resourceThread = new Thread(() -> {
            // Use try-with-resources to ensure cleanup
            try (ThreadLocalResource<StringBuilder> resource = 
                    new ThreadLocalResource<>(() -> new StringBuilder("Initial value"))) {
                
                // Use the resource
                StringBuilder builder = resource.get();
                builder.append(" - Modified");
                System.out.println("Resource thread: " + builder);
                
                // Do some work
                // ...
            }
            
            System.out.println("Resource thread: ThreadLocal value automatically removed");
        });
        
        // Start the thread
        resourceThread.start();
        
        // Wait for the thread to finish
        try {
            resourceThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}
