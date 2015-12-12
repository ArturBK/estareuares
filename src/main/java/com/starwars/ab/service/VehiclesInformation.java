package com.starwars.ab.service;

import org.springframework.stereotype.Component;

@Component
public class VehiclesInformation {

	private final int TANKDROID = 4;
	
	public int getVehicleCapacitie(){
		
		return TANKDROID;
	}
}
