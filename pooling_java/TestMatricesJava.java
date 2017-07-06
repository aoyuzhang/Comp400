/*
 * This is a test base for programming matrices in Java
 */

public class TestMatricesJava {

    // Prints a 2D Matrix
    public static void print2dMatrix(Double[][] matrix2d){
        System.out.println();
        for (int i = 0 ; i < matrix2d.length ; i++) {
            for (int j = 0; j < matrix2d[i].length; j++) {
                System.out.print(matrix2d[i][j]+ " ");
            }
            System.out.println();
        }
    }

    // Adds a smaller matrix into a larger 0 filled matrix
    public static int[][] fill0s(int[][] matrix2d, int height, int width){
        int[][] blank_matrix = new int [height][width]; // Create blank matrix
        for (int i = 0; i<matrix2d.length;i++){
            for (int j = 0; j<matrix2d[i].length;j++){
                blank_matrix[i][j] = matrix2d[i][j];    // Index in blank matrix is value of smaller matrix
            }
        }

        return blank_matrix;
    }

/*
    public static void main(String[] args) {
        int [ ] [ ] scores = {   { 20, 18, 22, 20, 16 },
                                 { 18, 20, 18, 21, 20 },
                                 { 16, 18, 16, 20, 24 },
                                 { 25, 24, 22, 24, 25 }
                             };

        print2dMatrix(scores);

        int [][] matrix = new int[5][10];
        System.out.println("BLANK 5 X 10 MATRIX:");
        print2dMatrix(matrix);

        matrix = fill0s(scores,5,10);
        print2dMatrix(matrix);

}
*/
}
