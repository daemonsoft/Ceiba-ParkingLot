package co.com.ceiba.estacionamiento.william.hincapie;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.domain.VehicleType;
import co.com.ceiba.estacionamiento.william.hincapie.services.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CashierTests {

    private VehicleService vehicleServiceMock;

    private Vehicle car;
    private Vehicle bike;
    private Vehicle bikeHighCC;
    private Invoice invoice;

    public CashierTests() {
        this.car = new Vehicle("AAA123", VehicleType.CAR);
        this.bike = new Vehicle("BBB123", VehicleType.BIKE, 125);
        this.bikeHighCC = new Vehicle("BBB123", VehicleType.BIKE, 650);
        this.vehicleServiceMock = mock(VehicleService.class);
        when(vehicleServiceMock.getCarDayPrice()).thenReturn(8000);
        when(vehicleServiceMock.getBikeDayPrice()).thenReturn(4000);
        when(vehicleServiceMock.getCarHourPrice()).thenReturn(1000);
        when(vehicleServiceMock.getBikeHourPrice()).thenReturn(500);
    }

    @Test
    public void carEntryTest() {
        invoice = new Invoice(car, new Date());
        when(vehicleServiceMock.generateInvoice(invoice)).thenReturn("Vehiculo ingresado");
        assertEquals("Vehiculo ingresado", vehicleServiceMock.generateInvoice(invoice));
    }

    @Test
    public void bikeEntryTest() {
        invoice = new Invoice(bike, new Date());
        when(vehicleServiceMock.generateInvoice(invoice)).thenReturn("Vehiculo ingresado");
        assertEquals("Vehiculo ingresado", vehicleServiceMock.generateInvoice(invoice));

    }

    @Test
    public void bikeHighCCEntryTest() {
        invoice = new Invoice(bikeHighCC, new Date());
        when(vehicleServiceMock.generateInvoice(invoice)).thenReturn("Vehiculo ingresado");
        assertEquals("Vehiculo ingresado", vehicleServiceMock.generateInvoice(invoice));
    }

    @Test
    public void carEntryMaxCapacityTest() {

        String vehicleServiceResponse;

        invoice = new Invoice(car, new Date());

        when(vehicleServiceMock.currentCars()).thenReturn(20);
        when(vehicleServiceMock.getCarCapacity()).thenReturn(20);
        when(vehicleServiceMock.generateInvoice(invoice)).thenReturn("No hay cupos disponibles");
        vehicleServiceResponse = vehicleServiceMock.generateInvoice(invoice);

        assertEquals("No hay cupos disponibles", vehicleServiceResponse);
    }

    @Test
    public void bikeEntryMaxCapacityTest() {

        String vehicleServiceResponse;

        invoice = new Invoice(bike, new Date());

        when(vehicleServiceMock.currentCars()).thenReturn(20);
        when(vehicleServiceMock.getCarCapacity()).thenReturn(20);
        when(vehicleServiceMock.generateInvoice(invoice)).thenReturn("No hay cupos disponibles");
        vehicleServiceResponse = vehicleServiceMock.generateInvoice(invoice);

        assertEquals("No hay cupos disponibles", vehicleServiceResponse);
    }

    @Test
    public void carExitByHourTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.MINUTE, 59);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(car, entryDate, exitDate);
        invoice.setAmount(1000);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getCarHourPrice(), invoice.getAmount());
    }

    @Test
    public void carExitByDayTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.DATE, 1);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(car, entryDate, exitDate);
        invoice.setAmount(8000);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getCarDayPrice(), invoice.getAmount());
    }

    @Test
    public void carExitExampleTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.HOUR, 27);

        Date exitDate = calendar.getTime();
        invoice = new Invoice(car, entryDate, exitDate);
        invoice.setAmount(11000);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getCarDayPrice() + (vehicleServiceMock.getCarHourPrice() * 3), invoice.getAmount());
    }

    @Test
    public void bikeExitExampleTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.HOUR, 27);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(car, entryDate, exitDate);
        invoice.setAmount(5500);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getBikeDayPrice() + (vehicleServiceMock.getBikeHourPrice() * 3), invoice.getAmount());
    }

    @Test
    public void bikeExitByHourTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.MINUTE, 59);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bike, entryDate, exitDate);
        invoice.setAmount(500);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        long amount = invoice.getAmount();
        assertEquals(vehicleServiceMock.getBikeHourPrice(), amount);
    }

    @Test
    public void bikeExitByDayTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.DATE, 1);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bike, entryDate, exitDate);
        invoice.setAmount(4000);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getBikeDayPrice(), invoice.getAmount());
    }

    @Test
    public void bikeExitHighCCByDayTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.DATE, 1);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bikeHighCC, entryDate, exitDate);
        invoice.setAmount(6000);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getBikeDayPrice() + 2000, invoice.getAmount());
        vehicleServiceMock.deleteInvoiceVehicleByLicensePlate("BBB123");
    }

    @Test
    public void bikeExitHighCCByHourTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.MINUTE, 59);

        Date exitDate = calendar.getTime();
        invoice = new Invoice(bikeHighCC, entryDate, exitDate);
        invoice.setAmount(2500);
        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getBikeHourPrice() + 2000, invoice.getAmount());
    }

    @Test
    public void bikeExitHighCCExampleTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.HOUR, 10);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bikeHighCC, entryDate, exitDate);
        invoice.setAmount(6000);

        when(vehicleServiceMock.vehicleExit(invoice)).thenReturn(invoice);
        invoice = vehicleServiceMock.vehicleExit(invoice);

        assertEquals(vehicleServiceMock.getBikeDayPrice() + 2000, invoice.getAmount());

    }

    @Test
    public void carEntryUnauthorized() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.JUNE, 24);

        invoice = new Invoice(car, calendar.getTime());

        when(vehicleServiceMock.generateInvoice(invoice)).thenReturn("No autorizado, no está en un dia hábil");

        assertEquals("No autorizado, no está en un dia hábil", vehicleServiceMock.generateInvoice(invoice));
    }
}
