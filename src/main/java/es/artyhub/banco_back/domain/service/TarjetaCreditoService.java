package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.dto.OrigenDto;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import java.util.List;

public interface TarjetaCreditoService {

    TarjetaCredito findById(Long id);
    TarjetaCredito findByNumeroTarjeta(String numeroTarjeta);
    List<TarjetaCredito> findByCuentaId(Long cuenta_id);
    List<TarjetaCredito> findAll();
    List<TarjetaCredito> findAllOfUser(String token);
    Boolean tarjetaIsValid(OrigenDto tarjetaOrigen, TarjetaCredito tarjetaCredito);
    Boolean tarjetaPerteneceAUsuario(Long idTarjeta, String token);
}
