package es.artyhub.banco_back.domain.repository;

import java.util.List;

import es.artyhub.banco_back.domain.model.Cuenta;

public interface CuentaRepository {
    Cuenta findById(Long id);
    Cuenta findByIban(String iban);
    List<Cuenta> findByClienteId(Long cliente_id);
    List<Cuenta> findAll();
    Cuenta save(Cuenta cuenta);
    Cuenta findByNumeroTarjeta(String numeroTarjeta);
}
