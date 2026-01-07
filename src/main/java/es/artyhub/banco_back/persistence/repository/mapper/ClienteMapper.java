package es.artyhub.banco_back.persistence.repository.mapper;


import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;

public class ClienteMapper {
    private static ClienteMapper instance;

    public ClienteMapper() {
    }

    public static ClienteMapper getInstance() {
        if (instance == null) {
            instance = new ClienteMapper();
        }
        return instance;
    }


}
