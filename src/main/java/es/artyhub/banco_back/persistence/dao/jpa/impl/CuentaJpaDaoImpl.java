package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
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

    @Override
    public CuentaJpaEntity findByIban(String iban) {
        String sql = "SELECT cuenta FROM CuentaJpaEntity cuenta WHERE cuenta.iban = :iban";

        TypedQuery<CuentaJpaEntity> cuentaJpaEntityTypedQuery = entityManager
                .createQuery(sql, CuentaJpaEntity.class)
                .setParameter("iban", iban);
        return cuentaJpaEntityTypedQuery.getSingleResult();
    }

    @Override
    public List<CuentaJpaEntity> findByClienteId(Long cliente_id) {
        String sql = "SELECT cuenta FROM CuentaJpaEntity cuenta WHERE cuenta.cliente_id = :cliente_id";

        TypedQuery<CuentaJpaEntity> cuentaJpaEntityTypedQuery = entityManager
                .createQuery(sql, CuentaJpaEntity.class)
                .setParameter("cliente_id", cliente_id);
        return cuentaJpaEntityTypedQuery.getResultList();
    }

    @Override
    public List<CuentaJpaEntity> findByToken(String token) {
        String sql = """
        SELECT cta
        FROM SesionJpaEntity s
        JOIN s.cliente cli
        JOIN cli.cuentas cta
        WHERE s.token = :token
        """;
        TypedQuery<CuentaJpaEntity> cuentaJpaEntityTypedQuery = entityManager
                .createQuery(sql, CuentaJpaEntity.class)
                .setParameter("token", token);
        return cuentaJpaEntityTypedQuery.getResultList();
    }

    @Override
    public CuentaJpaEntity save(CuentaJpaEntity cuentaJpaEntity) {
        return entityManager.merge(cuentaJpaEntity);
    }

    @Override
    public CuentaJpaEntity findByNDeTarjeta(String nTarjeta) {
        try {
            // Primero obtenemos la tarjeta con la relación cuenta cargada
            String jpql = "SELECT t FROM TarjetaCreditoJpaEntity t " +
                    "LEFT JOIN FETCH t.cuenta " +
                    "WHERE t.numeroTarjeta = :numeroTarjeta";

            TarjetaCreditoJpaEntity tarjeta = entityManager
                    .createQuery(jpql, TarjetaCreditoJpaEntity.class)
                    .setParameter("numeroTarjeta", nTarjeta)
                    .getSingleResult();

            return tarjeta.getCuenta();
        } catch (Exception e) {
            return null;
        }
    }
}
