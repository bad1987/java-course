/**
 * Exercise 1: Thread-Safe Counter
 * 
 * Instructions:
 * 1. Implement a thread-safe counter class that can be safely incremented and decremented
 *    by multiple threads concurrently.
 * 
 * 2. Your counter should support the following operations:
 *    - increment(): Increments the counter by 1
 *    - decrement(): Decrements the counter by 1
 *    - getValue(): Returns the current value of the counter
 * 
 * 3. Implement three different versions of the counter:
 *    a. Using synchronized methods
 *    b. Using explicit locks (ReentrantLock)
 *    c. Using atomic variables (AtomicInteger)
 * 
 * 4. Create a test program that:
 *    - Creates multiple threads that increment and decrement the counter
 *    - Verifies that the final value is correct
 *    - Compares the performance of the three implementations
 * 
 * 5. Bonus: Implement a fourth version using volatile and compare-and-swap (CAS) operations
 *    without using AtomicInteger directly.
 */
public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("Thread-Safe Counter Exercise");
        System.out.println("===========================");
        
        // TODO: Implement the counter classes
        
        // TODO: Test the counter implementations
        
        // TODO: Compare the performance of the implementations
    }
}

/**
 * TODO: Implement SynchronizedCounter
 */
class SynchronizedCounter {
    // TODO: Implement this class
}

/**
 * TODO: Implement LockCounter
 */
class LockCounter {
    // TODO: Implement this class
}

/**
 * TODO: Implement AtomicCounter
 */
class AtomicCounter {
    // TODO: Implement this class
}

/**
 * TODO: Implement CASCounter (bonus)
 */
class CASCounter {
    // TODO: Implement this class
}
