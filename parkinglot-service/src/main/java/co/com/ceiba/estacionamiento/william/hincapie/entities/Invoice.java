package co.com.ceiba.estacionamiento.william.hincapie.entities;

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
