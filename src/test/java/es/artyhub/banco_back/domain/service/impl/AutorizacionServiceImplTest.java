package es.artyhub.banco_back.domain.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.domain.dto.DestinoDto;
import es.artyhub.banco_back.domain.dto.OrigenDto;
import es.artyhub.banco_back.domain.dto.PagoDto;
import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.Cuenta;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class AutorizacionServiceImplTest {
    
    @Mock
    private CuentaServiceImpl cuentaService;

    @InjectMocks
    private AutorizacionServiceImpl autorizacionService;

    @Nested
    @DisplayName("Test de autorizacion")
    class Autorizacion {
        @Test
        @DisplayName("While pago tarjeta doesn't exist should throw validation exception")
        public void whilePagoTarjetaDoesntExist_ShouldThrowValidationException() {
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(null, null, null, null);
            
            assertThrows(ValidationException.class, () -> autorizacionService.autorizar(pagoTarjetaDto));

            Mockito.verify(cuentaService, never()).findByIban(pagoTarjetaDto.destino().numeroCuenta());
        }

        @Test
        @DisplayName("While numeroCuenta doesn't exist should throw resource not found exception")
        public void whileNumeroCuentaDoesntExist_ShouldThrowResourceNotFoundException() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("numeroCuenta");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            when(cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta())).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta()));

            Mockito.verify(cuentaService, never()).findByIban(pagoTarjetaDto.destino().numeroCuenta());
        }

        @Test
        @DisplayName("While cliente doesn't exist should throw resource not found exception")
        public void whileClienteDoesntExist_ShouldThrowResourceNotFoundException() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("numeroCuenta");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuenta = cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta());

            when(cuenta.getCliente()).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> cuenta.getCliente());

            Mockito.verify(cuenta, never()).getCliente();
        }

        @Test
        @DisplayName("While login doesn't match return false")
        public void whileLoginDoesntMatch_ShouldReturnFalse() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("numeroCuenta");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuenta = cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta());

            Cliente cliente = cuenta.getCliente();

            when(pagoTarjetaDto.autorizacion().login().equals(cliente.getLogin())).thenReturn(false);

            Boolean result = pagoTarjetaDto.autorizacion().login().equals(cliente.getLogin());

            assertFalse(result);

            Mockito.verify(autorizacionService, never()).autorizar(pagoTarjetaDto);
        }

        @Test
        @DisplayName("While api_token doesn't match return false")
        public void whileApiTokenDoesntMatch_ShouldReturnFalse() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("numeroCuenta");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuenta = cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta());

            Cliente cliente = cuenta.getCliente();

            when(pagoTarjetaDto.autorizacion().api_token().equals(cliente.getApi_token())).thenReturn(false);

            Boolean result = pagoTarjetaDto.autorizacion().api_token().equals(cliente.getApi_token());

            assertFalse(result);

            Mockito.verify(autorizacionService, never()).autorizar(pagoTarjetaDto);
        }

        @Test
        @DisplayName("While login matches should return true")
        public void whileLoginMatches_ShouldReturnTrue() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("numeroCuenta");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuenta = cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta());

            Cliente cliente = cuenta.getCliente();

            when(pagoTarjetaDto.autorizacion().login().equals(cliente.getLogin())).thenReturn(true);

            Boolean result = pagoTarjetaDto.autorizacion().login().equals(cliente.getLogin());

            assertTrue(result);

            Mockito.verify(autorizacionService).autorizar(pagoTarjetaDto);
        }
    }
}
