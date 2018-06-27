package co.com.ceiba.estacionamiento.william.hincapie.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.com.ceiba.estacionamiento.william.hincapie.entities.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

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

	public String vehicleEntry(Vehicle vehicle) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehicle.getEntryDate());
		if (vehicleList.size() == this.getMaxCapacity()) {
			return "No hay cupos disponibles";
		} else if (vehicle.getLicensePlate().toUpperCase().startsWith("A")
				&& (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
						|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
			return "No autorizado";
		}
		vehicleList.add(vehicle);
		return "Vehiculo ingresado";
	}

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
