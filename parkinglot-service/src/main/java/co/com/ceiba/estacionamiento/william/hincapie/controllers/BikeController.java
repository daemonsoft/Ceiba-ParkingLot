package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.william.hincapie.domain.BikeCashier;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Cashier;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Bike;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

@RestController
@RequestMapping("/bike")
public class BikeController {

	Cashier cashier = new BikeCashier(20, 1000, 8000);
	
	@GetMapping
	public ResponseEntity<List<Vehicle>> getAllCars() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Bike("BIK123", 650));
		vehicles.add(new Bike("BIK123", 125));
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicles, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Vehicle> bikeEntry(@RequestBody Bike bike) {

		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(bike, responseHeaders, HttpStatus.OK);
	}

	@GetMapping("{licensePlate}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate) {
		Vehicle vehicle = new Vehicle(licensePlate);
		vehicle.add(linkTo(methodOn(BikeController.class).getVehicle(licensePlate)).withSelfRel());
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
	}
}
