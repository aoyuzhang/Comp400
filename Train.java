import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Train {

	    // File representing the folder that you select using a FileChooser
	    static final File dir = new File("C:\\Users\\hzhang127\\workspace\\NN\\src\\101_ObjectCategories\\airplanes");

	    // array of supported extensions (use a List if you prefer)
	    static final String[] EXTENSIONS = new String[]{
	        "jpg","gif", "png", "bmp" // and other formats you need
	    };
	    // filter to identify images based on their extensions
	    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() 
	    {

	        @Override
	        public boolean accept(final File dir, final String name) 
	        {
	            for (final String ext : EXTENSIONS) 
	            {
	                if (name.endsWith("." + ext)) 
	                {
	                    return (true);
	                }
	            }
	            return (false);
	        }
	    };

	    public static void main(String[] args) {

	        if (dir.isDirectory()) { // make sure it's a directory
	            for (final File f : dir.listFiles(IMAGE_FILTER)) {
	                BufferedImage img = null;

	                try {
	                    img = ImageIO.read(f);
	                    img=scale(img, 800, 800);

	                    // you probably want something more involved here
	                    // to display in your UI
	                    System.out.println("image: " + f.getName());
	                    System.out.println(" width : " + img.getWidth());
	                    System.out.println(" height: " + img.getHeight());
	                    System.out.println(" size  : " + f.length());
	                } catch (final IOException e) {
	                    // handle errors here
	                }
	            }
	        }
	    }
	    
	    /**
	     * scale image
	     * 
	     * @param sbi image to scale
	     * @param imageType type of image
	     * @param dWidth width of destination image
	     * @param dHeight height of destination image
	     * @param fWidth x-factor for transformation / scaling
	     * @param fHeight y-factor for transformation / scaling
	     * @return scaled image
	     */
	    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	        BufferedImage dbi = null;
	        if(sbi != null) {
	            dbi = new BufferedImage(dWidth, dHeight, imageType);
	            Graphics2D g = dbi.createGraphics();
	            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	            g.drawRenderedImage(sbi, at);
	        }
	        return dbi;
	    }
	    /**
	     * scale image 
	     * @param imageToScale
	     * @param dWidth width of new imgage
	     * @param dHeight height of new image
	     * @return
	     */
	    public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
	        BufferedImage scaledImage = null;
	        if (imageToScale != null) {
	            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
	            Graphics2D graphics2D = scaledImage.createGraphics();
	            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
	            graphics2D.dispose();
	        }
	        return scaledImage;
	    }
	

}
