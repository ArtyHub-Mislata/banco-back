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
import static org.mockito.ArgumentMatchers.any;
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

            Mockito.verify(cuentaService, never()).findByIban(any());
        }

        @Test
        @DisplayName("While numeroCuenta doesn't exist should throw resource not found exception")
        public void whileNumeroCuentaDoesntExist_ShouldThrowResourceNotFoundException() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("123456789012345678901234");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            when(cuentaService.findByIban(pagoTarjetaDto.destino().iban())).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> autorizacionService.autorizar(pagoTarjetaDto));

            Mockito.verify(cuentaService).findByIban(pagoTarjetaDto.destino().iban());
        }

        @Test
        @DisplayName("While cliente doesn't exist should throw resource not found exception")
        public void whileClienteDoesntExist_ShouldThrowResourceNotFoundException() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("123456789012345678901234");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuentaMock = Mockito.mock(Cuenta.class);

            when(cuentaService.findByIban(pagoTarjetaDto.destino().iban())).thenReturn(cuentaMock);

            when(cuentaMock.getCliente()).thenReturn(null);

            assertThrows(ResourceNotFoundException.class, () -> autorizacionService.autorizar(pagoTarjetaDto));

            Mockito.verify(cuentaService).findByIban(pagoTarjetaDto.destino().iban());
            Mockito.verify(cuentaMock).getCliente();
        }

        @Test
        @DisplayName("While login doesn't match return false")
        public void whileLoginDoesntMatch_ShouldReturnFalse() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("1234567890123456", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("123456789012345678901234");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuentaMock = Mockito.mock(Cuenta.class);

            when(cuentaService.findByIban(pagoTarjetaDto.destino().iban())).thenReturn(cuentaMock);

            Cliente clienteMock = Mockito.mock(Cliente.class);

            when(cuentaMock.getCliente()).thenReturn(clienteMock);

            when(clienteMock.getLogin()).thenReturn("otroLogin");

            boolean result = autorizacionService.autorizar(pagoTarjetaDto);

            assertFalse(result);

            Mockito.verify(cuentaService).findByIban(pagoTarjetaDto.destino().iban());
            Mockito.verify(cuentaMock).getCliente();
            Mockito.verify(clienteMock).getLogin();
        }

        @Test
        @DisplayName("While api_token doesn't match return false")
        public void whileApiTokenDoesntMatch_ShouldReturnFalse() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("numeroCuenta");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuentaMock = Mockito.mock(Cuenta.class);

            when(cuentaService.findByIban(pagoTarjetaDto.destino().iban())).thenReturn(cuentaMock);

            Cliente clienteMock = Mockito.mock(Cliente.class);

            when(cuentaMock.getCliente()).thenReturn(clienteMock);

            when(clienteMock.getLogin()).thenReturn("login");
            when(clienteMock.getApi_token()).thenReturn("otroApi_token");

            boolean result = autorizacionService.autorizar(pagoTarjetaDto);

            assertFalse(result);

            Mockito.verify(cuentaService).findByIban(pagoTarjetaDto.destino().iban());
            Mockito.verify(cuentaMock).getCliente();
            Mockito.verify(clienteMock).getLogin();
            Mockito.verify(clienteMock).getApi_token();
        }

        @Test
        @DisplayName("While login matches should return true")
        public void whileLoginMatches_ShouldReturnTrue() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            OrigenDto origenDto = new OrigenDto("numeroTarjeta", "fechaCaducidad", "cvc", "nombreCompleto");

            DestinoDto destinoDto = new DestinoDto("numeroCuenta");

            PagoDto pagoDto = new PagoDto(new BigDecimal(100.0), "concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            Cuenta cuentaMock = Mockito.mock(Cuenta.class);

            when(cuentaService.findByIban(pagoTarjetaDto.destino().iban())).thenReturn(cuentaMock);

            Cliente clienteMock = Mockito.mock(Cliente.class);

            when(cuentaMock.getCliente()).thenReturn(clienteMock);

            when(clienteMock.getLogin()).thenReturn("login");
            when(clienteMock.getApi_token()).thenReturn("api_token");

            boolean result = autorizacionService.autorizar(pagoTarjetaDto);

            assertTrue(result);

            Mockito.verify(cuentaService).findByIban(pagoTarjetaDto.destino().iban());
            Mockito.verify(cuentaMock).getCliente();
            Mockito.verify(clienteMock).getLogin();
            Mockito.verify(clienteMock).getApi_token();
        }
    }
}
