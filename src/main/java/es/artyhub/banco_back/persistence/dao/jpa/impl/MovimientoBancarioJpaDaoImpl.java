package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MovimientoBancarioJpaDaoImpl implements MovimientoBancarioJpaDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public MovimientoBancarioJpaEntity findById(Long id) {
        return entityManager.find(MovimientoBancarioJpaEntity.class, id);
    }

    @Override
    public List<MovimientoBancarioJpaEntity> findAll() {
        String sql = "SELECT movimiento FROM MovimientoBancarioJpaEntity movimiento ORDER BY movimiento.id ASC";

        TypedQuery<MovimientoBancarioJpaEntity> movimientoBancarioJpaEntityTypedQuery =  entityManager
                .createQuery(sql, MovimientoBancarioJpaEntity.class);
        return movimientoBancarioJpaEntityTypedQuery.getResultList();
    }
}
