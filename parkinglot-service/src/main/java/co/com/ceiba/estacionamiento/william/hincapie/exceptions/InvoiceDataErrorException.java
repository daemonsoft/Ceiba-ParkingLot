package co.com.ceiba.estacionamiento.william.hincapie.exceptions;

public class InvoiceDataErrorException extends Exception {

    public InvoiceDataErrorException() {
        super("Los datos de la factura no son válidos");
    }
}
