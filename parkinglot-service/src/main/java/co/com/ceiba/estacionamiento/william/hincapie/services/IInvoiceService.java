package co.com.ceiba.estacionamiento.william.hincapie.services;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.InvoiceDataErrorException;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.VehicleLicensePlateInvalidException;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.VehicleValidationException;

import java.util.List;

public interface IInvoiceService {

    List<Invoice> getAllCurrentInvoices();

    List<Invoice> getAllInvoices();

    Invoice saveInvoice(Invoice invoice) throws InvoiceDataErrorException;

    Invoice getInvoiceByVehicle(Vehicle vehicle) throws VehicleValidationException, VehicleLicensePlateInvalidException;
}
