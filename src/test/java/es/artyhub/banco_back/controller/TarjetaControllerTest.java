package es.artyhub.banco_back.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.AuthService;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;
import jakarta.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@WebMvcTest(TarjetaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TarjetaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TarjetaCreditoService tarjetaCreditoService;

    @MockitoBean
    private MovimientoBancarioService movimientoBancarioService;

    @MockitoBean
    private AuthService authService;

    @Nested
    @DisplayName("Test getAllTarjetasOfUser")
    public class TestGetAllTarjetasOfUser {
        @Test
        @DisplayName("Debe obtener todas las tarjetas del usuario si es valido")
        public void testGetAllTarjetasOfValidUser() throws Exception {
            String token = "token";

            HttpServletRequest request = mock(HttpServletRequest.class);

            when(request.getHeader("Authorization")).thenReturn(token);

            List<TarjetaCredito> tarjetas = List.of(new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Juan Perez"));

            when(tarjetaCreditoService.findAllOfUser(token)).thenReturn(tarjetas);

            mockMvc.perform(get("/api/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("Test getTarjetaByID")
    public class TestGetTarjetaByID {
        @Test
        @DisplayName("Should return the card when the card exists and belongs to the user")
        public void testGetTarjetaByIDValido() throws Exception {
            Long id = 1L;

            TarjetaCredito tarjeta = new TarjetaCredito(id, "1234567890123456", "12/12", "123", "Juan Perez");

            when(tarjetaCreditoService.findById(id)).thenReturn(tarjeta);

            String token = "token";

            HttpServletRequest request = mock(HttpServletRequest.class);

            when(request.getHeader("Authorization")).thenReturn(token);

            when(tarjetaCreditoService.tarjetaPerteneceAUsuario(id, token)).thenReturn(true);

            mockMvc.perform(get("/api/cards/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Should return not found when card is null")
        public void testGetTarjetaByIDNoExiste() throws Exception {
            Long id = 1L;

            when(tarjetaCreditoService.findById(id)).thenReturn(null);

            mockMvc.perform(get("/api/cards/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Should return unauthorized when card does not belong to the user")
        public void testGetTarjetaByIDNoPerteneceAUsuario() throws Exception {
            Long id = 1L;

            TarjetaCredito tarjeta = new TarjetaCredito(id, "1234567890123456", "12/12", "123", "Juan Perez");

            when(tarjetaCreditoService.findById(id)).thenReturn(tarjeta);

            String token = "token";

            HttpServletRequest request = mock(HttpServletRequest.class);

            when(request.getHeader("Authorization")).thenReturn(token);

            when(tarjetaCreditoService.tarjetaPerteneceAUsuario(id, token)).thenReturn(false);

            mockMvc.perform(get("/api/cards/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    @DisplayName("Test getMovimientosBancariosOfTarjeta")
    public class TestGetMovimientosBancariosOfTarjeta {
        @Test
        @DisplayName("Should return the transactions when the card exists and belongs to the user")
        public void testGetMovimientosBancariosOfTarjetaValido() throws Exception {
            Long id = 1L;

            List<MovimientoBancario> movimientos = List.of(
                new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "Concepto")
            );

            when(movimientoBancarioService.findByTarjetaId(id)).thenReturn(movimientos);

            mockMvc.perform(get("/api/cards/" + id + "/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
    }
}
