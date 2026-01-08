package es.artyhub.banco_back.persistence.repository.mapper;


import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;

public class MovimientoBancarioMapper {
    private static MovimientoBancarioMapper instance;

    public MovimientoBancarioMapper() {
    }

    public static MovimientoBancarioMapper getInstance() {
        if (instance == null) {
            instance = new MovimientoBancarioMapper();
        }
        return instance;
    }


}
