package com.starwars.ab.util;

public class StarWarsDate {

	private final double UNKNOW = 10000;
	
	public double convert(String dateString){
		
		if (dateString.contains("BBY"))
			return 0 - Double.parseDouble(dateString.replace("BBY","").replace(",","."));
		if (dateString.contains("ABY"))
			return 0 + Double.parseDouble(dateString.replace("BBY","").replace(",","."));
		
		return UNKNOW;
	}
}
