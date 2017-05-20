import java.util.Random;
import java.lang.Math;
public class GradientDecentLinearRegression 
{

	public static void main(String[] args) 
	{
		/*Setting up the training examples*/
		
		final int nOfDataPoints=100;	// Number of training examples
		Random rand = new Random(); 	// a random generator to generate random numbers
		double[] x= new double[nOfDataPoints]; // x values of a training examples
		double[] y= new double[100]; // the y values of training examples
		for(int i=0;i<nOfDataPoints;i++)
		{
			
			x[i] = Math.random() * 100 + 1; // get random value for x ranging from 1 to 1000
			y[i]= aFunction(x[i]); // give y values according to x values and function aFunction defined below
			System.out.println("x"+i+" is "+x[i]+" and y"+i+" is "+y[i]);
		}
		
		
		
		
		
		
		/* We now apply gradient decent on the input examples.
		 * We are now trying to minimize the cost function Sum((a1x[i]+a0-y[i])^2)
		 * a0=a0-alpha(partial(costFunction)/partial(a0))
		 * a1=a1-alpha(partial(costFunction)/partial(a0))
		 */
		
		final double alpha=0.2;// set the learning rate to be 0.5
		double a0=2;
		double a1=3; // pick initial a0 and a1
		double temp0=2; 
		double temp1=3; // used to store the the updated a0 and a1
		int count=0; // use to count the number of iteration
		do
		{
			a0=temp0;
			a1=temp1;
			temp0=updateA0(x,y,alpha,a0,a1);
			temp1=updateA1(x,y,alpha,a0,a1);
			count++;
			System.out.println("Iteration "+count+" The value of a0 is "+a0+" And it is updated to "
					+temp0+" The value of a1 is "+a1+" And it is updated to "+temp1);
		}
		while(Math.abs(temp0-a0)>=0.00001||Math.abs(temp1-a1)>=0.00001);
		System.out.println("The estimated value of a0 is "+a0+" And the estimated value of a1 is"+a1);

		
		
	}
	public static double updateA0(double[] x, double[] y,double alpha, double a0, double a1)
	{
		double sum=0; // use to store the result
		for(int i=0;i<x.length;i++)
		{
			sum=sum+a0+a1*x[i]-y[i];
		}
		return a0-alpha*(sum/x.length);
	}
	public static double updateA1(double[] x, double[] y,double alpha, double a0, double a1)
	{
		double sum=0; // use to store the result
		for(int i=0;i<x.length;i++)
		{
			sum=sum+(a0+a1*x[i]-y[i])*x[i];
		}
		return a0-alpha*(sum/x.length);
	}
	public static double aFunction(double x) // a function to produce random data
	{
		return Math.pow(x, 3)-9*x+1;
	}

}
