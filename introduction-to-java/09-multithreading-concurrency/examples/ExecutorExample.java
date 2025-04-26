/**
 * ExecutorExample.java
 * This program demonstrates the use of the Executor framework in Java.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorExample {
    public static void main(String[] args) {
        System.out.println("--- Executor Framework Examples ---");
        
        // Example 1: Basic Executor usage
        System.out.println("\nExample 1: Basic Executor usage");
        basicExecutorExample();
        
        // Example 2: Different types of Executors
        System.out.println("\nExample 2: Different types of Executors");
        executorTypesExample();
        
        // Example 3: Callable and Future
        System.out.println("\nExample 3: Callable and Future");
        callableFutureExample();
        
        // Example 4: Scheduled tasks
        System.out.println("\nExample 4: Scheduled tasks");
        scheduledTasksExample();
        
        // Example 5: CompletableFuture
        System.out.println("\nExample 5: CompletableFuture");
        completableFutureExample();
        
        // Example 6: Custom ThreadFactory
        System.out.println("\nExample 6: Custom ThreadFactory");
        customThreadFactoryExample();
        
        // Example 7: Thread pools with work queues
        System.out.println("\nExample 7: Thread pools with work queues");
        workQueueExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates basic usage of the Executor framework.
     */
    private static void basicExecutorExample() {
        // Create an ExecutorService with a fixed thread pool of 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        try {
            // Submit 5 tasks to the executor
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Task " + taskId + " executed by " + threadName);
                    
                    // Simulate some work
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Task " + taskId + " was interrupted.");
                    }
                    
                    System.out.println("Task " + taskId + " completed.");
                    return null;
                });
            }
        } finally {
            // Shutdown the executor
            System.out.println("Shutting down executor...");
            executor.shutdown();
            
            try {
                // Wait for all tasks to complete or timeout
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Executor did not terminate in the specified time.");
                    // Cancel currently executing tasks
                    executor.shutdownNow();
                    // Wait again for tasks to respond to being cancelled
                    if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                        System.out.println("Executor did not terminate.");
                    }
                }
            } catch (InterruptedException e) {
                // (Re-)Cancel if current thread also interrupted
                executor.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("All tasks completed.");
    }
    
    /**
     * Demonstrates different types of Executors.
     */
    private static void executorTypesExample() {
        // 1. Fixed thread pool
        System.out.println("Fixed thread pool example:");
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        
        try {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                fixedPool.execute(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Fixed pool task " + taskId + " executed by " + threadName);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Wait for tasks to complete
            fixedPool.shutdown();
            fixedPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (!fixedPool.isTerminated()) {
                fixedPool.shutdownNow();
            }
        }
        
        // 2. Cached thread pool
        System.out.println("\nCached thread pool example:");
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        
        try {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                cachedPool.execute(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Cached pool task " + taskId + " executed by " + threadName);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Wait for tasks to complete
            cachedPool.shutdown();
            cachedPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (!cachedPool.isTerminated()) {
                cachedPool.shutdownNow();
            }
        }
        
        // 3. Single thread executor
        System.out.println("\nSingle thread executor example:");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        
        try {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                singleThreadExecutor.execute(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Single thread task " + taskId + " executed by " + threadName);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Wait for tasks to complete
            singleThreadExecutor.shutdown();
            singleThreadExecutor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (!singleThreadExecutor.isTerminated()) {
                singleThreadExecutor.shutdownNow();
            }
        }
        
        // 4. Work stealing pool (Java 8+)
        System.out.println("\nWork stealing pool example:");
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        
        try {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                workStealingPool.execute(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Work stealing pool task " + taskId + " executed by " + threadName);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Wait for tasks to complete
            workStealingPool.shutdown();
            workStealingPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (!workStealingPool.isTerminated()) {
                workStealingPool.shutdownNow();
            }
        }
    }
    
    /**
     * Demonstrates the use of Callable and Future.
     */
    private static void callableFutureExample() {
        // Create an ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        try {
            // Create a list to hold the Future objects
            List<Future<Integer>> futures = new ArrayList<>();
            
            // Submit Callable tasks
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                Future<Integer> future = executor.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        String threadName = Thread.currentThread().getName();
                        System.out.println("Callable task " + taskId + " executed by " + threadName);
                        
                        // Simulate some work
                        Thread.sleep(1000);
                        
                        // Return a result
                        return taskId * 10;
                    }
                });
                
                futures.add(future);
            }
            
            // Process the results as they become available
            for (int i = 0; i < futures.size(); i++) {
                try {
                    // Get the result (blocks until the task completes)
                    Integer result = futures.get(i).get();
                    System.out.println("Result of task " + i + ": " + result);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Error getting result: " + e.getMessage());
                }
            }
            
            // Example of Future methods
            Future<String> future = executor.submit(() -> {
                Thread.sleep(2000);
                return "Future result";
            });
            
            System.out.println("Future isDone: " + future.isDone());
            System.out.println("Future isCancelled: " + future.isCancelled());
            
            try {
                // Get with timeout
                String result = future.get(3, TimeUnit.SECONDS);
                System.out.println("Future result: " + result);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error getting future result: " + e.getMessage());
            } catch (TimeoutException e) {
                System.out.println("Timeout waiting for future result");
                future.cancel(true);
            }
            
            System.out.println("Future isDone after get: " + future.isDone());
            
            // Example of cancelling a future
            Future<String> cancelledFuture = executor.submit(() -> {
                Thread.sleep(10000);  // Long-running task
                return "This should not be returned";
            });
            
            // Cancel the future
            boolean cancelled = cancelledFuture.cancel(true);
            System.out.println("Future cancelled: " + cancelled);
            System.out.println("Future isCancelled: " + cancelledFuture.isCancelled());
            System.out.println("Future isDone: " + cancelledFuture.isDone());
            
            try {
                String result = cancelledFuture.get();
                System.out.println("This should not be printed: " + result);
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting for result");
            } catch (ExecutionException e) {
                System.out.println("Execution exception: " + e.getMessage());
            } catch (CancellationException e) {
                System.out.println("Future was cancelled: " + e.getMessage());
            }
        } finally {
            // Shutdown the executor
            executor.shutdown();
            try {
                executor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Demonstrates scheduled tasks using ScheduledExecutorService.
     */
    private static void scheduledTasksExample() {
        // Create a ScheduledExecutorService
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        
        try {
            // Schedule a one-time task to run after a delay
            System.out.println("Scheduling a one-time task...");
            ScheduledFuture<?> oneTimeTask = scheduler.schedule(() -> {
                System.out.println("One-time task executed at: " + System.currentTimeMillis());
                return "Task completed";
            }, 2, TimeUnit.SECONDS);
            
            // Schedule a task to run repeatedly with a fixed delay
            System.out.println("Scheduling a fixed-delay task...");
            ScheduledFuture<?> fixedDelayTask = scheduler.scheduleWithFixedDelay(() -> {
                System.out.println("Fixed-delay task executed at: " + System.currentTimeMillis());
                try {
                    Thread.sleep(500);  // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, 1, 2, TimeUnit.SECONDS);
            
            // Schedule a task to run repeatedly at a fixed rate
            System.out.println("Scheduling a fixed-rate task...");
            ScheduledFuture<?> fixedRateTask = scheduler.scheduleAtFixedRate(() -> {
                System.out.println("Fixed-rate task executed at: " + System.currentTimeMillis());
                try {
                    Thread.sleep(500);  // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, 1, 2, TimeUnit.SECONDS);
            
            // Wait for the one-time task to complete
            try {
                String result = (String) oneTimeTask.get();
                System.out.println("One-time task result: " + result);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error getting one-time task result: " + e.getMessage());
            }
            
            // Let the periodic tasks run for a while
            Thread.sleep(10000);
            
            // Cancel the periodic tasks
            fixedDelayTask.cancel(false);
            fixedRateTask.cancel(false);
            
            System.out.println("Periodic tasks cancelled.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Shutdown the scheduler
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Demonstrates the use of CompletableFuture (Java 8+).
     */
    private static void completableFutureExample() {
        // Create a CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Hello";
        });
        
        // Chain operations
        CompletableFuture<String> transformedFuture = future
            .thenApply(s -> {
                System.out.println("Applying transformation to: " + s);
                return s + " World";
            })
            .thenApply(s -> {
                System.out.println("Applying another transformation to: " + s);
                return s + "!";
            });
        
        // Get the result
        try {
            String result = transformedFuture.get();
            System.out.println("Final result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error getting result: " + e.getMessage());
        }
        
        // Combining two CompletableFutures
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Hello";
        });
        
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "World";
        });
        
        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2);
        
        try {
            String result = combinedFuture.get();
            System.out.println("Combined result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error getting combined result: " + e.getMessage());
        }
        
        // Handling exceptions
        CompletableFuture<String> failingFuture = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Deliberate failure");
            }
            return "This will not be returned";
        });
        
        CompletableFuture<String> recoveredFuture = failingFuture.exceptionally(ex -> {
            System.out.println("Recovered from: " + ex.getMessage());
            return "Recovered value";
        });
        
        try {
            String result = recoveredFuture.get();
            System.out.println("Recovered result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error getting recovered result: " + e.getMessage());
        }
        
        // Composing CompletableFutures
        CompletableFuture<String> composedFuture = CompletableFuture
            .supplyAsync(() -> "Step 1")
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " -> Step 2"));
        
        try {
            String result = composedFuture.get();
            System.out.println("Composed result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error getting composed result: " + e.getMessage());
        }
        
        // Running tasks when all futures complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task 1 completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }),
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("Task 2 completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }),
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1500);
                    System.out.println("Task 3 completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            })
        );
        
        try {
            allOf.get();
            System.out.println("All tasks completed");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error waiting for all tasks: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates the use of a custom ThreadFactory.
     */
    private static void customThreadFactoryExample() {
        // Create a custom ThreadFactory
        ThreadFactory customThreadFactory = new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "CustomThread-" + threadNumber.getAndIncrement());
                thread.setDaemon(false);
                thread.setPriority(Thread.NORM_PRIORITY);
                
                // Add an uncaught exception handler
                thread.setUncaughtExceptionHandler((t, e) -> {
                    System.out.println("Uncaught exception in thread " + t.getName() + ": " + e.getMessage());
                });
                
                return thread;
            }
        };
        
        // Create an ExecutorService with the custom ThreadFactory
        ExecutorService executor = Executors.newFixedThreadPool(3, customThreadFactory);
        
        try {
            // Submit tasks
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Task " + taskId + " executed by " + threadName);
                    
                    // Simulate some work
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    
                    // Task 3 will throw an exception
                    if (taskId == 3) {
                        throw new RuntimeException("Deliberate exception in task " + taskId);
                    }
                    
                    return null;
                });
            }
        } finally {
            // Shutdown the executor
            executor.shutdown();
            try {
                executor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Demonstrates thread pools with different work queue types.
     */
    private static void workQueueExample() {
        // 1. Fixed thread pool with unbounded LinkedBlockingQueue (default)
        ThreadPoolExecutor defaultExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println("Default queue type: " + defaultExecutor.getQueue().getClass().getSimpleName());
        defaultExecutor.shutdown();
        
        // 2. Thread pool with ArrayBlockingQueue (bounded)
        ThreadPoolExecutor arrayQueueExecutor = new ThreadPoolExecutor(
            2, 4,  // Core and max pool size
            60, TimeUnit.SECONDS,  // Keep-alive time
            new ArrayBlockingQueue<>(10)  // Bounded queue with capacity 10
        );
        
        System.out.println("Array queue type: " + arrayQueueExecutor.getQueue().getClass().getSimpleName());
        
        try {
            // Submit more tasks than the queue can hold
            for (int i = 0; i < 15; i++) {
                final int taskId = i;
                try {
                    arrayQueueExecutor.submit(() -> {
                        String threadName = Thread.currentThread().getName();
                        System.out.println("ArrayQueue task " + taskId + " executed by " + threadName);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        return null;
                    });
                    System.out.println("Submitted task " + taskId);
                } catch (RejectedExecutionException e) {
                    System.out.println("Task " + taskId + " was rejected: " + e.getMessage());
                }
            }
        } finally {
            // Shutdown the executor
            arrayQueueExecutor.shutdown();
            try {
                arrayQueueExecutor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // 3. Thread pool with SynchronousQueue (direct handoff)
        ThreadPoolExecutor syncQueueExecutor = new ThreadPoolExecutor(
            2, 8,  // Core and max pool size
            60, TimeUnit.SECONDS,  // Keep-alive time
            new SynchronousQueue<>()  // Direct handoff
        );
        
        System.out.println("\nSynchronous queue type: " + syncQueueExecutor.getQueue().getClass().getSimpleName());
        
        try {
            // Submit tasks
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                try {
                    syncQueueExecutor.submit(() -> {
                        String threadName = Thread.currentThread().getName();
                        System.out.println("SyncQueue task " + taskId + " executed by " + threadName);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        return null;
                    });
                    System.out.println("Submitted task " + taskId);
                } catch (RejectedExecutionException e) {
                    System.out.println("Task " + taskId + " was rejected: " + e.getMessage());
                }
            }
            
            // Print pool statistics
            System.out.println("\nPool size: " + syncQueueExecutor.getPoolSize());
            System.out.println("Active threads: " + syncQueueExecutor.getActiveCount());
            System.out.println("Task count: " + syncQueueExecutor.getTaskCount());
            System.out.println("Completed tasks: " + syncQueueExecutor.getCompletedTaskCount());
        } finally {
            // Shutdown the executor
            syncQueueExecutor.shutdown();
            try {
                syncQueueExecutor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // 4. Thread pool with custom rejection handler
        RejectedExecutionHandler customHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("Custom handler: Task rejected, executing in the caller's thread");
                // Execute the task in the caller's thread
                r.run();
            }
        };
        
        ThreadPoolExecutor customRejectionExecutor = new ThreadPoolExecutor(
            1, 2,  // Core and max pool size
            60, TimeUnit.SECONDS,  // Keep-alive time
            new ArrayBlockingQueue<>(2),  // Small bounded queue
            customHandler  // Custom rejection handler
        );
        
        System.out.println("\nCustom rejection handler example:");
        
        try {
            // Submit more tasks than the queue and pool can handle
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                customRejectionExecutor.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Custom rejection task " + taskId + " executed by " + threadName);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return null;
                });
                System.out.println("Submitted task " + taskId);
            }
        } finally {
            // Shutdown the executor
            customRejectionExecutor.shutdown();
            try {
                customRejectionExecutor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
