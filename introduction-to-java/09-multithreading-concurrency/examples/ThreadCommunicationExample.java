/**
 * ThreadCommunicationExample.java
 * This program demonstrates thread communication techniques in Java.
 */
public class ThreadCommunicationExample {
    public static void main(String[] args) {
        System.out.println("--- Thread Communication Examples ---");
        
        // Example 1: wait(), notify(), and notifyAll()
        System.out.println("\nExample 1: wait(), notify(), and notifyAll()");
        waitNotifyExample();
        
        // Example 2: Producer-Consumer pattern
        System.out.println("\nExample 2: Producer-Consumer pattern");
        producerConsumerExample();
        
        // Example 3: Thread interruption
        System.out.println("\nExample 3: Thread interruption");
        interruptionExample();
        
        // Example 4: Thread signaling with a flag
        System.out.println("\nExample 4: Thread signaling with a flag");
        flagSignalingExample();
        
        // Example 5: Multiple threads waiting on a condition
        System.out.println("\nExample 5: Multiple threads waiting on a condition");
        multipleWaitersExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates wait(), notify(), and notifyAll() methods.
     */
    private static void waitNotifyExample() {
        // Create a shared object for synchronization
        final Object lock = new Object();
        
        // Create a waiter thread
        Thread waiterThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Waiter thread: Acquired the lock");
                System.out.println("Waiter thread: Waiting for notification...");
                
                try {
                    // Release the lock and wait for notification
                    lock.wait();
                    
                    // After notification, reacquire the lock and continue
                    System.out.println("Waiter thread: Received notification");
                    System.out.println("Waiter thread: Continuing execution");
                } catch (InterruptedException e) {
                    System.out.println("Waiter thread: Interrupted while waiting");
                }
            }
        });
        
        // Create a notifier thread
        Thread notifierThread = new Thread(() -> {
            // Sleep for a while to let the waiter thread start waiting
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Notifier thread: Interrupted while sleeping");
            }
            
            synchronized (lock) {
                System.out.println("Notifier thread: Acquired the lock");
                System.out.println("Notifier thread: Notifying waiter...");
                
                // Notify one waiting thread
                lock.notify();
                
                System.out.println("Notifier thread: Notification sent");
                // The lock is released when the synchronized block exits
            }
        });
        
        // Start the threads
        waiterThread.start();
        notifierThread.start();
        
        // Wait for the threads to finish
        try {
            waiterThread.join();
            notifierThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Example of notifyAll()
        System.out.println("\nExample of notifyAll():");
        
        // Create multiple waiter threads
        Thread[] waiters = new Thread[3];
        for (int i = 0; i < waiters.length; i++) {
            final int waiterId = i;
            waiters[i] = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("Waiter " + waiterId + ": Waiting for notification...");
                    
                    try {
                        lock.wait();
                        System.out.println("Waiter " + waiterId + ": Received notification");
                    } catch (InterruptedException e) {
                        System.out.println("Waiter " + waiterId + ": Interrupted while waiting");
                    }
                }
            });
        }
        
        // Create a thread that notifies all waiters
        Thread allNotifier = new Thread(() -> {
            // Sleep for a while to let the waiter threads start waiting
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("All-notifier thread: Interrupted while sleeping");
            }
            
            synchronized (lock) {
                System.out.println("All-notifier: Notifying all waiters...");
                
                // Notify all waiting threads
                lock.notifyAll();
                
                System.out.println("All-notifier: Notification sent to all");
            }
        });
        
        // Start the threads
        for (Thread waiter : waiters) {
            waiter.start();
        }
        allNotifier.start();
        
        // Wait for the threads to finish
        try {
            for (Thread waiter : waiters) {
                waiter.join();
            }
            allNotifier.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
    
    /**
     * Demonstrates the Producer-Consumer pattern.
     */
    private static void producerConsumerExample() {
        // Create a message queue with capacity 5
        MessageQueue queue = new MessageQueue(5);
        
        // Create a producer thread
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String message = "Message " + i;
                    System.out.println("Producer: Producing " + message);
                    queue.put(message);
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
                    String message = queue.take();
                    System.out.println("Consumer: Consumed " + message);
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
        
        System.out.println("Queue size after production and consumption: " + queue.size());
    }
    
    /**
     * Demonstrates thread interruption.
     */
    private static void interruptionExample() {
        // Create a thread that performs a long-running task
        Thread workerThread = new Thread(() -> {
            try {
                System.out.println("Worker thread: Starting long-running task...");
                
                // Simulate a long-running task with periodic checks for interruption
                for (int i = 0; i < 10; i++) {
                    // Check if the thread has been interrupted
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Worker thread: Interrupted, exiting loop.");
                        return;  // Exit the thread
                    }
                    
                    System.out.println("Worker thread: Working... " + i);
                    Thread.sleep(500);  // This can throw InterruptedException
                }
                
                System.out.println("Worker thread: Task completed normally.");
            } catch (InterruptedException e) {
                // Thread was interrupted during sleep
                System.out.println("Worker thread: Interrupted during sleep.");
                
                // Optionally restore the interrupted status
                Thread.currentThread().interrupt();
            }
            
            // Check if the thread is still interrupted
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Worker thread: Still interrupted after catch block.");
            }
            
            System.out.println("Worker thread: Exiting.");
        });
        
        // Start the worker thread
        workerThread.start();
        
        // Sleep for a while, then interrupt the worker thread
        try {
            Thread.sleep(2000);
            System.out.println("Main thread: Interrupting worker thread...");
            workerThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Wait for the worker thread to finish
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while joining worker thread.");
        }
        
        System.out.println("Interruption example completed.");
        
        // Example of handling interruption in a loop
        System.out.println("\nExample of handling interruption in a loop:");
        
        Thread loopThread = new Thread(() -> {
            System.out.println("Loop thread: Starting...");
            
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Do some work
                    System.out.println("Loop thread: Working...");
                    
                    // Simulate some blocking operation
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Loop thread: Interrupted during sleep.");
                // We don't restore the interrupted status here
            }
            
            System.out.println("Loop thread: Exiting.");
        });
        
        // Start the loop thread
        loopThread.start();
        
        // Sleep for a while, then interrupt the loop thread
        try {
            Thread.sleep(2000);
            System.out.println("Main thread: Interrupting loop thread...");
            loopThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Wait for the loop thread to finish
        try {
            loopThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while joining loop thread.");
        }
    }
    
    /**
     * Demonstrates thread signaling with a volatile flag.
     */
    private static void flagSignalingExample() {
        // Create a task with a stop flag
        class StoppableTask implements Runnable {
            private volatile boolean stopRequested = false;
            
            public void requestStop() {
                stopRequested = true;
            }
            
            @Override
            public void run() {
                System.out.println("Task: Starting...");
                
                // Run until stop is requested
                while (!stopRequested) {
                    // Do some work
                    System.out.println("Task: Working...");
                    
                    // Simulate some non-blocking work
                    for (int i = 0; i < 1000000; i++) {
                        // Just burn some CPU cycles
                        Math.sin(i);
                    }
                }
                
                System.out.println("Task: Stop requested, exiting.");
            }
        }
        
        // Create a stoppable task
        StoppableTask task = new StoppableTask();
        Thread taskThread = new Thread(task);
        
        // Start the task
        taskThread.start();
        
        // Sleep for a while, then request the task to stop
        try {
            Thread.sleep(100);
            System.out.println("Main thread: Requesting task to stop...");
            task.requestStop();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Wait for the task thread to finish
        try {
            taskThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while joining task thread.");
        }
        
        System.out.println("Flag signaling example completed.");
    }
    
    /**
     * Demonstrates multiple threads waiting on a condition.
     */
    private static void multipleWaitersExample() {
        // Create a barrier that waits for all threads to arrive
        class Barrier {
            private final int totalThreads;
            private int arrivedThreads = 0;
            private boolean barrierBroken = false;
            
            public Barrier(int totalThreads) {
                this.totalThreads = totalThreads;
            }
            
            public synchronized void await() throws InterruptedException {
                // Check if the barrier is broken
                if (barrierBroken) {
                    throw new InterruptedException("Barrier is broken");
                }
                
                // Increment the count of arrived threads
                arrivedThreads++;
                
                if (arrivedThreads == totalThreads) {
                    // All threads have arrived, notify all waiting threads
                    notifyAll();
                    return;
                }
                
                // Wait for all threads to arrive
                while (arrivedThreads < totalThreads && !barrierBroken) {
                    wait();
                }
            }
            
            public synchronized void breakBarrier() {
                barrierBroken = true;
                notifyAll();  // Wake up all waiting threads
            }
        }
        
        // Create a barrier for 3 threads
        Barrier barrier = new Barrier(3);
        
        // Create worker threads
        Thread[] workers = new Thread[3];
        for (int i = 0; i < workers.length; i++) {
            final int workerId = i;
            workers[i] = new Thread(() -> {
                try {
                    // Simulate some work before reaching the barrier
                    System.out.println("Worker " + workerId + ": Working before barrier...");
                    Thread.sleep(1000 + (int)(Math.random() * 2000));
                    
                    System.out.println("Worker " + workerId + ": Reached barrier, waiting for others...");
                    barrier.await();
                    
                    // All threads continue here after the barrier
                    System.out.println("Worker " + workerId + ": Passed barrier, continuing...");
                    
                    // Simulate some work after the barrier
                    Thread.sleep((int)(Math.random() * 1000));
                    System.out.println("Worker " + workerId + ": Completed work after barrier.");
                } catch (InterruptedException e) {
                    System.out.println("Worker " + workerId + ": Interrupted.");
                }
            });
        }
        
        // Start the worker threads
        for (Thread worker : workers) {
            worker.start();
        }
        
        // Wait for all worker threads to finish
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Multiple waiters example completed.");
        
        // Example of breaking a barrier
        System.out.println("\nExample of breaking a barrier:");
        
        // Create a new barrier
        Barrier breakableBarrier = new Barrier(3);
        
        // Create worker threads
        Thread[] moreWorkers = new Thread[3];
        for (int i = 0; i < moreWorkers.length; i++) {
            final int workerId = i;
            moreWorkers[i] = new Thread(() -> {
                try {
                    // Simulate some work before reaching the barrier
                    System.out.println("Worker " + workerId + ": Working before barrier...");
                    Thread.sleep(1000 + (int)(Math.random() * 2000));
                    
                    // The last worker will never reach the barrier
                    if (workerId == 2) {
                        System.out.println("Worker " + workerId + ": Breaking the barrier!");
                        breakableBarrier.breakBarrier();
                        return;
                    }
                    
                    System.out.println("Worker " + workerId + ": Reached barrier, waiting for others...");
                    breakableBarrier.await();
                    
                    // This should not be reached if the barrier is broken
                    System.out.println("Worker " + workerId + ": Passed barrier, continuing...");
                } catch (InterruptedException e) {
                    System.out.println("Worker " + workerId + ": Barrier was broken.");
                }
            });
        }
        
        // Start the worker threads
        for (Thread worker : moreWorkers) {
            worker.start();
        }
        
        // Wait for all worker threads to finish
        try {
            for (Thread worker : moreWorkers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Barrier breaking example completed.");
    }
    
    /**
     * A thread-safe message queue implementation using wait() and notify().
     */
    private static class MessageQueue {
        private final String[] buffer;
        private int count = 0;
        private int putIndex = 0;
        private int takeIndex = 0;
        
        public MessageQueue(int capacity) {
            buffer = new String[capacity];
        }
        
        /**
         * Puts a message into the queue, waiting if the queue is full.
         */
        public synchronized void put(String message) throws InterruptedException {
            // Wait while the queue is full
            while (count == buffer.length) {
                System.out.println("Producer: Queue is full, waiting...");
                wait();
            }
            
            // Add the message to the queue
            buffer[putIndex] = message;
            putIndex = (putIndex + 1) % buffer.length;
            count++;
            
            System.out.println("Producer: Added message, queue size is now " + count);
            
            // Notify a waiting consumer
            notify();
        }
        
        /**
         * Takes a message from the queue, waiting if the queue is empty.
         */
        public synchronized String take() throws InterruptedException {
            // Wait while the queue is empty
            while (count == 0) {
                System.out.println("Consumer: Queue is empty, waiting...");
                wait();
            }
            
            // Remove a message from the queue
            String message = buffer[takeIndex];
            takeIndex = (takeIndex + 1) % buffer.length;
            count--;
            
            System.out.println("Consumer: Removed message, queue size is now " + count);
            
            // Notify a waiting producer
            notify();
            
            return message;
        }
        
        /**
         * Returns the current size of the queue.
         */
        public synchronized int size() {
            return count;
        }
    }
}
