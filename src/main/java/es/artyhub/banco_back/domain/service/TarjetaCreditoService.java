package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import java.math.BigInteger;

public interface TarjetaCreditoService {
    TarjetaCredito findById(Long id);
    TarjetaCredito findByNumeroTarjeta(BigInteger numeroTarjeta);
    TarjetaCredito findByCuentaId(Long cuenta_id);
}
