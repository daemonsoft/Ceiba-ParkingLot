package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.*;
import co.com.ceiba.estacionamiento.william.hincapie.services.IVehicleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehicle")
public class VehicleController {

    private final IVehicleService vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(vehicles, responseHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vehicle> vehicleEntry(@RequestBody Vehicle vehicle) throws VehicleEntryException, VehicleNotAuthorizedException, VehicleCapacityReachedException, InvoiceDataErrorException {
        HttpHeaders responseHeaders = new HttpHeaders();
        vehicleService.generateInvoice(new Invoice(vehicle, new Date()));
        return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("{licensePlate}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate) throws VehicleLicensePlateInvalidException {
        Vehicle vehicle = vehicleService.getVehicle(licensePlate);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/exit/{licensePlate}")
    public ResponseEntity<Invoice> vehicleExit(@PathVariable String licensePlate) throws VehicleLicensePlateInvalidException, InvoiceDataErrorException {
        Invoice invoice = vehicleService.getVehicleInvoice(licensePlate);
        if (null == invoice) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        invoice = vehicleService.vehicleExit(invoice);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(invoice, responseHeaders, HttpStatus.OK);
    }
}
