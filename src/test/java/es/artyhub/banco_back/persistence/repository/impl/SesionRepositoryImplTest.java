package es.artyhub.banco_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.repository.mapper.ClienteMapper;

@ExtendWith(MockitoExtension.class)
public class SesionRepositoryImplTest {
    
    @Mock
    private SesionJpaDao sesionJpaDao;

    @InjectMocks
    private SesionRepositoryImpl sesionRepository;

    @Nested
    @DisplayName("logout")
    class LogoutTest {
        
        @Test
        @DisplayName("Should logout")
        void shouldLogout() {
            String token = "token";

            sesionRepository.logout(token);

            verify(sesionJpaDao, times(1)).deleteSesion(token);
        }
    }

    @Nested
    @DisplayName("findByToken")
    class FindByTokenTest {
        
        @Test
        @DisplayName("Should return a customer")
        void shouldReturnCustomer() {
            String token = "token";

            ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", token);

            when(sesionJpaDao.findByToken(token)).thenReturn(Optional.of(clienteJpaEntity));
            
            Cliente cliente = ClienteMapper.getInstance().fromClienteJpaEntityToCliente(clienteJpaEntity);

            Cliente result = sesionRepository.findByToken(token);

            assertEquals(cliente.getId(), result.getId());
            assertEquals(cliente.getLogin(), result.getLogin());
        }
    }

    @Nested
    @DisplayName("insertSesion")
    class InsertSesionTest {
        
        @Test
        @DisplayName("Should insert a movement if id is null")
        void shouldInsertMovement() {
            Long userId = 1L;

            when(sesionJpaDao.createSession(userId)).thenReturn("token");

            String result = sesionRepository.insertSesion(userId);

            assertEquals("token", result);
        }
    }
}
