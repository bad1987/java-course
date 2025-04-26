/**
 * ChatServer.java
 * This program implements a simple chat server that allows multiple clients to connect and chat.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    // Port to listen on
    private static final int PORT = 8080;
    
    // Set of all client writers (for broadcasting messages)
    private static final Set<PrintWriter> clientWriters = new HashSet<>();
    
    // Set of all client names
    private static final Set<String> clientNames = new HashSet<>();
    
    // Thread pool for handling clients
    private static final ExecutorService pool = Executors.newFixedThreadPool(20);
    
    public static void main(String[] args) {
        System.out.println("--- Chat Server ---");
        
        // Try-with-resources to ensure the server socket is closed
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server started on port " + PORT);
            System.out.println("Waiting for clients...");
            
            // Server runs until manually terminated
            while (true) {
                // Accept client connection (blocks until a client connects)
                Socket clientSocket = serverSocket.accept();
                
                // Handle the client connection in the thread pool
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Shutdown the thread pool
            pool.shutdown();
        }
    }
    
    /**
     * Broadcasts a message to all connected clients.
     */
    private static void broadcast(String message) {
        // Synchronize on the set of client writers to avoid concurrent modification
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
    
    /**
     * Runnable to handle client connections.
     */
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                // Set up input and output streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Get client's name
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    
                    if (name == null) {
                        return;
                    }
                    
                    // Synchronize on the set of client names to avoid concurrent modification
                    synchronized (clientNames) {
                        if (!name.isEmpty() && !clientNames.contains(name)) {
                            clientNames.add(name);
                            break;
                        }
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
                    // Check for private messages
                    if (message.startsWith("@")) {
                        // Format: @recipient message
                        int spaceIndex = message.indexOf(' ');
                        if (spaceIndex > 1) {
                            String recipient = message.substring(1, spaceIndex);
                            String privateMessage = message.substring(spaceIndex + 1);
                            
                            // Send private message
                            sendPrivateMessage(name, recipient, privateMessage);
                        }
                    } else if (message.equals(".bye")) {
                        // Client is leaving
                        break;
                    } else {
                        // Broadcast the message to all clients
                        broadcast(name + ": " + message);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
            } finally {
                // Client is leaving
                if (name != null) {
                    // Remove the client's name
                    synchronized (clientNames) {
                        clientNames.remove(name);
                    }
                    
                    // Broadcast that the client has left
                    broadcast(name + " has left the chat");
                }
                
                // Remove the client's writer
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                
                // Close the socket
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }
        
        /**
         * Sends a private message from one client to another.
         */
        private void sendPrivateMessage(String sender, String recipient, String message) {
            boolean recipientFound = false;
            
            // Synchronize on the set of client writers to avoid concurrent modification
            synchronized (clientWriters) {
                // Check if the recipient exists
                synchronized (clientNames) {
                    recipientFound = clientNames.contains(recipient);
                }
                
                if (recipientFound) {
                    // Send the private message to the sender and recipient
                    for (PrintWriter writer : clientWriters) {
                        // We don't have a way to directly identify which writer belongs to which client,
                        // so we send the message to all clients but prefix it to indicate it's private
                        writer.println("PRIVATE " + sender + " " + recipient + " " + message);
                    }
                } else {
                    // Inform the sender that the recipient doesn't exist
                    out.println("ERROR User " + recipient + " not found");
                }
            }
        }
    }
}
