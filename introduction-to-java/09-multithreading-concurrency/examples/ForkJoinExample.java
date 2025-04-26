/**
 * ForkJoinExample.java
 * This program demonstrates the use of the Fork/Join framework in Java.
 */
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {
    public static void main(String[] args) {
        System.out.println("--- Fork/Join Framework Examples ---");
        
        // Example 1: RecursiveTask for computing sum
        System.out.println("\nExample 1: RecursiveTask for computing sum");
        recursiveTaskExample();
        
        // Example 2: RecursiveAction for array sorting
        System.out.println("\nExample 2: RecursiveAction for array sorting");
        recursiveActionExample();
        
        // Example 3: Custom ForkJoinPool
        System.out.println("\nExample 3: Custom ForkJoinPool");
        customPoolExample();
        
        // Example 4: Fibonacci calculation
        System.out.println("\nExample 4: Fibonacci calculation");
        fibonacciExample();
        
        // Example 5: Performance comparison
        System.out.println("\nExample 5: Performance comparison");
        performanceComparisonExample();
        
        System.out.println("\nMain thread exiting.");
    }
    
    /**
     * Demonstrates the use of RecursiveTask for computing the sum of an array.
     */
    private static void recursiveTaskExample() {
        // Create a large array
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        
        // Create a ForkJoinPool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        
        // Create a task to compute the sum
        SumTask task = new SumTask(array, 0, array.length);
        
        // Execute the task
        long sum = pool.invoke(task);
        
        System.out.println("Sum: " + sum);
        
        // Verify the result
        long expectedSum = 0;
        for (int value : array) {
            expectedSum += value;
        }
        
        System.out.println("Expected sum: " + expectedSum);
        System.out.println("Result is correct: " + (sum == expectedSum));
    }
    
    /**
     * A RecursiveTask that computes the sum of an array.
     */
    private static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10000;
        private final int[] array;
        private final int start;
        private final int end;
        
        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Long compute() {
            int length = end - start;
            
            if (length <= THRESHOLD) {
                // Base case: compute directly
                return computeDirectly();
            } else {
                // Recursive case: fork and join
                int mid = start + length / 2;
                
                // Create subtasks
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);
                
                // Fork the left task
                leftTask.fork();
                
                // Compute the right task
                long rightResult = rightTask.compute();
                
                // Join the left task
                long leftResult = leftTask.join();
                
                // Combine the results
                return leftResult + rightResult;
            }
        }
        
        private long computeDirectly() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }
    }
    
    /**
     * Demonstrates the use of RecursiveAction for sorting an array.
     */
    private static void recursiveActionExample() {
        // Create an array to sort
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 1000000);
        }
        
        // Create a copy for verification
        int[] copy = Arrays.copyOf(array, array.length);
        
        // Create a ForkJoinPool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        
        // Create a task to sort the array
        MergeSortTask task = new MergeSortTask(array, 0, array.length, new int[array.length]);
        
        // Execute the task
        long startTime = System.nanoTime();
        pool.invoke(task);
        long endTime = System.nanoTime();
        
        System.out.println("Parallel merge sort time: " + (endTime - startTime) / 1000000 + " ms");
        
        // Verify the result
        Arrays.sort(copy);
        boolean correct = Arrays.equals(array, copy);
        
        System.out.println("Result is correct: " + correct);
    }
    
    /**
     * A RecursiveAction that sorts an array using merge sort.
     */
    private static class MergeSortTask extends RecursiveAction {
        private static final int THRESHOLD = 10000;
        private final int[] array;
        private final int start;
        private final int end;
        private final int[] temp;
        
        public MergeSortTask(int[] array, int start, int end, int[] temp) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.temp = temp;
        }
        
        @Override
        protected void compute() {
            int length = end - start;
            
            if (length <= THRESHOLD) {
                // Base case: sort directly
                Arrays.sort(array, start, end);
            } else {
                // Recursive case: fork and join
                int mid = start + length / 2;
                
                // Create subtasks
                MergeSortTask leftTask = new MergeSortTask(array, start, mid, temp);
                MergeSortTask rightTask = new MergeSortTask(array, mid, end, temp);
                
                // Execute the subtasks
                invokeAll(leftTask, rightTask);
                
                // Merge the results
                merge(start, mid, end);
            }
        }
        
        private void merge(int start, int mid, int end) {
            // Copy the array to the temp array
            System.arraycopy(array, start, temp, start, end - start);
            
            // Merge the two sorted subarrays
            int i = start;
            int j = mid;
            int k = start;
            
            while (i < mid && j < end) {
                if (temp[i] <= temp[j]) {
                    array[k++] = temp[i++];
                } else {
                    array[k++] = temp[j++];
                }
            }
            
            // Copy the remaining elements
            while (i < mid) {
                array[k++] = temp[i++];
            }
            
            // No need to copy the remaining elements from the right subarray
            // as they are already in the correct position
        }
    }
    
    /**
     * Demonstrates the use of a custom ForkJoinPool.
     */
    private static void customPoolExample() {
        // Create a custom ForkJoinPool with 4 threads
        ForkJoinPool customPool = new ForkJoinPool(4);
        
        try {
            // Create a large array
            int[] array = new int[1000000];
            for (int i = 0; i < array.length; i++) {
                array[i] = i + 1;
            }
            
            // Create a task to compute the sum
            SumTask task = new SumTask(array, 0, array.length);
            
            // Execute the task in the custom pool
            long sum = customPool.invoke(task);
            
            System.out.println("Sum computed with custom pool: " + sum);
            
            // Get pool information
            System.out.println("Pool parallelism: " + customPool.getParallelism());
            System.out.println("Pool size: " + customPool.getPoolSize());
            System.out.println("Active thread count: " + customPool.getActiveThreadCount());
            System.out.println("Steal count: " + customPool.getStealCount());
            
            // Execute multiple tasks
            System.out.println("\nExecuting multiple tasks:");
            
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                customPool.execute(() -> {
                    try {
                        System.out.println("Task " + taskId + " started");
                        Thread.sleep(1000);
                        System.out.println("Task " + taskId + " completed");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Wait for all tasks to complete
            customPool.awaitQuiescence(10, java.util.concurrent.TimeUnit.SECONDS);
            
            System.out.println("All tasks completed");
        } finally {
            // Shutdown the custom pool
            customPool.shutdown();
        }
    }
    
    /**
     * Demonstrates computing Fibonacci numbers using the Fork/Join framework.
     */
    private static void fibonacciExample() {
        // Create a ForkJoinPool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        
        // Compute Fibonacci numbers
        for (int n = 10; n <= 40; n += 10) {
            // Create a task to compute the nth Fibonacci number
            FibonacciTask task = new FibonacciTask(n);
            
            // Execute the task
            long startTime = System.nanoTime();
            long result = pool.invoke(task);
            long endTime = System.nanoTime();
            
            System.out.println("Fibonacci(" + n + ") = " + result + 
                              " (computed in " + (endTime - startTime) / 1000000 + " ms)");
        }
    }
    
    /**
     * A RecursiveTask that computes the nth Fibonacci number.
     */
    private static class FibonacciTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10;
        private final int n;
        
        public FibonacciTask(int n) {
            this.n = n;
        }
        
        @Override
        protected Long compute() {
            if (n <= THRESHOLD) {
                // Base case: compute directly
                return computeDirectly();
            } else {
                // Recursive case: fork and join
                FibonacciTask f1 = new FibonacciTask(n - 1);
                FibonacciTask f2 = new FibonacciTask(n - 2);
                
                // Fork f1
                f1.fork();
                
                // Compute f2
                long f2Result = f2.compute();
                
                // Join f1
                long f1Result = f1.join();
                
                // Combine the results
                return f1Result + f2Result;
            }
        }
        
        private long computeDirectly() {
            if (n <= 1) {
                return n;
            } else {
                long fib1 = 0;
                long fib2 = 1;
                long fibN = 0;
                
                for (int i = 2; i <= n; i++) {
                    fibN = fib1 + fib2;
                    fib1 = fib2;
                    fib2 = fibN;
                }
                
                return fibN;
            }
        }
    }
    
    /**
     * Compares the performance of sequential and parallel execution.
     */
    private static void performanceComparisonExample() {
        // Create a large array
        int size = 100000000;
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random();
        }
        
        // Sequential sum
        long startTime = System.nanoTime();
        double sequentialSum = 0;
        for (double value : array) {
            sequentialSum += value;
        }
        long endTime = System.nanoTime();
        
        System.out.println("Sequential sum: " + sequentialSum);
        System.out.println("Sequential time: " + (endTime - startTime) / 1000000 + " ms");
        
        // Parallel sum using Fork/Join
        ForkJoinPool pool = ForkJoinPool.commonPool();
        
        startTime = System.nanoTime();
        DoubleArraySumTask task = new DoubleArraySumTask(array, 0, array.length);
        double parallelSum = pool.invoke(task);
        endTime = System.nanoTime();
        
        System.out.println("Parallel sum: " + parallelSum);
        System.out.println("Parallel time: " + (endTime - startTime) / 1000000 + " ms");
        
        // Parallel sum using streams (Java 8+)
        startTime = System.nanoTime();
        double streamSum = Arrays.stream(array).parallel().sum();
        endTime = System.nanoTime();
        
        System.out.println("Stream sum: " + streamSum);
        System.out.println("Stream time: " + (endTime - startTime) / 1000000 + " ms");
    }
    
    /**
     * A RecursiveTask that computes the sum of a double array.
     */
    private static class DoubleArraySumTask extends RecursiveTask<Double> {
        private static final int THRESHOLD = 100000;
        private final double[] array;
        private final int start;
        private final int end;
        
        public DoubleArraySumTask(double[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Double compute() {
            int length = end - start;
            
            if (length <= THRESHOLD) {
                // Base case: compute directly
                return computeDirectly();
            } else {
                // Recursive case: fork and join
                int mid = start + length / 2;
                
                // Create subtasks
                DoubleArraySumTask leftTask = new DoubleArraySumTask(array, start, mid);
                DoubleArraySumTask rightTask = new DoubleArraySumTask(array, mid, end);
                
                // Fork the left task
                leftTask.fork();
                
                // Compute the right task
                double rightResult = rightTask.compute();
                
                // Join the left task
                double leftResult = leftTask.join();
                
                // Combine the results
                return leftResult + rightResult;
            }
        }
        
        private double computeDirectly() {
            double sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }
    }
}
