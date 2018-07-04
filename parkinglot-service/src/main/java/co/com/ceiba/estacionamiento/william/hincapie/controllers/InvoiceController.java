package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.services.InvoiceService;
import co.com.ceiba.estacionamiento.william.hincapie.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();

        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(invoiceList, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("vehicle/{licensePlate}")
    public ResponseEntity<Invoice> vehicleExit(@PathVariable String licensePlate) {
        Invoice invoice = vehicleService.getVehicleInvoice(licensePlate);

        if (null == invoice) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        invoice = vehicleService.vehicleExit(invoice);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(invoice, responseHeaders, HttpStatus.OK);
    }
}
