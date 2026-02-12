package es.artyhub.banco_back.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

import es.artyhub.banco_back.domain.dto.CredentialsDto;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;
    
    @Nested
    @DisplayName("Login test")
    public class LoginTest {
        @Test
        @DisplayName("Login with valid credentials should login")
        public void testLoginWithValidCredentials_ShouldLogin() throws Exception {
            CredentialsDto credentials = new CredentialsDto("username", "password");

            String token = "token";

            when(authService.login(credentials)).thenReturn(token);

            mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token));
        }
    }

    @Nested
    @DisplayName("Logout test")
    public class LogoutTest {
        @Test
        @DisplayName("Logout with valid token should logout")
        public void testLogoutWithValidToken_ShouldLogout() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getHeader("Authorization")).thenReturn("token");

            mockMvc.perform(delete("/api/logout")
                .header("Authorization", "Bearer token-valido")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("IsLogged test")
    public class IsLoggedTest {
        @Test
        @DisplayName("should return true if user is logged")
        public void shouldReturnTrue_IfUserIsLogged() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            String token = "token";
            when(request.getHeader("Authorization")).thenReturn(token);

            Cliente cliente = new Cliente(1L, "login", "password", "nombre", "apellido1", "apellido2", "dni", token);

            when(authService.getClienteByToken(token)).thenReturn(cliente);

            mockMvc.perform(get("/api/islogged")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("should return false if user is not logged")
        public void shouldReturnFalse_IfUserIsNotLogged() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            String token = "token";

            when(request.getHeader("Authorization")).thenReturn(token);

            when(authService.getClienteByToken(token)).thenReturn(null);

            mockMvc.perform(get("/api/islogged")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("should return false if token is null")
        public void shouldReturnFalse_IfTokenIsNull() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            
            when(request.getHeader("Authorization")).thenReturn(null);

            mockMvc.perform(get("/api/islogged")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        }
    }
}
