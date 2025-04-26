/**
 * Exercise 3: Dictionary Application
 * 
 * Instructions:
 * 1. Create a Dictionary class that uses a Map to store words and their definitions and
 *    implements the following functionality:
 *    - addWord(String word, String definition): Adds a word and its definition
 *    - removeWord(String word): Removes a word and its definition
 *    - getDefinition(String word): Returns the definition of a word
 *    - containsWord(String word): Checks if a word exists in the dictionary
 *    - updateDefinition(String word, String newDefinition): Updates the definition of a word
 *    - getAllWords(): Returns a set of all words in the dictionary
 *    - getWordCount(): Returns the number of words in the dictionary
 *    - getWordsStartingWith(String prefix): Returns words starting with the specified prefix
 *    - getWordsWithDefinitionContaining(String text): Returns words whose definitions contain the specified text
 *    - clear(): Removes all words from the dictionary
 * 
 * 2. Implement three different versions of the Dictionary class:
 *    - HashDictionary: Uses a HashMap (unordered)
 *    - TreeDictionary: Uses a TreeMap (sorted by word)
 *    - LinkedHashDictionary: Uses a LinkedHashMap (maintains insertion order)
 * 
 * 3. Create a DictionaryManager class that:
 *    - Manages multiple dictionaries (e.g., English, Spanish, Technical)
 *    - Allows adding, removing, and switching between dictionaries
 *    - Provides methods to search across all dictionaries
 *    - Supports importing and exporting dictionaries from/to files
 * 
 * 4. Create a Word class with additional properties:
 *    - word (String)
 *    - definition (String)
 *    - partOfSpeech (enum: NOUN, VERB, ADJECTIVE, etc.)
 *    - examples (List of example sentences)
 *    - synonyms (Set of synonyms)
 *    - antonyms (Set of antonyms)
 * 
 * 5. Enhance the Dictionary class to use Word objects instead of simple strings
 * 
 * 6. Create a DictionaryApp class with a main method that:
 *    - Creates a DictionaryManager
 *    - Loads sample dictionaries (or allows the user to create them)
 *    - Provides a menu-driven interface for the user to interact with the dictionaries
 *    - Implements features like word lookup, adding new words, quiz mode, etc.
 * 
 * 7. Implement proper exception handling for all operations
 * 
 * 8. Use appropriate Map methods and Java 8+ features (like streams, lambda expressions)
 *    where applicable
 * 
 * 9. Create sample dictionary files for testing
 */
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Exercise3 {
    public static void main(String[] args) {
        // TODO: Implement the DictionaryApp
    }
}

// TODO: Implement the PartOfSpeech enum

// TODO: Implement the Word class

// TODO: Implement the Dictionary interface

// TODO: Implement the HashDictionary class

// TODO: Implement the TreeDictionary class

// TODO: Implement the LinkedHashDictionary class

// TODO: Implement the DictionaryManager class
