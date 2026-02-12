package es.artyhub.banco_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import es.artyhub.banco_back.persistence.TestConfig;
import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuentaJpaDaoImplTest {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CuentaJpaDao cuentaJpaDao;


    @Test
    @DisplayName("Find cuenta by id")
    public void findCuentaById() {
        Long id = 1L;
        CuentaJpaEntity cuenta = cuentaJpaDao.findById(id);
        assertEquals(id, cuenta.getId());
    }

    @Test
    @DisplayName("Find all cuentas")
    public void findAllCuentas() {
        List<CuentaJpaEntity> cuentas = cuentaJpaDao.findAll();
        assertEquals(3, cuentas.size());
    }

    @Test
    @DisplayName("Find cuenta by iban")
    public void findCuentaByIban() {
        String iban = "ES9121000418450200051332";
        CuentaJpaEntity cuenta = cuentaJpaDao.findByIban(iban);
        assertEquals(iban, cuenta.getIban());
    }

    @Test
    @DisplayName("Find cuenta by cliente id")
    public void findCuentaByClienteId() {
        Long id = 1L;
        List<CuentaJpaEntity> cuentas = cuentaJpaDao.findByClienteId(id);
        assertEquals(id, cuentas.get(0).getCliente().getId());
    }

    @Test
    @DisplayName("Find cuenta by token")
    public void findCuentaByToken() {
        String token = "token_juan_123";
        List<CuentaJpaEntity> cuentas = cuentaJpaDao.findByToken(token);
        assertEquals(1, cuentas.size());
    }

    @Test
    @DisplayName("Find cuenta by n de tarjeta")
    public void findCuentaByNDeTarjeta() {
        String nTarjeta = "4532123456789012";
        CuentaJpaEntity cuenta = cuentaJpaDao.findByNDeTarjeta(nTarjeta);
        assertEquals(nTarjeta, cuenta.getTarjetas().get(0).getNumeroTarjeta());
    }

    @Test
    @DisplayName("Update cuenta")
    public void updateCuenta() {
        CuentaJpaEntity cuenta = cuentaJpaDao.findById(1L);
        
        cuenta.setSaldo(new BigDecimal(100.0));
        
        CuentaJpaEntity cuentaJpaEntity = cuentaJpaDao.update(cuenta);
        
        assertEquals(cuentaJpaEntity.getSaldo(), new BigDecimal(100.0));
    }
}
