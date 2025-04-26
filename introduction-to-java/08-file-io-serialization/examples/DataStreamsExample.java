/**
 * DataStreamsExample.java
 * This program demonstrates reading and writing primitive data types using DataInputStream and DataOutputStream.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamsExample {
    public static void main(String[] args) {
        System.out.println("--- Data Streams Examples ---");
        
        // Example 1: Writing primitive data types
        System.out.println("\nExample 1: Writing primitive data types");
        writePrimitiveTypes();
        
        // Example 2: Reading primitive data types
        System.out.println("\nExample 2: Reading primitive data types");
        readPrimitiveTypes();
        
        // Example 3: Writing and reading arrays of primitive types
        System.out.println("\nExample 3: Writing and reading arrays of primitive types");
        workWithArrays();
        
        // Example 4: Writing and reading records
        System.out.println("\nExample 4: Writing and reading records");
        workWithRecords();
        
        // Example 5: Random access with DataStreams
        System.out.println("\nExample 5: Random access with DataStreams");
        randomAccessWithDataStreams();
    }
    
    /**
     * Demonstrates writing primitive data types using DataOutputStream.
     */
    public static void writePrimitiveTypes() {
        // Create a DataOutputStream
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("primitive_data.bin"))) {
            
            // Write boolean
            dos.writeBoolean(true);
            
            // Write byte
            dos.writeByte(127);
            
            // Write short
            dos.writeShort(32767);
            
            // Write int
            dos.writeInt(2147483647);
            
            // Write long
            dos.writeLong(9223372036854775807L);
            
            // Write float
            dos.writeFloat(3.14159f);
            
            // Write double
            dos.writeDouble(2.71828);
            
            // Write char
            dos.writeChar('A');
            
            // Write String (UTF-8 encoded)
            dos.writeUTF("Hello, DataOutputStream!");
            
            System.out.println("Primitive data written to primitive_data.bin");
            System.out.println("File size: " + dos.size() + " bytes");
        } catch (IOException e) {
            System.out.println("Error writing primitive data: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates reading primitive data types using DataInputStream.
     */
    public static void readPrimitiveTypes() {
        // Create a DataInputStream
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream("primitive_data.bin"))) {
            
            // Read boolean
            boolean booleanValue = dis.readBoolean();
            System.out.println("Boolean: " + booleanValue);
            
            // Read byte
            byte byteValue = dis.readByte();
            System.out.println("Byte: " + byteValue);
            
            // Read short
            short shortValue = dis.readShort();
            System.out.println("Short: " + shortValue);
            
            // Read int
            int intValue = dis.readInt();
            System.out.println("Int: " + intValue);
            
            // Read long
            long longValue = dis.readLong();
            System.out.println("Long: " + longValue);
            
            // Read float
            float floatValue = dis.readFloat();
            System.out.println("Float: " + floatValue);
            
            // Read double
            double doubleValue = dis.readDouble();
            System.out.println("Double: " + doubleValue);
            
            // Read char
            char charValue = dis.readChar();
            System.out.println("Char: " + charValue);
            
            // Read String (UTF-8 encoded)
            String stringValue = dis.readUTF();
            System.out.println("String: " + stringValue);
            
        } catch (EOFException e) {
            System.out.println("End of file reached");
        } catch (IOException e) {
            System.out.println("Error reading primitive data: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates writing and reading arrays of primitive types.
     */
    public static void workWithArrays() {
        // Create arrays of primitive types
        int[] intArray = {1, 2, 3, 4, 5};
        double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};
        char[] charArray = {'H', 'e', 'l', 'l', 'o'};
        
        // Write arrays to file
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("array_data.bin"))) {
            
            // Write array lengths
            dos.writeInt(intArray.length);
            dos.writeInt(doubleArray.length);
            dos.writeInt(charArray.length);
            
            // Write int array
            for (int value : intArray) {
                dos.writeInt(value);
            }
            
            // Write double array
            for (double value : doubleArray) {
                dos.writeDouble(value);
            }
            
            // Write char array
            for (char value : charArray) {
                dos.writeChar(value);
            }
            
            System.out.println("Array data written to array_data.bin");
            System.out.println("File size: " + dos.size() + " bytes");
        } catch (IOException e) {
            System.out.println("Error writing array data: " + e.getMessage());
        }
        
        // Read arrays from file
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream("array_data.bin"))) {
            
            // Read array lengths
            int intArrayLength = dis.readInt();
            int doubleArrayLength = dis.readInt();
            int charArrayLength = dis.readInt();
            
            // Read int array
            int[] readIntArray = new int[intArrayLength];
            for (int i = 0; i < intArrayLength; i++) {
                readIntArray[i] = dis.readInt();
            }
            
            // Read double array
            double[] readDoubleArray = new double[doubleArrayLength];
            for (int i = 0; i < doubleArrayLength; i++) {
                readDoubleArray[i] = dis.readDouble();
            }
            
            // Read char array
            char[] readCharArray = new char[charArrayLength];
            for (int i = 0; i < charArrayLength; i++) {
                readCharArray[i] = dis.readChar();
            }
            
            // Display the read arrays
            System.out.println("\nRead int array:");
            for (int value : readIntArray) {
                System.out.print(value + " ");
            }
            System.out.println();
            
            System.out.println("Read double array:");
            for (double value : readDoubleArray) {
                System.out.print(value + " ");
            }
            System.out.println();
            
            System.out.println("Read char array:");
            for (char value : readCharArray) {
                System.out.print(value);
            }
            System.out.println();
            
        } catch (IOException e) {
            System.out.println("Error reading array data: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates writing and reading records (structured data).
     */
    public static void workWithRecords() {
        // Create some student records
        Student[] students = {
            new Student(1, "Alice", 20, 3.75),
            new Student(2, "Bob", 22, 3.45),
            new Student(3, "Charlie", 21, 3.90)
        };
        
        // Write records to file
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("student_records.bin"))) {
            
            // Write number of records
            dos.writeInt(students.length);
            
            // Write each record
            for (Student student : students) {
                dos.writeInt(student.getId());
                dos.writeUTF(student.getName());
                dos.writeInt(student.getAge());
                dos.writeDouble(student.getGpa());
            }
            
            System.out.println("Student records written to student_records.bin");
            System.out.println("File size: " + dos.size() + " bytes");
        } catch (IOException e) {
            System.out.println("Error writing student records: " + e.getMessage());
        }
        
        // Read records from file
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream("student_records.bin"))) {
            
            // Read number of records
            int numRecords = dis.readInt();
            
            // Read each record
            System.out.println("\nRead student records:");
            for (int i = 0; i < numRecords; i++) {
                int id = dis.readInt();
                String name = dis.readUTF();
                int age = dis.readInt();
                double gpa = dis.readDouble();
                
                Student student = new Student(id, name, age, gpa);
                System.out.println(student);
            }
        } catch (IOException e) {
            System.out.println("Error reading student records: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates random access with DataStreams.
     */
    public static void randomAccessWithDataStreams() {
        // Create a file with fixed-length records
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("fixed_records.bin"))) {
            
            // Write 5 fixed-length records
            for (int i = 1; i <= 5; i++) {
                dos.writeInt(i);  // 4 bytes
                dos.writeDouble(i * 1.1);  // 8 bytes
                
                // Write a fixed-length string (20 chars = 40 bytes)
                String name = "Record-" + i;
                dos.writeChars(padString(name, 20));
            }
            
            System.out.println("Fixed-length records written to fixed_records.bin");
            System.out.println("File size: " + dos.size() + " bytes");
            System.out.println("Each record size: " + (4 + 8 + 40) + " bytes");
        } catch (IOException e) {
            System.out.println("Error writing fixed-length records: " + e.getMessage());
        }
        
        // Read specific records using FileInputStream's skip method
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream("fixed_records.bin"))) {
            
            // Record size in bytes (4 + 8 + 40 = 52)
            int recordSize = 52;
            
            // Read record 3 (skip 2 records)
            dis.skipBytes(2 * recordSize);
            
            int id = dis.readInt();
            double value = dis.readDouble();
            String name = readFixedLengthString(dis, 20);
            
            System.out.println("\nRecord 3:");
            System.out.println("ID: " + id);
            System.out.println("Value: " + value);
            System.out.println("Name: " + name);
            
            // Go back to the beginning and read record 1
            dis.close();
            DataInputStream dis2 = new DataInputStream(
                    new FileInputStream("fixed_records.bin"));
            
            id = dis2.readInt();
            value = dis2.readDouble();
            name = readFixedLengthString(dis2, 20);
            
            System.out.println("\nRecord 1:");
            System.out.println("ID: " + id);
            System.out.println("Value: " + value);
            System.out.println("Name: " + name);
            
            // Skip to record 5
            dis2.skipBytes(3 * recordSize);
            
            id = dis2.readInt();
            value = dis2.readDouble();
            name = readFixedLengthString(dis2, 20);
            
            System.out.println("\nRecord 5:");
            System.out.println("ID: " + id);
            System.out.println("Value: " + value);
            System.out.println("Name: " + name);
            
            dis2.close();
        } catch (IOException e) {
            System.out.println("Error reading fixed-length records: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to pad a string to a fixed length.
     */
    private static String padString(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) {
            sb.append('\0');  // Pad with null character
        }
        
        return sb.toString();
    }
    
    /**
     * Helper method to read a fixed-length string from a DataInputStream.
     */
    private static String readFixedLengthString(DataInputStream dis, int length) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = dis.readChar();
            if (c != '\0') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

/**
 * A simple Student class for demonstrating record I/O.
 */
class Student {
    private int id;
    private String name;
    private int age;
    private double gpa;
    
    public Student(int id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + ", gpa=" + gpa + "}";
    }
}
