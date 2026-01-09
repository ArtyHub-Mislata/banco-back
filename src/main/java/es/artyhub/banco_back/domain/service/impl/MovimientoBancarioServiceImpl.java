package es.artyhub.banco_back.domain.service.impl;

import java.util.Date;
import java.util.List;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;

public class MovimientoBancarioServiceImpl implements MovimientoBancarioService {
    
    private final MovimientoBancarioRepository movimientoBancarioRepository;
    private final TarjetaCreditoServiceImpl tarjetaCreditoServiceImpl;

    public MovimientoBancarioServiceImpl(MovimientoBancarioRepository movimientoBancarioRepository, TarjetaCreditoServiceImpl tarjetaCreditoServiceImpl) {
        this.movimientoBancarioRepository = movimientoBancarioRepository;
        this.tarjetaCreditoServiceImpl = tarjetaCreditoServiceImpl;
    }
    
    @Override
    public MovimientoBancario findById(Long id) {
        
        if(id == null) {
            throw new ValidationException("Id no valido");
        }

        if(movimientoBancarioRepository.findById(id) == null) {
            throw new ResourceNotFoundException("Movimiento bancario no encontrado");
        }

        return movimientoBancarioRepository.findById(id);
    }

    @Override
    public MovimientoBancario findByImporte(Long importe) {
        
        if(importe == null) {
            throw new ValidationException("Importe no valido");
        }

        if(movimientoBancarioRepository.findByImporte(importe) == null) {
            throw new ResourceNotFoundException("Movimiento bancario no encontrado");
        }

        return movimientoBancarioRepository.findByImporte(importe);
    }

    @Override
    public MovimientoBancario findByConcepto(String concepto) {
        
        if(concepto == null) {
            throw new ValidationException("Concepto no valido");
        }

        if(movimientoBancarioRepository.findByConcepto(concepto) == null) {
            throw new ResourceNotFoundException("Movimiento bancario no encontrado");
        }

        return movimientoBancarioRepository.findByConcepto(concepto);
    }

    @Override
    public List<MovimientoBancario> findByCuentaId(Long cuenta_id) {
        
        if(cuenta_id == null) {
            throw new ValidationException("Id de la cuenta no valido");
        }

        if(movimientoBancarioRepository.findByCuentaId(cuenta_id) == null) {
            throw new ResourceNotFoundException("Movimientos bancarios no encontrados");
        }

        return movimientoBancarioRepository.findByCuentaId(cuenta_id);
    }

    @Override
    public List<MovimientoBancario> findAll() {
        
        if(movimientoBancarioRepository.findAll() == null) {
            throw new ResourceNotFoundException("Movimientos bancarios no encontrados");
        }

        return movimientoBancarioRepository.findAll();
    }

    @Override
    public MovimientoBancario saveMovimientoBancario(PagoTarjetaDto pagoTarjetaDto) {

        if(pagoTarjetaDto == null) {
            throw new ValidationException("Pago tarjeta nulo");
        }

        MovimientoBancario movimientoBancario = new MovimientoBancario();

        TarjetaCredito tarjetaCredito = tarjetaCreditoServiceImpl.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());

        if(tarjetaCredito == null) {
            throw new ResourceNotFoundException("Tarjeta de credito no encontrada");
        }

        movimientoBancario.setTipoMovimiento(TipoMovimiento.DEBE);
        movimientoBancario.setOrigenMovimiento(OrigenMovimiento.TARJETABANCARIA);
        movimientoBancario.setTarjetaCredito(tarjetaCredito);
        movimientoBancario.setFecha(new Date());
        movimientoBancario.setImporte(pagoTarjetaDto.pago().importe());
        movimientoBancario.setConcepto(pagoTarjetaDto.pago().concepto());
        movimientoBancario.setCuenta(tarjetaCredito.getCuenta());

        return movimientoBancario;
    }
}
