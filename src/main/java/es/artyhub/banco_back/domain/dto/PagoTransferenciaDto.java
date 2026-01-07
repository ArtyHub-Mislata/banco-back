package es.artyhub.banco_back.domain.dto;

public class PagoTransferenciaDto extends PagoDto {
    private CuentaDto origen;

    public PagoTransferenciaDto() {
    }

    public PagoTransferenciaDto(ClienteDto autorization, CuentaDto origen, CuentaDto destino, MovimientoBancarioDto pago) {
        super(autorization, destino, pago);
        this.origen = origen;
    }

    public CuentaDto getOrigen() {
        return origen;
    }

    public void setOrigen(CuentaDto origen) {
        this.origen = origen;
    }
}
