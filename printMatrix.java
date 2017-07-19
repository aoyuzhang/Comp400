import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class PrintArray {
	
	// Outputs arrayList of 2d matrices
	public static ArrayList<int[]> PoolConv(int[][] image, int filter_size){
		ArrayList<int[]> StackedMatrices = new ArrayList();
		
		// for every row:
		for(int i=0;i<image.length;i+=filter_size){
			int a = 0;				// slice [a:b]
			int b = filter_size;	// slice [a:b]
			
			// moving horizontally:
			for(int j=0;j<(image.length/filter_size);j++){
				List filter = new ArrayList();
				// for each row in the filter size:
				for(int k = 0;k<filter_size;k++){
					int[] slice_ = Arrays.copyOfRange(image[i+k], a, b);
					//System.out.println("k is " + k + " " + Arrays.toString(slice_));
					filter.add(slice_);
				}
				a+=filter_size;
				b+=filter_size;
				StackedMatrices.addAll(filter);
			}
		}
		return StackedMatrices;
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

	
	// Array with random numbers, specified by height/width
	public static int[][] ArrayMatrix(int height_size, int width_size){
		int [][] matrix = new int[height_size][width_size];
		for(int row=0;row<height_size;row++){
			for(int cell=0;cell<width_size;cell++){
				Random rn = new Random();
				matrix[row][cell] = rn.nextInt(9);
			}
		}
		return matrix;
	}
	
    // Prints a 2D Matrix
    public static <E> void print2dMatrix(int[][] listMatrix){
        System.out.println();
        for (int i = 0 ; i < listMatrix.length ; i++) {
            for (int j = 0; j < listMatrix[i].length; j++) {
                System.out.print(listMatrix[i][j]+ " ");
            }
            System.out.println();
        }
    }
    
    // Pooling method: returns max of each 2d matrix in a 1D ArrayList format
    public static ArrayList<int[]> poolingMax(ArrayList maxList, int filter_size){
    	// List with maximums for each:
    	ArrayList maxCompress = new ArrayList();
    	// Convert arrayList to array type
    	Object[] arrayInt = new Object[maxList.size()];
    	arrayInt = maxList.toArray(arrayInt);
    	
    	// Specify local variables
    	int f_size = filter_size*filter_size;	// filter area
    	int a = 0;								// slice [a:b]
    	int b = f_size;							// slice [a:b]

    	for(int iter = 0; iter<(maxList.size()/f_size);iter++){
    		Object[] slice  = Arrays.copyOfRange(arrayInt,a,b);
    		Integer max = 0;

    		for(int k = 0; k < slice.length; k++){
    			Integer value = (Integer) slice[k];
    			if(value > max){
    				max = value;
    			}
    		}
    		maxCompress.add(max);	// Add integer to max

    		// Increment [a:b]
    		a+=f_size;
    		b+=f_size;
    	}
    	return maxCompress;
    }
    
    // Convert 1D list of maximums to 2D matrix of maximums
    public static int[][] return2d(ArrayList max1d, int rows, int columns){
		int[][] poolMatrix = new int[rows][columns];	// creates blank matrix with specified dimensions
		int counter = 0;	// returns index of max1d 
		for(int i = 0; i < (rows); i++){
			for(int k = 0; k < columns; k++){
				poolMatrix[i][k] = (int) max1d.get(counter);
				counter++;
				
			}
		}
		return poolMatrix;
    }
	
	public static void main(String args[]){


		// Initialize both images
		int[][] ListMatrix = ArrayMatrix(5,5);
		print2dMatrix(ListMatrix);
		int[][] ListMatrix0 = fill0s(ListMatrix,6,6);
		print2dMatrix(ListMatrix0);
		System.out.println();
				
		// Testing PoolConv() method:
		ArrayList<int[]> stack = PoolConv(ListMatrix0,2);
		
		System.out.println("");
		// Print result from PoolConv() method:
		
		//System.out.println(Arrays.toString(stack));
		
		int[] maxList;
		ArrayList <Integer> max1d = new ArrayList();
		
		for(int i = 0; i<stack.size();i++){
			maxList = (stack.get(i));
			for(int k=0;k<maxList.length;k++){
				max1d.add(maxList[k]);
			}
		}
		
		
		ArrayList<int[]> al = poolingMax(max1d, 2);

		int[][] pm = return2d(al,3,3);
		print2dMatrix(pm);


		
	}
}
