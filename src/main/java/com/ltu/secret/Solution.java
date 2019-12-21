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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	
	public class Photo implements Comparable<Photo> {
		
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

	List<String> newNames(String s) {
		String lines[] = s.split("\\r?\\n");
		Map<String, List<Photo>> mapList = new HashMap<String, List<Photo>>();
		
		for (int i = 0; i < lines.length; i++) {
			String[] array = lines[i].split(",\\s+");
			if(!mapList.containsKey(array[1])){
				List<Photo> list = new ArrayList<Photo>();
				Date date = toDate(array[2]);
				list.add(new Photo(date, array[0]));
				mapList.put(array[1], list);
			} else {
				List<Photo> list = mapList.get(array[1]);
				Date date = toDate(array[2]);
				list.add(new Photo(date, array[0]));
				mapList.put(array[1], list);
			}
		}
		
		for (String key : mapList.keySet()) {
			Collections.sort(mapList.get(key));
		}
		List<String> result = new ArrayList<String>();
		
		for (String key : mapList.keySet()) {
			String plus = "";
			if (mapList.get(key).size() > 9) {
				plus = "0";
			}
			int num = 1;
			for (Photo photo : mapList.get(key)) {
				String[] arr = photo.getName().split("\\.");
				result.add(new String(key + plus + String.valueOf(num)  + "."  + arr[1]));
				num++;
				if (num > 9) {
					plus = "";
				}
			}
		}
		
		return result;
	}
	
	public static Date toDate(String value) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
			return date;
		} catch (ParseException e) {
			return Calendar.getInstance().getTime();
		}
		
	}
}
