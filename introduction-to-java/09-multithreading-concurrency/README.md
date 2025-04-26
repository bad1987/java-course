# Multithreading and Concurrency in Java

Welcome to the ninth lesson in our Java programming course! In this section, you'll learn about multithreading and concurrency in Java. These concepts are essential for developing applications that can perform multiple tasks simultaneously, improving performance and responsiveness.

## 1. Introduction to Threads

A thread is the smallest unit of execution within a process. Java supports multithreading, which allows multiple threads to run concurrently within a single program.

### Benefits of Multithreading

- **Improved Performance**: Utilizing multiple CPU cores for parallel execution
- **Responsiveness**: Keeping the UI responsive while performing background tasks
- **Resource Sharing**: Multiple threads can share resources within the same process
- **Simplified Program Structure**: Complex tasks can be broken down into simpler threads

### Creating Threads in Java

There are two primary ways to create threads in Java:

#### 1. Extending the Thread Class

```java
class MyThread extends Thread {
    @Override
    public void run() {
        // Code to be executed in this thread
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + i);
        }
    }
}

// Creating and starting the thread
MyThread thread = new MyThread();
thread.start();  // Calls the run() method in a new thread
```

#### 2. Implementing the Runnable Interface

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        // Code to be executed in this thread
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + i);
        }
    }
}

// Creating and starting the thread
Thread thread = new Thread(new MyRunnable());
thread.start();  // Calls the run() method in a new thread
```

#### 3. Using Lambda Expressions (Java 8+)

```java
// Creating a thread with a lambda expression
Thread thread = new Thread(() -> {
    // Code to be executed in this thread
    for (int i = 0; i < 5; i++) {
        System.out.println("Thread " + Thread.currentThread().getId() + ": " + i);
    }
});
thread.start();  // Calls the run() method in a new thread
```

### Thread Lifecycle

A thread in Java can be in one of the following states:

1. **New**: The thread has been created but not started yet
2. **Runnable**: The thread is executing or ready to execute
3. **Blocked**: The thread is blocked waiting for a monitor lock
4. **Waiting**: The thread is waiting indefinitely for another thread to perform a particular action
5. **Timed Waiting**: The thread is waiting for another thread for a specified period
6. **Terminated**: The thread has completed execution or been terminated

### Thread Methods

The `Thread` class provides several methods for controlling threads:

```java
// Creating a thread
Thread thread = new Thread(() -> {
    // Thread code
});

// Starting a thread
thread.start();

// Getting the current thread
Thread currentThread = Thread.currentThread();

// Setting thread name
thread.setName("MyThread");
String name = thread.getName();

// Setting thread priority (1-10, with 10 being highest)
thread.setPriority(Thread.MAX_PRIORITY);  // 10
thread.setPriority(Thread.NORM_PRIORITY); // 5
thread.setPriority(Thread.MIN_PRIORITY);  // 1

// Checking if a thread is alive
boolean isAlive = thread.isAlive();

// Joining threads (waiting for a thread to complete)
try {
    thread.join();  // Wait indefinitely
    thread.join(1000);  // Wait for 1 second
} catch (InterruptedException e) {
    // Handle interruption
}

// Sleeping a thread
try {
    Thread.sleep(1000);  // Sleep for 1 second
} catch (InterruptedException e) {
    // Handle interruption
}

// Interrupting a thread
thread.interrupt();
boolean wasInterrupted = Thread.interrupted();  // Clears the interrupted status
boolean isInterrupted = thread.isInterrupted(); // Doesn't clear the status

// Yielding to other threads
Thread.yield();
```

## 2. Thread Synchronization

When multiple threads access shared resources, synchronization is necessary to prevent race conditions and ensure data consistency.

### Race Conditions

A race condition occurs when multiple threads access and modify shared data simultaneously, leading to unpredictable results.

```java
// Example of a race condition
class Counter {
    private int count = 0;
    
    public void increment() {
        count++;  // Not atomic: read, increment, write
    }
    
    public int getCount() {
        return count;
    }
}

// Multiple threads incrementing the same counter can lead to incorrect results
```

### Synchronized Methods

The `synchronized` keyword can be used to make methods thread-safe:

```java
class Counter {
    private int count = 0;
    
    // Only one thread can execute this method at a time
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

### Synchronized Blocks

For more fine-grained control, you can synchronize specific blocks of code:

```java
class Counter {
    private int count = 0;
    private final Object lock = new Object();  // Lock object
    
    public void increment() {
        synchronized (lock) {  // Synchronize on the lock object
            count++;
        }
    }
    
    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}
```

### Static Synchronization

For static methods, synchronization is on the class object:

```java
class Counter {
    private static int count = 0;
    
    // Synchronized on Counter.class
    public static synchronized void increment() {
        count++;
    }
    
    public static synchronized int getCount() {
        return count;
    }
}
```

### The Volatile Keyword

The `volatile` keyword ensures that a variable is always read from and written to main memory, not from thread-local caches:

```java
class Flag {
    private volatile boolean flag = false;
    
    public void setFlag(boolean value) {
        flag = value;
    }
    
    public boolean isFlag() {
        return flag;
    }
}
```

## 3. Thread Communication

Threads often need to communicate and coordinate their actions.

### wait(), notify(), and notifyAll()

These methods, inherited from the `Object` class, are used for thread communication:

```java
class MessageQueue {
    private String message;
    private boolean empty = true;
    
    public synchronized String receive() {
        while (empty) {
            try {
                wait();  // Wait until notified
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        empty = true;
        notifyAll();  // Notify waiting threads
        return message;
    }
    
    public synchronized void send(String message) {
        while (!empty) {
            try {
                wait();  // Wait until notified
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        empty = false;
        this.message = message;
        notifyAll();  // Notify waiting threads
    }
}
```

### Thread Interruption

Threads can be interrupted to signal that they should stop what they're doing:

```java
Thread thread = new Thread(() -> {
    try {
        while (!Thread.currentThread().isInterrupted()) {
            // Do some work
            Thread.sleep(100);  // This can throw InterruptedException
        }
    } catch (InterruptedException e) {
        // Thread was interrupted during sleep
        // Clean up and exit
    }
});

thread.start();

// Later, interrupt the thread
thread.interrupt();
```

## 4. Thread Pools and Executors

The `java.util.concurrent` package provides higher-level concurrency utilities, including thread pools and executors.

### Executor Framework

The Executor framework separates task submission from task execution:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Creating a fixed thread pool with 5 threads
ExecutorService executor = Executors.newFixedThreadPool(5);

// Submitting tasks to the executor
executor.submit(() -> {
    System.out.println("Task executed by " + Thread.currentThread().getName());
});

// Shutting down the executor
executor.shutdown();
```

### Types of Executors

Java provides several types of executors:

```java
// Fixed thread pool
ExecutorService fixedPool = Executors.newFixedThreadPool(5);

// Cached thread pool (creates new threads as needed, reuses idle threads)
ExecutorService cachedPool = Executors.newCachedThreadPool();

// Single thread executor
ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

// Scheduled executor (for delayed or periodic tasks)
ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
```

### Callable and Future

The `Callable` interface is similar to `Runnable` but can return a result and throw exceptions:

```java
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

// Creating a Callable task
Callable<Integer> task = () -> {
    // Perform computation
    return 42;  // Return result
};

// Submitting the task and getting a Future
Future<Integer> future = executor.submit(task);

try {
    // Get the result (blocks until the task completes)
    Integer result = future.get();
    System.out.println("Result: " + result);
    
    // Get with timeout
    result = future.get(1, TimeUnit.SECONDS);
} catch (InterruptedException | ExecutionException | TimeoutException e) {
    // Handle exceptions
}

// Check if the task is done
boolean isDone = future.isDone();

// Cancel the task
boolean wasCancelled = future.cancel(true);  // true to interrupt if running
```

### CompletableFuture (Java 8+)

`CompletableFuture` provides a more flexible way to work with asynchronous computations:

```java
import java.util.concurrent.CompletableFuture;

// Creating a CompletableFuture
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    // Perform computation
    return "Result";
});

// Chaining operations
future = future
    .thenApply(result -> result + " processed")  // Transform the result
    .thenCompose(result -> CompletableFuture.supplyAsync(() -> result + " again"))  // Chain another async operation
    .thenCombine(CompletableFuture.supplyAsync(() -> " combined"), (r1, r2) -> r1 + r2)  // Combine with another future
    .exceptionally(ex -> "Error: " + ex.getMessage());  // Handle exceptions

// Get the result
String result = future.get();
```

## 5. Concurrent Collections

The `java.util.concurrent` package provides thread-safe collections designed for concurrent access.

### ConcurrentHashMap

A thread-safe implementation of `Map`:

```java
import java.util.concurrent.ConcurrentHashMap;

// Creating a ConcurrentHashMap
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

// Adding elements
map.put("one", 1);
map.put("two", 2);

// Retrieving elements
int value = map.get("one");

// Atomic operations
map.putIfAbsent("three", 3);  // Add only if not present
map.replace("two", 2, 22);    // Replace if value matches
```

### CopyOnWriteArrayList

A thread-safe variant of `ArrayList` that creates a fresh copy on each modification:

```java
import java.util.concurrent.CopyOnWriteArrayList;

// Creating a CopyOnWriteArrayList
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

// Adding elements
list.add("one");
list.add("two");

// Iterating (safe even if the list is modified during iteration)
for (String item : list) {
    System.out.println(item);
}
```

### BlockingQueue

A queue that supports operations that wait for the queue to become non-empty or non-full:

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Creating a BlockingQueue
BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);  // Capacity of 10

// Producer
new Thread(() -> {
    try {
        for (int i = 0; i < 20; i++) {
            String item = "Item " + i;
            queue.put(item);  // Blocks if the queue is full
            System.out.println("Produced: " + item);
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}).start();

// Consumer
new Thread(() -> {
    try {
        while (true) {
            String item = queue.take();  // Blocks if the queue is empty
            System.out.println("Consumed: " + item);
            Thread.sleep(100);  // Simulate processing time
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}).start();
```

## 6. Atomic Variables

Atomic variables provide atomic operations on single variables without using locks:

```java
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

// AtomicInteger
AtomicInteger counter = new AtomicInteger(0);
int value = counter.incrementAndGet();  // Atomic increment and get
value = counter.getAndIncrement();      // Atomic get and increment
counter.set(10);                        // Set value
value = counter.get();                  // Get value
counter.compareAndSet(10, 20);          // Compare and set

// AtomicReference
AtomicReference<String> reference = new AtomicReference<>("initial");
reference.set("updated");
String current = reference.get();
reference.compareAndSet("updated", "new value");
```

## 7. Locks and Conditions

The `java.util.concurrent.locks` package provides more flexible locking mechanisms than synchronized blocks:

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class BoundedBuffer {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    private final Object[] items = new Object[100];
    private int putIndex = 0, takeIndex = 0, count = 0;
    
    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();  // Wait until not full
            }
            items[putIndex] = x;
            putIndex = (putIndex + 1) % items.length;
            count++;
            notEmpty.signal();  // Signal not empty
        } finally {
            lock.unlock();
        }
    }
    
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();  // Wait until not empty
            }
            Object x = items[takeIndex];
            takeIndex = (takeIndex + 1) % items.length;
            count--;
            notFull.signal();  // Signal not full
            return x;
        } finally {
            lock.unlock();
        }
    }
}
```

### ReadWriteLock

`ReadWriteLock` provides separate locks for reading and writing:

```java
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();
    
    private final Map<String, Object> map = new HashMap<>();
    
    public Object get(String key) {
        readLock.lock();  // Multiple readers can acquire the read lock
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
    
    public void put(String key, Object value) {
        writeLock.lock();  // Only one writer can acquire the write lock
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
```

## 8. Thread-Local Variables

`ThreadLocal` provides thread-local variables, which are variables that are local to each thread:

```java
// Creating a ThreadLocal
ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

// Setting a value (specific to the current thread)
threadLocal.set(42);

// Getting the value (specific to the current thread)
Integer value = threadLocal.get();

// Removing the value
threadLocal.remove();

// Using initialValue
ThreadLocal<Integer> threadLocalWithInitial = ThreadLocal.withInitial(() -> 0);
```

## 9. Synchronizers

The `java.util.concurrent` package provides several synchronization aids:

### CountDownLatch

A synchronization aid that allows one or more threads to wait until a set of operations in other threads completes:

```java
import java.util.concurrent.CountDownLatch;

// Creating a CountDownLatch with a count of 3
CountDownLatch latch = new CountDownLatch(3);

// Threads that will count down the latch
for (int i = 0; i < 3; i++) {
    int threadNumber = i;
    new Thread(() -> {
        try {
            Thread.sleep(1000);  // Simulate work
            System.out.println("Thread " + threadNumber + " completed");
            latch.countDown();  // Decrement the count
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }).start();
}

// Main thread waits for all threads to complete
try {
    latch.await();  // Wait until count reaches 0
    System.out.println("All threads completed");
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

### CyclicBarrier

A synchronization aid that allows a set of threads to wait for each other to reach a common barrier point:

```java
import java.util.concurrent.CyclicBarrier;

// Creating a CyclicBarrier for 3 threads
CyclicBarrier barrier = new CyclicBarrier(3, () -> {
    // This runs when all threads reach the barrier
    System.out.println("All threads reached the barrier");
});

// Threads that will use the barrier
for (int i = 0; i < 3; i++) {
    int threadNumber = i;
    new Thread(() -> {
        try {
            System.out.println("Thread " + threadNumber + " is working");
            Thread.sleep(1000);  // Simulate work
            System.out.println("Thread " + threadNumber + " is waiting at the barrier");
            barrier.await();  // Wait for all threads to reach this point
            System.out.println("Thread " + threadNumber + " continued after the barrier");
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }).start();
}
```

### Semaphore

A synchronization aid that restricts the number of threads that can access a resource:

```java
import java.util.concurrent.Semaphore;

// Creating a Semaphore with 3 permits
Semaphore semaphore = new Semaphore(3);

// Threads that will use the semaphore
for (int i = 0; i < 10; i++) {
    int threadNumber = i;
    new Thread(() -> {
        try {
            semaphore.acquire();  // Acquire a permit (blocks if none available)
            System.out.println("Thread " + threadNumber + " acquired a permit");
            Thread.sleep(1000);  // Simulate work
            System.out.println("Thread " + threadNumber + " releasing permit");
            semaphore.release();  // Release the permit
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }).start();
}
```

### Phaser

A more flexible synchronization barrier that can be used for phased computations:

```java
import java.util.concurrent.Phaser;

// Creating a Phaser for 3 parties
Phaser phaser = new Phaser(3);

// Threads that will use the phaser
for (int i = 0; i < 3; i++) {
    int threadNumber = i;
    new Thread(() -> {
        // Phase 1
        System.out.println("Thread " + threadNumber + " executing phase 1");
        phaser.arriveAndAwaitAdvance();  // Wait for all threads to complete phase 1
        
        // Phase 2
        System.out.println("Thread " + threadNumber + " executing phase 2");
        phaser.arriveAndAwaitAdvance();  // Wait for all threads to complete phase 2
        
        // Phase 3
        System.out.println("Thread " + threadNumber + " executing phase 3");
        phaser.arriveAndDeregister();  // Complete and deregister from the phaser
    }).start();
}
```

## 10. Fork/Join Framework

The Fork/Join framework is designed for work that can be broken down into smaller tasks that are executed in parallel:

```java
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// A task that computes the sum of an array
class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 1000;
    private final long[] array;
    private final int start;
    private final int end;
    
    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            // Base case: compute directly
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Recursive case: fork and join
            int mid = start + length / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);
            
            leftTask.fork();  // Submit left task
            long rightResult = rightTask.compute();  // Compute right task
            long leftResult = leftTask.join();  // Wait for left task
            
            return leftResult + rightResult;
        }
    }
}

// Using the Fork/Join framework
ForkJoinPool pool = ForkJoinPool.commonPool();
long[] array = new long[10000];
// Initialize array...
SumTask task = new SumTask(array, 0, array.length);
long sum = pool.invoke(task);
```

## 11. Concurrent Programming Best Practices

### 1. Minimize Shared Mutable State

Shared mutable state is the root of most concurrency problems. Minimize it by:
- Using immutable objects
- Using thread-local variables
- Using message passing instead of shared memory

### 2. Prefer Higher-Level Concurrency Utilities

- Use `ExecutorService` instead of creating threads directly
- Use concurrent collections instead of synchronized collections
- Use atomic variables instead of explicit synchronization for simple cases

### 3. Use Proper Synchronization

- Synchronize all accesses to shared mutable state
- Be aware of the scope of synchronization
- Avoid unnecessary synchronization

### 4. Be Careful with Thread Interruption

- Always handle `InterruptedException` properly
- Preserve the interrupted status when catching `InterruptedException`
- Use interruption for cancellation

### 5. Avoid Deadlocks

- Acquire locks in a fixed, global order
- Use `tryLock()` with timeout
- Avoid calling unknown methods while holding locks

### 6. Document Thread Safety

- Clearly document the thread safety guarantees of your classes
- Specify which methods are thread-safe and which are not
- Document any synchronization requirements

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `BasicThreadExample.java`: Demonstrates creating and starting threads
- `SynchronizationExample.java`: Shows thread synchronization techniques
- `ThreadCommunicationExample.java`: Illustrates thread communication
- `ExecutorExample.java`: Demonstrates using the Executor framework
- `ConcurrentCollectionsExample.java`: Shows concurrent collections
- `AtomicVariablesExample.java`: Illustrates atomic variables
- `LocksExample.java`: Demonstrates using locks and conditions
- `ThreadLocalExample.java`: Shows thread-local variables
- `SynchronizersExample.java`: Illustrates synchronization aids
- `ForkJoinExample.java`: Demonstrates the Fork/Join framework

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Implement a thread-safe counter
2. `Exercise2.java`: Create a producer-consumer system using a blocking queue
3. `Exercise3.java`: Build a parallel file processor using the Executor framework
4. `Exercise4.java`: Implement a concurrent web crawler

Complete these exercises to practice what you've learned. Multithreading and concurrency are essential skills for developing high-performance Java applications!

## Next Steps

After completing this section, proceed to the "Java Network Programming" section to learn about network communication in Java. This will allow you to build applications that can communicate over networks.
