package co.com.ceiba.parkinglotservice;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parkinglotservice.domain.BikeCashier;
import co.com.ceiba.parkinglotservice.domain.CarCashier;
import co.com.ceiba.parkinglotservice.domain.Cashier;
import co.com.ceiba.parkinglotservice.entities.Bike;
import co.com.ceiba.parkinglotservice.entities.Car;
import co.com.ceiba.parkinglotservice.entities.Invoice;
import co.com.ceiba.parkinglotservice.entities.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CashierTests {

	Cashier carCashier = new CarCashier(20, 1000, 8000);
	Cashier bikeCashier = new BikeCashier(10, 500, 4000);

	@Test
	public void carEntryTest() {
		carCashier.getVehicleList().clear();
		Vehicle vehicle = new Car("ABC123");
		assertTrue("Vehiculo ingresado".equals(carCashier.vehicleEntry(vehicle)));
	}

	@Test
	public void bikeEntryTest() {
		bikeCashier.getVehicleList().clear();
		Vehicle vehicle = new Bike("ABC123");
		assertTrue("Vehiculo ingresado".equals(bikeCashier.vehicleEntry(vehicle)));
	}

	@Test
	public void carEntryMaxCapacityTest() {
		carCashier.getVehicleList().clear();
		Vehicle vehicle;
		String carEntryResponse = "";
		for (int i = 0; i < carCashier.getMaxCapacity() + 1; i++) {
			vehicle = new Car("ABC12" + i);
			carEntryResponse = carCashier.vehicleEntry(vehicle);
		}
		assertTrue("No hay cupos disponibles".equals(carEntryResponse));
	}

	@Test
	public void bikeEntryMaxCapacityTest() {
		bikeCashier.getVehicleList().clear();
		Vehicle vehicle;
		String bikeEntryResponse = "";
		for (int i = 0; i < bikeCashier.getMaxCapacity() + 1; i++) {
			vehicle = new Bike("ABC12" + i);
			bikeEntryResponse = bikeCashier.vehicleEntry(vehicle);
		}
		assertTrue("No hay cupos disponibles".equals(bikeEntryResponse));
	}

	@Test
	public void carExitByHourTest() {
		carCashier.getVehicleList().clear();
		Calendar calendar = Calendar.getInstance();
		Date entryDate = calendar.getTime();

		calendar.setTime(entryDate);
		calendar.add(Calendar.MINUTE, 59);

		Date exitDate = calendar.getTime();

		Vehicle vehicle = new Car("ABC123", entryDate);
		vehicle.setExitDate(exitDate);

		Invoice invoice = carCashier.vehicleExit(vehicle);
		long amount = invoice.getAmount();
		assertTrue(carCashier.getHourPrice() == amount);
	}

	@Test
	public void carExitByDayTest() {
		carCashier.getVehicleList().clear();
		Calendar calendar = Calendar.getInstance();
		Date entryDate = calendar.getTime();

		calendar.setTime(entryDate);
		calendar.add(Calendar.DATE, 1);

		Date exitDate = calendar.getTime();

		Vehicle vehicle = new Car("ABC123", entryDate);
		vehicle.setExitDate(exitDate);

		Invoice invoice = carCashier.vehicleExit(vehicle);

		assertTrue(carCashier.getDayPrice() == invoice.getAmount());
	}
}
