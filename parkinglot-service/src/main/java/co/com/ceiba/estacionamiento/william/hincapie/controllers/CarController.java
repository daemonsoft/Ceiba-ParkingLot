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

import co.com.ceiba.estacionamiento.william.hincapie.domain.CarCashier;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Cashier;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Car;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

@RestController
@RequestMapping("/car")
public class CarController {

	Cashier cashier = new CarCashier(20, 1000, 8000);

	@GetMapping
	public ResponseEntity<List<Vehicle>> getAllCars() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Car("CAR123"));
		vehicles.add(new Car("CAR123"));
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicles, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Vehicle> carEntry(@RequestBody Car car) {

		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(car, responseHeaders, HttpStatus.OK);
	}

	@GetMapping("{licensePlate}")
	public ResponseEntity<Vehicle> getCar(@PathVariable String licensePlate) {
		Vehicle vehicle = new Vehicle(licensePlate);
		vehicle.add(linkTo(methodOn(CarController.class).getCar(licensePlate)).withSelfRel());
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(vehicle, responseHeaders, HttpStatus.OK);
	}
}
