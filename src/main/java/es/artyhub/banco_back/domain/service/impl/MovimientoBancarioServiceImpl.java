package es.artyhub.banco_back.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;
import jakarta.transaction.Transactional;

public class MovimientoBancarioServiceImpl implements MovimientoBancarioService {
    
    private final MovimientoBancarioRepository movimientoBancarioRepository;

    public MovimientoBancarioServiceImpl(MovimientoBancarioRepository movimientoBancarioRepository) {
        this.movimientoBancarioRepository = movimientoBancarioRepository;
    }
    
    @Override
    public MovimientoBancario findById(Long id) {
        
        if(id == null) {
            throw new ValidationException("Id no valido");
        }

        MovimientoBancario movimientoBancario = movimientoBancarioRepository.findById(id);

        if(movimientoBancario == null) {
            throw new ResourceNotFoundException("Movimiento bancario no encontrado");
        }

        return movimientoBancario;
    }

    @Override
    public MovimientoBancario findByImporte(BigDecimal importe) {
        
        if(importe == null) {
            throw new ValidationException("Importe no valido");
        }

        MovimientoBancario movimientoBancario = movimientoBancarioRepository.findByImporte(importe);

        if(movimientoBancario == null) {
            throw new ResourceNotFoundException("Movimiento bancario no encontrado");
        }

        return movimientoBancario;
    }

    @Override
    public MovimientoBancario findByConcepto(String concepto) {
        
        if(concepto == null) {
            throw new ValidationException("Concepto no valido");
        }

        MovimientoBancario movimientoBancario = movimientoBancarioRepository.findByConcepto(concepto);

        if(movimientoBancario == null) {
            throw new ResourceNotFoundException("Movimiento bancario no encontrado");
        }

        return movimientoBancario;
    }

    @Override
    public List<MovimientoBancario> findByCuentaId(Long cuenta_id) {
        
        if(cuenta_id == null) {
            throw new ValidationException("Id de la cuenta no valido");
        }

        List<MovimientoBancario> movimientoBancario = movimientoBancarioRepository.findByCuentaId(cuenta_id);

        if(movimientoBancario == null) {
            throw new ResourceNotFoundException("Movimientos bancarios no encontrados");
        }

        return movimientoBancario;
    }

    @Override
    public List<MovimientoBancario> findAll() {
        
        List<MovimientoBancario> movimientoBancario = movimientoBancarioRepository.findAll();

        if(movimientoBancario == null) {
            throw new ResourceNotFoundException("Movimientos bancarios no encontrados");
        }

        return movimientoBancario;
    }
    @Transactional
    @Override
    public MovimientoBancario saveMovimientoBancario(MovimientoBancario movimientoBancario, Long cuentaId) {
        return movimientoBancarioRepository.save(movimientoBancario, cuentaId);
    }

    @Override
    public List<MovimientoBancario> findByTarjetaId(Long idTarjeta) {
        return movimientoBancarioRepository.findAllOfTarjeta(idTarjeta);
    }
}
