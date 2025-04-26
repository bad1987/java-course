/**
 * Exercise 4: Configuration Manager using Properties
 * 
 * Instructions:
 * 1. Create a configuration management system that allows applications to:
 *    - Load configuration settings from properties files
 *    - Access configuration values with type conversion
 *    - Provide default values for missing properties
 *    - Save updated configuration settings
 *    - Support multiple configuration profiles
 * 
 * 2. Implement the following classes:
 *    - ConfigManager: Main class for managing configuration settings
 *    - ConfigProfile: Represents a named set of configuration properties
 *    - ConfigException: Custom exception for configuration errors
 * 
 * 3. The ConfigManager class should:
 *    - Support loading properties from files
 *    - Support loading properties from XML files
 *    - Provide methods to get values as different types (getString, getInt, getBoolean, etc.)
 *    - Allow setting new property values
 *    - Support saving properties to files
 *    - Support multiple named configuration profiles
 * 
 * 4. The ConfigProfile class should:
 *    - Maintain a set of properties for a specific profile
 *    - Provide methods to get and set properties
 *    - Support loading and saving to a file
 * 
 * 5. Implement proper exception handling for all operations.
 * 
 * 6. Create a demonstration application that:
 *    - Creates a default configuration profile
 *    - Loads settings from a properties file
 *    - Displays the current settings
 *    - Allows the user to modify settings
 *    - Saves the updated settings
 *    - Supports switching between different profiles
 * 
 * 7. Include the following configuration categories:
 *    - Database settings (url, username, password, etc.)
 *    - User interface settings (theme, language, font size, etc.)
 *    - Application settings (log level, temp directory, etc.)
 */
import java.util.Properties;
import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        System.out.println("Configuration Manager");
        System.out.println("====================");
        
        Scanner scanner = new Scanner(System.in);
        
        // TODO: Implement the ConfigManager class
        // TODO: Implement the ConfigProfile class
        // TODO: Implement the ConfigException class
        
        try {
            // Create a configuration manager
            // ConfigManager configManager = new ConfigManager();
            
            boolean running = true;
            
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. Create New Profile");
                System.out.println("2. Load Profile");
                System.out.println("3. View Current Settings");
                System.out.println("4. Modify Setting");
                System.out.println("5. Save Current Profile");
                System.out.println("6. Switch Profile");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                
                switch (choice) {
                    case 1:
                        // Create New Profile
                        System.out.print("Enter profile name: ");
                        String profileName = scanner.nextLine();
                        // TODO: Create a new profile
                        System.out.println("Profile created: " + profileName);
                        break;
                    case 2:
                        // Load Profile
                        System.out.print("Enter profile file path: ");
                        String profilePath = scanner.nextLine();
                        // TODO: Load the profile
                        System.out.println("Profile loaded from: " + profilePath);
                        break;
                    case 3:
                        // View Current Settings
                        System.out.println("Current Settings:");
                        // TODO: Display current settings
                        break;
                    case 4:
                        // Modify Setting
                        System.out.print("Enter setting key: ");
                        String key = scanner.nextLine();
                        System.out.print("Enter setting value: ");
                        String value = scanner.nextLine();
                        // TODO: Update the setting
                        System.out.println("Setting updated: " + key + " = " + value);
                        break;
                    case 5:
                        // Save Current Profile
                        System.out.print("Enter file path to save: ");
                        String savePath = scanner.nextLine();
                        // TODO: Save the current profile
                        System.out.println("Profile saved to: " + savePath);
                        break;
                    case 6:
                        // Switch Profile
                        System.out.print("Enter profile name to switch to: ");
                        String switchProfile = scanner.nextLine();
                        // TODO: Switch to the specified profile
                        System.out.println("Switched to profile: " + switchProfile);
                        break;
                    case 7:
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

// TODO: Implement the ConfigManager class

// TODO: Implement the ConfigProfile class

// TODO: Implement the ConfigException class
