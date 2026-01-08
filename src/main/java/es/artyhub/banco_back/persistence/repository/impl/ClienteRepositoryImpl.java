package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteJpaDao clienteJpaDao;

    public ClienteRepositoryImpl(ClienteJpaDao clienteJpaDao) {
        this.clienteJpaDao = clienteJpaDao;
    }

    @Override
    public Cliente findById(Long id) {
        return null;
    }

    @Override
    public Cliente findByLogin(String login) {
        return null;
    }

    @Override
    public Cliente findByToken(String api_token) {
        return null;
    }
}
