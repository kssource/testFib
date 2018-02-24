package de.jk.fib1;

import java.util.ArrayList;

import de.jk.util.FibUtil;

public class Fib1Data {

	private ArrayList<Fib1Item> trainingItemsList ;

	private ArrayList<Fib1Item> testItemsList;

	private double [][] inputTrainigsData;
	private double [][] outputTrainigsData;
	

	public void createData(int inputUnitsCount, int outputUnitsCount, int trainingDataCount) {
		ArrayList<Integer> fibArr = FibUtil.getFibList();
		
		trainingItemsList = new ArrayList<>();
		testItemsList = new ArrayList<>();
		
		for (int i = 0; i < fibArr.size(); i++) {
			Integer fib = fibArr.get(i);
			
			Fib1Item fibItem = new Fib1Item(i, fib, inputUnitsCount, outputUnitsCount);
			if(i < trainingDataCount) {
				trainingItemsList.add(fibItem);
			}else {
				testItemsList.add(fibItem);
			}
		}
		
		inputTrainigsData = new double[trainingDataCount][inputUnitsCount];
		outputTrainigsData = new double[trainingDataCount][outputUnitsCount];
		
		for (int j = 0; j < trainingItemsList.size(); j++) {
			Fib1Item fibItem = trainingItemsList.get(j);
			inputTrainigsData[j] = fibItem.getIndexAsBinary();
			outputTrainigsData[j] = fibItem.getFibNumberAsBinary();
		}

		
	}

	
	public ArrayList<Fib1Item> getTrainingItemsList() {
		return trainingItemsList;
	}

	public ArrayList<Fib1Item> getTestItemsList() {
		return testItemsList;
	}

	public double[][] getInputTrainigsData() {
		return inputTrainigsData;
	}

	public double[][] getOutputTrainigsData() {
		return outputTrainigsData;
	}
	
}
