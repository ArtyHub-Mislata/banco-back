package es.artyhub.banco_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import es.artyhub.banco_back.persistence.repository.mapper.CuentaMapper;

@ExtendWith(MockitoExtension.class)
public class CuentaRepositoryImplTest {
    
    @Mock
    private CuentaJpaDao cuentaJpaDao;

    @InjectMocks
    private CuentaRepositoryImpl cuentaRepository;

    @Nested
    @DisplayName("findById")
    class FindByIdTest {
        
        @Test
        @DisplayName("Should return a customer")
        void shouldReturnCustomer() {
            Long cuentaId = 1L;

            ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token");
            List<TarjetaCreditoJpaEntity> tarjetasJpaEntity = List.of(new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"));
            List<MovimientoBancarioJpaEntity> movimientosJpaEntity = List.of(new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto"));

            CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(1L, new BigDecimal(100.00), "ES1234567890123456789012", clienteJpaEntity, tarjetasJpaEntity, movimientosJpaEntity);
            
            when(cuentaJpaDao.findById(cuentaId)).thenReturn(cuentaJpaEntity);

            Cuenta cuenta = CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaEntity);

            Cuenta result = cuentaRepository.findById(cuentaId);

            assertEquals(cuenta.getSaldo(), result.getSaldo());
            assertEquals(cuenta.getIban(), result.getIban());
            assertEquals(cuenta.getCliente().getId(), result.getCliente().getId());
            assertEquals(cuenta.getTarjetas().get(0).getId(), result.getTarjetas().get(0).getId());
            assertEquals(cuenta.getMovimientos().get(0).getId(), result.getMovimientos().get(0).getId());
        }
    }

    @Nested
    @DisplayName("findByIban")
    class FindByIbanTest {
        
        @Test
        @DisplayName("Should return an account")
        void shouldReturnAccount() {
            String iban = "ES1234567890123456789012";

            CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(1L, new BigDecimal(100.00), iban, new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token"), List.of(new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe")), List.of(new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto")));

            when(cuentaJpaDao.findByIban(iban)).thenReturn(cuentaJpaEntity);
            
            Cuenta cuenta = CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaEntity);

            Cuenta result = cuentaRepository.findByIban(iban);

            assertEquals(cuenta.getSaldo(), result.getSaldo());
            assertEquals(cuenta.getIban(), result.getIban());
            assertEquals(cuenta.getCliente().getId(), result.getCliente().getId());
            assertEquals(cuenta.getTarjetas().get(0).getId(), result.getTarjetas().get(0).getId());
            assertEquals(cuenta.getMovimientos().get(0).getId(), result.getMovimientos().get(0).getId());
        }
    }

    @Nested
    @DisplayName("findByNumberCard")
    class FindByNumberCardTest {
        
        @Test
        @DisplayName("Should return an account")
        void shouldReturnAccount() {
            String nTarjeta = "1234567890123456";

            CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(1L, new BigDecimal(100.00), "ES1234567890123456789012", new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token"), List.of(new TarjetaCreditoJpaEntity(1L, nTarjeta, "12/12", "123", "Jhon Doe")), List.of(new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto")));
            
            when(cuentaJpaDao.findByNDeTarjeta(nTarjeta)).thenReturn(cuentaJpaEntity);

            Cuenta cuenta = CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaEntity);

            Cuenta result = cuentaRepository.findByNTarjeta(nTarjeta);

            assertEquals(cuenta.getSaldo(), result.getSaldo());
            assertEquals(cuenta.getIban(), result.getIban());
            assertEquals(cuenta.getCliente().getId(), result.getCliente().getId());
            assertEquals(cuenta.getTarjetas().get(0).getId(), result.getTarjetas().get(0).getId());
            assertEquals(cuenta.getMovimientos().get(0).getId(), result.getMovimientos().get(0).getId());
        }
    }

    @Nested
    @DisplayName("findByCustomerId")
    class FindByCustomerIdTest {
        
        @Test
        @DisplayName("Should return an account list")
        void shouldReturnAccountList() {
            Long clienteId = 1L;

            CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(1L, new BigDecimal(100.00), "ES1234567890123456789012", new ClienteJpaEntity(clienteId, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token"), List.of(new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe")), List.of(new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto")));
            
            List<CuentaJpaEntity> cuentaJpaEntityList = List.of(cuentaJpaEntity);
            List<Cuenta> cuentaList = cuentaJpaEntityList.stream().map(CuentaMapper.getInstance()::fromCuentaJpaEntityToCuenta).toList();

            when(cuentaJpaDao.findByClienteId(clienteId)).thenReturn(cuentaJpaEntityList);

            List<Cuenta> result = cuentaRepository.findByClienteId(clienteId);

            assertEquals(cuentaList.get(0).getId(), result.get(0).getId());
        }
    }

    @Nested
    @DisplayName("findAll")
    class FindAllTest {
        
        @Test
        @DisplayName("Should return a list of accounts")
        void shouldReturnAccountList() {
            CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(1L, new BigDecimal(100.00), "ES1234567890123456789012", new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token"), List.of(new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe")), List.of(new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto")));
            List<CuentaJpaEntity> cuentaJpaEntityList = List.of(cuentaJpaEntity);
            List<Cuenta> cuentaList = cuentaJpaEntityList.stream().map(CuentaMapper.getInstance()::fromCuentaJpaEntityToCuenta).toList();

            when(cuentaJpaDao.findAll()).thenReturn(cuentaJpaEntityList);

            List<Cuenta> result = cuentaRepository.findAll();

            assertEquals(cuentaList.get(0).getId(), result.get(0).getId());
        }
    }

    @Nested
    @DisplayName("findByToken")
    class FindByTokenTest {
        
        @Test
        @DisplayName("Should return an account")
        void shouldReturnAccount() {
            String token = "api_token";

            CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(1L, new BigDecimal(100.00), "ES1234567890123456789012", new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", token), List.of(new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe")), List.of(new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto")));
            List<CuentaJpaEntity> cuentaJpaEntityList = List.of(cuentaJpaEntity);
            List<Cuenta> cuentaList = cuentaJpaEntityList.stream().map(CuentaMapper.getInstance()::fromCuentaJpaEntityToCuenta).toList();
            
            when(cuentaJpaDao.findByToken(token)).thenReturn(cuentaJpaEntityList);

            List<Cuenta> result = cuentaRepository.findByToken(token);

            assertEquals(cuentaList.get(0).getId(), result.get(0).getId());
        }
    }

    @Nested
    @DisplayName("save")
    class SaveTest {
        
        @Test
        @DisplayName("Should insert an account if id is null")
        void shouldInsertAccount() {
            Cuenta cuenta = new Cuenta(null, new BigDecimal(100.00), "ES1234567890123456789012", new Cliente(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token"), List.of(new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Jhon Doe")), List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto")));
         
            CuentaJpaEntity cuentaJpaEntity = CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(cuenta);

            when(cuentaJpaDao.insert(any(CuentaJpaEntity.class))).thenReturn(cuentaJpaEntity);

            Cuenta result = cuentaRepository.save(cuenta);

            assertEquals(cuenta.getSaldo(), result.getSaldo());
            assertEquals(cuenta.getIban(), result.getIban());
            assertEquals(cuenta.getCliente().getId(), result.getCliente().getId());
            assertEquals(cuenta.getTarjetas().get(0).getId(), result.getTarjetas().get(0).getId());
            assertEquals(cuenta.getMovimientos().get(0).getId(), result.getMovimientos().get(0).getId());
        }

        @Test
        @DisplayName("Should update an account if id is not null")
        void shouldUpdateAccount() {
            Cuenta cuenta = new Cuenta(1L, new BigDecimal(100.00), "ES1234567890123456789012", new Cliente(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token"), List.of(new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Jhon Doe")), List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto")));
         
            CuentaJpaEntity cuentaJpaEntity = CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(cuenta);
            
            when(cuentaJpaDao.update(any(CuentaJpaEntity.class))).thenReturn(cuentaJpaEntity);

            Cuenta result = cuentaRepository.save(cuenta);

            assertEquals(cuenta.getSaldo(), result.getSaldo());
            assertEquals(cuenta.getIban(), result.getIban());
            assertEquals(cuenta.getCliente().getId(), result.getCliente().getId());
            assertEquals(cuenta.getTarjetas().get(0).getId(), result.getTarjetas().get(0).getId());
            assertEquals(cuenta.getMovimientos().get(0).getId(), result.getMovimientos().get(0).getId());
        }
    }
}
