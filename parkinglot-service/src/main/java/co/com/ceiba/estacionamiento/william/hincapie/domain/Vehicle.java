package co.com.ceiba.estacionamiento.william.hincapie.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String licensePlate;
    private VehicleType type;
    private int engineCapacity;

    public Vehicle(Integer id, String licensePlate, VehicleType type, int engineCapacity) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.type = type;
        this.engineCapacity = engineCapacity;
    }

    public Vehicle() {
    }

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public Vehicle(String licensePlate, VehicleType type, int engineCapacity) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.engineCapacity = engineCapacity;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(licensePlate, vehicle.getLicensePlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }
}

