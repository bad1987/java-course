/**
 * NIOExample.java
 * This program demonstrates the use of Java NIO (New I/O) for file operations.
 */
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIOExample {
    public static void main(String[] args) {
        System.out.println("--- Java NIO Examples ---");
        
        // Example 1: Working with Path objects
        System.out.println("\nExample 1: Working with Path objects");
        workWithPaths();
        
        // Example 2: Reading and writing files with Files class
        System.out.println("\nExample 2: Reading and writing files with Files class");
        readWriteWithFiles();
        
        // Example 3: File operations (copy, move, delete)
        System.out.println("\nExample 3: File operations (copy, move, delete)");
        fileOperations();
        
        // Example 4: Directory operations
        System.out.println("\nExample 4: Directory operations");
        directoryOperations();
        
        // Example 5: Walking a directory tree
        System.out.println("\nExample 5: Walking a directory tree");
        walkDirectoryTree();
        
        // Example 6: Using channels and buffers
        System.out.println("\nExample 6: Using channels and buffers");
        useChannelsAndBuffers();
        
        // Example 7: File attributes
        System.out.println("\nExample 7: File attributes");
        workWithFileAttributes();
    }
    
    /**
     * Demonstrates working with Path objects.
     */
    public static void workWithPaths() {
        // Creating Path objects
        Path path1 = Paths.get("example.txt");
        Path path2 = Paths.get("/home", "user", "documents", "file.txt");
        Path path3 = Paths.get("C:\\Users\\user\\Documents\\file.txt");
        
        System.out.println("Path 1: " + path1);
        System.out.println("Path 2: " + path2);
        System.out.println("Path 3: " + path3);
        
        // Getting information from a Path
        Path examplePath = Paths.get("/home/user/documents/example.txt");
        
        System.out.println("\nPath information:");
        System.out.println("toString: " + examplePath.toString());
        System.out.println("getFileName: " + examplePath.getFileName());
        System.out.println("getName(0): " + examplePath.getName(0));
        System.out.println("getNameCount: " + examplePath.getNameCount());
        System.out.println("subpath(1,3): " + examplePath.subpath(1, 3));
        System.out.println("getParent: " + examplePath.getParent());
        System.out.println("getRoot: " + examplePath.getRoot());
        
        // Normalizing paths
        Path relativePath = Paths.get("docs/../photos/./vacation/pic.jpg");
        System.out.println("\nOriginal path: " + relativePath);
        System.out.println("Normalized path: " + relativePath.normalize());
        
        // Resolving paths
        Path basePath = Paths.get("/home/user");
        Path resolvedPath = basePath.resolve("documents/file.txt");
        System.out.println("\nBase path: " + basePath);
        System.out.println("Resolved path: " + resolvedPath);
        
        // Relativizing paths
        Path path4 = Paths.get("/home/user/documents");
        Path path5 = Paths.get("/home/user/photos/vacation");
        Path relativizedPath = path4.relativize(path5);
        System.out.println("\nPath 4: " + path4);
        System.out.println("Path 5: " + path5);
        System.out.println("Relativized path: " + relativizedPath);
        
        // Converting between Path and File
        java.io.File file = path1.toFile();
        Path pathFromFile = file.toPath();
        
        System.out.println("\nFile: " + file);
        System.out.println("Path from file: " + pathFromFile);
    }
    
    /**
     * Demonstrates reading and writing files using the Files class.
     */
    public static void readWriteWithFiles() {
        Path filePath = Paths.get("nio_test.txt");
        
        // Writing to a file
        try {
            // Write string to file
            Files.writeString(filePath, "Hello, Java NIO!\n", StandardCharsets.UTF_8);
            
            // Append to file
            Files.writeString(filePath, "This is a test of Files.writeString().\n", 
                             StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            
            // Write lines to file
            List<String> lines = List.of(
                "Line 1 - Files.write()",
                "Line 2 - with multiple lines",
                "Line 3 - using a List<String>"
            );
            Files.write(filePath, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            
            System.out.println("Data written to " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
        // Reading from a file
        try {
            // Read entire file as a string
            String content = Files.readString(filePath, StandardCharsets.UTF_8);
            System.out.println("\nFile content (readString):");
            System.out.println(content);
            
            // Read file as lines
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            System.out.println("\nFile content (readAllLines):");
            for (String line : lines) {
                System.out.println(line);
            }
            
            // Read file as bytes
            byte[] bytes = Files.readAllBytes(filePath);
            System.out.println("\nFile size: " + bytes.length + " bytes");
            
            // Read file using a Stream (Java 8+)
            System.out.println("\nFile content (lines() stream):");
            try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
                stream.forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates file operations like copy, move, and delete.
     */
    public static void fileOperations() {
        // Create source file
        Path sourcePath = Paths.get("source_nio.txt");
        try {
            Files.writeString(sourcePath, "This is the source file for NIO operations.");
            System.out.println("Source file created: " + sourcePath);
        } catch (IOException e) {
            System.out.println("Error creating source file: " + e.getMessage());
            return;
        }
        
        // Copy file
        Path targetPath = Paths.get("target_nio.txt");
        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied to: " + targetPath);
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        
        // Move/rename file
        Path movedPath = Paths.get("moved_nio.txt");
        try {
            Files.move(targetPath, movedPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved/renamed to: " + movedPath);
        } catch (IOException e) {
            System.out.println("Error moving file: " + e.getMessage());
        }
        
        // Delete files
        try {
            boolean deleted1 = Files.deleteIfExists(sourcePath);
            boolean deleted2 = Files.deleteIfExists(movedPath);
            
            System.out.println("Source file deleted: " + deleted1);
            System.out.println("Moved file deleted: " + deleted2);
        } catch (IOException e) {
            System.out.println("Error deleting files: " + e.getMessage());
        }
        
        // Create a temporary file
        try {
            Path tempFile = Files.createTempFile("nio_", ".tmp");
            System.out.println("Temporary file created: " + tempFile);
            
            // Write some data to the temp file
            Files.writeString(tempFile, "This is a temporary file.");
            
            // Delete the temp file when the JVM exits
            tempFile.toFile().deleteOnExit();
        } catch (IOException e) {
            System.out.println("Error working with temporary file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates directory operations.
     */
    public static void directoryOperations() {
        // Create directories
        Path dirPath = Paths.get("nio_test_dir");
        Path nestedDirPath = Paths.get("nio_test_dir/nested/deep");
        
        try {
            // Create a single directory
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
                System.out.println("Directory created: " + dirPath);
            } else {
                System.out.println("Directory already exists: " + dirPath);
            }
            
            // Create multiple directory levels
            if (!Files.exists(nestedDirPath)) {
                Files.createDirectories(nestedDirPath);
                System.out.println("Nested directories created: " + nestedDirPath);
            } else {
                System.out.println("Nested directories already exist: " + nestedDirPath);
            }
        } catch (IOException e) {
            System.out.println("Error creating directories: " + e.getMessage());
        }
        
        // Create some files in the directory
        try {
            Files.writeString(dirPath.resolve("file1.txt"), "Content of file 1");
            Files.writeString(dirPath.resolve("file2.txt"), "Content of file 2");
            Files.writeString(nestedDirPath.resolve("nested_file.txt"), "Content of nested file");
            
            System.out.println("Files created in directories");
        } catch (IOException e) {
            System.out.println("Error creating files in directories: " + e.getMessage());
        }
        
        // List directory contents
        try {
            System.out.println("\nListing directory contents:");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
                for (Path entry : stream) {
                    String type = Files.isDirectory(entry) ? "Directory" : "File";
                    System.out.println(type + ": " + entry.getFileName());
                }
            }
            
            // List only text files
            System.out.println("\nListing only .txt files:");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.txt")) {
                for (Path entry : stream) {
                    System.out.println("File: " + entry.getFileName());
                }
            }
            
            // List using a filter
            System.out.println("\nListing files larger than 10 bytes:");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, 
                    path -> Files.isRegularFile(path) && Files.size(path) > 10)) {
                for (Path entry : stream) {
                    System.out.println("File: " + entry.getFileName() + 
                                      " (" + Files.size(entry) + " bytes)");
                }
            }
        } catch (IOException e) {
            System.out.println("Error listing directory contents: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates walking a directory tree.
     */
    public static void walkDirectoryTree() {
        Path rootDir = Paths.get("nio_test_dir");
        
        // Using Files.walk
        try {
            System.out.println("Walking directory tree with Files.walk:");
            try (Stream<Path> paths = Files.walk(rootDir)) {
                List<Path> pathList = paths.collect(Collectors.toList());
                for (Path path : pathList) {
                    String type = Files.isDirectory(path) ? "Directory" : "File";
                    System.out.println(type + ": " + path);
                }
            }
        } catch (IOException e) {
            System.out.println("Error walking directory tree: " + e.getMessage());
        }
        
        // Using Files.walkFileTree with SimpleFileVisitor
        try {
            System.out.println("\nWalking directory tree with Files.walkFileTree:");
            Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    System.out.println("About to visit directory: " + dir);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    System.out.println("Visited file: " + file);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.out.println("Failed to visit file: " + file);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    System.out.println("Done visiting directory: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println("Error walking directory tree: " + e.getMessage());
        }
        
        // Clean up - delete the test directory and its contents
        try {
            System.out.println("\nCleaning up - deleting test directory and contents");
            Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    System.out.println("Deleted file: " + file);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    System.out.println("Deleted directory: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println("Error deleting directory tree: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates using channels and buffers for I/O operations.
     */
    public static void useChannelsAndBuffers() {
        Path filePath = Paths.get("channel_test.txt");
        
        // Writing to a file using a channel
        try (FileChannel channel = FileChannel.open(filePath, 
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            
            // Create a buffer and put data into it
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("Hello, FileChannel!".getBytes());
            
            // Flip the buffer to prepare for writing
            buffer.flip();
            
            // Write the buffer to the channel
            int bytesWritten = channel.write(buffer);
            
            System.out.println("Bytes written to channel: " + bytesWritten);
        } catch (IOException e) {
            System.out.println("Error writing to channel: " + e.getMessage());
        }
        
        // Reading from a file using a channel
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            // Create a buffer for reading
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            
            // Read data from the channel into the buffer
            int bytesRead = channel.read(buffer);
            
            // Flip the buffer to prepare for reading
            buffer.flip();
            
            // Read the data from the buffer
            byte[] data = new byte[bytesRead];
            buffer.get(data);
            
            String content = new String(data);
            System.out.println("Read from channel: " + content);
            
            // Get the file size
            long fileSize = channel.size();
            System.out.println("File size: " + fileSize + " bytes");
        } catch (IOException e) {
            System.out.println("Error reading from channel: " + e.getMessage());
        }
        
        // Copying a file using channels
        Path sourcePath = filePath;
        Path targetPath = Paths.get("channel_copy.txt");
        
        try (FileChannel sourceChannel = FileChannel.open(sourcePath, StandardOpenOption.READ);
             FileChannel targetChannel = FileChannel.open(targetPath, 
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            
            // Get the source channel size
            long size = sourceChannel.size();
            
            // Copy from source to target
            sourceChannel.transferTo(0, size, targetChannel);
            
            System.out.println("File copied using channels: " + size + " bytes");
        } catch (IOException e) {
            System.out.println("Error copying with channels: " + e.getMessage());
        }
        
        // Clean up
        try {
            Files.deleteIfExists(filePath);
            Files.deleteIfExists(targetPath);
            System.out.println("Test files deleted");
        } catch (IOException e) {
            System.out.println("Error deleting test files: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates working with file attributes.
     */
    public static void workWithFileAttributes() {
        Path filePath = Paths.get("attributes_test.txt");
        
        // Create a test file
        try {
            Files.writeString(filePath, "File for testing attributes");
            System.out.println("Test file created: " + filePath);
        } catch (IOException e) {
            System.out.println("Error creating test file: " + e.getMessage());
            return;
        }
        
        // Check basic file attributes
        try {
            System.out.println("\nBasic file attributes:");
            System.out.println("Exists: " + Files.exists(filePath));
            System.out.println("Is regular file: " + Files.isRegularFile(filePath));
            System.out.println("Is directory: " + Files.isDirectory(filePath));
            System.out.println("Is symbolic link: " + Files.isSymbolicLink(filePath));
            System.out.println("Is hidden: " + Files.isHidden(filePath));
            System.out.println("Is readable: " + Files.isReadable(filePath));
            System.out.println("Is writable: " + Files.isWritable(filePath));
            System.out.println("Is executable: " + Files.isExecutable(filePath));
            System.out.println("Size: " + Files.size(filePath) + " bytes");
            System.out.println("Last modified time: " + Files.getLastModifiedTime(filePath));
            System.out.println("Owner: " + Files.getOwner(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file attributes: " + e.getMessage());
        }
        
        // Read all basic file attributes at once
        try {
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            
            System.out.println("\nAll basic attributes at once:");
            System.out.println("Creation time: " + attrs.creationTime());
            System.out.println("Last access time: " + attrs.lastAccessTime());
            System.out.println("Last modified time: " + attrs.lastModifiedTime());
            System.out.println("Is directory: " + attrs.isDirectory());
            System.out.println("Is regular file: " + attrs.isRegularFile());
            System.out.println("Is symbolic link: " + attrs.isSymbolicLink());
            System.out.println("Size: " + attrs.size() + " bytes");
        } catch (IOException e) {
            System.out.println("Error reading basic file attributes: " + e.getMessage());
        }
        
        // Set file attributes
        try {
            // Set last modified time
            Files.setLastModifiedTime(filePath, java.nio.file.attribute.FileTime.fromMillis(
                    System.currentTimeMillis() - 86400000)); // 1 day ago
            
            System.out.println("\nAfter setting last modified time:");
            System.out.println("Last modified time: " + Files.getLastModifiedTime(filePath));
            
            // Set permissions (this may not work on all platforms)
            try {
                Files.setPosixFilePermissions(filePath, 
                        java.nio.file.attribute.PosixFilePermissions.fromString("rw-r--r--"));
                System.out.println("Permissions set successfully");
            } catch (UnsupportedOperationException e) {
                System.out.println("Setting POSIX permissions not supported on this platform");
            }
        } catch (IOException e) {
            System.out.println("Error setting file attributes: " + e.getMessage());
        }
        
        // Clean up
        try {
            Files.delete(filePath);
            System.out.println("Test file deleted");
        } catch (IOException e) {
            System.out.println("Error deleting test file: " + e.getMessage());
        }
    }
}
