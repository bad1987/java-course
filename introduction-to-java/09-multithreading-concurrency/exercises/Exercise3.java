/**
 * Exercise 3: Parallel File Processor
 * 
 * Instructions:
 * 1. Implement a parallel file processor that processes multiple files concurrently.
 * 
 * 2. The file processor should:
 *    a. Take a directory path as input
 *    b. Find all text files in the directory (recursively)
 *    c. Process each file in parallel using a thread pool
 *    d. Collect and aggregate the results
 * 
 * 3. For each file, the processor should:
 *    a. Count the number of lines
 *    b. Count the number of words
 *    c. Count the number of characters
 *    d. Find the most frequent word
 * 
 * 4. Implement the file processor using the Executor framework:
 *    a. Use ExecutorService to create a thread pool
 *    b. Use Callable to process each file and return a result
 *    c. Use Future to collect the results
 * 
 * 5. Implement a FileProcessorResult class to hold the results for each file.
 * 
 * 6. Implement an AggregateResult class to hold the aggregated results for all files.
 * 
 * 7. The program should print:
 *    a. Results for each file
 *    b. Aggregated results for all files
 *    c. Time taken to process all files
 * 
 * 8. Bonus: Implement a version using the Fork/Join framework and compare its performance
 *    with the ExecutorService implementation.
 */
import java.io.File;
import java.util.concurrent.ExecutorService;

public class Exercise3 {
    public static void main(String[] args) {
        System.out.println("Parallel File Processor Exercise");
        System.out.println("================================");
        
        // TODO: Get the directory path from the command line or use a default
        String directoryPath = ".";
        
        // TODO: Create a file processor
        
        // TODO: Process the files and measure the time
        
        // TODO: Print the results
        
        // TODO: Bonus - Implement and test a Fork/Join version
    }
}

/**
 * TODO: Implement FileProcessor
 */
class FileProcessor {
    // TODO: Implement this class
}

/**
 * TODO: Implement FileProcessorTask
 */
class FileProcessorTask implements Callable<FileProcessorResult> {
    // TODO: Implement this class
}

/**
 * TODO: Implement FileProcessorResult
 */
class FileProcessorResult {
    // TODO: Implement this class
}

/**
 * TODO: Implement AggregateResult
 */
class AggregateResult {
    // TODO: Implement this class
}

/**
 * TODO: Bonus - Implement ForkJoinFileProcessor
 */
class ForkJoinFileProcessor {
    // TODO: Implement this class
}
