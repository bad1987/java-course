/**
 * Exercise 2: Producer-Consumer System
 * 
 * Instructions:
 * 1. Implement a producer-consumer system using a blocking queue.
 * 
 * 2. The system should have the following components:
 *    a. Producer: Produces items and adds them to the queue
 *    b. Consumer: Consumes items from the queue
 *    c. BlockingQueue: A thread-safe queue that blocks when empty or full
 * 
 * 3. Implement your own BlockingQueue class with the following methods:
 *    - put(T item): Adds an item to the queue, blocking if the queue is full
 *    - take(): Removes and returns an item from the queue, blocking if the queue is empty
 *    - size(): Returns the current number of items in the queue
 * 
 * 4. Your BlockingQueue implementation should:
 *    - Have a configurable maximum capacity
 *    - Be thread-safe
 *    - Use proper synchronization mechanisms
 *    - Use wait() and notifyAll() for blocking behavior
 * 
 * 5. Create multiple producer and consumer threads that share the same queue.
 * 
 * 6. Implement a monitoring thread that periodically reports the queue size.
 * 
 * 7. Bonus: Implement a timeout version of put and take that gives up after a specified time.
 */
public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("Producer-Consumer Exercise");
        System.out.println("=========================");
        
        // TODO: Create a BlockingQueue
        
        // TODO: Create and start producer threads
        
        // TODO: Create and start consumer threads
        
        // TODO: Create and start a monitoring thread
        
        // TODO: Wait for all threads to finish
    }
}

/**
 * TODO: Implement BlockingQueue
 */
class BlockingQueue<T> {
    // TODO: Implement this class
}

/**
 * TODO: Implement Producer
 */
class Producer implements Runnable {
    // TODO: Implement this class
}

/**
 * TODO: Implement Consumer
 */
class Consumer implements Runnable {
    // TODO: Implement this class
}

/**
 * TODO: Implement QueueMonitor
 */
class QueueMonitor implements Runnable {
    // TODO: Implement this class
}
