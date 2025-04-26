/**
 * UDPServer.java
 * This program demonstrates a simple UDP server that receives and responds to datagrams.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPServer {
    // Port to listen on
    private static final int PORT = 9090;
    // Maximum size of the datagram packet
    private static final int MAX_PACKET_SIZE = 1024;
    
    public static void main(String[] args) {
        System.out.println("--- UDP Server ---");
        
        // Try-with-resources to ensure the socket is closed
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP Server started on port " + PORT);
            System.out.println("Server IP address: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Waiting for datagrams...");
            
            // Buffer for receiving data
            byte[] receiveBuffer = new byte[MAX_PACKET_SIZE];
            
            // Server runs until manually terminated
            while (true) {
                // Create a packet for receiving data
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                
                // Receive a packet (blocks until a packet is received)
                socket.receive(receivePacket);
                
                // Get client information
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                
                // Convert the received data to a string
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                System.out.println("Received from " + clientAddress.getHostAddress() + ":" + clientPort + ": " + message);
                
                // Prepare a response
                String response;
                
                // Process special commands
                if (message.equalsIgnoreCase("time")) {
                    response = "Current time: " + new Date();
                } else if (message.equalsIgnoreCase("info")) {
                    response = "Server Info:\n" +
                              "- Server Address: " + InetAddress.getLocalHost().getHostAddress() + "\n" +
                              "- Server Port: " + PORT + "\n" +
                              "- Client Address: " + clientAddress.getHostAddress() + "\n" +
                              "- Client Port: " + clientPort;
                } else if (message.equalsIgnoreCase("bye")) {
                    response = "Goodbye!";
                } else {
                    // Echo the message back to the client
                    response = "Server echo: " + message;
                }
                
                // Convert the response to bytes
                byte[] sendBuffer = response.getBytes();
                
                // Create a packet for sending the response
                DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer, 
                    sendBuffer.length, 
                    clientAddress, 
                    clientPort
                );
                
                // Send the response
                socket.send(sendPacket);
                
                System.out.println("Sent response to " + clientAddress.getHostAddress() + ":" + clientPort);
                
                // Clear the buffer for the next packet
                receiveBuffer = new byte[MAX_PACKET_SIZE];
            }
        } catch (SocketException e) {
            System.out.println("Socket error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
