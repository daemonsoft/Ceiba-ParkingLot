package co.com.ceiba.parkinglotservice.entities;

public class Invoice {

	private Vehicle vehicle;
	private long amount;

	public Invoice(Vehicle vehicle, long amount) {
		this.vehicle = vehicle;
		this.amount = amount;
	}

	public long getAmount() {
		return this.amount;
	}

}
