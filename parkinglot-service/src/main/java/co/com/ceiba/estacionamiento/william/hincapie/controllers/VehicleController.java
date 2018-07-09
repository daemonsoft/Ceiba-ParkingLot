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
    public ResponseEntity getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(vehicles, responseHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity vehicleEntry(@RequestBody Vehicle vehicle) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            vehicleService.generateInvoice(new Invoice(vehicle, new Date()));
        } catch (VehicleEntryException | VehicleNotAuthorizedException | VehicleCapacityReachedException | InvoiceDataErrorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("{licensePlate}")
    public ResponseEntity getVehicle(@PathVariable String licensePlate) {
        Vehicle vehicle = null;
        try {
            vehicle = vehicleService.getVehicle(licensePlate);
        } catch (VehicleLicensePlateInvalidException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/exit/{licensePlate}")
    public ResponseEntity vehicleExit(@PathVariable String licensePlate) {
        Invoice invoice;
        try {
            invoice = vehicleService.getVehicleInvoice(licensePlate);
        } catch (VehicleLicensePlateInvalidException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        try {
            invoice = vehicleService.vehicleExit(invoice);
        } catch (InvoiceDataErrorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(invoice, responseHeaders, HttpStatus.OK);
    }
}
