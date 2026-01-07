package es.artyhub.banco_back.domain.model;

public class PagoTransferencia extends Pago {
    private Cuenta origen;

    public PagoTransferencia() {
    }

    public PagoTransferencia(Cliente autorization, Cuenta origen, Cuenta destino, MovimientoBancario pago) {
        super(autorization, destino, pago);
        this.origen = origen;
    }

    public Cuenta getOrigen() {
        return origen;
    }

    public void setOrigen(Cuenta origen) {
        this.origen = origen;
    }
}
