package es.artyhub.banco_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteJpaDaoImplTest {
    
    @Autowired
    private ClienteJpaDao clienteJpaDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Find cliente by id")
    public void findClienteById() {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setLogin("login");
        clienteJpaEntity.setPassword("password");
        clienteJpaEntity.setName("name");
        clienteJpaEntity.setLastName1("lastName1");
        clienteJpaEntity.setLastName2("lastName2");
        clienteJpaEntity.setDni("dni");
        clienteJpaEntity.setApi_token("api_token");
        entityManager.persist(clienteJpaEntity);
        entityManager.flush();

        Long id = 1L;
        ClienteJpaEntity cliente = clienteJpaDao.findById(id);
        assertEquals(id, cliente.getId());
    }

    @Test
    @DisplayName("Find all clientes")
    public void findAllClientes() {
        ClienteJpaEntity clienteJpaEntity1 = new ClienteJpaEntity();
        clienteJpaEntity1.setLogin("login");
        clienteJpaEntity1.setPassword("password");
        clienteJpaEntity1.setName("name");
        clienteJpaEntity1.setLastName1("lastName1");
        clienteJpaEntity1.setLastName2("lastName2");
        clienteJpaEntity1.setDni("dni");
        clienteJpaEntity1.setApi_token("api_token");
        entityManager.persist(clienteJpaEntity1);
        entityManager.flush();
        
        ClienteJpaEntity clienteJpaEntity2 = new ClienteJpaEntity();
        clienteJpaEntity2.setLogin("login2");
        clienteJpaEntity2.setPassword("password2");
        clienteJpaEntity2.setName("name2");
        clienteJpaEntity2.setLastName1("lastName12");
        clienteJpaEntity2.setLastName2("lastName22");
        clienteJpaEntity2.setDni("dni2");
        clienteJpaEntity2.setApi_token("api_token2");
        entityManager.persist(clienteJpaEntity2);
        entityManager.flush();
        
        List<ClienteJpaEntity> clientes = clienteJpaDao.findAll();
        assertEquals(2, clientes.size());
    }

    @Test
    @DisplayName("Find cliente by login")
    public void findClienteByLogin() {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setLogin("login");
        clienteJpaEntity.setPassword("password");
        clienteJpaEntity.setName("name");
        clienteJpaEntity.setLastName1("lastName1");
        clienteJpaEntity.setLastName2("lastName2");
        clienteJpaEntity.setDni("dni");
        clienteJpaEntity.setApi_token("api_token");
        entityManager.persist(clienteJpaEntity);
        entityManager.flush();
    
        String login = "login";
        ClienteJpaEntity cliente = clienteJpaDao.findByLogin(login);
        assertEquals(login, cliente.getLogin());
    }
}
