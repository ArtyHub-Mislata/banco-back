package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TarjetaCreditoJpaDaoImpl implements TarjetaCreditoJpaDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public TarjetaCreditoJpaEntity findById(Long id) {
        return entityManager.find(TarjetaCreditoJpaEntity.class, id);
    }

    @Override
    public List<TarjetaCreditoJpaEntity> findAll() {
        String sql = "SELECT tarjeta FROM TarjetaCreditoJpaEntity tarjeta ORDER BY tarjeta.id ASC";

        TypedQuery<TarjetaCreditoJpaEntity> tarjetaCreditoJpaEntityTypedQuery =  entityManager
                .createQuery(sql, TarjetaCreditoJpaEntity.class);
        return tarjetaCreditoJpaEntityTypedQuery.getResultList();
    }
}
