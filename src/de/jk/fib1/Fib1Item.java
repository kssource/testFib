package de.jk.fib1;

import java.util.Arrays;
import java.util.Iterator;

import de.jk.bit.util.BitSequence;

public class Fib1Item {

	// 0-based index
	private int index;
	private double[] indexAsBinary;

	private int fibNumber;
	private double[] fibNumberAsBinary;
	
	
	private int inputBinaryLenght;
	private int outputBinaryLenght;


	public Fib1Item(int index, int fibNumber, int inputBinaryLenght, int outputBinaryLenght) {
		super();
		this.index = index;
		this.fibNumber = fibNumber;
		this.inputBinaryLenght = inputBinaryLenght;
		this.outputBinaryLenght = outputBinaryLenght;
		
		
		createBinaries();
	}


	private void createBinaries() {
		indexAsBinary = numberToSequence(index, inputBinaryLenght);
		fibNumberAsBinary = numberToSequence(fibNumber, outputBinaryLenght);
	}


	private double[] numberToSequence(int num, int binaryLength) {
		double[] binArr = new double[binaryLength];
		BitSequence indexSeq = new BitSequence(num, binaryLength);
		Iterator<Integer> iter = indexSeq.iterarorInt(BitSequence.DIRECTION.LEFT_TO_RIGHT);
		int i=0;
		while (iter.hasNext()) {
			int val = iter.next();
			binArr[i++] = val;
		}
		
		return binArr;
	}


	public int getIndex() {
		return index;
	}


	public double[] getIndexAsBinary() {
		return indexAsBinary;
	}


	public int getFibNumber() {
		return fibNumber;
	}


	public double[] getFibNumberAsBinary() {
		return fibNumberAsBinary;
	}
	
	public static int binaryToInt(double[] bin) {
//		System.out.println(Arrays.toString(bin));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bin.length; i++) {
			double dResult = bin[i];
			int intDigit = (int) Math.round(dResult);
			if(intDigit<0) {
				intDigit = 0;
			}
			builder.append(intDigit);
		}
		
		String str = builder.toString();

		Integer outInt = Integer.parseInt(str, 2);
		return outInt;
	}
	
}
