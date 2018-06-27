package co.com.ceiba.estacionamiento.william.hincapie.domain;

import java.util.concurrent.TimeUnit;

import co.com.ceiba.estacionamiento.william.hincapie.entities.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

public class BikeCashier extends Cashier {

	public BikeCashier(int maxCapacity, int hourPrice, int dayPrice) {
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
		long amount = 0;
		long difference = vehicle.getExitDate().getTime() - vehicle.getEntryDate().getTime();

		long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
		long hours = (long) Math.ceil((float) seconds / 3600);

		if ((hours + 1) < MINIMUN_HOURS_TO_DAY) {
			amount = hourPrice * hours;
		} else {
			amount = dayPrice;
		}
		Invoice invoice = new Invoice(vehicle, amount);
		return invoice;
	}
}
