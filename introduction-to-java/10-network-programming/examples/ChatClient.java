/**
 * ChatClient.java
 * This program implements a simple chat client that connects to the chat server.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    // Default server address and port
    private static final String DEFAULT_SERVER_ADDRESS = "localhost";
    private static final int DEFAULT_SERVER_PORT = 8080;
    
    // Socket for the connection
    private Socket socket;
    
    // Input and output streams
    private BufferedReader in;
    private PrintWriter out;
    
    // Client's name
    private String name;
    
    /**
     * Constructs a new ChatClient with the given name.
     */
    public ChatClient(String name) {
        this.name = name;
    }
    
    /**
     * Connects to the server and starts the client.
     */
    public void start(String serverAddress, int serverPort) {
        try {
            // Connect to the server
            System.out.println("Connecting to server at " + serverAddress + ":" + serverPort + "...");
            socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server!");
            
            // Set up input and output streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            // Start a thread to read messages from the server
            Thread readerThread = new Thread(new ServerListener());
            readerThread.setDaemon(true); // Set as daemon so it doesn't prevent program exit
            readerThread.start();
            
            // Read user input and send to server
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type messages to send (type '.bye' to exit):");
            System.out.println("For private messages, use format: @recipient message");
            
            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                
                // Send the message to the server
                out.println(userInput);
                
                // If user types ".bye", exit the loop
                if (userInput.equals(".bye")) {
                    break;
                }
            }
            
            // Clean up resources
            System.out.println("Disconnecting from server...");
            scanner.close();
            socket.close();
            
            System.out.println("Disconnected from server.");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + serverAddress);
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
    
    /**
     * Thread to listen for messages from the server.
     */
    private class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    // Process messages from the server
                    if (serverMessage.startsWith("SUBMITNAME")) {
                        // Server is asking for our name
                        out.println(name);
                    } else if (serverMessage.startsWith("NAMEACCEPTED")) {
                        // Our name has been accepted
                        System.out.println("Connected to chat as " + name);
                    } else if (serverMessage.startsWith("PRIVATE")) {
                        // Private message format: PRIVATE sender recipient message
                        String[] parts = serverMessage.split(" ", 4);
                        if (parts.length >= 4) {
                            String sender = parts[1];
                            String recipient = parts[2];
                            String message = parts[3];
                            
                            // Only display the message if we're the sender or recipient
                            if (name.equals(sender) || name.equals(recipient)) {
                                if (name.equals(sender)) {
                                    System.out.println("(Private to " + recipient + "): " + message);
                                } else {
                                    System.out.println("(Private from " + sender + "): " + message);
                                }
                            }
                        }
                    } else if (serverMessage.startsWith("ERROR")) {
                        // Error message from the server
                        System.out.println(serverMessage);
                    } else {
                        // Regular message
                        System.out.println(serverMessage);
                    }
                }
            } catch (IOException e) {
                if (!socket.isClosed()) {
                    System.out.println("Error reading from server: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Main method to start the client.
     */
    public static void main(String[] args) {
        // Parse command line arguments
        String name = null;
        String serverAddress = DEFAULT_SERVER_ADDRESS;
        int serverPort = DEFAULT_SERVER_PORT;
        
        // Get the client's name
        if (args.length >= 1) {
            name = args[0];
        }
        
        // Get the server address
        if (args.length >= 2) {
            serverAddress = args[1];
        }
        
        // Get the server port
        if (args.length >= 3) {
            try {
                serverPort = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number. Using default port " + DEFAULT_SERVER_PORT);
            }
        }
        
        // If no name was provided, ask for one
        if (name == null || name.isEmpty()) {
            System.out.println("Please enter your name:");
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Exiting.");
                scanner.close();
                return;
            }
        }
        
        // Create and start the client
        ChatClient client = new ChatClient(name);
        client.start(serverAddress, serverPort);
    }
}
