package es.artyhub.banco_back.persistence.repository.mapper;

import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;

public class CuentaMapper {
    private static CuentaMapper instance;

    public CuentaMapper() {
    }

    public static CuentaMapper getInstance() {
        if (instance == null) {
            instance = new CuentaMapper();
        }
        return instance;
    }


}
