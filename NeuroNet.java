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
	private static final int IMG_WIDTH = 600;
	private static final int IMG_HEIGHT = 600;
	
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
	    	for(int i=0;i<img.getWidth();i++)
	    	{
	    		for(int j=0;j<img.getHeight();j++)
	    		{
	    			Color c= new Color(img.getRGB(i, j));
	    			rgbTable.clone()[i][j][0]=c.getRed();
	    			rgbTable.clone()[i][j][1]=c.getGreen();
	    			rgbTable.clone()[i][j][2]=c.getBlue();
	    			rgbTable.clone()[i][j][3]=c.getAlpha();
	    			greyScaledImage[i][j]=(c.getRed()+c.getGreen()+c.getBlue())/3; // Instead of using three values for each pixel, we take the weighted average. 
//	    			count++;
//	    			System.out.println(count+":"+"red is:"+rgbTable.clone()[i][j][0]+"alpha is"+rgbTable.clone()[i][j][3]+"" );
	    			
	    		}
	    	}
	    }
	    catch(IOException e)
	    {
	    	System.out.println("Error: "+e);
	    }
	    
	    /*
	     * Create neuro network
	     */

	    /* Convolution layer frames*/
	    int frameWidth=20;
	    int frameHeight=20;
	    int numberOfFeatures=10;
	    ConvolutedLayerNode[][][] convLayer = new ConvolutedLayerNode[numberOfFeatures][IMG_WIDTH/frameWidth][IMG_HEIGHT/frameHeight];
	    //double[][][] frame= new double[IMG_WIDTH*IMG_HEIGHT/(frameWidth*frameHeight)][frameWidth][frameHeight]; // convoluted layer
	    
	    for(int k=0;k<numberOfFeatures;k++)
	    {
		    for(int i=0;i<IMG_WIDTH;i=i+frameWidth)
		    {
		    	for(int j=0;j<IMG_HEIGHT;j=j+frameHeight)
		    	{
		    		double[][] tempFrame= new double[frameWidth][frameHeight]; // a frame to store each frames of the image after we divided it into frames
		    		for(int x=0;x<frameWidth;x++)
		    		{
		    			for(int y=0;y<frameHeight;y++)
		    			{
		    				tempFrame[x][y]=greyScaledImage[i+x][j+y];
		    			}
		    		}
		    		
		    		
		    		convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT]= new ConvolutedLayerNode();
		    		/* create the weights*/
		    	    double[][] weight= new double[frameWidth][frameHeight];
		    	    Random randomGenerator= new Random();
		    	    for(int n=0;n<frameWidth;n++)
		    	    {
		    	    	for(int m=0;m<frameHeight;m++)
		    	    	{
		    	    		weight[n][m]=randomGenerator.nextDouble();
		    	    	}
		    	    }
		    	    convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT].setWeight(weight); // set the weight of the slides
		    	    convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT].setFrames(multMatricesC(convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT].getWeight(),tempFrame)); // set the weighted sum
		    	    convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT].setBias(0.5); // set the bias to be 0.5
		    	    convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT].setOutput(sigmoid(sum2d(convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT].getFrames())+convLayer[k][i/IMG_WIDTH][j/IMG_HEIGHT].getBias()));
		    	    
		    	}
		    }
	    }

	    
	    
	    
	}
	/* 
	 * A function that multiply two matrices Entries
	 */
	public static double[][] multMatricesC(double[][] A, double[][] B)
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
	/*
	 * A function to compute the sum of entries in an matrix
	 */
	public static double sum2d (double[ ][ ] array2d)  
	{
	    double sum = 0;
	    for (int row=0; row < array2d.length; row++)
	    {
	        for (int col=0; col < array2d[row].length; col++)
	        {
	            sum = sum + array2d [row][col];
	        }
	    }

	    return sum;
	}
	/*
	 *  Function used to saves an image in a url to src folder
	 */
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
	    URL url = new URL(imageUrl);
	    InputStream is = url.openStream();
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
