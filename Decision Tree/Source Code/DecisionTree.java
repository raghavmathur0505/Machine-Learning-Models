import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class contains methods which will create the Decision Tree by using the ID3 techniques.
 * @author Shweta
 * 
 */
public class DecisionTree {
	
	private DecisionTreeNode parentNode = new DecisionTreeNode();
	private Double classEntropy = 0.0;
	private ArrayList<String> attributes = new ArrayList<String>();
	private int numberOfNodes = 1;
	private ArrayList<Integer> leafNodes = new ArrayList<Integer>();
	
	ArrayList<DecisionTreeNode> pruneNodes = new ArrayList<DecisionTreeNode>();
	int numTrainInst = 0;															
	int count = 0;																	
	int total = 0;																	
	int numToPrune = 0;																
	int accuracyNodes =0;
	public DecisionTree(){
		
	}
	
	/**
	 * Data input file is read and stored in the data structure 
	 * @param path
	 */
	public void readTrainData(String path){
		File f = new File(path);
		if(f.exists()){
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line, words[];
				int i = 0;
				if((line = br.readLine()) != null){
					words = line.split("\t");
					//initialize arrays for attributes
					for(int j = 0; j < words.length; j++){
						attributes.add(words[j]);
						ArrayList<String> input = new ArrayList<String>();
						parentNode.data.add(input);
						parentNode.attributeNames.add(words[j]);
					}
				}
				parentNode.nodeNumber=1;
				//storing the data in the attribute arrays
				while((line = br.readLine())!= null){
					words = line.split("\t");
					for(int j = 0; j < words.length; j++){
						parentNode.data.get(j).add(words[j]);
					}
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Test Data input file is read and stored in the data structure 
	 * @param path
	 */
	public void readTestData(String path){ //used for reading test data-file
		DecisionTreeNode node = null;
		File f = new File(path);
		if(f.exists()){
			String line, words[] = null;
			double noOfInstancesCorrectlyClassified=0;
			double numInstances=0;
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				if((line = br.readLine()) != null){
					words = line.split("\t");
					//initialize arrays for attributes	
				}			
				while((line = br.readLine())!= null){ //testing the data-set for accuracy
					words = line.split("\t");
					ArrayList<String> test = new ArrayList<>(words.length);
					for(int i = 0; i < words.length; i++){
						test.add(words[i]);
					}
					node = parentNode;
					int k=0;
					while(k != words.length-2 && (node.leftNode !=null || node.rightNode!=null)){
						if(test.get(node.decidingAttribute).equals("0")){
							test.remove(node.decidingAttribute);
							node=node.leftNode;
						}
						else{
							test.remove(node.decidingAttribute);
							node=node.rightNode;
						}
						k++;
					}
					String prediction = node.noOfOnes > node.noOfZeros ? "1" : "0";
					if(prediction.equalsIgnoreCase(words[words.length-1])){
						noOfInstancesCorrectlyClassified = noOfInstancesCorrectlyClassified+1;
					}
					numInstances++;
				}
				br.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("*********************************************************************************************");
			System.out.println("Number of testing instances="+numInstances);
			System.out.println("Number of testing instances correctly classified="+noOfInstancesCorrectlyClassified);
			System.out.println("Number of testing attributes="+words.length);
			System.out.println("Accuracy of the model on the testing dataset="+((noOfInstancesCorrectlyClassified/numInstances)*100));
			System.out.println("*********************************************************************************************");
		}	
	}
	
	/**
	 * Test Data input file is read and stored in the data structure 
	 * @param path
	 */
	public void classifyTrainData(String path){ //used for reading test data-file
		DecisionTreeNode node = null;
		File f = new File(path);
		if(f.exists()){
			String line, words[] = null;
			double noOfInstancesCorrectlyClassified=0;
			double numInstances=0;
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				if((line = br.readLine()) != null){
					words = line.split("\t");
					//initialize arrays for attributes	
				}			
				while((line = br.readLine())!= null){ //testing the data-set for accuracy
					words = line.split("\t");
					ArrayList<String> test = new ArrayList<>(words.length);
					for(int i = 0; i < words.length; i++){
						test.add(words[i]);
					}
					node = parentNode;
					int k=0;
					while(k != words.length-2 && (node.leftNode !=null || node.rightNode!=null)){
						if(test.get(node.decidingAttribute).equals("0")){
							test.remove(node.decidingAttribute);
							node=node.leftNode;
						}
						else{
							test.remove(node.decidingAttribute);
							node=node.rightNode;
						}
						k++;
					}
					String prediction = node.noOfOnes > node.noOfZeros ? "1" : "0";
					if(prediction.equalsIgnoreCase(words[words.length-1])){
						noOfInstancesCorrectlyClassified = noOfInstancesCorrectlyClassified+1;
					}
					numInstances++;
				}
				br.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("*********************************************************************************************");
			System.out.println("Number of training instances="+numInstances);
			System.out.println("Number of training instances correctly classified="+noOfInstancesCorrectlyClassified);
			System.out.println("Number of training attributes="+words.length);
			System.out.println("Accuracy of the model on the training dataset="+((noOfInstancesCorrectlyClassified/numInstances)*100));
			System.out.println("*********************************************************************************************");
		}	
	}

	public void testPrunedTree(String path) {
		DecisionTreeNode node = null;
		File f = new File(path);
		if(f.exists()){
			String line, words[] = null;
			double noOfInstancesCorrectlyClassified=0;
			double numInstances=0;
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				if((line = br.readLine()) != null){
					words = line.split("\t");
					//initialize arrays for attributes	
				}			
				while((line = br.readLine())!= null){ //testing the data-set for accuracy
					words = line.split("\t");
					ArrayList<String> test = new ArrayList<>(words.length);
					for(int i = 0; i < words.length; i++){
						test.add(words[i]);
					}
					node = parentNode;
					int k=0;
					while(k != words.length-2 && (node.leftNode !=null || node.rightNode!=null)){
						if(test.get(node.decidingAttribute).equals("0")){
							if(!node.leftNode.isPruned){
								test.remove(node.decidingAttribute);
								node=node.leftNode;
							}else{
								break;
							}
						}
						else{
							if(!node.rightNode.isPruned){
								test.remove(node.decidingAttribute);
								node=node.rightNode;
							}else {
								break;
							}
						}
						k++;
					}
					String prediction = node.noOfOnes > node.noOfZeros ? "1" : "0";
					if(prediction.equalsIgnoreCase(words[words.length-1])){
						noOfInstancesCorrectlyClassified = noOfInstancesCorrectlyClassified+1;
					}
					numInstances++;
				}
				br.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("*********************************************************************************************");
			System.out.println("Number of testing instances for pruned tree="+numInstances);
			System.out.println("Number of instances correctly classified after pruning="+noOfInstancesCorrectlyClassified);
			System.out.println("Number of testing attributes="+words.length);
			System.out.println("Accuracy of the model on the testing dataset after pruning="+((noOfInstancesCorrectlyClassified/numInstances)*100));
			System.out.println("*********************************************************************************************");
		}
	}
	
	/**
	 * Method will create the root node for DT
	 */
	public void buildModel(){
		if(isID3SplitNode(parentNode)==true){
			splitNode(parentNode);
		}
		System.out.println("No of leaf nodes in the tree "+leafNodes.size());
	}
	
	
	/**
	 * method will split the node using ID3 calculations
	 * @param node
	 * @param  
	 */
	public void splitNode(DecisionTreeNode node){
		ArrayList<Double> conditionalEntropy = new ArrayList<Double>();
		Iterator<ArrayList<String>> i1= node.data.iterator();
		//calculate class entropy
		classEntropy = calculateClassEntropy(node);
		//calculate conditinal entropy for attributes of node
		for(int i = 0; i < node.data.size()-1 && i1.hasNext(); i++){			
				conditionalEntropy.add(calculateConditionalEntropy(i1.next(),node.data.get(node.data.size()-1)));
		}
		//Select the deciding attribute
		node.decidingAttribute = findMaxInformationGain(conditionalEntropy,classEntropy);
		
		//defining the split nodes
		node.leftNode = new DecisionTreeNode();
		node.rightNode = new DecisionTreeNode();
		node.leftNode.nodeNumber = node.nodeNumber*2;
		node.leftNode.parent = node;
		node.rightNode.nodeNumber = (node.nodeNumber*2)+1;
		node.rightNode.parent = node;
		numberOfNodes = numberOfNodes +2;
				
		for(int i=0; i<node.data.size()-1;i++){
			ArrayList<String> input1 = new ArrayList<>();
			ArrayList<String> input = new ArrayList<>();
			node.leftNode.data.add(input1);
			node.rightNode.data.add(input);
			if(i!= node.decidingAttribute){
				node.leftNode.attributeNames.add(node.attributeNames.get(i));
				node.rightNode.attributeNames.add(node.attributeNames.get(i));
			}
		}
		node.leftNode.attributeNames.add(node.attributeNames.get(node.attributeNames.size()-1));
		node.rightNode.attributeNames.add(node.attributeNames.get(node.attributeNames.size()-1));
		
		//Assigning the data to left and right nodes of the selected split node
		ArrayList<String> splitAttribute = node.data.get(node.decidingAttribute);
		//Note: j calculates going through each instance and k calculates going across attributes
		for(int j = 0; j < node.data.get(0).size(); j++){
			if(splitAttribute.get(j).equals("0")){
				for(int parentDataIndex = 0, childDataIndex = 0; parentDataIndex < node.data.size(); parentDataIndex++, childDataIndex++){
					if(parentDataIndex == node.decidingAttribute) parentDataIndex++;
					node.leftNode.data.get(childDataIndex).add(node.data.get(parentDataIndex).get(j));
				}
			}else {
				for(int parentDataIndex = 0, childDataIndex = 0; parentDataIndex < node.data.size(); parentDataIndex++, childDataIndex++){
					if(parentDataIndex == node.decidingAttribute) parentDataIndex++;
					node.rightNode.data.get(childDataIndex).add(node.data.get(parentDataIndex).get(j));
				}
			}
		}/*
		System.out.println(node.leftNode.attributeNames+"-------------------LeftNode Attributes");
		System.out.println(node.rightNode.attributeNames+"-------------------RightNode Attributes");*/
		//Check for ID3 split node conditions
		if(isID3SplitNode(node.leftNode)){
			splitNode(node.leftNode);
		}else{
			leafNodes.add(node.nodeNumber);
		}
		
		if(isID3SplitNode(node.rightNode)){
			splitNode(node.rightNode);	
		}else {
			leafNodes.add(node.nodeNumber);
		}
	}
	
	/**
	 * Check for the ID3 Stop splitting conditions. Will return true when node needs to be split, otherwise returns false.
	 * @param node
	 * @return true/false
	 */
	private boolean isID3SplitNode(DecisionTreeNode node) {
		HashSet<String> output= new HashSet<String>(node.data.get(node.data.size()-1));
		if((output.size()>1) && (node.data.size()>1))
			return true;
		else return false;
	}

	void printTree(){
		ArrayList<ArrayList<Integer>> traversalPaths = new ArrayList<ArrayList<Integer>>();
		for(int i = 0;i < leafNodes.size(); i++){
			ArrayList<Integer> traversalPath = new ArrayList<>();
			int factor = leafNodes.get(i);
			while(factor != 1){
				traversalPath.add(factor);
				if(factor % 2 == 0){
					factor = factor/2;
				}else{
					factor = (factor-1)/2;
				}
			}
			Collections.sort(traversalPath);
			traversalPaths.add(traversalPath);
		}
		for(ArrayList<Integer> traversalPath : traversalPaths){
			DecisionTreeNode node = parentNode;
			System.out.print(node.attributeNames.get(node.decidingAttribute));
			for(int i : traversalPath){
				if(i % 2 == 0){
					System.out.print(" :0"+"->");
					node = node.leftNode;
					System.out.print(node.attributeNames.get(node.decidingAttribute));
				}else {
					System.out.print(" :1"+"->");
					node = node.rightNode;
					System.out.print(node.attributeNames.get(node.decidingAttribute));
				}
			}
			System.out.print(" "+ (node.noOfOnes > node.noOfZeros ? 1 : 0));
			System.out.println();
		}
	}
	/**
	 * Finds the attribute for splitting
	 * @param conditionalEntropy
	 * @param classEntropy
	 * @return index of the split attribute selected
	 */
	private int findMaxInformationGain(ArrayList<Double> conditionalEntropy, Double classEntropy) {
		double minVal = conditionalEntropy.get(0);
		int index = 0;
		for(int i = 1; i < conditionalEntropy.size(); i++){
			if(conditionalEntropy.get(i) < minVal){
				minVal = conditionalEntropy.get(i);
				index = i;
			}
		}
		return index;
	}
	
	
	/**
	 *	method calculates conditional entropy for a given node
	 * @param x The attribute list and values
	 * @param y the output list and values
	 * @return conditionalEntropy
	 */
	private Double calculateConditionalEntropy(ArrayList<String> x, ArrayList<String> y) {
		Iterator<String> xPos = x.iterator();
		Iterator<String> yPos = y.iterator();
		Double noOfZeros = 0.0;
		Double noOfOnes = 0.0;
		Double noOfZeroGivenZero = 0.0;
		Double noOfOneGivenZero = 0.0;
		Double noOfZeroGivenOne = 0.0;
		Double noOfOneGivenOne = 0.0;
		
		while(xPos.hasNext()){
			if(xPos.next().equals("0")){
				noOfZeros++;
				if(yPos.next().equals("0"))
					noOfZeroGivenZero++;
				else
					noOfOneGivenZero++;
			}else{
				noOfOnes++;
				if(yPos.next().equals("0"))
					noOfZeroGivenOne++;
				else
					noOfOneGivenOne++;
			}
		}
		Double probabilityOfZero = noOfZeros/(noOfZeros + noOfOnes);
		Double probabilityOfOne = noOfOnes/(noOfZeros + noOfOnes);
		Double probabilityZeroGivenZero = (noOfZeroGivenZero/(noOfZeroGivenZero+noOfOneGivenZero));
		Double probabilityOneGivenZero = 1- probabilityZeroGivenZero;
		Double probabilityZeroGivenOne = (noOfZeroGivenOne/(noOfZeroGivenOne+noOfOneGivenOne));
		Double probabilityOneGivenOne = 1- probabilityZeroGivenOne;
		Double entropyGivenZero = 0.0;
		Double entropyGivenOne = 0.0;
		if(probabilityZeroGivenZero == 0.0 || probabilityOneGivenZero == 0.0){
			
		}else{
			entropyGivenZero = -( (probabilityZeroGivenZero*(Math.log10(probabilityZeroGivenZero)/Math.log10(2)) ) + (probabilityOneGivenZero*(Math.log10(probabilityOneGivenZero)/Math.log10(2))));
		}
		if(probabilityZeroGivenOne == 0.0 || probabilityOneGivenOne == 0.0){
			
		}else {
			entropyGivenOne = -( (probabilityZeroGivenOne*(Math.log10(probabilityZeroGivenOne)/Math.log10(2)) ) + (probabilityOneGivenOne*(Math.log10(probabilityOneGivenOne)/Math.log10(2))));
		}
		Double conditionalEntropy =  (probabilityOfZero * entropyGivenZero) + (probabilityOfOne * entropyGivenOne);
		return conditionalEntropy;
	}
	
	/**
	 *  method calculates class entropy for a given node
	 * @param node
	 * @return classEntropy
	 */
	private Double calculateClassEntropy(DecisionTreeNode node) {
		ArrayList<String> outputData = node.data.get(node.data.size()-1);
		Double noOfZeros = 0.0;
		Double noOfOnes = 0.0;
		for(int i = 0; i < outputData.size();  i++){
			if(outputData.get(i).equals("0")){
				noOfZeros++;
			}else{
				noOfOnes++;
			}
		}
		Double probabilityOfZero = noOfZeros/(noOfZeros + noOfOnes);
		Double probabilityOfOne = noOfOnes/(noOfZeros + noOfOnes);
		node.noOfOnes = noOfOnes;
		node.noOfZeros = noOfZeros;
		Double entropy = -( (probabilityOfZero*(Math.log10(probabilityOfZero)/Math.log10(2)) ) + (probabilityOfOne*(Math.log10(probabilityOfOne)/Math.log10(2))));
		return entropy;
	}
	
	public void pruning(String pruneFactor) throws IOException{ //added for pruning
		double pruningFactor = Math.ceil(Double.parseDouble(pruneFactor) * numberOfNodes);
		System.out.println("PruningFactor "+pruningFactor);
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 2; i <= numberOfNodes; i++ ){
			list.add(i);
		}
		Collections.shuffle(list);
		ArrayList<Integer> nodesToPrune = new ArrayList<>();
		for(int i = 0; i < pruningFactor; i++){
			nodesToPrune.add(list.get(i));
		}
		System.out.println("Nodes to prune "+nodesToPrune);
		ArrayList<ArrayList<Integer>> traversalPaths = new ArrayList<ArrayList<Integer>>();
		for(int i = 0;i < nodesToPrune.size(); i++){
			ArrayList<Integer> traversalPath = new ArrayList<>();
			int factor = nodesToPrune.get(i);
			while(factor != 1){
				traversalPath.add(factor);
				if(factor % 2 == 0){
					factor = factor/2;
				}else{
					factor = (factor-1)/2;
				}
			}
			Collections.sort(traversalPath);
			traversalPaths.add(traversalPath);
		}
		DecisionTreeNode dtCopy = parentNode;
		for(ArrayList<Integer> traversalPath : traversalPaths){
			DecisionTreeNode node = dtCopy;
			for(int i : traversalPath){
				if(i % 2 == 0){
					if(node.leftNode != null){
						node = node.leftNode;
					}
				}else {
					if(node.rightNode != null){
						node = node.rightNode;
					}
				}
			}
			node.isPruned = true;
		}
		parentNode = dtCopy;
	}

	/**
	 * This is a simple data structure of a a binary tree.
	 * It contains two pointers. For a given decidingAttribute(which is calculated from the data attribute of the class by calculating the 
	 * information gain and comparing them ), the left node is when the decidingAttribute is 0 and rightNode when decidingAttribute is 1.
	 * All the values of an attribute is stored in an ArrayList and all such ArrayLists are stored in another ArrayList 'data'.
	 * @author Shweta
	 *
	 */
	public class DecisionTreeNode {
		DecisionTreeNode leftNode;
		DecisionTreeNode rightNode;
		DecisionTreeNode parent;
		ArrayList<ArrayList<String>> data;
		int decidingAttribute;
		ArrayList<String> attributeNames;
		double noOfZeros;
		double noOfOnes;
		int totalNumInstances;
		int nodeNumber;
		boolean isPruned;
		
		public DecisionTreeNode(){
			this.leftNode = null;
			this.rightNode = null;
			this.parent= null;
			this.data = new ArrayList<ArrayList<String>>();
			this.decidingAttribute = -2;
			this.attributeNames = new ArrayList<>();
			this.noOfZeros = 0;
			this.noOfOnes = 0;
			this.totalNumInstances= 0;
			this.nodeNumber=0;
			this.isPruned = false;
		}
	}
}
