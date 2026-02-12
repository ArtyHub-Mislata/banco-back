package es.artyhub.banco_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public class TarjetaCreditoMapperTest {
    
    @Nested
    @DisplayName("Test fromTarjetaCreditoJpaEntityToTarjetaCredito")
    class FromTarjetaCreditoJpaEntityToTarjetaCreditoTest {

        @Test
        @DisplayName("Test fromTarjetaCreditoJpaEntityToTarjetaCredito with null TarjetaCreditoJpaEntity should return null")
        void testFromTarjetaCreditoJpaEntityToTarjetaCredito_NullInput() {
            TarjetaCredito result = TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCredito(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromTarjetaCreditoJpaEntityToTarjetaCredito with valid TarjetaCreditoJpaEntity should return TarjetaCredito")
        void testFromTarjetaCreditoJpaEntityToTarjetaCredito_ValidInput() {
            TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = new TarjetaCreditoJpaEntity(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe");
            
            TarjetaCredito tarjetaCredito = TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCredito(tarjetaCreditoJpaEntity);

            assertAll(
                    () -> assertNotNull(tarjetaCredito),
                    () -> assertEquals(1L, tarjetaCredito.getId()),
                    () -> assertEquals("1234-5678-9012-3456", tarjetaCredito.getNumeroTarjeta()),
                    () -> assertEquals("12/25", tarjetaCredito.getFechaCaducidad()),
                    () -> assertEquals("123", tarjetaCredito.getCvv()),
                    () -> assertEquals("John Doe", tarjetaCredito.getNombreCompleto()));
        }
    }

    @Nested
    @DisplayName("Test fromTarjetaCreditoToTarjetaCreditoJpaEntity")
    class FromTarjetaCreditoToTarjetaCreditoJpaEntityTest {

        @Test
        @DisplayName("Test fromTarjetaCreditoToTarjetaCreditoJpaEntity with null TarjetaCredito should return null")
        void testFromTarjetaCreditoToTarjetaCreditoJpaEntity_NullInput() {
            TarjetaCreditoJpaEntity result = TarjetaCreditoMapper.getInstance().fromTarjetaCreditoToTarjetaCreditoJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromTarjetaCreditoToTarjetaCreditoJpaEntity with valid TarjetaCredito should return TarjetaCreditoJpaEntity")
        void testFromTarjetaCreditoToTarjetaCreditoJpaEntity_ValidInput() {
            TarjetaCredito tarjetaCredito = new TarjetaCredito(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe");

            TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity = TarjetaCreditoMapper.getInstance().fromTarjetaCreditoToTarjetaCreditoJpaEntity(tarjetaCredito);

            assertAll(
                    () -> assertNotNull(tarjetaCreditoJpaEntity),
                    () -> assertEquals(1L, tarjetaCreditoJpaEntity.getId()),
                    () -> assertEquals("1234-5678-9012-3456", tarjetaCreditoJpaEntity.getNumeroTarjeta()),
                    () -> assertEquals("12/25", tarjetaCreditoJpaEntity.getFechaCaducidad()),
                    () -> assertEquals("123", tarjetaCreditoJpaEntity.getCvv()),
                    () -> assertEquals("John Doe", tarjetaCreditoJpaEntity.getNombreCompleto()));
        }
    }
}
