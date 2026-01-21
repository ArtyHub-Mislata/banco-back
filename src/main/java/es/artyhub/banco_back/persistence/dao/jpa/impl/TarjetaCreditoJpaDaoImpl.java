package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
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

    @Override
    public TarjetaCreditoJpaEntity findByNumeroTarjeta(String numero) {
        String sql = "SELECT t FROM TarjetaCreditoJpaEntity t WHERE t.numeroTarjeta = :numero";

        List<TarjetaCreditoJpaEntity> resultados = entityManager
                .createQuery(sql, TarjetaCreditoJpaEntity.class)
                .setParameter("numero", numero)
                .setMaxResults(1)
                .getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public List<TarjetaCreditoJpaEntity> findByCuentaId(Long cuenta_id) {
        String sql = "SELECT tarjeta FROM TarjetaCreditoJpaEntity tarjeta WHERE tarjeta.cuenta.id = :cuenta_id";

        TypedQuery<TarjetaCreditoJpaEntity> tarjetaCreditoJpaEntityTypedQuery = entityManager
                .createQuery(sql, TarjetaCreditoJpaEntity.class)
                .setParameter("cuenta_id", cuenta_id);
        return tarjetaCreditoJpaEntityTypedQuery.getResultList();
    }

    @Override
    public TarjetaCreditoJpaEntity save(TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity) {
        return entityManager.merge(tarjetaCreditoJpaEntity);
    }

    @Override
    public List<TarjetaCreditoJpaEntity> findAllOfUser(String token) {
        String sql = """
        SELECT t 
        FROM TarjetaCreditoJpaEntity t
        JOIN t.cuenta c
        JOIN c.cliente cli
        JOIN SesionJpaEntity s ON s.cliente = cli
        WHERE s.token = :token
        ORDER BY t.id ASC
        """;

        TypedQuery<TarjetaCreditoJpaEntity> query = entityManager
                .createQuery(sql, TarjetaCreditoJpaEntity.class)
                .setParameter("token", token);

        return query.getResultList();
    }

    @Override
    public Boolean tarjetaPerteneceAUsuario(Long tarjetaId, String token) {
        String sql = """
        SELECT COUNT(t) 
        FROM TarjetaCreditoJpaEntity t
        JOIN t.cuenta c
        JOIN c.cliente cli
        JOIN SesionJpaEntity s ON s.cliente = cli
        WHERE s.token = :token 
        AND t.id = :tarjetaId
        """;

        Long count = entityManager
                .createQuery(sql, Long.class)
                .setParameter("token", token)
                .setParameter("tarjetaId", tarjetaId)
                .getSingleResult();

        return count > 0;
    }


}
