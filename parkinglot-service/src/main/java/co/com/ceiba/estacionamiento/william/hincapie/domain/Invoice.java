package co.com.ceiba.estacionamiento.william.hincapie.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Vehicle vehicle;
    private Date entryDate;
    private Date exitDate;
    private long amount;

    public Invoice(Vehicle vehicle, Date entryDate) {
        this.vehicle = vehicle;
        this.entryDate = entryDate;
    }

    public Invoice(Vehicle vehicle, Date entryDate, Date exitDate) {
        this.vehicle = vehicle;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
