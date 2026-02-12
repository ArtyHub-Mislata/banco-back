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

import com.fasterxml.jackson.databind.ObjectMapper;

import es.artyhub.banco_back.domain.service.PagoTarjetaService;
import es.artyhub.banco_back.domain.service.PagoTransferenciaService;
import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.domain.dto.DestinoDto;
import es.artyhub.banco_back.domain.dto.OrigenDto;
import es.artyhub.banco_back.domain.dto.OrigenTransferenciaDto;
import es.artyhub.banco_back.domain.dto.PagoDto;
import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.dto.PagoTransferenciaDto;
import es.artyhub.banco_back.domain.service.AuthService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

@WebMvcTest(PagoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PagoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagoTarjetaService pagoTarjetaService;

    @MockitoBean
    private PagoTransferenciaService pagoTransferenciaService;

    @MockitoBean
    private AuthService authService;

    @Nested
    @DisplayName("Test pagarConTarjeta")
    public class TestPagarConTarjeta {
        @Test
        @DisplayName("Debe pagar con tarjeta si el pago es válido")
        public void testPagarConTarjetaValido() throws Exception {
            AutorizacionDto autorization = new AutorizacionDto("login", "token");

            OrigenDto origen = new OrigenDto("1234567890123456", "12/12", "123", "Juan Perez");

            DestinoDto destino = new DestinoDto("ES1234567890123456789012");

            PagoDto pago = new PagoDto(new BigDecimal(100), "Concepto");

            PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(autorization, origen, destino, pago);

            mockMvc.perform(post("/api/pagos/pago_tarjeta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pagoTarjetaDto)))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("Test hacerTransferencia")
    public class TestHacerTransferencia {
        @Test
        @DisplayName("Debe hacer transferencia si la transferencia es válida")
        public void testHacerTransferenciaValido() throws Exception {
            AutorizacionDto autorization = new AutorizacionDto("login", "token");

            OrigenTransferenciaDto origen = new OrigenTransferenciaDto("ES1234567890123456789014");

            DestinoDto destino = new DestinoDto("ES1234567890123456789012");

            PagoDto pago = new PagoDto(new BigDecimal(100), "Concepto");

            PagoTransferenciaDto pagoTransferenciaDto = new PagoTransferenciaDto(autorization, origen, destino, pago);

            mockMvc.perform(post("/api/pagos/transferencia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pagoTransferenciaDto)))
                .andExpect(status().isOk());
        }
    }
}
