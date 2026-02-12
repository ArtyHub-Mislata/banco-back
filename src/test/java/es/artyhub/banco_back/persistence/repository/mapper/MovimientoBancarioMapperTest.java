package es.artyhub.banco_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public class MovimientoBancarioMapperTest {
    
    @Nested
    @DisplayName("Test fromMovimientoBancarioJpaEntityToMovimientoBancario")
    class FromMovimientoBancarioJpaEntityToMovimientoBancarioTest {

        @Test
        @DisplayName("Test fromMovimientoBancarioJpaEntityToMovimientoBancario with null MovimientoBancarioJpaEntity should return null")
        void testFromMovimientoBancarioJpaEntityToMovimientoBancario_NullInput() {
            MovimientoBancario result = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromMovimientoBancarioJpaEntityToMovimientoBancario with valid MovimientoBancarioJpaEntity should return MovimientoBancario")
        void testFromMovimientoBancarioJpaEntityToMovimientoBancario_ValidInput() {
            TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe");
            MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, tarjetaCreditoJpaEntity, new Date(), new BigDecimal(100.00), "Concepto");

            MovimientoBancario movimientoBancario = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaEntity);

            assertAll(
                    () -> assertNotNull(movimientoBancario),
                    () -> assertEquals(1L, movimientoBancario.getId()),
                    () -> assertEquals(1L, movimientoBancario.getTarjetaCredito().getId()));
        }
    }

    @Nested
    @DisplayName("Test fromMovimientoBancarioToMovimientoBancarioJpaEntity")
    class FromMovimientoBancarioToMovimientoBancarioJpaEntityTest {

        @Test
        @DisplayName("Test fromMovimientoBancarioToMovimientoBancarioJpaEntity with null MovimientoBancario should return null")
        void testFromMovimientoBancarioToMovimientoBancarioJpaEntity_NullInput() {
            MovimientoBancarioJpaEntity result = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromMovimientoBancarioToMovimientoBancarioJpaEntity with valid MovimientoBancario should return MovimientoBancarioJpaEntity")
        void testFromMovimientoBancarioToMovimientoBancarioJpaEntity_ValidInput() {
            TarjetaCredito tarjetaCredito = new TarjetaCredito(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe");
            MovimientoBancario movimientoBancario = new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, tarjetaCredito, new Date(), new BigDecimal(100.00), "Concepto");

            MovimientoBancarioJpaEntity movimientoBancarioJpaEntity = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(movimientoBancario);

            assertAll(
                    () -> assertNotNull(movimientoBancarioJpaEntity),
                    () -> assertEquals(1L, movimientoBancarioJpaEntity.getId()),
                    () -> assertEquals(1L, movimientoBancarioJpaEntity.getTarjetaCredito().getId()));
        }
    }
}
