import java.util.Random;
import java.lang.Math;
public class GradientDecentLinearRegressionMultivariable 
{

	public static void main(String[] args) 
	{
		/*Setting up the training examples
		 * By giving some random data points
		 */
		
		final int nOfDataPoints=100;	// Number of training examples
      	final int numberOfFeatures=3;
		double[][] x= new double[nOfDataPoints][numberOfFeatures+1]; // x values of a training examples
		double[][] y= new double[nOfDataPoints][1]; // the y values of training examples
      	for(int i=0;i<nOfDataPoints;i++)
		{
          x[i][0]=1;
          for(int j=1;j<numberOfFeatures+1;j++)
			x[i][j] = Math.random()*10; // get random value for x ranging from 1 to 100
          y[i][0]= aFunction(x[i]); // give y values according to x values and function aFunction defined below
			
		}  	
	
		/* We now apply gradient decent on the input examples.
		 * We are now trying to minimize the cost function (1/(2*nOfDataPoints))*SumOverAllDataPOoints((a1x[i]+a0-y[i])^2)
		 * a0=a0-alpha(partial(costFunction)/partial(a0))
		 * a1=a1-alpha(partial(costFunction)/partial(a0))
		 */
		
		final double alpha=0.00001;// set the learning rate to be 0.5
		double[][] thetas=new double[numberOfFeatures+1][1];
		double[][] temp= new double[numberOfFeatures+1][1];
		int count=0;
      	for(int i=0;i<thetas.length;i++)
        	thetas[i][0]=Math.random();
      	for(int i=0;i<thetas.length;i++)
        	temp[i][0]=thetas[i][0];
      	
      	
      	do
      	{
          	for(int i=0;i<thetas.length;i++)
          		thetas[i][0]=temp[i][0];
          	temp=updateThetas(x,y,alpha,thetas);
      		count++;
      		System.out.println("Iteration "+count+" The value of thetas is: ");
      		printMatrix(thetas);
      		System.out.println("And it is updated to: ");
      		printMatrix(temp);
      	}
      	while(isCloseTo(0.0001,temp,thetas));
        
		
	}
	/* check if every value of a matrix is close to one and the other*/
	public static boolean isCloseTo(double n,double[][]A, double [][]B )
	{
	  int aRows = A.length;
      int aColumns = A[0].length;
      int bRows = B.length;
      int bColumns = B[0].length;
      if (aColumns != bColumns||aRows != bRows) 
      {
        throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
      }
	  for(int i=0;i<aRows;i++)
	  {
		  for(int j=0;j<aColumns;j++)
		  {
			  if(Math.abs(A[i][j]-B[i][j])>=n)
				  return true;
		  }
	  }
	  return false;
	}
	/* change a vector to a matrix*/
	public static double[][] chageToMatrix(double[] a)
	{
		int length=a.length;
		double[][] result=new double[1][length];
		for(int i=0;i<length;i++)
			result[0][i]=a[i];
		return result;
	}
  	/* Function to update Thetas*/
	public static double[][] updateThetas(double[][] x, double[][] y,double alpha, double[][] thetas)
	{
      double[][] result=new double[thetas.length][1];
      for(int j=0;j<thetas.length;j++) // Go through each thetas one by one
      {
		double sum=0; // use to store the result
		for(int i=0;i<x.length;i++) // iterate through each data points
		{
			double[][] eval=mMultiplication(chageToMatrix(x[i]),thetas);
			double evalR=eval[0][0];
//			for(int m=0;m<x[i].length;m++) // get the vector product of x with theta
//				eval=eval+x[i][m]+thetas[m][0];
			sum=sum+(evalR-y[i][0])*x[i][j];
		}
		result[j][0]= result[j][0]-alpha*(sum/x.length);
      }
      return result;
	}
    
	
	/* A function that used to simulate the linear regression*/
	public static double aFunction(double[] x) // a function to produce random data
	{
      	int length=x.length;
      	double sum=0;
      	for(int i=0;i<length;i++)
        {
          sum=sum+x[i]*(double)(i+1);
        }
        return sum;
	}
	
	/* A function that add two matrices*/
	public static double[][] addMatrices(double[][] A, double[][] B)
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
	
	/*A function to multiply two matrices*/
    public static double[][] mMultiplication(double[][] A, double[][] B) 
    {

      int aRows = A.length;
      int aColumns = A[0].length;
      int bRows = B.length;
      int bColumns = B[0].length;

      if (aColumns != bRows) 
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

      for (int i = 0; i < aRows; i++) { // aRow
        for (int j = 0; j < bColumns; j++) { // bColumn
          for (int k = 0; k < aColumns; k++) { // aColumn
            C[i][j] += A[i][k] * B[k][j];
          }
        }
      }
      return C;
    }
    
    /*A function that print a matrix on the screen*/
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

      }
      catch(Exception e){System.out.println("Matrix is empty!!");}
    }

}
