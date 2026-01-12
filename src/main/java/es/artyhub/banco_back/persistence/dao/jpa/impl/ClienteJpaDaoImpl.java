package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ClienteJpaDaoImpl implements ClienteJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClienteJpaEntity findById(Long id) {
        return entityManager.find(ClienteJpaEntity.class, id);
    }

    @Override
    public List<ClienteJpaEntity> findAll() {

        String sql = "SELECT cliente FROM ClienteJpaEntity cliente ORDER BY cliente.id ASC";

        TypedQuery<ClienteJpaEntity> clienteJpaEntityTypedQuery = entityManager
                .createQuery(sql, ClienteJpaEntity.class);
        return clienteJpaEntityTypedQuery.getResultList();
    }

    @Override
    public ClienteJpaEntity findByLogin(String login) {
        String sql = "SELECT cliente FROM ClienteJpaEntity cliente WHERE cliente.login = :login";

        TypedQuery<ClienteJpaEntity> clienteJpaEntityTypedQuery = entityManager
                .createQuery(sql, ClienteJpaEntity.class)
                .setParameter("login", login);
        return clienteJpaEntityTypedQuery.getSingleResult();
    }
}
