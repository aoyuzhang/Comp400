public class PoolingFunctions {

    // ReLu method

    // Fill needed spaces with 0
    public static Double[][] fill0s(Double[][] image, int filter_size){
        int columns_remaining = filter_size - (image[0].length%filter_size);
        int rows_remaining = filter_size - (image.length%filter_size);
        Double [][] blank_matrix = new Double [(image.length)+columns_remaining][(image[0].length)+rows_remaining];
        //if true{
            for (int i = 0; i<image.length; i++) {
                for (int j = 0; j<image[i].length; j++) {
                    blank_matrix[i][j] = image[i][j];
                }
            }
        //}

        return blank_matrix;
    }

    public static Double[][][] pooling(image,filter_size){

    }
}
