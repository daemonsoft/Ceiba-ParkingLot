package co.com.ceiba.estacionamiento.william.hincapie.services;

import co.com.ceiba.estacionamiento.william.hincapie.data.InvoiceRepository;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllCurrentInvoices() {
        return invoiceRepository.findAllByExitDate(null);
    }

    public List<Invoice> getAllInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();
        for (Invoice invoice : invoiceRepository.findAll()) {
            invoiceList.add(invoice);
        }
        return invoiceList;
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public Invoice getInvoiceByVehicle(Vehicle vehicle) {
        for (Invoice invoice : getAllCurrentInvoices()) {
            if (vehicle.equals(invoice.getVehicle())) {
                return invoice;
            }
        }
        return null;
    }
}