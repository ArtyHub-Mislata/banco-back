package es.artyhub.banco_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;

public class ClienteMapperTest {
    
    @Nested
    @DisplayName("Test fromClienteJpaEntityToCliente")
    class FromClienteJpaEntityToClienteTest {

        @Test
        @DisplayName("Test fromClienteJpaEntityToCliente with null ClienteJpaEntity should return null")
        void testFromClienteJpaEntityToCliente_NullInput() {
            Cliente result = ClienteMapper.getInstance().fromClienteJpaEntityToCliente(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromClienteJpaEntityToCliente with valid ClienteJpaEntity should return ClienteDto")
        void testFromClienteJpaEntityToCliente_ValidInput() {
            ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity(
                    1L,
                    "login",
                    "password",
                    "name",
                    "lastName1",
                    "lastName2",
                    "dni",
                    "apiToken"
            );

            Cliente cliente = ClienteMapper.getInstance().fromClienteJpaEntityToCliente(clienteJpaEntity);

            assertAll(
                    () -> assertNotNull(cliente),
                    () -> assertEquals(1L, cliente.getId()),
                    () -> assertEquals("login", cliente.getLogin()),
                    () -> assertEquals("password", cliente.getPassword()),
                    () -> assertEquals("name", cliente.getName()),
                    () -> assertEquals("lastName1", cliente.getLastName1()),
                    () -> assertEquals("lastName2", cliente.getLastName2()),
                    () -> assertEquals("dni", cliente.getDni()),
                    () -> assertEquals("apiToken", cliente.getApi_token()));
        }
    }

    @Nested
    @DisplayName("Test fromClienteToClienteJpaEntity")
    class FromClienteToClienteJpaEntityTest {

        @Test
        @DisplayName("Test fromClienteToClienteJpaEntity with null ClienteDto should return null")
        void testFromClienteToClienteJpaEntity_NullInput() {
            ClienteJpaEntity result = ClienteMapper.getInstance().fromClienteToClienteJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromClienteToClienteJpaEntity with valid ClienteDto should return ClienteJpaEntity")
        void testFromClienteToClienteJpaEntity_ValidInput() {
            Cliente cliente = new Cliente(
                    1L,
                    "login",
                    "password",
                    "name",
                    "lastName1",
                    "lastName2",
                    "dni",
                    "apiToken"
            );

            ClienteJpaEntity clienteJpaEntity = ClienteMapper.getInstance().fromClienteToClienteJpaEntity(cliente);

            assertAll(
                    () -> assertNotNull(clienteJpaEntity),
                    () -> assertEquals(1L, clienteJpaEntity.getId()),
                    () -> assertEquals("login", clienteJpaEntity.getLogin()),
                    () -> assertEquals("password", clienteJpaEntity.getPassword()),
                    () -> assertEquals("name", clienteJpaEntity.getName()),
                    () -> assertEquals("lastName1", clienteJpaEntity.getLastName1()),
                    () -> assertEquals("lastName2", clienteJpaEntity.getLastName2()),
                    () -> assertEquals("dni", clienteJpaEntity.getDni()),
                    () -> assertEquals("apiToken", clienteJpaEntity.getApiToken()));
        }
    }
}
