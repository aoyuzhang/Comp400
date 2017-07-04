package neuralnetwork;

import java.util.ArrayList;

public class Perceptron {
	
	private ArrayList<Double> input;
	private ArrayList<ArrayList<Double>> hiddenMatrix;
	private ArrayList<Double> output;
	private ArrayList<Double> bias;
	
	public Perceptron(){
		this.input=new ArrayList<Double>();
		this.hiddenMatrix=new ArrayList<ArrayList<Double>>();
		this.output=new ArrayList<Double>();
		this.bias=new ArrayList<Double>();
		
	}
	
	public Perceptron(int inputSize,int outputSize){
		this.input=new ArrayList<Double>(inputSize);
		for(int i=0;i<inputSize;i++){
			this.input.add(0.0);
		}
		this.hiddenMatrix=new ArrayList<ArrayList<Double>>(outputSize);
		for(int i=0;i<outputSize;i++){
			ArrayList<Double> row=new ArrayList<Double>(inputSize);
			for(int j=0;j<inputSize;j++){
				row.add(0.0);
			}
			hiddenMatrix.add(row);
		}
		
		this.output=new ArrayList<Double>(outputSize);
		for(int i=0;i<outputSize;i++){
			this.output.add(0.0);
		}
		
		this.bias=new ArrayList<Double>(outputSize);
		for(int i=0;i<outputSize;i++){
			this.bias.add(0.0);
		}		
	}
	
	public void setInput(ArrayList<Double> input){
		this.input=input;
	}
	public ArrayList<Double> getInput(){
		return this.input;
	}
	
	public void setOutput(ArrayList<Double> output){
		this.output=output;
	}
	public ArrayList<Double> getOutput(){
		return this.output;
	}
	
	public void setHiddenMatrix(ArrayList<ArrayList<Double>> matrix){
		this.hiddenMatrix=matrix;
	}
	public ArrayList<ArrayList<Double>> getHiddenMatrix(){
		return this.hiddenMatrix;
	}
	
	public void setBias(ArrayList<Double> bias){
		this.bias=bias;
	}
	public ArrayList<Double> getBias(){
		return this.bias;
	}
	
	//
	//Add or remove a row inside  the hidden Matrix
	//
	
	public void addRow(ArrayList<Double> row,double bias){
		this.hiddenMatrix.add(row);
		this.bias.add(bias);
	}
	
	public boolean addRow(int position, ArrayList<Double> row, double bias){
		if(position<this.hiddenMatrix.size() && position>=0){
			this.hiddenMatrix.add(position, row);
			this.output.add(position,0.0);
			this.bias.add(position,bias);
			return true;
		}
		else
			return false;
	}
	
	public boolean removeRow(int position){
		if(position<this.hiddenMatrix.size() && position>=0){
			this.hiddenMatrix.remove(position);
			this.output.remove(position);
			this.bias.remove(position);
			return true;
		}
		else
			return false;
	}
	
	public boolean replaceRow(int position, ArrayList<Double> row, double bias){
		if(position<this.hiddenMatrix.size() && position>=0){
			this.hiddenMatrix.set(position,row);
			this.bias.set(position, bias);
			return true;
		}
		else
			return false;
	}
	
	public void randomize(){
		for(int i=0;i<this.hiddenMatrix.size();i++){
			ArrayList<Double> random=new ArrayList<Double>(this.hiddenMatrix.get(i).size());
			for(int j=0;j<this.input.size();j++){
				random.add(j,Math.random());
			}
			this.hiddenMatrix.set(i, random);
			this.bias.set(i, Math.random());
		}
	}
	
	//
	// Compute output
	//
	
	public ArrayList<Double> computeOutput(){
		//For each row
		ArrayList<Double> result = new ArrayList<Double>(this.hiddenMatrix.size());
		for(int i=0;i<this.hiddenMatrix.size();i++){
			
			double sum=0;
			ArrayList<Double> current=this.hiddenMatrix.get(i);
			//for each element in the row
			for(int j=0;j<current.size();j++){			
				sum=sum+current.get(j)*this.input.get(j);
			}
			result.add(i,sum+this.bias.get(i));
		}
		return result;
	}
	
	public void setComputedOutput(){
		//Use bias to set the real output of the NN
		ArrayList<Double> result=this.computeOutput();
		for(int i=0;i<this.output.size();i++){
			if(result.get(i)>0){
				this.output.set(i,1.0);
			}
			else{
				this.output.set(i,0.0);
			}
		}
	}
	
	public double getMaxOutput(){
		ArrayList<Double> output=this.output;
		output.sort(null);
		double result=output.get(output.size()-1);
		return result;
	}
	
	
	//
	// Train 
	//
	
	private double train(ArrayList<ArrayList<Double>> dataInput,ArrayList<Double> dataOutput, int featureIndex,boolean randomize){
		
		if(dataInput.size()!=dataOutput.size() || (featureIndex>this.output.size()-1 || featureIndex<0) ){
			return -1;
		}
		
		int count=dataInput.size();
		double totalError=0;
		
		if(randomize){
			this.randomize();
		}

		for(int k=0;k<count;k++){
			ArrayList<Double> input=dataInput.get(k);
			double expectedOutput=dataOutput.get(k);
			this.input=input;
			this.setComputedOutput();
			
			double error=(expectedOutput-this.output.get(featureIndex));
			//
			//Back propagation to adjust the weights
			//
			double newBias=0;
			double oldBias=this.bias.get(featureIndex);
			ArrayList<Double> row=this.hiddenMatrix.get(featureIndex);
			
			for(int i=0;i<this.input.size();i++){
				
				double newWeight = row.get(i) + (error * this.input.get(i));
				row.set(i, newWeight);
				
			}
			newBias=oldBias+error;
			
			this.replaceRow(featureIndex, row,newBias);
		}
		//
		//Second pass to get the error produced by this learning set
		//
		for(int k=0;k<count;k++){
			ArrayList<Double> input=dataInput.get(k);
			double expectedOutput=dataOutput.get(k);
			this.input=input;
			this.setComputedOutput();
			totalError+=Math.abs(expectedOutput-this.output.get(featureIndex));
		}
		return totalError/count;
	}
	
	public void trainPerceptron(ArrayList<ArrayList<Double>> dataInput,ArrayList<Double> dataOutput, int featureIndex, double errorThreshold, int iterationCount){		
		double error=train(dataInput,dataOutput,featureIndex,true);
		int i;
		for(i =1;i<iterationCount;i++){
			if(error>errorThreshold){
				error=train(dataInput,dataOutput,featureIndex,false);
			}
			else{
				break;
			}	
		}
		System.out.println("Error after step "+i+": "+error);
	}
}
