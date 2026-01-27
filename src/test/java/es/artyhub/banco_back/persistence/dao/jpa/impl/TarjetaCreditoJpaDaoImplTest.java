package es.artyhub.banco_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TarjetaCreditoJpaDaoImplTest {

    @Autowired
    private TarjetaCreditoJpaDao tarjetaCreditoJpaDao;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Find tarjeta by id")
    public void findTarjetaById() {
        TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity();
        tarjetaCreditoJpaEntity.setNumeroTarjeta("numeroTarjeta");
        tarjetaCreditoJpaEntity.setFechaCaducidad("fechaCaducidad");
        tarjetaCreditoJpaEntity.setCvv("cvv");
        tarjetaCreditoJpaEntity.setNombreCompleto("nombreCompleto");
        entityManager.persist(tarjetaCreditoJpaEntity);
        entityManager.flush();

        Long id = 1L;
        TarjetaCreditoJpaEntity tarjeta = tarjetaCreditoJpaDao.findById(id);
        assertEquals(id, tarjeta.getId());
    }

    @Test
    @DisplayName("Find all tarjetas")
    void findAllTarjetas() {
        TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity();
        tarjetaCreditoJpaEntity.setNumeroTarjeta("numeroTarjeta");
        tarjetaCreditoJpaEntity.setFechaCaducidad("fechaCaducidad");
        tarjetaCreditoJpaEntity.setCvv("cvv");
        tarjetaCreditoJpaEntity.setNombreCompleto("nombreCompleto");
        entityManager.persist(tarjetaCreditoJpaEntity);
        entityManager.flush();

        TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity2 = new TarjetaCreditoJpaEntity();
        tarjetaCreditoJpaEntity.setNumeroTarjeta("numeroTarjeta2");
        tarjetaCreditoJpaEntity.setFechaCaducidad("fechaCaducidad2");
        tarjetaCreditoJpaEntity.setCvv("cvv2");
        tarjetaCreditoJpaEntity.setNombreCompleto("nombreCompleto2");
        entityManager.persist(tarjetaCreditoJpaEntity2);
        entityManager.flush();

        List<TarjetaCreditoJpaEntity> tarjetas = tarjetaCreditoJpaDao.findAll();
        assertEquals(2, tarjetas.size());
    }

    @Test
    @DisplayName("Find tarjeta by numero tarjeta")
    public void findTarjetaByNumeroTarjeta() {
        TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity();
        tarjetaCreditoJpaEntity.setNumeroTarjeta("numeroTarjeta");
        tarjetaCreditoJpaEntity.setFechaCaducidad("fechaCaducidad");
        tarjetaCreditoJpaEntity.setCvv("cvv");
        tarjetaCreditoJpaEntity.setNombreCompleto("nombreCompleto");
        entityManager.persist(tarjetaCreditoJpaEntity);
        entityManager.flush();

        String nTarjeta = "numeroTarjeta";
        TarjetaCreditoJpaEntity tarjeta = tarjetaCreditoJpaDao.findByNumeroTarjeta(nTarjeta);
        assertEquals(nTarjeta, tarjeta.getNumeroTarjeta());
    }

    @Test
    @DisplayName("Find tarjeta by cuenta id")
    public void findTarjetaByCuentaId() {
        CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity();
        cuentaJpaEntity.setSaldo(new BigDecimal(100.0));
        cuentaJpaEntity.setIban("iban");
        cuentaJpaEntity.setCliente(null);
        cuentaJpaEntity.setTarjetas(List.of());
        cuentaJpaEntity.setMovimientos(List.of());
        entityManager.persist(cuentaJpaEntity);
        entityManager.flush();

        TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity();
        tarjetaCreditoJpaEntity.setNumeroTarjeta("numeroTarjeta");
        tarjetaCreditoJpaEntity.setFechaCaducidad("fechaCaducidad");
        tarjetaCreditoJpaEntity.setCvv("cvv");
        tarjetaCreditoJpaEntity.setNombreCompleto("nombreCompleto");
        tarjetaCreditoJpaEntity.setCuenta(cuentaJpaEntity);
        entityManager.persist(tarjetaCreditoJpaEntity);
        entityManager.flush();

        List<TarjetaCreditoJpaEntity> tarjetas = tarjetaCreditoJpaDao.findByCuentaId(cuentaJpaEntity.getId());
        assertEquals(cuentaJpaEntity.getId(), tarjetas.get(0).getCuenta().getId());
    }

    @Test
    @DisplayName("Save tarjeta")
    public void saveTarjeta() {
        TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity();
        tarjetaCreditoJpaEntity.setNumeroTarjeta("numeroTarjeta");
        tarjetaCreditoJpaEntity.setFechaCaducidad("fechaCaducidad");
        tarjetaCreditoJpaEntity.setCvv("cvv");
        tarjetaCreditoJpaEntity.setNombreCompleto("nombreCompleto");
        entityManager.persist(tarjetaCreditoJpaEntity);
        entityManager.flush();

        TarjetaCreditoJpaEntity tarjeta = tarjetaCreditoJpaDao.save(tarjetaCreditoJpaEntity);
        assertEquals(tarjetaCreditoJpaEntity.getId(), tarjeta.getId());
    }
}