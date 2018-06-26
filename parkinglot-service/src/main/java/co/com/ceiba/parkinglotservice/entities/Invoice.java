package co.com.ceiba.parkinglotservice.entities;

import java.util.concurrent.TimeUnit;

import co.com.ceiba.parkinglotservice.domain.Cashier;

public class Invoice {

	private static final int MINIMUN_HOURS_TO_DAY = 9;

	private final long DAY_IN_SECONDS = 86400;

	private Vehicle vehicle;
	private int amount;

	public Invoice(Vehicle vehicle, int amount) {
		this.vehicle = vehicle;
		this.amount = amount;
	}

	public int getAmount() {
		return this.amount;
	}

}
