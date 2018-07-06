package co.com.ceiba.estacionamiento.william.hincapie.exceptions;

public class VehicleNotAuthorizedException extends Exception {

    public VehicleNotAuthorizedException() {
        super("No autorizado, no está en un dia hábil");
    }
}
