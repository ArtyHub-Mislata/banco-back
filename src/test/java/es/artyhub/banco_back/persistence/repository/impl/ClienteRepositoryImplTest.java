package es.artyhub.banco_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;
import es.artyhub.banco_back.persistence.repository.mapper.ClienteMapper;
import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.domain.model.Cliente;

@ExtendWith(MockitoExtension.class)
public class ClienteRepositoryImplTest {
    
    @Mock
    private ClienteJpaDao clienteJpaDao;

    @InjectMocks
    private ClienteRepositoryImpl clienteRepository;

    @Nested
    @DisplayName("findById")
    class FindByIdTest {
        
        @Test
        @DisplayName("Should return a customer")
        void shouldReturnCustomer() {
            Long clienteId = 1L;

            ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token");
            
            when(clienteJpaDao.findById(clienteId)).thenReturn(clienteJpaEntity);

            Cliente cliente = ClienteMapper.getInstance().fromClienteJpaEntityToCliente(clienteJpaEntity);

            Cliente result = clienteRepository.findById(clienteId);

            assertEquals(cliente.getLogin(), result.getLogin());
            assertEquals(cliente.getPassword(), result.getPassword());
            assertEquals(cliente.getName(), result.getName());
            assertEquals(cliente.getLastName1(), result.getLastName1());
            assertEquals(cliente.getLastName2(), result.getLastName2());
            assertEquals(cliente.getDni(), result.getDni());
        }
    }

    @Nested
    @DisplayName("findAll")
    class FindAllTest {
        
        @Test
        @DisplayName("Should return a page of customers")
        void shouldReturnPageOfCustomers() {
            ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token");
            List<ClienteJpaEntity> clienteJpaEntityList = List.of(clienteJpaEntity);
            List<Cliente> clienteList = clienteJpaEntityList.stream().map(ClienteMapper.getInstance()::fromClienteJpaEntityToCliente).toList();

            when(clienteJpaDao.findAll()).thenReturn(clienteJpaEntityList);

            List<Cliente> result = clienteRepository.findAll();

            assertEquals(clienteList.get(0).getId(), result.get(0).getId());
        }
    }

    @Nested
    @DisplayName("findClienteByLogin")
    class FindClienteByLoginTest {
        
        @Test
        @DisplayName("Should return cliente by login")
        void shouldReturnClienteByLogin() {
            String login = "login";

            ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity(1L, "login", "password", "name", "lastName1", "lastName2", "dni", "api_token");

            when(clienteJpaDao.findByLogin(login)).thenReturn(clienteJpaEntity);

            Cliente cliente = ClienteMapper.getInstance().fromClienteJpaEntityToCliente(clienteJpaEntity);

            Cliente result = clienteRepository.findByLogin(login);

            assertEquals(cliente.getId(), result.getId());
        }
    }

    @Nested
    @DisplayName("Cliente and api_token correct")
    class ClienteAndApiTokenCorrectTest {
        
        @Test
        @DisplayName("Should return true if cliente and api_token correct")
        void shouldReturnTrueIfClienteAndApiTokenCorrect() {
            AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

            when(clienteJpaDao.userAndApiTokenCorrect(autorizacionDto)).thenReturn(true);

            boolean result = clienteRepository.clienAndApiTokenCorrect(autorizacionDto);

            assertEquals(true, result);
        }
    }
}
