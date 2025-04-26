/**
 * URLConnectionExample.java
 * This program demonstrates working with URLs and HTTP connections.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLConnectionExample {
    public static void main(String[] args) {
        System.out.println("--- URL and URLConnection Examples ---");
        
        // Example 1: Basic URL operations
        System.out.println("\nExample 1: Basic URL operations");
        basicURLOperations();
        
        // Example 2: Reading content from a URL
        System.out.println("\nExample 2: Reading content from a URL");
        readFromURL("https://www.example.com");
        
        // Example 3: Working with HTTP headers
        System.out.println("\nExample 3: Working with HTTP headers");
        workWithHTTPHeaders("https://www.example.com");
        
        // Example 4: HTTP GET request
        System.out.println("\nExample 4: HTTP GET request");
        httpGetRequest("https://httpbin.org/get");
        
        // Example 5: HTTP POST request
        System.out.println("\nExample 5: HTTP POST request");
        httpPostRequest("https://httpbin.org/post", "name=John&age=30");
        
        // Example 6: Handling redirects
        System.out.println("\nExample 6: Handling redirects");
        handleRedirects("https://httpbin.org/redirect/1");
        
        // Example 7: Setting timeouts
        System.out.println("\nExample 7: Setting timeouts");
        setTimeouts("https://www.example.com");
    }
    
    /**
     * Demonstrates basic URL operations.
     */
    private static void basicURLOperations() {
        try {
            // Create a URL
            URL url = new URL("https://www.example.com:443/path/to/resource?param1=value1&param2=value2#section");
            
            // Get URL components
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Default Port: " + url.getDefaultPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + url.getQuery());
            System.out.println("Ref: " + url.getRef());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("User Info: " + url.getUserInfo());
            System.out.println("File: " + url.getFile());
            System.out.println("External Form: " + url.toExternalForm());
            
            // Create a URL from a base URL and a relative path
            URL baseURL = new URL("https://www.example.com/base/");
            URL relativeURL = new URL(baseURL, "relative/path");
            
            System.out.println("\nBase URL: " + baseURL);
            System.out.println("Relative URL: " + relativeURL);
            
            // Compare URLs
            URL url1 = new URL("https://www.example.com/path/");
            URL url2 = new URL("https://www.example.com/path/");
            URL url3 = new URL("https://www.example.com/different/path/");
            
            System.out.println("\nURL1 equals URL2: " + url1.equals(url2));
            System.out.println("URL1 equals URL3: " + url1.equals(url3));
            
            // URL encoding/decoding
            String encodedURL = java.net.URLEncoder.encode("param with spaces", "UTF-8");
            String decodedURL = java.net.URLDecoder.decode(encodedURL, "UTF-8");
            
            System.out.println("\nEncoded URL parameter: " + encodedURL);
            System.out.println("Decoded URL parameter: " + decodedURL);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading content from a URL.
     */
    private static void readFromURL(String urlString) {
        try {
            // Create a URL
            URL url = new URL(urlString);
            
            // Open a connection to the URL
            URLConnection connection = url.openConnection();
            
            // Set properties (optional)
            connection.setRequestProperty("User-Agent", "Java URLConnection Example");
            
            // Connect to the URL
            connection.connect();
            
            // Get content type
            String contentType = connection.getContentType();
            System.out.println("Content Type: " + contentType);
            
            // Get content length
            int contentLength = connection.getContentLength();
            System.out.println("Content Length: " + contentLength + " bytes");
            
            // Get last modified date
            long lastModified = connection.getLastModified();
            System.out.println("Last Modified: " + new java.util.Date(lastModified));
            
            // Read the content
            System.out.println("\nContent (first 500 characters):");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String line;
                int lineCount = 0;
                
                while ((line = reader.readLine()) != null && lineCount < 10) {
                    content.append(line).append("\n");
                    lineCount++;
                }
                
                // Truncate if too long
                String contentStr = content.toString();
                if (contentStr.length() > 500) {
                    contentStr = contentStr.substring(0, 500) + "...";
                }
                
                System.out.println(contentStr);
            }
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates working with HTTP headers.
     */
    private static void workWithHTTPHeaders(String urlString) {
        try {
            // Create a URL
            URL url = new URL(urlString);
            
            // Open a connection to the URL
            URLConnection connection = url.openConnection();
            
            // Set request headers
            connection.setRequestProperty("User-Agent", "Java URLConnection Example");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
            
            // Connect to the URL
            connection.connect();
            
            // Get response headers
            System.out.println("Response Headers:");
            Map<String, List<String>> headers = connection.getHeaderFields();
            
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                
                if (key != null) {
                    System.out.println(key + ": " + String.join(", ", values));
                }
            }
            
            // Get specific headers
            System.out.println("\nSpecific Headers:");
            System.out.println("Content-Type: " + connection.getHeaderField("Content-Type"));
            System.out.println("Server: " + connection.getHeaderField("Server"));
            System.out.println("Date: " + connection.getHeaderField("Date"));
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates making an HTTP GET request.
     */
    private static void httpGetRequest(String urlString) {
        try {
            // Create a URL
            URL url = new URL(urlString);
            
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set request method
            connection.setRequestMethod("GET");
            
            // Set request headers
            connection.setRequestProperty("User-Agent", "Java HttpURLConnection Example");
            
            // Get response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append("\n");
                    }
                    
                    // Truncate if too long
                    String responseStr = response.toString();
                    if (responseStr.length() > 500) {
                        responseStr = responseStr.substring(0, 500) + "...";
                    }
                    
                    System.out.println("Response (first 500 characters):");
                    System.out.println(responseStr);
                }
            } else {
                System.out.println("GET request failed");
            }
            
            // Disconnect
            connection.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates making an HTTP POST request.
     */
    private static void httpPostRequest(String urlString, String postData) {
        try {
            // Create a URL
            URL url = new URL(urlString);
            
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set request method
            connection.setRequestMethod("POST");
            
            // Set request headers
            connection.setRequestProperty("User-Agent", "Java HttpURLConnection Example");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            // Enable output (for POST)
            connection.setDoOutput(true);
            
            // Write the POST data
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            // Get response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append("\n");
                    }
                    
                    // Truncate if too long
                    String responseStr = response.toString();
                    if (responseStr.length() > 500) {
                        responseStr = responseStr.substring(0, 500) + "...";
                    }
                    
                    System.out.println("Response (first 500 characters):");
                    System.out.println(responseStr);
                }
            } else {
                System.out.println("POST request failed");
            }
            
            // Disconnect
            connection.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates handling HTTP redirects.
     */
    private static void handleRedirects(String urlString) {
        try {
            // Create a URL
            URL url = new URL(urlString);
            
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set request method
            connection.setRequestMethod("GET");
            
            // Set request headers
            connection.setRequestProperty("User-Agent", "Java HttpURLConnection Example");
            
            // Enable following redirects (default is true)
            connection.setInstanceFollowRedirects(true);
            
            // Get response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Get the final URL after redirects
            URL finalURL = connection.getURL();
            System.out.println("Final URL: " + finalURL);
            
            // Read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    int lineCount = 0;
                    
                    while ((line = reader.readLine()) != null && lineCount < 5) {
                        response.append(line).append("\n");
                        lineCount++;
                    }
                    
                    System.out.println("Response (first 5 lines):");
                    System.out.println(response.toString());
                }
            } else {
                System.out.println("Request failed");
            }
            
            // Disconnect
            connection.disconnect();
            
            // Disable following redirects
            System.out.println("\nDisabling automatic redirect following:");
            
            HttpURLConnection noRedirectConnection = (HttpURLConnection) url.openConnection();
            noRedirectConnection.setRequestMethod("GET");
            noRedirectConnection.setInstanceFollowRedirects(false);
            
            // Get response code
            int noRedirectResponseCode = noRedirectConnection.getResponseCode();
            System.out.println("Response Code: " + noRedirectResponseCode);
            
            // Check if it's a redirect
            if (noRedirectResponseCode == HttpURLConnection.HTTP_MOVED_TEMP || 
                noRedirectResponseCode == HttpURLConnection.HTTP_MOVED_PERM || 
                noRedirectResponseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                
                // Get the redirect URL
                String redirectURL = noRedirectConnection.getHeaderField("Location");
                System.out.println("Redirect URL: " + redirectURL);
            }
            
            // Disconnect
            noRedirectConnection.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates setting timeouts for URL connections.
     */
    private static void setTimeouts(String urlString) {
        try {
            // Create a URL
            URL url = new URL(urlString);
            
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set timeouts
            connection.setConnectTimeout(5000); // 5 seconds connect timeout
            connection.setReadTimeout(5000);    // 5 seconds read timeout
            
            // Set request method
            connection.setRequestMethod("GET");
            
            // Start timing
            long startTime = System.currentTimeMillis();
            
            // Connect to the URL
            connection.connect();
            
            // Get response code
            int responseCode = connection.getResponseCode();
            
            // Calculate elapsed time
            long elapsedTime = System.currentTimeMillis() - startTime;
            
            System.out.println("Response Code: " + responseCode);
            System.out.println("Elapsed Time: " + elapsedTime + " ms");
            
            // Disconnect
            connection.disconnect();
            
            // Try with a very short timeout (likely to fail)
            System.out.println("\nTrying with a very short timeout (10 ms):");
            
            HttpURLConnection shortTimeoutConnection = (HttpURLConnection) url.openConnection();
            shortTimeoutConnection.setConnectTimeout(10); // 10 milliseconds connect timeout
            
            try {
                shortTimeoutConnection.connect();
                System.out.println("Connected successfully (unexpected)");
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("Connection timed out as expected: " + e.getMessage());
            } finally {
                shortTimeoutConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
