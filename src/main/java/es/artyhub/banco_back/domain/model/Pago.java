package es.artyhub.banco_back.domain.model;

public class Pago {
    private Cliente autorization;
    private Cuenta destino;
    private MovimientoBancario pago;

    public Pago() {
    }

    public Pago(Cliente autorization, Cuenta destino, MovimientoBancario pago) {
        this.autorization = autorization;
        this.destino = destino;
        this.pago = pago;
    }

    public Cliente getAutorization() {
        return autorization;
    }

    public void setAutorization(Cliente autorization) {
        this.autorization = autorization;
    }

    public Cuenta getDestino() {
        return destino;
    }

    public void setDestino(Cuenta destino) {
        this.destino = destino;
    }

    public MovimientoBancario getPago() {
        return pago;
    }

    public void setPago(MovimientoBancario pago) {
        this.pago = pago;
    }
}
