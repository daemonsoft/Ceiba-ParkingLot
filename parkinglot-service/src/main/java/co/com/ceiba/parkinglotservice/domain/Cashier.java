package co.com.ceiba.parkinglotservice.domain;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.parkinglotservice.entities.Bike;
import co.com.ceiba.parkinglotservice.entities.Car;
import co.com.ceiba.parkinglotservice.entities.Invoice;
import co.com.ceiba.parkinglotservice.entities.Vehicle;

public class Cashier {

	public static final int MAX_CAPACITY_CAR = 20;
	public static final int MAX_CAPACITY_BIKE = 10;
	public static final int HOUR_PRICE_BIKE = 500;
	public static final int HOUR_PRICE_CAR = 1000;
	public static final int DAY_PRICE_CAR = 8000;
	public static final int DAY_PRICE_BIKE = 4000;

	private List<Vehicle> bikeList;
	private List<Vehicle> carList;

	public Cashier() {
		bikeList = new ArrayList<>(MAX_CAPACITY_BIKE);
		carList = new ArrayList<>(MAX_CAPACITY_CAR);
	}

	public String vehicleEntry(Vehicle vehicle) {

		if (vehicle instanceof Car) {
			if (carList.size() == MAX_CAPACITY_CAR) {
				return "No hay cupos disponibles";
			}
			carList.add(vehicle);
			return "Vehiculo ingresado";
		} else if (vehicle instanceof Bike) {
			if (bikeList.size() == MAX_CAPACITY_BIKE) {
				return "No hay cupos disponibles";
			}
			bikeList.add(vehicle);
			return "Vehiculo ingresado";
		}
		return "Vehiculo no ingresado";

	}

	public Invoice exitVehicle(Vehicle vehicle) {
		Invoice invoice = new Invoice(vehicle);
		return invoice;
	}

}
