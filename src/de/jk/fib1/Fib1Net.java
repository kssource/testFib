package de.jk.fib1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.dkriesel.snipe.core.NeuralNetwork;
import com.dkriesel.snipe.core.NeuralNetworkDescriptor;
import com.dkriesel.snipe.training.ErrorMeasurement;
import com.dkriesel.snipe.training.TrainingSampleLesson;

public class Fib1Net {

	private Fib1Data fib1Data;
	private NeuralNetwork network;


	public void doTest1() {

		int inputUnitsCount = 6;// Bits
		int outputUnitsCount = 13;// Bits
		int hidenUnitsCount = 20;

		double trainRate = 0.1;
		int trainRunsCount = 200000;

		int trainingDataCount = 20;

		
		// net
		NeuralNetworkDescriptor desc = 
				new NeuralNetworkDescriptor(inputUnitsCount, hidenUnitsCount, outputUnitsCount);
		
		desc.setSettingsTopologyFeedForward();
		
		
		network = new NeuralNetwork(desc);
		
		fib1Data = new Fib1Data();
		fib1Data.createData(inputUnitsCount, outputUnitsCount, trainingDataCount);
		
		
		
		double[][] inputs = fib1Data.getInputTrainigsData();
		double[][] desiredOutputs = fib1Data.getOutputTrainigsData();

		
		TrainingSampleLesson lesson = new TrainingSampleLesson(inputs, desiredOutputs);
		
		printCurDate();
		System.out.println("Before train, err: "+ErrorMeasurement.getErrorRootMeanSquareSum(network, lesson));
		// train
		network.trainBackpropagationOfError(lesson, trainRunsCount , trainRate );
		printCurDate();
		System.out.println("After train, err: "+ErrorMeasurement.getErrorRootMeanSquareSum(network, lesson));
		
		
		// test
		
		boolean printOutput = true;
		
		int errCount = 0;
		ArrayList<Fib1Item> testTrainData = fib1Data.getTrainingItemsList();
		for(int i=0; i<testTrainData.size(); ++i) {
			Fib1Item item = testTrainData.get(i);
			boolean err = testNet(item, printOutput);
			if(err==true) {
				++errCount;
			}
		}
		
		System.out.println("After Test trainData, errCount: "+errCount);
		
//		int errCount2 = 0;
//		ArrayList<FibItem> testUntrainData = fib1Data.getTestItemsList();
//		for(int i=0; i<testUntrainData.size(); ++i) {
//			FibItem item = testUntrainData.get(i);
//			boolean err = testNet(item, printOutput);
//			if(err==true) {
//				++errCount2;
//			}
//		}
//		
//		System.out.println("After Test UnTrainData, errCount: "+errCount2);
		
		System.out.println("fibNet end");
	}

	private void printCurDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43		
	}
	
	
	// inputIndex 1-based
	private boolean testNet(Fib1Item item, boolean printOutput) {
		double[] testInput = item.getIndexAsBinary();
		double[] outResult = network.propagate(testInput);


		int outFib = Fib1Item.binaryToInt(outResult);
		int expectedVal = item.getFibNumber();
		int errorDif = expectedVal - outFib;
		
		int oneBasedIndex = item.getIndex() + 1;
		System.out.println("Test input val: "+oneBasedIndex+", Test output val: "+outFib
				+", expected :"+item.getFibNumber()+", error: "+errorDif );
		
		
		boolean isErr = errorDif != 0;;
		return isErr ;
	}
	
}
