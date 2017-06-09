/*
 * 4 pixel camera,
 * 
 * */
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
	public static void main(String[] args) throws Exception 
	{
		/*get image from Internet and saves it to src folder*/
	    String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
	    String destinationFile = "image.jpg";
	    saveImage(imageUrl, destinationFile);
	    
	    /*
	     * Read image
	     * Get the RGB values of each pixels
	     */
	    File originalImage = new File("C:\\Users\\hzhang127\\workspace\\NeuroNetwork\\img_fjords.jpg");
	    BufferedImage img=null;
	    try
	    {
	    	img= ImageIO.read(originalImage);
	    	//BufferedImage grayscaleImage= new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
	    	int[][][] rgbTable= new int[img.getWidth()][img.getHeight()][4]; // A table used to store the RGB values of the image's pixels
	    	int[][] greyScaledImage= new int[img.getWidth()][img.getHeight()]; // A table to store greyScaled image.
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
	    			greyScaledImage[i][j]=(c.getRed()+c.getGreen()+c.getBlue())/3;
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

	    int frameWidth=30;
	    int frameHeight=20;
	    double[][] weight= new double[frameWidth][frameHeight];
	    Random randomGenerator= new Random();
	    for(int i=0;i<frameWidth;i++)
	    {
	    	for(int j=0;j<frameHeight;j++)
	    	{
	    		weight[i][j]=randomGenerator.nextDouble();
	    	}
	    }
	    
	    
	    
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
}
