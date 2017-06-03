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

public class NeuroNet 
{
	public static void main(String[] args) throws Exception 
	{
		final int imageHeight=640;
		final int imageWidth=963;
		/*get image from Internet and saves it to current project folder */
	    String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
	    String destinationFile = "image.jpg";
	    saveImage(imageUrl, destinationFile);
	    /* open image*/
	    BufferedImage image= null;
	    File f= null;
	    try
	    {
	    	f= new File("C:\\Users\\hzhang127\\workspace\\NeuroNetwork\\image.jpg");
	    	image= new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_ARGB);
	    	image = ImageIO.read(f);
	    	int[][] readValues = new int[imageHeight][imageWidth];
	    	int[][] blueValues = new int[imageHeight][imageWidth]; 
	    	int[][] greenValues =new int[imageHeight][imageWidth];
	    	System.out.println("Reading complete.");
	    }
	    catch(IOException e)
	    {
	    	System.out.println("Error: "+e);
	    }
	    
	    
	    
	    
	}
	
	/* Function used to saves an image in a url to src folder*/
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
}
