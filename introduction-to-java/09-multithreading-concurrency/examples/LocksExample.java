/**
 * LocksExample.java
 * This program demonstrates the use of locks and conditions in Java.
 */
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class LocksExample {
    public static void main(String[] args) {
        System.out.println("--- Locks and Conditions Examples ---");
        
        // Example 1: ReentrantLock
        System.out.println("\nExample 1: ReentrantLock");
        reentrantLockExample();
        
        // Example 2: Lock with Condition
        System.out.println("\nExample 2: Lock with Condition");
        lockWithConditionExample();
        
        // Example 3: ReadWriteLock
        System.out.println("\nExample 3: ReadWriteLock");
        readWriteLockExample();
        
        // Example 4: StampedLock (Java 8+)
        System.out.println("\nExample 4: StampedLock");
        stampedLockExample();
        
        // Example 5: Timed Lock Acquisition
        System.out.println("\nExample 5: Timed Lock Acquisition");
        timedLockAcquisitionExample();
        
        // Example 6: Interruptible Lock Acquisition
        System.out.println("\nExample 6: Interruptible Lock Acquisition");
        interruptibleLockAcquisitionExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates the use of ReentrantLock.
     */
    private static void reentrantLockExample() {
        // Create a ReentrantLock
        ReentrantLock lock = new ReentrantLock();
        
        // Create a shared counter
        class Counter {
            private int count = 0;
            
            public void increment() {
                lock.lock();
                try {
                    count++;
                } finally {
                    lock.unlock();
                }
            }
            
            public int getCount() {
                lock.lock();
                try {
                    return count;
                } finally {
                    lock.unlock();
                }
            }
        }
        
        // Create a shared counter
        Counter counter = new Counter();
        
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
        
        // The final count should be 20000
        System.out.println("Final count: " + counter.getCount());
        
        // Demonstrate ReentrantLock features
        System.out.println("\nReentrantLock features:");
        
        // Create a ReentrantLock with fairness policy
        ReentrantLock fairLock = new ReentrantLock(true);
        System.out.println("Fair lock: " + fairLock.isFair());
        
        // Demonstrate lock reentrancy
        fairLock.lock();
        try {
            System.out.println("Hold count: " + fairLock.getHoldCount());
            
            // Reenter the lock
            fairLock.lock();
            try {
                System.out.println("Hold count after reentry: " + fairLock.getHoldCount());
            } finally {
                fairLock.unlock();
            }
            
            System.out.println("Hold count after inner unlock: " + fairLock.getHoldCount());
        } finally {
            fairLock.unlock();
        }
        
        System.out.println("Hold count after outer unlock: " + fairLock.getHoldCount());
        System.out.println("Is locked: " + fairLock.isLocked());
        System.out.println("Is held by current thread: " + fairLock.isHeldByCurrentThread());
        System.out.println("Queue length: " + fairLock.getQueueLength());
    }
    
    /**
     * Demonstrates the use of Lock with Condition.
     */
    private static void lockWithConditionExample() {
        // Create a bounded buffer using Lock and Condition
        class BoundedBuffer {
            private final Lock lock = new ReentrantLock();
            private final Condition notFull = lock.newCondition();
            private final Condition notEmpty = lock.newCondition();
            
            private final Object[] items;
            private int putIndex = 0;
            private int takeIndex = 0;
            private int count = 0;
            
            public BoundedBuffer(int capacity) {
                items = new Object[capacity];
            }
            
            public void put(Object x) throws InterruptedException {
                lock.lock();
                try {
                    // Wait until the buffer is not full
                    while (count == items.length) {
                        System.out.println("Buffer is full, waiting...");
                        notFull.await();
                    }
                    
                    // Add the item
                    items[putIndex] = x;
                    putIndex = (putIndex + 1) % items.length;
                    count++;
                    
                    System.out.println("Added item: " + x + ", count: " + count);
                    
                    // Signal that the buffer is not empty
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }
            
            public Object take() throws InterruptedException {
                lock.lock();
                try {
                    // Wait until the buffer is not empty
                    while (count == 0) {
                        System.out.println("Buffer is empty, waiting...");
                        notEmpty.await();
                    }
                    
                    // Remove the item
                    Object x = items[takeIndex];
                    items[takeIndex] = null;  // Help GC
                    takeIndex = (takeIndex + 1) % items.length;
                    count--;
                    
                    System.out.println("Removed item: " + x + ", count: " + count);
                    
                    // Signal that the buffer is not full
                    notFull.signal();
                    
                    return x;
                } finally {
                    lock.unlock();
                }
            }
            
            public int size() {
                lock.lock();
                try {
                    return count;
                } finally {
                    lock.unlock();
                }
            }
        }
        
        // Create a bounded buffer with capacity 5
        BoundedBuffer buffer = new BoundedBuffer(5);
        
        // Create a producer thread
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    buffer.put("Item " + i);
                    Thread.sleep(200);  // Simulate varying production time
                }
            } catch (InterruptedException e) {
                System.out.println("Producer thread interrupted.");
            }
        });
        
        // Create a consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Object item = buffer.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(500);  // Simulate varying consumption time
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer thread interrupted.");
            }
        });
        
        // Start the threads
        producerThread.start();
        consumerThread.start();
        
        // Wait for the threads to finish
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Final buffer size: " + buffer.size());
        
        // Demonstrate other Condition methods
        System.out.println("\nCondition methods:");
        
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        
        // Create a thread that waits on the condition
        Thread waiterThread = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Waiter thread: Waiting on condition...");
                
                try {
                    // Wait with timeout
                    boolean signaled = condition.await(2, TimeUnit.SECONDS);
                    System.out.println("Waiter thread: Woke up, signaled = " + signaled);
                } catch (InterruptedException e) {
                    System.out.println("Waiter thread: Interrupted while waiting.");
                }
            } finally {
                lock.unlock();
            }
        });
        
        // Start the waiter thread
        waiterThread.start();
        
        // Sleep for a while, then signal the condition
        try {
            Thread.sleep(1000);
            
            lock.lock();
            try {
                System.out.println("Main thread: Signaling condition...");
                condition.signal();
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Wait for the waiter thread to finish
        try {
            waiterThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while joining waiter thread.");
        }
    }
    
    /**
     * Demonstrates the use of ReadWriteLock.
     */
    private static void readWriteLockExample() {
        // Create a cache using ReadWriteLock
        class Cache {
            private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
            private final Lock readLock = rwLock.readLock();
            private final Lock writeLock = rwLock.writeLock();
            
            private final java.util.Map<String, Object> map = new java.util.HashMap<>();
            
            public Object get(String key) {
                readLock.lock();
                try {
                    return map.get(key);
                } finally {
                    readLock.unlock();
                }
            }
            
            public void put(String key, Object value) {
                writeLock.lock();
                try {
                    map.put(key, value);
                } finally {
                    writeLock.unlock();
                }
            }
            
            public boolean containsKey(String key) {
                readLock.lock();
                try {
                    return map.containsKey(key);
                } finally {
                    readLock.unlock();
                }
            }
            
            public int size() {
                readLock.lock();
                try {
                    return map.size();
                } finally {
                    readLock.unlock();
                }
            }
            
            public void clear() {
                writeLock.lock();
                try {
                    map.clear();
                } finally {
                    writeLock.unlock();
                }
            }
        }
        
        // Create a cache
        Cache cache = new Cache();
        
        // Create reader threads
        Thread[] readers = new Thread[5];
        for (int i = 0; i < readers.length; i++) {
            final int readerId = i;
            readers[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    String key = "Key-" + (j % 5);
                    Object value = cache.get(key);
                    System.out.println("Reader " + readerId + ": " + key + " = " + value);
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
        
        // Create writer threads
        Thread[] writers = new Thread[2];
        for (int i = 0; i < writers.length; i++) {
            final int writerId = i;
            writers[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String key = "Key-" + j;
                    String value = "Value-" + writerId + "-" + j;
                    cache.put(key, value);
                    System.out.println("Writer " + writerId + ": Put " + key + " = " + value);
                    
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
        
        // Start the threads
        for (Thread writer : writers) {
            writer.start();
        }
        
        for (Thread reader : readers) {
            reader.start();
        }
        
        // Wait for the threads to finish
        try {
            for (Thread writer : writers) {
                writer.join();
            }
            
            for (Thread reader : readers) {
                reader.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Final cache size: " + cache.size());
        
        // Demonstrate ReadWriteLock features
        System.out.println("\nReadWriteLock features:");
        
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        
        // Multiple readers can acquire the read lock simultaneously
        rwLock.readLock().lock();
        System.out.println("First read lock acquired");
        
        rwLock.readLock().lock();
        System.out.println("Second read lock acquired");
        
        // Release the read locks
        rwLock.readLock().unlock();
        System.out.println("First read lock released");
        
        rwLock.readLock().unlock();
        System.out.println("Second read lock released");
        
        // Write lock is exclusive
        rwLock.writeLock().lock();
        System.out.println("Write lock acquired");
        
        // Cannot acquire read lock while write lock is held (by the same thread)
        // This would cause a deadlock in a real application
        // rwLock.readLock().lock();
        
        rwLock.writeLock().unlock();
        System.out.println("Write lock released");
    }
    
    /**
     * Demonstrates the use of StampedLock (Java 8+).
     */
    private static void stampedLockExample() {
        // Create a point class using StampedLock
        class Point {
            private final StampedLock sl = new StampedLock();
            private double x, y;
            
            public Point(double x, double y) {
                this.x = x;
                this.y = y;
            }
            
            // Write lock
            public void move(double deltaX, double deltaY) {
                long stamp = sl.writeLock();
                try {
                    x += deltaX;
                    y += deltaY;
                } finally {
                    sl.unlockWrite(stamp);
                }
            }
            
            // Read lock
            public double distanceFromOrigin() {
                long stamp = sl.readLock();
                try {
                    return Math.sqrt(x * x + y * y);
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            
            // Optimistic read
            public double distanceFromOriginOptimistic() {
                // Optimistic read
                long stamp = sl.tryOptimisticRead();
                double currentX = x;
                double currentY = y;
                
                // Check if the stamp is still valid
                if (!sl.validate(stamp)) {
                    // Fall back to reading lock
                    stamp = sl.readLock();
                    try {
                        currentX = x;
                        currentY = y;
                    } finally {
                        sl.unlockRead(stamp);
                    }
                }
                
                return Math.sqrt(currentX * currentX + currentY * currentY);
            }
            
            // Convert read lock to write lock
            public void moveIfAtOrigin(double newX, double newY) {
                // Read lock
                long stamp = sl.readLock();
                try {
                    while (x == 0.0 && y == 0.0) {
                        // Try to convert to write lock
                        long ws = sl.tryConvertToWriteLock(stamp);
                        if (ws != 0L) {
                            // Write lock acquired
                            stamp = ws;
                            x = newX;
                            y = newY;
                            break;
                        } else {
                            // Could not convert to write lock
                            sl.unlockRead(stamp);
                            stamp = sl.writeLock();
                        }
                    }
                } finally {
                    sl.unlock(stamp);
                }
            }
            
            @Override
            public String toString() {
                long stamp = sl.readLock();
                try {
                    return "Point{x=" + x + ", y=" + y + "}";
                } finally {
                    sl.unlockRead(stamp);
                }
            }
        }
        
        // Create a point
        Point point = new Point(3.0, 4.0);
        
        // Create threads that use the point
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    if (threadId % 3 == 0) {
                        // Move the point
                        point.move(0.1, 0.1);
                        System.out.println("Thread " + threadId + ": Moved point to " + point);
                    } else if (threadId % 3 == 1) {
                        // Calculate distance with read lock
                        double distance = point.distanceFromOrigin();
                        System.out.println("Thread " + threadId + ": Distance from origin = " + distance);
                    } else {
                        // Calculate distance with optimistic read
                        double distance = point.distanceFromOriginOptimistic();
                        System.out.println("Thread " + threadId + ": Optimistic distance = " + distance);
                    }
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
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
        
        System.out.println("Final point: " + point);
        
        // Demonstrate StampedLock features
        System.out.println("\nStampedLock features:");
        
        StampedLock sl = new StampedLock();
        
        // Write lock
        long writeStamp = sl.writeLock();
        System.out.println("Write lock acquired, stamp = " + writeStamp);
        
        // Release write lock
        sl.unlockWrite(writeStamp);
        System.out.println("Write lock released");
        
        // Read lock
        long readStamp = sl.readLock();
        System.out.println("Read lock acquired, stamp = " + readStamp);
        
        // Try to get write lock (will fail because read lock is held)
        long tryWriteStamp = sl.tryWriteLock();
        System.out.println("Try write lock result = " + tryWriteStamp);
        
        // Release read lock
        sl.unlockRead(readStamp);
        System.out.println("Read lock released");
        
        // Optimistic read
        long optimisticStamp = sl.tryOptimisticRead();
        System.out.println("Optimistic read stamp = " + optimisticStamp);
        System.out.println("Optimistic read valid = " + sl.validate(optimisticStamp));
        
        // Write lock (invalidates optimistic read)
        writeStamp = sl.writeLock();
        System.out.println("Write lock acquired, stamp = " + writeStamp);
        System.out.println("Optimistic read still valid = " + sl.validate(optimisticStamp));
        
        // Release write lock
        sl.unlockWrite(writeStamp);
        System.out.println("Write lock released");
    }
    
    /**
     * Demonstrates timed lock acquisition.
     */
    private static void timedLockAcquisitionExample() {
        // Create a lock
        Lock lock = new ReentrantLock();
        
        // Create a thread that holds the lock for a while
        Thread lockHolderThread = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Lock holder: Acquired lock");
                
                // Hold the lock for 2 seconds
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.println("Lock holder: Releasing lock");
            } finally {
                lock.unlock();
            }
        });
        
        // Create a thread that tries to acquire the lock with timeout
        Thread timedLockThread = new Thread(() -> {
            System.out.println("Timed lock thread: Trying to acquire lock with timeout...");
            
            try {
                // Try to acquire the lock with a timeout of 1 second
                boolean acquired = lock.tryLock(1, TimeUnit.SECONDS);
                
                if (acquired) {
                    try {
                        System.out.println("Timed lock thread: Acquired lock");
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("Timed lock thread: Could not acquire lock within timeout");
                }
            } catch (InterruptedException e) {
                System.out.println("Timed lock thread: Interrupted while trying to acquire lock");
            }
        });
        
        // Start the lock holder thread
        lockHolderThread.start();
        
        // Sleep briefly to let the lock holder acquire the lock
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Start the timed lock thread
        timedLockThread.start();
        
        // Wait for the threads to finish
        try {
            lockHolderThread.join();
            timedLockThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Try again after the lock holder has released the lock
        Thread secondTimedLockThread = new Thread(() -> {
            System.out.println("\nSecond timed lock thread: Trying to acquire lock with timeout...");
            
            try {
                // Try to acquire the lock with a timeout of 1 second
                boolean acquired = lock.tryLock(1, TimeUnit.SECONDS);
                
                if (acquired) {
                    try {
                        System.out.println("Second timed lock thread: Acquired lock");
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("Second timed lock thread: Could not acquire lock within timeout");
                }
            } catch (InterruptedException e) {
                System.out.println("Second timed lock thread: Interrupted while trying to acquire lock");
            }
        });
        
        // Start the second timed lock thread
        secondTimedLockThread.start();
        
        // Wait for the thread to finish
        try {
            secondTimedLockThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
    
    /**
     * Demonstrates interruptible lock acquisition.
     */
    private static void interruptibleLockAcquisitionExample() {
        // Create a lock
        Lock lock = new ReentrantLock();
        
        // Create a thread that holds the lock for a while
        Thread lockHolderThread = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Lock holder: Acquired lock");
                
                // Hold the lock for 5 seconds
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.println("Lock holder: Releasing lock");
            } finally {
                lock.unlock();
            }
        });
        
        // Create a thread that tries to acquire the lock (interruptibly)
        Thread interruptibleThread = new Thread(() -> {
            System.out.println("Interruptible thread: Trying to acquire lock...");
            
            try {
                // Try to acquire the lock (can be interrupted)
                lock.lockInterruptibly();
                
                try {
                    System.out.println("Interruptible thread: Acquired lock");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("Interruptible thread: Interrupted while trying to acquire lock");
            }
        });
        
        // Start the lock holder thread
        lockHolderThread.start();
        
        // Sleep briefly to let the lock holder acquire the lock
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Start the interruptible thread
        interruptibleThread.start();
        
        // Sleep for a while, then interrupt the interruptible thread
        try {
            Thread.sleep(2000);
            System.out.println("Main thread: Interrupting the interruptible thread...");
            interruptibleThread.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Wait for the threads to finish
        try {
            interruptibleThread.join();
            lockHolderThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Create a thread that tries to acquire the lock (non-interruptibly)
        Thread nonInterruptibleThread = new Thread(() -> {
            System.out.println("\nNon-interruptible thread: Trying to acquire lock...");
            
            // Try to acquire the lock (cannot be interrupted)
            lock.lock();
            
            try {
                System.out.println("Non-interruptible thread: Acquired lock");
            } finally {
                lock.unlock();
            }
            
            System.out.println("Non-interruptible thread: Released lock");
        });
        
        // Acquire the lock in the main thread
        lock.lock();
        
        try {
            System.out.println("Main thread: Acquired lock");
            
            // Start the non-interruptible thread
            nonInterruptibleThread.start();
            
            // Sleep briefly to let the non-interruptible thread try to acquire the lock
            Thread.sleep(500);
            
            // Interrupt the non-interruptible thread (will not affect lock acquisition)
            System.out.println("Main thread: Interrupting the non-interruptible thread...");
            nonInterruptibleThread.interrupt();
            
            // Sleep for a while
            Thread.sleep(2000);
            
            System.out.println("Main thread: Releasing lock");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
        
        // Wait for the non-interruptible thread to finish
        try {
            nonInterruptibleThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}
