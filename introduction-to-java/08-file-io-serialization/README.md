# File I/O and Serialization in Java

Welcome to the eighth lesson in our Java programming course! In this section, you'll learn about file input/output (I/O) operations and object serialization in Java. These concepts are essential for reading from and writing to files, as well as for persisting objects to disk or transmitting them over a network.

## 1. Introduction to File I/O

Java provides comprehensive support for file operations through various classes in the `java.io` and `java.nio` packages. The I/O classes are organized into a hierarchy of streams that handle different types of data.

### File Class

The `File` class represents a file or directory path in the file system. It provides methods to create, delete, and get information about files and directories.

```java
// Creating a File object
File file = new File("example.txt");

// Checking if a file exists
boolean exists = file.exists();

// Getting file information
String name = file.getName();
String path = file.getPath();
String absolutePath = file.getAbsolutePath();
long length = file.length();
boolean isDirectory = file.isDirectory();
boolean isFile = file.isFile();

// Creating a new file
boolean created = file.createNewFile();

// Creating directories
File dir = new File("myDirectory");
boolean dirCreated = dir.mkdir();

// Creating multiple directory levels
File dirs = new File("parent/child/grandchild");
boolean dirsCreated = dirs.mkdirs();

// Listing files in a directory
File directory = new File(".");
String[] fileList = directory.list();
File[] fileObjects = directory.listFiles();

// Deleting a file
boolean deleted = file.delete();
```

## 2. Byte Streams

Byte streams handle I/O of raw binary data. The base classes for byte streams are `InputStream` and `OutputStream`.

### FileInputStream and FileOutputStream

These classes are used for reading from and writing to files as streams of bytes.

```java
// Reading bytes from a file
try (FileInputStream fis = new FileInputStream("input.txt")) {
    int data;
    while ((data = fis.read()) != -1) {
        // Process each byte
        System.out.print((char) data);
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Writing bytes to a file
try (FileOutputStream fos = new FileOutputStream("output.txt")) {
    String text = "Hello, World!";
    byte[] bytes = text.getBytes();
    fos.write(bytes);
} catch (IOException e) {
    e.printStackTrace();
}
```

### BufferedInputStream and BufferedOutputStream

These classes add buffering to byte streams, which can significantly improve performance by reducing the number of I/O operations.

```java
// Reading with buffering
try (BufferedInputStream bis = new BufferedInputStream(
        new FileInputStream("input.txt"))) {
    int data;
    while ((data = bis.read()) != -1) {
        System.out.print((char) data);
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Writing with buffering
try (BufferedOutputStream bos = new BufferedOutputStream(
        new FileOutputStream("output.txt"))) {
    String text = "Hello, World!";
    byte[] bytes = text.getBytes();
    bos.write(bytes);
} catch (IOException e) {
    e.printStackTrace();
}
```

## 3. Character Streams

Character streams handle I/O of character data, automatically handling the conversion between characters and bytes. The base classes for character streams are `Reader` and `Writer`.

### FileReader and FileWriter

These classes are used for reading from and writing to files as streams of characters.

```java
// Reading characters from a file
try (FileReader fr = new FileReader("input.txt")) {
    int character;
    while ((character = fr.read()) != -1) {
        System.out.print((char) character);
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Writing characters to a file
try (FileWriter fw = new FileWriter("output.txt")) {
    String text = "Hello, World!";
    fw.write(text);
} catch (IOException e) {
    e.printStackTrace();
}
```

### BufferedReader and BufferedWriter

These classes add buffering to character streams, improving performance and providing additional functionality like reading entire lines.

```java
// Reading lines with BufferedReader
try (BufferedReader br = new BufferedReader(
        new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Writing lines with BufferedWriter
try (BufferedWriter bw = new BufferedWriter(
        new FileWriter("output.txt"))) {
    bw.write("Line 1");
    bw.newLine();
    bw.write("Line 2");
} catch (IOException e) {
    e.printStackTrace();
}
```

## 4. Scanner for File Input

The `Scanner` class provides a convenient way to read formatted input from files.

```java
// Reading a file with Scanner
try (Scanner scanner = new Scanner(new File("input.txt"))) {
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(line);
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
}

// Reading different data types
try (Scanner scanner = new Scanner(new File("data.txt"))) {
    while (scanner.hasNext()) {
        if (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            System.out.println("Integer: " + i);
        } else if (scanner.hasNextDouble()) {
            double d = scanner.nextDouble();
            System.out.println("Double: " + d);
        } else {
            String s = scanner.next();
            System.out.println("String: " + s);
        }
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
```

## 5. PrintWriter for File Output

The `PrintWriter` class provides methods to write formatted text to a file.

```java
// Writing formatted output to a file
try (PrintWriter writer = new PrintWriter(new File("output.txt"))) {
    writer.println("Line 1");
    writer.println("Line 2");
    writer.printf("Formatted: %d, %s, %.2f%n", 42, "Hello", 3.14159);
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
```

## 6. Data Streams

`DataInputStream` and `DataOutputStream` allow you to read and write primitive data types.

```java
// Writing primitive data types
try (DataOutputStream dos = new DataOutputStream(
        new FileOutputStream("data.bin"))) {
    dos.writeInt(42);
    dos.writeDouble(3.14159);
    dos.writeUTF("Hello, World!");
} catch (IOException e) {
    e.printStackTrace();
}

// Reading primitive data types
try (DataInputStream dis = new DataInputStream(
        new FileInputStream("data.bin"))) {
    int i = dis.readInt();
    double d = dis.readDouble();
    String s = dis.readUTF();
    System.out.println("Read: " + i + ", " + d + ", " + s);
} catch (IOException e) {
    e.printStackTrace();
}
```

## 7. Object Serialization

Serialization is the process of converting an object into a byte stream, which can be saved to a file or sent over a network. Deserialization is the reverse process of creating an object from a byte stream.

### Serializable Interface

To make a class serializable, it must implement the `Serializable` interface.

```java
// Serializable class
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
```

### ObjectOutputStream and ObjectInputStream

These classes are used for serializing and deserializing objects.

```java
// Serializing an object
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("person.ser"))) {
    Person person = new Person("Alice", 30);
    oos.writeObject(person);
} catch (IOException e) {
    e.printStackTrace();
}

// Deserializing an object
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("person.ser"))) {
    Person person = (Person) ois.readObject();
    System.out.println("Deserialized: " + person);
} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}
```

### Transient Fields

Fields marked as `transient` are not serialized.

```java
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private transient String password;  // Not serialized
    
    // Constructor, getters, setters...
}
```

### serialVersionUID

The `serialVersionUID` is a version identifier for a serializable class. It's used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization.

```java
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Fields, methods...
}
```

## 8. NIO (New I/O)

Java NIO (New I/O) provides an alternative set of I/O APIs that offer features like non-blocking I/O and better performance in some scenarios.

### Path and Files

The `Path` interface and `Files` class provide methods for file operations.

```java
// Creating a Path
Path path = Paths.get("example.txt");

// Getting path information
String fileName = path.getFileName().toString();
Path absolutePath = path.toAbsolutePath();
Path parent = path.getParent();

// Checking if a file exists
boolean exists = Files.exists(path);

// Creating a file
Files.createFile(path);

// Creating directories
Files.createDirectory(Paths.get("myDirectory"));
Files.createDirectories(Paths.get("parent/child/grandchild"));

// Reading all lines from a file
List<String> lines = Files.readAllLines(path);

// Reading all bytes from a file
byte[] bytes = Files.readAllBytes(path);

// Writing lines to a file
List<String> linesToWrite = Arrays.asList("Line 1", "Line 2");
Files.write(path, linesToWrite);

// Writing bytes to a file
byte[] bytesToWrite = "Hello, World!".getBytes();
Files.write(path, bytesToWrite);

// Copying a file
Path source = Paths.get("source.txt");
Path target = Paths.get("target.txt");
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

// Moving/renaming a file
Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

// Deleting a file
Files.delete(path);
Files.deleteIfExists(path);
```

### Buffered Reading and Writing

NIO provides buffered reading and writing through `BufferedReader` and `BufferedWriter`.

```java
// Reading lines with Files and BufferedReader
try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Writing lines with Files and BufferedWriter
try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"))) {
    writer.write("Line 1");
    writer.newLine();
    writer.write("Line 2");
} catch (IOException e) {
    e.printStackTrace();
}
```

### Directory Operations

NIO provides methods for working with directories.

```java
// Listing directory contents
try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("."))) {
    for (Path entry : stream) {
        System.out.println(entry.getFileName());
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Walking a directory tree
Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        System.out.println("File: " + file);
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println("Failed to visit: " + file);
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        System.out.println("Directory: " + dir);
        return FileVisitResult.CONTINUE;
    }
});
```

## 9. Properties

The `Properties` class is used for storing and loading configuration values.

```java
// Creating and storing properties
Properties properties = new Properties();
properties.setProperty("username", "admin");
properties.setProperty("password", "secret");
properties.setProperty("server", "localhost");

// Saving properties to a file
try (FileOutputStream fos = new FileOutputStream("config.properties")) {
    properties.store(fos, "Configuration Settings");
} catch (IOException e) {
    e.printStackTrace();
}

// Loading properties from a file
Properties loadedProps = new Properties();
try (FileInputStream fis = new FileInputStream("config.properties")) {
    loadedProps.load(fis);
} catch (IOException e) {
    e.printStackTrace();
}

// Accessing properties
String username = loadedProps.getProperty("username");
String password = loadedProps.getProperty("password");
String server = loadedProps.getProperty("server");
```

## 10. Working with ZIP Files

Java provides classes for working with ZIP files.

```java
// Creating a ZIP file
try (ZipOutputStream zos = new ZipOutputStream(
        new FileOutputStream("archive.zip"))) {
    
    // Add file 1
    ZipEntry entry1 = new ZipEntry("file1.txt");
    zos.putNextEntry(entry1);
    byte[] data1 = "Content of file 1".getBytes();
    zos.write(data1, 0, data1.length);
    zos.closeEntry();
    
    // Add file 2
    ZipEntry entry2 = new ZipEntry("file2.txt");
    zos.putNextEntry(entry2);
    byte[] data2 = "Content of file 2".getBytes();
    zos.write(data2, 0, data2.length);
    zos.closeEntry();
    
} catch (IOException e) {
    e.printStackTrace();
}

// Reading a ZIP file
try (ZipInputStream zis = new ZipInputStream(
        new FileInputStream("archive.zip"))) {
    
    ZipEntry entry;
    while ((entry = zis.getNextEntry()) != null) {
        System.out.println("Entry: " + entry.getName());
        
        // Read the entry content
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = zis.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        
        System.out.println("Content: " + baos.toString());
        zis.closeEntry();
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

## Examples

The `examples` directory contains sample code for each topic. Study these examples to see the concepts in action:

- `FileBasicsExample.java`: Demonstrates basic file operations
- `ByteStreamsExample.java`: Shows reading and writing with byte streams
- `CharacterStreamsExample.java`: Illustrates character stream operations
- `ScannerPrintWriterExample.java`: Uses Scanner and PrintWriter for formatted I/O
- `DataStreamsExample.java`: Demonstrates reading and writing primitive data types
- `SerializationExample.java`: Shows object serialization and deserialization
- `NIOExample.java`: Illustrates the use of Java NIO
- `PropertiesExample.java`: Demonstrates working with properties files
- `ZipFilesExample.java`: Shows how to create and read ZIP files

## Exercises

The `exercises` directory contains practice problems to reinforce your understanding:

1. `Exercise1.java`: Create a file copy utility with progress reporting
2. `Exercise2.java`: Implement a simple text editor with file operations
3. `Exercise3.java`: Build a contact manager with serialization
4. `Exercise4.java`: Create a configuration manager using properties

Complete these exercises to practice what you've learned. File I/O and serialization are essential skills for developing Java applications that interact with the file system and persist data!

## Next Steps

After completing this section, proceed to the "Multithreading and Concurrency" section to learn about concurrent programming in Java. This will help you write applications that can perform multiple tasks simultaneously.
