package co.com.ceiba.estacionamiento.william.hincapie.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Vehicle extends ResourceSupport {

	@Id
	private String licensePlate;
	private Date entryDate;
	private Date exitDate;
	
	@JsonCreator
	public Vehicle() {
	}

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

	@Override
	public boolean equals(Object object) {

		if (!super.equals(object)) {
			return false;
		}
		Vehicle vehicleObject = (Vehicle) object;
		if (licensePlate.equals(vehicleObject.getLicensePlate())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
