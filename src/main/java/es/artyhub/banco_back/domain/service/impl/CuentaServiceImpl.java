package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.service.CuentaService;

public class CuentaServiceImpl implements CuentaService {
    
    @Override
    public Cuenta findById(Long id) {
        return null;
    }

    @Override
    public Cuenta findByIban(String iban) {
        return null;
    }

    @Override
    public Cuenta findByClienteId(Long cliente_id) {
        return null;
    }
}
