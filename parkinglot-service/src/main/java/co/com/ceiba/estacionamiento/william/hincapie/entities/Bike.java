package co.com.ceiba.estacionamiento.william.hincapie.entities;

import java.util.Date;

public class Bike extends Vehicle {
	private int engineCapacity;

	public Bike(String licensePlate) {
		super(licensePlate);
	}
	
	public Bike(String licensePlate, int engineCapacity) {
		super(licensePlate);
		this.engineCapacity = engineCapacity;
	}

	public Bike(String licensePlate, Date entryDate, int engineCapacity) {
		super(licensePlate, entryDate);
		this.engineCapacity = engineCapacity;
	}

	public int getEngineCapacity() {
		return this.engineCapacity;
	}
}
