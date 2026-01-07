package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;

import java.math.BigInteger;

public class TarjetaCreditoRepositoryImpl implements TarjetaCreditoRepository {

    @Override
    public TarjetaCredito findById(Long id) {
        return null;
    }

    @Override
    public TarjetaCredito findByNumeroTarjeta(BigInteger numeroTarjeta) {
        return null;
    }

    @Override
    public TarjetaCredito findByCuentaId(Long cuenta_id) {
        return null;
    }
}
