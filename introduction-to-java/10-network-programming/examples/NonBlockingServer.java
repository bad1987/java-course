/**
 * NonBlockingServer.java
 * This program demonstrates a non-blocking server using Java NIO.
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingServer {
    // Port to listen on
    private static final int PORT = 8080;
    
    // Buffer size
    private static final int BUFFER_SIZE = 1024;
    
    // Client counter
    private static final AtomicInteger clientCounter = new AtomicInteger(0);
    
    // Map to store client information
    private static final ConcurrentHashMap<SocketChannel, ClientInfo> clients = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("--- Non-Blocking Server ---");
        
        try {
            // Create a selector
            Selector selector = Selector.open();
            
            // Create a server socket channel
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            
            // Configure the server socket channel to be non-blocking
            serverChannel.configureBlocking(false);
            
            // Bind the server socket channel to the port
            serverChannel.socket().bind(new InetSocketAddress(PORT));
            
            // Register the server socket channel with the selector for accept operations
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            
            System.out.println("Non-Blocking Server started on port " + PORT);
            
            // Server loop
            while (true) {
                // Wait for events (blocks until at least one channel is ready)
                selector.select();
                
                // Get the keys for the ready channels
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                
                // Process each key
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    
                    try {
                        // Handle the event
                        if (key.isAcceptable()) {
                            // A connection was accepted by the server socket channel
                            handleAccept(selector, serverChannel);
                        } else if (key.isReadable()) {
                            // A channel is ready for reading
                            handleRead(key);
                        } else if (key.isWritable()) {
                            // A channel is ready for writing
                            handleWrite(key);
                        }
                    } catch (IOException e) {
                        // Handle channel errors
                        System.out.println("Channel error: " + e.getMessage());
                        
                        // Close the channel
                        SocketChannel channel = (SocketChannel) key.channel();
                        handleClientDisconnect(channel);
                        key.cancel();
                    }
                    
                    // Remove the key from the selected keys set
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Handles a connection accept event.
     */
    private static void handleAccept(Selector selector, ServerSocketChannel serverChannel) throws IOException {
        // Accept the connection
        SocketChannel clientChannel = serverChannel.accept();
        
        // Configure the client channel to be non-blocking
        clientChannel.configureBlocking(false);
        
        // Register the client channel with the selector for read operations
        clientChannel.register(selector, SelectionKey.OP_READ);
        
        // Assign a client ID
        int clientId = clientCounter.incrementAndGet();
        
        // Store client information
        ClientInfo clientInfo = new ClientInfo(clientId, clientChannel.getRemoteAddress().toString());
        clients.put(clientChannel, clientInfo);
        
        System.out.println("Client " + clientId + " connected from " + clientChannel.getRemoteAddress());
        
        // Send a welcome message to the client
        String welcomeMessage = "Welcome to the Non-Blocking Server!\r\n" +
                               "Your client ID is " + clientId + "\r\n" +
                               "Connected at: " + new Date() + "\r\n" +
                               "Type 'bye' to disconnect\r\n";
        
        ByteBuffer welcomeBuffer = ByteBuffer.wrap(welcomeMessage.getBytes(StandardCharsets.UTF_8));
        clientChannel.write(welcomeBuffer);
    }
    
    /**
     * Handles a read event.
     */
    private static void handleRead(SelectionKey key) throws IOException {
        // Get the client channel
        SocketChannel clientChannel = (SocketChannel) key.channel();
        
        // Get the client information
        ClientInfo clientInfo = clients.get(clientChannel);
        
        // Create a buffer for reading
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        
        // Read data from the channel
        int bytesRead = clientChannel.read(buffer);
        
        if (bytesRead == -1) {
            // End of stream, client closed the connection
            handleClientDisconnect(clientChannel);
            key.cancel();
            return;
        }
        
        // Process the data
        buffer.flip();
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        String message = new String(data, StandardCharsets.UTF_8).trim();
        
        System.out.println("Received from client " + clientInfo.getId() + ": " + message);
        
        // Check if the client wants to disconnect
        if (message.equalsIgnoreCase("bye")) {
            // Send a goodbye message
            String goodbyeMessage = "Goodbye!\r\n";
            ByteBuffer goodbyeBuffer = ByteBuffer.wrap(goodbyeMessage.getBytes(StandardCharsets.UTF_8));
            clientChannel.write(goodbyeBuffer);
            
            // Disconnect the client
            handleClientDisconnect(clientChannel);
            key.cancel();
            return;
        }
        
        // Prepare a response
        String response;
        
        // Process special commands
        if (message.equalsIgnoreCase("time")) {
            response = "Current time: " + new Date() + "\r\n";
        } else if (message.equalsIgnoreCase("info")) {
            response = "Server Info:\r\n" +
                      "- Server Port: " + PORT + "\r\n" +
                      "- Client ID: " + clientInfo.getId() + "\r\n" +
                      "- Client Address: " + clientInfo.getAddress() + "\r\n" +
                      "- Connected Clients: " + clients.size() + "\r\n";
        } else if (message.equalsIgnoreCase("clients")) {
            // List all connected clients
            StringBuilder clientList = new StringBuilder("Connected Clients:\r\n");
            for (ClientInfo info : clients.values()) {
                clientList.append("- Client ").append(info.getId())
                         .append(" (").append(info.getAddress()).append(")\r\n");
            }
            response = clientList.toString();
        } else {
            // Echo the message back to the client
            response = "Server echo: " + message + "\r\n";
        }
        
        // Store the response in the client's write buffer
        clientInfo.setWriteBuffer(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
        
        // Register the channel for write operations
        clientChannel.register(key.selector(), SelectionKey.OP_WRITE);
    }
    
    /**
     * Handles a write event.
     */
    private static void handleWrite(SelectionKey key) throws IOException {
        // Get the client channel
        SocketChannel clientChannel = (SocketChannel) key.channel();
        
        // Get the client information
        ClientInfo clientInfo = clients.get(clientChannel);
        
        // Get the write buffer
        ByteBuffer buffer = clientInfo.getWriteBuffer();
        
        // Write the data to the channel
        clientChannel.write(buffer);
        
        // Check if all data has been written
        if (!buffer.hasRemaining()) {
            // Register the channel for read operations
            clientChannel.register(key.selector(), SelectionKey.OP_READ);
        }
    }
    
    /**
     * Handles a client disconnect.
     */
    private static void handleClientDisconnect(SocketChannel clientChannel) throws IOException {
        // Get the client information
        ClientInfo clientInfo = clients.get(clientChannel);
        
        if (clientInfo != null) {
            System.out.println("Client " + clientInfo.getId() + " disconnected");
            
            // Remove the client from the map
            clients.remove(clientChannel);
        }
        
        // Close the channel
        clientChannel.close();
    }
    
    /**
     * Class to store client information.
     */
    private static class ClientInfo {
        private final int id;
        private final String address;
        private ByteBuffer writeBuffer;
        
        public ClientInfo(int id, String address) {
            this.id = id;
            this.address = address;
        }
        
        public int getId() {
            return id;
        }
        
        public String getAddress() {
            return address;
        }
        
        public ByteBuffer getWriteBuffer() {
            return writeBuffer;
        }
        
        public void setWriteBuffer(ByteBuffer writeBuffer) {
            this.writeBuffer = writeBuffer;
        }
    }
}
