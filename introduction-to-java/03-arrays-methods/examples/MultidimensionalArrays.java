/**
 * MultidimensionalArrays.java
 * This program demonstrates working with multidimensional arrays in Java.
 */
public class MultidimensionalArrays {
    public static void main(String[] args) {
        System.out.println("--- Two-Dimensional Arrays ---");
        
        // Declare and create a 2D array (3 rows, 4 columns)
        int[][] matrix = new int[3][4];
        
        // Initialize elements
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;
        matrix[0][3] = 4;
        
        matrix[1][0] = 5;
        matrix[1][1] = 6;
        matrix[1][2] = 7;
        matrix[1][3] = 8;
        
        matrix[2][0] = 9;
        matrix[2][1] = 10;
        matrix[2][2] = 11;
        matrix[2][3] = 12;
        
        // Print the 2D array
        System.out.println("Matrix (3x4):");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Initialize a 2D array with values
        int[][] anotherMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("\nAnother Matrix (3x3):");
        for (int i = 0; i < anotherMatrix.length; i++) {
            for (int j = 0; j < anotherMatrix[i].length; j++) {
                System.out.print(anotherMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\n--- Accessing Elements in 2D Arrays ---");
        
        // Access specific elements
        System.out.println("Element at row 1, column 2: " + anotherMatrix[1][2]);
        
        // Modify an element
        anotherMatrix[0][0] = 99;
        System.out.println("Modified element at row 0, column 0: " + anotherMatrix[0][0]);
        
        System.out.println("\n--- Using Enhanced for Loop with 2D Arrays ---");
        
        for (int[] row : anotherMatrix) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\n--- Jagged Arrays (Different Row Lengths) ---");
        
        // Create a jagged array (rows have different lengths)
        int[][] jaggedArray = new int[3][];
        jaggedArray[0] = new int[2];
        jaggedArray[1] = new int[4];
        jaggedArray[2] = new int[3];
        
        // Initialize the jagged array
        jaggedArray[0][0] = 1;
        jaggedArray[0][1] = 2;
        
        jaggedArray[1][0] = 3;
        jaggedArray[1][1] = 4;
        jaggedArray[1][2] = 5;
        jaggedArray[1][3] = 6;
        
        jaggedArray[2][0] = 7;
        jaggedArray[2][1] = 8;
        jaggedArray[2][2] = 9;
        
        // Print the jagged array
        System.out.println("Jagged Array:");
        for (int i = 0; i < jaggedArray.length; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < jaggedArray[i].length; j++) {
                System.out.print(jaggedArray[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\n--- Three-Dimensional Arrays ---");
        
        // Create a 3D array (2x3x2)
        int[][][] threeDArray = new int[2][3][2];
        
        // Initialize some elements
        threeDArray[0][0][0] = 1;
        threeDArray[0][0][1] = 2;
        threeDArray[1][2][0] = 9;
        threeDArray[1][2][1] = 10;
        
        // Print the 3D array
        System.out.println("3D Array (2x3x2):");
        for (int i = 0; i < threeDArray.length; i++) {
            System.out.println("Layer " + i + ":");
            for (int j = 0; j < threeDArray[i].length; j++) {
                for (int k = 0; k < threeDArray[i][j].length; k++) {
                    System.out.print(threeDArray[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        
        System.out.println("\n--- Practical Example: Student Grades ---");
        
        // 2D array to store grades for multiple students across multiple subjects
        double[][] studentGrades = {
            {85.5, 90.0, 78.5, 92.5}, // Student 1's grades
            {88.0, 76.5, 89.0, 94.0}, // Student 2's grades
            {92.0, 91.5, 85.0, 82.0}  // Student 3's grades
        };
        
        // Calculate and print average grade for each student
        System.out.println("Student Averages:");
        for (int i = 0; i < studentGrades.length; i++) {
            double sum = 0;
            for (int j = 0; j < studentGrades[i].length; j++) {
                sum += studentGrades[i][j];
            }
            double average = sum / studentGrades[i].length;
            System.out.println("Student " + (i + 1) + ": " + average);
        }
    }
}
