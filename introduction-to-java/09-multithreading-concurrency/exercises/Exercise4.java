/**
 * Exercise 4: Concurrent Web Crawler
 * 
 * Instructions:
 * 1. Implement a concurrent web crawler that crawls web pages in parallel.
 * 
 * 2. The web crawler should:
 *    a. Start with a given URL
 *    b. Download and parse the page
 *    c. Extract links to other pages
 *    d. Follow the links up to a specified depth
 *    e. Process multiple pages concurrently
 * 
 * 3. The crawler should avoid:
 *    a. Visiting the same URL twice
 *    b. Exceeding the maximum depth
 *    c. Crawling external domains (optional)
 * 
 * 4. Implement the crawler using:
 *    a. A thread pool for concurrent downloads
 *    b. A thread-safe set to track visited URLs
 *    c. A thread-safe queue to manage URLs to be crawled
 *    d. Proper synchronization mechanisms
 * 
 * 5. For each page, the crawler should:
 *    a. Extract the title
 *    b. Count the number of links
 *    c. Save the content to a file (optional)
 * 
 * 6. The program should print:
 *    a. The crawling progress
 *    b. Statistics about the crawled pages
 *    c. Time taken to complete the crawl
 * 
 * 7. Bonus: Implement a polite crawler that respects robots.txt and adds delays between requests.
 * 
 * Note: You'll need to use a library for HTTP requests and HTML parsing, such as jsoup.
 * You can add the jsoup dependency to your project or use the provided JAR file.
 */
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class Exercise4 {
    public static void main(String[] args) {
        System.out.println("Concurrent Web Crawler Exercise");
        System.out.println("===============================");
        
        // TODO: Get the starting URL and maximum depth from the command line or use defaults
        String startUrl = "https://example.com";
        int maxDepth = 2;
        
        // TODO: Create a web crawler
        
        // TODO: Start the crawl and measure the time
        
        // TODO: Print the results
    }
}

/**
 * TODO: Implement WebCrawler
 */
class WebCrawler {
    // TODO: Implement this class
}

/**
 * TODO: Implement CrawlTask
 */
class CrawlTask implements Runnable {
    // TODO: Implement this class
}

/**
 * TODO: Implement PageResult
 */
class PageResult {
    // TODO: Implement this class
}

/**
 * TODO: Implement CrawlStatistics
 */
class CrawlStatistics {
    // TODO: Implement this class
}

/**
 * TODO: Bonus - Implement RobotsTxtParser
 */
class RobotsTxtParser {
    // TODO: Implement this class
}
