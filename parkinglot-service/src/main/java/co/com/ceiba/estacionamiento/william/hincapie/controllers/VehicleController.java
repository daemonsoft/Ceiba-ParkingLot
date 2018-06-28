package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.william.hincapie.entities.Bike;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Car;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@GetMapping
	public ResponseEntity<List<Vehicle>> getAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Car("CAR123"));
		vehicles.add(new Bike("CAR123", 125));
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicles, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Vehicle> vehicleEntry(@RequestBody Vehicle vehicle) {
		System.err.println(vehicle.toString());
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
	}

	@GetMapping("{licensePlate}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate) {
		Vehicle vehicle = new Vehicle(licensePlate);
		vehicle.add(linkTo(methodOn(VehicleController.class).getVehicle(licensePlate)).withSelfRel());
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
	}
}
