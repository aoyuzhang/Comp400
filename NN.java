

// Training images from: http://www.vision.caltech.edu/Image_Datasets/Caltech101/#Download
public class NN 
{
	final int imgaeWidth=800;
	final int imageHeight=800;
	
	private int inputSizes;
	private int numberOfKernel ;
	private int kernelSize;
	private int outputSize;
	
	private double[][] input;
	private double[][][] Kernel;
	private double[][][] convolutionLayer;
	private double[][][] poolingLayer;
	private double[] fullyConnectedHiddenLayer;
	private double[] output;
	
	/**
	 * @param inputSizes
	 * @param numberOfKernel
	 * @param kernelSize
	 * @param outputSize
	 */
	public NN(int inputSizes, int numberOfKernel, int kernelSize, int outputSize) {
		super();
		this.inputSizes = inputSizes;
		this.numberOfKernel = numberOfKernel;
		this.kernelSize = kernelSize;
		this.outputSize = outputSize;
	}
	public void train()
	{
		
	}
	public double[] test(BufferedImage img)
	{
		// Scale image
		ImageUtilities.scale(img, imgaeWidth, imageHeight);
		//Create kernel
		
		
	}
	
	
	//ArrayList<>
	
	
//	File folder = new File("C:\\Users\\hzhang127\\workspace\\NN\\src\\101_ObjectCategories");
//	File[] listOfFiles = folder.listFiles();
//	for (int i = 0; i < listOfFiles.length; i++) 
//	{
//	  File file = listOfFiles[i];
//	  if (file.isFile() && file.getName().endsWith(".txt")) {
//	    String content = FileUtils.readFileToString(file);
//	    /* do somthing with content */
//	  } 
//	}
	
	



}
