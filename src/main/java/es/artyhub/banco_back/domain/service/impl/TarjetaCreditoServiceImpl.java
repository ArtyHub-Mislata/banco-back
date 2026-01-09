package es.artyhub.banco_back.domain.service.impl;

import java.util.List;

import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;
import jakarta.validation.ValidationException;

public class TarjetaCreditoServiceImpl implements TarjetaCreditoService {

    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    public TarjetaCreditoServiceImpl(TarjetaCreditoRepository tarjetaCreditoRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
    }
    
    @Override
    public TarjetaCredito findById(Long id) {
        
        if(id == null) {
            throw new ValidationException("Id no valido");
        }

        if(tarjetaCreditoRepository.findById(id) == null) {
            throw new ResourceNotFoundException("Tarjeta no encontrada");
        }

        return tarjetaCreditoRepository.findById(id);
    }

    @Override
    public TarjetaCredito findByNumeroTarjeta(String numeroTarjeta) {
        
        if(numeroTarjeta == null) {
            throw new ValidationException("Numero de tarjeta no valido");
        }

        if(tarjetaCreditoRepository.findByNumeroTarjeta(numeroTarjeta) == null) {
            throw new ResourceNotFoundException("Tarjeta no encontrada");
        }

        return tarjetaCreditoRepository.findByNumeroTarjeta(numeroTarjeta);
    }

    @Override
    public List<TarjetaCredito> findByCuentaId(Long cuenta_id) {
        
        if(cuenta_id == null) {
            throw new ValidationException("Id de la cuenta no valido");
        }

        if(tarjetaCreditoRepository.findByCuentaId(cuenta_id) == null) {
            throw new ResourceNotFoundException("Tarjetas no encontradas");
        }

        return tarjetaCreditoRepository.findByCuentaId(cuenta_id);
    }

    @Override
    public List<TarjetaCredito> findAll() {
        
        if(tarjetaCreditoRepository.findAll() == null) {
            throw new ResourceNotFoundException("Tarjetas no encontradas");
        }

        return tarjetaCreditoRepository.findAll();
    }

    @Override
    public Boolean tarjetaIsValid(String nTarjeta) {
        
        if(nTarjeta == null) {
            throw new ValidationException("Numero de tarjeta nulo");
        }

        if(nTarjeta.length() != 16) {
            throw new ValidationException("Numero de tarjeta invalido");
        }

        if(tarjetaCreditoRepository.findByNumeroTarjeta(nTarjeta) == null) {
            throw new ResourceNotFoundException("Tarjeta no encontrada");
        }

        return true;
    }
}
