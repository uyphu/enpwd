package com.ltu.secret.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Map<String, Integer> map = new HashMap<String, Integer>();
//		
//		map.put("A", 1);
//		map.put("B", 2);
//		map.put("C", 3);
//		map.put("D", 4);
//		map.put("E", 5);
//		map.put("F", 6);
//		map.put("G", 7);
//		map.put("H", 8);
//		map.put("I", 9);
//		map.put("J", 10);
//		map.put("K", 11);
//		map.put("L", 12);
//		map.put("M", 13);
//		map.put("N", 14);
//		map.put("O", 15);
//		map.put("P", 16);
//		map.put("Q", 17);
//		map.put("R", 18);
//		map.put("S", 19);
//		map.put("T", 20);
//		map.put("U", 21);
//		map.put("V", 22);
//		map.put("W", 23);
//		map.put("X", 24);
//		map.put("Y", 25);
//		map.put("Z", 26);
//		
//		Map<Integer, String> map2 = new HashMap<Integer, String>();
//		
//		map2.put(1, "A");
//		map2.put(2, "B");
//		map2.put(3, "C");
//		map2.put(4, "D");
//		map2.put(5, "E");
//		map2.put(6, "F");
//		map2.put(7, "G");
//		map2.put(8, "H");
//		map2.put(9, "I");
//		map2.put(10, "J");
//		map2.put(11, "K");
//		map2.put(12, "L");
//		map2.put(13, "M");
//		map2.put(14, "N");
//		map2.put(15, "O");
//		map2.put(16, "P");
//		map2.put(17, "Q");
//		map2.put(18, "R");
//		map2.put(19, "S");
//		map2.put(20, "T");
//		map2.put(21, "U");
//		map2.put(22, "V");
//		map2.put(23, "W");
//		map2.put(24, "X");
//		map2.put(25, "Y");
//		map2.put(26, "Z");
//		
//		int n = map.get(String.valueOf(number));
//		List<String> result = new ArrayList<String>();
//		for(int i=0; i<=n-1; i++) {
//			for (int j=i; j<=n-1; j++) {
//				if (i+j == n-1) {
//					result.add("["+map2.get(i+1)+" + " +map2.get(j+1)+"]");
//				}
//			}
//		}
//		String[] stringArray = result.toArray(new String[0]);
//		return stringArray;
////		for (String string : stringArray) {
////			System.out.println(string);
////		}
		
		//String s = "z1[y]zzz2[abc]";
		//System.out.println(decodeString(s));
		int n = 1230;
		System.out.println(isLucky(n));

	}

	public static String decodeString(String s) {
		if (s.isEmpty()) {
			return s;
		}
		int i = 0;
		do {
			String str = "";
			int len = 0;
			if ("[".equals(String.valueOf(s.charAt(i)))) {

				boolean flag = true;
				int j = i + 1; 
				do {
					if (String.valueOf(s.charAt(j)).matches("[0-9]")) {
						flag = false;
						break;
					}
					if ("]".equals(String.valueOf(s.charAt(j)))) {
						break;
					}
					
					//reset
					if ("[".equals(String.valueOf(s.charAt(j)))) {
						str = "";
						len = 0;																		
					} else {
						len++;
						str += s.charAt(j);
					}
					j++;
				} while (j < s.length());
				
				if (flag) {
					String num = "";
					for (int k = i - 1; k >= 0; k--) {
						if (!String.valueOf(s.charAt(k)).matches("[0-9]")) {
							break;
						}
						num = s.charAt(k) + num;
						
					}

					int number = Integer.parseInt(num);
					String result = "";
					for (int l = 0; l < number; l++) {
						result += str;
					}
					int idxStart = len + num.length() + 2;
					String start = s.substring(0, i - num.length());
					String end = s.substring(start.length() + idxStart, s.length());
					// System.out.println(start + result+end);
					s = (start + result + end);
					i = 0;
				} else {
					i++;
				}
				
			} else {
				i++;
			}
			
		} while (i < s.length());
		
		return s;

	}
	
	public static boolean isLucky(int n) {
		String s = String.valueOf(n);
		int first = 0;
		int second = 0;
		int index = s.length()/2;
		for (int i = 0; i < index; i++) {
			first += Integer.parseInt(String.valueOf(s.charAt(i)));
			//System.out.println("s.charAt(i): " + s.charAt(i));
			second += Integer.parseInt(String.valueOf(s.charAt(i+index)));
			//System.out.println("String.valueOf(s.charAt(i+index)): " + String.valueOf(s.charAt(i+index)));
		}
		if (first == second) {
			return true;
		}
		return false;
	}


}
