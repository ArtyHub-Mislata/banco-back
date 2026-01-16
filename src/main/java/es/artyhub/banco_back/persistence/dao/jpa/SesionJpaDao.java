package es.artyhub.banco_back.persistence.dao.jpa;

import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;

import java.util.Optional;

public interface SesionJpaDao {
    String createSession(Long userId);
    void deleteSesion(String token);
    Optional<ClienteJpaEntity> findByToken(String token);
    Long count();
}
