/**
 * SynchronizersExample.java
 * This program demonstrates the use of synchronization aids in Java.
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SynchronizersExample {
    public static void main(String[] args) {
        System.out.println("--- Synchronizers Examples ---");
        
        // Example 1: CountDownLatch
        System.out.println("\nExample 1: CountDownLatch");
        countDownLatchExample();
        
        // Example 2: CyclicBarrier
        System.out.println("\nExample 2: CyclicBarrier");
        cyclicBarrierExample();
        
        // Example 3: Semaphore
        System.out.println("\nExample 3: Semaphore");
        semaphoreExample();
        
        // Example 4: Phaser
        System.out.println("\nExample 4: Phaser");
        phaserExample();
        
        // Example 5: Exchanger
        System.out.println("\nExample 5: Exchanger");
        exchangerExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates the use of CountDownLatch.
     */
    private static void countDownLatchExample() {
        // Create a CountDownLatch with a count of 3
        CountDownLatch latch = new CountDownLatch(3);
        
        System.out.println("Starting CountDownLatch example with count = " + latch.getCount());
        
        // Create worker threads
        Thread[] workers = new Thread[3];
        for (int i = 0; i < workers.length; i++) {
            final int workerId = i;
            workers[i] = new Thread(() -> {
                try {
                    // Simulate work
                    System.out.println("Worker " + workerId + " is working...");
                    Thread.sleep(1000 + (int)(Math.random() * 2000));
                    
                    // Signal completion
                    System.out.println("Worker " + workerId + " is done");
                    latch.countDown();
                    System.out.println("Latch count after worker " + workerId + ": " + latch.getCount());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Create a waiter thread
        Thread waiterThread = new Thread(() -> {
            try {
                System.out.println("Waiter thread: Waiting for all workers to complete...");
                
                // Wait for all workers to complete
                latch.await();
                
                System.out.println("Waiter thread: All workers have completed!");
            } catch (InterruptedException e) {
                System.out.println("Waiter thread: Interrupted while waiting");
                Thread.currentThread().interrupt();
            }
        });
        
        // Start the threads
        waiterThread.start();
        for (Thread worker : workers) {
            worker.start();
        }
        
        // Wait for all threads to finish
        try {
            waiterThread.join();
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate CountDownLatch with timeout
        System.out.println("\nCountDownLatch with timeout:");
        
        // Create a new latch
        CountDownLatch timeoutLatch = new CountDownLatch(2);
        
        // Create a thread that counts down only once
        Thread partialWorker = new Thread(() -> {
            try {
                System.out.println("Partial worker: Working...");
                Thread.sleep(1000);
                
                // Count down only once
                System.out.println("Partial worker: Counting down once");
                timeoutLatch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Create a waiter thread with timeout
        Thread timeoutWaiter = new Thread(() -> {
            try {
                System.out.println("Timeout waiter: Waiting with timeout...");
                
                // Wait with timeout
                boolean completed = timeoutLatch.await(3, TimeUnit.SECONDS);
                
                if (completed) {
                    System.out.println("Timeout waiter: All tasks completed within timeout");
                } else {
                    System.out.println("Timeout waiter: Timeout expired before all tasks completed");
                    System.out.println("Timeout waiter: Remaining count = " + timeoutLatch.getCount());
                }
            } catch (InterruptedException e) {
                System.out.println("Timeout waiter: Interrupted while waiting");
                Thread.currentThread().interrupt();
            }
        });
        
        // Start the threads
        partialWorker.start();
        timeoutWaiter.start();
        
        // Wait for the threads to finish
        try {
            partialWorker.join();
            timeoutWaiter.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
    
    /**
     * Demonstrates the use of CyclicBarrier.
     */
    private static void cyclicBarrierExample() {
        // Create a CyclicBarrier for 3 threads with a barrier action
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            // This runs when all threads reach the barrier
            System.out.println("Barrier action: All threads have reached the barrier!");
        });
        
        System.out.println("Starting CyclicBarrier example with parties = " + barrier.getParties());
        
        // Create worker threads
        Thread[] workers = new Thread[3];
        for (int i = 0; i < workers.length; i++) {
            final int workerId = i;
            workers[i] = new Thread(() -> {
                try {
                    for (int cycle = 0; cycle < 2; cycle++) {
                        // Simulate work before reaching the barrier
                        System.out.println("Worker " + workerId + " in cycle " + cycle + " is working...");
                        Thread.sleep(1000 + (int)(Math.random() * 2000));
                        
                        System.out.println("Worker " + workerId + " in cycle " + cycle + " has reached the barrier");
                        System.out.println("Waiting parties: " + barrier.getNumberWaiting());
                        
                        // Wait for all threads to reach this point
                        int arrivalIndex = barrier.await();
                        
                        System.out.println("Worker " + workerId + " in cycle " + cycle + 
                                          " continuing after the barrier (arrival index: " + arrivalIndex + ")");
                    }
                } catch (InterruptedException e) {
                    System.out.println("Worker " + workerId + " interrupted");
                    Thread.currentThread().interrupt();
                } catch (java.util.concurrent.BrokenBarrierException e) {
                    System.out.println("Worker " + workerId + ": Barrier is broken");
                }
            });
        }
        
        // Start the threads
        for (Thread worker : workers) {
            worker.start();
        }
        
        // Wait for all threads to finish
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate barrier reset and broken barrier
        System.out.println("\nBarrier reset and broken barrier:");
        
        // Create a new barrier
        CyclicBarrier resetBarrier = new CyclicBarrier(3);
        
        // Create threads
        Thread[] resetWorkers = new Thread[3];
        for (int i = 0; i < resetWorkers.length; i++) {
            final int workerId = i;
            resetWorkers[i] = new Thread(() -> {
                try {
                    System.out.println("Reset worker " + workerId + " is working...");
                    
                    // Worker 0 will interrupt itself, breaking the barrier
                    if (workerId == 0) {
                        Thread.sleep(500);
                        System.out.println("Reset worker 0 is interrupting itself");
                        Thread.currentThread().interrupt();
                    }
                    
                    System.out.println("Reset worker " + workerId + " trying to await on barrier");
                    resetBarrier.await();
                    
                    System.out.println("Reset worker " + workerId + " continuing after the barrier");
                } catch (InterruptedException e) {
                    System.out.println("Reset worker " + workerId + " interrupted");
                } catch (java.util.concurrent.BrokenBarrierException e) {
                    System.out.println("Reset worker " + workerId + ": Barrier is broken");
                }
            });
        }
        
        // Start the threads
        for (Thread worker : resetWorkers) {
            worker.start();
        }
        
        // Wait for all threads to finish
        try {
            for (Thread worker : resetWorkers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Check if the barrier is broken
        System.out.println("Is barrier broken? " + resetBarrier.isBroken());
        
        // Reset the barrier
        System.out.println("Resetting the barrier");
        resetBarrier.reset();
        
        System.out.println("Is barrier still broken after reset? " + resetBarrier.isBroken());
    }
    
    /**
     * Demonstrates the use of Semaphore.
     */
    private static void semaphoreExample() {
        // Create a Semaphore with 3 permits
        Semaphore semaphore = new Semaphore(3);
        
        System.out.println("Starting Semaphore example with permits = " + semaphore.availablePermits());
        
        // Create worker threads
        Thread[] workers = new Thread[5];
        for (int i = 0; i < workers.length; i++) {
            final int workerId = i;
            workers[i] = new Thread(() -> {
                try {
                    System.out.println("Worker " + workerId + " is trying to acquire a permit");
                    
                    // Acquire a permit
                    semaphore.acquire();
                    
                    try {
                        System.out.println("Worker " + workerId + " acquired a permit");
                        System.out.println("Available permits: " + semaphore.availablePermits());
                        
                        // Simulate work
                        Thread.sleep(2000);
                        
                        System.out.println("Worker " + workerId + " is done");
                    } finally {
                        // Release the permit
                        semaphore.release();
                        System.out.println("Worker " + workerId + " released a permit");
                        System.out.println("Available permits: " + semaphore.availablePermits());
                    }
                } catch (InterruptedException e) {
                    System.out.println("Worker " + workerId + " interrupted");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Start the threads
        for (Thread worker : workers) {
            worker.start();
        }
        
        // Wait for all threads to finish
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate fair semaphore and tryAcquire
        System.out.println("\nFair semaphore and tryAcquire:");
        
        // Create a fair semaphore with 2 permits
        Semaphore fairSemaphore = new Semaphore(2, true);
        
        // Create threads
        Thread[] fairWorkers = new Thread[4];
        for (int i = 0; i < fairWorkers.length; i++) {
            final int workerId = i;
            fairWorkers[i] = new Thread(() -> {
                try {
                    System.out.println("Fair worker " + workerId + " is trying to acquire a permit");
                    
                    // Try to acquire a permit with timeout
                    boolean acquired = fairSemaphore.tryAcquire(1, TimeUnit.SECONDS);
                    
                    if (acquired) {
                        try {
                            System.out.println("Fair worker " + workerId + " acquired a permit");
                            System.out.println("Available permits: " + fairSemaphore.availablePermits());
                            
                            // Simulate work
                            Thread.sleep(2000);
                            
                            System.out.println("Fair worker " + workerId + " is done");
                        } finally {
                            // Release the permit
                            fairSemaphore.release();
                            System.out.println("Fair worker " + workerId + " released a permit");
                            System.out.println("Available permits: " + fairSemaphore.availablePermits());
                        }
                    } else {
                        System.out.println("Fair worker " + workerId + " could not acquire a permit within timeout");
                    }
                } catch (InterruptedException e) {
                    System.out.println("Fair worker " + workerId + " interrupted");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Start the threads
        for (Thread worker : fairWorkers) {
            worker.start();
        }
        
        // Wait for all threads to finish
        try {
            for (Thread worker : fairWorkers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate acquiring multiple permits
        System.out.println("\nAcquiring multiple permits:");
        
        // Create a semaphore with 5 permits
        Semaphore multiPermitSemaphore = new Semaphore(5);
        
        // Create threads that acquire different numbers of permits
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Thread 1 is trying to acquire 2 permits");
                
                // Acquire 2 permits
                multiPermitSemaphore.acquire(2);
                
                try {
                    System.out.println("Thread 1 acquired 2 permits");
                    System.out.println("Available permits: " + multiPermitSemaphore.availablePermits());
                    
                    // Simulate work
                    Thread.sleep(2000);
                    
                    System.out.println("Thread 1 is done");
                } finally {
                    // Release the permits
                    multiPermitSemaphore.release(2);
                    System.out.println("Thread 1 released 2 permits");
                    System.out.println("Available permits: " + multiPermitSemaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                System.out.println("Thread 1 interrupted");
                Thread.currentThread().interrupt();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Thread 2 is trying to acquire 3 permits");
                
                // Acquire 3 permits
                multiPermitSemaphore.acquire(3);
                
                try {
                    System.out.println("Thread 2 acquired 3 permits");
                    System.out.println("Available permits: " + multiPermitSemaphore.availablePermits());
                    
                    // Simulate work
                    Thread.sleep(3000);
                    
                    System.out.println("Thread 2 is done");
                } finally {
                    // Release the permits
                    multiPermitSemaphore.release(3);
                    System.out.println("Thread 2 released 3 permits");
                    System.out.println("Available permits: " + multiPermitSemaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                System.out.println("Thread 2 interrupted");
                Thread.currentThread().interrupt();
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
    }
    
    /**
     * Demonstrates the use of Phaser.
     */
    private static void phaserExample() {
        // Create a Phaser with 1 registered party (the main thread)
        Phaser phaser = new Phaser(1);
        
        System.out.println("Starting Phaser example with phase = " + phaser.getPhase() + 
                          " and registered parties = " + phaser.getRegisteredParties());
        
        // Create worker threads
        Thread[] workers = new Thread[3];
        for (int i = 0; i < workers.length; i++) {
            // Register a new party for each worker
            phaser.register();
            
            final int workerId = i;
            workers[i] = new Thread(() -> {
                try {
                    // Phase 0
                    System.out.println("Worker " + workerId + " starting phase " + phaser.getPhase());
                    
                    // Simulate work
                    Thread.sleep(1000 + (int)(Math.random() * 1000));
                    
                    System.out.println("Worker " + workerId + " completed phase " + phaser.getPhase());
                    
                    // Wait for all threads to complete the phase
                    int phase = phaser.arriveAndAwaitAdvance();
                    
                    // Phase 1
                    System.out.println("Worker " + workerId + " starting phase " + phaser.getPhase());
                    
                    // Simulate work
                    Thread.sleep(1000 + (int)(Math.random() * 1000));
                    
                    System.out.println("Worker " + workerId + " completed phase " + phaser.getPhase());
                    
                    // Wait for all threads to complete the phase
                    phase = phaser.arriveAndAwaitAdvance();
                    
                    // Phase 2
                    System.out.println("Worker " + workerId + " starting phase " + phaser.getPhase());
                    
                    // Simulate work
                    Thread.sleep(1000 + (int)(Math.random() * 1000));
                    
                    System.out.println("Worker " + workerId + " completed phase " + phaser.getPhase());
                    
                    // Deregister from the phaser
                    phaser.arriveAndDeregister();
                    
                    System.out.println("Worker " + workerId + " deregistered from the phaser");
                } catch (InterruptedException e) {
                    System.out.println("Worker " + workerId + " interrupted");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Start the threads
        for (Thread worker : workers) {
            worker.start();
        }
        
        // Wait for all threads to complete phase 0
        System.out.println("Main thread waiting for phase 0 to complete");
        int phase = phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + phase + " completed, moving to phase " + phaser.getPhase());
        
        // Wait for all threads to complete phase 1
        System.out.println("Main thread waiting for phase 1 to complete");
        phase = phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + phase + " completed, moving to phase " + phaser.getPhase());
        
        // Wait for all threads to complete phase 2
        System.out.println("Main thread waiting for phase 2 to complete");
        phase = phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + phase + " completed");
        
        // Deregister the main thread
        phaser.arriveAndDeregister();
        
        // Wait for all threads to finish
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Final phase: " + phaser.getPhase());
        System.out.println("Registered parties: " + phaser.getRegisteredParties());
        
        // Demonstrate Phaser with onAdvance
        System.out.println("\nPhaser with onAdvance:");
        
        // Create a Phaser with a custom onAdvance implementation
        Phaser customPhaser = new Phaser(1) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("onAdvance: phase = " + phase + ", registered parties = " + registeredParties);
                
                // Terminate after phase 2 or when all parties have deregistered
                return phase >= 2 || registeredParties == 0;
            }
        };
        
        // Create worker threads
        Thread[] customWorkers = new Thread[3];
        for (int i = 0; i < customWorkers.length; i++) {
            // Register a new party for each worker
            customPhaser.register();
            
            final int workerId = i;
            customWorkers[i] = new Thread(() -> {
                try {
                    for (int p = 0; !customPhaser.isTerminated(); p++) {
                        System.out.println("Custom worker " + workerId + " starting phase " + p);
                        
                        // Simulate work
                        Thread.sleep(500 + (int)(Math.random() * 500));
                        
                        System.out.println("Custom worker " + workerId + " completed phase " + p);
                        
                        // Wait for all threads to complete the phase
                        int nextPhase = customPhaser.arriveAndAwaitAdvance();
                        
                        if (nextPhase < 0) {
                            System.out.println("Custom worker " + workerId + ": Phaser is terminated");
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Custom worker " + workerId + " interrupted");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Start the threads
        for (Thread worker : customWorkers) {
            worker.start();
        }
        
        // Participate in the phases
        while (!customPhaser.isTerminated()) {
            System.out.println("Main thread participating in phase " + customPhaser.getPhase());
            
            // Arrive and await advance
            int nextPhase = customPhaser.arriveAndAwaitAdvance();
            
            if (nextPhase < 0) {
                System.out.println("Main thread: Phaser is terminated");
                break;
            }
        }
        
        // Wait for all threads to finish
        try {
            for (Thread worker : customWorkers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("Custom phaser is terminated: " + customPhaser.isTerminated());
    }
    
    /**
     * Demonstrates the use of Exchanger.
     */
    private static void exchangerExample() {
        // Create an Exchanger for exchanging data between two threads
        Exchanger<String> exchanger = new Exchanger<>();
        
        // Create a producer thread
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    // Produce data
                    String data = "Data-" + i;
                    System.out.println("Producer produced: " + data);
                    
                    // Exchange data with the consumer
                    System.out.println("Producer waiting to exchange...");
                    String received = exchanger.exchange(data);
                    
                    System.out.println("Producer received: " + received);
                    
                    // Sleep before next exchange
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted");
                Thread.currentThread().interrupt();
            }
        });
        
        // Create a consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    // Prepare response
                    String response = "Response-" + i;
                    
                    // Sleep to simulate different processing times
                    Thread.sleep(2000);
                    
                    // Exchange data with the producer
                    System.out.println("Consumer waiting to exchange...");
                    String received = exchanger.exchange(response);
                    
                    System.out.println("Consumer received: " + received);
                    System.out.println("Consumer sent: " + response);
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted");
                Thread.currentThread().interrupt();
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
        
        // Demonstrate Exchanger with timeout
        System.out.println("\nExchanger with timeout:");
        
        // Create a new Exchanger
        Exchanger<String> timeoutExchanger = new Exchanger<>();
        
        // Create a thread that exchanges with timeout
        Thread timeoutThread = new Thread(() -> {
            try {
                System.out.println("Timeout thread waiting to exchange with timeout...");
                
                // Try to exchange with a timeout
                String received = timeoutExchanger.exchange("Timeout data", 2, TimeUnit.SECONDS);
                
                System.out.println("Timeout thread received: " + received);
            } catch (InterruptedException e) {
                System.out.println("Timeout thread interrupted");
                Thread.currentThread().interrupt();
            } catch (java.util.concurrent.TimeoutException e) {
                System.out.println("Timeout thread: Exchange timed out");
            }
        });
        
        // Start the timeout thread
        timeoutThread.start();
        
        // Wait for the timeout thread to finish
        try {
            timeoutThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        // Demonstrate successful exchange with timeout
        System.out.println("\nSuccessful exchange with timeout:");
        
        // Create threads
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Thread 1 waiting to exchange with timeout...");
                
                // Try to exchange with a timeout
                String received = timeoutExchanger.exchange("Thread 1 data", 5, TimeUnit.SECONDS);
                
                System.out.println("Thread 1 received: " + received);
            } catch (InterruptedException e) {
                System.out.println("Thread 1 interrupted");
                Thread.currentThread().interrupt();
            } catch (java.util.concurrent.TimeoutException e) {
                System.out.println("Thread 1: Exchange timed out");
            }
        });
        
        Thread thread2 = new Thread(() -> {
            try {
                // Sleep before exchanging
                Thread.sleep(2000);
                
                System.out.println("Thread 2 exchanging...");
                
                // Exchange
                String received = timeoutExchanger.exchange("Thread 2 data");
                
                System.out.println("Thread 2 received: " + received);
            } catch (InterruptedException e) {
                System.out.println("Thread 2 interrupted");
                Thread.currentThread().interrupt();
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
    }
}
