package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import java.util.List;

public interface TarjetaCreditoService {
    TarjetaCredito findById(Long id);
    TarjetaCredito findByNumeroTarjeta(String numeroTarjeta);
    List<TarjetaCredito> findByCuentaId(Long cuenta_id);
    List<TarjetaCredito> findAll();
    Boolean tarjetaIsValid(String nTarjeta);
}
