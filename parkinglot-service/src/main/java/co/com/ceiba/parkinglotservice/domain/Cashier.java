package co.com.ceiba.parkinglotservice.domain;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.parkinglotservice.entities.Invoice;
import co.com.ceiba.parkinglotservice.entities.Vehicle;

public abstract class Cashier {

	protected static final long MINIMUN_HOURS_TO_DAY = 9;
	protected int maxCapacity;
	protected int hourPrice;
	protected int dayPrice;

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
		return this.maxCapacity;
	}

	public List<Vehicle> getVehicleList() {
		return this.vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public int getDayPrice() {
		return this.dayPrice;
	}

	public int getHourPrice() {
		return this.hourPrice;
	}
}
