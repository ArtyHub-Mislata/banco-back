package es.artyhub.banco_back.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import es.artyhub.banco_back.domain.model.MovimientoBancario;

public interface MovimientoBancarioRepository {
    MovimientoBancario findById(Long id);
    MovimientoBancario findByImporte(BigDecimal importe);
    MovimientoBancario findByConcepto(String concepto);
    List<MovimientoBancario> findByCuentaId(Long cuenta_id);
    List<MovimientoBancario> findAll();
    MovimientoBancario save(MovimientoBancario movimientoBancario, Long cuentaId);
    List<MovimientoBancario> findAllOfTarjeta(Long tarjetaId);

}