package es.artyhub.banco_back.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;

import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.repository.CuentaRepository;
import es.artyhub.banco_back.domain.service.CuentaService;
import jakarta.transaction.Transactional;

public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }
    
    @Override
    public Cuenta findById(Long id) {
        
        if(id == null) {
            throw new ValidationException("Id no valido");
        }

        Cuenta cuenta = cuentaRepository.findById(id);
        
        if(cuenta == null) {
            throw new ResourceNotFoundException("Cuenta no encontrada");
        }

        return cuenta;
    }

    @Override
    public Cuenta findByIban(String iban) {

        if(iban == null) {
            throw new ValidationException("Iban no valido");
        }

        return cuentaRepository.findByIban(iban);
    }

    @Override
    public Cuenta findByNumeroTarjeta(String numeroTarjeta) {
        return cuentaRepository.findByNTarjeta(numeroTarjeta);
    }

    @Override
    public List<Cuenta> findByClienteId(Long cliente_id) {
        
        if(cliente_id == null) {
            throw new ValidationException("Id del cliente no valido");
        }

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(cliente_id);
        
        if(cuentas == null) {
            throw new ResourceNotFoundException("No se ha encontrado ninguna cuenta");
        }

        return cuentas;
    }

    @Override
    public List<Cuenta> findAll() {

        List<Cuenta> cuentas = cuentaRepository.findAll();
        
        if(cuentas == null) {
            throw new ResourceNotFoundException("No se ha encontrado ninguna cuenta");
        }

        return cuentas;
    }

    @Override
    public List<Cuenta> findByToken(String token) {
        return cuentaRepository.findByToken(token);
    }

    @Transactional
    @Override
    public Cuenta save(Cuenta cuenta) {
        if (cuenta == null) {
            throw new ValidationException("La cuenta no puede ser nula");
        }
        return cuentaRepository.save(cuenta);
    }
    @Transactional
    @Override
    public void updateSaldo(Cuenta cuenta, BigDecimal importe, TipoMovimiento tipoMovimiento) {

        BigDecimal saldo = cuenta.getSaldo();
        BigDecimal saldoFinal;
        if(tipoMovimiento.equals(TipoMovimiento.DEBE)){
            saldoFinal = saldo.subtract(importe);
        } else {
            saldoFinal = saldo.add(importe);
        }
        cuenta.setSaldo(saldoFinal);
        cuentaRepository.save(cuenta);
    }
}
