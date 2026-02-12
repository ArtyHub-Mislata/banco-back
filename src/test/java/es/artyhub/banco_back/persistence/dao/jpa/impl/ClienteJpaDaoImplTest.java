package es.artyhub.banco_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.persistence.TestConfig;
import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteJpaDaoImplTest {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClienteJpaDao clienteJpaDao;


    @Test
    @DisplayName("Find cliente by id")
    public void findClienteById() {
        Long id = 1L;
        ClienteJpaEntity cliente = clienteJpaDao.findById(id);
        assertEquals(id, cliente.getId());
    }

    @Test
    @DisplayName("Find all clientes")
    public void findAllClientes() {
        List<ClienteJpaEntity> clientes = clienteJpaDao.findAll();
        assertEquals(3, clientes.size());
    }

    @Test
    @DisplayName("Find cliente by login")
    public void findClienteByLogin() {
        String login = "juan.perez";
        ClienteJpaEntity cliente = clienteJpaDao.findByLogin(login);
        assertEquals(login, cliente.getLogin());
    }

    @Test
    @DisplayName("User and api token correct")
    public void userAndApiTokenCorrect() {
        AutorizacionDto autorizacionDto = new AutorizacionDto("juan.perez", "token_juan_123");
        Boolean result = clienteJpaDao.userAndApiTokenCorrect(autorizacionDto);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("User and api token incorrect")
    public void userAndApiTokenIncorrect() {
        AutorizacionDto autorizacionDto = new AutorizacionDto("juan.perez", "token_juan");
        Boolean result = clienteJpaDao.userAndApiTokenCorrect(autorizacionDto);
        assertEquals(false, result);
    }
}
