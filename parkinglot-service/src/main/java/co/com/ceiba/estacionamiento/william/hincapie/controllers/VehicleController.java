package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
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
    public ResponseEntity<Vehicle> vehicleEntry(@RequestBody Vehicle vehicle) {

        String message = vehicleService.generateInvoice(new Invoice(vehicle, new Date()));

        HttpHeaders responseHeaders = new HttpHeaders();
        if ("Vehiculo ingresado".equals(message)) {
            return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{licensePlate}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate) {
        Vehicle vehicle = vehicleService.getVehicle(licensePlate);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
    }
}