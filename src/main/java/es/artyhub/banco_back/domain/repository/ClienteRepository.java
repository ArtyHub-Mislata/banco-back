package es.artyhub.banco_back.domain.repository;

import es.artyhub.banco_back.domain.model.Cliente;

public interface ClienteRepository {
    Cliente findById(Long id);
    Cliente findByLogin(String login);
}