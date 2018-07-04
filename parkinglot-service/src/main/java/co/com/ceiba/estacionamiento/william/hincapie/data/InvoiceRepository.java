package co.com.ceiba.estacionamiento.william.hincapie.data;

import co.com.ceiba.estacionamiento.william.hincapie.domain.Invoice;
import co.com.ceiba.estacionamiento.william.hincapie.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Invoice findByVehicle(Vehicle byLicensePlate);

    List<Invoice> findAllByExitDate(Date exitDate);
}