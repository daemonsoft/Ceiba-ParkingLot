package co.com.ceiba.estacionamiento.william.hincapie.entities;

import java.util.Date;

public class Vehicle {
	private String licensePlate;
	private Date entryDate;
	private Date exitDate;

	public Vehicle(String licensePlate) {
		this.licensePlate = licensePlate;
		this.entryDate = new Date();
	}

	public Vehicle(String licensePlate, Date entryDate) {
		this.licensePlate = licensePlate;
		this.entryDate = entryDate;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public Date getExitDate() {
		return this.exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
}
