/**
 * PropertiesExample.java
 * This program demonstrates working with properties files in Java.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) {
        System.out.println("--- Properties Examples ---");
        
        // Example 1: Creating and storing properties
        System.out.println("\nExample 1: Creating and storing properties");
        createAndStoreProperties();
        
        // Example 2: Loading properties from a file
        System.out.println("\nExample 2: Loading properties from a file");
        loadProperties();
        
        // Example 3: Working with system properties
        System.out.println("\nExample 3: Working with system properties");
        workWithSystemProperties();
        
        // Example 4: Properties with default values
        System.out.println("\nExample 4: Properties with default values");
        propertiesWithDefaults();
        
        // Example 5: XML properties
        System.out.println("\nExample 5: XML properties");
        xmlProperties();
        
        // Example 6: Using properties for application configuration
        System.out.println("\nExample 6: Using properties for application configuration");
        applicationConfiguration();
    }
    
    /**
     * Demonstrates creating and storing properties.
     */
    public static void createAndStoreProperties() {
        // Create a Properties object
        Properties properties = new Properties();
        
        // Add properties
        properties.setProperty("database.url", "jdbc:mysql://localhost:3306/mydb");
        properties.setProperty("database.username", "admin");
        properties.setProperty("database.password", "password123");
        properties.setProperty("app.name", "MyApplication");
        properties.setProperty("app.version", "1.0.0");
        
        // Store properties to a file
        try (OutputStream output = new FileOutputStream("config.properties")) {
            // Save properties with a comment
            properties.store(output, "Application Configuration");
            
            System.out.println("Properties stored to config.properties");
        } catch (IOException e) {
            System.out.println("Error storing properties: " + e.getMessage());
        }
        
        // Display the properties
        System.out.println("\nProperties:");
        for (String key : properties.stringPropertyNames()) {
            System.out.println(key + " = " + properties.getProperty(key));
        }
    }
    
    /**
     * Demonstrates loading properties from a file.
     */
    public static void loadProperties() {
        // Create a Properties object
        Properties properties = new Properties();
        
        // Load properties from a file
        try (InputStream input = new FileInputStream("config.properties")) {
            // Load properties
            properties.load(input);
            
            System.out.println("Properties loaded from config.properties");
        } catch (IOException e) {
            System.out.println("Error loading properties: " + e.getMessage());
            return;
        }
        
        // Access individual properties
        String dbUrl = properties.getProperty("database.url");
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");
        String appName = properties.getProperty("app.name");
        String appVersion = properties.getProperty("app.version");
        
        System.out.println("\nAccessing individual properties:");
        System.out.println("Database URL: " + dbUrl);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("App Name: " + appName);
        System.out.println("App Version: " + appVersion);
        
        // Access a non-existent property
        String nonExistent = properties.getProperty("non.existent.property");
        System.out.println("Non-existent property: " + nonExistent);
        
        // Access a non-existent property with a default value
        String nonExistentWithDefault = properties.getProperty("non.existent.property", "Default Value");
        System.out.println("Non-existent property with default: " + nonExistentWithDefault);
    }
    
    /**
     * Demonstrates working with system properties.
     */
    public static void workWithSystemProperties() {
        // Get the system properties
        Properties systemProps = System.getProperties();
        
        // Display some common system properties
        System.out.println("Java version: " + systemProps.getProperty("java.version"));
        System.out.println("Java home: " + systemProps.getProperty("java.home"));
        System.out.println("OS name: " + systemProps.getProperty("os.name"));
        System.out.println("OS version: " + systemProps.getProperty("os.version"));
        System.out.println("User home directory: " + systemProps.getProperty("user.home"));
        System.out.println("User working directory: " + systemProps.getProperty("user.dir"));
        
        // Set a system property
        System.setProperty("my.custom.property", "Custom Value");
        
        // Get the custom property
        String customProp = System.getProperty("my.custom.property");
        System.out.println("Custom property: " + customProp);
        
        // List all system properties
        System.out.println("\nAll system properties (first 10):");
        int count = 0;
        for (String key : systemProps.stringPropertyNames()) {
            if (count++ < 10) {
                System.out.println(key + " = " + systemProps.getProperty(key));
            } else {
                break;
            }
        }
        System.out.println("... and " + (systemProps.size() - 10) + " more");
    }
    
    /**
     * Demonstrates using properties with default values.
     */
    public static void propertiesWithDefaults() {
        // Create default properties
        Properties defaults = new Properties();
        defaults.setProperty("app.name", "DefaultApp");
        defaults.setProperty("app.version", "0.0.0");
        defaults.setProperty("app.author", "Unknown");
        defaults.setProperty("app.url", "http://example.com");
        
        // Create properties with defaults
        Properties properties = new Properties(defaults);
        
        // Add some properties (overriding defaults)
        properties.setProperty("app.name", "MyApp");
        properties.setProperty("app.version", "1.0.0");
        
        // Access properties (some from defaults, some overridden)
        System.out.println("App Name: " + properties.getProperty("app.name"));  // Overridden
        System.out.println("App Version: " + properties.getProperty("app.version"));  // Overridden
        System.out.println("App Author: " + properties.getProperty("app.author"));  // From defaults
        System.out.println("App URL: " + properties.getProperty("app.url"));  // From defaults
        
        // Store properties to a file (only stores the non-default properties)
        try (OutputStream output = new FileOutputStream("app.properties")) {
            properties.store(output, "Application Properties");
            
            System.out.println("\nProperties stored to app.properties");
        } catch (IOException e) {
            System.out.println("Error storing properties: " + e.getMessage());
        }
        
        // Load properties from the file
        Properties loadedProps = new Properties(defaults);
        try (InputStream input = new FileInputStream("app.properties")) {
            loadedProps.load(input);
            
            System.out.println("Properties loaded from app.properties");
        } catch (IOException e) {
            System.out.println("Error loading properties: " + e.getMessage());
        }
        
        // Access loaded properties
        System.out.println("\nLoaded properties (with defaults):");
        System.out.println("App Name: " + loadedProps.getProperty("app.name"));
        System.out.println("App Version: " + loadedProps.getProperty("app.version"));
        System.out.println("App Author: " + loadedProps.getProperty("app.author"));
        System.out.println("App URL: " + loadedProps.getProperty("app.url"));
    }
    
    /**
     * Demonstrates working with XML properties.
     */
    public static void xmlProperties() {
        // Create a Properties object
        Properties properties = new Properties();
        
        // Add properties
        properties.setProperty("database.url", "jdbc:mysql://localhost:3306/mydb");
        properties.setProperty("database.username", "admin");
        properties.setProperty("database.password", "password123");
        properties.setProperty("app.name", "MyApplication");
        properties.setProperty("app.version", "1.0.0");
        
        // Store properties to an XML file
        try (OutputStream output = new FileOutputStream("config.xml")) {
            // Save properties as XML with a comment
            properties.storeToXML(output, "Application Configuration", "UTF-8");
            
            System.out.println("Properties stored to config.xml");
        } catch (IOException e) {
            System.out.println("Error storing XML properties: " + e.getMessage());
        }
        
        // Load properties from an XML file
        Properties loadedProps = new Properties();
        try (InputStream input = new FileInputStream("config.xml")) {
            // Load properties from XML
            loadedProps.loadFromXML(input);
            
            System.out.println("Properties loaded from config.xml");
        } catch (IOException e) {
            System.out.println("Error loading XML properties: " + e.getMessage());
            return;
        }
        
        // Display the loaded properties
        System.out.println("\nLoaded XML properties:");
        for (String key : loadedProps.stringPropertyNames()) {
            System.out.println(key + " = " + loadedProps.getProperty(key));
        }
    }
    
    /**
     * Demonstrates using properties for application configuration.
     */
    public static void applicationConfiguration() {
        // Create a DatabaseConfig class
        DatabaseConfig dbConfig = new DatabaseConfig();
        
        // Load configuration from properties file
        boolean loaded = dbConfig.loadConfig("config.properties");
        
        if (loaded) {
            // Display the configuration
            System.out.println("\nDatabase Configuration:");
            System.out.println("URL: " + dbConfig.getUrl());
            System.out.println("Username: " + dbConfig.getUsername());
            System.out.println("Password: " + dbConfig.getPassword());
            
            // Simulate connecting to the database
            boolean connected = dbConfig.connect();
            System.out.println("Database connection: " + (connected ? "Successful" : "Failed"));
            
            // Update the configuration
            dbConfig.setUrl("jdbc:mysql://localhost:3306/newdb");
            dbConfig.setUsername("newadmin");
            dbConfig.setPassword("newpassword");
            
            // Save the updated configuration
            boolean saved = dbConfig.saveConfig("updated_config.properties");
            if (saved) {
                System.out.println("\nUpdated configuration saved to updated_config.properties");
            }
        }
        
        // Clean up
        try {
            Files.deleteIfExists(Paths.get("app.properties"));
            Files.deleteIfExists(Paths.get("config.xml"));
            Files.deleteIfExists(Paths.get("updated_config.properties"));
            System.out.println("\nCleaned up property files");
        } catch (IOException e) {
            System.out.println("Error cleaning up: " + e.getMessage());
        }
    }
}

/**
 * A simple database configuration class that uses properties.
 */
class DatabaseConfig {
    private String url;
    private String username;
    private String password;
    
    public DatabaseConfig() {
        // Default values
        this.url = "jdbc:mysql://localhost:3306/defaultdb";
        this.username = "root";
        this.password = "";
    }
    
    /**
     * Loads configuration from a properties file.
     */
    public boolean loadConfig(String filename) {
        Properties properties = new Properties();
        
        try (InputStream input = new FileInputStream(filename)) {
            properties.load(input);
            
            // Get properties with defaults if not found
            this.url = properties.getProperty("database.url", this.url);
            this.username = properties.getProperty("database.username", this.username);
            this.password = properties.getProperty("database.password", this.password);
            
            return true;
        } catch (IOException e) {
            System.out.println("Error loading database configuration: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Saves configuration to a properties file.
     */
    public boolean saveConfig(String filename) {
        Properties properties = new Properties();
        
        // Set properties
        properties.setProperty("database.url", this.url);
        properties.setProperty("database.username", this.username);
        properties.setProperty("database.password", this.password);
        
        try (OutputStream output = new FileOutputStream(filename)) {
            properties.store(output, "Database Configuration");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving database configuration: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Simulates connecting to the database.
     */
    public boolean connect() {
        // In a real application, this would actually connect to the database
        System.out.println("Connecting to database at " + this.url);
        System.out.println("Using username: " + this.username);
        
        // Simulate a successful connection
        return true;
    }
    
    // Getters and setters
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
