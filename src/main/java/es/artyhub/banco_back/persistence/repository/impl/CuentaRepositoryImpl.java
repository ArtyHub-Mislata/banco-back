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
    public Cuenta findByNTarjeta(String nTarjeta) {
        return CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(cuentaJpaDao.findByNDeTarjeta(nTarjeta));
    }

    @Override
    public List<Cuenta> findByClienteId(Long cliente_id) {
        return cuentaJpaDao.findByClienteId(cliente_id)
                .stream()
                .map(CuentaMapper.getInstance()::fromCuentaJpaEntityToCuenta)
                .toList();
    }

    @Override
    public List<Cuenta> findAll() {
        return cuentaJpaDao.findAll()
                .stream()
                .map(CuentaMapper.getInstance()::fromCuentaJpaEntityToCuenta)
                .toList();
    }

    @Override
    public List<Cuenta> findByToken(String token) {
        return cuentaJpaDao.findByToken(token)
                .stream()
                .map(CuentaMapper.getInstance()::fromCuentaJpaEntityToCuenta)
                .toList();
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        if(cuenta.getId() == null){
            return CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(
                    cuentaJpaDao.insert(CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(cuenta))
            );
        } else {
            return CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(
                    cuentaJpaDao.update(CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(cuenta))
            );
        }
    }
}
