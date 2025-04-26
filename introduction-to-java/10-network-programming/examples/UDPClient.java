/**
 * UDPClient.java
 * This program demonstrates a simple UDP client that sends datagrams to a server.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {
    // Default server address and port
    private static final String DEFAULT_SERVER_ADDRESS = "localhost";
    private static final int DEFAULT_SERVER_PORT = 9090;
    // Maximum size of the datagram packet
    private static final int MAX_PACKET_SIZE = 1024;
    // Timeout for receiving responses (in milliseconds)
    private static final int TIMEOUT = 5000;
    
    public static void main(String[] args) {
        System.out.println("--- UDP Client ---");
        
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
        
        // Try-with-resources to ensure the socket is closed
        try (DatagramSocket socket = new DatagramSocket()) {
            // Set a timeout for receiving responses
            socket.setSoTimeout(TIMEOUT);
            
            // Get the server's InetAddress
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            
            System.out.println("UDP Client started");
            System.out.println("Server address: " + serverAddress + ":" + serverPort);
            System.out.println("Local port: " + socket.getLocalPort());
            
            // Read user input and send to server
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type messages to send to the server (type 'bye' to exit):");
            
            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                
                // Convert the message to bytes
                byte[] sendBuffer = userInput.getBytes();
                
                // Create a packet for sending the message
                DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer, 
                    sendBuffer.length, 
                    serverInetAddress, 
                    serverPort
                );
                
                // Send the packet
                socket.send(sendPacket);
                
                System.out.println("Sent message to server: " + userInput);
                
                // If user types "bye", exit the loop
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
                
                // Buffer for receiving the response
                byte[] receiveBuffer = new byte[MAX_PACKET_SIZE];
                
                // Create a packet for receiving the response
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                
                try {
                    // Receive the response (blocks until a packet is received or timeout)
                    socket.receive(receivePacket);
                    
                    // Convert the received data to a string
                    String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    
                    System.out.println("Server: " + response);
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout waiting for server response");
                }
            }
            
            // Clean up resources
            System.out.println("Exiting...");
            scanner.close();
            
        } catch (SocketException e) {
            System.out.println("Socket error: " + e.getMessage());
            e.printStackTrace();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + serverAddress);
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
