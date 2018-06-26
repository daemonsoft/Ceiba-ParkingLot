package co.com.ceiba.parkinglotservice.entities;

import java.util.concurrent.TimeUnit;

import co.com.ceiba.parkinglotservice.domain.Cashier;

public class Invoice {

	private static final int MINIMUN_HOURS_TO_DAY = 9;

	private final long DAY_IN_SECONDS = 86400;

	private Vehicle vehicle;

	public Invoice(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public long getAmount() {

		long difference = vehicle.getExitDate().getTime() - vehicle.getEntryDate().getTime();

		long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
		long hours = (long) Math.ceil((float) seconds / 3600);
		System.out.println("Horas " + hours);
		if ((hours + 1) < MINIMUN_HOURS_TO_DAY) {
			return Cashier.HOUR_PRICE_CAR * hours;
		} else {
			return Cashier.DAY_PRICE_CAR;
		}
	}
}
