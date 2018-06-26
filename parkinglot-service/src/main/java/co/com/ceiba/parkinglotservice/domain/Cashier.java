package co.com.ceiba.parkinglotservice.domain;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.parkinglotservice.entities.Bike;
import co.com.ceiba.parkinglotservice.entities.Car;
import co.com.ceiba.parkinglotservice.entities.Invoice;
import co.com.ceiba.parkinglotservice.entities.Vehicle;

public abstract class Cashier {

	private int maxCapacity;
	private int hourPrice;
	private int dayPrice;

	protected List<Vehicle> vehicleList;

	public Cashier(int maxCapacity, int hourPrice, int dayPrice) {
		this.maxCapacity = maxCapacity;
		this.hourPrice = hourPrice;
		this.dayPrice = dayPrice;
		setVehicleList(new ArrayList<>());
	}

	public abstract String vehicleEntry(Vehicle vehicle);

	public abstract Invoice vehicleExit(Vehicle vehicle);

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public int getDayPrice() {
		return this.dayPrice;
	}

	public int getHourPrice() {
		return this.getHourPrice();
	}
}
