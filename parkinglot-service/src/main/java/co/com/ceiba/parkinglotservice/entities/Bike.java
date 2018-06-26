package co.com.ceiba.parkinglotservice.entities;

import java.util.Date;

public class Bike extends Vehicle {
	private int engineCapacity;

	public Bike(String licensePlate) {
		super(licensePlate);
	}

	public Bike(String licensePlate, int dayPrice, int hourPrice, Date entryDate, int engineCapacity) {
		super(licensePlate, dayPrice, hourPrice, entryDate);
		this.engineCapacity = engineCapacity;

	}
}
