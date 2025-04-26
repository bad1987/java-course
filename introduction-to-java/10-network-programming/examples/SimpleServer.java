/**
 * SimpleServer.java
 * This program demonstrates a simple TCP server that listens for client connections.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Date;

public class SimpleServer {
    // Port to listen on
    private static final int PORT = 8080;
    
    public static void main(String[] args) {
        System.out.println("--- Simple TCP Server ---");
        
        // Try-with-resources to ensure the server socket is closed
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            System.out.println("Server IP address: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Waiting for clients...");
            
            // Server runs until manually terminated
            while (true) {
                // Accept client connection (blocks until a client connects)
                Socket clientSocket = serverSocket.accept();
                
                // Handle the client connection in a new thread
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Thread to handle client connections.
     */
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
        
        public void run() {
            try {
                // Get client information
                String clientAddress = clientSocket.getInetAddress().getHostAddress();
                int clientPort = clientSocket.getPort();
                
                System.out.println("New client connected: " + clientAddress + ":" + clientPort);
                
                // Set up input and output streams
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                // Send welcome message to client
                out.println("Welcome to the Simple TCP Server!");
                out.println("Connected at: " + new Date());
                out.println("Type 'bye' to disconnect");
                
                // Process client messages
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from " + clientAddress + ": " + inputLine);
                    
                    // Echo the message back to the client
                    out.println("Server echo: " + inputLine);
                    
                    // If client sends "bye", break the loop
                    if (inputLine.equalsIgnoreCase("bye")) {
                        out.println("Goodbye!");
                        break;
                    }
                    
                    // Special commands
                    if (inputLine.equalsIgnoreCase("time")) {
                        out.println("Current time: " + new Date());
                    } else if (inputLine.equalsIgnoreCase("info")) {
                        out.println("Server Info:");
                        out.println("- Server Address: " + InetAddress.getLocalHost().getHostAddress());
                        out.println("- Server Port: " + PORT);
                        out.println("- Client Address: " + clientAddress);
                        out.println("- Client Port: " + clientPort);
                    }
                }
                
                System.out.println("Client disconnected: " + clientAddress + ":" + clientPort);
            } catch (IOException e) {
                System.out.println("Client handler exception: " + e.getMessage());
            } finally {
                // Clean up resources
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error closing resources: " + e.getMessage());
                }
            }
        }
    }
}
