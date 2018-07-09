package co.com.ceiba.estacionamiento.william.hincapie.services;

import co.com.ceiba.estacionamiento.william.hincapie.data.VehicleRepository;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.domain.VehicleType;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private final InvoiceService invoiceService;

    private int hoursToDay;
    private int bikeCapacity;
    private int carCapacity;
    private int bikeHourPrice;
    private int bikeDayPrice;
    private int carHourPrice;
    private int carDayPrice;

    public VehicleService(VehicleRepository vehicleRepository, InvoiceService invoiceService) {
        this.hoursToDay = 9;
        this.bikeCapacity = 10;
        this.carCapacity = 20;
        this.bikeHourPrice = 500;
        this.bikeDayPrice = 4000;
        this.carHourPrice = 1000;
        this.carDayPrice = 8000;
        this.vehicleRepository = vehicleRepository;
        this.invoiceService = invoiceService;
    }

    public int getBikeCapacity() {
        return bikeCapacity;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public int getBikeHourPrice() {
        return bikeHourPrice;
    }

    public int getBikeDayPrice() {
        return bikeDayPrice;
    }

    public int getCarHourPrice() {
        return carHourPrice;
    }

    public int getCarDayPrice() {
        return carDayPrice;
    }

    public int currentBikes() {
        int counter = 0;

        for (Invoice invoice : invoiceService.getAllCurrentInvoices()) {
            if (invoice.getVehicle().getType().equals(VehicleType.BIKE)) {
                counter = counter + 1;
            }
        }
        return counter;
    }

    private int currentCars() {

        int counter = 0;

        for (Invoice invoice : invoiceService.getAllCurrentInvoices()) {
            if (invoice.getVehicle().getType().equals(VehicleType.CAR)) {
                counter = counter + 1;
            }
        }
        return counter;
    }

    public boolean isNotValidDay(Invoice invoice) throws InvoiceDataErrorException {
        if (null == invoice || null == invoice.getVehicle()) {
            throw new InvoiceDataErrorException();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(invoice.getEntryDate());

        return (invoice.getVehicle().getLicensePlate().toUpperCase().startsWith("A")
                && (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY));
    }

    public void generateInvoice(Invoice invoice) throws VehicleEntryException, VehicleNotAuthorizedException, VehicleCapacityReachedException, InvoiceDataErrorException {

        if (null == invoice || null == invoice.getVehicle()) {
            throw new InvoiceDataErrorException();
        }

        if (isNotValidDay(invoice)) {
            throw new VehicleNotAuthorizedException();
        }
        Vehicle vehicle = vehicleRepository.findByLicensePlate(invoice.getVehicle().getLicensePlate());

        if (null != vehicle) {
            invoice.getVehicle().setId(vehicle.getId());

            for (Invoice i : invoiceService.getAllCurrentInvoices()) {
                if (vehicle.getLicensePlate().equals(i.getVehicle().getLicensePlate())) {
                    return;
                }
            }
        } else {
            invoice.setVehicle(vehicleRepository.save(invoice.getVehicle()));
        }

        if (VehicleType.CAR.equals(invoice.getVehicle().getType())) {
            if (this.getCarCapacity() == this.currentCars()) {
                throw new VehicleCapacityReachedException();
            }
            invoiceService.saveInvoice(invoice);
            return;
        } else if (VehicleType.BIKE.equals(invoice.getVehicle().getType())) {
            if (this.getBikeCapacity() == this.currentBikes()) {
                throw new VehicleCapacityReachedException();
            }
            invoiceService.saveInvoice(invoice);
            return;
        }
        throw new VehicleEntryException();
    }

    public Vehicle getVehicle(String licensePlate) throws VehicleLicensePlateInvalidException {
        if (null == licensePlate || 3 > licensePlate.length()) {
            throw new VehicleLicensePlateInvalidException();
        }

        return vehicleRepository.findByLicensePlate(licensePlate);
    }

    public Invoice getVehicleInvoice(String licensePlate) throws VehicleLicensePlateInvalidException{
        if (null == licensePlate || 3 > licensePlate.length()) {
            throw new VehicleLicensePlateInvalidException();
        }

        return invoiceService.getInvoiceByVehicle(vehicleRepository.findByLicensePlate(licensePlate));
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (Vehicle item : vehicleRepository.findAll()) {
            vehicleList.add(item);
        }
        return vehicleList;
    }

    public Invoice vehicleExit(Invoice invoice) throws InvoiceDataErrorException {
        if (null == invoice || null == invoice.getVehicle()) {
            throw new InvoiceDataErrorException();
        }

        calculateAmount(invoice);

        Vehicle vehicle = vehicleRepository.findByLicensePlate(invoice.getVehicle().getLicensePlate());

        if (null != vehicle) {
            invoice.getVehicle().setId(vehicle.getId());
        } else {
            invoice.setVehicle(vehicleRepository.save(invoice.getVehicle()));
        }

        invoiceService.saveInvoice(invoice);
        return invoice;
    }

    private void calculateAmount(Invoice invoice) throws InvoiceDataErrorException {
        if (null == invoice || null == invoice.getVehicle()) {
            throw new InvoiceDataErrorException();
        }

        if (null == invoice.getExitDate()) {
            invoice.setExitDate(new Date());
        }

        long amount;
        long difference = invoice.getExitDate().getTime() - invoice.getEntryDate().getTime();

        long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
        long hours = (long) Math.ceil((float) minutes / 60);
        long days = (long) (float) minutes / 1440;
        int hourPrice;
        int dayPrice;

        if (VehicleType.CAR.equals(invoice.getVehicle().getType())) {
            hourPrice = carHourPrice;
            dayPrice = carDayPrice;
        } else {
            hourPrice = bikeHourPrice;
            dayPrice = bikeDayPrice;
        }
        if ((hours + 1) < hoursToDay) {
            amount = hourPrice * hours;
        } else {
            if (minutes<60) {
                amount = dayPrice;
            }else{
                amount = dayPrice;
                if (hours > 24) {
                    amount = dayPrice * days;
                    for (int i = 0; i < days; i++) {
                        hours = hours - 24;
                    }
                    if ((hours + 1) < hoursToDay) {
                        amount = amount + hourPrice * hours;
                    } else {
                        amount = amount + dayPrice;
                    }
                }
            }
        }

        if (invoice.getVehicle().getEngineCapacity() > 500) {
            amount = amount + 2000;
        }

        invoice.setAmount(amount);
    }
}
