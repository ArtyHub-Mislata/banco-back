package es.artyhub.banco_back.domain.repository;

import es.artyhub.banco_back.domain.model.MovimientoBancario;

public interface MovimientoBancarioRepository {
    MovimientoBancario findById(Long id);
    MovimientoBancario findByImporte(Long importe);
    MovimientoBancario findByConcepto(String concepto);
    MovimientoBancario findByCuentaId(Long cuenta_id);
}
