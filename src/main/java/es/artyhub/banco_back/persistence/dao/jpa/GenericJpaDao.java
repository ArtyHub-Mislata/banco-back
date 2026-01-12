package es.artyhub.banco_back.persistence.dao.jpa;

import java.util.List;

public interface GenericJpaDao<T> {
    T findById(Long id);
    List<T> findAll();
}
