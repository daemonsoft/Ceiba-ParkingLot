package co.com.ceiba.parkinglotservice.entities;

import java.util.concurrent.TimeUnit;

import co.com.ceiba.parkinglotservice.domain.Cashier;

public class Invoice {

	private final long DAY_IN_SECONDS = 86400;

	private Vehicle vehicle;

	public Invoice(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public long getAmount() {

		long difference = vehicle.getExitDate().getTime() - vehicle.getEntryDate().getTime();

		long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
		long hours = TimeUnit.MILLISECONDS.toHours(difference);
		System.out.println("Horas " + hours);
		if (seconds <= DAY_IN_SECONDS) {
			return Cashier.HOUR_PRICE_CAR * (hours + 1);
		} else {
			return 8000;
		}
	}
}
