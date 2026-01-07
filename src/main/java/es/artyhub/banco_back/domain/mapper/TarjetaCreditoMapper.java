package es.artyhub.banco_back.domain.mapper;

import es.artyhub.banco_back.domain.dto.TarjetaCreditoDto;
import es.artyhub.banco_back.domain.model.TarjetaCredito;

public class TarjetaCreditoMapper {
    private static TarjetaCreditoMapper instance;

    public TarjetaCreditoMapper() {
    }

    public static TarjetaCreditoMapper getInstance() {
        if (instance == null) {
            instance = new TarjetaCreditoMapper();
        }
        return instance;
    }

    public TarjetaCreditoDto fromTarjetaCreditoToTarjetaCreditoDto(TarjetaCredito tarjetaCredito) {
        if (tarjetaCredito == null) {
            return null;
        }
        return new TarjetaCreditoDto(
            tarjetaCredito.getId(), 
            tarjetaCredito.getNumeroTarjeta(), 
            tarjetaCredito.getFechaCaducidad(), 
            tarjetaCredito.getCvv(), 
            tarjetaCredito.getNombreCompleto());
    }

    public TarjetaCredito fromTarjetaCreditoDtoToTarjetaCredito(TarjetaCreditoDto tarjetaCreditoDto) {
        if (tarjetaCreditoDto == null) {
            return null;
        }
        return new TarjetaCredito(
            tarjetaCreditoDto.getId(), 
            tarjetaCreditoDto.getNumeroTarjeta(), 
            tarjetaCreditoDto.getFechaCaducidad(), 
            tarjetaCreditoDto.getCvv(), 
            tarjetaCreditoDto.getNombreCompleto());
    }
}
