/**
 * SimpleClient.java
 * This program demonstrates a simple TCP client that connects to a server.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.InetAddress;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {
    // Default server address and port
    private static final String DEFAULT_SERVER_ADDRESS = "localhost";
    private static final int DEFAULT_SERVER_PORT = 8080;
    
    public static void main(String[] args) {
        System.out.println("--- Simple TCP Client ---");
        
        // Parse command line arguments for server address and port
        String serverAddress = DEFAULT_SERVER_ADDRESS;
        int serverPort = DEFAULT_SERVER_PORT;
        
        if (args.length >= 1) {
            serverAddress = args[0];
        }
        
        if (args.length >= 2) {
            try {
                serverPort = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number. Using default port " + DEFAULT_SERVER_PORT);
            }
        }
        
        // Try to connect to the server
        try {
            System.out.println("Connecting to server at " + serverAddress + ":" + serverPort + "...");
            
            // Create a socket to connect to the server
            Socket socket = new Socket(serverAddress, serverPort);
            
            System.out.println("Connected to server!");
            System.out.println("Local port: " + socket.getLocalPort());
            
            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Create a thread to read messages from the server
            Thread serverListener = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        System.out.println("Error reading from server: " + e.getMessage());
                    }
                }
            });
            
            // Start the server listener thread
            serverListener.start();
            
            // Read user input and send to server
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type messages to send to the server (type 'bye' to exit):");
            
            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                
                // Send the message to the server
                out.println(userInput);
                
                // If user types "bye", exit the loop
                if (userInput.equalsIgnoreCase("bye")) {
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
        } catch (ConnectException e) {
            System.out.println("Connection refused. Make sure the server is running.");
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
