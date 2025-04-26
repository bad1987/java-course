/**
 * ZipFilesExample.java
 * This program demonstrates working with ZIP files in Java.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFilesExample {
    public static void main(String[] args) {
        System.out.println("--- ZIP Files Examples ---");
        
        // Example 1: Creating a ZIP file
        System.out.println("\nExample 1: Creating a ZIP file");
        createZipFile();
        
        // Example 2: Reading a ZIP file
        System.out.println("\nExample 2: Reading a ZIP file");
        readZipFile();
        
        // Example 3: Adding files to an existing ZIP file
        System.out.println("\nExample 3: Adding files to an existing ZIP file");
        addToZipFile();
        
        // Example 4: Extracting files from a ZIP file
        System.out.println("\nExample 4: Extracting files from a ZIP file");
        extractZipFile();
        
        // Example 5: Working with ZIP file comments
        System.out.println("\nExample 5: Working with ZIP file comments");
        zipFileComments();
        
        // Example 6: Compressing with different levels
        System.out.println("\nExample 6: Compressing with different levels");
        compressionLevels();
        
        // Example 7: Creating a ZIP file from a directory
        System.out.println("\nExample 7: Creating a ZIP file from a directory");
        zipDirectory();
    }
    
    /**
     * Demonstrates creating a ZIP file.
     */
    public static void createZipFile() {
        // Create some test files
        createTestFiles();
        
        // Create a ZIP file
        try (ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream("example.zip"))) {
            
            // Add file 1
            addToZip(zos, "test1.txt");
            
            // Add file 2
            addToZip(zos, "test2.txt");
            
            // Add file 3
            addToZip(zos, "test3.txt");
            
            System.out.println("ZIP file created: example.zip");
        } catch (IOException e) {
            System.out.println("Error creating ZIP file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading a ZIP file.
     */
    public static void readZipFile() {
        // Method 1: Using ZipFile
        try (ZipFile zipFile = new ZipFile("example.zip")) {
            System.out.println("Reading ZIP file using ZipFile:");
            
            // Get the number of entries
            System.out.println("Number of entries: " + zipFile.size());
            
            // Get all entries
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                
                System.out.println("\nEntry: " + entry.getName());
                System.out.println("Size: " + entry.getSize() + " bytes");
                System.out.println("Compressed size: " + entry.getCompressedSize() + " bytes");
                System.out.println("Method: " + (entry.getMethod() == ZipEntry.STORED ? 
                                               "STORED" : "DEFLATED"));
                
                // Read the entry content
                if (!entry.isDirectory()) {
                    try (BufferedInputStream bis = new BufferedInputStream(
                            zipFile.getInputStream(entry))) {
                        
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        
                        while ((len = bis.read(buffer)) > 0) {
                            baos.write(buffer, 0, len);
                        }
                        
                        String content = baos.toString(StandardCharsets.UTF_8);
                        System.out.println("Content: " + content);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading ZIP file: " + e.getMessage());
        }
        
        // Method 2: Using ZipInputStream
        try (ZipInputStream zis = new ZipInputStream(
                new FileInputStream("example.zip"))) {
            
            System.out.println("\nReading ZIP file using ZipInputStream:");
            
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("\nEntry: " + entry.getName());
                System.out.println("Size: " + entry.getSize() + " bytes");
                
                // Read the entry content
                if (!entry.isDirectory()) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    
                    while ((len = zis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }
                    
                    String content = baos.toString(StandardCharsets.UTF_8);
                    System.out.println("Content: " + content);
                }
                
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.out.println("Error reading ZIP file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates adding files to an existing ZIP file.
     */
    public static void addToZipFile() {
        // Create a new test file
        try {
            Files.writeString(Paths.get("test4.txt"), "This is test file 4.");
            System.out.println("Created test4.txt");
        } catch (IOException e) {
            System.out.println("Error creating test file: " + e.getMessage());
            return;
        }
        
        // We can't directly append to a ZIP file, so we need to:
        // 1. Read the existing ZIP file
        // 2. Create a new ZIP file
        // 3. Copy all entries from the old file to the new file
        // 4. Add the new entries
        // 5. Replace the old file with the new file
        
        try {
            // Create a temporary file
            Path tempFile = Files.createTempFile("temp", ".zip");
            
            // Open the existing ZIP file for reading
            try (ZipInputStream zis = new ZipInputStream(
                    new FileInputStream("example.zip"));
                 
                 // Open the temporary file for writing
                 ZipOutputStream zos = new ZipOutputStream(
                    new FileOutputStream(tempFile.toFile()))) {
                
                // Copy existing entries
                ZipEntry entry;
                byte[] buffer = new byte[1024];
                int len;
                
                while ((entry = zis.getNextEntry()) != null) {
                    // Create a new entry in the output file
                    zos.putNextEntry(new ZipEntry(entry.getName()));
                    
                    // Copy the entry content
                    while ((len = zis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                    
                    zis.closeEntry();
                    zos.closeEntry();
                }
                
                // Add the new file
                addToZip(zos, "test4.txt");
                
                System.out.println("Added test4.txt to the ZIP file");
            }
            
            // Replace the original file with the temporary file
            Files.move(tempFile, Paths.get("example.zip"), 
                      java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            
        } catch (IOException e) {
            System.out.println("Error adding to ZIP file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates extracting files from a ZIP file.
     */
    public static void extractZipFile() {
        // Create an extraction directory
        Path extractDir = Paths.get("extracted");
        try {
            if (!Files.exists(extractDir)) {
                Files.createDirectory(extractDir);
            }
        } catch (IOException e) {
            System.out.println("Error creating extraction directory: " + e.getMessage());
            return;
        }
        
        // Extract using ZipInputStream
        try (ZipInputStream zis = new ZipInputStream(
                new FileInputStream("example.zip"))) {
            
            ZipEntry entry;
            byte[] buffer = new byte[1024];
            
            while ((entry = zis.getNextEntry()) != null) {
                Path filePath = extractDir.resolve(entry.getName());
                
                System.out.println("Extracting: " + entry.getName());
                
                // Create directories if needed
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    // Ensure parent directories exist
                    Files.createDirectories(filePath.getParent());
                    
                    // Extract the file
                    try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
                         BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                        
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            bos.write(buffer, 0, len);
                        }
                    }
                }
                
                zis.closeEntry();
            }
            
            System.out.println("Files extracted to: " + extractDir);
        } catch (IOException e) {
            System.out.println("Error extracting ZIP file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates working with ZIP file comments.
     */
    public static void zipFileComments() {
        // Create a ZIP file with a comment
        try (ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream("commented.zip"))) {
            
            // Set a comment for the entire ZIP file
            zos.setComment("This is a ZIP file with comments");
            
            // Add a file with a comment
            ZipEntry entry = new ZipEntry("commented.txt");
            entry.setComment("This is a file with a comment");
            zos.putNextEntry(entry);
            
            // Write content to the file
            String content = "This file demonstrates ZIP entry comments.";
            zos.write(content.getBytes());
            
            zos.closeEntry();
            
            System.out.println("Created ZIP file with comments: commented.zip");
        } catch (IOException e) {
            System.out.println("Error creating ZIP file with comments: " + e.getMessage());
        }
        
        // Read the ZIP file and its comments
        try (ZipFile zipFile = new ZipFile("commented.zip")) {
            // Get the ZIP file comment
            String comment = zipFile.getComment();
            System.out.println("\nZIP file comment: " + comment);
            
            // Get the entry and its comment
            ZipEntry entry = zipFile.getEntry("commented.txt");
            if (entry != null) {
                String entryComment = entry.getComment();
                System.out.println("Entry comment: " + entryComment);
                
                // Read the entry content
                try (BufferedInputStream bis = new BufferedInputStream(
                        zipFile.getInputStream(entry))) {
                    
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    
                    while ((len = bis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }
                    
                    String content = baos.toString(StandardCharsets.UTF_8);
                    System.out.println("Content: " + content);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading ZIP file comments: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates compressing with different levels.
     */
    public static void compressionLevels() {
        // Create a large test file
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("This is line ").append(i).append(" of the test file.\n");
            }
            
            Files.writeString(Paths.get("large_test.txt"), sb.toString());
            System.out.println("Created large_test.txt");
            
            // Get the original file size
            long originalSize = Files.size(Paths.get("large_test.txt"));
            System.out.println("Original file size: " + originalSize + " bytes");
        } catch (IOException e) {
            System.out.println("Error creating large test file: " + e.getMessage());
            return;
        }
        
        // Compression levels to test
        int[] levels = {
            Deflater.NO_COMPRESSION,
            Deflater.BEST_SPEED,
            Deflater.DEFAULT_COMPRESSION,
            Deflater.BEST_COMPRESSION
        };
        
        String[] levelNames = {
            "NO_COMPRESSION",
            "BEST_SPEED",
            "DEFAULT_COMPRESSION",
            "BEST_COMPRESSION"
        };
        
        // Test each compression level
        for (int i = 0; i < levels.length; i++) {
            int level = levels[i];
            String levelName = levelNames[i];
            
            String zipFileName = "compression_" + levelName + ".zip";
            
            try (ZipOutputStream zos = new ZipOutputStream(
                    new FileOutputStream(zipFileName))) {
                
                // Set the compression level
                zos.setLevel(level);
                
                // Add the large test file
                ZipEntry entry = new ZipEntry("large_test.txt");
                zos.putNextEntry(entry);
                
                // Write the file content
                Files.copy(Paths.get("large_test.txt"), zos);
                
                zos.closeEntry();
                
                System.out.println("\nCreated " + zipFileName + " with compression level " + levelName);
            } catch (IOException e) {
                System.out.println("Error creating ZIP with compression level " + levelName + 
                                  ": " + e.getMessage());
                continue;
            }
            
            // Get the compressed file size
            try {
                long compressedSize = Files.size(Paths.get(zipFileName));
                long originalSize = Files.size(Paths.get("large_test.txt"));
                
                double ratio = (double) compressedSize / originalSize * 100;
                
                System.out.println("Compression level: " + levelName);
                System.out.println("Compressed size: " + compressedSize + " bytes");
                System.out.println("Compression ratio: " + String.format("%.2f", ratio) + "%");
            } catch (IOException e) {
                System.out.println("Error getting file size: " + e.getMessage());
            }
        }
    }
    
    /**
     * Demonstrates creating a ZIP file from a directory.
     */
    public static void zipDirectory() {
        // Create a directory structure
        try {
            // Create the main directory
            Path dirPath = Paths.get("test_dir");
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            
            // Create a subdirectory
            Path subDirPath = dirPath.resolve("subdir");
            if (!Files.exists(subDirPath)) {
                Files.createDirectory(subDirPath);
            }
            
            // Create some files
            Files.writeString(dirPath.resolve("file1.txt"), "This is file 1 in the main directory.");
            Files.writeString(dirPath.resolve("file2.txt"), "This is file 2 in the main directory.");
            Files.writeString(subDirPath.resolve("file3.txt"), "This is file 3 in the subdirectory.");
            
            System.out.println("Created directory structure for zipping");
        } catch (IOException e) {
            System.out.println("Error creating directory structure: " + e.getMessage());
            return;
        }
        
        // Zip the directory
        try (ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream("directory.zip"))) {
            
            Path sourceDirPath = Paths.get("test_dir");
            
            // Walk the directory tree and add all files to the ZIP
            Files.walk(sourceDirPath)
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    try {
                        // Create a ZIP entry with a relative path
                        String relativePath = sourceDirPath.relativize(path).toString();
                        ZipEntry entry = new ZipEntry(relativePath);
                        zos.putNextEntry(entry);
                        
                        // Write the file content
                        Files.copy(path, zos);
                        
                        zos.closeEntry();
                        
                        System.out.println("Added to ZIP: " + relativePath);
                    } catch (IOException e) {
                        System.out.println("Error adding file to ZIP: " + e.getMessage());
                    }
                });
            
            System.out.println("\nDirectory zipped to directory.zip");
        } catch (IOException e) {
            System.out.println("Error zipping directory: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to create test files.
     */
    private static void createTestFiles() {
        try {
            Files.writeString(Paths.get("test1.txt"), "This is test file 1.");
            Files.writeString(Paths.get("test2.txt"), "This is test file 2.");
            Files.writeString(Paths.get("test3.txt"), "This is test file 3.");
            
            System.out.println("Created test files: test1.txt, test2.txt, test3.txt");
        } catch (IOException e) {
            System.out.println("Error creating test files: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to add a file to a ZIP output stream.
     */
    private static void addToZip(ZipOutputStream zos, String fileName) throws IOException {
        // Create a new entry
        ZipEntry entry = new ZipEntry(fileName);
        zos.putNextEntry(entry);
        
        // Write the file content
        try (FileInputStream fis = new FileInputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int len;
            
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
        }
        
        zos.closeEntry();
        System.out.println("Added to ZIP: " + fileName);
    }
    
    /**
     * Clean up method to delete test files and directories.
     * This method is not called in the main method to allow inspection of the created files.
     */
    public static void cleanUp() {
        try {
            // Delete test files
            Files.deleteIfExists(Paths.get("test1.txt"));
            Files.deleteIfExists(Paths.get("test2.txt"));
            Files.deleteIfExists(Paths.get("test3.txt"));
            Files.deleteIfExists(Paths.get("test4.txt"));
            Files.deleteIfExists(Paths.get("large_test.txt"));
            
            // Delete ZIP files
            Files.deleteIfExists(Paths.get("example.zip"));
            Files.deleteIfExists(Paths.get("commented.zip"));
            Files.deleteIfExists(Paths.get("directory.zip"));
            Files.deleteIfExists(Paths.get("compression_NO_COMPRESSION.zip"));
            Files.deleteIfExists(Paths.get("compression_BEST_SPEED.zip"));
            Files.deleteIfExists(Paths.get("compression_DEFAULT_COMPRESSION.zip"));
            Files.deleteIfExists(Paths.get("compression_BEST_COMPRESSION.zip"));
            
            // Delete extracted files
            deleteDirectory(Paths.get("extracted").toFile());
            
            // Delete test directory
            deleteDirectory(Paths.get("test_dir").toFile());
            
            System.out.println("Cleaned up all test files and directories");
        } catch (IOException e) {
            System.out.println("Error during cleanup: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to recursively delete a directory.
     */
    private static void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}
