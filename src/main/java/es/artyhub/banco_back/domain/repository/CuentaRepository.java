package es.artyhub.banco_back.domain.repository;

import es.artyhub.banco_back.domain.model.Cuenta;

public interface CuentaRepository {
    Cuenta findById(Long id);
    Cuenta findByIban(String iban);
    Cuenta findByClienteId(Long cliente_id);
}
