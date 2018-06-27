package co.com.ceiba.estacionamiento.william.hincapie.domain;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import co.com.ceiba.estacionamiento.william.hincapie.entities.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

public class CarCashier extends Cashier {

	public CarCashier(int maxCapacity, int hourPrice, int dayPrice) {
		super(maxCapacity, hourPrice, dayPrice);
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

	public Invoice vehicleExit(Vehicle vehicle) {
		long amount = 0;
		long difference = vehicle.getExitDate().getTime() - vehicle.getEntryDate().getTime();

		long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
		long hours = (long) Math.ceil((float) seconds / 3600);

		if ((hours + 1) < MINIMUN_HOURS_TO_DAY) {
			amount = hourPrice * hours;
		} else {
			amount = dayPrice;
		}

		return new Invoice(vehicle, amount);
	}
}
