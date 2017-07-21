import java.util.Random;
import java.util.Scanner;

/**
 * Example of calling the PoolingFunctions class
 *
 */

public class TestCalling {

	
	public static void main(String args[]){
		// The following values are returned by calling Scanner
		// int image_height = 13;
		// int image_width = 17;		
		
		Scanner myScanner = new Scanner(System.in);
		System.out.print("Image height: ");
		int image_height = myScanner.nextInt();
		System.out.print("Image length: ");
		int image_width = myScanner.nextInt();
		System.out.print("Filter size: ");
		int filter_size = myScanner.nextInt();

		
		int [][] matrix = new int[image_height][image_width];
		for(int row=0;row<image_height;row++){
			for(int cell=0;cell<image_width;cell++){
				Random rn = new Random();
				matrix[row][cell] = rn.nextInt(10);
			}
		}
		
		PoolingFunctions pool = new PoolingFunctions(matrix,filter_size);
		pool.poolingMain();
		
	}

	
	
}
