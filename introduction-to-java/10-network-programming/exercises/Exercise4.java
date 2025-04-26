/**
 * Exercise 4: File Transfer Application
 * 
 * Instructions:
 * 1. Implement a file transfer application using sockets.
 * 
 * 2. The application should have two components:
 *    a. A server that receives files
 *    b. A client that sends files
 * 
 * 3. The server should:
 *    a. Listen for client connections
 *    b. Accept file uploads from clients
 *    c. Save the received files to a specified directory
 *    d. Handle multiple client connections concurrently
 *    e. Provide feedback on the transfer progress
 * 
 * 4. The client should:
 *    a. Connect to the server
 *    b. Allow the user to select a file to upload
 *    c. Send the file to the server
 *    d. Display the transfer progress
 *    e. Handle errors gracefully
 * 
 * 5. Implement a simple protocol for the file transfer:
 *    a. Send the file name and size before sending the file data
 *    b. Send the file data in chunks
 *    c. Include a checksum to verify the integrity of the transferred file
 * 
 * 6. Add support for resuming interrupted transfers.
 * 
 * 7. Bonus: Implement file download from the server to the client.
 * 
 * 8. Bonus: Add encryption for secure file transfer.
 * 
 * 9. Bonus: Implement a graphical user interface using JavaFX or Swing.
 * 
 * Example usage:
 * java FileTransferServer [port] [directory]
 * java FileTransferClient [server] [port] [file]
 */
public class Exercise4 {
    public static void main(String[] args) {
        System.out.println("File Transfer Application Exercise");
        System.out.println("=================================");
        
        // TODO: Parse command-line arguments
        
        // TODO: Determine whether to start the server or client
        
        // TODO: Start the server or client based on the arguments
    }
}

/**
 * TODO: Implement FileTransferServer
 */
class FileTransferServer {
    // TODO: Implement this class
}

/**
 * TODO: Implement FileTransferClient
 */
class FileTransferClient {
    // TODO: Implement this class
}

/**
 * TODO: Implement FileTransferProtocol
 */
class FileTransferProtocol {
    // TODO: Implement this class
}

/**
 * TODO: Implement FileInfo
 */
class FileInfo {
    // TODO: Implement this class
}

/**
 * TODO: Implement TransferProgress
 */
class TransferProgress {
    // TODO: Implement this class
}

/**
 * TODO: Implement ChecksumCalculator
 */
class ChecksumCalculator {
    // TODO: Implement this class
}

/**
 * TODO: Implement ClientHandler
 */
class ClientHandler implements Runnable {
    // TODO: Implement this class
}
