package co.com.ceiba.estacionamiento.william.hincapie.domain;

import java.util.concurrent.TimeUnit;

import co.com.ceiba.estacionamiento.william.hincapie.entities.Bike;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

public class BikeCashier extends Cashier {

	public BikeCashier(int maxCapacity, int hourPrice, int dayPrice) {
		super(maxCapacity, hourPrice, dayPrice);
	}

	public Invoice vehicleExit(Vehicle vehicle) {
		long amount = 0;
		long difference = vehicle.getExitDate().getTime() - vehicle.getEntryDate().getTime();

		long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
		long hours = (long) Math.ceil((float) seconds / 3600);
		long days = (long) Math.ceil((float) seconds / 86400);

		if ((hours + 1) < MINIMUN_HOURS_TO_DAY) {
			amount = hourPrice * hours;
		} else {
			amount = dayPrice * days;
			if (hours > 24) {
				for (int i = 0; i < days; i++) {
					hours = hours - 24;
				}
				amount = amount + hourPrice * hours;
			}
		}

		if (((Bike) vehicle).getEngineCapacity() > 500) {
			amount = amount + 2000;
		}

		return new Invoice(vehicle, amount);
	}
}
