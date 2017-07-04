
public class MatrixUtilities {

    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = StdRandom.uniform(0.0, 1.0);
        return a;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }

    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return B = A^T
    public static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] b = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b[j][i] = a[i][j];
        return b;
    }
	
    /* Function to compute transpose of a row vector*/
    public static double[][] transposeV(double[] m)
    {
         double[][] temp = new double[m.length][1];
         for (int i = 0; i < m.length; i++)
                 temp[i][0] = m[i];
         return temp;
     }

 
	/* A function that add  matrices elements -wise*/
	public static double[][] add(double[][] A, double[][] B)
	{
		  int aRows = A.length;
	      int aColumns = A[0].length;
	      int bRows = B.length;
	      int bColumns = B[0].length;
	      if (aColumns != bColumns||aRows != bRows) 
	      {
	        throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
	      }
	      double[][] C = new double[aRows][bColumns];
	      for (int i = 0; i < aRows; i++) 
	      {
	        for (int j = 0; j < bColumns; j++) 
	        {
	          C[i][j] = 0.00000;
	        }
	      }
	      for (int i = 0; i < aRows; i++) 
	      { // aRow
	          for (int j = 0; j < bColumns; j++) 
	          { // bColumn
	        	  C[i][j]=A[i][j]+B[i][j];
	          }
	      }
	      return C;
	 }

	/* A function that add subtract matrices elements -wise*/
	public static double[][] subtract(double[][] A, double[][] B)
	{
		  int aRows = A.length;
	      int aColumns = A[0].length;
	      int bRows = B.length;
	      int bColumns = B[0].length;
	      if (aColumns != bColumns||aRows != bRows) 
	      {
	        throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
	      }
	      double[][] C = new double[aRows][bColumns];
	      for (int i = 0; i < aRows; i++) 
	      {
	        for (int j = 0; j < bColumns; j++) 
	        {
	          C[i][j] = 0.00000;
	        }
	      }
	      for (int i = 0; i < aRows; i++) 
	      { // aRow
	          for (int j = 0; j < bColumns; j++) 
	          { // bColumn
	        	  C[i][j]=A[i][j]-B[i][j];
	          }
	      }
	      return C;
	 }

    // return c = a * b
	public static double[][] multiply(double[][] matrixA, double[][] matrixB){

		double[][] result = new double[matrixA.length][matrixB[0].length];

		for(int i = 0; i<result.length; i++){
			for (int j = 0; j<result[0].length; j++){
				result[i][j] = 0;
			}
		}	
		if(matrixA[0].length != matrixB.length){
			System.out.println("Error: matrices dimension don't match.");
		}else{

			for(int i = 0; i<matrixA.length; i++){
				for(int j = 0; j<matrixB[0].length; j++){
					for(int k = 0; k< matrixA[0].length; k++){
						result[i][j] += matrixA[i][k] * matrixB[k][j];
					}
				}
			}
		}

		return result;
	}
    
	/* Element wise_multiplication*/
	public static double[][] MultiplyElementWise(double[][] A, double[][] B)
	{
		  int aRows = A.length;
	      int aColumns = A[0].length;
	      int bRows = B.length;
	      int bColumns = B[0].length;
	      if (aColumns != bColumns||aRows != bRows) 
	      {
	        throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
	      }
	      double[][] C = new double[aRows][bColumns];
	      for (int i = 0; i < aRows; i++) 
	      {
	        for (int j = 0; j < bColumns; j++) 
	        {
	          C[i][j] = 0.00000;
	        }
	      }
	      for (int i = 0; i < aRows; i++) 
	      { // aRow
	          for (int j = 0; j < bColumns; j++) 
	          { // bColumn
	        	  C[i][j]=A[i][j]*B[i][j];
	          }
	      }
	      return C;
	 }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] a, double[] x) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += a[i][j] * x[j];
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] a) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += a[i][j] * x[i];
        return y;
    }
    
	//Makes an array from a n by 1 matrix
	public static double[] matrixToArray(double[][] matrix){
		int length = matrix.length;
		double[] arrayToOutput = new double[length];

		for(int i=0; i<length; i++){
			arrayToOutput[i] = matrix[i][0];
		}
		return arrayToOutput;
	}
	
	//Makes a n by 1 matrix from an array
	public static double[][] arrayToMatrix(double[] array){
		int length = array.length;
		double[][] matrixToOutput = new double[length][1];

		for(int i=0; i<length; i++){
			matrixToOutput[i][0] = array[i];
		}
		return matrixToOutput;

	}
	
	/* Create a vector of ones*/
	public static double[] createVectorOfOnes(int size)
	{
		double[] result= new double[size];
		for(int i=0;i<size;i++)
		{
			result[i]=1;
		}
		return result;
	}
	/* Create matrix of Zeros*/
	public static double[][] createMatrixZeros(int row,int column)
	{
		double[][] result= new double[row][column];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<column;j++)
			result[i][j]=0;
		}
		return result;
	}
	/*Create matrix of ones*/
	public static double[][] createMatrixOnes(int row,int column)
	{
		double[][] result= new double[row][column];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<column;j++)
				result[i][j]=1;
		}
		return result;
	}
	// Print a matrix
	public static void printMatrix(double[][] m)
    {
      try{
        int rows = m.length;
        int columns = m[0].length;
        String str = "|\t";

        for(int i=0;i<rows;i++){
          for(int j=0;j<columns;j++){
            str += m[i][j] + "\t";
          }

          System.out.println(str + "|");
          str = "|\t";
        }
        System.out.println();
      }
      catch(Exception e){System.out.println("Matrix is empty!!");}
    }
	// print an array
	public static void printArray(double[] array){
		System.out.print("[");
		for(int i = 0; i<array.length; i++)
		{
			System.out.print(array[i]+" | ");
		}
		System.out.print("]");
		System.out.println();
	}
	
	/*
	 * Sigmoid Function
	 */
	public static double[] sigmoid(double[] x) 
	{
		int length= x.length;
		double[] result = new double[length];
		for(int i=0;i<length;i++)
		{
			result[i]=(1/( 1 + Math.pow(Math.E,(-1*x[i]))));
		}
		return result;
	}

	
	
}