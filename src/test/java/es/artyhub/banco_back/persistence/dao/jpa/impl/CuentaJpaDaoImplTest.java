package es.artyhub.banco_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuentaJpaDaoImplTest {
    
    @Autowired
    private CuentaJpaDao cuentaJpaDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Find cuenta by id")
    public void findCuentaById() {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setLogin("login");
        clienteJpaEntity.setPassword("password");
        clienteJpaEntity.setName("name");
        clienteJpaEntity.setLastName1("lastName1");
        clienteJpaEntity.setLastName2("lastName2");
        clienteJpaEntity.setDni("dni");
        clienteJpaEntity.setApiToken("api_token");
        entityManager.persist(clienteJpaEntity);
        entityManager.flush();

        CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity();
        cuentaJpaEntity.setSaldo(new BigDecimal(100.0));
        cuentaJpaEntity.setIban("iban");
        cuentaJpaEntity.setCliente(clienteJpaEntity);
        cuentaJpaEntity.setTarjetas(null);
        cuentaJpaEntity.setMovimientos(null);
        entityManager.persist(cuentaJpaEntity);
        entityManager.flush();

        Long id = 1L;
        CuentaJpaEntity cuenta = cuentaJpaDao.findById(id);
        assertEquals(id, cuenta.getId());
    }

    @Test
    @DisplayName("Find all cuentas")
    public void findAllCuentas() {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setLogin("login");
        clienteJpaEntity.setPassword("password");
        clienteJpaEntity.setName("name");
        clienteJpaEntity.setLastName1("lastName1");
        clienteJpaEntity.setLastName2("lastName2");
        clienteJpaEntity.setDni("dni");
        clienteJpaEntity.setApiToken("api_token");
        entityManager.persist(clienteJpaEntity);
        entityManager.flush();

        CuentaJpaEntity cuentaJpaEntity1 = new CuentaJpaEntity();
        cuentaJpaEntity1.setSaldo(new BigDecimal(100.0));
        cuentaJpaEntity1.setIban("iban");
        cuentaJpaEntity1.setCliente(clienteJpaEntity);
        cuentaJpaEntity1.setTarjetas(null);
        cuentaJpaEntity1.setMovimientos(null);
        entityManager.persist(cuentaJpaEntity1);
        entityManager.flush();
        
        CuentaJpaEntity cuentaJpaEntity2 = new CuentaJpaEntity();
        cuentaJpaEntity2.setSaldo(new BigDecimal(200.0));
        cuentaJpaEntity2.setIban("iban2");
        cuentaJpaEntity2.setCliente(clienteJpaEntity);
        cuentaJpaEntity2.setTarjetas(null);
        cuentaJpaEntity2.setMovimientos(null);
        entityManager.persist(cuentaJpaEntity2);    
        entityManager.flush();
        
        List<CuentaJpaEntity> cuentas = cuentaJpaDao.findAll();
        assertEquals(2, cuentas.size());
    }

    @Test
    @DisplayName("Find cuenta by iban")
    public void findCuentaByIban() {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setLogin("login");
        clienteJpaEntity.setPassword("password");
        clienteJpaEntity.setName("name");
        clienteJpaEntity.setLastName1("lastName1");
        clienteJpaEntity.setLastName2("lastName2");
        clienteJpaEntity.setDni("dni");
        clienteJpaEntity.setApiToken("api_token");
        entityManager.persist(clienteJpaEntity);
        entityManager.flush();

        CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity();
        cuentaJpaEntity.setSaldo(new BigDecimal(100.0));
        cuentaJpaEntity.setIban("iban");
        cuentaJpaEntity.setCliente(clienteJpaEntity);
        cuentaJpaEntity.setTarjetas(null);
        cuentaJpaEntity.setMovimientos(null);
        entityManager.persist(cuentaJpaEntity);
        entityManager.flush();

        String iban = "iban";
        CuentaJpaEntity cuenta = cuentaJpaDao.findByIban(iban);
        assertEquals(iban, cuenta.getIban());
    }

    @Test
    @DisplayName("Find cuenta by cliente id")
    public void findCuentaByClienteId() {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setId(1L);
        clienteJpaEntity.setLogin("login");
        clienteJpaEntity.setPassword("password");
        clienteJpaEntity.setName("name");
        clienteJpaEntity.setLastName1("lastName1");
        clienteJpaEntity.setLastName2("lastName2");
        clienteJpaEntity.setDni("dni");
        clienteJpaEntity.setApiToken("api_token");
        entityManager.persist(clienteJpaEntity);
        entityManager.flush();

        CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity();
        cuentaJpaEntity.setSaldo(new BigDecimal(100.0));
        cuentaJpaEntity.setIban("iban");
        cuentaJpaEntity.setCliente(clienteJpaEntity);
        cuentaJpaEntity.setTarjetas(null);
        cuentaJpaEntity.setMovimientos(null);
        entityManager.persist(cuentaJpaEntity);
        entityManager.flush();

        List<CuentaJpaEntity> cuentas = cuentaJpaDao.findByClienteId(clienteJpaEntity.getId());
        assertEquals(clienteJpaEntity.getId(), cuentas.get(0).getCliente().getId());
    }

    @Test
    @DisplayName("Save cuenta")
    public void saveCuenta() {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setLogin("login");
        clienteJpaEntity.setPassword("password");
        clienteJpaEntity.setName("name");
        clienteJpaEntity.setLastName1("lastName1");
        clienteJpaEntity.setLastName2("lastName2");
        clienteJpaEntity.setDni("dni");
        clienteJpaEntity.setApiToken("api_token");
        entityManager.persist(clienteJpaEntity);
        entityManager.flush();

        CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity();
        cuentaJpaEntity.setSaldo(new BigDecimal(100.0));
        cuentaJpaEntity.setIban("iban");
        cuentaJpaEntity.setCliente(clienteJpaEntity);
        cuentaJpaEntity.setTarjetas(null);
        cuentaJpaEntity.setMovimientos(null);
        entityManager.persist(cuentaJpaEntity);
        entityManager.flush();

        CuentaJpaEntity cuenta = cuentaJpaDao.insert(cuentaJpaEntity);
        assertEquals(cuentaJpaEntity.getId(), cuenta.getId());
    }
}
