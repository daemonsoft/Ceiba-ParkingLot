package co.com.ceiba.estacionamiento.william.hincapie.data;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.domain.VehicleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findAllByType(VehicleType type);

    Vehicle findByLicensePlate(String licensePlate);
}