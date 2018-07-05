package co.com.ceiba.estacionamiento.william.hincapie;

import co.com.ceiba.estacionamiento.william.hincapie.data.VehicleRepository;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.domain.VehicleType;
import co.com.ceiba.estacionamiento.william.hincapie.services.InvoiceService;
import co.com.ceiba.estacionamiento.william.hincapie.services.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VehicleServiceTests {

    private VehicleService vehicleService;

    @Mock
    private InvoiceService invoiceService;

    @Mock
    private VehicleRepository vehicleRepository;

    private Vehicle car;
    private Vehicle bike;
    private Vehicle bikeHighCC;
    private Invoice invoice;

    public VehicleServiceTests() {
        this.car = new Vehicle("AAA123", VehicleType.CAR);
        this.bike = new Vehicle("BBB123", VehicleType.BIKE, 125);
        this.bikeHighCC = new Vehicle("BBB123", VehicleType.BIKE, 650);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vehicleService = new VehicleService(vehicleRepository, invoiceService);
        when(vehicleRepository.findByLicensePlate(bike.getLicensePlate())).thenReturn(bike);
        when(vehicleRepository.findByLicensePlate(bikeHighCC.getLicensePlate())).thenReturn(bikeHighCC);
        when(vehicleRepository.findByLicensePlate(car.getLicensePlate())).thenReturn(car);

        when(invoiceService.getInvoiceByVehicle(car)).thenReturn(invoice);
        when(invoiceService.getInvoiceByVehicle(bike)).thenReturn(invoice);
        when(invoiceService.getInvoiceByVehicle(bikeHighCC)).thenReturn(invoice);
    }

    @Test
    public void carEntryTest() {
        invoice = new Invoice(car, new Date());
        assertEquals("Vehiculo ingresado", vehicleService.generateInvoice(invoice));
    }

    @Test
    public void bikeEntryTest() {
        invoice = new Invoice(bike, new Date());
        assertEquals("Vehiculo ingresado", vehicleService.generateInvoice(invoice));
    }

    @Test
    public void bikeHighCCEntryTest() {
        invoice = new Invoice(bikeHighCC, new Date());
        assertEquals("Vehiculo ingresado", vehicleService.generateInvoice(invoice));
    }

    @Test
    public void carEntryMaxCapacityTest() {
        List<Invoice> invoiceList = new ArrayList<>();
        for (int i = 0; i < vehicleService.getCarCapacity(); i++) {
            invoice = new Invoice(new Vehicle("AAK123", VehicleType.CAR), new Date());
            invoiceList.add(invoice);
        }
        when(invoiceService.getAllCurrentInvoices()).thenReturn(invoiceList);

        invoice = new Invoice(car, new Date());

        assertEquals("No hay cupos disponibles", vehicleService.generateInvoice(invoice));
    }

    @Test
    public void bikeEntryMaxCapacityTest() {
        List<Invoice> invoiceList = new ArrayList<>();
        for (int i = 0; i < vehicleService.getBikeCapacity(); i++) {
            invoice = new Invoice(new Vehicle("BBK123", VehicleType.BIKE, 125), new Date());
            invoiceList.add(invoice);
        }
        when(invoiceService.getAllCurrentInvoices()).thenReturn(invoiceList);

        invoice = new Invoice(bike, new Date());

        assertEquals("No hay cupos disponibles", vehicleService.generateInvoice(invoice));
    }

    @Test
    public void carExitByHourTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.MINUTE, 59);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(car, entryDate, exitDate);

        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getCarHourPrice(), invoice.getAmount());
    }

    @Test
    public void carExitByDayTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.DATE, 1);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(car, entryDate, exitDate);
        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getCarDayPrice(), invoice.getAmount());
    }

    @Test
    public void carExitExampleTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.HOUR, 27);

        Date exitDate = calendar.getTime();
        invoice = new Invoice(car, entryDate, exitDate);

        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getCarDayPrice() + (vehicleService.getCarHourPrice() * 3), invoice.getAmount());
    }

    @Test
    public void bikeExitExampleTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.HOUR, 27);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bike, entryDate, exitDate);
        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getBikeDayPrice() + (vehicleService.getBikeHourPrice() * 3), invoice.getAmount());
    }

    @Test
    public void bikeExitByHourTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.MINUTE, 59);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bike, entryDate, exitDate);
        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getBikeHourPrice(), invoice.getAmount());
    }

    @Test
    public void bikeExitByDayTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.DATE, 1);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bike, entryDate, exitDate);
        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getBikeDayPrice(), invoice.getAmount());
    }

    @Test
    public void bikeExitHighCCByDayTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.DATE, 1);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bikeHighCC, entryDate, exitDate);
        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getBikeDayPrice() + 2000, invoice.getAmount());
    }

    @Test
    public void bikeExitHighCCByHourTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.MINUTE, 59);

        Date exitDate = calendar.getTime();
        invoice = new Invoice(bikeHighCC, entryDate, exitDate);
        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getBikeHourPrice() + 2000, invoice.getAmount());
    }

    @Test
    public void bikeExitHighCCExampleTest() {
        Calendar calendar = Calendar.getInstance();
        Date entryDate = calendar.getTime();

        calendar.setTime(entryDate);
        calendar.add(Calendar.HOUR, 10);

        Date exitDate = calendar.getTime();

        invoice = new Invoice(bikeHighCC, entryDate, exitDate);
        invoice = vehicleService.vehicleExit(invoice);

        assertEquals(vehicleService.getBikeDayPrice() + 2000, invoice.getAmount());

    }

    @Test
    public void carEntryUnauthorized() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.JUNE, 24);

        invoice = new Invoice(car, calendar.getTime());

        assertEquals("No autorizado, no está en un dia hábil", vehicleService.generateInvoice(invoice));
    }
}
