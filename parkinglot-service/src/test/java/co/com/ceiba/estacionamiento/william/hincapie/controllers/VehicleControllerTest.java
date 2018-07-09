package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import co.com.ceiba.estacionamiento.william.hincapie.data.VehicleRepository;
import co.com.ceiba.estacionamiento.william.hincapie.services.InvoiceService;
import co.com.ceiba.estacionamiento.william.hincapie.services.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class VehicleControllerTest {

    private VehicleController vehicleController;

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private InvoiceService invoiceService;


    @Test
    public void getAllVehicles() {
        System.out.println(vehicleController);
        vehicleController.getAllVehicles();
    }

    @Test
    public void vehicleEntry() {
    }

    @Test
    public void getVehicle() {
    }
}