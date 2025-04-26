/**
 * JSONExample.java
 * This program demonstrates working with JSON data in Java.
 * Note: This example uses the org.json library, which needs to be added to your project.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONExample {
    public static void main(String[] args) {
        System.out.println("--- JSON Examples ---");
        
        // Example 1: Creating JSON objects
        System.out.println("\nExample 1: Creating JSON objects");
        createJSONObjects();
        
        // Example 2: Parsing JSON strings
        System.out.println("\nExample 2: Parsing JSON strings");
        parseJSONStrings();
        
        // Example 3: Working with JSON arrays
        System.out.println("\nExample 3: Working with JSON arrays");
        workWithJSONArrays();
        
        // Example 4: Converting between Java objects and JSON
        System.out.println("\nExample 4: Converting between Java objects and JSON");
        convertBetweenJavaAndJSON();
        
        // Example 5: Fetching and parsing JSON from a web API
        System.out.println("\nExample 5: Fetching and parsing JSON from a web API");
        fetchAndParseJSON();
    }
    
    /**
     * Demonstrates creating JSON objects.
     */
    private static void createJSONObjects() {
        try {
            // Create a simple JSON object
            JSONObject person = new JSONObject();
            person.put("name", "John Doe");
            person.put("age", 30);
            person.put("email", "john.doe@example.com");
            person.put("isEmployed", true);
            
            // Print the JSON object
            System.out.println("Simple JSON object:");
            System.out.println(person.toString(2)); // Pretty print with 2-space indentation
            
            // Create a nested JSON object
            JSONObject address = new JSONObject();
            address.put("street", "123 Main St");
            address.put("city", "Anytown");
            address.put("state", "CA");
            address.put("zipCode", "12345");
            
            // Add the nested object to the person
            person.put("address", address);
            
            // Create a JSON array of phone numbers
            JSONArray phoneNumbers = new JSONArray();
            
            JSONObject homePhone = new JSONObject();
            homePhone.put("type", "home");
            homePhone.put("number", "555-1234");
            
            JSONObject mobilePhone = new JSONObject();
            mobilePhone.put("type", "mobile");
            mobilePhone.put("number", "555-5678");
            
            // Add the phone objects to the array
            phoneNumbers.put(homePhone);
            phoneNumbers.put(mobilePhone);
            
            // Add the array to the person
            person.put("phoneNumbers", phoneNumbers);
            
            // Print the complex JSON object
            System.out.println("\nComplex JSON object:");
            System.out.println(person.toString(2));
        } catch (JSONException e) {
            System.out.println("JSON Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates parsing JSON strings.
     */
    private static void parseJSONStrings() {
        try {
            // JSON string to parse
            String jsonString = "{"
                + "\"name\": \"Jane Smith\","
                + "\"age\": 28,"
                + "\"email\": \"jane.smith@example.com\","
                + "\"isEmployed\": true,"
                + "\"address\": {"
                + "  \"street\": \"456 Oak Ave\","
                + "  \"city\": \"Somewhere\","
                + "  \"state\": \"NY\","
                + "  \"zipCode\": \"67890\""
                + "},"
                + "\"phoneNumbers\": ["
                + "  {\"type\": \"home\", \"number\": \"555-4321\"},"
                + "  {\"type\": \"mobile\", \"number\": \"555-8765\"}"
                + "]"
                + "}";
            
            // Parse the JSON string
            JSONObject person = new JSONObject(jsonString);
            
            // Extract values from the JSON object
            String name = person.getString("name");
            int age = person.getInt("age");
            String email = person.getString("email");
            boolean isEmployed = person.getBoolean("isEmployed");
            
            System.out.println("Parsed JSON data:");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Email: " + email);
            System.out.println("Employed: " + isEmployed);
            
            // Extract the nested address object
            JSONObject address = person.getJSONObject("address");
            String street = address.getString("street");
            String city = address.getString("city");
            String state = address.getString("state");
            String zipCode = address.getString("zipCode");
            
            System.out.println("\nAddress:");
            System.out.println("Street: " + street);
            System.out.println("City: " + city);
            System.out.println("State: " + state);
            System.out.println("Zip Code: " + zipCode);
            
            // Extract the phone numbers array
            JSONArray phoneNumbers = person.getJSONArray("phoneNumbers");
            
            System.out.println("\nPhone Numbers:");
            for (int i = 0; i < phoneNumbers.length(); i++) {
                JSONObject phone = phoneNumbers.getJSONObject(i);
                String type = phone.getString("type");
                String number = phone.getString("number");
                
                System.out.println(type + ": " + number);
            }
            
            // Check if a key exists
            if (person.has("age")) {
                System.out.println("\nThe 'age' key exists");
            }
            
            // Get a value with a default
            String website = person.optString("website", "N/A");
            System.out.println("Website: " + website);
            
            // Remove a key
            person.remove("email");
            System.out.println("\nAfter removing 'email':");
            System.out.println(person.toString(2));
        } catch (JSONException e) {
            System.out.println("JSON Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates working with JSON arrays.
     */
    private static void workWithJSONArrays() {
        try {
            // Create a JSON array
            JSONArray people = new JSONArray();
            
            // Add JSON objects to the array
            JSONObject person1 = new JSONObject();
            person1.put("name", "Alice");
            person1.put("age", 25);
            
            JSONObject person2 = new JSONObject();
            person2.put("name", "Bob");
            person2.put("age", 30);
            
            JSONObject person3 = new JSONObject();
            person3.put("name", "Charlie");
            person3.put("age", 35);
            
            // Add the objects to the array
            people.put(person1);
            people.put(person2);
            people.put(person3);
            
            // Print the JSON array
            System.out.println("JSON array:");
            System.out.println(people.toString(2));
            
            // Iterate through the array
            System.out.println("\nIterating through the array:");
            for (int i = 0; i < people.length(); i++) {
                JSONObject person = people.getJSONObject(i);
                String name = person.getString("name");
                int age = person.getInt("age");
                
                System.out.println(name + " is " + age + " years old");
            }
            
            // Create a JSON array from a string
            String jsonArrayString = "[\"apple\", \"banana\", \"cherry\"]";
            JSONArray fruits = new JSONArray(jsonArrayString);
            
            System.out.println("\nFruits array:");
            for (int i = 0; i < fruits.length(); i++) {
                System.out.println(fruits.getString(i));
            }
            
            // Create a JSON array with mixed types
            JSONArray mixedArray = new JSONArray();
            mixedArray.put("string");
            mixedArray.put(42);
            mixedArray.put(true);
            mixedArray.put(new JSONObject().put("key", "value"));
            
            System.out.println("\nMixed array:");
            System.out.println(mixedArray.toString(2));
            
            // Get values of different types
            String stringValue = mixedArray.getString(0);
            int intValue = mixedArray.getInt(1);
            boolean boolValue = mixedArray.getBoolean(2);
            JSONObject objValue = mixedArray.getJSONObject(3);
            
            System.out.println("\nValues from mixed array:");
            System.out.println("String: " + stringValue);
            System.out.println("Integer: " + intValue);
            System.out.println("Boolean: " + boolValue);
            System.out.println("Object: " + objValue.toString());
        } catch (JSONException e) {
            System.out.println("JSON Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates converting between Java objects and JSON.
     */
    private static void convertBetweenJavaAndJSON() {
        try {
            // Convert Java Map to JSON
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("name", "David");
            personMap.put("age", 40);
            
            Map<String, Object> addressMap = new HashMap<>();
            addressMap.put("street", "789 Pine St");
            addressMap.put("city", "Elsewhere");
            
            personMap.put("address", addressMap);
            
            List<String> hobbies = new ArrayList<>();
            hobbies.add("reading");
            hobbies.add("hiking");
            hobbies.add("cooking");
            
            personMap.put("hobbies", hobbies);
            
            // Convert Map to JSONObject
            JSONObject personJson = new JSONObject(personMap);
            
            System.out.println("Map converted to JSON:");
            System.out.println(personJson.toString(2));
            
            // Convert JSON to Java Map
            Map<String, Object> convertedMap = jsonToMap(personJson);
            
            System.out.println("\nJSON converted to Map:");
            printMap(convertedMap, 0);
        } catch (JSONException e) {
            System.out.println("JSON Error: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to convert a JSONObject to a Map.
     */
    private static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> map = new HashMap<>();
        
        for (String key : json.keySet()) {
            Object value = json.get(key);
            
            if (value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            } else if (value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }
            
            map.put(key, value);
        }
        
        return map;
    }
    
    /**
     * Helper method to convert a JSONArray to a List.
     */
    private static List<Object> jsonToList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            
            if (value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            } else if (value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }
            
            list.add(value);
        }
        
        return list;
    }
    
    /**
     * Helper method to print a Map with indentation.
     */
    private static void printMap(Map<String, Object> map, int indent) {
        String indentStr = " ".repeat(indent);
        
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            if (value instanceof Map) {
                System.out.println(indentStr + key + ":");
                printMap((Map<String, Object>) value, indent + 2);
            } else if (value instanceof List) {
                System.out.println(indentStr + key + ":");
                printList((List<Object>) value, indent + 2);
            } else {
                System.out.println(indentStr + key + ": " + value);
            }
        }
    }
    
    /**
     * Helper method to print a List with indentation.
     */
    private static void printList(List<Object> list, int indent) {
        String indentStr = " ".repeat(indent);
        
        for (int i = 0; i < list.size(); i++) {
            Object value = list.get(i);
            
            if (value instanceof Map) {
                System.out.println(indentStr + "- Item " + i + ":");
                printMap((Map<String, Object>) value, indent + 2);
            } else if (value instanceof List) {
                System.out.println(indentStr + "- Item " + i + ":");
                printList((List<Object>) value, indent + 2);
            } else {
                System.out.println(indentStr + "- " + value);
            }
        }
    }
    
    /**
     * Demonstrates fetching and parsing JSON from a web API.
     */
    private static void fetchAndParseJSON() {
        try {
            // Create a URL for the API
            URL url = new URL("https://jsonplaceholder.typicode.com/users/1");
            
            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                // Parse the JSON response
                JSONObject user = new JSONObject(response.toString());
                
                // Extract user information
                int id = user.getInt("id");
                String name = user.getString("name");
                String username = user.getString("username");
                String email = user.getString("email");
                
                System.out.println("\nUser Information:");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                
                // Extract address information
                JSONObject address = user.getJSONObject("address");
                String street = address.getString("street");
                String city = address.getString("city");
                
                System.out.println("\nAddress:");
                System.out.println("Street: " + street);
                System.out.println("City: " + city);
                
                // Extract company information
                JSONObject company = user.getJSONObject("company");
                String companyName = company.getString("name");
                
                System.out.println("\nCompany:");
                System.out.println("Name: " + companyName);
            } else {
                System.out.println("Error fetching data from API");
            }
            
            // Disconnect
            connection.disconnect();
        } catch (IOException | JSONException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
