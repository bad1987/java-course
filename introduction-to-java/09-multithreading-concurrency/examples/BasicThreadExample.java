/**
 * BasicThreadExample.java
 * This program demonstrates the basics of creating and working with threads in Java.
 */
public class BasicThreadExample {
    public static void main(String[] args) {
        System.out.println("--- Basic Thread Examples ---");
        
        // Example 1: Creating a thread by extending Thread class
        System.out.println("\nExample 1: Creating a thread by extending Thread class");
        extendThreadExample();
        
        // Example 2: Creating a thread by implementing Runnable interface
        System.out.println("\nExample 2: Creating a thread by implementing Runnable interface");
        implementRunnableExample();
        
        // Example 3: Creating a thread using lambda expressions (Java 8+)
        System.out.println("\nExample 3: Creating a thread using lambda expressions");
        lambdaThreadExample();
        
        // Example 4: Thread methods and properties
        System.out.println("\nExample 4: Thread methods and properties");
        threadMethodsExample();
        
        // Example 5: Thread states
        System.out.println("\nExample 5: Thread states");
        threadStatesExample();
        
        // Example 6: Thread priorities
        System.out.println("\nExample 6: Thread priorities");
        threadPrioritiesExample();
        
        // Example 7: Joining threads
        System.out.println("\nExample 7: Joining threads");
        joiningThreadsExample();
        
        // Example 8: Daemon threads
        System.out.println("\nExample 8: Daemon threads");
        daemonThreadsExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates creating a thread by extending the Thread class.
     */
    private static void extendThreadExample() {
        // Create a thread by extending Thread class
        class MyThread extends Thread {
            private final String name;
            
            public MyThread(String name) {
                this.name = name;
            }
            
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(name + " thread: " + i);
                    try {
                        Thread.sleep(100);  // Sleep for 100 milliseconds
                    } catch (InterruptedException e) {
                        System.out.println(name + " thread interrupted.");
                        return;
                    }
                }
                System.out.println(name + " thread exiting.");
            }
        }
        
        // Create and start the threads
        MyThread thread1 = new MyThread("First");
        MyThread thread2 = new MyThread("Second");
        
        thread1.start();  // Start the first thread
        thread2.start();  // Start the second thread
        
        // Wait for the threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Both threads have finished execution.");
    }
    
    /**
     * Demonstrates creating a thread by implementing the Runnable interface.
     */
    private static void implementRunnableExample() {
        // Create a thread by implementing Runnable interface
        class MyRunnable implements Runnable {
            private final String name;
            
            public MyRunnable(String name) {
                this.name = name;
            }
            
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(name + " runnable: " + i);
                    try {
                        Thread.sleep(100);  // Sleep for 100 milliseconds
                    } catch (InterruptedException e) {
                        System.out.println(name + " runnable interrupted.");
                        return;
                    }
                }
                System.out.println(name + " runnable exiting.");
            }
        }
        
        // Create Runnable objects
        MyRunnable runnable1 = new MyRunnable("First");
        MyRunnable runnable2 = new MyRunnable("Second");
        
        // Create threads with the Runnable objects
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        
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
        
        System.out.println("Both threads have finished execution.");
    }
    
    /**
     * Demonstrates creating a thread using lambda expressions (Java 8+).
     */
    private static void lambdaThreadExample() {
        // Create threads using lambda expressions
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Lambda thread 1: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Lambda thread 1 interrupted.");
                    return;
                }
            }
            System.out.println("Lambda thread 1 exiting.");
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Lambda thread 2: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Lambda thread 2 interrupted.");
                    return;
                }
            }
            System.out.println("Lambda thread 2 exiting.");
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
        
        System.out.println("Both lambda threads have finished execution.");
    }
    
    /**
     * Demonstrates various thread methods and properties.
     */
    private static void threadMethodsExample() {
        // Create a thread
        Thread thread = new Thread(() -> {
            // Get the current thread
            Thread currentThread = Thread.currentThread();
            
            // Display thread information
            System.out.println("Thread ID: " + currentThread.getId());
            System.out.println("Thread Name: " + currentThread.getName());
            System.out.println("Thread Priority: " + currentThread.getPriority());
            System.out.println("Thread State: " + currentThread.getState());
            System.out.println("Thread Group: " + currentThread.getThreadGroup().getName());
            System.out.println("Is Daemon: " + currentThread.isDaemon());
            System.out.println("Is Alive: " + currentThread.isAlive());
            System.out.println("Is Interrupted: " + currentThread.isInterrupted());
            
            // Simulate some work
            for (int i = 0; i < 3; i++) {
                System.out.println("Working... " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted.");
                    return;
                }
            }
        });
        
        // Set thread name
        thread.setName("MyThread");
        
        // Set thread priority
        thread.setPriority(Thread.MAX_PRIORITY);  // 10
        
        // Start the thread
        thread.start();
        
        // Wait for the thread to finish
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Check if the thread is alive after completion
        System.out.println("Thread is alive after completion: " + thread.isAlive());
    }
    
    /**
     * Demonstrates the different states of a thread.
     */
    private static void threadStatesExample() {
        // Create a thread
        Thread thread = new Thread(() -> {
            try {
                // Sleep for a while
                Thread.sleep(500);
                
                // Wait for a monitor lock
                synchronized (BasicThreadExample.class) {
                    BasicThreadExample.class.wait(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        });
        
        // NEW state
        System.out.println("Thread state after creation: " + thread.getState());
        
        // Start the thread
        thread.start();
        
        // RUNNABLE state
        System.out.println("Thread state after starting: " + thread.getState());
        
        // Wait for the thread to enter TIMED_WAITING state
        try {
            Thread.sleep(100);
            System.out.println("Thread state during sleep: " + thread.getState());
            
            // Wait for the thread to enter WAITING state
            Thread.sleep(500);
            
            synchronized (BasicThreadExample.class) {
                System.out.println("Thread state during wait: " + thread.getState());
                BasicThreadExample.class.notifyAll();
            }
            
            // Wait for the thread to finish
            thread.join();
            
            // TERMINATED state
            System.out.println("Thread state after completion: " + thread.getState());
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
    
    /**
     * Demonstrates thread priorities.
     */
    private static void threadPrioritiesExample() {
        // Create threads with different priorities
        Thread minPriorityThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Min priority thread: " + i);
                Thread.yield();  // Yield to other threads
            }
        });
        
        Thread normPriorityThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Norm priority thread: " + i);
                Thread.yield();  // Yield to other threads
            }
        });
        
        Thread maxPriorityThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Max priority thread: " + i);
                Thread.yield();  // Yield to other threads
            }
        });
        
        // Set thread priorities
        minPriorityThread.setPriority(Thread.MIN_PRIORITY);      // 1
        normPriorityThread.setPriority(Thread.NORM_PRIORITY);    // 5
        maxPriorityThread.setPriority(Thread.MAX_PRIORITY);      // 10
        
        System.out.println("Min priority: " + minPriorityThread.getPriority());
        System.out.println("Norm priority: " + normPriorityThread.getPriority());
        System.out.println("Max priority: " + maxPriorityThread.getPriority());
        
        // Start the threads
        minPriorityThread.start();
        normPriorityThread.start();
        maxPriorityThread.start();
        
        // Wait for the threads to finish
        try {
            minPriorityThread.join();
            normPriorityThread.join();
            maxPriorityThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("All priority threads have finished execution.");
        System.out.println("Note: Thread priorities are hints to the scheduler and may not be honored.");
    }
    
    /**
     * Demonstrates joining threads.
     */
    private static void joiningThreadsExample() {
        // Create threads
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 starting...");
            try {
                Thread.sleep(2000);  // Sleep for 2 seconds
            } catch (InterruptedException e) {
                System.out.println("Thread 1 interrupted.");
            }
            System.out.println("Thread 1 completed.");
        });
        
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 starting...");
            try {
                Thread.sleep(1000);  // Sleep for 1 second
            } catch (InterruptedException e) {
                System.out.println("Thread 2 interrupted.");
            }
            System.out.println("Thread 2 completed.");
        });
        
        // Start the threads
        thread1.start();
        thread2.start();
        
        System.out.println("Waiting for threads to complete...");
        
        // Join thread1 with timeout
        try {
            System.out.println("Joining thread1 with timeout of 1 second...");
            thread1.join(1000);  // Wait for 1 second
            System.out.println("Join with timeout completed. Thread1 is still alive: " + thread1.isAlive());
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while joining thread1.");
        }
        
        // Join thread2 without timeout
        try {
            System.out.println("Joining thread2 without timeout...");
            thread2.join();  // Wait indefinitely
            System.out.println("Thread2 has completed.");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while joining thread2.");
        }
        
        // Wait for thread1 to complete
        try {
            thread1.join();
            System.out.println("Thread1 has completed.");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while joining thread1 again.");
        }
        
        System.out.println("All threads have completed.");
    }
    
    /**
     * Demonstrates daemon threads.
     */
    private static void daemonThreadsExample() {
        // Create a daemon thread
        Thread daemonThread = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("Daemon thread is running... " + count++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Daemon thread interrupted.");
                    break;
                }
            }
        });
        
        // Set as daemon thread
        daemonThread.setDaemon(true);
        System.out.println("Is daemon thread: " + daemonThread.isDaemon());
        
        // Start the daemon thread
        daemonThread.start();
        
        // Create a normal (non-daemon) thread
        Thread normalThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Normal thread is running... " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Normal thread interrupted.");
                    break;
                }
            }
            System.out.println("Normal thread completed.");
        });
        
        // Start the normal thread
        normalThread.start();
        
        // Wait for the normal thread to complete
        try {
            normalThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Main thread and normal thread completed.");
        System.out.println("Daemon thread will be terminated when the JVM exits.");
        
        // Sleep briefly to allow the daemon thread to run a bit more
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted during final sleep.");
        }
    }
}
