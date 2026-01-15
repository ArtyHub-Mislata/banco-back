package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.SesionRepository;
import es.artyhub.banco_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.banco_back.persistence.repository.mapper.ClienteMapper;

public class SesionRepositoryImpl implements SesionRepository {
    private final SesionJpaDao sesionJpaDao;

    public SesionRepositoryImpl(SesionJpaDao sesionJpaDao) {
        this.sesionJpaDao = sesionJpaDao;
    }

    @Override
    public String insertSesion(Long idUser) {
        return sesionJpaDao.createSession(idUser);
    }

    @Override
    public Cliente findByToken(String token) {
        return ClienteMapper
                .getInstance()
                .fromClienteJpaEntityToCliente(sesionJpaDao.findByToken(token).orElseThrow(()-> new ResourceNotFoundException("No e")));
    }

    @Override
    public void logout(String token) {
        sesionJpaDao.logout(token);
    }
}
