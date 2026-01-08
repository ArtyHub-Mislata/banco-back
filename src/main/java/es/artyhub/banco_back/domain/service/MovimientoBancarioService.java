package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.model.MovimientoBancario;

public interface MovimientoBancarioService {
    MovimientoBancario findById(Long id);
    MovimientoBancario findByImporte(Long importe);
    MovimientoBancario findByConcepto(String concepto);
    MovimientoBancario findByCuentaId(Long cuenta_id);
}
