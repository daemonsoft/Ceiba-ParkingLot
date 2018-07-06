package co.com.ceiba.estacionamiento.william.hincapie.exceptions;

public class VehicleCapacityReachedException extends Exception  {
    public VehicleCapacityReachedException() {
        super("No hay cupos disponibles");
    }
}
