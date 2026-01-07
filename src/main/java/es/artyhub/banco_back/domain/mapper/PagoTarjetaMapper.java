package es.artyhub.banco_back.domain.mapper;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.model.PagoTarjeta;

public class PagoTarjetaMapper {
    private static PagoTarjetaMapper instance;

    public PagoTarjetaMapper() {
    }

    public static PagoTarjetaMapper getInstance() {
        if (instance == null) {
            instance = new PagoTarjetaMapper();
        }
        return instance;
    }

    public PagoTarjetaDto fromPagoTarjetaToPagoTarjetaDto(PagoTarjeta pagoTarjeta) {
        if (pagoTarjeta == null) {
            return null;
        }
        return new PagoTarjetaDto(
            ClienteMapper.getInstance().fromClienteToClienteDto(pagoTarjeta.getAutorization()),
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoToTarjetaCreditoDto(pagoTarjeta.getOrigen()),
            CuentaMapper.getInstance().fromCuentaToCuentaDto(pagoTarjeta.getDestino()),
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioDto(pagoTarjeta.getPago()));
    }

    public PagoTarjeta fromPagoTarjetaDtoToPagoTarjeta(PagoTarjetaDto pagoTarjetaDto) {
        if (pagoTarjetaDto == null) {
            return null;
        }
        return new PagoTarjeta(
            ClienteMapper.getInstance().fromClienteDtoToCliente(pagoTarjetaDto.getAutorization()), 
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoDtoToTarjetaCredito(pagoTarjetaDto.getOrigen()), 
            CuentaMapper.getInstance().fromCuentaDtoToCuenta(pagoTarjetaDto.getDestino()), 
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioDtoToMovimientoBancario(pagoTarjetaDto.getPago()));
    }
}
