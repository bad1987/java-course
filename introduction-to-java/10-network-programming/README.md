# Java Network Programming

Welcome to the tenth lesson in our Java programming course! In this section, you'll learn about network programming in Java. Network programming allows your applications to communicate over networks, enabling you to build distributed systems, client-server applications, and web services.

## 1. Networking Basics

Before diving into Java's networking APIs, let's review some fundamental networking concepts:

### IP Addresses

An IP (Internet Protocol) address is a numerical label assigned to each device connected to a computer network. IP addresses serve two main functions:

- **Host or Network Interface Identification**: Identifies a specific device on a network
- **Location Addressing**: Provides the location of the device in the network

There are two versions of IP addresses:

- **IPv4**: 32-bit address written in decimal format with four numbers separated by dots (e.g., `192.168.1.1`)
- **IPv6**: 128-bit address written in hexadecimal format separated by colons (e.g., `2001:0db8:85a3:0000:0000:8a2e:0370:7334`)

### Ports

A port is a communication endpoint identified by a 16-bit unsigned integer (0-65535). Ports allow a single device with a single IP address to run multiple services. Some well-known ports include:

- **Port 80**: HTTP (Web)
- **Port 443**: HTTPS (Secure Web)
- **Port 21**: FTP (File Transfer)
- **Port 25**: SMTP (Email)
- **Port 22**: SSH (Secure Shell)

### Protocols

Network protocols are sets of rules that govern how data is transmitted over a network. Common protocols include:

- **TCP (Transmission Control Protocol)**: Connection-oriented, reliable, ordered delivery
- **UDP (User Datagram Protocol)**: Connectionless, faster but unreliable
- **HTTP (Hypertext Transfer Protocol)**: Application protocol for web browsers
- **FTP (File Transfer Protocol)**: For transferring files
- **SMTP (Simple Mail Transfer Protocol)**: For sending email

### Client-Server Model

The client-server model is a distributed application structure where:

- **Server**: Provides resources, services, or functionality
- **Client**: Requests and consumes those resources or services

## 2. Java Networking API

Java provides a comprehensive set of classes for network programming in the `java.net` package. Here are the key classes:

### InetAddress

The `InetAddress` class represents an IP address:

```java
// Get the local host address
InetAddress localHost = InetAddress.getLocalHost();
System.out.println("Local Host: " + localHost.getHostName() + " (" + localHost.getHostAddress() + ")");

// Get address by name
InetAddress googleAddress = InetAddress.getByName("www.google.com");
System.out.println("Google: " + googleAddress.getHostAddress());

// Get all addresses for a host
InetAddress[] addresses = InetAddress.getAllByName("www.google.com");
for (InetAddress address : addresses) {
    System.out.println(address.getHostAddress());
}
```

### Socket and ServerSocket

These classes provide the client and server sides of a TCP connection:

#### Server Side:

```java
// Create a server socket on port 8080
ServerSocket serverSocket = new ServerSocket(8080);
System.out.println("Server started on port 8080");

// Wait for a client to connect
Socket clientSocket = serverSocket.accept();
System.out.println("Client connected: " + clientSocket.getInetAddress());

// Create input and output streams
BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

// Read from client
String message = in.readLine();
System.out.println("Received: " + message);

// Send response to client
out.println("Hello from server!");

// Close the connections
clientSocket.close();
serverSocket.close();
```

#### Client Side:

```java
// Connect to the server
Socket socket = new Socket("localhost", 8080);
System.out.println("Connected to server");

// Create input and output streams
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

// Send message to server
out.println("Hello from client!");

// Read response from server
String response = in.readLine();
System.out.println("Server says: " + response);

// Close the connection
socket.close();
```

### DatagramSocket and DatagramPacket

These classes are used for UDP communication:

#### UDP Server:

```java
// Create a UDP socket on port 9090
DatagramSocket socket = new DatagramSocket(9090);
byte[] buffer = new byte[1024];

// Create a packet for receiving data
DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

// Receive a packet (blocks until a packet is received)
socket.receive(packet);

// Process the packet
String message = new String(packet.getData(), 0, packet.getLength());
System.out.println("Received: " + message);

// Create a response packet
String response = "Hello from UDP server!";
byte[] responseData = response.getBytes();
DatagramPacket responsePacket = new DatagramPacket(
    responseData, 
    responseData.length, 
    packet.getAddress(), 
    packet.getPort()
);

// Send the response
socket.send(responsePacket);

// Close the socket
socket.close();
```

#### UDP Client:

```java
// Create a UDP socket
DatagramSocket socket = new DatagramSocket();

// Prepare the message
String message = "Hello from UDP client!";
byte[] data = message.getBytes();

// Create a packet for sending data
DatagramPacket packet = new DatagramPacket(
    data, 
    data.length, 
    InetAddress.getByName("localhost"), 
    9090
);

// Send the packet
socket.send(packet);

// Prepare to receive a response
byte[] buffer = new byte[1024];
DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);

// Receive the response
socket.receive(responsePacket);

// Process the response
String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
System.out.println("Server says: " + response);

// Close the socket
socket.close();
```

### URL and URLConnection

These classes provide a higher-level API for accessing resources over HTTP:

```java
// Create a URL
URL url = new URL("https://www.example.com");

// Open a connection
URLConnection connection = url.openConnection();

// Set properties (for HTTP connections)
if (connection instanceof HttpURLConnection) {
    HttpURLConnection httpConnection = (HttpURLConnection) connection;
    httpConnection.setRequestMethod("GET");
    httpConnection.setConnectTimeout(5000);
    httpConnection.setReadTimeout(5000);
}

// Read the response
try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}

// For HTTP connections, disconnect when done
if (connection instanceof HttpURLConnection) {
    ((HttpURLConnection) connection).disconnect();
}
```

## 3. Building a Simple Chat Application

Let's combine what we've learned to build a simple chat application with a server and multiple clients.

### Chat Server:

```java
public class ChatServer {
    private static final int PORT = 8080;
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    
    public static void main(String[] args) throws Exception {
        System.out.println("Chat Server started on port " + PORT);
        ServerSocket serverSocket = new ServerSocket(PORT);
        
        try {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } finally {
            serverSocket.close();
        }
    }
    
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Get client's name
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name != null && !name.isEmpty()) {
                        break;
                    }
                }
                
                // Welcome the new client
                out.println("NAMEACCEPTED " + name);
                broadcast(name + " has joined the chat");
                
                // Add this client's writer to the set
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }
                
                // Process messages from this client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equals(".bye")) {
                        break;
                    }
                    broadcast(name + ": " + message);
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // Client is leaving
                if (name != null) {
                    broadcast(name + " has left the chat");
                }
                
                // Remove client's writer from the set
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                
                // Close the socket
                try {
                    socket.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
        
        // Broadcast a message to all clients
        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println("MESSAGE " + message);
                }
            }
        }
    }
}
```

### Chat Client:

```java
public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    
    public ChatClient(String name) {
        this.name = name;
    }
    
    public void start() throws IOException {
        // Connect to the server
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        // Process messages from the server
        Thread readerThread = new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.startsWith("SUBMITNAME")) {
                        out.println(name);
                    } else if (line.startsWith("NAMEACCEPTED")) {
                        System.out.println("Connected to chat as " + name);
                    } else if (line.startsWith("MESSAGE")) {
                        System.out.println(line.substring(8));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e);
            }
        });
        readerThread.start();
        
        // Send messages from console to server
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter messages (type .bye to exit):");
        String message;
        while (scanner.hasNextLine()) {
            message = scanner.nextLine();
            out.println(message);
            if (message.equals(".bye")) {
                break;
            }
        }
        
        // Clean up
        scanner.close();
        socket.close();
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java ChatClient <username>");
            return;
        }
        
        ChatClient client = new ChatClient(args[0]);
        client.start();
    }
}
```

## 4. Working with HTTP

Java provides several ways to work with HTTP:

### HttpURLConnection

The traditional way to make HTTP requests:

```java
URL url = new URL("https://api.example.com/data");
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("GET");
connection.setRequestProperty("Accept", "application/json");

int responseCode = connection.getResponseCode();
System.out.println("Response Code: " + responseCode);

BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
String inputLine;
StringBuilder response = new StringBuilder();

while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();

System.out.println("Response: " + response.toString());
connection.disconnect();
```

### HttpClient (Java 11+)

Java 11 introduced a new HTTP client API that supports HTTP/2 and WebSocket:

```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.example.com/data"))
        .header("Accept", "application/json")
        .GET()
        .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println("Status Code: " + response.statusCode());
System.out.println("Response: " + response.body());
```

### Asynchronous Requests (Java 11+)

The new HttpClient also supports asynchronous requests:

```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.example.com/data"))
        .build();

CompletableFuture<HttpResponse<String>> futureResponse = 
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

futureResponse.thenApply(HttpResponse::body)
        .thenAccept(System.out::println)
        .join();
```

## 5. Working with JSON

When working with web APIs, you'll often need to parse and create JSON. Java doesn't have built-in JSON support, but there are several libraries available:

### Using Jackson

Jackson is a popular JSON library for Java:

```java
// Parse JSON
ObjectMapper mapper = new ObjectMapper();
String json = "{\"name\":\"John\",\"age\":30}";
Person person = mapper.readValue(json, Person.class);
System.out.println(person.getName() + " is " + person.getAge() + " years old");

// Create JSON
Person newPerson = new Person("Alice", 25);
String newJson = mapper.writeValueAsString(newPerson);
System.out.println(newJson);
```

### Using Gson

Gson is another popular JSON library developed by Google:

```java
// Parse JSON
Gson gson = new Gson();
String json = "{\"name\":\"John\",\"age\":30}";
Person person = gson.fromJson(json, Person.class);
System.out.println(person.getName() + " is " + person.getAge() + " years old");

// Create JSON
Person newPerson = new Person("Alice", 25);
String newJson = gson.toJson(newPerson);
System.out.println(newJson);
```

## 6. Network Programming Best Practices

### 1. Always Close Resources

Always close sockets, streams, and connections in a `finally` block or using try-with-resources:

```java
try (Socket socket = new Socket("localhost", 8080);
     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
    
    // Use the socket, in, and out
    
} catch (IOException e) {
    e.printStackTrace();
}
```

### 2. Handle Exceptions Properly

Network operations can fail for many reasons. Always handle exceptions properly:

```java
try {
    // Network operations
} catch (UnknownHostException e) {
    System.err.println("Unknown host: " + e.getMessage());
} catch (ConnectException e) {
    System.err.println("Connection refused: " + e.getMessage());
} catch (SocketTimeoutException e) {
    System.err.println("Connection timed out: " + e.getMessage());
} catch (IOException e) {
    System.err.println("I/O error: " + e.getMessage());
}
```

### 3. Set Timeouts

Always set timeouts to prevent your application from hanging indefinitely:

```java
Socket socket = new Socket();
socket.connect(new InetSocketAddress("example.com", 80), 5000); // 5 seconds connect timeout
socket.setSoTimeout(5000); // 5 seconds read timeout
```

### 4. Use Buffered I/O

Always use buffered streams for better performance:

```java
// Instead of:
InputStream in = socket.getInputStream();
OutputStream out = socket.getOutputStream();

// Use:
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
```

### 5. Consider Thread Safety

When multiple threads access shared resources, use proper synchronization:

```java
private static final Set<Socket> activeSockets = Collections.synchronizedSet(new HashSet<>());

// Adding a socket
synchronized (activeSockets) {
    activeSockets.add(socket);
}

// Removing a socket
synchronized (activeSockets) {
    activeSockets.remove(socket);
}
```

### 6. Use Non-Blocking I/O for High-Performance Applications

For applications that need to handle many connections, consider using non-blocking I/O with `java.nio`:

```java
Selector selector = Selector.open();
ServerSocketChannel serverChannel = ServerSocketChannel.open();
serverChannel.configureBlocking(false);
serverChannel.socket().bind(new InetSocketAddress(8080));
serverChannel.register(selector, SelectionKey.OP_ACCEPT);

while (true) {
    selector.select();
    Set<SelectionKey> selectedKeys = selector.selectedKeys();
    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
    
    while (keyIterator.hasNext()) {
        SelectionKey key = keyIterator.next();
        
        if (key.isAcceptable()) {
            // Accept a new connection
        } else if (key.isReadable()) {
            // Read from a connection
        }
        
        keyIterator.remove();
    }
}
```

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `InetAddressExample.java`: Demonstrates working with IP addresses
- `SimpleServer.java` and `SimpleClient.java`: Basic TCP client-server communication
- `UDPServer.java` and `UDPClient.java`: Basic UDP communication
- `URLConnectionExample.java`: Working with URLs and HTTP
- `HttpClientExample.java`: Using the new HttpClient API (Java 11+)
- `ChatServer.java` and `ChatClient.java`: A simple chat application
- `JSONExample.java`: Working with JSON data
- `NonBlockingServer.java`: A non-blocking server using NIO

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Build a simple HTTP server that serves static files
2. `Exercise2.java`: Create a multi-user chat application with private messaging
3. `Exercise3.java`: Implement a REST client that consumes a public API
4. `Exercise4.java`: Build a file transfer application using sockets

Complete these exercises to practice what you've learned. Network programming is a powerful skill that allows you to build distributed applications and web services!

## Next Steps

After completing this section, you have completed the Introduction to Java course! You now have a solid foundation in Java programming, from basic syntax to advanced topics like multithreading and network programming.

To continue your Java journey, consider exploring:

- Java Enterprise Edition (Jakarta EE) for building enterprise applications
- Spring Framework for building web applications
- Android development with Java
- JavaFX for desktop applications
- Advanced topics like reflection, annotations, and design patterns
