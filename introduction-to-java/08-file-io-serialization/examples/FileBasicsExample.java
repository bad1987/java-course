/**
 * FileBasicsExample.java
 * This program demonstrates basic file operations using the File class.
 */
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileBasicsExample {
    public static void main(String[] args) {
        System.out.println("--- File Basics Examples ---");
        
        // Example 1: Creating File objects
        System.out.println("\nExample 1: Creating File objects");
        createFileObjects();
        
        // Example 2: Getting file information
        System.out.println("\nExample 2: Getting file information");
        getFileInformation();
        
        // Example 3: Creating files and directories
        System.out.println("\nExample 3: Creating files and directories");
        createFilesAndDirectories();
        
        // Example 4: Listing directory contents
        System.out.println("\nExample 4: Listing directory contents");
        listDirectoryContents();
        
        // Example 5: File operations (rename, delete)
        System.out.println("\nExample 5: File operations (rename, delete)");
        fileOperations();
        
        // Example 6: Working with file paths
        System.out.println("\nExample 6: Working with file paths");
        workWithFilePaths();
        
        // Example 7: Temporary files
        System.out.println("\nExample 7: Temporary files");
        workWithTemporaryFiles();
    }
    
    /**
     * Demonstrates creating File objects.
     */
    public static void createFileObjects() {
        // Creating a File object for a file
        File file1 = new File("example.txt");
        System.out.println("File object created: " + file1.getPath());
        
        // Creating a File object with absolute path
        File file2 = new File("C:/temp/data.txt");
        System.out.println("File object with absolute path: " + file2.getPath());
        
        // Creating a File object for a directory
        File directory = new File("myDirectory");
        System.out.println("Directory object created: " + directory.getPath());
        
        // Creating a File object with parent and child
        File parentDir = new File("parent");
        File childFile = new File(parentDir, "child.txt");
        System.out.println("Child file path: " + childFile.getPath());
        
        // Creating a File object with a relative path
        File relativeFile = new File("./data/config.properties");
        System.out.println("Relative file path: " + relativeFile.getPath());
        System.out.println("Absolute file path: " + relativeFile.getAbsolutePath());
    }
    
    /**
     * Demonstrates getting information about files.
     */
    public static void getFileInformation() {
        // Create a File object for an existing file
        File file = new File("FileBasicsExample.java");
        
        // Check if the file exists
        System.out.println("Does file exist? " + file.exists());
        
        if (file.exists()) {
            // Get basic file information
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getPath());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Canonical path: " + getCanonicalPath(file));
            System.out.println("Parent directory: " + file.getParent());
            
            // Get file properties
            System.out.println("Is file? " + file.isFile());
            System.out.println("Is directory? " + file.isDirectory());
            System.out.println("Is hidden? " + file.isHidden());
            System.out.println("Can read? " + file.canRead());
            System.out.println("Can write? " + file.canWrite());
            System.out.println("Can execute? " + file.canExecute());
            
            // Get file size and timestamps
            System.out.println("File size: " + file.length() + " bytes");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Last modified: " + sdf.format(new Date(file.lastModified())));
        } else {
            System.out.println("File does not exist: " + file.getPath());
        }
        
        // Check a directory
        File directory = new File(".");
        System.out.println("\nCurrent directory exists? " + directory.exists());
        System.out.println("Is directory? " + directory.isDirectory());
        System.out.println("Absolute path: " + directory.getAbsolutePath());
    }
    
    /**
     * Demonstrates creating files and directories.
     */
    public static void createFilesAndDirectories() {
        // Create a new file
        File newFile = new File("test_file.txt");
        
        try {
            boolean created = newFile.createNewFile();
            if (created) {
                System.out.println("File created: " + newFile.getPath());
            } else {
                System.out.println("File already exists: " + newFile.getPath());
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
        
        // Create a directory
        File newDir = new File("test_directory");
        boolean dirCreated = newDir.mkdir();
        if (dirCreated) {
            System.out.println("Directory created: " + newDir.getPath());
        } else {
            System.out.println("Failed to create directory or it already exists: " + newDir.getPath());
        }
        
        // Create multiple directory levels
        File nestedDirs = new File("parent/child/grandchild");
        boolean nestedCreated = nestedDirs.mkdirs();
        if (nestedCreated) {
            System.out.println("Nested directories created: " + nestedDirs.getPath());
        } else {
            System.out.println("Failed to create nested directories or they already exist: " + nestedDirs.getPath());
        }
    }
    
    /**
     * Demonstrates listing directory contents.
     */
    public static void listDirectoryContents() {
        // List files in the current directory
        File currentDir = new File(".");
        
        System.out.println("Contents of directory: " + currentDir.getAbsolutePath());
        
        // List file names
        String[] fileNames = currentDir.list();
        if (fileNames != null) {
            System.out.println("\nFile names:");
            for (String fileName : fileNames) {
                System.out.println("- " + fileName);
            }
        }
        
        // List file objects
        File[] files = currentDir.listFiles();
        if (files != null) {
            System.out.println("\nFile details:");
            for (File file : files) {
                String type = file.isDirectory() ? "Directory" : "File";
                String size = file.isFile() ? file.length() + " bytes" : "";
                System.out.println("- " + type + ": " + file.getName() + " " + size);
            }
        }
        
        // Filter files using FilenameFilter
        System.out.println("\nJava files only:");
        String[] javaFiles = currentDir.list((dir, name) -> name.endsWith(".java"));
        if (javaFiles != null) {
            for (String javaFile : javaFiles) {
                System.out.println("- " + javaFile);
            }
        }
        
        // Filter files using FileFilter
        System.out.println("\nDirectories only:");
        File[] directories = currentDir.listFiles(File::isDirectory);
        if (directories != null) {
            for (File directory : directories) {
                System.out.println("- " + directory.getName());
            }
        }
    }
    
    /**
     * Demonstrates file operations like rename and delete.
     */
    public static void fileOperations() {
        // Create a test file
        File originalFile = new File("original.txt");
        
        try {
            boolean created = originalFile.createNewFile();
            if (created) {
                System.out.println("File created: " + originalFile.getPath());
                
                // Rename the file
                File renamedFile = new File("renamed.txt");
                boolean renamed = originalFile.renameTo(renamedFile);
                if (renamed) {
                    System.out.println("File renamed to: " + renamedFile.getPath());
                    
                    // Delete the renamed file
                    boolean deleted = renamedFile.delete();
                    if (deleted) {
                        System.out.println("File deleted: " + renamedFile.getPath());
                    } else {
                        System.out.println("Failed to delete file: " + renamedFile.getPath());
                    }
                } else {
                    System.out.println("Failed to rename file");
                    
                    // Delete the original file if rename failed
                    boolean deleted = originalFile.delete();
                    if (deleted) {
                        System.out.println("Original file deleted: " + originalFile.getPath());
                    }
                }
            } else {
                System.out.println("File already exists: " + originalFile.getPath());
            }
        } catch (IOException e) {
            System.out.println("Error during file operations: " + e.getMessage());
        }
        
        // Create and delete a directory
        File testDir = new File("test_dir_to_delete");
        boolean dirCreated = testDir.mkdir();
        if (dirCreated) {
            System.out.println("Directory created: " + testDir.getPath());
            
            boolean dirDeleted = testDir.delete();
            if (dirDeleted) {
                System.out.println("Directory deleted: " + testDir.getPath());
            } else {
                System.out.println("Failed to delete directory: " + testDir.getPath());
            }
        }
        
        // Create a directory with files and try to delete it
        File dirWithFiles = new File("dir_with_files");
        if (dirWithFiles.mkdir()) {
            try {
                File fileInDir = new File(dirWithFiles, "file_in_dir.txt");
                fileInDir.createNewFile();
                
                boolean dirWithFilesDeleted = dirWithFiles.delete();
                if (!dirWithFilesDeleted) {
                    System.out.println("Cannot delete directory with files: " + dirWithFiles.getPath());
                    
                    // Delete the file first, then the directory
                    if (fileInDir.delete()) {
                        System.out.println("Deleted file in directory: " + fileInDir.getPath());
                        
                        if (dirWithFiles.delete()) {
                            System.out.println("Directory deleted after removing files: " + dirWithFiles.getPath());
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Demonstrates working with file paths.
     */
    public static void workWithFilePaths() {
        // Working with relative and absolute paths
        File relativeFile = new File("data/config.txt");
        System.out.println("Relative path: " + relativeFile.getPath());
        System.out.println("Absolute path: " + relativeFile.getAbsolutePath());
        System.out.println("Canonical path: " + getCanonicalPath(relativeFile));
        
        // Working with parent directories
        File file = new File("/home/user/documents/file.txt");
        System.out.println("\nFile: " + file.getPath());
        System.out.println("Parent: " + file.getParent());
        
        File parent = file.getParentFile();
        if (parent != null) {
            System.out.println("Parent exists? " + parent.exists());
            System.out.println("Parent's parent: " + parent.getParent());
        }
        
        // Resolving paths
        File baseDir = new File("/base/dir");
        File resolvedFile = new File(baseDir, "subdir/file.txt");
        System.out.println("\nResolved path: " + resolvedFile.getPath());
        
        // Separating path components
        String filePath = "/path/to/some/file.txt";
        File pathFile = new File(filePath);
        
        System.out.println("\nPath: " + pathFile.getPath());
        System.out.println("Name: " + pathFile.getName());
        System.out.println("Parent: " + pathFile.getParent());
        
        // Getting file extension
        String fileName = pathFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            String extension = fileName.substring(dotIndex + 1);
            String nameWithoutExtension = fileName.substring(0, dotIndex);
            System.out.println("Extension: " + extension);
            System.out.println("Name without extension: " + nameWithoutExtension);
        }
    }
    
    /**
     * Demonstrates working with temporary files.
     */
    public static void workWithTemporaryFiles() {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("prefix_", "_suffix");
            System.out.println("Temporary file created: " + tempFile.getAbsolutePath());
            
            // Mark the file to be deleted on JVM exit
            tempFile.deleteOnExit();
            System.out.println("File will be deleted when the JVM exits");
            
            // Get the system temporary directory
            String tempDirPath = System.getProperty("java.io.tmpdir");
            System.out.println("System temp directory: " + tempDirPath);
            
            // Create a temporary file in a specific directory
            File customTempDir = new File("custom_temp");
            if (customTempDir.mkdir() || customTempDir.exists()) {
                File customTempFile = File.createTempFile("custom_", ".tmp", customTempDir);
                System.out.println("Custom temp file created: " + customTempFile.getAbsolutePath());
                customTempFile.deleteOnExit();
                customTempDir.deleteOnExit();
            }
        } catch (IOException e) {
            System.out.println("Error working with temporary files: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to get the canonical path of a file.
     */
    private static String getCanonicalPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return "Error getting canonical path: " + e.getMessage();
        }
    }
}
