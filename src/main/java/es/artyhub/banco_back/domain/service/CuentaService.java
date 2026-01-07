package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.model.Cuenta;

public interface CuentaService {
    Cuenta findById(Long id);
    Cuenta findByIban(String iban);
    Cuenta findByClienteId(Long cliente_id);
}
