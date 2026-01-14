package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.SesionRepository;
import es.artyhub.banco_back.persistence.dao.jpa.SesionJpaDao;

public class SesionRepositoryImpl implements SesionRepository {
    private final SesionJpaDao sesionJpaDao;

    public SesionRepositoryImpl(SesionJpaDao sesionJpaDao) {
        this.sesionJpaDao = sesionJpaDao;
    }

    @Override
    public String insertSesion(Long idUser) {
        return sesionJpaDao.;
    }

    @Override
    public Cliente findByToken(String token) {
        return null;
    }

    @Override
    public void logout(String token) {

    }
}
