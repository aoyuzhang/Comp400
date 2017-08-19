/**
 * 
 * This code performs pooling by converting the 2D image into a 1D array of doubles
 * By using no external library and avoiding nested loops when possible,
 * this version of pooling code aims to be faster than the previous attempt,
 * found in PoolingFunctions.
 *
 * Example usage:
 * double[][] image;  // must be a square image
 * int filter_size;   // length of one side of a square filter
 * ConvArray pool = new ConvArray(image, filter_size);
 * double[][] pooled_matrix = pool.poolingMain1d();
 */


public class ConvArray {

	// Constructors
	static double[][] image;
	static int filter_size_constr;
	public ConvArray(double[][] image, int filter_size_constr){
		this.image = image;			// image
		this.filter_size_constr = filter_size_constr;	// filter size
	}
	

	public static double[][]imageFrame(double[][] matrix2d, int height_image, int width_image, int filter_size_height, int filter_size_width){
		/**
		 * Returns a 0-border around image so all filter positions can fit in image
		 * Computes height/width dimensions based on filter and image size
		 */
		
		// Define dimensions
		int new_height = height_image;
		int new_width = width_image;
		// Add additional columns/ rows if necessary:
		if((height_image%filter_size_height)!= 0){		// Rows
			 new_height = height_image + (filter_size_height - (height_image % filter_size_height));	// height of new image
		}
		if((width_image%filter_size_width)!= 0){		// Columns
			new_width = width_image + (filter_size_width - (width_image % filter_size_height));		// width of new image
		}
		// Initialize blank matrix with repsective dimensions
		double[][] blank_matrix = new double [new_height][new_width];
		// Fill in blank matrix
        for (int i = 0; i<matrix2d.length;i++){			// for each row
            for (int j = 0; j<matrix2d[0].length;j++){	// for each cell
                blank_matrix[i][j] = matrix2d[i][j];    // Index in blank matrix is value of smaller matrix
            }
        }
		return blank_matrix;	// return image with frame
	}
	
	public static double[] convert1d(double[][] image){
		/**
		 *  Convert 1D image
		 */
		
		// Final 1d compressed array
		double[] array_final = new double[(image.length)*(image[0].length)];
		int counter = 0;
		
		// Convert 2D image into 1D array
		for(int i = 0;i<image.length;i++){
			for(int j = 0; j<image[0].length;j++ ){
				array_final[counter] = image[i][j];
				counter++;
			}
		}
		return array_final;
	}
		
	public static int[] generate_indices(int filter_size,double[] image_array, double[][] image){
		/**
		 *	This function generates a list of indices that the next function uses to access the picture
		 */
		int[] counter_list = new int[(image[0].length/filter_size)*image.length];
		for(int i=0;i<counter_list.length;i++){
			counter_list[i] = i;
		}
		return counter_list;
	}
	
	public static int[] sort_indices(int[] unsorted_indices,int filter_size, double[][] image, double[] image_array){
		/**
		 * 	Reorder indices in a way that is suitable for indexing the image"""
		 */
		int[] sorted_indices = new int[unsorted_indices.length];
		int counter = 0;			// Used for index
		int biais = 0;				// Used for moving horizontally
		int add_horizontal = 0;		// Keeps track of distance to subtract when moving vertically
		// Moving vertically:
		for(int i = 0; i<(image.length/filter_size); i++){
			// Moving horizontally
			for(int j = 0; j<(image[0].length/filter_size); j++){
				// For each item in row:
				for(int k = 0; k<(filter_size); k++){
					sorted_indices[counter] = unsorted_indices[(image[0].length/filter_size)* k + biais];
					counter++;		// Update index
				}
				biais++;			// Moving right
				add_horizontal++;	// Moving right
			}
			biais -= add_horizontal;	// Substract distance from edge
			biais += image[0].length;	// Move vertically one filter position
			add_horizontal = 0;			// Reinitialize add_horizontal value to 0
		}
		
		return sorted_indices;		// Array of indices
	}
	
	public static double[][] nest_image_array(double[] image_array, int filter_size){
		/**
		 * Converts 1D array into 2D image
		 */
		double[][] nested_image_array = new double[image_array.length/filter_size][filter_size];
		int counter = 0;
		for(int i = 0; i < (image_array.length/filter_size); i++){
			for(int j = 0; j<filter_size; j++){
				nested_image_array[i][j] = image_array[counter];
				counter++;
			}
		}
		return nested_image_array;
	}
	
	public static double[] iterate_indices(double[] image_array, double[][] nested_image_array, int[] sorted_indices, int filter_size){
		/**
		 * Returns a usable 1D representation of the image
		 * Returns an array of doubles that return_maximum() can use
		 */
		// Create new 1D array
		double[] sorted_image_array = new double[image_array.length];
		int counter = 0;
		// for each nested image:
		for(int i = 0; i < (image_array.length/filter_size);i++){
			for(int j = 0; j<filter_size; j++){
				sorted_image_array[counter] = nested_image_array[sorted_indices[i]][j];
				counter++;
			}
		}
		return sorted_image_array;
	}
	
	public static double[] return_maximum(double[] image_array, int filter_size){
		/**
		 * 	Returns the maximum of each filter
		 */
		double[] maximums_array = new double[image_array.length/(filter_size*filter_size)];
		int counter = 0;
		int index = 0;
		double max_value = 0;

		// For number of maximums to be returned
		for(int i = 0; i < image_array.length; i++){
			counter++;
			double number = image_array[counter-1];
			// If not at end, but number is max
			if(counter%(filter_size*filter_size) != 0 && number>max_value){	// inside filter, if number is larger than max value:
				max_value = number;
			}
			// If at end:
			if(counter%(filter_size*filter_size) == 0){	// at the end of the filter
				// If end number is max:
				if(number>max_value){
					max_value = number;
				}
				maximums_array[index] = max_value;		// append maximum value to the list
				max_value = 0;							// reset max value to 0
				index++;
			}
		}	
		return maximums_array;
	}
	
	public static double[][] restore_format(double[] max_list, int filter_size, int image_height, int image_width){
		/**
		 * Reconverts the 1D array into a 2D matrix
		 */
		int pooled_height = image_height/filter_size;	// Computes final image height
		int pooled_width = image_width/filter_size;		// Computes final image width
		int counter = 0;								// Used for indexing
		double[][] image = new double[pooled_width][pooled_height]; // NOT SURE!!!
		for(int i = 0; i<pooled_height; i++){
			for(int j = 0; j<pooled_width; j++){
				image[i][j] = max_list[counter];
				counter++;
			}
		}
		return image;
	}
	
	public static double[][] poolingMain1d(){
		/**
		 * The main function of the ConvArray class
		 */
		
		// Add frame to picture if picture isn't a perfect square
		double[][] listMatrix0 = imageFrame(image, image.length, image[0].length,
				filter_size_constr, filter_size_constr);
		
		// Convert 2D image into 1D array of doubles
		double[] image_1d = convert1d(listMatrix0);
			
		
		// Generate list of unsorted indices
		int[] unsorted_indices = generate_indices(filter_size_constr, image_1d, listMatrix0);
		
		// Generate list of sorted indices
		int[] sorted_indices = sort_indices(unsorted_indices, filter_size_constr,listMatrix0,image_1d);

		// Generate nested image
		double[][] nested_image = nest_image_array(image_1d,filter_size_constr);
		
		// Sort image from 2D to 1D
		double[] sorted_image_array = iterate_indices(image_1d, nested_image, sorted_indices, filter_size_constr);
		
		// Generate maximums list based on 1D array
		double[] max_list = return_maximum(sorted_image_array, filter_size_constr);
		
		// Reconvert 1D maximums array into a 2D image
		double[][] pooled_image = restore_format(max_list,filter_size_constr,listMatrix0.length,listMatrix0[0].length);
		return pooled_image;
	}
	
	
}
