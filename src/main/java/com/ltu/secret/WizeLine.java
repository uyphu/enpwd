/*
 * Copyright 2017 ltu.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://ltu.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.ltu.secret;

// TODO: Auto-generated Javadoc
/**
 * The Class WizeLine.
 *
 * @author Phu Le
 * @date Jul 2, 2017
 */
public class WizeLine {
	
	/**
	 * Prints the 1 to 100.
	 */
	public static void print1To100() {
		for (int i = 1; i <= 100; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				System.out.println("WizeLine");
			} else if (i % 3 == 0) {
				System.out.println("Wize");
			} else if (i % 5 == 0) {
				System.out.println("Line");
			} else {
				System.out.println(i);
			}
		}
	}
	
	public static int[] move0s(int[] a, int n) {
		int[] array = new int[n];
		int p = 0;
		for (int i = 0; i < n; i++) {
	        if (a[i] == 0) {
	        	array[p++] = 0;
	        }
	    }

	    for (int i = 0; i < n; ++i) {
	        if (a[i] != 0) {
	        	array[p++] = a[i];
	        }
	    }
	    
	    return array;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		print1To100();
		
		int[] a = {2, -12, 0, 7, 0, -99};
		int n = 6;
		a = move0s(a, n);
		
		for (int i = 0; i < n; ++i) {
	        System.out.println(a[i]);
	    }
	}
}
