package es.artyhub.banco_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public class CuentaMapperTest {
    
    @Nested
    @DisplayName("Test fromCuentaJpaEntityToCuenta")
    class FromCuentaJpaEntityToCuentaTest {

        @Test
        @DisplayName("Test fromCuentaJpaEntityToCuenta with null CuentaJpaEntity should return null")
        void testFromCuentaJpaEntityToCuenta_NullInput() {
            Cuenta result = CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCuentaJpaEntityToCuenta with valid CuentaJpaEntity should return CuentaDto")
        void testFromCuentaJpaEntityToCuenta_ValidInput() {
            ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity(
                    1L,
                    "login",
                    "password",
                    "name",
                    "lastName1",
                    "lastName2",
                    "dni",
                    "apiToken"
            );
            List<TarjetaCreditoJpaEntity> tarjetasJpaEntities = List.of(new TarjetaCreditoJpaEntity(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe"));
            List<MovimientoBancarioJpaEntity> movimientosBancariosJpaEntities = List.of(new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe"), new Date(), new BigDecimal(100.00), "Concepto"));
            CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(
                    1L,
                    new BigDecimal(100.0),
                    "ES1234567890123456789012",
                    clienteJpaEntity,
                    tarjetasJpaEntities,
                    movimientosBancariosJpaEntities
            );

            Cuenta cuenta = CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaEntity);

            assertAll(
                    () -> assertNotNull(cuenta),
                    () -> assertEquals(1L, cuenta.getId()),
                    () -> assertEquals("ES1234567890123456789012", cuenta.getIban()),
                    () -> assertEquals(1L, cuenta.getCliente().getId()));
        }
    }

    @Nested
    @DisplayName("Test fromCuentaToCuentaJpaEntity")
    class FromCuentaToCuentaJpaEntityTest {

        @Test
        @DisplayName("Test fromCuentaToCuentaJpaEntity with null CuentaDto should return null")
        void testFromCuentaToCuentaJpaEntity_NullInput() {
            CuentaJpaEntity result = CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCuentaToCuentaJpaEntity with valid CuentaDto should return CuentaJpaEntity")
        void testFromCuentaToCuentaJpaEntity_ValidInput() {
            Cuenta cuenta = new Cuenta(
                    1L,
                    new BigDecimal(100.0),
                    "ES1234567890123456789012",
                    new Cliente(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "apiToken"),
                    List.of(new TarjetaCredito(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe")),
                    List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCredito(1L, "1234-5678-9012-3456", "12/25", "123", "John Doe"), new Date(), new BigDecimal(100.00), "Concepto"))
            );

            CuentaJpaEntity cuentaJpaEntity = CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(cuenta);

            assertAll(
                    () -> assertNotNull(cuentaJpaEntity),
                    () -> assertEquals(1L, cuentaJpaEntity.getId()),
                    () -> assertEquals("ES1234567890123456789012", cuentaJpaEntity.getIban()));
        }
    }
}
