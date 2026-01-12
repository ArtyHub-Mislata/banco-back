package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.domain.service.ClienteService;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    @Override
    public Cliente findById(Long id) {
        
        if(id == null) {
            throw new ValidationException("Id no valido");
        }
        
        if(clienteRepository.findById(id) == null) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        return clienteRepository.findById(id);
    }

    @Override
    public Cliente findByLogin(String login) {

        if(login == null) {
            throw new ValidationException("Login no valido");
        }

        if(clienteRepository.findByLogin(login) == null) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        return clienteRepository.findByLogin(login);
    }
}
