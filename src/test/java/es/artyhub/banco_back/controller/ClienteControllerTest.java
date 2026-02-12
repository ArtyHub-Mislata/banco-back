package es.artyhub.banco_back.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.service.AuthService;
import es.artyhub.banco_back.domain.service.ClienteService;

@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClienteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @MockitoBean
    private AuthService authService;

    @Nested
    @DisplayName("Test getCliente")
    public class TestGetCliente {
        @Test
        @DisplayName("Debe devolver el cliente si el token es válido")
        public void testGetClienteValido() throws Exception {
            String token = "token";
            Cliente cliente = new Cliente(1L, "login", "password", "nombre", "apellido1", "apellido2", "dni", token);

            when(authService.getClienteByToken(token)).thenReturn(cliente);

            mockMvc.perform(get("/api/customer")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.login").value("login"))
                .andExpect(jsonPath("$.password").value("password"))
                .andExpect(jsonPath("$.name").value("nombre"))
                .andExpect(jsonPath("$.lastName1").value("apellido1"))
                .andExpect(jsonPath("$.lastName2").value("apellido2"))
                .andExpect(jsonPath("$.dni").value("dni"))
                .andExpect(jsonPath("$.api_token").value(token));
        }
    }
}
