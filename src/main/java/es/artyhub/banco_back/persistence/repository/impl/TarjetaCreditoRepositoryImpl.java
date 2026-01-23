package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;
import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
import es.artyhub.banco_back.persistence.repository.mapper.TarjetaCreditoMapper;

import java.util.List;

public class TarjetaCreditoRepositoryImpl implements TarjetaCreditoRepository {

    private final TarjetaCreditoJpaDao tarjetaCreditoJpaDao;

    public TarjetaCreditoRepositoryImpl(TarjetaCreditoJpaDao tarjetaCreditoJpaDao) {
        this.tarjetaCreditoJpaDao = tarjetaCreditoJpaDao;
    }

    @Override
    public TarjetaCredito findById(Long id) {
        return TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCredito(tarjetaCreditoJpaDao.findById(id));
    }

    @Override
    public TarjetaCredito findByNumeroTarjeta(String numeroTarjeta) {
        return TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCredito(tarjetaCreditoJpaDao.findByNumeroTarjeta(numeroTarjeta));
    }

    @Override
    public List<TarjetaCredito> findByCuentaId(Long cuenta_id) {
        return tarjetaCreditoJpaDao.findByCuentaId(cuenta_id)
                .stream()
                .map(TarjetaCreditoMapper.getInstance()::fromTarjetaCreditoJpaEntityToTarjetaCredito)
                .toList();
    }

    @Override
    public List<TarjetaCredito> findAll() {
        return tarjetaCreditoJpaDao.findAll()
                .stream()
                .map(TarjetaCreditoMapper.getInstance()::fromTarjetaCreditoJpaEntityToTarjetaCredito)
                .toList();
    }

    @Override
    public List<TarjetaCredito> findAllOfUser(String token) {
        return tarjetaCreditoJpaDao.findAllOfUser(token)
                .stream()
                .map(TarjetaCreditoMapper.getInstance():: fromTarjetaCreditoJpaEntityToTarjetaCredito)
                .toList();
    }

    @Override
    public Boolean tarjetaPerteneceAUsuario(Long idTarjeta, String token) {
        return tarjetaCreditoJpaDao.tarjetaPerteneceAUsuario(idTarjeta, token);
    }
}
