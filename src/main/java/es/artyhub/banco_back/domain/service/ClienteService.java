package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.model.Cliente;

public interface ClienteService {
    Cliente findById(Long id);
    Cliente findByLogin(String login);
}