package co.com.ceiba.estacionamiento.william.hincapie.domain;

public enum VehicleType {

    CAR(0), BIKE(1);

    private int value;

    VehicleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
