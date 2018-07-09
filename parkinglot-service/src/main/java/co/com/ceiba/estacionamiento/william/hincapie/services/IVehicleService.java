package co.com.ceiba.estacionamiento.william.hincapie.services;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.*;

import java.util.List;

public interface IVehicleService {

    int getBikeCapacity();

    int getCarCapacity();

    int getBikeHourPrice();

    int getBikeDayPrice();

    int getCarHourPrice();

    int getCarDayPrice();

    int currentBikes();

    boolean isNotValidDay(Invoice invoice) throws InvoiceDataErrorException;

    void generateInvoice(Invoice invoice) throws VehicleEntryException, VehicleNotAuthorizedException, VehicleCapacityReachedException, InvoiceDataErrorException;

    Vehicle getVehicle(String licensePlate) throws VehicleLicensePlateInvalidException;

    Invoice getVehicleInvoice(String licensePlate) throws VehicleLicensePlateInvalidException;

    List<Vehicle> getAllVehicles();

    Invoice vehicleExit(Invoice invoice) throws InvoiceDataErrorException;
}
