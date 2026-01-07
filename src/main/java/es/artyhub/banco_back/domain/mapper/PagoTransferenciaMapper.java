package es.artyhub.banco_back.domain.mapper;

import es.artyhub.banco_back.domain.dto.PagoTransferenciaDto;
import es.artyhub.banco_back.domain.model.PagoTransferencia;

public class PagoTransferenciaMapper {
    private static PagoTransferenciaMapper instance;

    public PagoTransferenciaMapper() {
    }

    public static PagoTransferenciaMapper getInstance() {
        if (instance == null) {
            instance = new PagoTransferenciaMapper();
        }
        return instance;
    }

    public PagoTransferenciaDto fromPagoTransferenciaToPagoTransferenciaDto(PagoTransferencia pagoTransferencia) {
        if (pagoTransferencia == null) {
            return null;
        }
        return new PagoTransferenciaDto(
            ClienteMapper.getInstance().fromClienteToClienteDto(pagoTransferencia.getAutorization()),
            CuentaMapper.getInstance().fromCuentaToCuentaDto(pagoTransferencia.getOrigen()),
            CuentaMapper.getInstance().fromCuentaToCuentaDto(pagoTransferencia.getDestino()),
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioDto(pagoTransferencia.getPago()));
    }

    public PagoTransferencia fromPagoTransferenciaDtoToPagoTransferencia(PagoTransferenciaDto pagoTransferenciaDto) {
        if (pagoTransferenciaDto == null) {
            return null;
        }
        return new PagoTransferencia(
            ClienteMapper.getInstance().fromClienteDtoToCliente(pagoTransferenciaDto.getAutorization()), 
            CuentaMapper.getInstance().fromCuentaDtoToCuenta(pagoTransferenciaDto.getOrigen()), 
            CuentaMapper.getInstance().fromCuentaDtoToCuenta(pagoTransferenciaDto.getDestino()), 
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioDtoToMovimientoBancario(pagoTransferenciaDto.getPago()));
    }
}
