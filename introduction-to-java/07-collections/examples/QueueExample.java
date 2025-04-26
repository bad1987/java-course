/**
 * QueueExample.java
 * This program demonstrates the use of Queue and Deque implementations in Java.
 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Comparator;

public class QueueExample {
    public static void main(String[] args) {
        System.out.println("--- Queue and Deque Examples ---");
        
        // Example 1: Basic Queue Operations
        System.out.println("\nExample 1: Basic Queue Operations");
        basicQueueOperations();
        
        // Example 2: PriorityQueue
        System.out.println("\nExample 2: PriorityQueue");
        priorityQueueExample();
        
        // Example 3: Custom Priority Queue
        System.out.println("\nExample 3: Custom Priority Queue");
        customPriorityQueueExample();
        
        // Example 4: Basic Deque Operations
        System.out.println("\nExample 4: Basic Deque Operations");
        basicDequeOperations();
        
        // Example 5: Using Deque as a Stack
        System.out.println("\nExample 5: Using Deque as a Stack");
        dequeAsStack();
        
        // Example 6: Real-world Queue Example
        System.out.println("\nExample 6: Real-world Queue Example");
        printQueueSimulation();
    }
    
    /**
     * Demonstrates basic Queue operations using LinkedList.
     */
    public static void basicQueueOperations() {
        // Creating a Queue using LinkedList implementation
        Queue<String> queue = new LinkedList<>();
        
        // Adding elements to the queue (enqueue)
        queue.add("First");
        queue.add("Second");
        queue.add("Third");
        
        System.out.println("Queue: " + queue);
        
        // Examining the head of the queue without removing it
        String head = queue.peek();
        System.out.println("Head of queue: " + head);
        
        // Removing and returning the head of the queue (dequeue)
        String removed = queue.poll();
        System.out.println("Removed from queue: " + removed);
        System.out.println("Queue after poll: " + queue);
        
        // Adding more elements
        queue.offer("Fourth");  // offer is similar to add but preferred for capacity-restricted queues
        System.out.println("Queue after offer: " + queue);
        
        // Size of the queue
        System.out.println("Queue size: " + queue.size());
        
        // Checking if the queue contains an element
        boolean containsSecond = queue.contains("Second");
        System.out.println("Queue contains 'Second': " + containsSecond);
        
        // Removing a specific element
        boolean removed2 = queue.remove("Second");
        System.out.println("Removed 'Second': " + removed2);
        System.out.println("Queue after removing 'Second': " + queue);
        
        // Processing all elements in the queue
        System.out.println("Processing all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Processing: " + queue.poll());
        }
        
        System.out.println("Queue is empty: " + queue.isEmpty());
    }
    
    /**
     * Demonstrates PriorityQueue with natural ordering.
     */
    public static void priorityQueueExample() {
        // Creating a PriorityQueue (min-heap by default)
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        
        // Adding elements
        priorityQueue.add(30);
        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(5);
        
        System.out.println("PriorityQueue: " + priorityQueue);
        // Note: The toString() output may not show elements in priority order
        
        // Examining the head (minimum element)
        System.out.println("Head of priority queue: " + priorityQueue.peek());
        
        // Removing elements (in priority order)
        System.out.println("Removing elements in priority order:");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
        
        // Creating a max-heap using a custom comparator
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        
        // Adding elements
        maxHeap.add(30);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(5);
        
        System.out.println("\nMax-Heap PriorityQueue:");
        // Removing elements (in reverse priority order)
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
    
    /**
     * Demonstrates PriorityQueue with custom objects and comparator.
     */
    public static void customPriorityQueueExample() {
        // Creating a PriorityQueue with a custom comparator
        Queue<Task> taskQueue = new PriorityQueue<>(new TaskPriorityComparator());
        
        // Adding tasks with different priorities
        taskQueue.add(new Task("Check email", 3));
        taskQueue.add(new Task("Fix critical bug", 1));
        taskQueue.add(new Task("Prepare presentation", 2));
        taskQueue.add(new Task("Update documentation", 4));
        
        System.out.println("Task queue size: " + taskQueue.size());
        
        // Processing tasks in priority order
        System.out.println("Processing tasks in priority order:");
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            System.out.println("Processing: " + task);
        }
        
        // Alternative: Using a lambda expression for the comparator
        Queue<Task> taskQueue2 = new PriorityQueue<>((t1, t2) -> t1.getPriority() - t2.getPriority());
        
        // Adding tasks
        taskQueue2.add(new Task("Check email", 3));
        taskQueue2.add(new Task("Fix critical bug", 1));
        taskQueue2.add(new Task("Prepare presentation", 2));
        
        System.out.println("\nAlternative task queue (using lambda):");
        while (!taskQueue2.isEmpty()) {
            System.out.println(taskQueue2.poll());
        }
    }
    
    /**
     * Demonstrates basic Deque operations using ArrayDeque.
     */
    public static void basicDequeOperations() {
        // Creating a Deque using ArrayDeque implementation
        Deque<String> deque = new ArrayDeque<>();
        
        // Adding elements to both ends
        deque.addFirst("First");
        deque.addLast("Last");
        
        System.out.println("Deque: " + deque);
        
        // Adding more elements
        deque.offerFirst("New First");
        deque.offerLast("New Last");
        
        System.out.println("Deque after offers: " + deque);
        
        // Examining elements at both ends without removing
        String first = deque.peekFirst();
        String last = deque.peekLast();
        
        System.out.println("First element: " + first);
        System.out.println("Last element: " + last);
        
        // Removing elements from both ends
        String removedFirst = deque.pollFirst();
        String removedLast = deque.pollLast();
        
        System.out.println("Removed from front: " + removedFirst);
        System.out.println("Removed from back: " + removedLast);
        System.out.println("Deque after polls: " + deque);
        
        // Size of the deque
        System.out.println("Deque size: " + deque.size());
        
        // Adding more elements
        deque.add("Middle");  // Adds to the end (equivalent to addLast)
        System.out.println("Deque after add: " + deque);
        
        // Removing the first occurrence of an element
        boolean removed = deque.removeFirstOccurrence("First");
        System.out.println("Removed 'First': " + removed);
        System.out.println("Deque after remove: " + deque);
        
        // Clearing the deque
        deque.clear();
        System.out.println("Deque after clear: " + deque);
        System.out.println("Deque is empty: " + deque.isEmpty());
    }
    
    /**
     * Demonstrates using a Deque as a Stack.
     */
    public static void dequeAsStack() {
        // Creating a Deque to use as a stack
        Deque<String> stack = new ArrayDeque<>();
        
        // Pushing elements onto the stack
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");
        
        System.out.println("Stack: " + stack);
        
        // Peeking at the top element
        String top = stack.peek();
        System.out.println("Top element: " + top);
        
        // Popping elements from the stack
        System.out.println("Popping elements:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        
        System.out.println("Stack is empty: " + stack.isEmpty());
        
        // Note: push() is equivalent to addFirst()
        // pop() is equivalent to removeFirst()
        // peek() is equivalent to peekFirst()
    }
    
    /**
     * Demonstrates a real-world example of using a Queue for a print queue simulation.
     */
    public static void printQueueSimulation() {
        // Creating a print queue
        Queue<PrintJob> printQueue = new LinkedList<>();
        
        // Adding print jobs to the queue
        printQueue.add(new PrintJob("Report.pdf", 5));
        printQueue.add(new PrintJob("Letter.doc", 2));
        printQueue.add(new PrintJob("Image.jpg", 8));
        printQueue.add(new PrintJob("Presentation.ppt", 10));
        
        System.out.println("Print queue size: " + printQueue.size());
        
        // Simulating a printer processing jobs
        System.out.println("Printer processing jobs:");
        int totalPages = 0;
        
        while (!printQueue.isEmpty()) {
            PrintJob job = printQueue.poll();
            System.out.println("Printing: " + job);
            
            // Simulate printing time (1 second per page)
            try {
                System.out.println("Printing " + job.getPages() + " pages...");
                // In a real application, we would use Thread.sleep() here
                // Thread.sleep(job.getPages() * 1000);
            } catch (Exception e) {
                System.out.println("Printing interrupted: " + e.getMessage());
            }
            
            totalPages += job.getPages();
            System.out.println("Finished printing: " + job.getFilename());
        }
        
        System.out.println("All jobs completed. Total pages printed: " + totalPages);
    }
}

/**
 * A Task class for the PriorityQueue example.
 */
class Task {
    private String name;
    private int priority;  // Lower number = higher priority
    
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPriority() {
        return priority;
    }
    
    @Override
    public String toString() {
        return "Task{name='" + name + "', priority=" + priority + "}";
    }
}

/**
 * A comparator for Task objects based on priority.
 */
class TaskPriorityComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        // Compare tasks by priority (lower number = higher priority)
        return t1.getPriority() - t2.getPriority();
    }
}

/**
 * A PrintJob class for the print queue simulation.
 */
class PrintJob {
    private String filename;
    private int pages;
    
    public PrintJob(String filename, int pages) {
        this.filename = filename;
        this.pages = pages;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public int getPages() {
        return pages;
    }
    
    @Override
    public String toString() {
        return "PrintJob{filename='" + filename + "', pages=" + pages + "}";
    }
}
