package es.artyhub.banco_back.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
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
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.AuthService;
import es.artyhub.banco_back.domain.service.AutorizacionService;
import es.artyhub.banco_back.domain.service.CuentaService;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;

@ExtendWith(MockitoExtension.class)
public class PagoTarjetaServiceImplTest {
    
    @Mock
    private AutorizacionService autorizacionService;

    @Mock
    private AuthService authService;

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
        @DisplayName("While tarjeta crédito is invalid should throw business exception")
        public void whileTarjetaCreditoIsInvalid_ShouldThrowBusinessException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            TarjetaCredito tarjetaCredito = tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());
            
            when(tarjetaCreditoService.tarjetaIsValid(pagoTarjetaDto.origen(), tarjetaCredito)).thenReturn(false);
            
            assertThrows(BusinessException.class, () -> pagoTarjetaService.save(pagoTarjetaDto));
        }

        @Test
        @DisplayName("While login cuenta destino doesn't match with autorizacion login should throw business exception")
        public void whileLoginCuentaDestinoDoesntMatch_ShouldThrowBusinessException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            TarjetaCredito tarjetaCredito = new TarjetaCredito(1L, "1234567890123456", "12/24", "123", "nombre_completo");

            when(tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta())).thenReturn(tarjetaCredito);

            Cliente cliente = new Cliente(1L, "loginO", "api_tokenO", "nombre_completo", "email", "password", "telefono", "direccion");
            List<TarjetaCredito> tarjetas = List.of(tarjetaCredito);
            List<MovimientoBancario> movimientos = List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "concepto"));
            Cuenta cuentaOrigen = new Cuenta(1L, new BigDecimal(1000), "ES1234567890123456789013", cliente, tarjetas, movimientos);

            Cliente clienteDestino = new Cliente(1L, "loginD", "api_tokenD", "nombre_completo", "email", "password", "telefono", "direccion");
            List<TarjetaCredito> tarjetasDestino = List.of(tarjetaCredito);
            List<MovimientoBancario> movimientosDestino = List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "concepto"));
            Cuenta cuentaDestino = new Cuenta(1L, new BigDecimal(1000), "ES1234567890123456789013", clienteDestino, tarjetasDestino, movimientosDestino);

            assertThrows(BusinessException.class, () -> pagoTarjetaService.save(pagoTarjetaDto));
        }

        @Test
        @DisplayName("While saldo cuenta isn't enough should throw business exception")
        public void whileSaldoCuentaIsntEnough_ShouldThrowBusinessException() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            TarjetaCredito tarjetaCredito = new TarjetaCredito(1L, "1234567890123456", "12/24", "123", "nombre_completo");

            Cliente cliente = new Cliente(1L, "loginO", "api_tokenO", "nombre_completo", "email", "password", "telefono", "direccion");
            List<TarjetaCredito> tarjetas = List.of(tarjetaCredito);
            List<MovimientoBancario> movimientos = List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "concepto"));
            Cuenta cuentaOrigen = new Cuenta(1L, new BigDecimal(10), "ES1234567890123456789013", cliente, tarjetas, movimientos);

            Cliente clienteDestino = new Cliente(1L, "login", "api_token", "nombre_completo", "email", "password", "telefono", "direccion");
            List<TarjetaCredito> tarjetasDestino = List.of(tarjetaCredito);
            List<MovimientoBancario> movimientosDestino = List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "concepto"));
            Cuenta cuentaDestino = new Cuenta(1L, new BigDecimal(1000), "ES1234567890123456789013", clienteDestino, tarjetasDestino, movimientosDestino);

            
            assertThrows(BusinessException.class, () -> pagoTarjetaService.save(pagoTarjetaDto));
        }

        @Test
        @DisplayName("While pago tarjeta is valid should save movimiento bancario")
        public void whilePagoTarjetaIsValid_ShouldSaveMovimientoBancario() {

            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
            OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "nombre_completo");
            DestinoDto destinoDto = new DestinoDto("iban");
            PagoDto pagoDto = new PagoDto(new BigDecimal(100), "concepto");
            
            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, pagoDto);

            TarjetaCredito tarjetaCredito = new TarjetaCredito(1L, "1234567890123456", "12/24", "123", "nombre_completo");

            Cliente cliente = new Cliente(1L, "loginD", "api_tokenD", "nombre_completo", "email", "password", "telefono", "direccion");
            List<TarjetaCredito> tarjetas = List.of(tarjetaCredito);
            List<MovimientoBancario> movimientos = List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "concepto"));
            Cuenta cuentaOrigen = new Cuenta(1L, new BigDecimal(1000), "ES1234567890123456789013", cliente, tarjetas, movimientos);

            Cliente clienteDestino = new Cliente(1L, "login", "api_token", "nombre_completo", "email", "password", "telefono", "direccion");
            List<TarjetaCredito> tarjetasDestino = List.of(tarjetaCredito);
            List<MovimientoBancario> movimientosDestino = List.of(new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "concepto"));
            Cuenta cuentaDestino = new Cuenta(1L, new BigDecimal(1000), "ES1234567890123456789013", clienteDestino, tarjetasDestino, movimientosDestino);

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
