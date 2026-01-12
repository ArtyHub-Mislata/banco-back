package es.artyhub.banco_back.persistence.repository.impl;

import java.util.List;

import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.repository.CuentaRepository;
import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;

public class CuentaRepositoryImpl implements CuentaRepository {
    private final CuentaJpaDao cuentaJpaDao;

    public CuentaRepositoryImpl(CuentaJpaDao cuentaJpaDao) {
        this.cuentaJpaDao = cuentaJpaDao;
    }

    @Override
    public Cuenta findById(Long id) {
        return null;
    }

    @Override
    public Cuenta findByIban(String iban) {
        return null;
    }

    @Override
    public List<Cuenta> findByClienteId(Long cliente_id) {
        return null;
    }

    @Override
    public List<Cuenta> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
