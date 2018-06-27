package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	@GetMapping
	public String getAllVehicles() {
		return "AllVehicles";
	}

	@PostMapping("{licensePlate}")
	public String vehicleEntry(@PathVariable String licensePlate) {
		return "Hello  " + licensePlate;
	}

	@GetMapping("{licensePlate}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate) {
		Vehicle vehicle = new Vehicle(licensePlate);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
	}
}
