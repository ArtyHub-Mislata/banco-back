package es.artyhub.banco_back.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

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

import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;

@ExtendWith(MockitoExtension.class)
public class TarjetaCreditoServiceImplTest {
    
    @Mock
    private TarjetaCreditoRepository tarjetaCreditoRepository;

    @InjectMocks
    private TarjetaCreditoServiceImpl tarjetaCreditoService;

    @Nested
    @DisplayName("Find tarjeta by id")
    class FindTarjetaById {
        @Test
        @DisplayName("While id doesn't exist should throw validation exception")
        public void whileIdDoesntExist_ShouldThrowValidationException() {
            Long id = null;
            
            assertThrows(ValidationException.class, () -> tarjetaCreditoService.findById(id));

            Mockito.verify(tarjetaCreditoRepository, never()).findById(id);
        }

        @Test
        @DisplayName("While tarjeta doesn't exist should throw resource not found exception")
        public void whileTarjetaDoesntExist_ShouldThrowResourceNotFoundException() {
            Long id = 1L;

            when(tarjetaCreditoRepository.findById(id)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> tarjetaCreditoService.findById(id));
        }

        @Test
        @DisplayName("While tarjeta exists should return tarjeta")
        public void whileTarjetaExists_ShouldReturnTarjeta() {
            TarjetaCredito tarjetaCredito = new TarjetaCredito();
            tarjetaCredito.setId(1L);
            tarjetaCredito.setNumeroTarjeta("numeroTarjeta");
            tarjetaCredito.setFechaCaducidad("12/24");
            tarjetaCredito.setCvv("cvv");

            when(tarjetaCreditoRepository.findById(tarjetaCredito.getId())).thenReturn(tarjetaCredito);
            
            assertEquals(tarjetaCredito, tarjetaCreditoService.findById(tarjetaCredito.getId()));
        }
    }

    @Nested
    @DisplayName("Find tarjeta by número tarjeta")
    class FindTarjetaByNumeroTarjeta {
        @Test
        @DisplayName("While número tarjeta doesn't exist should throw validation exception")
        public void whileNumeroTarjetaDoesntExist_ShouldThrowValidationException() {
            String numeroTarjeta = null;
            
            assertThrows(ValidationException.class, () -> tarjetaCreditoService.findByNumeroTarjeta(numeroTarjeta));

            Mockito.verify(tarjetaCreditoRepository, never()).findByNumeroTarjeta(numeroTarjeta);
        }
        
        @Test
        @DisplayName("While tarjeta exists should return tarjeta")
        public void whileTarjetaExists_ShouldReturnTarjeta() {
            TarjetaCredito tarjetaCredito = new TarjetaCredito();
            tarjetaCredito.setId(1L);
            tarjetaCredito.setNumeroTarjeta("numeroTarjeta");
            tarjetaCredito.setFechaCaducidad("12/24");
            tarjetaCredito.setCvv("cvv");

            when(tarjetaCreditoRepository.findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta())).thenReturn(tarjetaCredito);
            
            assertEquals(tarjetaCredito, tarjetaCreditoService.findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta()));

            Mockito.verify(tarjetaCreditoRepository).findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta());
        }
    }

    @Nested
    @DisplayName("Find tarjetas by cuenta id")
    class FindTarjetasByCuentaId {
        @Test
        @DisplayName("While cuenta id doesn't exist should throw validation exception")
        public void whileCuentaIdDoesntExist_ShouldThrowValidationException() {
            Long cuentaId = null;
            
            assertThrows(ValidationException.class, () -> tarjetaCreditoService.findByCuentaId(cuentaId));
        }

        @Test
        @DisplayName("While tarjeta doesn't exist should throw resource not found exception")
        public void whileTarjetaDoesntExist_ShouldThrowResourceNotFoundException() {
            Long cuentaId = 1L;

            when(tarjetaCreditoRepository.findByCuentaId(cuentaId)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> tarjetaCreditoService.findByCuentaId(cuentaId));
        }

        @Test
        @DisplayName("While tarjeta exists should return list of tarjetas")
        public void whileTarjetaExists_ShouldReturnListOfTarjetas() {
            TarjetaCredito tarjetaCredito = new TarjetaCredito();
            tarjetaCredito.setId(1L);
            tarjetaCredito.setNumeroTarjeta("numeroTarjeta");
            tarjetaCredito.setFechaCaducidad("12/24");
            tarjetaCredito.setCvv("cvv");

            List<TarjetaCredito> tarjetas = new ArrayList<>();
            tarjetas.add(tarjetaCredito);

            when(tarjetaCreditoRepository.findByCuentaId(tarjetaCredito.getId())).thenReturn(tarjetas);
            
            assertEquals(tarjetas, tarjetaCreditoService.findByCuentaId(tarjetaCredito.getId()));
        }
    }

    @Nested
    @DisplayName("Find all tarjetas")
    class FindAllTarjetas {
        @Test
        @DisplayName("While tarjetas doesn't exist should throw resource not found exception")
        public void whileTarjetasDoesntExist_ShouldThrowResourceNotFoundException() {
            when(tarjetaCreditoRepository.findAll()).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> tarjetaCreditoService.findAll());

            Mockito.verify(tarjetaCreditoRepository).findAll();
        }

        @Test
        @DisplayName("While tarjetas exists should return tarjetas")
        public void whileTarjetasExists_ShouldReturnTarjetas() {
            TarjetaCredito tarjetaCredito = new TarjetaCredito();
            tarjetaCredito.setId(1L);
            tarjetaCredito.setNumeroTarjeta("numeroTarjeta");
            tarjetaCredito.setFechaCaducidad("12/24");
            tarjetaCredito.setCvv("cvv");

            List<TarjetaCredito> tarjetas = new ArrayList<>();
            tarjetas.add(tarjetaCredito);

            when(tarjetaCreditoRepository.findAll()).thenReturn(tarjetas);
            
            assertEquals(tarjetas, tarjetaCreditoService.findAll());
        }
    }
}
