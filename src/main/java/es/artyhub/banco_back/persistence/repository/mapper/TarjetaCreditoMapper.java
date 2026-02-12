package es.artyhub.banco_back.persistence.repository.mapper;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
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

    //Esto seria para crear tarjetas de crédito
    public TarjetaCreditoJpaEntity fromTarjetaCreditoToTarjetaCreditoJpaEntity(TarjetaCredito tarjetaCredito) {
        if (tarjetaCredito == null) {
            return null;
        }
        return new TarjetaCreditoJpaEntity(
            tarjetaCredito.getId(),
            tarjetaCredito.getNumeroTarjeta(),
            tarjetaCredito.getFechaCaducidad(),
            tarjetaCredito.getCvv(),
            tarjetaCredito.getNombreCompleto()
        );
    }

    public TarjetaCredito fromTarjetaCreditoJpaEntityToTarjetaCredito(TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity) {
        if (tarjetaCreditoJpaEntity == null) {
            return null;
        }
        return new TarjetaCredito(
            tarjetaCreditoJpaEntity.getId(),
            tarjetaCreditoJpaEntity.getNumeroTarjeta(),
            tarjetaCreditoJpaEntity.getFechaCaducidad(),
            tarjetaCreditoJpaEntity.getCvv(),
            tarjetaCreditoJpaEntity.getNombreCompleto()
        );
    }


}
