package co.com.ceiba.estacionamiento.william.hincapie.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.william.hincapie.entities.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

	public Vehicle findByLicensePlate(String licensePlate);

	public List<Vehicle> findAllVehicles();

}