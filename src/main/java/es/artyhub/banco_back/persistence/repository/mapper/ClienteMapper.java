package es.artyhub.banco_back.persistence.repository.mapper;

import java.util.List;

import es.artyhub.banco_back.domain.model.Cliente;

import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;

public class ClienteMapper {
    private static ClienteMapper instance;

    public ClienteMapper() {
    }

    public static ClienteMapper getInstance() {
        if (instance == null) {
            instance = new ClienteMapper();
        }
        return instance;
    }

    public Cliente fromClienteJpaEntityToCliente(ClienteJpaEntity clienteJpaEntity) {
        if (clienteJpaEntity == null) {
            return null;
        }
        return new Cliente(
            clienteJpaEntity.getId(), 
            clienteJpaEntity.getLogin(), 
            clienteJpaEntity.getPassword(), 
            clienteJpaEntity.getName(), 
            clienteJpaEntity.getLastName1(), 
            clienteJpaEntity.getLastName2(), 
            clienteJpaEntity.getDni(), 
            clienteJpaEntity.getApiToken());
    }

    public ClienteJpaEntity fromClienteToClienteJpaEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteJpaEntity(
            cliente.getId(), 
            cliente.getLogin(), 
            cliente.getPassword(), 
            cliente.getName(), 
            cliente.getLastName1(), 
            cliente.getLastName2(), 
            cliente.getDni(), 
            cliente.getApi_token());
    }
}
