package Bonus;

/**
  * Mat1 is the class that represents the adjacency matrix A of a cycle graph
 */
public class Mat1 {

    private int[][] matrix;  //this attribute is the adjacency matrix
    private int size; //an attribute that represents the size of the matrix

    private int[][] result_matrix; //this attribute will contain the final matrix after multiplication

    /**
     * Mat1 constructor creates a new Mat1 object with the specified size
     * @param size
     */

    public Mat1(int size) {
        this.size = size;
        this.matrix = new int[size][size];
        this.result_matrix = new int[size][size];
    }

    /**
     *By making some examples we can see an easy rule for the matrix
     *Example for size 4:
     *       0 1 0 1
     *       1 0 1 0
     *       0 1 0 1
     *       1 0 1 0
     * it puts the 1 value immediately next to the diagonal (the edge between two consecutive verteces),
     * but also in the upper right corner and in the lower left corner (the edge between the first and the last vertex)
     * @return the adjacency matrix
     */

    public int[][] InitializeAdjacencyMatrix() {
        int i; //an index
        int[][] m= new int[size][size]; // an auxiliary matrix

        for (i = 0; i < size - 1; i++) {
            m[i][i + 1] = 1;
            m[i + 1][i] = 1;
        }

        m[0][matrix.length - 1] = 1;
        m[matrix.length - 1][0] = 1;
        return m;
    }

    /**
     * This method set the values in the matrix and in the result matrix by calling the InitializeAdjacencyMatrix method
     */
    public void setMatrices(){
        this.matrix=InitializeAdjacencyMatrix();
        this.result_matrix=InitializeAdjacencyMatrix();
    }

    /**
     * CopyMatrix copies a matrix in another one
     * @param a will have the b's values
     * @param b is the matrix that is copied
     */
    public void CopyMatrix(int[][] a, int[][] b)
    {
        int i, j;
        for (i = 0; i < size; i++)
            for (j = 0; j < size; j++) {
                a[i][j]=b[i][j];
            }
    }

    /**
     * Here the method multiply the matrix by itself
     * The power is the same with the size of the matrix so we don't need aditional parameters
     *
     */
    public void multiplication() {
        int i, j, k, sum;
        int power = size; //the power that the matrix is rising
        int[][] aux_matrix= new int[size][size]; //this auxiliary matrix change after a multiply
        while (power > 1) {
            for (i = 0; i < size; i++) //multiplication of 2 matrix
                for (j = 0; j < size; j++) {
                    sum = 0;
                    for (k = 0; k < size; k++) {
                        sum = sum + result_matrix[i][k] * matrix[k][j];
                    }
                    aux_matrix[i][j] = sum;
                }
            CopyMatrix(result_matrix, aux_matrix); //the matrix result is updated with the auxiliary one
            power--;
        }
    }

    /**
     * Prints the final matrix
     */
    public void print() {
        int i, j;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++)
                System.out.print(result_matrix[i][j] + " ");
            System.out.println();
        }
    }

}

