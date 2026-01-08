package es.artyhub.banco_back.domain.service.impl;

import java.math.BigInteger;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;

public class TarjetaCreditoServiceImpl implements TarjetaCreditoService {
    
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
