package es.artyhub.banco_back.domain.dto;

public class PagoDto {
    private ClienteDto autorization;
    private CuentaDto destino;
    private MovimientoBancarioDto pago;

    public PagoDto() {
    }

    public PagoDto(ClienteDto autorization, CuentaDto destino, MovimientoBancarioDto pago) {
        this.autorization = autorization;
        this.destino = destino;
        this.pago = pago;
    }

    public ClienteDto getAutorization() {
        return autorization;
    }

    public void setAutorization(ClienteDto autorization) {
        this.autorization = autorization;
    }

    public CuentaDto getDestino() {
        return destino;
    }

    public void setDestino(CuentaDto destino) {
        this.destino = destino;
    }

    public MovimientoBancarioDto getPago() {
        return pago;
    }

    public void setPago(MovimientoBancarioDto pago) {
        this.pago = pago;
    }
}
