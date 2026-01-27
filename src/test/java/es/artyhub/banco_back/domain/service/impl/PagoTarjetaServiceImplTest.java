package es.artyhub.banco_back.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.domain.dto.DestinoDto;
import es.artyhub.banco_back.domain.dto.OrigenDto;
import es.artyhub.banco_back.domain.dto.PagoDto;
import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.AutorizacionService;
import es.artyhub.banco_back.domain.service.CuentaService;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;

@ExtendWith(MockitoExtension.class)
public class PagoTarjetaServiceImplTest {
    
    @Mock
    private AutorizacionService autorizacionService;
    @Mock
    private CuentaService cuentaService;
    @Mock
    private MovimientoBancarioService movimientoBancarioService;
    @Mock
    private TarjetaCreditoService tarjetaCreditoService;

    @InjectMocks
    private PagoTarjetaServiceImpl pagoTarjetaService;

    @Nested
    @DisplayName("Save pago tarjeta")
    class SavePagoTarjeta {
        @Test
        @DisplayName("While pago tarjeta doesn't exist should throw validation exception")
        public void whilePagoTarjetaDoesntExist_ShouldThrowValidationException() {

            PagoTarjetaDto pagoTarjetaDto = null;
            
            assertThrows(ValidationException.class, () -> pagoTarjetaService.save(pagoTarjetaDto));

            Mockito.verify(pagoTarjetaService, never()).save(pagoTarjetaDto);
        }

        @Test
        @DisplayName("While tarjeta crédito doesn't exist should throw validation exception")
        public void whileTarjetaCreditoDoesntExist_ShouldThrowValidationException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);
            
            when(tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta())).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta()));

            Mockito.verify(tarjetaCreditoService, never()).findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());
        }

        @Test
        @DisplayName("While tarjeta crédito es invalida should throw business exception")
        public void whileTarjetaCreditoEsInvalida_ShouldThrowBusinessException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            TarjetaCredito tarjetaCredito = tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());
            
            when(tarjetaCreditoService.tarjetaIsValid(pagoTarjetaDto.origen(), tarjetaCredito)).thenReturn(false);
            
            assertThrows(BusinessException.class, () -> tarjetaCreditoService.tarjetaIsValid(pagoTarjetaDto.origen(), tarjetaCredito));

            Mockito.verify(tarjetaCreditoService, never()).tarjetaIsValid(pagoTarjetaDto.origen(), tarjetaCredito);
        }

        @Test
        @DisplayName("While cuenta origen es nula should throw resource not found exception")
        public void whileCuentaOrigenEsNula_ShouldThrowResourceNotFoundException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            TarjetaCredito tarjetaCredito = tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());
            
            when(cuentaService.findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta())).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> cuentaService.findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta()));

            Mockito.verify(cuentaService, never()).findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta());
        }

        @Test
        @DisplayName("While cuenta destino es nula should throw resource not found exception")
        public void whileCuentaDestinoEsNula_ShouldThrowResourceNotFoundException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);
            
            when(cuentaService.findByIban(pagoTarjetaDto.destino().iban())).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> cuentaService.findByIban(pagoTarjetaDto.destino().iban()));

            Mockito.verify(cuentaService, never()).findByIban(pagoTarjetaDto.destino().iban());
        }

        @Test
        @DisplayName("While autorizacion isn't valid should throw business exception")
        public void whileAutorizacionIsntValid_ShouldThrowBusinessException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);
            
            when(autorizacionService.autorizar(pagoTarjetaDto)).thenReturn(false);
            
            assertThrows(BusinessException.class, () -> autorizacionService.autorizar(pagoTarjetaDto));

            Mockito.verify(autorizacionService, never()).autorizar(pagoTarjetaDto);
        }

        @Test
        @DisplayName("While pago tarjeta is valid should save movimiento bancario")
        public void whilePagoTarjetaIsValid_ShouldSaveMovimientoBancario() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            TarjetaCredito tarjetaCredito = tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());

            Cuenta cuentaOrigen = cuentaService.findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta());

            Cuenta cuentaDestino = cuentaService.findByIban(pagoTarjetaDto.destino().iban());

            MovimientoBancario movimientoBancarioDebe = new MovimientoBancario();
            movimientoBancarioDebe.setConcepto(pagoTarjetaDto.pago().concepto());
            movimientoBancarioDebe.setFecha(new Date());
            movimientoBancarioDebe.setOrigenMovimiento(OrigenMovimiento.TARJETABANCARIA);
            movimientoBancarioDebe.setImporte(pagoTarjetaDto.pago().importe());
            movimientoBancarioDebe.setTarjetaCredito(tarjetaCredito);
            
            MovimientoBancario movimientoBancarioHaber = new MovimientoBancario();
            movimientoBancarioHaber.setConcepto(pagoTarjetaDto.pago().concepto());
            movimientoBancarioHaber.setFecha(new Date());
            movimientoBancarioHaber.setOrigenMovimiento(OrigenMovimiento.TRANSFERENCIA);
            movimientoBancarioHaber.setImporte(pagoTarjetaDto.pago().importe());
            movimientoBancarioHaber.setTarjetaCredito(null);

            cuentaService.updateSaldo(cuentaOrigen, pagoTarjetaDto.pago().importe(), TipoMovimiento.DEBE);
            cuentaService.updateSaldo(cuentaDestino, pagoTarjetaDto.pago().importe(), TipoMovimiento.HABER);

            when(movimientoBancarioService.saveMovimientoBancario(movimientoBancarioDebe, cuentaOrigen.getId())).thenReturn(movimientoBancarioDebe);
            when(movimientoBancarioService.saveMovimientoBancario(movimientoBancarioHaber, cuentaDestino.getId())).thenReturn(movimientoBancarioHaber);

            assertEquals(movimientoBancarioDebe, movimientoBancarioService.saveMovimientoBancario(movimientoBancarioDebe, cuentaOrigen.getId()));
            assertEquals(movimientoBancarioHaber, movimientoBancarioService.saveMovimientoBancario(movimientoBancarioHaber, cuentaDestino.getId()));

            Mockito.verify(movimientoBancarioService, times(2)).saveMovimientoBancario(any(MovimientoBancario.class), any(Long.class));
        }
    }
}
