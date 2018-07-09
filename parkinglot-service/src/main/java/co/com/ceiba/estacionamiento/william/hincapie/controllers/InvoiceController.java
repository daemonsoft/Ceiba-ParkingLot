package co.com.ceiba.estacionamiento.william.hincapie.controllers;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.services.IInvoiceService;
import co.com.ceiba.estacionamiento.william.hincapie.services.IVehicleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/invoice")
public class InvoiceController {

    private final IInvoiceService invoiceService;

    private final IVehicleService vehicleService;

    public InvoiceController(IInvoiceService invoiceService, IVehicleService vehicleService) {
        this.invoiceService = invoiceService;
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getOpenInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllCurrentInvoices();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(invoiceList, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("history")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllInvoices();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(invoiceList, responseHeaders, HttpStatus.OK);
    }
}
