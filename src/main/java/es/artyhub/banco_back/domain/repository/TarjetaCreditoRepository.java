package es.artyhub.banco_back.domain.repository;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import java.math.BigInteger;

public interface TarjetaCreditoRepository {
    TarjetaCredito findById(Long id);
    TarjetaCredito findByNumeroTarjeta(BigInteger numeroTarjeta);
    TarjetaCredito findByCuentaId(Long cuenta_id);
}
