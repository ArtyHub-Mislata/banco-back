package es.artyhub.banco_back.persistence.dao.jpa.impl;

import es.artyhub.banco_back.persistence.TestConfig;
import es.artyhub.banco_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.SesionJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SesionJpaDaoImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private SesionJpaDao sesionJpaDao;


    @Test
    @DisplayName("Create session")
    public void createSession() {
        Long id = 1L;

        ClienteJpaEntity clienteJpaEntity = entityManager.find(ClienteJpaEntity.class, id);

        SesionJpaEntity sesionJpaEntity = new SesionJpaEntity("token", clienteJpaEntity, new Date());

        assertEquals(clienteJpaEntity.getId(), sesionJpaEntity.getCliente().getId());
    }

    @Test
    @DisplayName("Delete session")
    public void deleteSesion() {
        String token = "token_juan_123";

        sesionJpaDao.deleteSesion(token);

        assertEquals(Optional.empty(), sesionJpaDao.findByToken(token));
    }

    @Test
    @DisplayName("Find by token")
    public void findByToken() {
        String token = "token_juan_123";

        Optional<ClienteJpaEntity> clienteJpaEntity = sesionJpaDao.findByToken(token);

        assertEquals(token, clienteJpaEntity.get().getApiToken());
    }

    @Test
    @DisplayName("Count")
    public void count() {
        Long sesions = sesionJpaDao.count();

        assertEquals(3, sesions);
    }
}
