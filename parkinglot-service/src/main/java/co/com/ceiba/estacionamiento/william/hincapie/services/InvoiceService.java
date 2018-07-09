package co.com.ceiba.estacionamiento.william.hincapie.services;

import co.com.ceiba.estacionamiento.william.hincapie.data.InvoiceRepository;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import co.com.ceiba.estacionamiento.william.hincapie.exceptions.InvoiceDataErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    // public InvoiceService(InvoiceRepository invoiceRepository) {
//        this.invoiceRepository = invoiceRepository;
//    }

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

    public Invoice saveInvoice(Invoice invoice) throws InvoiceDataErrorException {
        if (null == invoice || null == invoice.getVehicle()) {
            throw new InvoiceDataErrorException();
        }
        return invoiceRepository.save(invoice);
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