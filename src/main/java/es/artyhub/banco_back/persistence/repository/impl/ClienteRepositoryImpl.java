package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.ClienteRepository;

public class ClienteRepositoryImpl implements ClienteRepository {

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
