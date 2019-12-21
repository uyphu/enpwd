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

import java.util.Date;
import java.util.List;

public class Test {
	
	public static class Photo implements Comparable<Photo> {
		
		@Override
		public String toString() {
			return "Photo [date=" + date + ", name=" + name + "]";
		}
		private Date date;
		private String name;
		
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Photo(Date date, String name) {
			this.date = date;
			this.name = name;
		}
		
		public Photo() {
		}
		@Override
		public int compareTo(Photo o) {
			int result = this.date.compareTo(o.date);
			if (result == 0) {
				return this.name.compareTo(o.name);
			}
			return result;
		}
		
	}

	public static void main(String[] args) {
//		// String string = "Ram is going to school";
//		String string = "Your code is syntactically correct and works properly on the example test";
//		// String[] arrayOfString = string.split("\\s+");
//
//		int K = 12;
//
//		String[] array = string.split("\\s+");
//		int count = 0;
//		int i = 0;
//		do {
//			if (array[i].length() < K) {
//				int len = array[i].length();
//				int j = i + 1;
//				if (j < array.length) {
//					do {
//						len += 1;
//						len += array[j++].length();
//					} while (len + 1 < K);
//					if (len > K) {
//						i = j - 1;
//					} else {
//						i = j;
//					}
//				} else {
//					i++;
//				}
//			} else {
//				i++;
//			}
//			count++;
//		} while (i < array.length);
//		System.out.println(count);
		
		String s = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n"
		+"john.png, London, 2015-06-20 15:13:22\n"
		+"myFriends.png, Warsaw, 2013-09-05 14:07:13\n"
		+"Eiffel.jpg, Paris, 2015-07-23 08:03:02\n"
		+"pisatower.jpg, Paris, 2015-07-22 23:59:59\n"
		+"BOB.jpg, London, 2015-08-05 00:02:03\n"
		+"notredame.png, Paris, 2015-09-01 12:00:00\n"
		+"me.jpg, Warsaw, 2013-09-06 15:40:22\n"
		+"a.png, Warsaw, 2016-02-13 13:33:50\n"
		+"b.jpg, Warsaw, 2016-01-02 15:12:22\n"
		+"c.jpg, Warsaw, 2016-01-02 14:34:30\n"
		+"d.jpg, Warsaw, 2016-01-02 15:15:01\n"
		+"e.png, Warsaw, 2016-01-02 09:49:09\n"
		+"f.png, Warsaw, 2016-01-02 10:55:32\n"
		+"g.jpg, Warsaw, 2016-02-29 22:13:11";
				
		Solution solution = new Solution();
		
		List<String> list = solution.newNames(s);
		
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	
	
}
