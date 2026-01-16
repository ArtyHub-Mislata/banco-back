package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
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

    @Override
    public MovimientoBancarioJpaEntity findByImporte(Long importe) {
        String sql = "SELECT movimiento FROM MovimientoBancarioJpaEntity movimiento WHERE movimiento.importe = :importe";

        TypedQuery<MovimientoBancarioJpaEntity> movimientoBancarioJpaEntityTypedQuery = entityManager
                .createQuery(sql, MovimientoBancarioJpaEntity.class)
                .setParameter("importe", importe);
        return movimientoBancarioJpaEntityTypedQuery.getSingleResult();
    }

    @Override
    public MovimientoBancarioJpaEntity findByConcepto(String concepto) {
        String sql = "SELECT movimiento FROM MovimientoBancarioJpaEntity movimiento WHERE movimiento.concepto = :concepto";

        TypedQuery<MovimientoBancarioJpaEntity> movimientoBancarioJpaEntityTypedQuery = entityManager
                .createQuery(sql, MovimientoBancarioJpaEntity.class)
                .setParameter("concepto", concepto);
        return movimientoBancarioJpaEntityTypedQuery.getSingleResult();
    }

    @Override
    public List<MovimientoBancarioJpaEntity> findByCuentaId(Long cuenta_id) {
        String sql = "SELECT movimiento FROM MovimientoBancarioJpaEntity movimiento WHERE movimiento.cuenta_id = :cuenta_id";

        TypedQuery<MovimientoBancarioJpaEntity> movimientoBancarioJpaEntityTypedQuery = entityManager
                .createQuery(sql, MovimientoBancarioJpaEntity.class)
                .setParameter("cuenta_id", cuenta_id);
        return movimientoBancarioJpaEntityTypedQuery.getResultList();
    }

    @Override
    public MovimientoBancarioJpaEntity insert(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity) {

        entityManager.persist(movimientoBancarioJpaEntity);
        return movimientoBancarioJpaEntity;
    }

    @Override
    public MovimientoBancarioJpaEntity update(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity) {
        return entityManager.merge(movimientoBancarioJpaEntity);
    }
}
