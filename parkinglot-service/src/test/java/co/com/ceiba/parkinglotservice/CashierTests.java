package co.com.ceiba.parkinglotservice;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parkinglotservice.domain.Cashier;
import co.com.ceiba.parkinglotservice.entities.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CashierTests {
	@Test
	public void carEntryTest() {
		Cashier cashier = new Cashier();
		Vehicle vehicle = new Vehicle("ABC123");
		assertTrue("Vehiculo ingresado".equals(cashier.carEntry(vehicle)));
	}
}
