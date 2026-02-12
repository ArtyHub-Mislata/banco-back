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
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.AuthService;
import es.artyhub.banco_back.domain.service.CuentaService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@WebMvcTest(CuentaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CuentaService cuentaService;

    @MockitoBean
    private AuthService authService;

    @Nested
    @DisplayName("Test getAllCuentas")
    public class TestGetAllCuentas {
        @Test
        @DisplayName("Should return all accounts if token is valid")
        public void shouldReturnAllAccounts_IfTokenIsValid() throws Exception {
            String token = "token";

            Cliente cliente = new Cliente(1L, "login", "password", "nombre", "apellido1", "apellido2", "dni", token);

            List<TarjetaCredito> tarjetas = List.of( new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Juan Perez"));

            List<MovimientoBancario> movimientos = List.of( new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "Concepto"));

            Cuenta cuenta = new Cuenta(1L, new BigDecimal(100), "ES1234567890123456789012", cliente, tarjetas, movimientos);
            List<Cuenta> cuentas = List.of(cuenta);

            when(cuentaService.findByToken(token)).thenReturn(cuentas);
            
            mockMvc.perform(get("/api/customer/accounts")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].iban").value("ES1234567890123456789012"))
                .andExpect(jsonPath("$.[0].saldo").value(new BigDecimal(100)))
                .andExpect(jsonPath("$.[0].cliente.id").value(1));
        }
    }

    @Nested
    @DisplayName("Test getCuentaById")
    public class TestGetCuentaById {
        @Test
        @DisplayName("Should return the account if the id is valid")
        public void shouldReturnTheAccount_IfTheIdIsValid() throws Exception {
            Long cuentaId = 1L;

            Cliente cliente = new Cliente();

            List<TarjetaCredito> tarjetas = List.of( new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Juan Perez"));

            List<MovimientoBancario> movimientos = List.of( new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new Date(), new BigDecimal(100), "Concepto"));

            Cuenta cuenta = new Cuenta(cuentaId, new BigDecimal(100), "ES1234567890123456789012", cliente, tarjetas, movimientos);

            when(cuentaService.findById(cuentaId)).thenReturn(cuenta);
            
            mockMvc.perform(get("/api/customer/accounts/" + cuentaId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.iban").value("ES1234567890123456789012"))
                .andExpect(jsonPath("$.saldo").value(100.0));
        }
    }
}
