package es.artyhub.banco_back.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.repository.CuentaRepository;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceImplTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaServiceImpl cuentaService;
    
    @Nested
    @DisplayName("Find cuenta by id")
    class FindCuentaById {
        @Test
        @DisplayName("While id doesn't exist should throw validation exception")
        public void whileIdDoesntExist_ShouldThrowValidationException() {
            Long id = null;
            
            assertThrows(ValidationException.class, () -> cuentaService.findById(id));

            Mockito.verify(cuentaRepository, never()).findById(id);
        }

        @Test
        @DisplayName("While cuenta doesn't exist should throw resource not found exception")
        public void whileCuentaDoesntExist_ShouldThrowResourceNotFoundException() {
            Long id = 1L;

            when(cuentaRepository.findById(id)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> cuentaService.findById(id));

            Mockito.verify(cuentaRepository).findById(id);
        }

        @Test
        @DisplayName("While cuenta exists should return cuenta")
        public void whileCuentaExists_ShouldReturnCuenta() {
            Cuenta cuenta = new Cuenta();
            cuenta.setId(1L);
            cuenta.setSaldo(new BigDecimal(100.0));
            cuenta.setIban("iban");
            cuenta.setCliente(new Cliente());
            cuenta.setTarjetas(null);
            cuenta.setMovimientos(null);

            when(cuentaRepository.findById(cuenta.getId())).thenReturn(cuenta);
            
            assertEquals(cuenta, cuentaService.findById(cuenta.getId()));

            Mockito.verify(cuentaRepository).findById(cuenta.getId());
        }
    }

    @Nested
    @DisplayName("Find cuenta by iban")
    class FindCuentaByIban {
        @Test
        @DisplayName("While iban doesn't exist should throw validation exception")
        public void whileIbanDoesntExist_ShouldThrowValidationException() {
            String iban = null;
            
            assertThrows(ValidationException.class, () -> cuentaService.findByIban(iban));

            Mockito.verify(cuentaRepository, never()).findByIban(iban);
        }

        @Test
        @DisplayName("While cuenta exists should return cuenta")
        public void whileCuentaExists_ShouldReturnCuenta() {
            Cuenta cuenta = new Cuenta();
            cuenta.setId(1L);
            cuenta.setSaldo(new BigDecimal(100.0));
            cuenta.setIban("iban");
            cuenta.setCliente(new Cliente());
            cuenta.setTarjetas(null);
            cuenta.setMovimientos(null);

            when(cuentaRepository.findByIban(cuenta.getIban())).thenReturn(cuenta);
            
            assertEquals(cuenta, cuentaService.findByIban(cuenta.getIban()));

            Mockito.verify(cuentaRepository).findByIban(cuenta.getIban());
        }
    }

    @Nested
    @DisplayName("Find cuenta by número tarjeta")
    class FindCuentaByNumeroTarjeta {
        @Test
        @DisplayName("While card exists should return cuenta")
        public void whileCardExists_ShouldReturnCuenta() {
            TarjetaCredito tarjetaCredito = new TarjetaCredito();
            tarjetaCredito.setId(1L);
            tarjetaCredito.setNumeroTarjeta("numeroTarjeta");
            tarjetaCredito.setFechaCaducidad("01/01/2025");
            tarjetaCredito.setCvv("cvv");

            List<TarjetaCredito> tarjetas = new ArrayList<>();
            tarjetas.add(tarjetaCredito);

            Cuenta cuenta = new Cuenta();
            cuenta.setId(1L);
            cuenta.setSaldo(new BigDecimal(100.0));
            cuenta.setIban("iban");
            cuenta.setCliente(new Cliente());
            cuenta.setTarjetas(null);
            cuenta.setMovimientos(null);
            cuenta.setTarjetas(tarjetas);

            when(cuentaRepository.findByNTarjeta(cuenta.getTarjetas().get(0).getNumeroTarjeta())).thenReturn(cuenta);
            
            assertEquals(cuenta, cuentaService.findByNumeroTarjeta(cuenta.getTarjetas().get(0).getNumeroTarjeta()));

            Mockito.verify(cuentaRepository).findByNTarjeta(cuenta.getTarjetas().get(0).getNumeroTarjeta());
        }
    }

    @Nested
    @DisplayName("Find cuenta by cliente id")
    class FindCuentaByClienteId {
        @Test
        @DisplayName("While cliente id doesn't exist should throw validation exception")
        public void whileClienteIdDoesntExist_ShouldThrowValidationException() {
            Long clienteId = null;
            
            assertThrows(ValidationException.class, () -> cuentaService.findByClienteId(clienteId));

            Mockito.verify(cuentaRepository, never()).findByClienteId(clienteId);
        }

        @Test
        @DisplayName("While cuenta doesn't exist should throw resource not found exception")
        public void whileCuentaDoesntExist_ShouldThrowResourceNotFoundException() {
            Long clienteId = 1L;

            when(cuentaRepository.findByClienteId(clienteId)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> cuentaService.findByClienteId(clienteId));

            Mockito.verify(cuentaRepository).findByClienteId(clienteId);
        }

        @Test
        @DisplayName("While cuenta exists should return cuenta")
        public void whileCuentaExists_ShouldReturnCuenta() {
            Long clienteId = 1L;

            Cuenta cuentaMock = Mockito.mock(Cuenta.class);

            List<Cuenta> cuentasMock = new ArrayList<>();
            cuentasMock.add(cuentaMock);

            when(cuentaRepository.findByClienteId(clienteId)).thenReturn(cuentasMock);
            
            assertEquals(cuentasMock, cuentaService.findByClienteId(clienteId));

            Mockito.verify(cuentaRepository).findByClienteId(clienteId);
        }
    }

    @Nested
    @DisplayName("Find all cuentas")
    class FindAllCuentas {
        @Test
        @DisplayName("While cuentas doesn't exist should throw resource not found exception")
        public void whileCuentasDoesntExist_ShouldThrowResourceNotFoundException() {
            when(cuentaRepository.findAll()).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> cuentaService.findAll());

            Mockito.verify(cuentaRepository).findAll();
        }

        @Test
        @DisplayName("While cuentas exists should return cuentas")
        public void whileCuentasExists_ShouldReturnCuentas() {
            Cuenta cuentaMock = Mockito.mock(Cuenta.class);

            List<Cuenta> cuentasMock = new ArrayList<>();
            cuentasMock.add(cuentaMock);

            when(cuentaRepository.findAll()).thenReturn(cuentasMock);
            
            assertEquals(cuentasMock, cuentaService.findAll());

            Mockito.verify(cuentaRepository).findAll();
        }
    }

    @Nested
    @DisplayName("Save cuenta")
    class SaveCuenta {
        @Test
        @DisplayName("While cuenta is null should throw validation exception")
        public void whileCuentaIsNull_ShouldThrowValidationException() {
            Cuenta cuenta = null;
            
            assertThrows(ValidationException.class, () -> cuentaService.save(cuenta));

            Mockito.verify(cuentaRepository, never()).save(cuenta);
        }

        @Test
        @DisplayName("While cuenta is valid should return cuenta")
        public void whileCuentaIsValid_ShouldReturnCuenta() {
            Cuenta cuenta = new Cuenta();
            cuenta.setId(1L);
            cuenta.setSaldo(new BigDecimal(100.0));
            cuenta.setIban("iban");
            cuenta.setCliente(new Cliente());
            cuenta.setTarjetas(null);
            cuenta.setMovimientos(null);

            when(cuentaRepository.save(cuenta)).thenReturn(cuenta);
            
            assertEquals(cuenta, cuentaService.save(cuenta));

            Mockito.verify(cuentaRepository).save(cuenta);
        }
    }

    @Nested
    @DisplayName("Update saldo cuenta")
    class UpdateSaldoCuenta {
        @Test
        @DisplayName("While tipo movimiento equals DEBE and saldo cuenta is enough should substract")
        public void whileTipoMovimientoEqualsDebeAndSaldoCuentaIsEnough_ShouldSubstract() {
            Cuenta cuenta = new Cuenta();
            cuenta.setSaldo(new BigDecimal(100.0));

            when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);
            
            cuentaService.updateSaldo(cuenta, new BigDecimal(30.0), TipoMovimiento.DEBE);

            assertEquals(new BigDecimal("70"), cuenta.getSaldo());

            Mockito.verify(cuentaRepository).save(cuenta);
        }

        @Test
        @DisplayName("While tipo movimiento equals HABE and saldo cuenta is enough should add")
        public void whileTipoMovimientoEqualsHabeAndSaldoCuentaIsEnough_ShouldAdd() {
            Cuenta cuenta = new Cuenta();
            cuenta.setSaldo(new BigDecimal(100.0));

            when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);

            cuentaService.updateSaldo(cuenta, new BigDecimal(30.0), TipoMovimiento.HABER);

            assertEquals(new BigDecimal("130"), cuenta.getSaldo());

            Mockito.verify(cuentaRepository).save(cuenta);
        }
    }
}
