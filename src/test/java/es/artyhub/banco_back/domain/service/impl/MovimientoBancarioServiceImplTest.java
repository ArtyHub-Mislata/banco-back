package es.artyhub.banco_back.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;

@ExtendWith(MockitoExtension.class)
public class MovimientoBancarioServiceImplTest {
    
    @Mock
    private MovimientoBancarioRepository movimientoBancarioRepository;

    @InjectMocks
    private MovimientoBancarioServiceImpl movimientoBancarioService;

    @Nested
    @DisplayName("Find movimiento bancario by id")
    class FindMovimientoBancarioById {
        @Test
        @DisplayName("While id doesn't exist should throw validation exception")
        public void whileIdDoesntExist_ShouldThrowValidationException() {
            Long id = null;
            
            assertThrows(ValidationException.class, () -> movimientoBancarioService.findById(id));

            Mockito.verify(movimientoBancarioRepository, never()).findById(id);
        }

        @Test
        @DisplayName("While movimiento bancario doesn't exist should throw resource not found exception")
        public void whileMovimientoBancarioDoesntExist_ShouldThrowResourceNotFoundException() {
            Long id = 1L;

            when(movimientoBancarioRepository.findById(id)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> movimientoBancarioService.findById(id));

            Mockito.verify(movimientoBancarioRepository).findById(id);
        }

        @Test
        @DisplayName("While movimiento bancario exists should return movimiento bancario")
        public void whileMovimientoBancarioExists_ShouldReturnMovimientoBancario() {
            MovimientoBancario movimientoBancario = new MovimientoBancario();
            movimientoBancario.setId(1L);
            movimientoBancario.setTipoMovimiento(null);
            movimientoBancario.setOrigenMovimiento(null);
            movimientoBancario.setTarjetaCredito(null);
            movimientoBancario.setFecha(new Date());
            movimientoBancario.setImporte(new BigDecimal(100.0));
            movimientoBancario.setConcepto("concepto");

            when(movimientoBancarioRepository.findById(movimientoBancario.getId())).thenReturn(movimientoBancario);
            
            assertEquals(movimientoBancario, movimientoBancarioService.findById(movimientoBancario.getId()));

            Mockito.verify(movimientoBancarioRepository).findById(movimientoBancario.getId());
        }
    }

    @Nested
    @DisplayName("Find movimiento bancario by importe")
    class FindMovimientoBancarioByImporte {
        @Test
        @DisplayName("While importe doesn't exist should throw validation exception")
        public void whileImporteDoesntExist_ShouldThrowValidationException() {
            BigDecimal importe = null;
            
            assertThrows(ValidationException.class, () -> movimientoBancarioService.findByImporte(importe));

            Mockito.verify(movimientoBancarioRepository, never()).findByImporte(importe);
        }

        @Test
        @DisplayName("While movimiento bancario doesn't exist should throw resource not found exception")
        public void whileMovimientoBancarioDoesntExist_ShouldThrowResourceNotFoundException() {
            BigDecimal importe = new BigDecimal(100.0);

            when(movimientoBancarioRepository.findByImporte(importe)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> movimientoBancarioService.findByImporte(importe));
        }

        @Test
        @DisplayName("While movimiento bancario exists should return movimiento bancario")
        public void whileMovimientoBancarioExists_ShouldReturnMovimientoBancario() {
            MovimientoBancario movimientoBancario = new MovimientoBancario();
            movimientoBancario.setId(1L);
            movimientoBancario.setTipoMovimiento(null);
            movimientoBancario.setOrigenMovimiento(null);
            movimientoBancario.setTarjetaCredito(null);
            movimientoBancario.setFecha(new Date());
            movimientoBancario.setImporte(new BigDecimal(100.0));
            movimientoBancario.setConcepto("concepto");

            when(movimientoBancarioRepository.findByImporte(movimientoBancario.getImporte())).thenReturn(movimientoBancario);
            
            assertEquals(movimientoBancario, movimientoBancarioService.findByImporte(movimientoBancario.getImporte()));

            Mockito.verify(movimientoBancarioRepository).findByImporte(movimientoBancario.getImporte());
        }
    }

    @Nested
    @DisplayName("Find movimiento bancario by concepto")
    class FindMovimientoBancarioByConcepto {
        @Test
        @DisplayName("While concepto doesn't exist should throw validation exception")
        public void whileConceptoDoesntExist_ShouldThrowValidationException() {
            String concepto = null;
            
            assertThrows(ValidationException.class, () -> movimientoBancarioService.findByConcepto(concepto));

            Mockito.verify(movimientoBancarioRepository, never()).findByConcepto(concepto);
        }

        @Test
        @DisplayName("While movimiento bancario doesn't exist should throw resource not found exception")
        public void whileMovimientoBancarioDoesntExist_ShouldThrowResourceNotFoundException() {
            String concepto = "concepto";

            when(movimientoBancarioRepository.findByConcepto(concepto)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> movimientoBancarioService.findByConcepto(concepto));
        }

        @Test
        @DisplayName("While movimiento bancario exists should return movimiento bancario")
        public void whileMovimientoBancarioExists_ShouldReturnMovimientoBancario() {
            MovimientoBancario movimientoBancario = new MovimientoBancario();
            movimientoBancario.setId(1L);
            movimientoBancario.setTipoMovimiento(null);
            movimientoBancario.setOrigenMovimiento(null);
            movimientoBancario.setTarjetaCredito(null);
            movimientoBancario.setFecha(new Date());
            movimientoBancario.setImporte(new BigDecimal(100.0));
            movimientoBancario.setConcepto("concepto");

            when(movimientoBancarioRepository.findByConcepto(movimientoBancario.getConcepto())).thenReturn(movimientoBancario);
            
            assertEquals(movimientoBancario, movimientoBancarioService.findByConcepto(movimientoBancario.getConcepto()));

            Mockito.verify(movimientoBancarioRepository).findByConcepto(movimientoBancario.getConcepto());
        }
    }

    @Nested
    @DisplayName("Find movimiento bancario by cuenta_id")
    class FindMovimientoBancarioByCuentaId {
        @Test
        @DisplayName("While id doesn't exist should throw validation exception")
        public void whileIdDoesntExist_ShouldThrowValidationException() {
            Long id = null;
            
            assertThrows(ValidationException.class, () -> movimientoBancarioService.findByCuentaId(id));

            Mockito.verify(movimientoBancarioRepository, never()).findById(id);
        }

        @Test
        @DisplayName("While movimiento bancario doesn't exist should throw resource not found exception")
        public void whileMovimientoBancarioDoesntExist_ShouldThrowResourceNotFoundException() {
            Long id = 1L;

            when(movimientoBancarioRepository.findByCuentaId(id)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> movimientoBancarioService.findByCuentaId(id));
        }

        @Test
        @DisplayName("While movimiento bancario exists should return movimiento bancario")
        public void whileMovimientoBancarioExists_ShouldReturnMovimientoBancario() {
            MovimientoBancario movimientoBancario = new MovimientoBancario();
            movimientoBancario.setId(1L);
            movimientoBancario.setTipoMovimiento(null);
            movimientoBancario.setOrigenMovimiento(null);
            movimientoBancario.setTarjetaCredito(null);
            movimientoBancario.setFecha(new Date());
            movimientoBancario.setImporte(new BigDecimal(100.0));
            movimientoBancario.setConcepto("concepto");

            List<MovimientoBancario> movimientosBancarios = new ArrayList<>();
            movimientosBancarios.add(movimientoBancario);

            when(movimientoBancarioRepository.findByCuentaId(movimientoBancario.getId())).thenReturn(movimientosBancarios);
            
            assertEquals(movimientosBancarios, movimientoBancarioService.findByCuentaId(movimientoBancario.getId()));

            Mockito.verify(movimientoBancarioRepository).findByCuentaId(movimientoBancario.getId());
        }
    }

    @Nested
    @DisplayName("Find all movimiento bancarios")
    class FindAllMovimientoBancarios {
        @Test
        @DisplayName("While movimiento bancarios doesn't exist should throw resource not found exception")
        public void whileMovimientoBancariosDoesntExist_ShouldThrowResourceNotFoundException() {
            when(movimientoBancarioRepository.findAll()).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> movimientoBancarioService.findAll());
        }

        @Test
        @DisplayName("While movimiento bancarios exists should return movimiento bancarios")
        public void whileMovimientoBancariosExists_ShouldReturnMovimientoBancarios() {
            MovimientoBancario movimientoBancario = new MovimientoBancario();
            movimientoBancario.setId(1L);
            movimientoBancario.setTipoMovimiento(null);
            movimientoBancario.setOrigenMovimiento(null);
            movimientoBancario.setTarjetaCredito(null);
            movimientoBancario.setFecha(new Date());
            movimientoBancario.setImporte(new BigDecimal(100.0));
            movimientoBancario.setConcepto("concepto");

            List<MovimientoBancario> movimientoBancarios = new ArrayList<>();
            movimientoBancarios.add(movimientoBancario);

            when(movimientoBancarioRepository.findAll()).thenReturn(movimientoBancarios);
            
            assertEquals(movimientoBancarios, movimientoBancarioService.findAll());

            Mockito.verify(movimientoBancarioRepository).findAll();
        }
    }

    @Nested
    @DisplayName("Save movimiento bancario")
    class SaveMovimientoBancario {
        @Test
        @DisplayName("While movimiento bancario is valid should return movimiento bancario")
        public void whileMovimientoBancarioIsValid_ShouldReturnMovimientoBancario() {
            MovimientoBancario movimientoBancario = new MovimientoBancario();
            movimientoBancario.setId(1L);
            movimientoBancario.setTipoMovimiento(null);
            movimientoBancario.setOrigenMovimiento(null);
            movimientoBancario.setTarjetaCredito(null);
            movimientoBancario.setFecha(new Date());
            movimientoBancario.setImporte(new BigDecimal(100.0));
            movimientoBancario.setConcepto("concepto");

            when(movimientoBancarioRepository.save(movimientoBancario, 1L)).thenReturn(movimientoBancario);
            
            assertEquals(movimientoBancario, movimientoBancarioService.saveMovimientoBancario(movimientoBancario, 1L));

            Mockito.verify(movimientoBancarioRepository).save(movimientoBancario, 1L);
        }
    }
}
