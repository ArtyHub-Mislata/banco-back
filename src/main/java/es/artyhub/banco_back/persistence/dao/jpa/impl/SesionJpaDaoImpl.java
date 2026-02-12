package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.SesionJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SesionJpaDaoImpl implements SesionJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String createSession(Long userId) {
        ClienteJpaEntity clienteJpaEntity = entityManager.find(ClienteJpaEntity.class, userId);

        String uuid = UUID.randomUUID().toString();

        SesionJpaEntity sesionJpaEntity = new SesionJpaEntity();
        sesionJpaEntity.setDateCreate(new Date());
        sesionJpaEntity.setToken(uuid);
        sesionJpaEntity.setCliente(clienteJpaEntity);

        entityManager.persist(sesionJpaEntity);
        return uuid;
    }

    @Override
    public void deleteSesion(String token) {
        SesionJpaEntity sesion = entityManager.createQuery(
                        "SELECT s FROM SesionJpaEntity s WHERE s.token = :token", SesionJpaEntity.class)
                .setParameter("token", token)
                .getSingleResult();

        if (sesion != null) {
            entityManager.remove(sesion);
        }
    }

    @Override
    public Optional<ClienteJpaEntity> findByToken(String token) {

        String jpql = "SELECT s.cliente FROM SesionJpaEntity s WHERE s.token = :token";

        List<ClienteJpaEntity> result = entityManager
                .createQuery(jpql, ClienteJpaEntity.class)
                .setParameter("token", token)
                .setMaxResults(1)
                .getResultList();

        return result.stream().findFirst();
    }

    @Override
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(s) FROM SesionJpaEntity s", Long.class).getSingleResult();
    }
}
