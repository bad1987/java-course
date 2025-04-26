/**
 * Exercise 4: Task Scheduler
 * 
 * Instructions:
 * 1. Create a Task class with the following attributes:
 *    - id (String)
 *    - name (String)
 *    - description (String)
 *    - priority (enum: HIGH, MEDIUM, LOW)
 *    - dueDate (LocalDateTime)
 *    - status (enum: TODO, IN_PROGRESS, DONE)
 *    - tags (Set of strings)
 * 
 * 2. Implement the following methods in the Task class:
 *    - Constructor to initialize all attributes
 *    - Getters and setters for all attributes
 *    - toString() method to display task information
 *    - equals() and hashCode() methods based on the task id
 *    - compareTo() method to compare tasks by due date
 * 
 * 3. Create a TaskScheduler class that uses a PriorityQueue to manage tasks and
 *    implements the following functionality:
 *    - addTask(Task task): Adds a task to the queue
 *    - getNextTask(): Retrieves and removes the highest priority task
 *    - peekNextTask(): Retrieves but does not remove the highest priority task
 *    - removeTask(String id): Removes a task with the given id
 *    - getAllTasks(): Returns a list of all tasks
 *    - getTasksByPriority(Priority priority): Returns tasks with the specified priority
 *    - getTasksByStatus(Status status): Returns tasks with the specified status
 *    - getTasksByTag(String tag): Returns tasks with the specified tag
 *    - getTasksDueToday(): Returns tasks due today
 *    - getTasksOverdue(): Returns tasks that are overdue
 * 
 * 4. Create a TaskComparator class that implements different comparison strategies:
 *    - ByPriority: Compares tasks by priority (HIGH > MEDIUM > LOW)
 *    - ByDueDate: Compares tasks by due date (earlier dates first)
 *    - ByStatus: Compares tasks by status (TODO > IN_PROGRESS > DONE)
 *    - Combined: Combines multiple comparators (e.g., priority then due date)
 * 
 * 5. Create a TaskManager class that:
 *    - Uses the TaskScheduler to manage tasks
 *    - Provides methods to add, update, and remove tasks
 *    - Supports different views of tasks (by priority, by status, by due date)
 *    - Implements task filtering and searching
 *    - Supports importing and exporting tasks from/to files
 * 
 * 6. Create a TaskSchedulerApp class with a main method that:
 *    - Creates a TaskManager
 *    - Adds sample tasks (or allows the user to create them)
 *    - Provides a menu-driven interface for the user to interact with the system
 *    - Implements features like task creation, updating, viewing, and scheduling
 * 
 * 7. Implement proper exception handling for all operations
 * 
 * 8. Use appropriate Queue methods and Java 8+ features (like streams, lambda expressions)
 *    where applicable
 */
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Exercise4 {
    public static void main(String[] args) {
        // TODO: Implement the TaskSchedulerApp
    }
}

// TODO: Implement the Priority enum

// TODO: Implement the Status enum

// TODO: Implement the Task class

// TODO: Implement the TaskComparator classes

// TODO: Implement the TaskScheduler class

// TODO: Implement the TaskManager class
