package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CuentaJpaDaoImpl implements CuentaJpaDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public CuentaJpaEntity findById(Long id) {
        return entityManager.find(CuentaJpaEntity.class, id);
    }

    @Override
    public List<CuentaJpaEntity> findAll() {
        String sql = "SELECT cuenta FROM CuentaJpaEntity cuenta ORDER BY cuenta.id ASC";

        TypedQuery<CuentaJpaEntity> cuentaJpaEntityTypedQuery =  entityManager
                .createQuery(sql, CuentaJpaEntity.class);
        return cuentaJpaEntityTypedQuery.getResultList();
    }
}
