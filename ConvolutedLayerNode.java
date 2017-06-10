
public class ConvolutedLayerNode 
{
	private double bias;
	private double[][] frames;
	private double[][] weight;
	private double output;
	
	public ConvolutedLayerNode(double bias, double[][] frames, double[][] weight, double output) 
	{
		super();
		this.bias = bias;
		this.frames = frames;
		this.weight = weight;
		this.output = output;
	}
	/**
	 * @param bias
	 * @param frames
	 * @param weight
	 */
	public ConvolutedLayerNode(double bias, double[][] frames, double[][] weight) 
	{
		super();
		this.bias = bias;
		this.frames = frames;
		this.weight = weight;
	}
	/**
	 * @return the bias
	 */
	public double getBias() {
		return bias;
	}
	/**
	 * @param bias the bias to set
	 */
	public void setBias(double bias) {
		this.bias = bias;
	}
	/**
	 * @return the frames
	 */
	public double[][] getFrames() {
		return frames;
	}
	/**
	 * @param frames the frames to set
	 */
	public void setFrames(double[][] frames) {
		this.frames = frames;
	}
	/**
	 * @return the weight
	 */
	public double[][] getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double[][] weight) {
		this.weight = weight;
	}
	/**
	 * @return the output
	 */
	public double getOutput() {
		return output;
	}
	/**
	 * @param output the output to set
	 */
	public void setOutput(double output) {
		this.output = output;
	}
	/**
	 * 
	 */
	public ConvolutedLayerNode() 
	{
		super();
	}
	
	
}
