package Homework;

public class LatinSquare {
    private int[][] matrix; //this attribute will contain the latin square
    private int size; //an attribute that contain the size of the matrix



    public LatinSquare(int size) {
        this.size = size;
        this.matrix = new int[size][size];
    }

    /**
     * This method fill the matrix with the numbers from 1 to size
     * The rule is every line is a left permutation from the previous one and we also can calculate the values with a formula like the one below
     */
    public void Fill() {
        int i, j;
        for (i = 0; i < size; i++)
            for (j = 0; j < size; j++)
                matrix[i][j] = (i + j) % size + 1;
    }

    /**
     * Prints the matrix
     */
    public void Print() {
        int i, j;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * Prints the concatenation of the elements of each row and each column
     */

    public void Display() {
        int i, j;
        for (i = 0; i < size; i++) {
            String row = "";
            String column = "";
            for (j = 0; j < size; j++) {
                row = row + matrix[i][j];
                column = column + matrix[j][i];
            }
            System.out.println("Row " + (i + 1) + ": " + row);
            System.out.println("Column " + (i + 1) + ": " + column);
        }
    }
}
