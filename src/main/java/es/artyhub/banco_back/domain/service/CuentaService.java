package es.artyhub.banco_back.domain.service;

import java.math.BigDecimal;
import java.util.List;

import es.artyhub.banco_back.domain.model.Cuenta;

public interface CuentaService {
    Cuenta findById(Long id);
    Cuenta findByIban(String iban);
    List<Cuenta> findByClienteId(Long cliente_id);
    List<Cuenta> findAll();
    Boolean saldoIsEnough(BigDecimal importe, String iban);
    void updateSaldo(Cuenta cuenta, BigDecimal importe);
    Cuenta save(Cuenta cuenta);
}
