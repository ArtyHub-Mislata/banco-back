package es.artyhub.banco_back.domain.dto;

public class PagoTarjetaDto extends PagoDto{
    private TarjetaCreditoDto origen;

    public PagoTarjetaDto() {
    }

    public PagoTarjetaDto(ClienteDto autorization, TarjetaCreditoDto origen, CuentaDto destino, MovimientoBancarioDto pago) {
        super(autorization, destino, pago);
        this.origen = origen;
    }

    public TarjetaCreditoDto getOrigen() {
        return origen;
    }

    public void setOrigen(TarjetaCreditoDto origen) {
        this.origen = origen;
    }
}
