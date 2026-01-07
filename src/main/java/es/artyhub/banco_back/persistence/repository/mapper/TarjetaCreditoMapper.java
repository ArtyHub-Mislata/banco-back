package es.artyhub.banco_back.persistence.repository.mapper;

import es.artyhub.banco_back.domain.dto.TarjetaCreditoDto;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

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

    public TarjetaCreditoDto fromTarjetaCreditoJpaEntityToTarjetaCreditoDto(TarjetaCreditoJpaEntity tarjetaCredito) {
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

    public TarjetaCreditoJpaEntity fromTarjetaCreditoDtoToTarjetaCreditoJpaEntity(TarjetaCreditoDto tarjetaCreditoDto) {
        if (tarjetaCreditoDto == null) {
            return null;
        }
        return new TarjetaCreditoJpaEntity(
            tarjetaCreditoDto.getId(), 
            tarjetaCreditoDto.getNumeroTarjeta(), 
            tarjetaCreditoDto.getFechaCaducidad(), 
            tarjetaCreditoDto.getCvv(), 
            tarjetaCreditoDto.getNombreCompleto());
    }
}
