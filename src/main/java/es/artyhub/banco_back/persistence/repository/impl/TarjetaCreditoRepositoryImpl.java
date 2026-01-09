package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;

import java.util.List;

public class TarjetaCreditoRepositoryImpl implements TarjetaCreditoRepository {

    @Override
    public TarjetaCredito findById(Long id) {
        return null;
    }

    @Override
    public TarjetaCredito findByNumeroTarjeta(String numeroTarjeta) {
        return null;
    }

    @Override
    public List<TarjetaCredito> findByCuentaId(Long cuenta_id) {
        return null;
    }

    @Override
    public List<TarjetaCredito> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
