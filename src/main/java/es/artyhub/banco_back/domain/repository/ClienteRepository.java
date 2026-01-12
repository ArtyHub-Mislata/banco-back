package es.artyhub.banco_back.domain.repository;

import java.util.List;

import es.artyhub.banco_back.domain.model.Cliente;

public interface ClienteRepository {
    Cliente findById(Long id);
    List<Cliente> findAll();
    Cliente findByLogin(String login);
}