

public class HiddenLayerNeuron 
{
	private double[][] Weights;
	private double bias;
	public HiddenLayerNeuron(double[][] weights, double bias) {
		super();
		Weights = weights;
		this.bias = bias;
	}
	/**
	 * @return the weights
	 */
	public double[][] getWeights() {
		return Weights;
	}
	/**
	 * @param weights the weights to set
	 */
	public void setWeights(double[][] weights) {
		Weights = weights;
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
	
	
}
