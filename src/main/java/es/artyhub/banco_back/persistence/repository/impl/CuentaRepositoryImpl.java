package es.artyhub.banco_back.persistence.repository.impl;

import java.util.List;

import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.repository.CuentaRepository;
import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.repository.mapper.CuentaMapper;

public class CuentaRepositoryImpl implements CuentaRepository {
    private final CuentaJpaDao cuentaJpaDao;

    public CuentaRepositoryImpl(CuentaJpaDao cuentaJpaDao) {
        this.cuentaJpaDao = cuentaJpaDao;
    }

    @Override
    public Cuenta findById(Long id) {
        return CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaDao.findById(id));
    }

    @Override
    public Cuenta findByIban(String iban) {
        return CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaDao.findByIban(iban));
    }

    @Override
    public List<Cuenta> findByClienteId(Long cliente_id) {
        return CuentaMapper.getInstance().fromCuentaJpaEntityListToCuentaList(cuentaJpaDao.findByClienteId(cliente_id));
    }

    @Override
    public List<Cuenta> findAll() {
        return CuentaMapper.getInstance().fromCuentaJpaEntityListToCuentaList(cuentaJpaDao.findAll());
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaDao.save(CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(cuenta)));
    }

    @Override
    public Cuenta findByNumeroTarjeta(String numeroTarjeta) {
        return CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaDao.findByNumeroTarjeta(numeroTarjeta));
    }
}
