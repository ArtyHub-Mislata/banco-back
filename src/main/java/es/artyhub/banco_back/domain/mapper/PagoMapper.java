package es.artyhub.banco_back.domain.mapper;

import es.artyhub.banco_back.domain.dto.PagoDto;
import es.artyhub.banco_back.domain.model.Pago;

public class PagoMapper {
    private static PagoMapper instance;

    public PagoMapper() {
    }

    public static PagoMapper getInstance() {
        if (instance == null) {
            instance = new PagoMapper();
        }
        return instance;
    }

    public PagoDto fromPagoToPagoDto(Pago pago) {
        if (pago == null) {
            return null;
        }
        return new PagoDto(
            ClienteMapper.getInstance().fromClienteToClienteDto(pago.getAutorization()),
            CuentaMapper.getInstance().fromCuentaToCuentaDto(pago.getDestino()),
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioDto(pago.getPago()));
    }

    public Pago fromPagoDtoToPago(PagoDto pagoDto) {
        if (pagoDto == null) {
            return null;
        }
        return new Pago(
            ClienteMapper.getInstance().fromClienteDtoToCliente(pagoDto.getAutorization()), 
            CuentaMapper.getInstance().fromCuentaDtoToCuenta(pagoDto.getDestino()), 
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioDtoToMovimientoBancario(pagoDto.getPago()));
    }
}
