package co.com.ceiba.parkinglotservice.entities;

import java.util.Date;

public class Vehicle {
	private String licensePlate;
	private Date entryDate;
	private Date exitDate;
	private int hourPrice;
	private int dayPrice;

	public Vehicle(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Vehicle(String licensePlate, int dayPrice, int hourPrice, Date entryDate) {
		this.licensePlate = licensePlate;
		this.dayPrice = dayPrice;
		this.hourPrice = hourPrice;
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
