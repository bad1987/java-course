/**
 * Exercise 2: Multi-User Chat Application with Private Messaging
 * 
 * Instructions:
 * 1. Extend the chat application from the examples to add more features.
 * 
 * 2. Implement the following features:
 *    a. User authentication (simple username/password)
 *    b. Private messaging between users
 *    c. Chat rooms (users can create and join different rooms)
 *    d. User status (online, away, busy)
 *    e. Message history (show recent messages when a user joins)
 * 
 * 3. The server should:
 *    a. Handle multiple client connections concurrently
 *    b. Maintain a list of registered users
 *    c. Maintain a list of chat rooms
 *    d. Store message history for each chat room
 *    e. Handle commands for various actions (create room, join room, etc.)
 * 
 * 4. The client should:
 *    a. Provide a simple text-based interface
 *    b. Allow users to log in with a username and password
 *    c. Display messages in the current chat room
 *    d. Allow sending private messages to other users
 *    e. Support commands for changing rooms, status, etc.
 * 
 * 5. Implement the following commands:
 *    - /help - Show available commands
 *    - /login <username> <password> - Log in
 *    - /register <username> <password> - Register a new user
 *    - /msg <username> <message> - Send a private message
 *    - /join <room> - Join a chat room
 *    - /create <room> - Create a new chat room
 *    - /rooms - List available chat rooms
 *    - /users - List users in the current room
 *    - /status <status> - Change your status (online, away, busy)
 *    - /quit - Disconnect from the server
 * 
 * 6. Bonus: Implement file sharing between users.
 * 
 * 7. Bonus: Add encryption for private messages.
 * 
 * 8. Bonus: Implement a graphical user interface using JavaFX or Swing.
 */
public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("Multi-User Chat Application Exercise");
        System.out.println("===================================");
        
        // TODO: Implement the chat application
        
        // TODO: Start the server or client based on command-line arguments
    }
}

/**
 * TODO: Implement EnhancedChatServer
 */
class EnhancedChatServer {
    // TODO: Implement this class
}

/**
 * TODO: Implement EnhancedChatClient
 */
class EnhancedChatClient {
    // TODO: Implement this class
}

/**
 * TODO: Implement User
 */
class User {
    // TODO: Implement this class
}

/**
 * TODO: Implement ChatRoom
 */
class ChatRoom {
    // TODO: Implement this class
}

/**
 * TODO: Implement Message
 */
class Message {
    // TODO: Implement this class
}

/**
 * TODO: Implement ClientHandler
 */
class ClientHandler implements Runnable {
    // TODO: Implement this class
}

/**
 * TODO: Implement CommandProcessor
 */
class CommandProcessor {
    // TODO: Implement this class
}
