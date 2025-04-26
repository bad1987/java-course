/**
 * Exercise 2: Unique Word Counter
 * 
 * Instructions:
 * 1. Create a WordCounter class that uses a Set to store unique words and implements
 *    the following functionality:
 *    - addWord(String word): Adds a word to the set
 *    - addWords(String text): Splits the text into words and adds each word to the set
 *    - getWordCount(): Returns the number of unique words
 *    - getWords(): Returns the set of unique words
 *    - containsWord(String word): Checks if a word is in the set
 *    - removeWord(String word): Removes a word from the set
 *    - getWordsSorted(): Returns the words sorted alphabetically
 *    - getWordsLongerThan(int length): Returns words longer than the specified length
 *    - getWordsStartingWith(String prefix): Returns words starting with the specified prefix
 *    - clear(): Removes all words from the set
 * 
 * 2. Implement three different versions of the WordCounter class:
 *    - HashWordCounter: Uses a HashSet (unordered)
 *    - TreeWordCounter: Uses a TreeSet (sorted)
 *    - LinkedHashWordCounter: Uses a LinkedHashSet (maintains insertion order)
 * 
 * 3. Create a TextAnalyzer class that:
 *    - Takes a text file as input
 *    - Reads the file and processes its content
 *    - Uses the WordCounter classes to analyze the text
 *    - Provides statistics about the text (word count, unique words, etc.)
 *    - Allows searching for specific words or patterns
 *    - Compares the performance of different Set implementations
 * 
 * 4. Create a WordCounterApp class with a main method that:
 *    - Creates a TextAnalyzer
 *    - Processes a sample text file (or allows the user to specify a file)
 *    - Displays various statistics and analysis results
 *    - Provides a menu-driven interface for the user to interact with the system
 * 
 * 5. Implement proper exception handling for file operations and other potential errors
 * 
 * 6. Use appropriate Set methods and Java 8+ features (like streams, lambda expressions)
 *    where applicable
 * 
 * 7. Create a sample text file with at least 1000 words for testing
 */
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Exercise2 {
    public static void main(String[] args) {
        // TODO: Implement the WordCounterApp
    }
}

// TODO: Implement the WordCounter interface

// TODO: Implement the HashWordCounter class

// TODO: Implement the TreeWordCounter class

// TODO: Implement the LinkedHashWordCounter class

// TODO: Implement the TextAnalyzer class
