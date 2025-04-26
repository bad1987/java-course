/**
 * HttpClientExample.java
 * This program demonstrates using the HttpClient API introduced in Java 11.
 * Note: This example requires Java 11 or higher to run.
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HttpClientExample {
    public static void main(String[] args) {
        System.out.println("--- HttpClient Examples (Java 11+) ---");
        
        // Check if running on Java 11 or higher
        double javaVersion = Double.parseDouble(System.getProperty("java.specification.version"));
        if (javaVersion < 11) {
            System.out.println("This example requires Java 11 or higher to run.");
            System.out.println("Current Java version: " + System.getProperty("java.version"));
            return;
        }
        
        // Example 1: Simple GET request
        System.out.println("\nExample 1: Simple GET request");
        simpleGetRequest();
        
        // Example 2: GET request with headers
        System.out.println("\nExample 2: GET request with headers");
        getRequestWithHeaders();
        
        // Example 3: POST request
        System.out.println("\nExample 3: POST request");
        postRequest();
        
        // Example 4: Asynchronous requests
        System.out.println("\nExample 4: Asynchronous requests");
        asynchronousRequests();
        
        // Example 5: Handling redirects
        System.out.println("\nExample 5: Handling redirects");
        handlingRedirects();
        
        // Example 6: Setting timeouts
        System.out.println("\nExample 6: Setting timeouts");
        settingTimeouts();
        
        // Example 7: Multiple requests
        System.out.println("\nExample 7: Multiple requests");
        multipleRequests();
    }
    
    /**
     * Demonstrates a simple GET request using HttpClient.
     */
    private static void simpleGetRequest() {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();
            
            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/get"))
                    .GET() // GET is the default
                    .build();
            
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Print the response status and body
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:");
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates a GET request with headers.
     */
    private static void getRequestWithHeaders() {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();
            
            // Create an HttpRequest with headers
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/headers"))
                    .header("User-Agent", "Java HttpClient Example")
                    .header("X-Custom-Header", "Custom Value")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Print the response status and body
            System.out.println("Status Code: " + response.statusCode());
            
            // Print response headers
            System.out.println("Response Headers:");
            Map<String, List<String>> headers = response.headers().map();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
            }
            
            System.out.println("Response Body:");
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates a POST request.
     */
    private static void postRequest() {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();
            
            // Create the request body
            String requestBody = "name=John&age=30";
            
            // Create an HttpRequest for POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/post"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Print the response status and body
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:");
            System.out.println(response.body());
            
            // POST with JSON
            System.out.println("\nPOST with JSON:");
            
            String jsonBody = "{\"name\":\"John\",\"age\":30}";
            
            HttpRequest jsonRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/post"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            
            HttpResponse<String> jsonResponse = client.send(jsonRequest, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status Code: " + jsonResponse.statusCode());
            System.out.println("Response Body:");
            System.out.println(jsonResponse.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates asynchronous requests.
     */
    private static void asynchronousRequests() {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();
            
            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/delay/2")) // This endpoint delays the response
                    .GET()
                    .build();
            
            System.out.println("Sending asynchronous request...");
            
            // Send the request asynchronously
            CompletableFuture<HttpResponse<String>> futureResponse = 
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            
            // Do other work while waiting for the response
            System.out.println("Request sent asynchronously. Doing other work...");
            for (int i = 0; i < 5; i++) {
                System.out.println("Working... " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            // Process the response when it's ready
            futureResponse.thenAccept(response -> {
                System.out.println("Response received!");
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Response Body (first 100 chars):");
                String body = response.body();
                System.out.println(body.length() > 100 ? body.substring(0, 100) + "..." : body);
            });
            
            // Wait for the future to complete
            futureResponse.join();
            
            // Chain operations with CompletableFuture
            System.out.println("\nChaining operations with CompletableFuture:");
            
            HttpRequest anotherRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/json"))
                    .GET()
                    .build();
            
            client.sendAsync(anotherRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        System.out.println("Response status code: " + response.statusCode());
                        return response.body();
                    })
                    .thenApply(body -> {
                        System.out.println("Processing the body...");
                        // Just a simple processing example
                        return body.contains("slideshow") ? "Contains slideshow data" : "No slideshow data";
                    })
                    .thenAccept(result -> {
                        System.out.println("Processing result: " + result);
                    })
                    .join(); // Wait for the chain to complete
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates handling redirects.
     */
    private static void handlingRedirects() {
        try {
            // Create an HttpClient with redirect policy
            HttpClient clientFollowingRedirects = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL) // This is the default
                    .build();
            
            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/redirect/2")) // This will redirect twice
                    .GET()
                    .build();
            
            System.out.println("Sending request with redirect following enabled...");
            
            // Send the request
            HttpResponse<String> response = clientFollowingRedirects.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Print the response
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Final URI: " + response.uri());
            System.out.println("Response Body (first 100 chars):");
            String body = response.body();
            System.out.println(body.length() > 100 ? body.substring(0, 100) + "..." : body);
            
            // Create an HttpClient that doesn't follow redirects
            HttpClient clientNotFollowingRedirects = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NEVER)
                    .build();
            
            System.out.println("\nSending request with redirect following disabled...");
            
            // Send the request
            HttpResponse<String> noRedirectResponse = 
                    clientNotFollowingRedirects.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Print the response
            System.out.println("Status Code: " + noRedirectResponse.statusCode());
            System.out.println("Location Header: " + noRedirectResponse.headers().firstValue("location").orElse("None"));
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates setting timeouts.
     */
    private static void settingTimeouts() {
        try {
            // Create an HttpClient with timeouts
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5)) // 5 seconds connect timeout
                    .build();
            
            // Create an HttpRequest with a timeout
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/delay/1")) // This endpoint delays the response by 1 second
                    .timeout(Duration.ofSeconds(2)) // 2 seconds request timeout
                    .GET()
                    .build();
            
            System.out.println("Sending request with 2 second timeout to endpoint with 1 second delay...");
            
            // Send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Print the response
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response received successfully");
            
            // Create a request with a timeout that will be exceeded
            HttpRequest timeoutRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/delay/3")) // This endpoint delays the response by 3 seconds
                    .timeout(Duration.ofSeconds(1)) // 1 second request timeout
                    .GET()
                    .build();
            
            System.out.println("\nSending request with 1 second timeout to endpoint with 3 second delay...");
            
            try {
                // Send the request (should timeout)
                client.send(timeoutRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response received (unexpected)");
            } catch (java.net.http.HttpTimeoutException e) {
                System.out.println("Request timed out as expected: " + e.getMessage());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates sending multiple requests.
     */
    private static void multipleRequests() {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();
            
            // Create multiple requests
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/get?param=1"))
                    .GET()
                    .build();
            
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/get?param=2"))
                    .GET()
                    .build();
            
            HttpRequest request3 = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/get?param=3"))
                    .GET()
                    .build();
            
            // Send requests sequentially
            System.out.println("Sending requests sequentially...");
            long startTime = System.currentTimeMillis();
            
            HttpResponse<String> response1 = client.send(request1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response 1 Status: " + response1.statusCode());
            
            HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response 2 Status: " + response2.statusCode());
            
            HttpResponse<String> response3 = client.send(request3, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response 3 Status: " + response3.statusCode());
            
            long endTime = System.currentTimeMillis();
            System.out.println("Sequential requests took " + (endTime - startTime) + " ms");
            
            // Send requests in parallel
            System.out.println("\nSending requests in parallel...");
            startTime = System.currentTimeMillis();
            
            CompletableFuture<HttpResponse<String>> futureResponse1 = 
                    client.sendAsync(request1, HttpResponse.BodyHandlers.ofString());
            
            CompletableFuture<HttpResponse<String>> futureResponse2 = 
                    client.sendAsync(request2, HttpResponse.BodyHandlers.ofString());
            
            CompletableFuture<HttpResponse<String>> futureResponse3 = 
                    client.sendAsync(request3, HttpResponse.BodyHandlers.ofString());
            
            // Wait for all requests to complete
            CompletableFuture<Void> allOf = CompletableFuture.allOf(
                    futureResponse1, futureResponse2, futureResponse3);
            
            // Wait for all requests to complete
            allOf.join();
            
            System.out.println("Response 1 Status: " + futureResponse1.get().statusCode());
            System.out.println("Response 2 Status: " + futureResponse2.get().statusCode());
            System.out.println("Response 3 Status: " + futureResponse3.get().statusCode());
            
            endTime = System.currentTimeMillis();
            System.out.println("Parallel requests took " + (endTime - startTime) + " ms");
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
