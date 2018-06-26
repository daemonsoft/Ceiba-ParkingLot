package co.com.ceiba.parkinglotservice.entities;

import java.util.Date;

public class Car extends Vehicle {

	public Car(String licensePlate) {
		super(licensePlate);
	}

	public Car(String licensePlate, int dayPrice, int hourPrice, Date entryDate) {
		super(licensePlate, dayPrice, hourPrice, entryDate);
	}
}
