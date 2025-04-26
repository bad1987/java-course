/**
 * Exercise 1: Simple HTTP Server
 * 
 * Instructions:
 * 1. Implement a simple HTTP server that serves static files from a directory.
 * 
 * 2. The server should:
 *    a. Listen on port 8080 (or another port specified as a command-line argument)
 *    b. Accept HTTP GET requests
 *    c. Serve files from a specified directory (default: current directory)
 *    d. Return appropriate HTTP status codes (200, 404, etc.)
 *    e. Set appropriate Content-Type headers based on file extensions
 * 
 * 3. The server should handle the following file types at minimum:
 *    - HTML (.html, .htm)
 *    - CSS (.css)
 *    - JavaScript (.js)
 *    - Images (.jpg, .jpeg, .png, .gif)
 *    - Text (.txt)
 * 
 * 4. Implement basic request logging that shows:
 *    - Client IP address
 *    - Request method and path
 *    - Response status code
 *    - Timestamp
 * 
 * 5. Bonus: Implement directory listing for directories without an index.html file.
 * 
 * 6. Bonus: Add support for HTTP HEAD requests.
 * 
 * 7. Bonus: Implement simple caching using the If-Modified-Since header.
 * 
 * Example usage:
 * java SimpleHTTPServer [port] [directory]
 */
public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("Simple HTTP Server Exercise");
        System.out.println("==========================");
        
        // TODO: Parse command-line arguments for port and directory
        
        // TODO: Implement the HTTP server
        
        // TODO: Start the server and handle requests
    }
}

/**
 * TODO: Implement SimpleHTTPServer
 */
class SimpleHTTPServer {
    // TODO: Implement this class
}

/**
 * TODO: Implement HTTPRequest
 */
class HTTPRequest {
    // TODO: Implement this class
}

/**
 * TODO: Implement HTTPResponse
 */
class HTTPResponse {
    // TODO: Implement this class
}

/**
 * TODO: Implement RequestHandler
 */
class RequestHandler implements Runnable {
    // TODO: Implement this class
}

/**
 * TODO: Implement ContentTypeMapper
 */
class ContentTypeMapper {
    // TODO: Implement this class
}
