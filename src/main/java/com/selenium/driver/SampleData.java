package com.selenium.driver;

import org.json.simple.JSONObject;

public class SampleData {
	
	private static String destinations[] = {"Pune", "Mumbai", "Shendwa", "Indore"};
	
	private static String buses[][] = { {"Pune", "Mumbai", "3"}, {"Mumbai", "Pune", "3"},
			{"Pune", "Indore", "2"}, {"Indore", "Pune", "2"},
			{}};
	

	public static String[] setLocations(String source, String destination, String date) {
		String a[] = new String[3];
		a[0] = source;
		a[1] = destination;
		a[2] = date;
		return a;
	}
	
	public static String[] getdestinations() {
		return destinations;
	}
	
	public static void getbuses() {
		
	}

}
