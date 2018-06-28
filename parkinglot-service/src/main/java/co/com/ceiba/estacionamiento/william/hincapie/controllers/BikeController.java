package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.util.JSON;

import co.com.ceiba.estacionamiento.william.hincapie.domain.CarCashier;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Cashier;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Bike;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Car;
import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

@RestController
@RequestMapping("/bike")
public class BikeController {

	Cashier cashier = new CarCashier(20, 1000, 8000);
	
	@GetMapping
	public ResponseEntity<List<Vehicle>> getAllCars() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Car("CAR123"));
		vehicles.add(new Bike("CAR123", 125));
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
