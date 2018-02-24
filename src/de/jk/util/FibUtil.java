package de.jk.util;

import java.util.ArrayList;

public class FibUtil {

	// out arr [1, 1, 2, 3, 5, ..., 1 134 903 170]
	
	
	public static final int COUNT_OF_FIB_NUMBERS = 45;// fib < MAX_INTEGER
	
	private static ArrayList<Integer> fibList = new ArrayList<>(FibUtil.COUNT_OF_FIB_NUMBERS);
	

	static {
		createFibList();
	}

	
	
	
	
	
	public static ArrayList<Integer> getFibList() {
		return fibList;
	}

	
	private static void createFibList() {
		fibList.add(1);
		fibList.add(1);
		
		for (int i = 2; i <= FibUtil.COUNT_OF_FIB_NUMBERS; i++){
			int n1 = fibList.get(i - 1);
			int n2 = fibList.get(i - 2);
			int r = n1 + n2;
			fibList.add(r);
		}
	}
}
