package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.VehicleCapacityReachedException;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.VehicleEntryException;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.VehicleNotAuthorizedException;
import co.com.ceiba.estacionamiento.william.hincapie.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(vehicles, responseHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vehicle> vehicleEntry(@RequestBody Vehicle vehicle) throws VehicleEntryException, VehicleNotAuthorizedException, VehicleCapacityReachedException {
        HttpHeaders responseHeaders = new HttpHeaders();
        vehicleService.generateInvoice(new Invoice(vehicle, new Date()));
        return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("{licensePlate}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate) {
        Vehicle vehicle = vehicleService.getVehicle(licensePlate);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
    }
}
