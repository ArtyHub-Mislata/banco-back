package es.artyhub.banco_back.persistence.repository.mapper;

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


}
