package es.artyhub.banco_back.persistence.repository.mapper;

import es.artyhub.banco_back.domain.dto.ClienteDto;
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

    public ClienteDto fromClienteJpaEntityToClienteDto(ClienteJpaEntity cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDto(
            cliente.getId(), 
            cliente.getLogin(), 
            cliente.getPassword(), 
            cliente.getName(), 
            cliente.getLastName1(), 
            cliente.getLastName2(), 
            cliente.getDni(), 
            cliente.getApi_token());
    }

    public ClienteJpaEntity fromClienteDtoToClienteJpaEntity(ClienteDto clienteDto) {
        if (clienteDto == null) {
            return null;
        }
        return new ClienteJpaEntity(
            clienteDto.getId(), 
            clienteDto.getLogin(), 
            clienteDto.getPassword(), 
            clienteDto.getName(), 
            clienteDto.getLastName1(), 
            clienteDto.getLastName2(), 
            clienteDto.getDni(), 
            clienteDto.getApi_token());
    }
}
