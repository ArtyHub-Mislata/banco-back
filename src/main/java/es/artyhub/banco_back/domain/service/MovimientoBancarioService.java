package es.artyhub.banco_back.domain.service;

import java.math.BigDecimal;
import java.util.List;

import es.artyhub.banco_back.domain.model.MovimientoBancario;

public interface MovimientoBancarioService {
    MovimientoBancario findById(Long id);
    MovimientoBancario findByImporte(BigDecimal importe);
    MovimientoBancario findByConcepto(String concepto);
    List<MovimientoBancario> findByCuentaId(Long cuenta_id);
    List<MovimientoBancario> findAll();
    MovimientoBancario saveMovimientoBancario(MovimientoBancario movimientoBancario);

}
