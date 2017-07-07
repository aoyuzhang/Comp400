public class MainPooling{
    public static void main(String[] args) {
        Double[][] image =  {   {1.0,1.0,1.0,1.0},
                                {1.0,1.0,1.0,1.0},
                                {1.0,1.0,1.0,1.0},
                                {1.0,1.0,1.0,1.0}
                            };

        Double[][] formatted_image;
        formatted_image = PoolingFunctions.fill0s(image,3);
        TestMatricesJava.print2dMatrix(formatted_image);
    }
}
