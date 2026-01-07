package es.artyhub.banco_back.domain.model;

public class PagoTarjeta extends Pago {
    private TarjetaCredito origen;

    public PagoTarjeta() {
    }

    public PagoTarjeta(Cliente autorizacion, TarjetaCredito origen, Cuenta destino, MovimientoBancario pago) {
        super(autorizacion, destino, pago);
        this.origen = origen;
    }

    public TarjetaCredito getOrigen() {
        return origen;
    }

    public void setOrigen(TarjetaCredito origen) {
        this.origen = origen;
    }
}
