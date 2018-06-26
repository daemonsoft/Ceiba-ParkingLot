package co.com.ceiba.parkinglotservice.domain;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.parkinglotservice.entities.Invoice;
import co.com.ceiba.parkinglotservice.entities.Vehicle;

public class CarCashier extends Cashier {

	private int maxCapacity;
	private int hourPrice;
	private int dayPrice;

	public CarCashier(int maxCapacity, int hourPrice, int dayPrice) {
		super(maxCapacity, hourPrice, dayPrice);
	}

	public String vehicleEntry(Vehicle vehicle) {

		if (vehicleList.size() == this.getMaxCapacity()) {
			return "No hay cupos disponibles";
		}
		vehicleList.add(vehicle);
		return "Vehiculo ingresado";

	}

	public Invoice vehicleExit(Vehicle vehicle) {
		Invoice invoice = new Invoice(vehicle, 0);
		return invoice;
	}
}
