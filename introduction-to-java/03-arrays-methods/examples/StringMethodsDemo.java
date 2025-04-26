/**
 * StringMethodsDemo.java
 * This program demonstrates common String methods in Java.
 */
public class StringMethodsDemo {
    public static void main(String[] args) {
        String text = "Hello, Java Programming!";
        
        System.out.println("Original string: \"" + text + "\"");
        
        System.out.println("\n--- Basic String Information ---");
        
        // String length
        System.out.println("Length: " + text.length());
        
        // Check if string is empty
        System.out.println("Is empty: " + text.isEmpty());
        
        System.out.println("\n--- Case Conversion ---");
        
        // Convert to uppercase
        String upperCase = text.toUpperCase();
        System.out.println("Uppercase: \"" + upperCase + "\"");
        
        // Convert to lowercase
        String lowerCase = text.toLowerCase();
        System.out.println("Lowercase: \"" + lowerCase + "\"");
        
        System.out.println("\n--- Searching in Strings ---");
        
        // Check if string contains a substring
        System.out.println("Contains 'Java': " + text.contains("Java"));
        System.out.println("Contains 'Python': " + text.contains("Python"));
        
        // Find the index of a character or substring
        System.out.println("Index of 'J': " + text.indexOf('J'));
        System.out.println("Index of 'Java': " + text.indexOf("Java"));
        System.out.println("Index of 'X' (not found): " + text.indexOf('X'));
        
        // Find the last occurrence
        String repeated = "Hello, Hello, Hello!";
        System.out.println("Last index of 'Hello' in '" + repeated + "': " + repeated.lastIndexOf("Hello"));
        
        // Check if string starts with or ends with a substring
        System.out.println("Starts with 'Hello': " + text.startsWith("Hello"));
        System.out.println("Ends with '!': " + text.endsWith("!"));
        
        System.out.println("\n--- Extracting Substrings ---");
        
        // Extract a substring
        String sub1 = text.substring(7);  // From index 7 to the end
        System.out.println("Substring from index 7: \"" + sub1 + "\"");
        
        String sub2 = text.substring(7, 11);  // From index 7 to 10 (11 is exclusive)
        System.out.println("Substring from index 7 to 10: \"" + sub2 + "\"");
        
        System.out.println("\n--- Modifying Strings ---");
        
        // Replace characters or substrings
        String replaced = text.replace('o', '0');
        System.out.println("Replace 'o' with '0': \"" + replaced + "\"");
        
        String replacedSubstring = text.replace("Java", "Python");
        System.out.println("Replace 'Java' with 'Python': \"" + replacedSubstring + "\"");
        
        // Trim whitespace
        String withSpaces = "   Trim me!   ";
        System.out.println("Original: \"" + withSpaces + "\"");
        System.out.println("Trimmed: \"" + withSpaces.trim() + "\"");
        
        System.out.println("\n--- Splitting Strings ---");
        
        // Split a string into an array
        String sentence = "Java is a programming language";
        String[] words = sentence.split(" ");
        System.out.println("Split result:");
        for (int i = 0; i < words.length; i++) {
            System.out.println("Word " + i + ": \"" + words[i] + "\"");
        }
        
        // Split with limit
        String[] limitedSplit = sentence.split(" ", 3);
        System.out.println("\nSplit with limit 3:");
        for (int i = 0; i < limitedSplit.length; i++) {
            System.out.println("Part " + i + ": \"" + limitedSplit[i] + "\"");
        }
        
        System.out.println("\n--- Joining Strings ---");
        
        // Concatenate strings using +
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        System.out.println("Concatenation using +: \"" + fullName + "\"");
        
        // Concatenate using concat()
        String greeting = "Hello, ".concat(firstName).concat("!");
        System.out.println("Concatenation using concat(): \"" + greeting + "\"");
        
        // Join using String.join() (Java 8+)
        String[] parts = {"Java", "is", "fun"};
        String joined = String.join(" ", parts);
        System.out.println("Joined with spaces: \"" + joined + "\"");
        
        System.out.println("\n--- String Comparison ---");
        
        // Compare strings
        String str1 = "Hello";
        String str2 = "hello";
        String str3 = "Hello";
        
        System.out.println("str1: \"" + str1 + "\", str2: \"" + str2 + "\", str3: \"" + str3 + "\"");
        System.out.println("str1 equals str2: " + str1.equals(str2));
        System.out.println("str1 equals str3: " + str1.equals(str3));
        System.out.println("str1 equalsIgnoreCase str2: " + str1.equalsIgnoreCase(str2));
        
        // Compare lexicographically (dictionary order)
        System.out.println("str1.compareTo(str2): " + str1.compareTo(str2));
        System.out.println("str1.compareTo(str3): " + str1.compareTo(str3));
        System.out.println("str2.compareTo(str1): " + str2.compareTo(str1));
        
        System.out.println("\n--- String Formatting ---");
        
        // Format strings
        String formatted = String.format("Name: %s, Age: %d, Height: %.2f", "Alice", 25, 5.75);
        System.out.println("Formatted string: \"" + formatted + "\"");
        
        // Format with printf
        System.out.printf("Name: %s, Score: %.1f%%\n", "Bob", 92.5);
    }
}
