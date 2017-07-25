import java.io.IOException;

/**
 * Driver for the DecisionTree class. Inputs datafile path from the console and will start the DecisionTree build.
 * @author Shweta
 *
 */
public class testDecisionTree {
	public static void main(String[] args) throws IOException{
		DecisionTree dt = new DecisionTree();
		dt.readTrainData(args[0]);
		dt.buildModel();
		dt.printTree();
		dt.classifyTrainData(args[0]);
		dt.readTestData(args[1]);
		dt.pruning(args[2]);
		dt.testPrunedTree(args[1]);
	}
}
