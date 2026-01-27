package es.artyhub.banco_back.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

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
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Nested
    @DisplayName("Find cliente by id")
    class FindClienteById {
        @Test
        @DisplayName("While id doesn't exist should throw validation exception")
        public void whileIdDoesntExist_ShouldThrowValidationException() {
            Long id = null;
            
            assertThrows(ValidationException.class, () -> clienteService.findById(id));

            Mockito.verify(clienteRepository, never()).findById(id);
        }

        @Test
        @DisplayName("While cliente doesn't exist should throw resource not found exception")
        public void whileClienteDoesntExist_ShouldThrowResourceNotFoundException() {
            Long id = 1L;

            when(clienteRepository.findById(id)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> clienteService.findById(id));

            Mockito.verify(clienteRepository).findById(id);
        }

        @Test
        @DisplayName("While cliente exists should return cliente")
        public void whileClienteExists_ShouldReturnCliente() {
            Cliente clienteMock = Mockito.mock(Cliente.class);

            Long id = 1L;

            when(clienteRepository.findById(id)).thenReturn(clienteMock);
            
            assertEquals(clienteMock, clienteService.findById(id));

            Mockito.verify(clienteRepository).findById(id);
        }
    }

    @Nested
    @DisplayName("Find cliente by login")
    class FindClienteByLogin {
        @Test
        @DisplayName("While login doesn't exist should throw validation exception")
        public void whileLoginDoesntExist_ShouldThrowValidationException() {
            String login = null;
            
            assertThrows(ValidationException.class, () -> clienteService.findByLogin(login));

            Mockito.verify(clienteRepository, never()).findByLogin(login);
        }

        @Test
        @DisplayName("While cliente doesn't exist should throw resource not found exception")
        public void whileClienteDoesntExist_ShouldThrowResourceNotFoundException() {
            String login = "login";

            when(clienteRepository.findByLogin(login)).thenReturn(null);
            
            assertThrows(ResourceNotFoundException.class, () -> clienteService.findByLogin(login));

            Mockito.verify(clienteRepository).findByLogin(login);
        }

        @Test
        @DisplayName("While cliente exists should return cliente")
        public void whileClienteExists_ShouldReturnCliente() {
            Cliente clienteMock = Mockito.mock(Cliente.class);

            String login = "login";

            when(clienteRepository.findByLogin(login)).thenReturn(clienteMock);
            
            assertEquals(clienteMock, clienteService.findByLogin(login));

            Mockito.verify(clienteRepository).findByLogin(login);
        }
    }
}
