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
	    try
	    {
	    	img= ImageIO.read(originalImage);
	    	int type = img.getType() == 0? BufferedImage.TYPE_INT_ARGB : img.getType();

			BufferedImage resizeImage = resizeImageWithHint(img, type);
			ImageIO.write(resizeImage, "jpg", new File("C:\\Users\\hzhang127\\workspace\\NeuroNetwork\\img_fjords.jpg"));
			
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

	    /* Convolutional layer frames*/
	    int frameWidth=20;
	    int frameHeight=20;
	    int numberOfFeatures=10;
	    ConvolutedLayerNode[][] convLayer = new ConvolutedLayerNode[numberOfFeatures][IMG_WIDTH/frameWidth*IMG_HEIGHT/frameHeight];
	    //double[][][] frame= new double[IMG_WIDTH*IMG_HEIGHT/(frameWidth*frameHeight)][frameWidth][frameHeight]; // covoluted layer
	    
	    for(int k=0;k<numberOfFeatures;k++)
	    {
		    for(int i=0;i<IMG_WIDTH;i=i+frameWidth)
		    {
		    	for(int j=0;j<IMG_HEIGHT;j=j+frameHeight)
		    	{
		    		convLayer[k][]
		    	}
		    }
	    }
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
