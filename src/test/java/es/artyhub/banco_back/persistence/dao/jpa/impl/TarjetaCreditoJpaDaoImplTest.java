package es.artyhub.banco_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.banco_back.persistence.TestConfig;
import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TarjetaCreditoJpaDaoImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private TarjetaCreditoJpaDao tarjetaCreditoJpaDao;
    

    @Test
    @DisplayName("Find tarjeta by id")
    public void findTarjetaById() {
        Long id = 1L;
        TarjetaCreditoJpaEntity tarjeta = tarjetaCreditoJpaDao.findById(id);
        assertEquals(id, tarjeta.getId());
    }

    @Test
    @DisplayName("Find all tarjetas")
    void findAllTarjetas() {
        List<TarjetaCreditoJpaEntity> tarjetas = tarjetaCreditoJpaDao.findAll();
        assertEquals(3, tarjetas.size());
    }

    @Test
    @DisplayName("Find tarjeta by numero tarjeta")
    public void findTarjetaByNumeroTarjeta() {
        String nTarjeta = "4532123456789012";
        TarjetaCreditoJpaEntity tarjeta = tarjetaCreditoJpaDao.findByNumeroTarjeta(nTarjeta);
        assertEquals(nTarjeta, tarjeta.getNumeroTarjeta());
    }

    @Test
    @DisplayName("Find tarjeta by cuenta id")
    public void findTarjetaByCuentaId() {
        Long id = 1L;
        List<TarjetaCreditoJpaEntity> tarjetas = tarjetaCreditoJpaDao.findByCuentaId(id);
        assertEquals(2, tarjetas.size());
    }

    @Test
    @DisplayName("Save tarjeta")
    public void saveTarjeta() {
        TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity();
        tarjetaCreditoJpaEntity.setNumeroTarjeta("1234567890123456");
        tarjetaCreditoJpaEntity.setFechaCaducidad("12/25");
        tarjetaCreditoJpaEntity.setCvv("123");
        tarjetaCreditoJpaEntity.setNombreCompleto("John Dere");
        
        TarjetaCreditoJpaEntity tarjeta = tarjetaCreditoJpaDao.save(tarjetaCreditoJpaEntity);
        assertEquals(4L, tarjeta.getId());
    }

    @Test
    @DisplayName("Find all tarjetas of user")
    public void findAllTarjetasOfUser() {
        String token = "token_juan_123";
        List<TarjetaCreditoJpaEntity> tarjetas = tarjetaCreditoJpaDao.findAllOfUser(token);
        assertEquals(2, tarjetas.size());
    }

    @Test
    @DisplayName("Tarjeta pertenece a usuario")
    public void tarjetaPerteneceAUsuario() {
        Long tarjetaId = 1L;
        String token = "token_juan_123";
        Boolean tarjeta = tarjetaCreditoJpaDao.tarjetaPerteneceAUsuario(tarjetaId, token);
        assertEquals(true, tarjeta);
    }
}