package es.artyhub.banco_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.persistence.TestConfig;
import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovimientoBancarioJpaDaoImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MovimientoBancarioJpaDao movimientoBancarioJpaDao;
    

    @Test
    @DisplayName("Find movimiento bancario by id")
    void findMovimientoBancarioById() {
        Long id = 1L;
        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findById(id);
        assertEquals(id, movimiento.getId());
    }

    @Test
    @DisplayName("Find all movimientos bancarios")
    void findAllMovimientosBancarios() {
        List<MovimientoBancarioJpaEntity> movimientos = movimientoBancarioJpaDao.findAll();
        assertEquals(3, movimientos.size());
    }

    @Test
    @DisplayName("Find movimiento bancario by importe")
    void findMovimientoBancarioByImporte() {
        BigDecimal importe = new BigDecimal("100.00");
        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findByImporte(importe);
        assertEquals(importe, movimiento.getImporte());
    }

    @Test
    @DisplayName("Find movimiento bancario by concepto")
    void findMovimientoBancarioByConcepto() {
        String concepto = "ingreso";
        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findByConcepto(concepto);
        assertEquals(concepto, movimiento.getConcepto());
    }

    @Test
    @DisplayName("Find movimiento bancario by cuenta id")
    void findMovimientoBancarioByCuentaId() {
        List<MovimientoBancarioJpaEntity> movimientos = movimientoBancarioJpaDao.findByCuentaId(1L);
        assertEquals(1L, movimientos.get(0).getCuenta().getId());
    }

    @Test
    @DisplayName("Insert movimiento bancario")
    void insertMovimientoBancario() {
        TarjetaCreditoJpaEntity tarjetaCredito = entityManager.find(TarjetaCreditoJpaEntity.class, 1L);
        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity(null, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, tarjetaCredito, new Date(), new BigDecimal(100.00), "concepto");

        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.insert(movimientoBancarioJpaEntity, 1L);
        assertEquals(4L, movimiento.getId());
    }

    @Test
    @DisplayName("Should throw resource not found exception if cuenta doesn't exist")
    void insertMovimientoBancario_shouldThrowResourceNotFoundException_WhenCuentaNotExists() {
        MovimientoBancarioJpaEntity movimiento = movimientoBancarioJpaDao.findById(4L);
        assertThrows(ResourceNotFoundException.class, () -> movimientoBancarioJpaDao.insert(movimiento, 4L));
    }

    @Test
    @DisplayName("Update movimiento bancario")
    void updateMovimientoBancario() {
        MovimientoBancarioJpaEntity movimientoBancario = movimientoBancarioJpaDao.findById(1L);

        movimientoBancario.setImporte(new BigDecimal(100.0));

        MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = movimientoBancarioJpaDao.update(movimientoBancario);

        assertEquals(movimientoBancarioJpaEntity.getImporte(), new BigDecimal(100.0));
    }
}
