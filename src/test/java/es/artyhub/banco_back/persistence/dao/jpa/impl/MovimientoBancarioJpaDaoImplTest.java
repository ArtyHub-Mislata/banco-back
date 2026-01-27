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

import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovimientoBancarioJpaDaoImplTest {

    @Autowired
    private MovimientoBancarioJpaDao movimientoBancarioJpaDao;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Find movimiento bancario by id")
    void findMovimientoBancarioById() {
        TarjetaCreditoJpaEntity tarjetaCredito = new TarjetaCreditoJpaEntity();
        tarjetaCredito.setNumeroTarjeta("1234567890123456");
        tarjetaCredito.setFechaCaducidad("12/25");
        tarjetaCredito.setCvv("123");
        tarjetaCredito.setNombreCompleto("nombre");
        entityManager.persist(tarjetaCredito);
        entityManager.flush();
        
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity.setTipoMovimiento(null);
        movimientoBancarioJpaEntity.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity.setTarjetaCredito(tarjetaCredito);
        movimientoBancarioJpaEntity.setFecha(new Date());
        movimientoBancarioJpaEntity.setImporte(new BigDecimal(100.0));
        movimientoBancarioJpaEntity.setConcepto("concepto");
        movimientoBancarioJpaEntity.setCuenta(null);
        entityManager.persist(movimientoBancarioJpaEntity);
        entityManager.flush();

        Long id = 1L;
        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findById(id);
        assertEquals(id, movimiento.getId());
    }

    @Test
    @DisplayName("Find all movimientos bancarios")
    void findAllMovimientosBancarios() {
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity1 = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity1.setTipoMovimiento(null);
        movimientoBancarioJpaEntity1.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity1.setTarjetaCredito(null);
        movimientoBancarioJpaEntity1.setFecha(new Date());
        movimientoBancarioJpaEntity1.setImporte(new BigDecimal(100.0));
        movimientoBancarioJpaEntity1.setConcepto("concepto");
        movimientoBancarioJpaEntity1.setCuenta(null);
        entityManager.persist(movimientoBancarioJpaEntity1);
        entityManager.flush();
        
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity2 = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity2.setTipoMovimiento(null);
        movimientoBancarioJpaEntity2.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity2.setTarjetaCredito(null);
        movimientoBancarioJpaEntity2.setFecha(new Date());
        movimientoBancarioJpaEntity2.setImporte(new BigDecimal(200.0));
        movimientoBancarioJpaEntity2.setConcepto("concepto2");
        movimientoBancarioJpaEntity2.setCuenta(null);
        entityManager.persist(movimientoBancarioJpaEntity2);
        entityManager.flush();
        
        List<MovimientoBancarioJpaEntity> movimientos = movimientoBancarioJpaDao.findAll();
        assertEquals(2, movimientos.size());
    }

    @Test
    @DisplayName("Find movimiento bancario by importe")
    void findMovimientoBancarioByImporte() {
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity.setTipoMovimiento(null);
        movimientoBancarioJpaEntity.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity.setTarjetaCredito(null);
        movimientoBancarioJpaEntity.setFecha(new Date());
        movimientoBancarioJpaEntity.setImporte(new BigDecimal(100.0));
        movimientoBancarioJpaEntity.setConcepto("concepto");
        movimientoBancarioJpaEntity.setCuenta(null);
        entityManager.persist(movimientoBancarioJpaEntity);
        entityManager.flush();

        BigDecimal importe = new BigDecimal(100.0);
        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findByImporte(importe);
        assertEquals(importe, movimiento.getImporte());
    }

    @Test
    @DisplayName("Find movimiento bancario by concepto")
    void findMovimientoBancarioByConcepto() {
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity.setTipoMovimiento(null);
        movimientoBancarioJpaEntity.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity.setTarjetaCredito(null);
        movimientoBancarioJpaEntity.setFecha(new Date());
        movimientoBancarioJpaEntity.setImporte(new BigDecimal(100.0));
        movimientoBancarioJpaEntity.setConcepto("concepto");
        movimientoBancarioJpaEntity.setCuenta(null);
        entityManager.persist(movimientoBancarioJpaEntity);
        entityManager.flush();

        String concepto = "concepto";
        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findByConcepto(concepto);
        assertEquals(concepto, movimiento.getConcepto());
    }

    @Test
    @DisplayName("Find movimiento bancario by cuenta id")
    void findMovimientoBancarioByCuentaId() {
        CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity();
        cuentaJpaEntity.setSaldo(new BigDecimal(100.0));
        cuentaJpaEntity.setIban("iban");
        cuentaJpaEntity.setCliente(null);
        cuentaJpaEntity.setTarjetas(List.of());
        cuentaJpaEntity.setMovimientos(List.of());
        entityManager.persist(cuentaJpaEntity);
        entityManager.flush();

        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity.setTipoMovimiento(null);
        movimientoBancarioJpaEntity.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity.setTarjetaCredito(null);
        movimientoBancarioJpaEntity.setFecha(new Date());
        movimientoBancarioJpaEntity.setImporte(new BigDecimal(100.0));
        movimientoBancarioJpaEntity.setConcepto("concepto");
        movimientoBancarioJpaEntity.setCuenta(cuentaJpaEntity);
        entityManager.persist(movimientoBancarioJpaEntity);
        entityManager.flush();

        List<MovimientoBancarioJpaEntity> movimientos = movimientoBancarioJpaDao.findByCuentaId(cuentaJpaEntity.getId());
        assertEquals(cuentaJpaEntity.getId(), movimientos.get(0).getCuenta().getId());
    }

    @Test
    @DisplayName("Insert movimiento bancario")
    void insertMovimientoBancario() {
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity.setTipoMovimiento(null);
        movimientoBancarioJpaEntity.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity.setTarjetaCredito(null);
        movimientoBancarioJpaEntity.setFecha(new Date());
        movimientoBancarioJpaEntity.setImporte(new BigDecimal(100.0));
        movimientoBancarioJpaEntity.setConcepto("concepto");
        movimientoBancarioJpaEntity.setCuenta(null);
        entityManager.persist(movimientoBancarioJpaEntity);
        entityManager.flush();

        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findById(1L);
        assertEquals(1L, movimiento.getId());
    }

    @Test
    @DisplayName("Update movimiento bancario")
    void updateMovimientoBancario() {
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity();
        movimientoBancarioJpaEntity.setTipoMovimiento(null);
        movimientoBancarioJpaEntity.setOrigenMovimiento(null);
        movimientoBancarioJpaEntity.setTarjetaCredito(null);
        movimientoBancarioJpaEntity.setFecha(new Date());
        movimientoBancarioJpaEntity.setImporte(new BigDecimal(100.0));
        movimientoBancarioJpaEntity.setConcepto("concepto");
        movimientoBancarioJpaEntity.setCuenta(null);
        entityManager.persist(movimientoBancarioJpaEntity);
        entityManager.flush();

        movimientoBancarioJpaEntity.setConcepto("concepto2");
        movimientoBancarioJpaDao.update(movimientoBancarioJpaEntity);

        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findById(1L);
        assertEquals("concepto2", movimiento.getConcepto());
    }
}
