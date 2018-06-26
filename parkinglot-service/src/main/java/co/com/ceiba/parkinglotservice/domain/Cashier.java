package co.com.ceiba.parkinglotservice.domain;

import co.com.ceiba.parkinglotservice.entities.Vehicle;

public class Cashier {
	
	private final int MAX_CAR = 20;
	private final int MAX_BIKE= 10;

	public String carEntry(Vehicle vehicle) {
		return "Vehiculo ingresado";
	}

}
