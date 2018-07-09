package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import co.com.ceiba.estacionamiento.william.hincapie.data.VehicleRepository;
import co.com.ceiba.estacionamiento.william.hincapie.services.InvoiceService;
import co.com.ceiba.estacionamiento.william.hincapie.services.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
    }

    @Test
    public void vehicleEntry() {
    }

    @Test
    public void getVehicle() {
    }
}