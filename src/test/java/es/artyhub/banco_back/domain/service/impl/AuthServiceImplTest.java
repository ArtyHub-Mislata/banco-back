package es.artyhub.banco_back.domain.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.domain.dto.CredentialsDto;
import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.domain.repository.SesionRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    
    @Mock
    private SesionRepository sesionRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @Nested
    @DisplayName("Test de login")
    class Login {
        @Test
        @DisplayName("While cliente doesn't exist should throw resource not found exception")
        public void whileClienteDoesntExist_ShouldThrowResourceNotFoundException() {
            CredentialsDto credentialsDto = new CredentialsDto("login", "password");

            when(clienteRepository.findByLogin(credentialsDto.username())).thenReturn(null);

            assertThrows(ResourceNotFoundException.class, () -> authService.login(credentialsDto));

            Mockito.verify(clienteRepository).findByLogin(credentialsDto.username());
        }

        @Test
        @DisplayName("While password doesn't match should throw business exception")
        public void whilePasswordDoesntMatch_ShouldThrowBusinessException() {
            CredentialsDto credentialsDto = new CredentialsDto("login", "pass");

            Cliente cliente = new Cliente(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "token");

            when(clienteRepository.findByLogin(credentialsDto.username())).thenReturn(cliente);

            assertThrows(BusinessException.class, () -> authService.login(credentialsDto));

            Mockito.verify(clienteRepository).findByLogin(credentialsDto.username());
        }

        @Test
        @DisplayName("While cliente exists should login")
        public void whileClienteExists_ShouldLogin() {
            CredentialsDto credentialsDto = new CredentialsDto("login", "password");

            Cliente cliente = new Cliente(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "token");

            when(clienteRepository.findByLogin(credentialsDto.username())).thenReturn(cliente);

            when(sesionRepository.insertSesion(cliente.getId())).thenReturn("token");

            String result = authService.login(credentialsDto);

            assertTrue(result != null);

            Mockito.verify(sesionRepository).insertSesion(cliente.getId());
        }
    }

    @Nested
    @DisplayName("Test de logout")
    class Logout {
        @Test
        @DisplayName("While token doesn't exist should throw business exception")
        public void whileTokenDoesntExist_ShouldThrowBusinessException() {
            String token = null;

            assertThrows(BusinessException.class, () -> authService.logout(token));
        }

        @Test
        @DisplayName("While token exists should logout")
        public void whileTokenExists_ShouldLogout() {
            String token = "token";

            authService.logout(token);

            Mockito.verify(sesionRepository).logout(token);
        }
    }

    @Nested
    @DisplayName("Test getClienteByToken")
    class GetClienteByToken {
        @Test
        @DisplayName("While token doesn't exist should throw business exception")
        public void whileTokenDoesntExist_ShouldThrowBusinessException() {
            String token = null;

            assertThrows(BusinessException.class, () -> authService.getClienteByToken(token));
        }

        @Test
        @DisplayName("While token exists should find cliente")
        public void whileTokenExists_ShouldFindCliente() {
            String token = "token";

            Cliente cliente = new Cliente(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "token");

            when(sesionRepository.findByToken(token)).thenReturn(cliente);

            Cliente result = authService.getClienteByToken(token);

            assertTrue(result != null);

            Mockito.verify(sesionRepository).findByToken(token);
        }
    }

    @Nested
    @DisplayName("Test autorizate")
    class Autorizate {
        @Test
        @DisplayName("While token exists should autorizate")
        public void whileTokenExists_ShouldAutorizate() {
            AutorizacionDto autorizacion = new AutorizacionDto("login", "apiKey");

            when(clienteRepository.clienAndApiTokenCorrect(autorizacion)).thenReturn(true);

            assertTrue(authService.autorizar(autorizacion));
        }
    }
}
