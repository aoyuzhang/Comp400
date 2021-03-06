/*
 * 4 pixel camera,
 * 
 * */
 import java.awt.AlphaComposite;
 import java.awt.Graphics2D;
 import java.awt.RenderingHints;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.URL;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import java.awt.Color;
 import java.io.IOException;
 import java.util.Random;

public class NeuroNet 
{
	private static final int IMG_WIDTH = 800;
	private static final int IMG_HEIGHT = 800;
	private static int numberOfImagesInsrc=0;
	
	public static void main(String[] args) throws Exception 
	{
		/*get image from Internet and saves it to src folder*/
	    String imageUrl = "https://stepupandlive.files.wordpress.com/2014/09/3d-animated-frog-image.jpg";
	    String destinationFile = "image.jpg";
	    saveImage(imageUrl, destinationFile);
	    
	    /*
	     * Read image
	     * Get the RGB values of each pixels
	     */
	    File originalImage = new File("C:\\Users\\hzhang127\\workspace\\NeuroNetwork\\image.jpg");
	    BufferedImage img=null;
	    double[][][] rgbTable=new double[IMG_WIDTH][IMG_HEIGHT][4]; // A table used to store the RGB values of the image's pixels
	    double[][] greyScaledImage = new double[IMG_WIDTH][IMG_HEIGHT]; // A table to store greyScaled image.
	    try
	    {
	    	img= ImageIO.read(originalImage);
	    	int type = img.getType() == 0? BufferedImage.TYPE_INT_ARGB : img.getType();

	    	/* resize the image*/
			BufferedImage resizeImage = resizeImageWithHint(img, type);
			ImageIO.write(resizeImage, "jpg", new File("C:\\Users\\hzhang127\\workspace\\NeuroNetwork\\img_fjords.jpg"));
			
	    	//BufferedImage grayscaleImage= new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
	    	// int count=0;
			System.out.println("The height of the rescaled image is:"+resizeImage.getHeight() );
			System.out.println("The width of the rescaled image is:"+resizeImage.getWidth() );
	    	for(int i=0;i<resizeImage.getWidth();i++)
	    	{
	    		for(int j=0;j<resizeImage.getHeight();j++)
	    		{
	    			Color c= new Color(resizeImage.getRGB(i, j));
//	    			rgbTable[i][j][0]=c.getRed(); 
//	    			rgbTable[i][j][1]=c.getGreen();
//	    			rgbTable[i][j][2]=c.getBlue();
//	    			rgbTable[i][j][3]=c.getAlpha();
	    			greyScaledImage[i][j]=(c.getRed()*1000000+c.getGreen()*1000+c.getBlue()); // Instead of using three values for each pixel, we take the weighted average. 
//	    			count++;
//	    			System.out.println(count+":"+"red is:"+rgbTable.clone()[i][j][0]+"alpha is"+rgbTable.clone()[i][j][3]+"" );
	    			
	    		}
	    	}
	    }
	    catch(IOException e)
	    {
	    	System.out.println("Error: "+e);
	    }
	    
	    
	    
	    double[][] oness= MatrixUtilities.random(800,800);
	   // MatrixUtilities.printMatrix(oness);
	    double[][]randoms=MatrixUtilities.createMatrixOnes(9, 9);
	    //MatrixUtilities.printMatrix(randoms);
	    double[][] result=Convolution2.convolution2DPadded(oness,800,800,randoms,9,9);
	   // MatrixUtilities.printMatrix(result);
	    
	    
	    //System.out.println("The matrix representation of the image is as follow:");
	    //printMatrix(greyScaledImage);
	    
	    /*
	     * Create neuro network
	     */

//	    /* 1st Convolution layer frames*/
//	    int frameWidth=20;
//	    int frameHeight=20;
//	    int numberOfFeatures=20;
//	    ConvolutedLayerNode[] convLayer = new ConvolutedLayerNode[numberOfFeatures]; // the nodes in a convoluted layer
//	    //double[][][] frame= new double[IMG_WIDTH*IMG_HEIGHT/(frameWidth*frameHeight)][frameWidth][frameHeight]; // convoluted layer
//	    
//	    for(int k=0;k<numberOfFeatures;k++)
//	    {
//	    	double[][] tempConvolutedFrame = new double[IMG_WIDTH/frameWidth][IMG_HEIGHT/frameHeight];// the convoluted frame associated with each feature
//		    for(int i=0;i<IMG_WIDTH;i=i+frameWidth)
//		    {
//		    	for(int j=0;j<IMG_HEIGHT;j=j+frameHeight)
//		    	{
//		    		/*
//		    		 *  a frame to store each frames of the image after we divided it into frames
//		    		 */
//		    		double[][] tempFrame= new double[frameWidth][frameHeight]; 
//		    		for(int x=0;x<frameWidth;x++)
//		    		{
//		    			for(int y=0;y<frameHeight;y++)
//		    			{
//		    				tempFrame[x][y]=greyScaledImage[i+x][j+y];
//		    			}
//		    		}
//		    		convLayer[k]= new ConvolutedLayerNode(); // Create a Convolutional layer node
//		    		
//		    		/* 
//		    		 * create the weights by giving random datas
//		    		 */
//		    	    double[][] weight= new double[frameWidth][frameHeight];
//		    	    Random randomGenerator= new Random();
//		    	    for(int n=0;n<frameWidth;n++)
//		    	    {
//		    	    	for(int m=0;m<frameHeight;m++)
//		    	    	{
//		    	    		weight[n][m]=randomGenerator.nextDouble()*2-1;
//		    	    	}
//		    	    }
//		    	    //printMatrix(weight);
//		    	    
//		    	    /*
//		    	     * update convoluted frame
//		    	     */
//		    	    convLayer[k].setWeight(weight); // set the weight of the slides
//		    	    convLayer[k].setBias(0.5); // set the bias to be 0.5
//		    	    tempConvolutedFrame[i/frameWidth][j/frameHeight]= sigmoid(sum2d(multMatricesC(convLayer[k].getWeight(),tempFrame))); // store sum of weighed value in 
//		    	    //System.out.println("The weighted sum of frame starting from "+i+" width and "+j+" depth and feature number "+k+" is :"+tempConvolutedFrame[i/frameWidth][j/frameHeight]);
//
//		    	}
//		    }
//		    convLayer[k].setFrames(tempConvolutedFrame);
//    	    System.out.println("The weighted image with feature "+k+" is as follow:");
//    	    printMatrix(convLayer[k].getFrames());
//	    }
	    
	    /* Second convolutional layer*/
	    

	    
	    
	    
	}
	
	/*
	 *  Function used to saves an image in a url to src folder
	 */
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
	    URL url = new URL(imageUrl);
	    InputStream is = url.openStream();
	    destinationFile=destinationFile.concat(String.valueOf(numberOfImagesInsrc));
	    numberOfImagesInsrc++;
	    OutputStream os = new FileOutputStream(destinationFile);

	    byte[] b = new byte[2048];
	    int length;

	    while ((length = is.read(b)) != -1) {
	        os.write(b, 0, length);
	    }

	    is.close();
	    os.close();
	}
	/*
	 * Sigmoid Function
	 */
	  public static double sigmoid(double x) 
	  {
		    return (1/( 1 + Math.pow(Math.E,(-1*x))));
	  }
	  /*
	   * Resize image
	   */
	  private static BufferedImage resizeImage(BufferedImage originalImage, int type)
	  {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	  }
	  /*
	   * Resize image with hint
	   */
	  private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type)
	  {

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	 }
}
