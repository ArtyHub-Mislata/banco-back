package es.artyhub.banco_back.persistence.repository.impl;

import java.util.List;

import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;
import es.artyhub.banco_back.persistence.repository.mapper.ClienteMapper;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteJpaDao clienteJpaDao;

    public ClienteRepositoryImpl(ClienteJpaDao clienteJpaDao) {
        this.clienteJpaDao = clienteJpaDao;
    }

    @Override
    public Cliente findById(Long id) {
        return ClienteMapper.getInstance().fromClienteJpaEntityToCliente(clienteJpaDao.findById(id));
    }

    @Override
    public List<Cliente> findAll() {
        return ClienteMapper.getInstance().fromClienteJpaEntityListToClienteList(clienteJpaDao.findAll());
    }

    @Override
    public Cliente findByLogin(String login) {
        return ClienteMapper.getInstance().fromClienteJpaEntityToCliente(clienteJpaDao.findByLogin(login));
    }

    @Override
    public Boolean clienAndApiTokenCorrect(AutorizacionDto autorizacionDto) {
        return clienteJpaDao.userAndApiTokenCorrect(autorizacionDto);
    }
}
