/**
 * Exercise 3: Contact Manager with Serialization
 * 
 * Instructions:
 * 1. Create a contact management application that allows users to:
 *    - Add new contacts
 *    - View existing contacts
 *    - Search for contacts
 *    - Edit contacts
 *    - Delete contacts
 *    - Save contacts to a file
 *    - Load contacts from a file
 * 
 * 2. Implement the following classes:
 *    - Contact: Represents a contact with properties like name, phone, email, etc.
 *    - ContactManager: Manages a collection of contacts and provides CRUD operations
 *    - ContactSerializer: Handles saving and loading contacts using serialization
 * 
 * 3. The Contact class should:
 *    - Implement the Serializable interface
 *    - Have properties for name, phone number, email, address, and notes
 *    - Include appropriate constructors, getters, setters, and toString method
 *    - Override equals and hashCode methods
 * 
 * 4. The ContactManager class should:
 *    - Maintain a collection of contacts
 *    - Provide methods to add, view, search, edit, and delete contacts
 *    - Use the ContactSerializer to save and load contacts
 * 
 * 5. The ContactSerializer class should:
 *    - Provide methods to save contacts to a file using ObjectOutputStream
 *    - Provide methods to load contacts from a file using ObjectInputStream
 *    - Handle serialization exceptions appropriately
 * 
 * 6. Create a console-based application that demonstrates all the functionality.
 * 
 * 7. Implement proper exception handling for all operations.
 * 
 * 8. Bonus: Implement a graphical user interface using JavaFX or Swing.
 */
import java.io.Serializable;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        System.out.println("Contact Manager");
        System.out.println("===============");
        
        Scanner scanner = new Scanner(System.in);
        
        // TODO: Implement the Contact class
        // TODO: Implement the ContactManager class
        // TODO: Implement the ContactSerializer class
        
        try {
            // Create a contact manager
            // ContactManager manager = new ContactManager();
            
            boolean running = true;
            
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. Add Contact");
                System.out.println("2. View All Contacts");
                System.out.println("3. Search Contacts");
                System.out.println("4. Edit Contact");
                System.out.println("5. Delete Contact");
                System.out.println("6. Save Contacts");
                System.out.println("7. Load Contacts");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                
                switch (choice) {
                    case 1:
                        // Add Contact
                        System.out.println("Add Contact");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        
                        // TODO: Create and add the contact
                        System.out.println("Contact added successfully.");
                        break;
                    case 2:
                        // View All Contacts
                        System.out.println("All Contacts:");
                        // TODO: Display all contacts
                        break;
                    case 3:
                        // Search Contacts
                        System.out.print("Enter search term: ");
                        String searchTerm = scanner.nextLine();
                        System.out.println("Search Results:");
                        // TODO: Search and display matching contacts
                        break;
                    case 4:
                        // Edit Contact
                        System.out.print("Enter contact name to edit: ");
                        String editName = scanner.nextLine();
                        // TODO: Find and edit the contact
                        break;
                    case 5:
                        // Delete Contact
                        System.out.print("Enter contact name to delete: ");
                        String deleteName = scanner.nextLine();
                        // TODO: Find and delete the contact
                        break;
                    case 6:
                        // Save Contacts
                        System.out.print("Enter file name to save: ");
                        String saveFile = scanner.nextLine();
                        // TODO: Save contacts to file
                        System.out.println("Contacts saved successfully.");
                        break;
                    case 7:
                        // Load Contacts
                        System.out.print("Enter file name to load: ");
                        String loadFile = scanner.nextLine();
                        // TODO: Load contacts from file
                        System.out.println("Contacts loaded successfully.");
                        break;
                    case 8:
                        // Exit
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

// TODO: Implement the Contact class

// TODO: Implement the ContactManager class

// TODO: Implement the ContactSerializer class
