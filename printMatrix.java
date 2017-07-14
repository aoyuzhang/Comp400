import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;


public class printMatrix {
	

	public static List PoolConv(int[][] image, int filter_size){
		List StackedMatrices = new ArrayList();
		
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
					filter.add(Arrays.toString(slice_));
				}
				a+=filter_size;
				b+=filter_size;
				StackedMatrices.add(filter);

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
    
    // Pooling method:
    
	
	public static void main(String args[]){


		// Initialize both images
		int[][] ListMatrix = ArrayMatrix(5,5);
		print2dMatrix(ListMatrix);
		int[][] ListMatrix0 = fill0s(ListMatrix,6,6);
		print2dMatrix(ListMatrix0);
		System.out.println();
				
		// Testing PoolConv() method:
		List stack = PoolConv(ListMatrix0,2);
		
		// Print result from PoolConv() method:
		for(int i = 0; i<stack.size();i++){
			System.out.println(stack.get(i));
		}
		
		System.out.println();
		//System.out.println(stack.get(0));
		

		
		/*
		List subList = new ArrayList();
		subList = (List) stack.get(0);
		System.out.println(subList);
		System.out.println(((List) subList).size());
		
		int max = 0; 			// max value

		for(int i=0;i<2;i++){	// For every row:
			max = 0;
			List row = new ArrayList();
			row = (List) subList.get(i);
			for(int k=0;k<2;k++){	// For every item:
				int item;
				item = (int) row.get(k);
				if(k>max){
					max = k;
				}
			}
		}
		
		System.out.println(max);	// prints out max value
		*/
		/*
		Object rowLost = new ArrayList();
		rowList = ((ArrayList) john).get(0);
		System.out.println(sam);
		System.out.println(sam.getClass());
		
		List values = new ArrayList();
		values.add(sam);
		System.out.println(values);
		List Pete = new ArrayList();
		System.out.println(Pete);
		
		// Testing converting list to array

		Object[] stringArray = Pete.toArray(new String[0]);
		System.out.println(stringArray.getClass());
		*/
		
		
		/*
		// stockList is of ArrayList type
		List<String> stockList = new ArrayList<String>();
		stockList.add("stock1");
		stockList.add("stock2");
		System.out.println(stockList);
		
		// stockArr is of Array type
		String[] stockArr = new String[((List) john).size()];
		// converts stockList to array
		stockArr = (String[]) ((List) john).toArray(stockArr);
		System.out.println(Arrays.toString(stockArr));

		for(String s : stockArr)
		    System.out.println(s);
		
		String[][] newList = new String[2][2];
		newList[0] = stockArr;
		System.out.println(Arrays.toString(newList[0]));
		*/
		
	}
}
 