package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.SesionJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class SesionJpaDaoImpl implements SesionJpaDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public String createSession(Long userId) {
        if(userId == null){
            throw new BusinessException("Nonononoo");
        }
        String uuid = UUID.randomUUID().toString();
        SesionJpaEntity sesionJpaEntity = new SesionJpaEntity();
        sesionJpaEntity.setDateCreate(new Date());
        sesionJpaEntity.setToken(uuid);



        entityManager.persist(sesionJpaEntity);
        return uuid;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public Optional<ClienteJpaEntity> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public Long count() {
        return 0L;
    }
}
