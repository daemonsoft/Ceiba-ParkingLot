package co.com.ceiba.parkinglotservice;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parkinglotservice.domain.Cashier;
import co.com.ceiba.parkinglotservice.entities.Bike;
import co.com.ceiba.parkinglotservice.entities.Car;
import co.com.ceiba.parkinglotservice.entities.Invoice;
import co.com.ceiba.parkinglotservice.entities.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CashierTests {
	@Test
	public void carEntryTest() {
		Cashier cashier = new Cashier();
		Vehicle vehicle = new Car("ABC123");
		assertTrue("Vehiculo ingresado".equals(cashier.vehicleEntry(vehicle)));
	}

	@Test
	public void bikeEntryTest() {
		Cashier cashier = new Cashier();
		Vehicle vehicle = new Bike("ABC123");
		assertTrue("Vehiculo ingresado".equals(cashier.vehicleEntry(vehicle)));
	}

	@Test
	public void carEntryMaxCapacityTest() {
		Cashier cashier = new Cashier();
		Vehicle vehicle;
		String carEntryResponse = "";
		for (int i = 0; i < Cashier.MAX_CAPACITY_CAR + 1; i++) {
			vehicle = new Car("ABC12" + i);
			carEntryResponse = cashier.vehicleEntry(vehicle);
		}
		assertTrue("No hay cupos disponibles".equals(carEntryResponse));
	}

	@Test
	public void bikeEntryMaxCapacityTest() {
		Cashier cashier = new Cashier();
		Vehicle vehicle;
		String bikeEntryResponse = "";
		for (int i = 0; i < Cashier.MAX_CAPACITY_BIKE + 1; i++) {
			vehicle = new Bike("ABC12" + i);
			bikeEntryResponse = cashier.vehicleEntry(vehicle);
		}
		assertTrue("No hay cupos disponibles".equals(bikeEntryResponse));
	}

	@Test
	public void carExitByHourTest() {

		Cashier cashier = new Cashier();
		Calendar calendar = Calendar.getInstance();
		Date entryDate = calendar.getTime();

		calendar.setTime(entryDate);
		calendar.add(Calendar.MINUTE, 59);

		Date exitDate = calendar.getTime();

		Vehicle vehicle = new Car("ABC123", Cashier.DAY_PRICE_CAR, Cashier.HOUR_PRICE_CAR, entryDate);
		vehicle.setExitDate(exitDate);

		Invoice invoice = cashier.exitVehicle(vehicle);
		long amount = invoice.getAmount();
		assertTrue(Cashier.HOUR_PRICE_CAR == amount);
	}

	@Test
	public void carExitByDayTest() {
		Cashier cashier = new Cashier();
		Calendar calendar = Calendar.getInstance();
		Date entryDate = calendar.getTime();

		calendar.setTime(entryDate);
		calendar.add(Calendar.DATE, 1);

		Date exitDate = calendar.getTime();

		Vehicle vehicle = new Car("ABC123", Cashier.DAY_PRICE_CAR, Cashier.HOUR_PRICE_CAR, entryDate);
		vehicle.setExitDate(exitDate);

		Invoice invoice = cashier.exitVehicle(vehicle);

		assertTrue(Cashier.DAY_PRICE_CAR == invoice.getAmount());
	}
}
